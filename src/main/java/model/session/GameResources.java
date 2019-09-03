package model.session;

import java.util.HashMap;

public class GameResources {

    private HashMap<String, Integer> resources = new HashMap<>();

    public GameResources() {
        this.addType("Citizens relationship", 50)
                .addType("Government relationship", 75)
                .addType("City infrastructure", 300000)
                .addType("City budget", 60000);
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
