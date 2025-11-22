//package test.Person;
//
//import main.Person.Contract;
//import main.Person.Employee;
//import main.Enums.ContractType;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class EmployeeTest {
//
//    private Contract sampleContract;
//    private List<Employee> emptySubordinates;
//
//    @BeforeEach
//    void setup() {
//        Employee.setExtent(List.of());  //reset extent before each test
//
//        sampleContract = new Contract(
//                ContractType.EMPLOYMENT,
//                LocalDate.now().minusDays(10),
//                LocalDate.now().plusDays(300)
//        );
//
//        emptySubordinates = new ArrayList<>();
//    }
//
//    @Test
//    void testValidConstructor() {
//        Employee e = new Employee(
//                "John",
//                List.of("Street 1"),
//                "pjatk",
//                "john.pjatk@example.com",
//                LocalDate.of(1990, 1, 1),
//                4500.0,
//                10,
//                sampleContract,
//                emptySubordinates
//        );
//
//        assertEquals(4500.0, e.getSalary());
//        assertEquals(10, e.getItemsSold());
//        assertEquals(sampleContract, e.getContract());
//        assertEquals(emptySubordinates, e.getSubordinates());
//        assertTrue(Employee.getExtent().contains(e));
//    }
//
//
//    @Test
//    void testConstructorRejectsNegativeSalary() {
//        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
//                () -> new Employee(
//                        "John",
//                        List.of("Street 1"),
//                        "pjatk",
//                        "john.pjatk@example.com",
//                        LocalDate.of(1990, 1, 1),
//                        -5.0,
//                        10,
//                        sampleContract,
//                        emptySubordinates
//                ));
//        assertTrue(ex.getMessage().contains("salary"));
//    }
//
//    @Test
//    void testConstructorRejectsNegativeItemsSold() {
//        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
//                () -> new Employee(
//                        "John",
//                        List.of("Street 1"),
//                        "pjatk",
//                        "john.pjatk@example.com",
//                        LocalDate.of(1990, 1, 1),
//                        5000,
//                        -1,
//                        sampleContract,
//                        emptySubordinates
//                ));
//        assertTrue(ex.getMessage().contains("itemsSold"));
//    }
//
//    @Test
//    void testConstructorRejectsNullContract() {
//        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
//                () -> new Employee(
//                        "John",
//                        List.of("Street 1"),
//                        "pjatk",
//                        "john.pjatk@example.com",
//                        LocalDate.of(1990, 1, 1),
//                        5000,
//                        2,
//                        null,
//                        emptySubordinates
//                ));
//        assertTrue(ex.getMessage().contains("contract"));
//    }
//
//    @Test
//    void testConstructorRejectsNullSubordinates() {
//        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
//                () -> new Employee(
//                        "John",
//                        List.of("Street 1"),
//                        "pjatk",
//                        "john.pjatk@example.com",
//                        LocalDate.of(1990, 1, 1),
//                        5000,
//                        2,
//                        sampleContract,
//                        null
//                ));
//        assertTrue(ex.getMessage().contains("subordinates"));
//    }
//
//
//    @Test
//    void testGetterSetterForSalary() {
//        Employee e = new Employee(
//                "John",
//                List.of("Street 1"),
//                "pjatk",
//                "john.pjatk@example.com",
//                LocalDate.of(1990, 1, 1),
//                2000,
//                5,
//                sampleContract,
//                emptySubordinates
//        );
//
//        e.setSalary(7000);
//        assertEquals(7000, e.getSalary());
//    }
//
//    @Test
//    void testGetterSetterForItemsSold() {
//        Employee e = new Employee(
//                "John",
//                List.of("Street 1"),
//                "pjatk",
//                "john.pjatk@example.com",
//                LocalDate.of(1990, 1, 1),
//                2000,
//                5,
//                sampleContract,
//                emptySubordinates
//        );
//
//        e.setItemsSold(99);
//        assertEquals(99, e.getItemsSold());
//    }
//
//    @Test
//    void testGetterSetterForContract() {
//        Employee e = new Employee(
//                "John",
//                List.of("Street 1"),
//                "pjatk",
//                "john.pjatk@example.com",
//                LocalDate.of(1990, 1, 1),
//                2000,
//                5,
//                sampleContract,
//                emptySubordinates
//        );
//
//        Contract newContract = new Contract(
//                ContractType.COMMISSION,
//                LocalDate.now().minusDays(5),
//                LocalDate.now().plusDays(200)
//        );
//
//        e.setContract(newContract);
//        assertEquals(newContract, e.getContract());
//    }
//
//    @Test
//    void testGetterSetterForSubordinates() {
//        Employee e = new Employee(
//                "John",
//                List.of("Street 1"),
//                "pjatk",
//                "john.pjatk@example.com",
//                LocalDate.of(1990, 1, 1),
//                2000,
//                5,
//                sampleContract,
//                emptySubordinates
//        );
//
//        List<Employee> subs = new ArrayList<>();
//        subs.add(new Employee(
//                "Marek",
//                List.of("A"),
//                "asujdashbd",
//                "m@e.com",
//                LocalDate.of(1995, 5, 5),
//                1000,
//                1,
//                sampleContract,
//                new ArrayList<>()
//        ));
//
//        e.setSubordinates(subs);
//        assertEquals(subs, e.getSubordinates());
//    }
//
//
//    @Test
//    void testExtentStoresEmployees() {
//        Employee e1 = new Employee("A", List.of("1"), "B", "a@b.com", LocalDate.of(1990,1,1),
//                1000, 0, sampleContract, new ArrayList<>());
//
//        Employee e2 = new Employee("C", List.of("1"), "D", "c@d.com", LocalDate.of(1995,1,1),
//                2000, 2, sampleContract, new ArrayList<>());
//
//        List<Employee> extent = Employee.getExtent();
//
//        assertEquals(2, extent.size()); //should be 2
//        assertTrue(extent.contains(e1));
//        assertTrue(extent.contains(e2));
//    }
//
//    @Test
//    void testSetExtentReplacesExistingExtent() {
//        Employee e = new Employee("A", List.of("1"), "B", "a@b.com", LocalDate.of(1990,1,1),
//                1000, 0, sampleContract, new ArrayList<>());
//
//        Employee.setExtent(List.of());
//        assertTrue(Employee.getExtent().isEmpty()); //overwriting extent
//    }
//
//    @Test
//    void testPrivateAddToExtentRejectsNull() throws Exception {
//        Method m = Employee.class.getDeclaredMethod("addToExtent", Employee.class);
//        m.setAccessible(true);
//
//        InvocationTargetException ex = assertThrows(InvocationTargetException.class,
//                () -> m.invoke(null, (Object) null)); //like in other tests, need to unpack
//
//        assertTrue(ex.getCause() instanceof IllegalArgumentException);
//        assertTrue(ex.getCause().getMessage().contains("Employee cannot be null"));
//    }
//
//}
