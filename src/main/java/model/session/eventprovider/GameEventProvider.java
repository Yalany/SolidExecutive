package model.session.eventprovider;

import model.session.Event;
import model.session.GameResources;
import model.session.Session;

import java.util.HashMap;

@SuppressWarnings("WeakerAccess")
public class GameEventProvider {

    private HashMap<Integer, Event> events = new HashMap<>();

    public void setEvents(HashMap<Integer, Event> events) {
        this.events = events;
    }

    // session used for resource validation, event won't have options for which player don't have resources
    public Event getEventById(int id, Session forSession) {
//        Event event = new Event();

        return null;
    }


    // Resources:

    private HashMap<String, Integer> resourceSetup = new HashMap<>();

    public GameEventProvider addResourceToSetup(String resourceName, int defaultAmount) {
        resourceSetup.put(resourceName, defaultAmount);
        return this;
    }

    public GameResources getResourceSetup() {
        return new GameResources(resourceSetup);
    }

    // TODO: remove when no longer needed
    public GameEventProvider defaultResourceSetup() {
        return addResourceToSetup("Citizens relationship", 50)
                .addResourceToSetup("Government relationship", 75)
                .addResourceToSetup("City infrastructure", 300000)
                .addResourceToSetup("City budget", 60000);
    }
}
