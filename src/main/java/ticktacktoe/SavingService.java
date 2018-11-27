package ticktacktoe;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SavingService {

    private static final String GAME_STATE_PATH = "SavedGames.txt";
    Map<String, String> results = new HashMap<>();

    public void saveMap() {
        try {
            System.out.println("Saving game");
            File file = new File(GAME_STATE_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(results);
            oos.close();
        } catch (Exception e) {
            System.out.println("Exception occurred " + e.getMessage());
        }
        System.out.println("Game saved");
    }

    public void loadMap() {
        try {
            File file = new File(GAME_STATE_PATH);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            Object readMap = ois.readObject();
            if (readMap instanceof HashMap) {
                results.putAll((HashMap) readMap);
            }
            ois.close();
            System.out.println("Loaded results " + results);
        } catch (Exception e) {

        }
    }



}
