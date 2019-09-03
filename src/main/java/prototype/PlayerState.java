package prototype;

class PlayerState {

    private Event currentEvent;
    private String systemEvent;
    private int monthsPassed;
    private PlayerResources playerResources;

    PlayerState() {
        monthsPassed = 0;
        playerResources = new PlayerResources();
    }

    void setCurrentEvent(Event event) {
        currentEvent = event;
    }

    Event getCurrentEvent() {
        return currentEvent;
    }

    void setSystemEvent(String systemEvent) {
        this.systemEvent = systemEvent;
    }

    String getSystemEvent() {
        return systemEvent;
    }

    void flushSystemEvent() {
        systemEvent = null;
    }

    void nextMonth() {
        monthsPassed++;
    }

    int getMonthsPassed() {
        return monthsPassed;
    }

    PlayerResources getPlayerResources() {
        return playerResources;
    }

    boolean changeResources(ResourcesTransaction transaction) {
        return playerResources.acceptTransaction(transaction);
    }
}
