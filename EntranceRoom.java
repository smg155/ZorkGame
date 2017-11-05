import java.util.*;

public class EntranceRoom implements Room
{
    private static HashMap<String, Room> directionsAndRooms = null;
    private static EntranceRoom instance = null;

    private EntranceRoom()
    {
        if (directionsAndRooms == null) {
            directionsAndRooms = new HashMap<String, Room>();
            directionsAndRooms.put("right", IceChamber.getInstance());
            directionsAndRooms.put("left", FireChamber.getInstance());
            directionsAndRooms.put("straight", FirstHallway.getInstance());
        }
    }

    public static Room getInstance() {
        if (instance == null) {
            instance = new EntranceRoom();
        }
        return instance;
    }

    public String getRoomDescription(MainCharacter hero) {
        if (hero.hasItem("knife")) {
            return "\nYou find yourself in a dark room.\nQuite mysterious. There is a candle\nsitting on a table. There is a door to the\nleft, right, and in front of you.\n";
        } else {
            return "\nYou find yourself in a dark room.\nQuite mysterious. There is a candle\nsitting on a table. There is\na shiny object in the corner of the room.\nThere is a door to the\nleft, right, and in front of you.\n";
        }
    }

    public boolean isAValidDirection(String[] directions) {
        String direction = "";
        for (String ans : directions) {
            if (ans.equals("left") || ans.equals("right") || ans.equals("straight")) {
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
        for (String ans : words) {
            if (ans.equals("shiny") || ans.equals("object") || ans.equals("candle")) {
                return true;
            }
        }
        return false;
    }

    public void takeItemAction(String[] words, MainCharacter hero) {
        String key = "";
        for (String ans : words) {
            if (ans.equals("shiny") || ans.equals("object") || ans.equals("candle")) {
                key = ans;
            }
        }
        if (key.equals("shiny") || key.equals("object")) {
            if (hero.hasItem("knife")) {
                System.out.println("\nYou already took the knife.\n");
            } else {
                hero.addItem(new Item("knife", 2));
                System.out.println("\nYou found a nasty old knife! I'm sure it'll\ncome in handy. You added the knife to your bag!\n");
            }
        } else if (key.equals("candle")) {
            System.out.println("\nWhy do you want to see a candle? That's weird.\n");
        }
    }	

    public void takeMonsterAction(String[] words, MainCharacter hero) {
    }
}