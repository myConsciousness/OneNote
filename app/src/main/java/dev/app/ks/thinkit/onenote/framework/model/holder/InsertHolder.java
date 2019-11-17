package dev.app.ks.thinkit.onenote.framework.model.holder;

import android.content.ContentValues;

import java.util.Objects;

public final class InsertHolder extends QueryHolder {

    private final ContentValues cv = new ContentValues();
    private String nullColumnHack = null;

    public String getNullColumnHack() {
        return nullColumnHack;
    }

    public void setNullColumnHack(String nullColumnHack) {
        this.nullColumnHack = nullColumnHack;
    }

    public ContentValues getContentValues() {
        return cv;
    }

    @Override
    public void clearAll() {
        setNullColumnHack(null);
        getContentValues().clear();
    }

    @Override
    public String toString() {
        return "InsertHolder{" +
                "cv=" + cv +
                ", nullColumnHack='" + nullColumnHack + '\'' +
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
        InsertHolder that = (InsertHolder) o;
        return cv.equals(that.cv) &&
                Objects.equals(getNullColumnHack(), that.getNullColumnHack());
    }

    @Override
    public int hashCode() {
        return Objects.hash(cv, getNullColumnHack());
    }
}
