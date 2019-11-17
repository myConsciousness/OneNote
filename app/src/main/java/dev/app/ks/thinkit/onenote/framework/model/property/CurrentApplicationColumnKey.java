package dev.app.ks.thinkit.onenote.framework.model.property;

import android.content.ContentValues;
import android.database.Cursor;

import dev.app.ks.thinkit.onenote.framework.CalendarHandler;
import dev.app.ks.thinkit.onenote.framework.IModelMapKey;
import dev.app.ks.thinkit.onenote.framework.ModelMap;
import dev.app.ks.thinkit.onenote.framework.model.CursorHandler;
import dev.app.ks.thinkit.onenote.framework.model.holder.CurrentApplicationHolder;

public enum CurrentApplicationColumnKey implements IModelMapKey {
    ConfigName(Key.config_name) {
        @Override
        public void setModelMap(Cursor cursor, ModelMap<CurrentApplicationColumnKey, Object> modelMap) {
            modelMap.put(this, CursorHandler.getStringOrThrow(cursor, getKeyName()));
        }

        @Override
        public void setContentValues(ContentValues contentValues, CurrentApplicationHolder currentApplicationHolder) {
            contentValues.put(getKeyName(), currentApplicationHolder.getConfigName());
        }
    },

    ConfigValue(Key.config_value) {
        @Override
        public void setModelMap(Cursor cursor, ModelMap<CurrentApplicationColumnKey, Object> modelMap) {
            modelMap.put(this, CursorHandler.getStringOrThrow(cursor, getKeyName()));
        }

        @Override
        public void setContentValues(ContentValues contentValues, CurrentApplicationHolder currentApplicationHolder) {
            contentValues.put(getKeyName(), currentApplicationHolder.getConfigValue());
        }
    },

    ModifiedDatetime(Key.modified_datetime) {
        @Override
        public void setModelMap(Cursor cursor, ModelMap<CurrentApplicationColumnKey, Object> modelMap) {
            modelMap.put(this, CursorHandler.getStringOrThrow(cursor, getKeyName()));
        }

        @Override
        public void setContentValues(ContentValues contentValues, CurrentApplicationHolder currentApplicationHolder) {
            String currentClientDatetime = CalendarHandler.getClientDatetime();
            contentValues.put(getKeyName(), currentClientDatetime);
        }
    };

    private final Key key;

    CurrentApplicationColumnKey(Key key) {
        this.key = key;
    }

    @Override
    public String getKeyName() {
        return key.name();
    }

    public abstract void setModelMap(Cursor cursor, ModelMap<CurrentApplicationColumnKey, Object> modelMap);

    public abstract void setContentValues(ContentValues contentValues, CurrentApplicationHolder currentApplicationHolder);

    private enum Key {
        config_name,
        config_value,
        modified_datetime,
    }
}
