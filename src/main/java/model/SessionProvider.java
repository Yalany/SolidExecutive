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

    Session getSessionById(String userId) {
        if (containedInCache(userId))
            return getFromCache(userId);
        if (containedInDB(userId))
            return getFromDB(userId);
        return createUserSession(userId);
    }

    private Session createUserSession(String userId) {
        return null;
    }


    private EventProvider menuEventProvider;
    private EventProvider gameEventProvider;
    private EventProvider gamePlusEventProvider;

    private Commands menuCommands;
    private Commands gameCommands;

    private HashMap<String, Session> cache = new HashMap<>();
    private HashMap<String, Timer> cacheCleaner = new HashMap<>();

    private class SessionTimeout extends TimerTask {
        private String sessionId;

        SessionTimeout(String sessionId) {
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

    private boolean containedInCache(String userId) {
        return cache.containsKey(userId);
    }

    private Session getFromCache(String userId) {
        return cache.get(userId);
    }

    private Session removeFromCache(String userId) {
        return cache.remove(userId);
    }


    private void saveInDB(Session session) {

    }

    private boolean containedInDB(String userId) {
        return false;
    }

    private Session getFromDB(String userId) {
        return null;
    }
}
