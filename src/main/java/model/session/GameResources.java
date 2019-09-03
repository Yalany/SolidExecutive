package model.session;

import java.util.HashMap;

public class GameResources {

    private HashMap<String, Integer> resources;

    public GameResources() {
        this.resources = new HashMap<>();
    }

    public GameResources addType(String resourceName, int startingAmount) {
        resources.put(resourceName, startingAmount);
        return this;
    }

    boolean haveAmount(String resourceName, int amount) {
        return resources.get(resourceName) >= amount;
    }

    void setAmount(String resourceName, int amount) {
        resources.put(resourceName, amount);
    }

    void modifyAmount(String resourceName, int modification) {
        var currentAmount = resources.get(resourceName);
        resources.put(resourceName, currentAmount + modification);
    }
}
