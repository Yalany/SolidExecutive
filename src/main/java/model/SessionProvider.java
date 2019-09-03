package model;

import model.session.Commands;
import model.session.EventProvider;
import model.session.GameResources;
import model.session.Session;

import java.util.HashMap;

public class SessionProvider {

    private HashMap<Integer, Session> cache = new HashMap<>();

    Session getSessionById(int userId) {
        if (containsInCache(userId))
            return extractFromCache(userId);
        if (containsInDB(userId))
            return extractFromDB(userId);
        return createUserSession(userId);
    }

    // used by session timeout timer
    public void removeFromCache(int userId) {
        cache.remove(userId);
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
        var session = createUserSession(userId);
        cache.put(userId, session);
        session.startTimeout();
        throw new UnsupportedOperationException("not yet implemented");
    }

    private EventProvider menuEventProvider = new EventProvider();
    private EventProvider gameEventProvider = new EventProvider();
    private EventProvider gamePlusEventProvider = new EventProvider();

    private Commands menuCommands = new Commands();
    private Commands gameCommands = new Commands();

    // called only once for every unique user
    private Session createUserSession(int userId) {
        var session = new Session(menuEventProvider, menuCommands, createGameResources());
        cache.put(userId, session);
        saveSessionToDB(userId, session);
        return session;
    }

    private GameResources createGameResources() {
        return new GameResources()
                .addType("Citizens relationship", 50)
                .addType("Government relationship", 75)
                .addType("City infrastructure", 300000)
                .addType("City budget", 60000);
    }

    private void saveSessionToDB(int userId, Session session) {

    }
}
