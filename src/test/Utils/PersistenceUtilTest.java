package test.Utils;

import main.Enums.ClothingSize;
import main.PersistenceModels.Boot;
import main.PersistenceModels.Hoodie;
import main.PersistenceModels.PersistenceUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersistenceUtilTest {

    private static final String FILE_PATH = "data.ser";
    private final File dataFile = new File(FILE_PATH);

    @BeforeEach
    void setup(){
        clearMemory();
    }

    @AfterEach
    void tearDown() throws Exception{
        Files.deleteIfExists(dataFile.toPath());
        clearMemory();
    }

    @Test
    void saveAll_shouldCreateFile() {
        PersistenceUtil.saveAll();
        assertTrue(dataFile.exists(), "File has to be created");
    }

    @Test
    void saveAll_shouldActuallySaveObject(){
        new Hoodie("Test", "Brand", 41, 1, List.of("Test1"), List.of("Test1"), ClothingSize.S, true);
        PersistenceUtil.saveAll();

        assertTrue(dataFile.length() > 0, "File is not empty => object is saved successfully");
    }

    @Test
    void loadAll_shouldRestoreSavedObjects() throws Exception {
        new Boot("Testing", "Test", 61.0,5,List.of("test1"), List.of("test2"), true,37.0);
        PersistenceUtil.saveAll();

        File backupFile = new File("data.bak");
        Files.move(dataFile.toPath(), backupFile.toPath());
        PersistenceUtil.loadAll();
        Files.move(backupFile.toPath(), dataFile.toPath());

        PersistenceUtil.loadAll();
        List<Boot> loadedList = Boot.getExtent();
        assertEquals(1, loadedList.size(), "Object has to be loaded");

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