/*
 * Shiva Mahitha Maddi
 * 001061161
 */
package edu.neu.coe.info6205.functions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author mahit
 */
public class User implements Comparable<User> {

    private String name;
    private int id;
    private Date birth;

    public User(String name, int id, Date birth) {
        this.name = name;
        this.id = id;
        this.birth = birth;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || (this.getClass() != other.getClass())) {
            return false;
        }

        User guest = (User) other;
        return (this.id == guest.id) && (this.name != null && name.equals(guest.name))
                && (this.birth != null && birth.equals(guest.birth));
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birth != null ? birth.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(User o) {
        return this.id - o.id;
    }

    public static void main(String args[]) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        User u1, u2, u3, u4, u5;
        try {
            u1 = new User("User1", 1, sdf.parse("01/10/2019"));
            u2 = new User("User2", 2, sdf.parse("01/15/2019"));
            u3 = new User("User3", 3, sdf.parse("01/20/2019"));
            u4 = u1;
            u5 = new User("User5", 2, sdf.parse("01/25/2019"));

            System.out.println("Following User objects have been created: ");
            System.out.println("\tu1 -> name: User1, Id: 1, Dob: 01/10/2019");
            System.out.println("\tu2 -> name: User2, Id: 2, Dob: 01/15/2019");
            System.out.println("\tu3 -> name: User3, Id: 3, Dob: 01/20/2019");
            System.out.println("\tu4 -> name: User1, Id: 1, Dob: 01/10/2019");
            System.out.println("\tu5 -> name: User5, Id: 2, Dob: 01/25/2019");

            System.out.println();
            System.out.println("Testing 'equals()' method: ");
            System.out.println("\tu1.equals(u2) -> " + u1.equals(u2));
            System.out.println("\tu2.equals(u3) -> " + u2.equals(u3));
            System.out.println("\tu1.equals(u4) -> " + u1.equals(u4));
            System.out.println("\tu2.equals(u5) -> " + u2.equals(u5));

            System.out.println();
            System.out.println("Testing hashCode() method: ");
            System.out.println("\tu1.hashCode() -> " + u1.hashCode());
            System.out.println("\tu2.hashCode() -> " + u2.hashCode());
            System.out.println("\tu3.hashCode() -> " + u3.hashCode());
            System.out.println("\tu4.hashCode() -> " + u4.hashCode());
            System.out.println("\tu5.hashCode() -> " + u5.hashCode());
            System.out.println("\tu1.hashCode() == u2.hashCode() -> " + (u1.hashCode() == u2.hashCode()));
            System.out.println("\tu1.hashCode() == u4.hashCode() -> " + (u1.hashCode() == u4.hashCode()));
            System.out.println("\tu2.hashCode() == u5.hashCode() -> " + (u2.hashCode() == u5.hashCode()));

            System.out.println();
            System.out.println("Testing compareTo() method: ");
            System.out.println("\tu1.compareTo(u2) -> " + u1.compareTo(u2));
            System.out.println("\tu2.compareTo(u3) -> " + u1.compareTo(u3));
            System.out.println("\tu1.compareTo(u4) -> " + u1.compareTo(u4));
            System.out.println("\tu2.compareTo(u5) -> " + u2.compareTo(u5));

            System.out.println();
            System.out.println("Therefore, it can be seen that for u2 and u5 objects, ");
            System.out.println("equals() and hashCode() method returns false whereas,");
            System.out.println("compareTo() method returns 0 which is not correct");
            System.out.println("because only id is same and not the rest of the attributes");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
