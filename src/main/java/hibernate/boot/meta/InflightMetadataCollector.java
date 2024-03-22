package hibernate.boot.meta;

import hibernate.entity.meta.DuplicateMappingException;
import hibernate.entity.meta.PersistentClass;
import hibernate.entity.meta.PersistentCollectionClass;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InflightMetadataCollector {

    private final Map<String, PersistentClass> entityBindingMap = new HashMap<>();
    private final Map<String, PersistentCollectionClass> collectionBindingMap = new HashMap<>();


    public void addEntityBinding(PersistentClass persistentClass) {
        final String entityName = persistentClass.getEntityName();
        if (entityBindingMap.containsKey(entityName)) {
            throw new DuplicateMappingException(DuplicateMappingException.Type.ENTITY, entityName);
        }

        entityBindingMap.put(entityName, persistentClass);
    }

    public PersistentClass getEntityBinding(String entityName) {
        return entityBindingMap.get( entityName );
    }

    public void addCollectionBinding(PersistentCollectionClass persistentCollectionClass) {
        final String collectionEntityName = persistentCollectionClass.getCollectionEntityName();
        if (collectionBindingMap.containsKey(collectionEntityName)) {
            throw new DuplicateMappingException(DuplicateMappingException.Type.COLLECTION, collectionEntityName);
        }

        collectionBindingMap.put(collectionEntityName, persistentCollectionClass);
    }

    public Collection<PersistentClass> getEntityBindings() {
        return entityBindingMap.values();
    }

    public Collection<PersistentCollectionClass> getCollectionBindings() {
        return collectionBindingMap.values();
    }

    public Map<String, PersistentClass> getEntityBindingMap() {
        return entityBindingMap;
    }

    public Map<String, PersistentCollectionClass> getCollectionBindingMap() {
        return collectionBindingMap;
    }
}
