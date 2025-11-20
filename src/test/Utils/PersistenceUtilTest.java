package test.Utils;

import main.Clothing.Hoodie;
import main.Clothing.Shirt;
import main.Clothing.Trousers;
import main.Enums.ClothingSize;
import main.Enums.Fit;
import main.Enums.SleeveLength;
import main.Footwear.Boot;
import main.Footwear.HeeledShoe;
import main.MembershipTiers.MembershipCard;
import main.Order.Order;
import main.Person.Contract;
import main.Person.Customer;
import main.Person.DebitCard;
import main.Person.Employee;

import main.Utils.PersistenceUtil;
import org.junit.jupiter.api.*;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class PersistenceUtilTest {

    private static final String FILE = "data.ser";

    private List<String> materials;
    private List<String> colors;

    @BeforeEach
    @AfterEach
    void cleanupFile() {
        File f = new File(FILE);
        if (f.exists()) f.delete();

        materials = List.of("Cotton");
        colors = List.of("Black");
    }

    //helper to set static extents through reflection
    private void setExtent(Class<?> clazz, List<?> data) {
        try {
            Field extentField = clazz.getDeclaredField("extent");
            extentField.setAccessible(true);
            extentField.set(null, data);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set extent for " + clazz.getSimpleName(), e);
        }
    }

    //helper to get static extent through reflection
    private List<?> getExtent(Class<?> clazz) {
        try {
            Field extentField = clazz.getDeclaredField("extent");
            extentField.setAccessible(true);
            return (List<?>) extentField.get(null);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get extent for " + clazz.getSimpleName(), e);
        }
    }

    @Test
    void testSaveAllCreatesFile() {
        //fill some extent data so AppState is non-empty
        setExtent(Hoodie.class, new ArrayList<>(List.of(new Hoodie(
                "Classic Hoodie", "Adidas", 60.0, 5,
                materials, colors, ClothingSize.L, true
        ))));
        setExtent(Shirt.class, new ArrayList<>(List.of(new Shirt(
                "Oxford Shirt", "Ralph Lauren", 120.0, 5,
                materials, colors, ClothingSize.L,
                SleeveLength.LONG, Fit.REGULAR
        ))));

        PersistenceUtil.saveAll();

        File f = new File(FILE);
        assertTrue(f.exists(), "saveAll() should create a data.ser file");
        assertTrue(f.length() > 0, "Serialized file should not be empty");
    }

    @Test
    void testLoadAllSuccess() {
        //before saving - extents have some data
        List<Hoodie> originalHoodies = new ArrayList<>(List.of(new Hoodie(
                "Classic Hoodie", "Adidas", 60.0, 5,
                materials, colors, ClothingSize.L, true
        )));
        setExtent(Hoodie.class, originalHoodies);

        PersistenceUtil.saveAll();

        //now clear extents to see if load restores them
        setExtent(Hoodie.class, new ArrayList<>());

        PersistenceUtil.loadAll();

        assertEquals(originalHoodies.size(), getExtent(Hoodie.class).size(),
                "applyToStatics() should restore hoodie extent");
    }

    @Test
    void testLoadAllWhenFileMissingResetsExtents() {
        //non-empty extents
        setExtent(Hoodie.class, List.of("X"));
        setExtent(Shirt.class, List.of("Y"));

        assertFalse(getExtent(Hoodie.class).isEmpty());
        assertFalse(getExtent(Shirt.class).isEmpty());

        //since no file exists, loadAll() should reset
        PersistenceUtil.loadAll();

        assertEquals(List.of(), getExtent(Hoodie.class));
        assertEquals(List.of(), getExtent(Shirt.class));
    }

    @Test
    void testLoadAllWithCorruptFileResetsExtents() throws Exception {
        //write corrupt file
        try (FileWriter w = new FileWriter(FILE)) {
            w.write("not serialized");
        }

        setExtent(Trousers.class, List.of("T"));

        PersistenceUtil.loadAll();

        assertEquals(List.of(), getExtent(Trousers.class));
    }

    @Test
    void testLoadAllClassCastExceptionResetsExtents() throws Exception {
        //write valid serialized object but wrong type
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject("WrongObjectType");
        }

        setExtent(Boot.class, List.of("B"));

        PersistenceUtil.loadAll();

        assertEquals(List.of(), getExtent(Boot.class));
    }
}
