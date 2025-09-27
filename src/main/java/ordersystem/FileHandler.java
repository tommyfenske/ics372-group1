package ordersystem;


import javax.json.*;
import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


/**
 * This class will serve as the importer for our Json files. It can take in
 * multiple files and then turn each Json order into an object of the same name
 * Using the JFile chooser from Java swing makes this pretty easy
 * I have encapsulated the selection of the files in a private method to make
 * it usable for the user but not accessible
 */


public class FileHandler {

    /**
     * Creating the logic that takes in the JSON files and turns them into
     * Order type objects, extracting all the necessary things
     * I used a private method to ensure that the logic dor the user is
     * usable but not accessible
     *
     * @return One or more Objects of Order type
     */

    public List<Order> jsonParsing() {
            //Array of files, if one file, only one element if many, many elements
            //Calling our private method
            File[] filesToParse = fileFinder();

            // Create ArrayList of orders to return
            ArrayList<Order> ordersToReturn = new ArrayList<>();

            if (filesToParse == null) return ordersToReturn; //if empty files, return empty arraylist
            else {

                for (File singleFile : filesToParse) {
                    try {
                        JsonReader Jreader =
                                Json.createReader(new FileReader(singleFile));

                        System.out.println("Loaded JSON data from files: " + singleFile.getName());
                        // Get JSON "order" object

                        JsonObject readObject = Jreader.readObject();

                        // TODO: currently this only supports one "order" object per file
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

                        // Fill Out New Order Object
                        Order newOrder = new Order(orderType,  orderItems);
                        ordersToReturn.add(newOrder);

                    } catch (Exception e) {
                        System.out.println("Failed to read file " + singleFile.getName() + ", " +e);
                    }
                }
                // Return filled ArrayList of Orders
                return ordersToReturn;
            }


        }

        //Private file Finder and validator to ensure user does not
        //Have access to this logic

    private static File[] fileFinder() {


        JFileChooser myChooser = new JFileChooser();

        myChooser.setMultiSelectionEnabled(true);

        // Filter variable for myChooser for JSON files
        //Makes it easy to handle only JSON files
        myChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("JSON Files", "json"));

        int result = myChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {

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
            return myFileArray;
        }

        return null;
    }
}



