package hibernate.entity;

public interface EntityManager {

    <T> T find(Class<T> clazz, Object id);

    void persist(Object entity);

    void merge(Object entity);

    void remove(Object entity);
}
