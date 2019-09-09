package model.session;

import java.util.HashMap;
import java.util.Map;

public final class GameResources {

    private final Map<String, GameResource> resources;

    private GameResources(Map<String, GameResource> resources) {
        this.resources = resources;
    }

    String getDisplayName(String id) {
        return resources.get(id).getDisplayName();
    }

    public int getAmount(String id) {
        return resources.get(id).getValue();
    }

    void modifyAmount(String id, int modification) {
        resources.get(id).modifyValue(modification);
    }

    public static Builder builder() {
        return new Builder();
    }


    private static final class GameResource {
        private final String displayName;
        private int value;

        GameResource(String displayName, int value) {
            this.displayName = displayName;
            this.value = value;
        }

        String getDisplayName() {
            return displayName;
        }

        int getValue() {
            return value;
        }

        void modifyValue(int addition) {
            this.value += addition;
        }
    }


    public static final class Builder {

        private HashMap<String, GameResource> resources;

        private Builder() {
            resources = new HashMap<>();
        }

        public Builder addResource(String id, String displayName, int startingValue) {
            resources.put(id, new GameResource(displayName, startingValue));
            return this;
        }

        public GameResources build() {
            return new GameResources(resources);
        }
    }
}
