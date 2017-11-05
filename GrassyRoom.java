import java.util.*;

public class GrassyRoom implements Room
{
    private static HashMap<String, Room> directionsAndRooms = null;
    private static GrassyRoom instance = null;

    private GrassyRoom() {
        if (directionsAndRooms == null) {
            directionsAndRooms = new HashMap<String, Room>();
            directionsAndRooms.put("down", EntranceRoom.getInstance());
            directionsAndRooms.put("hole", EntranceRoom.getInstance());
        }
    }

    public static Room getInstance() {
        if (instance == null) {
            instance = new GrassyRoom();
        }
        return instance;
    }

    public String getRoomDescription(MainCharacter hero) {
        return "\nYou wake up on top of a\ngrassy knoll in the middle of\nan enchanted forest. All around you\nthe trees sparkle with light.\nIn front of you is a dark hole\nthat leads into what seems to be\na deep cavern.\n";
    }

    public boolean isAValidDirection(String[] directions) {
        String direction = "";
        for (String ans : directions) {
            if (ans.equals("down") || ans.equals("hole")) {
                direction = ans;
            }
        }
        return directionsAndRooms.containsKey(direction);
    }

    public Room getNextRoom(String[] direction) {
        String dir = "";
        for (String ans : direction) {
            if (directionsAndRooms.containsKey(ans)) {
                dir = ans;
            }
        }
        return directionsAndRooms.get(dir);
    }

    public boolean isMonsterWord(String[] words, MainCharacter hero) {
        return false;
    }

    public boolean isItemWord(String[] words, MainCharacter hero) {
        return false;
    }

    public void takeItemAction(String[] words, MainCharacter hero) {
    }

    public void takeMonsterAction(String[] words, MainCharacter hero) {
    }
}
