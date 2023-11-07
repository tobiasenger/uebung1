package org.hbrs.se1.ws23.uebung2;

import java.util.ArrayList;

public class Container {
    private final ArrayList<Member> list_of_members;

    public Container() {
        list_of_members = new ArrayList<>();
    }

    public Integer size() {
        return list_of_members.size();
    }


    public void addMember(Member member) throws ContainerException {
        for(Member member_in_list: list_of_members) {
                if(member_in_list.getID() == member.getID()) {
                    throw new ContainerException(member.getID());
                }
        }
        list_of_members.add(member);
    }

    public String deleteMember(Integer id) {

        for(Member member_in_list: list_of_members) {
            if(member_in_list.getID() == id) {
                list_of_members.remove(member_in_list);
                return "Member gelöscht";
            }
        }
        return "Member nicht in Container gefunden";

        /*
         * Antwort auf die Frage zu FA2:
         *
         * Problematisch an dieser Form des Fehlerhandlings ist die Tatsache, dass die Rückgabewerte (hier String)
         * innerhalb der aufrufenden Methode aufwendig interpretiert werden müssen, um herauszufinden, wie mit dem
         * Fehler verfahren werden soll. Bei einer geprüften Exception andererseits, kann innerhalb der try-catch
         * Blöcke anhand der Klasse der Exception erkannt werden, um was für einen Fehler es sich handelt und
         * entschieden werden, wie mit diesem umzugehen ist.
         */
    }

    public void dump() {
        for(Member member_in_list: list_of_members) {
            System.out.println(member_in_list);
        }
    }
}
