package model.session;

public class User {

    private String id;
    private int gamesFinished;
    private long subscriptionEndTime;
    private boolean haveActiveGame;

    public User(String id) {
        this.id = id;
        gamesFinished = 0;
        subscriptionEndTime = System.currentTimeMillis();
        haveActiveGame = false;
    }

    public String getId() {
        return id;
    }


    public int getGamesFinished() {
        return gamesFinished;
    }

    public long getSubscriptionEndTime() {
        return subscriptionEndTime;
    }

    public boolean haveActiveGame() {
        return haveActiveGame;
    }

    public boolean isSubscribed() {
        return System.currentTimeMillis() < subscriptionEndTime;
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
