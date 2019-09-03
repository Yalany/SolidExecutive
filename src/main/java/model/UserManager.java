package model;

class UserManager {

    User getUserById(int id) {
        if (isUserCached(id))
            return getUserFromCache(id);

        if (doesUserExists(id))
            return loadUser(id);

        return null;
    }


    // Does cache == session for user id?
    private boolean isUserCached(int id) {
        return false;
    }

    private User getUserFromCache(int id) {
        return null;
    }

    private boolean doesUserExists(int id) {
        return false;
    }

    private User loadUser(int id) {
        return null;
    }
}
