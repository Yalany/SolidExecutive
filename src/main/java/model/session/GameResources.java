package model.session;

import java.util.HashMap;

class GameResources {

    private HashMap<String, Integer> resources = new HashMap<>();

    GameResources() {
    }

    void addType(String resourceName, int startingAmount) {
        resources.put(resourceName, startingAmount);
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

    public enum Type {
        GOVERNMENT      ("Government relationship", 75, 100, 50),
        CITIZENS        ("Citizens relationship", 50, 75, 25),
        INFRASTRUCTURE  ("Infrastructure score", 5000, 7500, 2500),
        MONEY           ("Money", 10000, 15000, 5000);

        private String name;
        private int defaultValue;
        private int easyValue;
        private int hardValue;

        Type(String name, int defaultValue, int easyValue, int hardValue) {
            this.name = name;
            this.defaultValue = defaultValue;
            this.easyValue = easyValue;
            this.hardValue = hardValue;
        }

        public String getName() {
            return name;
        }

        public int getDefaultValue() {
            return defaultValue;
        }

        public int getEasyValue() {
            return easyValue;
        }

        public int getHardValue() {
            return hardValue;
        }
    }
}
