import java.util.*;

public class ContactManager {

    public static void main(String[] args) {

        HashMap<String, Contact> contacts = new HashMap<>();

        contacts.put("Ada Lovelace",
                new Contact("Ada Lovelace", "+1 617 555 0101"));

        contacts.put("John Smith",
                new Contact("John Smith", "+1 410 555 0123"));

        contacts.put("Maria Garcia",
                new Contact("Maria Garcia", "+1 301 555 0145"));

        contacts.put("David Lee",
                new Contact("David Lee", "+1 443 555 0167"));

        contacts.put("Sarah Kim",
                new Contact("Sarah Kim", "+1 240 555 0189"));

        Contact found = contacts.get("Ada Lovelace");

        if (found == null) {
            System.out.println("Contact not found.");
        } else {
            System.out.println("Lookup result:");
            System.out.println(found);
        }

        Contact missing = contacts.get("Michael Brown");

        if (missing == null) {
            System.out.println("Contact not found.");
        } else {
            System.out.println(missing);
        }

        ArrayList<Contact> sorted =
                new ArrayList<>(contacts.values());

        sorted.sort((a, b) ->
                a.getName().compareTo(b.getName()));

        System.out.println();
        System.out.println("=== All Contacts ===");

        for (Contact contact : sorted) {
            System.out.println(contact);
        }
    }
}