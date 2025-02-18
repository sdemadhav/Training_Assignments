package com.example.empManageApp.models;

import org.springframework.context.annotation.Scope;

@Scope("singleton")
public final class Ceo extends Employee {

    private static boolean isCreated = false;

    private Ceo() {
        super("CEO", 100000);
        setCeo();
    }

    public static Ceo getCeoInstance() {
        if (isCreated) {
            System.out.println("CEO already exists");
            return null; 
        }
        isCreated = true;
        return new Ceo();
    }

    private void setCeo() {
        isCreated = true;
    }
}
