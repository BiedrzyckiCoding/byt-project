package test.Utils;

import main.Editions.LimitedEdition;
import main.Enums.ClothingSize;
import main.Footwear.Footwear;
import main.PersistenceModels.Boot;
import main.PersistenceModels.DebitCard;
import main.PersistenceModels.Hoodie;
import main.PersistenceModels.PersistenceUtil;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersistenceUtilTest {

    private static final String FILE_PATH = "data.ser";
    private final File dataFile = new File(FILE_PATH);

    @AfterEach
    void tearDown() {
        clearMemory();
    }

    @Test
    @Order(0)
    void saveAll_shouldCreateFile() {
        PersistenceUtil.saveAll();
        assertTrue(dataFile.exists(), "File has to be created");
    }

    @Test
    @Order(1)
    void saveAll_shouldActuallySaveObject(){
        new Hoodie("Test", "Brand", 41, 1, List.of("Test1"), List.of("Test1"), ClothingSize.S, true);
        PersistenceUtil.saveAll();

        List<Hoodie> loadedHoodieList = Hoodie.getExtent();
        System.out.println("Hoodie list size: " + loadedHoodieList.size());
        assertTrue(dataFile.length() > 0, "File is not empty => object is saved successfully");
    }

    @Test
    @Order(2)
    void loadAll_shouldSaveAndLoadObject() {
        new Boot("Testing", "Test", 61.0,5,List.of("test1"), List.of("test2"), true,37.0);

        PersistenceUtil.saveAll();
        PersistenceUtil.loadAll();

        List<Boot> loadedList = Boot.getExtent();
        assertEquals(1, loadedList.size(), "Object has to be loaded");

    }

    @Test
    @Order(3)
    void saveObject_withNonSerializableField() {

        new Hoodie("Poison", "Test", 100, 0, List.of("em1"), List.of("em1"), ClothingSize.S, true) {

            private final Thread nonSerializableField = new Thread();
        };

        PersistenceUtil.saveAll();

        // catching error
//        assertNotNull(PersistenceUtil.lastError);
        assertInstanceOf(IOException.class, PersistenceUtil.lastError);
        System.out.println("Error was caught: " + PersistenceUtil.lastError.getMessage());

    }

    private void clearMemory(){
        try{
            Files.deleteIfExists(dataFile.toPath());
            PersistenceUtil.loadAll();
        } catch (IOException e){
            System.err.println("Error while clearing in test: " + e.getMessage());
        }
    }

}
