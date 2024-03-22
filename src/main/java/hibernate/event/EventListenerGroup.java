package hibernate.event;

import hibernate.event.load.LoadEventListener;
import hibernate.event.persister.PersistEventListener;

public interface EventListenerGroup<T> {

    public LoadEventListener getLoadEventListener(EventType eventType);

    public PersistEventListener getPersistEventListener(EventType eventType);
}
