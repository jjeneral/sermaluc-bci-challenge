package com.sermaluc.sermalucbcichallenge.helper;

import com.sermaluc.sermalucbcichallenge.model.entity.User;

public class UserHelper {
    public static void copyUserAttrs(User source, User target) {
        target.setName(source.getName());
        target.setEmail(source.getEmail());
        target.setPassword(source.getPassword());
        target.setIsactive(source.getIsactive());
    }
}
