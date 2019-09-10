package model;

public final class User {

    private final String id;
//    private int gamesFinished;
    private long subscriptionEndTime;
    private boolean haveActiveGame;

    User(String id) {
        this.id = id;
//        gamesFinished = 0;
        subscriptionEndTime = System.currentTimeMillis();
        haveActiveGame = false;
    }

    boolean isSubscribed() {
        return System.currentTimeMillis() < subscriptionEndTime;
    }

    boolean haveActiveGame() {
        return haveActiveGame;
    }

    public String getId() {
        return id;
    }

//    public int getGamesFinished() {
//        return gamesFinished;
//    }
//
//    public void incrementGamesFinished() {
//        this.gamesFinished++;
//    }
//
//    public void setSubscriptionEndTime(long subscriptionEndTime) {
//        this.subscriptionEndTime = subscriptionEndTime;
//    }
//
//    public void setHaveActiveGame(boolean haveActiveGame) {
//        this.haveActiveGame = haveActiveGame;
//    }
}
