package model.session;

public final class SessionOperator {

    private final Session session;

    SessionOperator(final Session session) {
        this.session = session;
    }

    public void scheduleEvent(int eventId, int offset) {
        session.getEventDeck().shuffleIn(eventId, offset);
    }

    public void insertNextEvent(int eventId) {
        session.getEventDeck().putOnTop(eventId);
    }

    public void changeResources(String resourceName, int resourceAmount) {
        session.getGameResources().modifyAmount(resourceName, resourceAmount);
    }
}
