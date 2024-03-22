package hibernate.event;

import hibernate.boot.meta.MetamodelImpl;
import hibernate.event.load.DefaultLoadEventListener;
import hibernate.event.load.LoadEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventListenerRegistryImpl {

    private final Map<EventType<?>, EventListenerGroup<?>> listenerGroupMap = new HashMap<>();

    public EventListenerRegistryImpl(MetamodelImpl metamodel) {
        listenerGroupMap.put(EventType.LOAD, new EventListenerGroupImpl<>(metamodel));
        listenerGroupMap.put(EventType.PERSIST, new EventListenerGroupImpl<>(metamodel));
    }

    public <T> EventListenerGroup<T> getEventListenerGroup(EventType<T> eventType) {
        return (EventListenerGroup<T>) listenerGroupMap.get(eventType);
    }
}
