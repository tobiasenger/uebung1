package org.hbrs.se1.ws23.uebung3.test;
import org.hbrs.se1.ws23.uebung3.control.ConcreteMember;
import org.hbrs.se1.ws23.uebung3.control.Container;
import org.hbrs.se1.ws23.uebung3.control.ContainerException;
import org.hbrs.se1.ws23.uebung3.control.Member;
import org.hbrs.se1.ws23.uebung3.view.MemberView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {

    Container container;
    Member member_1;
    Member member_2;
    Member member_3;
    Member member_4;

    @BeforeEach
    public void setUp() {
        container = Container.getInstance();
        member_1 = new ConcreteMember(3);
        member_2 = new ConcreteMember(8);
        member_3 = new ConcreteMember(11);
        member_4 = new ConcreteMember(3);
    }

    @Test
    public void testEmptyContainer() {
        assertEquals(0, container.size());
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
        } catch (ContainerException e) {
            System.out.println("Hinzufügen vor eigentlichem Test fehlgeschlagen!");
        }
        assertEquals(2, container.size());
    }

    @Test
    public void testAddAlreadyExisting() {
        assertEquals(0, container.size());
        try {
            container.addMember(member_1);
        } catch (ContainerException e) {
            System.out.println("Hinzufügen vor eigentlichem Test fehlgeschlagen!");
        }
        assertThrows(ContainerException.class, () -> container.addMember(member_4));
        assertEquals(1, container.size());
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
        } catch(ContainerException e) {
            System.out.println("Hinzufügen vor eigentlichem Test fehlgeschlagen!");
        }
        container.deleteMember(2);
        assertEquals(1, container.size());
    }

    @Test
    public void testDeleteNonEmptyExistingToZero() {
        try {
            container.addMember(member_1);
        } catch(ContainerException e) {
            System.out.println("Hinzufügen vor eigentlichem Test fehlgeschlagen!");
        }
        assertEquals(1, container.size());
        container.deleteMember(3);
        assertEquals(0, container.size());
    }

    @Test
    public void testDeleteNonEmptyExistingToNonZero() {
        try {
            container.addMember(member_1);
            container.addMember(member_2);
        } catch(ContainerException e) {
            System.out.println("Hinzufügen vor eigentlichem Test fehlgeschlagen!");
        }
        assertEquals(2, container.size());
        container.deleteMember(3);
        assertEquals(1, container.size());
    }

    @Test
    public void testDump() {
        try {
            container.addMember(member_1);
            container.addMember(member_2);
        } catch (ContainerException e) {
            System.out.println("Hinzufügen vor eigentlichem Test fehlgeschlagen!");
        }
        assertEquals(2, container.size());
        MemberView.dump(new ArrayList<>());
        assertEquals(2, container.size());
    }

    @Test
    public void testSize() {
        try {
            container.addMember(member_1);
            container.addMember(member_2);
        } catch (ContainerException e) {
            System.out.println("Hinzufügen vor eigentlichem Test fehlgeschlagen!");
        }
        assertEquals(2, container.size());
        container.size();
        assertEquals(2, container.size());
    }

    @Test
    public void testAddNull() {
        try {
            container.addMember(member_1);
            container.addMember(member_2);
        } catch (ContainerException e) {
            System.out.println("Hinzufügen vor eigentlichem Test fehlgeschlagen!");
        }
        assertEquals(2, container.size());
        assertThrows(NullPointerException.class, () -> container.addMember(null));
        assertEquals(2, container.size());
    }

    @Test
    public void testDeleteNull() {
        try {
            container.addMember(member_1);
            container.addMember(member_2);
        } catch (ContainerException e) {
            System.out.println("Hinzufügen vor eigentlichem Test fehlgeschlagen!");
        }
        assertEquals(2, container.size());
        container.deleteMember(null);
        assertEquals(2, container.size());
    }
}
