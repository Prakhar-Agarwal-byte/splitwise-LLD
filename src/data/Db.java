package data;

import models.Group;
import models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Db {
    public final Map<UUID, User> users = new HashMap<>();
    public final Map<UUID, Group> groups = new HashMap<>();
    private static volatile Db INSTANCE = null;

    private Db() {}
    public static Db getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (Db.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Db();
                }
            }
        }
        return INSTANCE;
    }
}
