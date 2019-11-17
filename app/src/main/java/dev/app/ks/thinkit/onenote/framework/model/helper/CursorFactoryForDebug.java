package dev.app.ks.thinkit.onenote.framework.model.helper;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;

import dev.app.ks.thinkit.onenote.BuildConfig;
import dev.app.ks.thinkit.onenote.framework.Logger;

/**
 * ======================================================================
 * Project Name    : Common
 * File Name       : CursorFactoryForDebug.java
 * Encoding        : UTF-8
 * Creation Date   : 2019/09/30
 * <p>
 * Copyright © 2019 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be
 * reproduced or used in any manner whatsoever.
 * ======================================================================
 * <p>
 * 実行するクエリのデバッグを行う機能を定義したファクトリクラスです。
 *
 * @author Kato Shinya
 * @version 1.0
 * @since 1.0
 */
public final class CursorFactoryForDebug implements SQLiteDatabase.CursorFactory {

    /**
     * クラス名。
     */
    private static final String TAG = CursorFactoryForDebug.class.getName();

    @Override
    public Cursor newCursor(
            SQLiteDatabase database,
            SQLiteCursorDriver masterQuery,
            String editTable,
            SQLiteQuery query) {
        String methodName = "newCursor";
        Logger.Info.write(TAG, methodName, "START");

        if (BuildConfig.DEBUG) {
            Logger.Debug.write(TAG, methodName, query.toString());
        }

        Logger.Info.write(TAG, methodName, "END");
        return new SQLiteCursor(masterQuery, editTable, query);
    }
}
