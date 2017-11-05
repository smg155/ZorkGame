import java.util.*;

public class Library implements Room
{
    private static HashMap<String, Room> directionsAndRooms = null;
    private boolean done = false;
    private static int count = 0;
    private Scanner kb = new Scanner(System.in);
    private static Library instance = null;

    private Library() {
        if (directionsAndRooms == null) {
            directionsAndRooms = new HashMap<String, Room>();
            directionsAndRooms.put("down", Gardens.getInstance());
            directionsAndRooms.put("slide", Gardens.getInstance());
            directionsAndRooms.put("back", NothingRoom.getInstance());
        }
        if (count == 0) {
            count = 1;
        }
    }

    public static Room getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public String getRoomDescription(MainCharacter hero)
    {
        if (count == 1) {
            return "\nYou walk into a library filled with thousands\nof volumes of books from every discipline\nimaginable. Mahogany bookshelves hold these relics.\nIn the corner of the room there is a wooden slide that\ngoes down to another room. On a coffee table there is a\nsparkly drink.\n";
        } 
        return "\nYou walk into a library filled with thousands\nof volumes of books from every discipline\nimaginable. Mahogany bookshelves hold these relics.\nIn the corner of the room there is a wooden slide that\ngoes down to another room.\n";
    }

    public boolean isAValidDirection(String[] directions)
    {
        for (String ans : directions) {
            if (ans.equals("down") || ans.equals("slide") || ans.equals("back")) {
                return true;
            }
        }
        return false;
    }

    public Room getNextRoom(String[] direction)
    {
        String dir = "";
        for (String ans : direction) {
            if (ans.equals("down") || ans.equals("slide") || ans.equals("back")) {
                dir = ans;
            }
        }
        return directionsAndRooms.get(dir);
    }

    public boolean isItemWord(String[] words, MainCharacter hero)
    {
        for (String ans : words) {
            if (ans.equals("books") || ans.equals("bookshelves") || ans.equals("shelves")) {
                return true;
            }
        }
        return false;
    }

    public boolean isMonsterWord(String[] words, MainCharacter hero)
    {
        if (count > 1) {
            return false;
        }
        for (String ans : words) {
            if (ans.equals("sparkly") || ans.equals("drink") || ans.equals("table")) {
                return true;
            }
        }
        return false;
    }

    public void takeItemAction(String[] words, MainCharacter hero)
    {
        System.out.println("\nYou approach the bookshelves. A few books catch your eye:\n\n1. The Almanac of Monsters\n2. The Mystery of the Garden\n3. The Final Zork\n4. Guide to the Kanto\n");

        String input = kb.nextLine();
        words = input.split(" ");

        while (input.indexOf("leave") < 0 && input.indexOf("stop") < 0) {
            for (String ans : words) {
                if (ans.equals("1")) {
                    System.out.println("\n\"...a gru has 25 health and drops a horn\nwhen defeated in battle. An angler...\"\n\nThat's quite the boring book.\n");
                } else if (ans.equals("2")) {
                    System.out.println("\n\"...the garden has a wonderful secret for those\nwho can complete its puzzle. A clue to its\ncompletion starts in the room with nothing\nin it.\"\n\nHm. Interesting.\n");
                } else if (ans.equals("3")) {
                    System.out.println("\n\"...the final room holds a great challenge to even\nthe most valiant warriors. At least two\npoti...\"\n\nThe rest of the words are burned off.\n");
                } else if (ans.equals("4")) {
                    System.out.println("\n\"...Oak will give you one of three starter pokemon\nand you will be on your journey to be the very best...\"\n\nHow did that book get there?\n");
                }
            }
            if (input.indexOf("1") < 0 && input.indexOf("2") < 0 && input.indexOf("3") < 0 && input.indexOf("4") < 0) {
                System.out.println("\nYou should check out those four books.\n");
            } else {
                System.out.println("These books are pretty cool.\n\n1. The Almanac of Monsters\n2. The Mystery of the Garden\n3. The Final Zork\n4. Guide to the Kanto\n\nYou can leave if you don't want to read anymore.\n");
            }
            input = kb.nextLine();
            words = input.split(" ");
        }
        System.out.println("\nYou left the bookshelves.\n");
    }

    public void takeMonsterAction(String[] words, MainCharacter hero)
    {
        System.out.println("\nYou walk over to the coffee table. The drink is a potion!\nYou take it and put it in your satchel.\n");
        count++;
    }
}