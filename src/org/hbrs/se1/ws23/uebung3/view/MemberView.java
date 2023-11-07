package org.hbrs.se1.ws23.uebung3.view;

import org.hbrs.se1.ws23.uebung3.control.Member;

import java.util.List;

public class MemberView {
    public static void dump(List<Member> liste) {
        for(Member member: liste) {
            System.out.println(member);
        }
    }
}
