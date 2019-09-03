package model.session;

public class SessionOperator {

    private Session context;

    SessionOperator(Session context) {
        this.context = context;
    }

    public void scheduleEvent(int eventId, int offset) {
        context.getEventDeck().shuffleIn(eventId, offset);
    }

    public void insertNextEvent(int eventId) {
        context.getEventDeck().putOnTop(eventId);
    }

    public void changeResources(String resourceName, int resourceAmount) {
        context.getResources().modifyAmount(resourceName, resourceAmount);
    }

    public void endMonth() {
        context.nextMonth();
    }

    public void exitGame() {
        context.exitGame();
    }
}
