package org.hbrs.se1.ws23.uebung4.prototype.view;

import org.hbrs.se1.ws23.uebung4.prototype.model.UserStory;

import java.util.Comparator;
import java.util.List;


public class UserStoryView {
    public static void showList(List<UserStory> list) {
        list.stream()
                .sorted(Comparator.comparingDouble(UserStory::getPrio))
                .forEach(userStory -> System.out.println(userStory));
    }

    public static void showProjectList(List<UserStory> list, String suchbegriff) {
        list.stream()
                .filter(userStory -> userStory.getProject().equals(suchbegriff))
                .sorted(Comparator.comparingDouble(UserStory::getPrio))
                .map(userStory -> "UserStory Nr. " + userStory.getId() + ", Titel: "
                        + userStory.getTitel() + ", Projekt " + userStory.getProject())
                .forEach(System.out::println);
    }

}
