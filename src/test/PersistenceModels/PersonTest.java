package test.PersistenceModels;

import main.PersistenceModels.Customer;
import main.PersistenceModels.DebitCard;
import main.PersistenceModels.Employee;
import main.PersistenceModels.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private Customer.CustomerData validCustomerData;
    private Employee.EmployeeData validEmployeeData;
    private List<String> validAddress;
    private LocalDate validBirthDate;

    @BeforeEach
    void setUp() {
//        Person.setExtent(Collections.emptyList());

        validAddress = List.of("123 Main St", "City");
        validBirthDate = LocalDate.of(1990, 1, 1);

        DebitCard debitCard = new DebitCard("1234123412341234", LocalDate.now().plusYears(1), "123");
        validCustomerData = new Customer.CustomerData("john_acc", LocalDate.now(), 0.0, debitCard);
        validEmployeeData = new Employee.EmployeeData(5000.0, 10);
    }

    @AfterEach
    void tearDown() {
//        Person.setExtent(Collections.emptyList());
    }

    @Test
    void constructor_shouldCreatePerson_whenCustomerDataProvided() {
        Person person = new Person("John", validAddress, "Doe", "mail@test.com", validBirthDate, validCustomerData);
        assertNotNull(person);
    }

    @Test
    void constructor_shouldCreatePerson_whenEmployeeDataProvided() {
        Person person = new Person("John", validAddress, "Doe", "mail@test.com", validBirthDate, validEmployeeData);
        assertNotNull(person);
    }

    @Test
    void constructor_shouldCreatePerson_whenBothDataProvided() {
        Person person = new Person("John", validAddress, "Doe", "mail@test.com", validBirthDate, validCustomerData, validEmployeeData);
        assertNotNull(person);
    }

    @Test
    void constructor_shouldThrowException_whenNameEmpty() {
        assertThrows(Exception.class, () ->
                new Person("", validAddress, "Doe", "mail@test.com", validBirthDate, validCustomerData)
        );
    }

    @Test
    void constructor_shouldThrowException_whenAddressEmpty() {
        assertThrows(Exception.class, () ->
                new Person("John", List.of(), "Doe", "mail@test.com", validBirthDate, validCustomerData)
        );
    }

    @Test
    void constructor_shouldThrowException_whenBirthDateFuture() {
        assertThrows(Exception.class, () ->
                new Person("John", validAddress, "Doe", "mail@test.com", LocalDate.now().plusDays(1), validCustomerData)
        );
    }

    @Test
    void constructor_shouldAddToExtent_whenValid() {
        Person person = new Person("John", validAddress, "Doe", "mail@test.com", validBirthDate, validCustomerData);
        assertTrue(Person.getExtent().contains(person));
    }

    @Test
    void isCustomer_shouldReturnTrue_whenCreatedAsCustomer() {
        Person person = new Person("John", validAddress, "Doe", "mail@test.com", validBirthDate, validCustomerData);
        assertTrue(person.isCustomer());
    }

    @Test
    void isEmployee_shouldReturnFalse_whenCreatedAsCustomer() {
        Person person = new Person("John", validAddress, "Doe", "mail@test.com", validBirthDate, validCustomerData);
        assertFalse(person.isEmployee());
    }

    @Test
    void isEmployee_shouldReturnTrue_whenCreatedAsEmployee() {
        Person person = new Person("John", validAddress, "Doe", "mail@test.com", validBirthDate, validEmployeeData);
        assertTrue(person.isEmployee());
    }

    @Test
    void asCustomer_shouldReturnRole_whenExists() {
        Person person = new Person("John", validAddress, "Doe", "mail@test.com", validBirthDate, validCustomerData);
        assertNotNull(person.asCustomer());
    }

    @Test
    void asCustomer_shouldThrowException_whenRoleMissing() {
        Person person = new Person("John", validAddress, "Doe", "mail@test.com", validBirthDate, validEmployeeData);
        assertThrows(IllegalStateException.class, () -> person.asCustomer());
    }

    @Test
    void asEmployee_shouldReturnRole_whenExists() {
        Person person = new Person("John", validAddress, "Doe", "mail@test.com", validBirthDate, validEmployeeData);
        assertNotNull(person.asEmployee());
    }

    @Test
    void asEmployee_shouldThrowException_whenRoleMissing() {
        Person person = new Person("John", validAddress, "Doe", "mail@test.com", validBirthDate, validCustomerData);
        assertThrows(IllegalStateException.class, () -> person.asEmployee());
    }

    @Test
    void attachCustomer_shouldAddRole_whenNotExists() {
        Person person = new Person("John", validAddress, "Doe", "mail@test.com", validBirthDate, validEmployeeData);
        person.attachCustomer(validCustomerData);
        assertTrue(person.isCustomer());
    }

    @Test
    void attachCustomer_shouldThrowException_whenAlreadyCustomer() {
        Person person = new Person("John", validAddress, "Doe", "mail@test.com", validBirthDate, validCustomerData);
        assertThrows(IllegalStateException.class, () -> person.attachCustomer(validCustomerData));
    }

    @Test
    void attachEmployee_shouldAddRole_whenNotExists() {
        Person person = new Person("John", validAddress, "Doe", "mail@test.com", validBirthDate, validCustomerData);
        person.attachEmployee(validEmployeeData);
        assertTrue(person.isEmployee());
    }

    @Test
    void attachEmployee_shouldThrowException_whenAlreadyEmployee() {
        Person person = new Person("John", validAddress, "Doe", "mail@test.com", validBirthDate, validEmployeeData);
        assertThrows(IllegalStateException.class, () -> person.attachEmployee(validEmployeeData));
    }

    @Test
    void detachCustomer_shouldRemoveRole_whenBothRolesExist() {
        Person person = new Person("John", validAddress, "Doe", "mail@test.com", validBirthDate, validCustomerData, validEmployeeData);
        person.detachCustomer();
        assertFalse(person.isCustomer());
    }

    @Test
    void detachCustomer_shouldThrowException_whenOnlyRole() {
        Person person = new Person("John", validAddress, "Doe", "mail@test.com", validBirthDate, validCustomerData);
        assertThrows(IllegalStateException.class, () -> person.detachCustomer());
    }

    @Test
    void detachEmployee_shouldRemoveRole_whenBothRolesExist() {
        Person person = new Person("John", validAddress, "Doe", "mail@test.com", validBirthDate, validCustomerData, validEmployeeData);
        person.detachEmployee();
        assertFalse(person.isEmployee());
    }

    @Test
    void detachEmployee_shouldThrowException_whenOnlyRole() {
        Person person = new Person("John", validAddress, "Doe", "mail@test.com", validBirthDate, validEmployeeData);
        assertThrows(IllegalStateException.class, () -> person.detachEmployee());
    }

    @Test
    void setName_shouldUpdateValue_whenValid() {
        Person person = new Person("John", validAddress, "Doe", "mail@test.com", validBirthDate, validCustomerData);
        person.setName("NewName");
        assertEquals("NewName", person.getName());
    }

    @Test
    void setName_shouldThrowException_whenEmpty() {
        Person person = new Person("John", validAddress, "Doe", "mail@test.com", validBirthDate, validCustomerData);
        assertThrows(Exception.class, () -> person.setName(""));
    }

    @Test
    void setAddress_shouldUpdateValue_whenValid() {
        Person person = new Person("John", validAddress, "Doe", "mail@test.com", validBirthDate, validCustomerData);
        List<String> newAddr = List.of("New", "Address");
        person.setAddress(newAddr);
        assertEquals(newAddr, person.getAddress());
    }

    @Test
    void setAddress_shouldThrowException_whenEmpty() {
        Person person = new Person("John", validAddress, "Doe", "mail@test.com", validBirthDate, validCustomerData);
        assertThrows(Exception.class, () -> person.setAddress(List.of()));
    }

    @Test
    void getAge_shouldCalculateCorrectly() {
        int expectedAge = LocalDate.now().getYear() - 1990;
        Person person = new Person("John", validAddress, "Doe", "mail@test.com", validBirthDate, validCustomerData);
        assertEquals(expectedAge, person.getAge());
    }
}