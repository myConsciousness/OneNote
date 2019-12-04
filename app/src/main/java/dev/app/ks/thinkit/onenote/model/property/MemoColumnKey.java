package dev.app.ks.thinkit.onenote.model.property;

import android.content.ContentValues;
import android.database.Cursor;

import dev.app.ks.thinkit.onenote.framework.CalendarHandler;
import dev.app.ks.thinkit.onenote.framework.IModelMapKey;
import dev.app.ks.thinkit.onenote.framework.ModelMap;
import dev.app.ks.thinkit.onenote.framework.model.CursorHandler;
import dev.app.ks.thinkit.onenote.model.MemoHolder;

/**
 * ======================================================================
 * Project Name    : OneNote
 * File Name       : MemoColumnKey.java
 * Encoding        : UTF-8
 * Creation Date   : 2019/12/04
 * <p>
 * Copyright © 2019 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be
 * reproduced or used in any manner whatsoever.
 * ======================================================================
 * <p>
 * 論理モデル名「メモ情報」のカラム項目を操作する処理を定義したEnumクラスです。
 * 概要情報を操作する際には当該Enumクラスを使用します。
 *
 * @author Kato Shinya
 * @version 1.0
 * @since 1.0
 */
public enum MemoColumnKey implements IModelMapKey {

    /**
     * 物理カラム名「memo_name」を表す項目です。
     * 当該項目では以下の処理が定義されています。
     * <p>
     * 1, setModelMap(Cursor, ModelMap<MemoColumnKey>, Object)
     * -> モデルオブジェクトのselect操作をした際に取得結果をモデルマップへ格納する処理です。
     * <p>
     * 2, setContentValues(ContentValues, MemoHolder)
     * -> モデルオブジェクトの挿入処理をする際に挿入情報を設定する処理です。
     *
     * @see #setModelMap(Cursor, ModelMap)
     * @see #setContentValues(ContentValues, MemoHolder)
     * @see Key#memo_name
     */
    MemoName(Key.memo_name) {
        @Override
        public void setModelMap(Cursor cursor, ModelMap<MemoColumnKey, Object> modelMap) {
            modelMap.put(this, CursorHandler.getStringOrThrow(cursor, getKeyName()));
        }

        @Override
        public void setContentValues(ContentValues contentValues, MemoHolder overviewHolder) {
            contentValues.put(getKeyName(), overviewHolder.getMemoName());
        }
    },

    /**
     * 物理カラム名「memo」を表す項目です。
     * 当該項目では以下の処理が定義されています。
     * <p>
     * 1, setModelMap(Cursor, ModelMap<MemoColumnKey>, Object)
     * -> モデルオブジェクトのselect操作をした際に取得結果をモデルマップへ格納する処理です。
     * <p>
     * 2, setContentValues(ContentValues, MemoHolder)
     * -> モデルオブジェクトの挿入処理をする際に挿入情報を設定する処理です。
     *
     * @see #setModelMap(Cursor, ModelMap)
     * @see #setContentValues(ContentValues, MemoHolder)
     * @see Key#memo
     */
    Memo(Key.memo) {
        @Override
        public void setModelMap(Cursor cursor, ModelMap<MemoColumnKey, Object> modelMap) {
            modelMap.put(this, CursorHandler.getStringOrThrow(cursor, getKeyName()));
        }

        @Override
        public void setContentValues(ContentValues contentValues, MemoHolder overviewHolder) {
            contentValues.put(getKeyName(), overviewHolder.getMemo());
        }
    },

    /**
     * 物理カラム名「registered_datetime」を表す項目です。
     * 当該項目では以下の処理が定義されています。
     * <p>
     * 1, setModelMap(Cursor, ModelMap<MemoColumnKey>, Object)
     * -> モデルオブジェクトのselect操作をした際に取得結果をモデルマップへ格納する処理です。
     * <p>
     * 2, setContentValues(ContentValues, MemoHolder)
     * -> モデルオブジェクトの挿入処理をする際に挿入情報を設定する処理です。
     *
     * @see #setModelMap(Cursor, ModelMap)
     * @see #setContentValues(ContentValues, MemoHolder)
     * @see Key#registered_datetime
     */
    RegisteredDatetime(Key.registered_datetime) {
        @Override
        public void setModelMap(Cursor cursor, ModelMap<MemoColumnKey, Object> modelMap) {
            modelMap.put(this, CursorHandler.getStringOrThrow(cursor, getKeyName()));
        }

        @Override
        public void setContentValues(ContentValues contentValues, MemoHolder memoHolder) {
            String currentClientDatetime = CalendarHandler.getClientDatetime();
            contentValues.put(getKeyName(), currentClientDatetime);
        }
    },

    /**
     * 物理カラム名「updated_datetime」を表す項目です。
     * 当該項目では以下の処理が定義されています。
     * <p>
     * 1, setModelMap(Cursor, ModelMap<MemoColumnKey>, Object)
     * -> モデルオブジェクトのselect操作をした際に取得結果をモデルマップへ格納する処理です。
     * <p>
     * 2, setContentValues(ContentValues, MemoHolder)
     * -> モデルオブジェクトの挿入処理をする際に挿入情報を設定する処理です。
     *
     * @see #setModelMap(Cursor, ModelMap)
     * @see #setContentValues(ContentValues, MemoHolder)
     * @see Key#updated_datetime
     */
    UpdatedDatetime(Key.updated_datetime) {
        @Override
        public void setModelMap(Cursor cursor, ModelMap<MemoColumnKey, Object> modelMap) {
            modelMap.put(this, CursorHandler.getStringOrThrow(cursor, getKeyName()));
        }

        @Override
        public void setContentValues(ContentValues contentValues, MemoHolder memoHolder) {
            String currentClientDatetime = CalendarHandler.getClientDatetime();
            contentValues.put(getKeyName(), currentClientDatetime);
        }
    };

    /**
     * カラムの物理名を格納するフィールドです。
     *
     * @see Key#memo_name
     * @see Key#memo
     * @see Key#registered_datetime
     * @see Key#updated_datetime
     */
    private final Key key;

    /**
     * 当該Enumのコンストラクタです。
     *
     * @see Key#memo_name
     * @see Key#memo
     * @see Key#registered_datetime
     * @see Key#updated_datetime
     */
    MemoColumnKey(Key key) {
        this.key = key;
    }

    @Override
    public String getKeyName() {
        return key.name();
    }

    /**
     * モデルオブジェクトのselect操作をした際に取得結果をモデルマップへ格納する処理です。
     * 当該Enumクラスの項目は当該抽象メソッドを必ず実装する必要があります。
     *
     * @param cursor   カーソルオブジェクト。
     * @param modelMap 値を設定するモデルマップ。
     */
    public abstract void setModelMap(Cursor cursor, ModelMap<MemoColumnKey, Object> modelMap);

    /**
     * モデルオブジェクトの挿入処理をする際に挿入情報を設定する処理です。
     * 当該Enumクラスの項目は当該抽象メソッドを必ず実装する必要があります。
     *
     * @param contentValues 挿入情報を保持するオブジェクト。
     * @param memoHolder    メモ情報を保持するオブジェクト。
     */
    public abstract void setContentValues(ContentValues contentValues, MemoHolder memoHolder);

    /**
     * 論理モデル名「メモ情報」のカラムに紐付く物理名を定義したEnumクラスです。
     * 概要情報のカラムに紐付く物理名は必ず当該Enumクラスへ定義する必要があります。
     *
     * @see #getKeyName()
     */
    private enum Key {
        memo_name,
        memo,
        registered_datetime,
        updated_datetime,
    }
}
