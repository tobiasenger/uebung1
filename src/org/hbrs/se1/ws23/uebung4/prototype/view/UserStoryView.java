package org.hbrs.se1.ws23.uebung4.prototype.view;

import org.hbrs.se1.ws23.uebung4.prototype.model.UserStory;

import java.util.List;


public class UserStoryView {
    public static void showList(List<UserStory> list) {
        list.stream()
                .forEach(System.out::println);
    }
}
