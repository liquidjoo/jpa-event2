package hibernate.event.persister;

public class PersistEvent {

    private Object object;
    private String entityName;

    public PersistEvent(Object object, String entityName) {
        this.object = object;
        this.entityName = entityName;
    }
}
