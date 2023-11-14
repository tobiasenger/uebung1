package org.hbrs.se1.ws23.uebung3.test;
import org.hbrs.se1.ws23.uebung3.control.ConcreteMember;
import org.hbrs.se1.ws23.uebung3.control.Container;
import org.hbrs.se1.ws23.uebung3.control.ContainerException;
import org.hbrs.se1.ws23.uebung3.control.Member;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategyStream;
import org.hbrs.se1.ws23.uebung3.view.MemberView;
import org.junit.jupiter.api.AfterEach;
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
        member_1 = new ConcreteMember(1);
        member_2 = new ConcreteMember(2);
        member_3 = new ConcreteMember(3);
        member_4 = new ConcreteMember(1);
    }

    @AfterEach
    public void tearDown() {
        try {
            container.deleteMember(1);
            container.deleteMember(2);
            container.deleteMember(3);
            container.setPersistence_strategy(null);
        } catch (Exception e) {
            System.out.println("Fehler beim Teardown aufgetreten.");
        }
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
        container.deleteMember(1);
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
        container.deleteMember(2);
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

    @Test
    public void testNoStrategySet() {
        assertThrows(PersistenceException.class, () -> container.store());
    }

    @Test
    public void testUnimplementedStrategy() {
        container.setPersistence_strategy(new PersistenceStrategyMongoDB<>());
        assertThrows(PersistenceException.class, () -> container.store());
    }

    @Test
    public void testWrongFileLocation() {
        PersistenceStrategyStream<Member> ps = new PersistenceStrategyStream<>();
        ps.setLocation("/");
        container.setPersistence_strategy(ps);
        assertThrows(PersistenceException.class, () -> container.store());
    }

    @Test
    public void testRoundTrip() {
        container.setPersistence_strategy(new PersistenceStrategyStream<>());
        try {
            assertEquals(0, container.size());
            container.addMember(member_1);
            assertEquals(1, container.size());
            container.addMember(member_2);
            assertEquals(2, container.size());
            container.store();
        } catch (ContainerException e) {
            System.out.println("Fehler beim Testablauf.");
        } catch (PersistenceException p) {
            System.out.println("Fehler beim Abspeichern.");
        }
        try {
            assertEquals(2, container.size());
            container.deleteMember(1);
            assertEquals(1, container.size());
            container.deleteMember(2);
            assertEquals(0, container.size());
            container.load();
            assertEquals(2, container.size());
        } catch (PersistenceException p) {
            System.out.println("Fehler beim Abspeichern.");
        }
    }
}
