package model;

public class User {

    private int id;
    private int gamesFinished;
    private boolean haveActiveGame;
    private long subscriptionEndTime;

    public User(int id) {
        this.id = id;
        gamesFinished = 0;
        haveActiveGame = false;
        subscriptionEndTime = System.currentTimeMillis();
    }

    public int getId() {
        return id;
    }

    public int getGamesFinished() {
        return gamesFinished;
    }

    public boolean isHaveActiveGame() {
        return haveActiveGame;
    }

    public boolean isSubscribed() {
        return System.currentTimeMillis() < subscriptionEndTime;
    }

    public long getSubscriptionEndTime() {
        return subscriptionEndTime;
    }

    public void incrementGamesFinished() {
        this.gamesFinished++;
    }

    public void setHaveActiveGame(boolean haveActiveGame) {
        this.haveActiveGame = haveActiveGame;
    }

    public void setSubscriptionEndTime(long subscriptionEndTime) {
        this.subscriptionEndTime = subscriptionEndTime;
    }
}
