package dev.app.ks.thinkit.onenote.framework.model;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import java.util.List;

import dev.app.ks.thinkit.onenote.framework.IModelMapKey;
import dev.app.ks.thinkit.onenote.framework.ModelList;
import dev.app.ks.thinkit.onenote.framework.ModelMap;
import dev.app.ks.thinkit.onenote.framework.StringChecker;
import dev.app.ks.thinkit.onenote.framework.model.adapter.DatabaseAdapter;
import dev.app.ks.thinkit.onenote.framework.model.holder.DeleteHolder;
import dev.app.ks.thinkit.onenote.framework.model.holder.InsertHolder;
import dev.app.ks.thinkit.onenote.framework.model.holder.SelectHolder;
import dev.app.ks.thinkit.onenote.model.property.Table;

public abstract class BaseModel<E extends Enum<E> & IModelMapKey> {

    /**
     * 定数 : where句のフォーマットを保持する。
     */
    protected static final String FORMAT_WHERE_CLAUSE = "%s = ?";
    /**
     * 定数 : クラス名を保持する。
     */
    private static final String TAG = BaseModel.class.getName();
    /**
     * 変数 : 操作するモデル情報を保持する。
     */
    private final Table TABLE;
    /**
     * 変数 : データベース操作を行うアダプターを保持する。
     */
    private final DatabaseAdapter databaseAdapter;
    protected ModelList<ModelMap<E, Object>> modelInfo = new ModelList<>(0);

    /**
     * 当該基底クラスのコンストラクタ。
     *
     * @param context アプリケーション情報。
     * @param table   操作するテーブルの情報。
     * @throws IllegalArgumentException 不正な入力を検知した際に発生します。
     * @see Table
     */
    protected BaseModel(Context context, Table table) {

        if (context == null
                || table == null) {
            // should not be happened
            throw new IllegalArgumentException();
        }

        databaseAdapter = new DatabaseAdapter(context);
        TABLE = table;
    }

    /**
     * 検索処理後に実行する処理を定義する抽象メソッドです。
     * 当該基底クラスを継承するクラスは当該メソッドを実装する必要があります。
     *
     * @param cursor 検索処理の結果。
     * @return 検索後処理が成功した場合は {@code true}、失敗した場合は {@code false}を返却します。
     */
    protected abstract ModelList<ModelMap<E, Object>> onPostSelect(Cursor cursor);

    public abstract ModelList<ModelMap<E, Object>> getModelInfo();

    private void setModelInfo(ModelList<ModelMap<E, Object>> modelInfo) {
        this.modelInfo = modelInfo;
    }

    protected final void selectByPrimaryKey(IModelMapKey primaryKeyName, String primaryKey) {

        if (primaryKeyName == null
                || !StringChecker.isEffectiveString(primaryKey)) {
            // should not be happened
            throw new IllegalArgumentException();
        }

        SelectHolder selectHolder = new SelectHolder();
        selectHolder.setColumns(null);
        selectHolder.setSelection(String.format(FORMAT_WHERE_CLAUSE, primaryKeyName.getKeyName()));
        selectHolder.setSelectionArgs(new String[]{primaryKey});

        select(selectHolder);
    }

    protected final void select(SelectHolder selectHolder) {

        if (selectHolder == null) {
            // should not be happened
            throw new IllegalArgumentException();
        }

        try {
            databaseAdapter.open();

            Cursor cursor = databaseAdapter.getDatabase().query(
                    TABLE.getName(),
                    selectHolder.getColumns(),
                    selectHolder.getSelection(),
                    selectHolder.getSelectionArgs(),
                    selectHolder.getGroupBy(),
                    selectHolder.getHaving(),
                    selectHolder.getOrderBy(),
                    selectHolder.getLimit()
            );

            if (cursor == null) {
                // should not be happened
                throw new SQLException();
            }

            ModelList<ModelMap<E, Object>> modelInfo = onPostSelect(cursor);
            setModelInfo(modelInfo);

        } finally {
            databaseAdapter.close();
        }
    }

    protected final void replaceAll(List<InsertHolder> insertHolderList) {

        if (insertHolderList == null) {
            // should not be happened
            throw new IllegalArgumentException();
        }

        try {
            databaseAdapter.open();
            databaseAdapter.beginTransaction();

            for (InsertHolder insertHolder : insertHolderList) {
                databaseAdapter.getDatabase().replace(
                        TABLE.getName(),
                        insertHolder.getNullColumnHack(),
                        insertHolder.getContentValues()
                );
            }

            databaseAdapter.setTransactionSuccessful();

        } finally {
            databaseAdapter.endTransaction();
            databaseAdapter.close();
        }
    }

    protected final void replace(InsertHolder insertHolder) {

        if (insertHolder == null) {
            // should not be happened
            throw new IllegalArgumentException();
        }

        try {
            databaseAdapter.open();
            databaseAdapter.beginTransaction();

            databaseAdapter.getDatabase().replace(
                    TABLE.getName(),
                    insertHolder.getNullColumnHack(),
                    insertHolder.getContentValues()
            );

            databaseAdapter.setTransactionSuccessful();

        } finally {
            databaseAdapter.endTransaction();
            databaseAdapter.close();
        }
    }

    protected final void insert(InsertHolder insertHolder) {

        if (insertHolder == null) {
            // should not be happened
            throw new IllegalArgumentException();
        }

        try {
            databaseAdapter.open();
            databaseAdapter.beginTransaction();

            databaseAdapter.getDatabase().insert(
                    TABLE.getName(),
                    insertHolder.getNullColumnHack(),
                    insertHolder.getContentValues()
            );

            databaseAdapter.setTransactionSuccessful();

        } finally {
            databaseAdapter.endTransaction();
            databaseAdapter.close();
        }
    }

    protected final void deleteByPrimaryKey(IModelMapKey primaryKeyName, String primaryKey) {

        if (primaryKeyName == null
                || !StringChecker.isEffectiveString(primaryKey)) {
            // should not be happened
            throw new IllegalArgumentException();
        }

        DeleteHolder deleteHolder = new DeleteHolder();
        deleteHolder.setWhereClause(String.format(FORMAT_WHERE_CLAUSE, primaryKeyName.getKeyName()));
        deleteHolder.setWhereArgs(new String[]{primaryKey});

        delete(deleteHolder);
    }


    protected final void delete(DeleteHolder deleteHolder) {

        try {
            databaseAdapter.open();

            databaseAdapter.getDatabase().delete(
                    TABLE.getName(),
                    deleteHolder.getWhereClause(),
                    deleteHolder.getWhereArgs()
            );

        } finally {
            databaseAdapter.close();
        }
    }

    public final boolean isEmpty() {
        return modelInfo.isEmpty();
    }

    /**
     * 検索条件で使用するオペランドを保持する。
     * 処理中で検索条件を作成する際に使用する。
     */
    protected enum Operand {
        AND(" and "),
        OR(" or ");

        private final String value;

        Operand(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
