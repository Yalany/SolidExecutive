package prototype;

import java.util.HashMap;
import java.util.Random;

class EventProvider {

    private HashMap<Integer, Event> events;

    private final Random randomGenerator;

    private final static int LAST_RANDOM_EVENT_ID = 2;

    // TODO: implement readEventsFromJSON(String JSON)

    EventProvider(HashMap<Integer, Event> events) {
        this.events = events;
        this.randomGenerator = new Random();
    }

    Event getEventByIdForSession(int id, Session session) {
        if (id == 0)
            return getRandomEvent().setSession(session);
        return events.get(id).setSession(session);
    }

    private Event getRandomEvent() {
        return events.get(randomGenerator.nextInt(LAST_RANDOM_EVENT_ID));
    }
}
