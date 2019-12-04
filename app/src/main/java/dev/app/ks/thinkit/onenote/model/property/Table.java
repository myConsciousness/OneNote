package dev.app.ks.thinkit.onenote.model.property;

import dev.app.ks.thinkit.onenote.framework.ITableInfo;

public enum Table implements ITableInfo {
    CurrentApplicationInformation(TableName.current_application_information),
    MemoInformation(TableName.memo_information);

    private final TableName key;

    Table(TableName key) {
        this.key = key;
    }

    @Override
    public String getName() {
        return key.name();
    }

    private enum TableName {
        current_application_information,
        memo_information
    }
}
