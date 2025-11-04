package ordersystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.codehaus.jackson.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class Saver {
    private static File saveFile;
    private static final ObjectMapper mapper = new ObjectMapper();

    private static final String saveDirName = "save";
    private static final String saveFileName = "save_file.json";

    public static void setup() throws NullPointerException {
        System.out.println("Setting up Saver.");
        // Get reference to save directory
        File saveDir = new File(saveDirName);

        // If save directory does not exist or has no files:
        if (!saveDir.isDirectory() || saveDir.list().length <= 0) {
            try {
                saveDir.mkdir(); // make the save directory
                saveFile = new File(saveDir, saveFileName); // create the reference to the file
                if (saveFile.createNewFile()) { // If the file creation was a success, pass a blank order manager
                    System.out.println("File created: " + saveFile.getName());
                    saveOrderManager(new OrderManager()); // save a new OrderManager object instance
                }
            } catch (IOException e) {
                System.err.println("IOException during Saver.setup: " + e.getMessage());
            }
        } else {
            // If directory and file already exist, load the first file in the save directory
            System.out.println("Directory already exists: " + saveDir.list()[0]);
            saveFile = saveDir.listFiles()[0];
            saveOrderManager( loadOrderManager() ); // Save OrderManager that is parsed from JSON save file
        }
    }

    public static void saveOrderManager(OrderManager orderManager) {
        try {
            System.out.println("Saving order manager to save file.");
            // Turn OrderManager to JSON
            String jsonStr = mapper.writeValueAsString(orderManager);

            FileWriter fw = new FileWriter(saveFile);
            fw.write(jsonStr);
            fw.close();
        }
        // Catch block to handle exceptions
        catch (IOException e) {
            System.err.println("IOException during Saver.saveOrderManager: " + e.getMessage());
        }
    }

    public static OrderManager loadOrderManager() {
        //mapper.disable(MapperFeature.REQUIRE_HANDLERS_FOR_JAVA8_TIMES);
        try {
            System.out.println("Loading order manager from save file.");
            mapper.registerModule(new JavaTimeModule());
            // create OrderManager object from JSON file
            OrderManager o = mapper.readValue(Files.readString(saveFile.toPath()), OrderManager.class);
            return o;
        } catch (IOException e) {
            System.err.println("IOException during Saver.loadOrderManager: " + e.getMessage());
            // If JSON file cannot be parsed, return new OrderManager object
            return new OrderManager();
        }
    }

    public static File getSaveFile() {
        return saveFile;
    }

    public static String getSaveDirectory() {
        return saveDirName;
    }
}