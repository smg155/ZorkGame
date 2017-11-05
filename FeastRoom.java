import java.util.*;

public class FeastRoom implements Room
{
    private static HashMap<String, Room> directionsAndRooms = null;
    private Scanner kb = new Scanner(System.in);
    private static int count = 0;
    private static FeastRoom instance = null;

    private FeastRoom() {
        if (directionsAndRooms == null) {
            directionsAndRooms = new HashMap<String, Room>();
            directionsAndRooms.put("right", ColiseumRoom.getInstance());
            directionsAndRooms.put("back", FireChamber.getInstance());
        }
        if (count == 0) {
            count = 1;
        }
    }

    public static Room getInstance() {
        if (instance == null) {
            instance = new FeastRoom();
        }
        return instance;
    }

    public String getRoomDescription(MainCharacter hero) {
        if (count > 1) {
            return "\nThe feast still has lots of food, but since\nyou took some you lost your appetite.\nThere's a door to the right of you.\n";
        } else {
            return "\nIn front of you lies a beautiful\nfeast. The long table is filled with pot roast,\npig, salads, fruits, pastas, and pastries.\nWhat does one do with this amount of food?\nI suppose they take it.\n";
        }
    }

    public boolean isAValidDirection(String[] directions) {   
        String direction = "";
        for (String ans : directions) {
            if (directionsAndRooms.containsKey(ans)) {
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

    public boolean isItemWord(String[] words, MainCharacter hero) {
        if (count > 1) {
            return false;
        } else {
            for (String ans : words) {
                if (ans.equals("food")) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isMonsterWord(String[] words, MainCharacter hero) {
        return false;
    }

    public void takeItemAction(String[] words, MainCharacter hero) {
        int messageCount = 0;

        System.out.println("\nThere's a lot of food on the table.\nBut two things catch your eye:\na sparkly drink and loaf of bread.\n");
        String input = kb.nextLine();
        words = input.split(" ");

        while (input.indexOf("bread") < 0 && input.indexOf("drink") < 0 && input.indexOf("loaf") < 0) {
            if (input.indexOf("both") > 0) {
                System.out.println("\nYou shouldn't take both. That's disrespectful.\n");
            } else if (messageCount == 0) {
                System.out.println("\nWhat are you trying to say?\n");
                messageCount++;
            } else if (messageCount == 1) {
                System.out.println("\nYou can't do that...\n");
                messageCount++;
            } else{
                System.out.println("\n...please just stop.\n");
            }
            input = kb.nextLine();
            words = input.split(" ");
        }
        for (String ans : words) {
            if (ans.equals("bread") || ans.equals("loaf")) {
                hero.addItem(new Item("bread", 0));
                System.out.println("\nYou took a loaf of bread! It restores 10 health points when you use it.\n");
                count++;
            } else if (ans.equals("drink")) {
                hero.addItem(new Item("potion", 0));
                System.out.println("\nYou took a potion! It restores 50 health points when you use it.\n");
                count++;
            }
        }
    }

    public void takeMonsterAction(String[] words, MainCharacter hero) {
    }
}