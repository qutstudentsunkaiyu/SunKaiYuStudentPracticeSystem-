package com.exercise.budgetreal.support;


import com.exercise.budgetreal.entity.User;

public class UserHolder {
    private static final ThreadLocal<User> hodler = new ThreadLocal<User>();

    public static void set(User user){
        hodler.set(user);
    }

    public static User get(){
        return hodler.get();
    }

    public static boolean isLogin(){

        return get() != null;
    }
}
