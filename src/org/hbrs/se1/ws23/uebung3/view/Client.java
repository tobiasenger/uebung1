package org.hbrs.se1.ws23.uebung3.view;

import org.hbrs.se1.ws23.uebung3.control.ConcreteMember;
import org.hbrs.se1.ws23.uebung3.control.Container;
import org.hbrs.se1.ws23.uebung3.control.ContainerException;
import org.hbrs.se1.ws23.uebung3.control.Member;

public class Client {
    public static void createAddDump() {
        Member member1 = new ConcreteMember(1);
        Member member2 = new ConcreteMember(2);
        Member member3 = new ConcreteMember(3);
        Member member4 = new ConcreteMember(4);
        Member member5 = new ConcreteMember(5);

        Container container = Container.getInstance();

        try {
            container.addMember(member1);
            container.addMember(member2);
            container.addMember(member3);
            container.addMember(member4);
            container.addMember(member5);
        } catch (ContainerException e) {
            System.out.println("Methode fehlgeschlagen");
        }

        MemberView.dump(container.getCurrentList());
    }
}
