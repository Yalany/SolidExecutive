package model;

import model.session.Commands;
import model.session.EventProvider;
import model.session.Session;

import java.util.HashMap;

public class SessionProvider {

    // TODO: build and fill with events
    private EventProvider menuEventProvider;
    private EventProvider gameEventProvider;
    private EventProvider gamePlusEventProvider;

    private Commands menuCommands = new Commands();
    private Commands gameCommands = new Commands();

    private HashMap<Integer, Session> cache = new HashMap<>();

    Session getSessionById(int userId) {
        if (containsInCache(userId))
            return extractFromCache(userId);
        if (containsInDB(userId))
            return extractFromDB(userId);
        return createUserSession(userId);
    }

    // used by session timeout timer
    public void removeFromCache(Session session) {
        saveSessionToDB(session);
        cache.remove(session.getId());
    }

    private boolean containsInCache(int userId) {
        return cache.containsKey(userId);
    }

    private Session extractFromCache(int userId) {
        return cache.get(userId);
    }

    private boolean containsInDB(int userId) {
        return false;
    }

    private Session extractFromDB(int userId) {
        Session extractedSession = null;
        cacheSession(extractedSession);
        return extractedSession;
    }

    // called only once for every unique user
    private Session createUserSession(int userId) {
        var session = new Session(userId)
                .setEventProvider(menuEventProvider)
                .setCommands(menuCommands)
                .setDefaultEventDeck();

        saveSessionToDB(session);
        cacheSession(session);
        return session;
    }

    private void cacheSession(Session session) {
        cache.put(session.getId(), session);
//        session.startTimeout();
    }

    private void saveSessionToDB(Session session) {

    }
}
