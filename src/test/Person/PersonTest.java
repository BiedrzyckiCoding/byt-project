package test.Person;

import main.Person.Person;
import main.Utils.ValidationUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    static class TestPerson extends Person {
        public TestPerson(String name, List<String> address, String surname, String email, LocalDate birthDate) {
            super(name, address, surname, email, birthDate);
        }
    }

    @Test
    void testValidConstructor() {
        LocalDate birth = LocalDate.of(1990, 1, 1);
        Person p = new TestPerson("John", List.of("Street 1"), "Doe", "john@example.com", birth);

        assertEquals("John", p.getName());
        assertEquals(List.of("Street 1"), p.getAddress());
        assertEquals("Doe", p.getSurname());
        assertEquals("john@example.com", p.getEmail());
        assertEquals(birth, p.getBirthDate());

        int expectedAge = LocalDate.now().getYear() - birth.getYear();
        assertEquals(expectedAge, p.getAge());
    }


    @Test
    void testConstructorRejectsNullName() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new TestPerson(null, List.of("Addr"), "Doe", "john@example.com", LocalDate.of(1990, 1, 1)));
        assertTrue(ex.getMessage().contains("name"));
    }

    @Test
    void testConstructorRejectsEmptyAddressList() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new TestPerson("John", List.of(), "Doe", "john@example.com", LocalDate.of(1990, 1, 1)));
        assertTrue(ex.getMessage().contains("address"));
    }

    @Test
    void testConstructorRejectsNullSurname() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new TestPerson("John", List.of("Addr"), null, "john@example.com", LocalDate.of(1990, 1, 1)));
        assertTrue(ex.getMessage().contains("surname"));
    }

    @Test
    void testConstructorRejectsNullEmail() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new TestPerson("John", List.of("Addr"), "Doe", null, LocalDate.of(1990, 1, 1)));
        assertTrue(ex.getMessage().contains("email"));
    }

    @Test
    void testConstructorRejectsBirthDateInFuture() {
        LocalDate future = LocalDate.now().plusDays(1);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new TestPerson("John", List.of("Addr"), "Doe", "john@example.com", future));
        assertTrue(ex.getMessage().contains("birthDate"));
    } //all the stuff from constructor



    @Test
    void testSetterGetterName() {
        Person p = new TestPerson("John", List.of("A"), "Doe", "e@mail.com", LocalDate.of(1990, 1, 1));
        p.setName("Adam");
        assertEquals("Adam", p.getName());
    }

    @Test
    void testSetterGetterAddress() {
        Person p = new TestPerson("John", List.of("A"), "Doe", "e@mail.com", LocalDate.of(1990, 1, 1));
        p.setAddress(List.of("New Street"));
        assertEquals(List.of("New Street"), p.getAddress());
    }

    @Test
    void testSetterGetterSurname() {
        Person p = new TestPerson("John", List.of("A"), "Doe", "e@mail.com", LocalDate.of(1990, 1, 1));
        p.setSurname("Nowak");
        assertEquals("Nowak", p.getSurname());
    }

    @Test
    void testSetterGetterEmail() {
        Person p = new TestPerson("John", List.of("A"), "Doe", "e@mail.com", LocalDate.of(1990, 1, 1));
        p.setEmail("new@mail.com");
        assertEquals("new@mail.com", p.getEmail());
    }

    @Test
    void testSetterGetterBirthDate() {
        Person p = new TestPerson("John", List.of("A"), "Doe", "e@mail.com", LocalDate.of(1990, 1, 1));
        LocalDate newBirth = LocalDate.of(2000, 1, 1);
        p.setBirthDate(newBirth);
        assertEquals(newBirth, p.getBirthDate());
    }

    @Test
    void testGetAgeRecalculatesCorrectly() {
        LocalDate birth = LocalDate.of(2000, 1, 1);
        Person p = new TestPerson("John", List.of("A"), "Doe", "e@mail.com", birth);

        int expected = LocalDate.now().getYear() - 2000;
        assertEquals(expected, p.getAge());
    }
}
