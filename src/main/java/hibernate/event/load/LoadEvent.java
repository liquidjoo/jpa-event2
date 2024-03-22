package hibernate.event.load;

public class LoadEvent {
    private Long id;
    private String entityName;
    private Object result;

    public LoadEvent(Long id, String entityName) {
        this.id = id;
        this.entityName = entityName;
    }

    public String getEntityName() {
        return entityName;
    }

    public Long getId() {
        return id;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
