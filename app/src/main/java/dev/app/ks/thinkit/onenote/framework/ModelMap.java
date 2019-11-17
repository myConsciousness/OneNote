package dev.app.ks.thinkit.onenote.framework;

import java.util.EnumMap;
import java.util.List;

public final class ModelMap<E extends Enum<E> & IModelMapKey, V> extends EnumMap<E, V> {

    private static final long serialVersionUID = 5033524892917061928L;

    public ModelMap(Class<E> keyType) {
        super(keyType);
    }

    public String getString(IModelMapKey key) {
        return (String) get(key);
    }

    public Integer getInteger(IModelMapKey key) {
        return (Integer) get(key);
    }

    public Double getDouble(IModelMapKey key) {
        return (Double) get(key);
    }

    public List<String> getStringList(IModelMapKey key) {
        return (List<String>) get(key);
    }
}
