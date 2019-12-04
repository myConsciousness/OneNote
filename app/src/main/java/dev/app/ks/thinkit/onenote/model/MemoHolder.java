package dev.app.ks.thinkit.onenote.model;

import java.util.Objects;

import dev.app.ks.thinkit.onenote.framework.model.holder.ModelAccessor;

/**
 * ======================================================================
 * Project Name    : One Note
 * File Name       : MemoHolder.java
 * Encoding        : UTF-8
 * Creation Date   : 2019/12/04
 * <p>
 * Copyright © 2019 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be
 * reproduced or used in any manner whatsoever.
 * ======================================================================
 * <p>
 * 論理モデル名「メモ情報」を操作する際に使用するデータクラスです。
 * 当該データクラスの値の設定及び取得は、
 * GetterメソッドとSetterメソッドを介して行われます。
 *
 * @author Kato Shinya
 * @version 1.0
 * @see MemoInformation
 * @since 1.0
 */
public final class MemoHolder extends ModelAccessor {

    /**
     * メモ名を格納するフィールドです。
     *
     * @see #getMemoName()
     */
    private final String memoName;

    /**
     * メモを格納するフィールドです。
     *
     * @see #getMemo()
     */
    private final String memo;

    /**
     * 当該データクラスのコンストラクタ。
     * 当該コンストラクタで各フィールドに値を設定します。
     *
     * @param memoName メモ名。
     * @param memo     メモ。
     */
    public MemoHolder(String memoName, String memo) {
        if (memoName == null || memo == null) {
            // should not be happened
            throw new IllegalArgumentException();
        }

        this.memoName = memoName;
        this.memo = memo;
    }

    /**
     * メモ名を返却するGetterメソッドです。
     *
     * @return メモ名。
     */
    public String getMemoName() {
        return memoName;
    }

    /**
     * メモを返却するメソッドです。
     *
     * @return メモ。
     */
    public String getMemo() {
        return memo;
    }

    @Override
    public String toString() {
        return "MemoHolder{" +
                "memoName='" + memoName + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MemoHolder that = (MemoHolder) o;
        return Objects.equals(getMemoName(), that.getMemoName()) &&
                Objects.equals(getMemo(), that.getMemo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMemoName(), getMemo());
    }
}
