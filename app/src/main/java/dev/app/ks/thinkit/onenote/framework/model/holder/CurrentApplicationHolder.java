package dev.app.ks.thinkit.onenote.framework.model.holder;

import java.util.Objects;

import dev.app.ks.thinkit.onenote.framework.model.CurrentApplicationInformation;

/**
 * ======================================================================
 * Project Name    : Duovoc
 * File Name       : CurrentApplicationHolder.java
 * Encoding        : UTF-8
 * Creation Date   : 2019/09/30
 * <p>
 * Copyright © 2019 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be
 * reproduced or used in any manner whatsoever.
 * ======================================================================
 * <p>
 * 論理モデル名「カレントアプリケーション情報」を操作する際に使用するデータクラスです。
 * 当該データクラスの値の設定及び取得は、
 * GetterメソッドとSetterメソッドを介して行われます。
 *
 * @author Kato Shinya
 * @version 1.0
 * @see CurrentApplicationInformation
 * @since 1.0
 */
public final class CurrentApplicationHolder {

    /**
     * コンフィグ名を格納するフィールドです。
     *
     * @see #getConfigName()
     * @see #setConfigName(CurrentApplicationInformation.ConfigName)
     */
    private CurrentApplicationInformation.ConfigName configName = null;

    /**
     * コンフィグ値を格納するフィールドです。
     *
     * @see #getConfigValue()
     * @see #configValue
     */
    private String configValue = "";

    /**
     * コンフィグ名を返却するGetterメソッドです。
     *
     * @return コンフィグ名。
     */
    public String getConfigName() {
        return configName.getConfigName();
    }

    /**
     * コンフィグ名を設定するSetterメソッドです。
     *
     * @param configName コンフィグ名。
     */
    public void setConfigName(CurrentApplicationInformation.ConfigName configName) {
        this.configName = configName;
    }

    /**
     * コンフィグ値を返却するGetterメソッドです。
     *
     * @return コンフィグ値。
     */
    public String getConfigValue() {
        return configValue;
    }

    /**
     * コンフィグ値を設定するSetterメソッドです」。
     *
     * @param configValue コンフィグ値。
     */
    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    @Override
    public String toString() {
        return "CurrentApplicationHolder{" +
                "configName=" + configName +
                ", configValue='" + configValue + '\'' +
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
        CurrentApplicationHolder that = (CurrentApplicationHolder) o;
        return getConfigName() == that.getConfigName() &&
                getConfigValue().equals(that.getConfigValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConfigName(), getConfigValue());
    }
}
