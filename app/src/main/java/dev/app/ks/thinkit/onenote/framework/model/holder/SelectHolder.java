package dev.app.ks.thinkit.onenote.framework.model.holder;

import java.util.Arrays;
import java.util.Objects;

public final class SelectHolder extends QueryHolder {

    private String[] columns = null;
    private String selection = null;
    private String[] selectionArgs = null;
    private String groupBy = null;
    private String having = null;
    private String orderBy = null;
    private String limit = null;

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String[] getSelectionArgs() {
        return selectionArgs;
    }

    public void setSelectionArgs(String[] selectionArgs) {
        this.selectionArgs = selectionArgs;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

    public String getHaving() {
        return having;
    }

    public void setHaving(String having) {
        this.having = having;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    @Override
    public void clearAll() {
        setColumns(null);
        setSelection(null);
        setSelectionArgs(null);
        setGroupBy(null);
        setHaving(null);
        setOrderBy(null);
        setLimit(null);
    }

    @Override
    public String toString() {
        return "SelectHolder{" +
                "columns=" + Arrays.toString(columns) +
                ", selection='" + selection + '\'' +
                ", selectionArgs=" + Arrays.toString(selectionArgs) +
                ", groupBy='" + groupBy + '\'' +
                ", having='" + having + '\'' +
                ", orderBy='" + orderBy + '\'' +
                ", limit='" + limit + '\'' +
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
        SelectHolder that = (SelectHolder) o;
        return Arrays.equals(getColumns(), that.getColumns()) &&
                Objects.equals(getSelection(), that.getSelection()) &&
                Arrays.equals(getSelectionArgs(), that.getSelectionArgs()) &&
                Objects.equals(getGroupBy(), that.getGroupBy()) &&
                Objects.equals(getHaving(), that.getHaving()) &&
                Objects.equals(getOrderBy(), that.getOrderBy()) &&
                Objects.equals(getLimit(), that.getLimit());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getSelection(), getGroupBy(), getHaving(), getOrderBy(), getLimit());
        result = 31 * result + Arrays.hashCode(getColumns());
        result = 31 * result + Arrays.hashCode(getSelectionArgs());
        return result;
    }
}
