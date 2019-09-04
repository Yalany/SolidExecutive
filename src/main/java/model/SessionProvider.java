package model;

import model.session.Commands;
import model.session.EventProvider;
import model.session.Session;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

class SessionProvider {

    // milliseconds since last action to remove session from cache
    private final static long SESSION_TIMEOUT = 300000;

    Session getSessionById(int userId) {
        if (containedInCache(userId))
            return getFromCache(userId);
        if (containedInDB(userId))
            return getFromDB(userId);
        return createUserSession(userId);
    }

    private Session createUserSession(int userId) {
        var session = setupSessionMenu(new Session(userId));
        saveInDB(session);
        saveInCache(session);
        return session;
    }


    private EventProvider menuEventProvider;
    private EventProvider gameEventProvider;
    private EventProvider gamePlusEventProvider;

    private Commands menuCommands;
    private Commands gameCommands;

    private Session setupSessionMenu(Session session) {
        return session
                .setEventProvider(menuEventProvider)
                .setCommands(menuCommands)
                .setDefaultEventDeck();
    }

    private Session setupSessionGame(Session session) {
        return session
                .setEventProvider(gameEventProvider)
                .setCommands(gameCommands)
                .setDefaultEventDeck();
    }

    private Session setupSessionGamePlus(Session session) {
        return session
                .setEventProvider(gamePlusEventProvider)
                .setCommands(gameCommands)
                .setDefaultEventDeck();
    }


    private HashMap<Integer, Session> cache = new HashMap<>();
    private HashMap<Integer, Timer> cacheCleaner = new HashMap<>();

    private class SessionTimeout extends TimerTask {
        private int sessionId;

        SessionTimeout(int sessionId) {
            this.sessionId = sessionId;
        }

        @Override
        public void run() {
            saveInDB(removeFromCache(sessionId));
        }
    }

    private void saveInCache(Session session) {
        var sessionId = session.getId();

        cache.put(sessionId, session);

        if (cacheCleaner.containsKey(sessionId))
            cacheCleaner.get(sessionId).cancel();

        var timeoutTimer = new Timer();
        timeoutTimer.schedule(new SessionTimeout(sessionId), SESSION_TIMEOUT);
        cacheCleaner.put(sessionId, timeoutTimer);
    }

    private boolean containedInCache(int userId) {
        return cache.containsKey(userId);
    }

    private Session getFromCache(int userId) {
        return cache.get(userId);
    }

    private Session removeFromCache(int userId) {
        return cache.remove(userId);
    }


    // TODO below: implement DB
    private void saveInDB(Session session) {

    }

    private boolean containedInDB(int userId) {
        return false;
    }

    private Session getFromDB(int userId) {
        Session session = new Session(userId);
        saveInCache(session);
        return null;
    }
}
