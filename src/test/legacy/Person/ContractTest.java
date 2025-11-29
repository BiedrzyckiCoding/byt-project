//package test.legacy.Person;
//
//import main.Enums.ContractType;
//import main.MembershipTiers.MembershipCard;
//import main.Person.Contract;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ContractTest {
//
//    @BeforeEach
//    void setup() {
//        Contract.setExtent(List.of()); //reset extent before each test
//    }
//
//    @Test
//    void testConstructorValid() {
//        LocalDate start = LocalDate.now().minusDays(10);
//        LocalDate end = LocalDate.now().plusDays(10);
//        Contract c = new Contract(ContractType.EMPLOYMENT, start, end);
//
//        assertEquals(ContractType.EMPLOYMENT, c.getType());
//        assertEquals(start, c.getEmploymentDate());
//        assertEquals(end, c.getEmploymentDueDate());
//        assertTrue(Contract.getExtent().contains(c));
//    }
//
//    @Test
//    void testConstructorRejectsNullType() {
//        LocalDate start = LocalDate.now().minusDays(5);
//        LocalDate end = LocalDate.now().plusDays(5);
//
//        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
//                () -> new Contract(null, start, end));
//        assertTrue(ex.getMessage().contains("type"));
//    }
//
//    @Test
//    void testConstructorRejectsFutureEmploymentDate() {
//        LocalDate futureDate = LocalDate.now().plusDays(1);
//        LocalDate end = LocalDate.now().plusDays(10);
//
//        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
//                () -> new Contract(ContractType.COMMISSION, futureDate, end));
//        assertTrue(ex.getMessage().contains("employmentDate"));
//    }
//
//    @Test
//    void testConstructorRejectsInvalidDateOrder() {
//        LocalDate start = LocalDate.now();
//        LocalDate end = LocalDate.now().minusDays(1);
//
//        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
//                () -> new Contract(ContractType.EMPLOYMENT, start, end));
//        assertTrue(ex.getMessage().contains("End date cannot be before start date"));
//    }
//
//    @Test
//    void testPrivateAddToExtentRejectsNull() throws Exception {
//        Method method = Contract.class.getDeclaredMethod("addToExtent", Contract.class);
//        method.setAccessible(true);
//
//        InvocationTargetException ex = assertThrows(InvocationTargetException.class,
//                () -> method.invoke(null, (Object) null));
//
//        assertTrue(ex.getCause() instanceof IllegalArgumentException);
//        assertTrue(ex.getCause().getMessage().contains("Contract cannot be null"));
//    }
//
//    @Test
//    void testSettersAndGetters() {
//        LocalDate start = LocalDate.now().minusDays(10);
//        LocalDate end = LocalDate.now().plusDays(10);
//        Contract c = new Contract(ContractType.EMPLOYMENT, start, end);
//
//        //new values
//        LocalDate newStart = start.minusDays(5);
//        LocalDate newEnd = end.plusDays(5);
//        c.setType(ContractType.COMMISSION);
//        c.setEmploymentDate(newStart);
//        c.setEmploymentDueDate(newEnd);
//
//        //assert getters return updated values
//        assertEquals(ContractType.COMMISSION, c.getType());
//        assertEquals(newStart, c.getEmploymentDate());
//        assertEquals(newEnd, c.getEmploymentDueDate());
//    }
//
//    @Test
//    void testExtentManipulation() {
//        Contract c1 = new Contract(ContractType.EMPLOYMENT, LocalDate.now().minusDays(10), LocalDate.now().plusDays(10));
//        Contract c2 = new Contract(ContractType.COMMISSION, LocalDate.now().minusDays(5), LocalDate.now().plusDays(5));
//
//        List<Contract> extent = Contract.getExtent();
//        assertEquals(2, extent.size());
//        assertTrue(extent.contains(c1));
//        assertTrue(extent.contains(c2));
//
//        Contract.setExtent(List.of());
//        assertTrue(Contract.getExtent().isEmpty());
//    }
//}
