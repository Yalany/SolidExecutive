package model.session;

import java.util.HashMap;

final class GameResources {

    private static class GameResource {
        private final String name;
        private int value;

        GameResource(String name, int value) {
            this.name = name;
            this.value = value;
        }

        String getName() {
            return name;
        }

        int getValue() {
            return value;
        }

        void setValue(int value) {
            this.value = value;
        }

        void modifyValue(int addition) {
            this.value += addition;
        }
    }

    private final HashMap<String, GameResource> resources = new HashMap<>();

    GameResources() {
    }

    void addType(String id, String displayName, int startingAmount) {
        resources.put(id, new GameResource(displayName, startingAmount));
    }

    String getDisplayName(String id) {
        return resources.get(id).getName();
    }

    boolean haveAmount(String id, int amount) {
        return resources.get(id).getValue() >= amount;
    }

    void setAmount(String id, int amount) {
        resources.get(id).setValue(amount);
    }

    void modifyAmount(String id, int modification) {
        resources.get(id).modifyValue(modification);
    }

    public enum Type {
        GOVERNMENT("Government relationship", "",
                100, 100, 75, 50),

        CITIZENS("Citizens relationship", "",
                100, 75, 50, 25),

        INFRASTRUCTURE("Infrastructure score", "",
                10000, 7500, 5000, 2500),

        MONEY("Money", "",
                20000, 15000, 10000, 5000);

        private String id;
        private String displayName;
        private int normalValue;
        private int easyValue;
        private int hardValue;
        private int tutorialValue;

        Type(String id, String displayName, int tutorialValue, int easyValue, int normalValue, int hardValue) {
            this.id = id;
            this.displayName = displayName;
            this.tutorialValue = tutorialValue;
            this.easyValue = easyValue;
            this.normalValue = normalValue;
            this.hardValue = hardValue;
        }

        public String getId() {
            return id;
        }

        public String getDisplayName() {
            return displayName;
        }

        public int getTutorialValue() {
            return tutorialValue;
        }

        public int getEasyValue() {
            return easyValue;
        }

        public int getNormalValue() {
            return normalValue;
        }

        public int getHardValue() {
            return hardValue;
        }
    }
}
