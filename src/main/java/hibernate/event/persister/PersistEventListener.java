package hibernate.event.persister;

import hibernate.event.EventListener;

public interface PersistEventListener extends EventListener {

    public void onPersist(PersistEvent persistEvent);
}
