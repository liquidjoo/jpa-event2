package hibernate.event;

import hibernate.boot.meta.MetamodelImpl;
import hibernate.event.load.DefaultLoadEventListener;
import hibernate.event.load.LoadEventListener;
import hibernate.event.persister.DefaultPersisterEventListener;
import hibernate.event.persister.PersistEventListener;

import java.util.Map;

public class EventListenerGroupImpl<T> implements EventListenerGroup<T> {

    private final Map<EventType<?>, EventListener> listeners;

    public EventListenerGroupImpl(MetamodelImpl metamodel) {
        this.listeners = Map.of(
                EventType.LOAD, new DefaultLoadEventListener(metamodel),
                EventType.PERSIST, new DefaultPersisterEventListener()
        );
    }

    @Override
    public PersistEventListener getPersistEventListener(EventType eventType) {
        return (PersistEventListener) listeners.get(eventType);
    }

    @Override
    public LoadEventListener getLoadEventListener(EventType eventType) {
        return (LoadEventListener) listeners.get(eventType);
    }
}
