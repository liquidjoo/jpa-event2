package hibernate.boot.meta;


import hibernate.entity.EntityCollectionLoader;
import hibernate.entity.EntityLoader;
import hibernate.entity.EntityPersister;
import hibernate.entity.meta.CollectionBindingMap;
import hibernate.entity.meta.PersistentClass;
import jdbc.JdbcTemplate;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MetamodelImpl {

    private final Map<String, EntityPersister> entityPersisterMap = new HashMap<>();
    private final Map<String, EntityLoader> entityLoaderMap = new HashMap<>();
    private final Map<String, EntityCollectionLoader> entityCollectionLoaderMap = new HashMap<>();
    private final JdbcTemplate jdbcTemplate;
    private final InflightMetadataCollector inflightMetadataCollector;

    public MetamodelImpl(InflightMetadataCollector inflightMetadataCollector, JdbcTemplate jdbcTemplate) {
        this.inflightMetadataCollector = inflightMetadataCollector;
        this.jdbcTemplate = jdbcTemplate;

        initPersister(inflightMetadataCollector, jdbcTemplate);
        initLoader(inflightMetadataCollector, jdbcTemplate);

    }

    private void initLoader(InflightMetadataCollector inflightMetadataCollector, JdbcTemplate jdbcTemplate) {
        Collection<PersistentClass> entityBindings = inflightMetadataCollector.getEntityBindings();
        for (PersistentClass entityBinding : entityBindings) {
            entityLoaderMap.put(entityBinding.getEntityName(), new EntityLoader(jdbcTemplate, new CollectionBindingMap(inflightMetadataCollector.getCollectionBindingMap())));
        }
    }

    private void initPersister(InflightMetadataCollector inflightMetadataCollector, JdbcTemplate jdbcTemplate) {
        Collection<PersistentClass> entityBindings = inflightMetadataCollector.getEntityBindings();
        for (PersistentClass entityBinding : entityBindings) {
            entityPersisterMap.put(entityBinding.getEntityName(), new EntityPersister(jdbcTemplate));
        }
    }

    public EntityLoader getEntityLoader(String entityName) {
        return entityLoaderMap.get(entityName);
    }

    public InflightMetadataCollector getInflightMetadataCollector() {
        return inflightMetadataCollector;
    }
}
