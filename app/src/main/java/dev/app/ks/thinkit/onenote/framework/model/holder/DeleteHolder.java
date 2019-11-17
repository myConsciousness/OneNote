package dev.app.ks.thinkit.onenote.framework.model.holder;

import java.util.Arrays;
import java.util.Objects;

public final class DeleteHolder extends QueryHolder {

    private String whereClause = null;
    private String[] whereArgs = null;

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    public String[] getWhereArgs() {
        return whereArgs;
    }

    public void setWhereArgs(String[] whereArgs) {
        this.whereArgs = whereArgs;
    }

    @Override
    public void clearAll() {
        setWhereClause(null);
        setWhereArgs(null);
    }

    @Override
    public String toString() {
        return "DeleteHolder{" +
                "whereClause='" + whereClause + '\'' +
                ", whereArgs=" + Arrays.toString(whereArgs) +
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
        DeleteHolder that = (DeleteHolder) o;
        return Objects.equals(getWhereClause(), that.getWhereClause()) &&
                Arrays.equals(getWhereArgs(), that.getWhereArgs());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getWhereClause());
        result = 31 * result + Arrays.hashCode(getWhereArgs());
        return result;
    }
}
