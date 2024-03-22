package hibernate.event.load;

import hibernate.boot.meta.MetamodelImpl;
import hibernate.entity.EntityLoader;
import hibernate.entity.meta.PersistentClass;

public class DefaultLoadEventListener implements LoadEventListener {

    private final MetamodelImpl metamodel;

    public DefaultLoadEventListener(MetamodelImpl metamodel) {
        this.metamodel = metamodel;
    }

    @Override
    public void onLoad(LoadEvent loadEvent) {
        String entityName = loadEvent.getEntityName();
        PersistentClass persistentClass = metamodel.getInflightMetadataCollector().getEntityBinding(entityName);
        EntityLoader entityLoader = metamodel.getEntityLoader(entityName);
        Object o = entityLoader.find(persistentClass, loadEvent.getId());
        loadEvent.setResult(o);
    }
}
