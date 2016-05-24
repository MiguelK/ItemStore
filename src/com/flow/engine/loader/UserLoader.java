package com.flow.engine.loader;

import com.flow.engine.model.User;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserLoader extends Loader {
    private final List<User> users = new ArrayList<User>();

    private final Map<String, User> usersById = new HashMap<String, User>();

    public void registerUser(User user) {
        users.add(user);
        usersById.put(user.getId(), user);

    }

    public List<User> getUsers() {
        return users;
    }
    //FIXME load/save from file

    public boolean isUnknownUser(String userId) {
        return !usersById.containsKey(userId);
    }

    public void updateUser(String userId, String firstName, List<String> receivedItems, List<String> excludeTags,
                           List<String> favoriteTags) {
        User user = usersById.get(userId);
        user.addReceivedItems(receivedItems);

        user.addExcludeTags(excludeTags);
        user.addFavoriteTags(favoriteTags);

        String s = StringUtils.trimToNull(firstName);

        if (s != null) {
            user.setFirstName(s);
        }
    }
}
