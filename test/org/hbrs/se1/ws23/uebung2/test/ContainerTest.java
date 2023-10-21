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

    Member member_4;

    @BeforeEach
    public void setUp() {
        container = new Container();
        member_1 = new ConcreteMember(3);
        member_2 = new ConcreteMember(8);
        member_3 = new ConcreteMember(11);
        member_4 = new ConcreteMember(3);
    }

    @Test
    public void testAddEmpty() {
        assertEquals(0, container.size());
        try {
            container.addMember(member_1);
        } catch (ContainerException e) { }
        assertEquals(1, container.size());
    }

    @Test
    public void testAddNotEmpty() {
        assertEquals(0, container.size());
        try {
            container.addMember(member_1);
            assertEquals(1, container.size());
            container.addMember(member_2);
        } catch (ContainerException e) { }
        assertEquals(2, container.size());
    }

    @Test
    public void testAddAlreadyExisting() {
        assertEquals(0, container.size());
        try {
            container.addMember(member_1);
        } catch (ContainerException e) { }
        assertThrows(ContainerException.class, () -> container.addMember(member_4));
    }

    @Test
    public void testDeleteEmptyNotExisting() {
        container.deleteMember(2);
        assertEquals(0, container.size());
    }

    @Test
    public void testDeleteNonEmptyNotExisting() {
        try {
            container.addMember(member_1);
        } catch(ContainerException e) { }
        container.deleteMember(2);
        assertEquals(1, container.size());
    }

    @Test
    public void testDeleteNonEmptyExisting() {
        try {
            container.addMember(member_1);
        } catch(ContainerException e) { }
        assertEquals(1, container.size());
        container.deleteMember(3);
        assertEquals(0, container.size());
    }
}
