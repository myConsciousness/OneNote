package dev.app.ks.thinkit.onenote.framework.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import dev.app.ks.thinkit.onenote.framework.IModelMapKey;
import dev.app.ks.thinkit.onenote.framework.ModelList;
import dev.app.ks.thinkit.onenote.framework.ModelMap;
import dev.app.ks.thinkit.onenote.framework.model.holder.CurrentApplicationHolder;
import dev.app.ks.thinkit.onenote.framework.model.holder.InsertHolder;
import dev.app.ks.thinkit.onenote.framework.model.property.CurrentApplicationColumnKey;
import dev.app.ks.thinkit.onenote.model.property.Table;

public final class CurrentApplicationInformation extends BaseModel {

    private static final String TAG = CurrentApplicationInformation.class.getName();

    private static CurrentApplicationInformation thisInstance = null;

    private CurrentApplicationInformation(Context context) {
        super(context, Table.CurrentApplicationInformation);
    }

    public static CurrentApplicationInformation getInstance(Context context) {

        if (thisInstance == null) {
            thisInstance = new CurrentApplicationInformation(context);
        }

        return thisInstance;
    }

    public void selectByPrimaryKey(ConfigName primaryKey) {
        super.selectByPrimaryKey(CurrentApplicationColumnKey.ConfigName, primaryKey.getKeyName());
    }

    @Override
    protected ModelList<ModelMap<CurrentApplicationColumnKey, Object>> onPostSelect(Cursor cursor) {

        ModelList<ModelMap<CurrentApplicationColumnKey, Object>> modelInfo = new ModelList<>(cursor.getCount());

        if (cursor.moveToFirst()) {
            ModelMap<CurrentApplicationColumnKey, Object> modelMap = new ModelMap<>(CurrentApplicationColumnKey.class);
            CurrentApplicationColumnKey[] currentApplicationColumnKeys = CurrentApplicationColumnKey.values();

            // 一意制約検索のため検索結果は一件のみ
            for (CurrentApplicationColumnKey column : currentApplicationColumnKeys) {
                column.setModelMap(cursor, modelMap);
            }

            modelInfo.add(modelMap);
        }

        return modelInfo;
    }

    public void replace(CurrentApplicationHolder currentApplicationHolder) {

        InsertHolder insertHolder = new InsertHolder();
        ContentValues contentValues = insertHolder.getContentValues();
        CurrentApplicationColumnKey[] currentApplicationColumnKeys = CurrentApplicationColumnKey.values();

        for (CurrentApplicationColumnKey column : currentApplicationColumnKeys) {
            column.setContentValues(contentValues, currentApplicationHolder);
        }

        super.replace(insertHolder);
    }

    @Override
    public ModelList<ModelMap<CurrentApplicationColumnKey, Object>> getModelInfo() {
        return super.modelInfo;
    }

    public String getConfigValue() {
        return getModelInfo().get(0).getString(CurrentApplicationColumnKey.ConfigValue);
    }

    public enum ConfigName implements IModelMapKey {
        UsesWifiOnCommunicate(Key.uses_wifi_on_communicate);

        private final Key key;

        ConfigName(Key key) {
            this.key = key;
        }

        public String getConfigName() {
            return key.name();
        }

        @Override
        public String getKeyName() {
            return key.name();
        }

        private enum Key {
            uses_wifi_on_communicate,
        }
    }
}
