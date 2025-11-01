package ordersystem;


import javax.json.*;
import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;


/**
 * This class will serve as the importer for our Json files. It can take in
 * multiple files and then turn each Json order into an object of the same name
 * Using the JFile chooser from Java swing makes this pretty easy
 * I have encapsulated the selection of the files in a private method to make
 * it usable for the user but not accessible
 */


public class JsonParser {
    private ArrayList<Order> ordersToReturn = new ArrayList<>();

    public List<Order> jsonParsing(File filepath) {

            // Filter for only files ending in JSON.
            FilenameFilter filter = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".json");
                }
            };

            // Array of files containing only the JSON files based on the filter.
            File[] jsonFiles = filepath.listFiles(filter);

            if (jsonFiles == null) return ordersToReturn; //if empty files, return empty arraylist
            else {

                for (File jsonFile : jsonFiles) {
                    try {
                        JsonReader Jreader = Json.createReader(new FileReader(jsonFile));

                        System.out.println("Loaded JSON data from files: " + jsonFile.getName());
                        // Get JSON "order" object

                        JsonObject readObject = Jreader.readObject();

                        //Professor said for this project this was okay,
                        // Each file will be one "order"
                        JsonObject orderObject = readObject.getJsonObject("order");

                        // Get Order Type
                        String orderType = orderObject.getString("type");

                        // Get JSONArray of items
                        JsonArray jsonItems = orderObject.getJsonArray("items");
                        // Create empty List of Items
                        List<Item> orderItems = new ArrayList<>();

                        // Add each JSON item to orderItems
                        for (int i = 0; i < jsonItems.size(); i++) {
                            JsonObject jsonItem = jsonItems.getJsonObject(i);
                            Item newItem = new Item(
                                    jsonItem.getString("name"),
                                    (float)jsonItem.getJsonNumber("price").doubleValue(),
                                    jsonItem.getInt("quantity"));
                            orderItems.add(newItem);
                        }

                        Jreader.close();
                        ordersToReturn.add(new Order(orderType,  orderItems, Order.orderStatus.INCOMING));
                    } catch (Exception e) {
                        System.err.println("Cannot import order due to an error in the data/format of the JSON file: " + jsonFile.getName());
                    }
                }

                // Return filled ArrayList of Orders
                return ordersToReturn;

            }


        }

        //Private file Finder and validator to ensure user does not
        //Have access to this logic

    /*
    private static File[] fileFinder() {

        JFrame ourFrame = new JFrame();

        ourFrame.setAlwaysOnTop(true);
        ourFrame.setLocationRelativeTo(null);
        ourFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



        JFileChooser myChooser = new JFileChooser();

        myChooser.setMultiSelectionEnabled(true);

        // Filter variable for myChooser for JSON files
        //Makes it easy to handle only JSON files
        myChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("JSON Files", "json"));


        int result = myChooser.showOpenDialog(ourFrame);


        if (result == JFileChooser.APPROVE_OPTION) {

            // Replace with
            File[] myFileArray = myChooser.getSelectedFiles();

            for (File singleFile : myFileArray) {

                if (!singleFile.getName().toLowerCase().endsWith("json")) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid file: " + singleFile.getName(), "Error" +
                                    "\nPlease select a JSON file",
                            JOptionPane.ERROR_MESSAGE);
                    //Looping user to select proper file

                    return fileFinder();

                }
            }
            ourFrame.dispose();
            return myFileArray;
        }

        return null;
    }
    */





}



