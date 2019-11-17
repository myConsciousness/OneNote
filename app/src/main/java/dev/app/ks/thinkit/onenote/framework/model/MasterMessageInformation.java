package dev.app.ks.thinkit.onenote.framework.model;

import android.content.Context;
import android.database.Cursor;

import dev.app.ks.thinkit.onenote.framework.ModelList;
import dev.app.ks.thinkit.onenote.framework.ModelMap;
import dev.app.ks.thinkit.onenote.framework.model.holder.SelectHolder;
import dev.app.ks.thinkit.onenote.framework.model.property.MasterMessageColumnKey;
import dev.app.ks.thinkit.onenote.model.property.Table;
import dev.app.ks.thinkit.onenote.property.MessageID;
import dev.app.ks.thinkit.onenote.property.MessageLanguageKind;

public final class MasterMessageInformation extends BaseModel {

    /**
     * 定数 : クラス名を保持する。
     */
    private static final String TAG = MasterMessageInformation.class.getName();

    /**
     * 定数 : 改行コードに置換するためのデリミタを保持する。
     */
    private static final String MESSAGE_DELIMITER = "@";

    /**
     * 変数 : 当該クラスのインスタンスを格納する。
     * 当該クラスにシングルトンパターンを適用するためnullで初期化する。
     *
     * @see #getInstance(Context)
     */
    private static MasterMessageInformation thisInstance = null;

    /**
     * 当該クラスのコンストラクタ。
     * 当該クラスにシングルトンパターンを適用するため修飾子をprivate指定する。
     *
     * @param context アプリケーション情報。
     * @see #getInstance(Context)
     */
    private MasterMessageInformation(Context context) {
        super(context, Table.MasterMessageInformation);
    }

    /**
     * 当該クラスのインスタンスを返却する。
     * 初回実行時に当該クラスのインスタンスを生成した後に返却し、
     * 初回以降に実行された際には初回時に生成されたインスタンスを返却する。
     *
     * @param context アプリケーション情報。
     * @return 当該クラスのインスタンス。
     * @see #MasterMessageInformation(Context)
     */
    public static MasterMessageInformation getInstance(Context context) {

        if (thisInstance == null) {
            thisInstance = new MasterMessageInformation(context);
        }

        return thisInstance;
    }

    public void searchDisplayMessage(MessageID messageId, MessageLanguageKind messageLanguageKind) {

        String[] selections = new String[]{
                String.format(BaseModel.FORMAT_WHERE_CLAUSE, MasterMessageColumnKey.MessageId.getKeyName()),
                String.format(BaseModel.FORMAT_WHERE_CLAUSE, MasterMessageColumnKey.LanguageKind.getKeyName())
        };

        SelectHolder selectHolder = new SelectHolder();
        selectHolder.setSelection(String.join(Operand.AND.getValue(), selections));
        selectHolder.setSelectionArgs(new String[]{messageId.getMessageId(), messageLanguageKind.getKindName()});

        super.select(selectHolder);
    }

    @Override
    protected ModelList<ModelMap<MasterMessageColumnKey, Object>> onPostSelect(Cursor cursor) {

        ModelList<ModelMap<MasterMessageColumnKey, Object>> modelInfo = new ModelList<>(cursor.getCount());

        if (cursor.moveToFirst()) {
            ModelMap<MasterMessageColumnKey, Object> modelMap = new ModelMap<>(MasterMessageColumnKey.class);
            MasterMessageColumnKey[] masterMessageColumnKeys = MasterMessageColumnKey.values();

            for (MasterMessageColumnKey column : masterMessageColumnKeys) {
                column.setModelMap(cursor, modelMap);
            }

            modelInfo.add(modelMap);
        }

        return modelInfo;
    }

    @Override
    public ModelList<ModelMap<MasterMessageColumnKey, Object>> getModelInfo() {
        return super.modelInfo;
    }

    /**
     * 検索結果で取得したマスタデータ情報からメッセージを返却します。
     * メッセージを取得する場合は検索処理を終えてから当該メソッドを実行してください。
     *
     * @return メッセージ
     * @see #searchDisplayMessage(MessageID, MessageLanguageKind)
     */
    public String getMessage() {
        String message = getModelInfo().get(0).getString(MasterMessageColumnKey.Message);
        return message.replaceAll(MESSAGE_DELIMITER, "\n");
    }
}
