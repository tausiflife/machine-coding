package org.lld.splitwise.model;

import java.util.Collections;
import java.util.List;

public class Group {
    private final String id;
    private final String desc;
    private final List<String> users;

    public Group(String id, String desc, List<String> users) {
        this.id = id;
        this.desc = desc;
        this.users = users;
    }

    public String getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public List<String> getUsers() {
        return Collections.unmodifiableList(users);
    }
}
