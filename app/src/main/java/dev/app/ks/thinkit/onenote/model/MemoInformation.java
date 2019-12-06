package dev.app.ks.thinkit.onenote.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import dev.app.ks.thinkit.onenote.framework.IModelMapKey;
import dev.app.ks.thinkit.onenote.framework.ModelList;
import dev.app.ks.thinkit.onenote.framework.ModelMap;
import dev.app.ks.thinkit.onenote.framework.model.BaseModel;
import dev.app.ks.thinkit.onenote.framework.model.holder.InsertHolder;
import dev.app.ks.thinkit.onenote.model.property.MemoColumnKey;
import dev.app.ks.thinkit.onenote.model.property.Table;

public final class MemoInformation extends BaseModel {

    /**
     * クラス名を保持する。
     */
    private static final String TAG = MemoInformation.class.getName();

    /**
     * 当該クラスのインスタンスを格納する。
     * 当該クラスにシングルトンパターンを適用するためnullで初期化する。
     *
     * @see #getInstance(Context)
     */
    private static MemoInformation thisInstance = null;

    /**
     * 当該クラスのコンストラクタ。
     * 当該クラスにシングルトンパターンを適用するため修飾子をprivate指定する。
     *
     * @param context アプリケーション情報。
     * @see #getInstance(Context)
     */
    private MemoInformation(Context context) {
        super(context, Table.MemoInformation);
    }

    /**
     * 当該クラスのインスタンスを返却する。
     * 初回実行時に当該クラスのインスタンスを生成した後に返却し、
     * 初回以降に実行された際には初回時に生成されたインスタンスを返却する。
     *
     * @param context アプリケーション情報。
     * @return 当該クラスのインスタンス。
     * @see #MemoInformation(Context)
     */
    public static MemoInformation getInstance(Context context) {

        if (thisInstance == null) {
            thisInstance = new MemoInformation(context);
        }

        return thisInstance;
    }

    /**
     * プライマリキーを基にレコードの検索処理を行います。
     * 検索結果はモデルリストに格納され、
     * {@code getModelInfo()}を実行することで取得できます。
     *
     * @param primaryKey 主キー。
     * @see BaseModel#selectByPrimaryKey(IModelMapKey, String)
     * @see #onPostSelect(Cursor)
     * @see #getModelInfo()
     */
    public void selectByPrimaryKey(String primaryKey) {
        super.selectByPrimaryKey(MemoColumnKey.MemoName, primaryKey);
    }

    @Override
    protected ModelList<ModelMap<MemoColumnKey, Object>> onPostSelect(Cursor cursor) {

        ModelList<ModelMap<MemoColumnKey, Object>> modelInfo = new ModelList<>(cursor.getCount());

        if (cursor.moveToFirst()) {
            MemoColumnKey[] overviewColumnKeys = MemoColumnKey.values();

            for (int i = 0, countRecords = cursor.getCount(); i < countRecords; i++) {
                ModelMap<MemoColumnKey, Object> modelMap = new ModelMap<>(MemoColumnKey.class);

                for (MemoColumnKey column : overviewColumnKeys) {
                    column.setModelMap(cursor, modelMap);
                }

                modelInfo.add(modelMap);
                cursor.moveToNext();
            }
        }

        return modelInfo;
    }

    /**
     * 渡された引数の情報を基にレコードの挿入処理を実行します。
     * 当該処理に依ってモデルリストは更新されません。
     *
     * @param memoHolder 挿入処理を行う際に必要な情報が格納されたデータクラス。
     * @see BaseModel#insert(InsertHolder)
     */
    public void insert(MemoHolder memoHolder) {

        InsertHolder insertHolder = new InsertHolder();
        ContentValues contentValues = insertHolder.getContentValues();

        MemoColumnKey[] memoColumnKeys = MemoColumnKey.values();

        for (MemoColumnKey column : memoColumnKeys) {
            column.setContentValues(contentValues, memoHolder);
        }

        super.insert(insertHolder);
    }

    /**
     * 渡された引数の情報を基にレコードの挿入処理を実行します。
     * 当該処理に依ってモデルリストは更新されません。
     *
     * @param memoHolder 挿入処理を行う際に必要な情報が格納されたデータクラス。
     * @see BaseModel#replace(InsertHolder)
     */
    public void replace(MemoHolder memoHolder) {

        InsertHolder insertHolder = new InsertHolder();
        ContentValues contentValues = insertHolder.getContentValues();

        MemoColumnKey[] memoColumnKeys = MemoColumnKey.values();

        for (MemoColumnKey column : memoColumnKeys) {
            column.setContentValues(contentValues, memoHolder);
        }

        super.replace(insertHolder);
    }

    @Override
    public ModelList<ModelMap<MemoColumnKey, Object>> getModelInfo() {
        return super.modelInfo;
    }
}
