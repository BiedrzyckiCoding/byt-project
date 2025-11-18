package main;

import main.Clothing.*;
import main.Enums.*;
import main.Footwear.*;
import main.MembershipTiers.*;
import main.Order.*;
import main.Person.*;
import main.Utils.PersistenceUtil;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PersistenceUtil.loadAll();

//        createSampleData();

        PersistenceUtil.saveAll();

        PersistenceUtil.loadAll();

        printExtentSizes();

        Hoodie anyHoodie = Hoodie.getExtent().isEmpty() ? null : Hoodie.getExtent().getFirst();
        if (anyHoodie != null) {
            System.out.println("Example hoodie after load: " + anyHoodie.getName() + " - " + anyHoodie.getBrand() + " - " + anyHoodie.getPrice() + " - " + anyHoodie.getMaterial());
        }
    }

    private static void createSampleData() {



        Hoodie h1 = new Hoodie(
                "Shadow Hoodie", "Nightwear", 59.99, 20,
                List.of("Cotton", "Polyester"), List.of("Black"),
                ClothingSize.L, true
        );

        Hoodie h2 = new Hoodie(
                "Urban Hoodie", "StreetStyle", 49.99, 35,
                List.of("Cotton"), List.of("Grey"),
                ClothingSize.M, false
        );

        Hoodie h3 = new Hoodie(
                "Fleece Hoodie", "WarmCo", 69.99, 15,
                List.of("Fleece"), List.of("Red"),
                ClothingSize.XL, false
        );

        Shirt s1 = new Shirt(
                "Oxford Shirt", "ClassicWear", 41, 50,
                List.of("Cotton"), List.of("White"),
                ClothingSize.M, SleeveLength.LONG, Fit.SLIM
        );

        Shirt s2 = new Shirt(
                "Casual Flannel", "Timberline", 45.99, 25,
                List.of("Flannel"), List.of("Green", "Black"),
                ClothingSize.L, SleeveLength.LONG, Fit.REGULAR
        );

        Shirt s3 = new Shirt(
                "Summer Polo", "Sunshine", 41, 40,
                List.of("Cotton"), List.of("Blue"),
                ClothingSize.S, SleeveLength.SHORT, Fit.REGULAR
        );

        Trousers t1 = new Trousers(
                "Slim Fit Jeans", "DenimPro", 59.99, 30,
                List.of("Denim"), List.of("Blue"),
                ClothingSize.M, 32, 34
        );

        Trousers t2 = new Trousers(
                "Chino Pants", "UrbanBasics", 49.99, 20,
                List.of("Cotton"), List.of("Beige"),
                ClothingSize.L, 34, 32
        );

        Trousers t3 = new Trousers(
                "Jogger Pants", "ComfortWear", 41, 25,
                List.of("Polyester"), List.of("Black"),
                ClothingSize.M, 32, 30
        );

        Boot b1 = new Boot(
                "Mountain Boot", "TrailMaster", 89.99, 12,
                List.of("Leather"), List.of("Brown"), true
        );

        Boot b2 = new Boot(
                "Work Boot", "HeavyDuty", 89, 18,
                List.of("Synthetic"), List.of("Black"), false
        );

        Boot b3 = new Boot(
                "Winter Boot", "SnowGuard", 99.99, 10,
                List.of("Leather", "Fur"), List.of("Grey"), true
        );

        HeeledShoe hs1 = new HeeledShoe(
                "Elegant Heels", "LuxeFashion", 89, 20,
                List.of("Leather"), List.of("Red"), 8.0
        );

        HeeledShoe hs2 = new HeeledShoe(
                "Party Heels", "GlamStyle", 89, 10,
                List.of("Synthetic"), List.of("Gold"), 10.5
        );

        HeeledShoe hs3 = new HeeledShoe(
                "Office Heels", "WorkWear", 89, 25,
                List.of("Leather"), List.of("Black"), 6.0
        );

        MembershipCard mc1 = new MembershipCard(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2024, 1, 1)
        );

        MembershipCard mc2 = new MembershipCard(
                LocalDate.of(2022, 6, 15),
                LocalDate.of(2023, 6, 15)
        );

        MembershipCard mc3 = new MembershipCard(
                LocalDate.of(2023, 3, 10),
                LocalDate.of(2024, 3, 10)
        );

        Contract ct1 = new Contract(
                ContractType.SPECIFIC_WORK,
                LocalDate.of(2022, 1, 5),
                LocalDate.of(2024, 1, 5)
        );

        Contract ct2 = new Contract(
                ContractType.EMPLOYMENT,
                LocalDate.of(2023, 2, 10),
                LocalDate.of(2024, 2, 10)
        );

        Contract ct3 = new Contract(
                ContractType.COMMISSION,
                LocalDate.of(2023, 7, 1),
                LocalDate.of(2023, 12, 31)
        );

        DebitCard dc1 = new DebitCard("1234123412341234", LocalDate.of(2026, 5, 1), "123");
        DebitCard dc2 = new DebitCard("9999888877776666", LocalDate.of(2025, 8, 1), "456");
        DebitCard dc3 = new DebitCard("5555444433332222", LocalDate.of(2027, 1, 1), "789");

        Employee e1 = new Employee(
                "John", List.of("Green Street 12"), "Doe", "john@example.com",
                LocalDate.of(1990, 5, 12), 3500, 150, ct1, List.of()
        );

        Employee e2 = new Employee(
                "Sarah", List.of("Blue Avenue 7"), "Smith", "sarah@example.com",
                LocalDate.of(1985, 8, 22), 4200, 300, ct2, List.of(e1)
        );

        Employee e3 = new Employee(
                "Paul", List.of("River Road 3"), "Brown", "paul@example.com",
                LocalDate.of(1995, 12, 3), 3200, 90, ct3, List.of()
        );

        Customer cu1 = new Customer(
                "Alice", List.of("Hill Street 99"), "Green", "alice@mail.com",
                LocalDate.of(1998, 4, 10), "alice123",
                LocalDate.of(2021, 3, 14), 500.0, dc1, mc1, new Basic()
        );

        Customer cu2 = new Customer(
                "Bob", List.of("Ocean Avenue 45"), "Johnson", "bob@mail.com",
                LocalDate.of(1992, 9, 5), "bobbyJ",
                LocalDate.of(2020, 1, 1), 1200.0, dc2, mc2, new Premium()
        );

        Customer cu3 = new Customer(
                "Catherine", List.of("Forest Path 11"), "White", "cat@mail.com",
                LocalDate.of(2000, 2, 20), "catty",
                LocalDate.of(2022, 6, 1), 300.0, dc3, mc3, new Basic()
        );

        Order o1 = new Order(
                List.of(), cu1, 100.0,
                DeliveryType.HOME_DELIVERY, 48,
                LocalDate.now(), 100.0, 0.0, OrderStatus.PROCESSING
        );

        Order o2 = new Order(
                List.of(), cu2, 250.0,
                DeliveryType.STORE_PICKUP, 24,
                LocalDate.now(), 250.0, 10.0, OrderStatus.COMPLETED
        );

        Order o3 = new Order(
                List.of(), cu3, 75.0,
                DeliveryType.HOME_DELIVERY, 72,
                LocalDate.now(), 75.0, 5.0, OrderStatus.PROCESSING
        );
    }

    private static void printExtentSizes() {
        System.out.println("Hoodies:        " + Hoodie.getExtent().size());
        System.out.println("Shirts:         " + Shirt.getExtent().size());
        System.out.println("Trousers:       " + Trousers.getExtent().size());
        System.out.println("Boots:          " + Boot.getExtent().size());
        System.out.println("HeeledShoes:    " + HeeledShoe.getExtent().size());
        System.out.println("MembershipCard: " + MembershipCard.getExtent().size());
        System.out.println("Contracts:      " + Contract.getExtent().size());
        System.out.println("DebitCards:     " + DebitCard.getExtent().size());
        System.out.println("Employees:      " + Employee.getExtent().size());
        System.out.println("Customers:      " + Customer.getExtent().size());
        System.out.println("Orders:         " + Order.getExtent().size());
        System.out.println();
    }
}
