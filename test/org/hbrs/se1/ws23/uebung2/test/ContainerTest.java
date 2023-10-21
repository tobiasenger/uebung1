package org.hbrs.se1.ws23.uebung2.test;

import org.hbrs.se1.ws23.uebung2.ConcreteMember;
import org.hbrs.se1.ws23.uebung2.Container;
import org.hbrs.se1.ws23.uebung2.ContainerException;
import org.hbrs.se1.ws23.uebung2.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {

    Container container;
    Member member_1;
    Member member_2;
    Member member_3;

    @BeforeEach
    public void setUp() {
        container = new Container();
        member_1 = new ConcreteMember(3);
        member_2 = new ConcreteMember(8);
        member_3 = new ConcreteMember(11);
    }

    @Test
    public void testAddFirst() {
        int size;
        assertEquals(0, container.size());
        try {
            container.addMember(member_1);
        } catch (ContainerException e) {
            size = -1;
        }
        assertEquals(1, container.size());
    }
}
