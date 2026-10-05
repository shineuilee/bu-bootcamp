import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    private Contact contact;

    @BeforeEach
    void setUp() {
        contact = new Contact("Ada Lovelace", "+1 617 555 0101");
    }

    @Test
    void constructor_setsNameCorrectly() {
        assertEquals("Ada Lovelace", contact.getName());
    }

    @Test
    void constructor_setsPhoneCorrectly() {
        assertEquals("+1 617 555 0101", contact.getPhone());
    }

    @Test
    void getName_returnsExactString_notTransformed() {
        Contact anotherContact =
                new Contact("Grace Hopper", "555-0000");

        assertEquals("Grace Hopper", anotherContact.getName());
    }

    @Test
    void toString_containsName() {
        assertTrue(contact.toString().contains("Ada Lovelace"));
    }

    @Test
    void toString_containsPhone() {
        assertTrue(contact.toString().contains("+1 617 555 0101"));
    }
    @Test
    void twoContacts_withSameName_haveTheirOwnPhoneNumbers() {
        Contact contact1 =
                new Contact("John Smith", "555-1111");

        Contact contact2 =
                new Contact("John Smith", "555-2222");

        assertEquals("555-1111", contact1.getPhone());
        assertEquals("555-2222", contact2.getPhone());
    }}