package org.hbrs.se1.ws23.uebung3.control;

import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategyStream;
import org.hbrs.se1.ws23.uebung3.view.MemberView;

import java.util.ArrayList;
import java.util.List;

public class Container {

    private static volatile Container instance;
    private static ArrayList<Member> list_of_members = null;
    private static PersistenceStrategy<Member> persistence_strategy = null;

    private Container(ArrayList<Member> list_of_members) {
        Container.list_of_members = list_of_members;
    }

    public static Container getInstance() {
        Container result = instance;
        if(result == null) {
            synchronized (Container.class) {
                result = instance;
                if(result == null) {
                    list_of_members = new ArrayList<Member>();
                    instance = result = new Container(list_of_members);
                }
            }
        }
        return result;
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
    }

    public void store() throws PersistenceException {
        persistence_strategy.openConnection();
        persistence_strategy.save(list_of_members);
        persistence_strategy.closeConnection();
    }

    public void load() throws PersistenceException {
        persistence_strategy.openConnection();
        list_of_members = (ArrayList) persistence_strategy.load();
        persistence_strategy.closeConnection();
    }

    public void setPersistence_strategy(PersistenceStrategy<Member> new_persistence_strategy) {
        persistence_strategy = new_persistence_strategy;
    }

    public List<Member> getCurrentList() {
        return list_of_members;
    }

    static class DemoMainStore {
        public static void main(String[] args) {
            Container demo = getInstance();
            demo.setPersistence_strategy(new PersistenceStrategyStream<>());
            Member member1 = new ConcreteMember(1);
            Member member2 = new ConcreteMember(2);
            Member member3 = new ConcreteMember(3);
            try {
                demo.addMember(member1);
                demo.addMember(member2);
                demo.addMember(member3);
            } catch (ContainerException e) {
                System.out.println("Das Hinzufügen hat leider nicht funktioniert.");
            }
            try {
                demo.store();
            } catch (PersistenceException e) {
                System.out.println("Das Speichern hat leider nicht funktioniert.");
            }
        }
    }

    static class DemoMainLoad {
        public static void main(String[] args) {
            Container demo = getInstance();
            demo.setPersistence_strategy(new PersistenceStrategyStream<>());
            try {
                demo.load();
            } catch (PersistenceException e) {
                System.out.println("Das Laden hat leider nicht funktioniert.");
            }
            MemberView.dump(demo.getCurrentList());
        }
    }
}
