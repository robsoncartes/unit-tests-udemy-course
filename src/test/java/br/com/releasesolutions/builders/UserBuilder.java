package br.com.releasesolutions.builders;

import br.com.releasesolutions.models.User;

public class UserBuilder {

    private User user;

    private UserBuilder() {

    }

    public static UserBuilder getUserBuilderInstance() {

        UserBuilder builder = new UserBuilder();
        builder.user = new User();
        builder.user.setName("User 1");

        return builder;
    }

    public UserBuilder setName(String name) {

        user.setName(name);

        return this;
    }

    public User getUser() {

        return user;
    }
}
