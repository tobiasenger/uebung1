package org.hbrs.se1.ws23.uebung2;

import java.util.ArrayList;

public class Container {
    private ArrayList<Member> list_of_members;
    private Integer size;


    public Container() {
        list_of_members = new ArrayList<>();
        size = 0;
    }

    public Integer size() {
        return size;
    }


    public void addMember(Member member) throws ContainerException {


        for(Member member_in_list: list_of_members) {
                if(member_in_list.getID() == member.getID()) {
                    throw new ContainerException(member.getID());
                }
        }
        list_of_members.add(member);
        size++;
    }

    public String deleteMember(Integer id) {

        for(Member member_in_list: list_of_members) {
            if(member_in_list.getID() == id) {
                list_of_members.remove(member_in_list);
                size--;
                return "Member gelöscht";
            }
        }
        return "Member nicht in Container gefunden";
    }

    public void dump() {

    }
}
