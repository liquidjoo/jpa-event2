package hibernate.entity.meta;

import java.util.HashMap;
import java.util.Map;

public class CollectionBindingMap {

    private final Map<String, PersistentCollectionClass> collectionBindingMap;

    public CollectionBindingMap(Map<String, PersistentCollectionClass> collectionBindingMap) {
        this.collectionBindingMap = collectionBindingMap;
    }

    public void addCollectionBinding(PersistentCollectionClass collection) throws DuplicateMappingException {
        final String collectionRole = collection.getCollectionEntityName();
        if (collectionBindingMap.containsKey(collectionRole)) {
            throw new DuplicateMappingException(DuplicateMappingException.Type.COLLECTION, collectionRole);
        }
        collectionBindingMap.put(collectionRole, collection);
    }

    public PersistentCollectionClass getCollection(String name) {
        return collectionBindingMap.get(name);
    }
}
