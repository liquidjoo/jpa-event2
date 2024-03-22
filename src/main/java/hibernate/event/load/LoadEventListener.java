package hibernate.event.load;

import hibernate.event.EventListener;

public interface LoadEventListener extends EventListener {
    public void onLoad(LoadEvent loadEvent);
}
