package Utils;

import Clothing.Hoodie;
import Clothing.Shirt;
import Clothing.Trousers;
import Footwear.Boot;
import Footwear.HeeledShoe;
import MembershipTiers.MembershipCard;
import Order.Order;
import Person.Contract;
import Person.Customer;
import Person.DebitCard;
import Person.Employee;

import java.io.*;
import java.util.List;

public class PersistenceUtil {

    private static final String FILE = "data.ser";

    public static void saveAll() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(AppState.fromStatics());
        } catch (IOException e) {
            System.err.println("Failed to save data to file: " + FILE);
            System.err.println("Reason: " + e.getMessage());
        }
    }

    public static void loadAll() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
            AppState state = (AppState) in.readObject();
            state.applyToStatics();

            System.out.println("Data loaded successfully from " + FILE);

        } catch (FileNotFoundException e) {
            System.err.println("Data file not found: " + FILE);
            resetAllExtents();

        } catch (ClassCastException e) {
            System.err.println("Data format mismatch – file incompatible with current version!");
            System.err.println("Message: " + e.getMessage());
            resetAllExtents();

        } catch (ClassNotFoundException e) {
            System.err.println("Missing class during deserialization.");
            System.err.println("Class: " + e.getMessage());
            resetAllExtents();

        } catch (IOException e) {
            System.err.println("I/O issue while loading data.");
            System.err.println("Reason: " + e.getMessage());
            resetAllExtents();

        } catch (Exception e) {
            System.err.println("Unexpected error during load.");
            System.err.println("Reason: " + e);
            resetAllExtents();
        }
    }

    private static void resetAllExtents() {
        Hoodie.setExtent(List.of());
        Shirt.setExtent(List.of());
        Trousers.setExtent(List.of());
        Boot.setExtent(List.of());
        HeeledShoe.setExtent(List.of());
        MembershipCard.setExtent(List.of());
        Contract.setExtent(List.of());
        DebitCard.setExtent(List.of());
        Employee.setExtent(List.of());
        Customer.setExtent(List.of());
        Order.setExtent(List.of());
    }
}
