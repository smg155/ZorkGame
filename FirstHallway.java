import java.util.*;

public class FirstHallway implements Room
{
    private static HashMap<String, Room> directionsAndRooms = null;
    private Scanner kb = new Scanner(System.in);
    private int count = 0;
    private static FirstHallway instance = null;

    private FirstHallway() {
        if (directionsAndRooms == null) {
            directionsAndRooms = new HashMap<String, Room>();
            directionsAndRooms.put("straight", ColiseumRoom.getInstance());
            directionsAndRooms.put("back", EntranceRoom.getInstance());
        }
    }

    public static Room getInstance() {
        if (instance == null) {
            instance = new FirstHallway();
        }
        return instance;
    }

    public String getRoomDescription(MainCharacter hero) {
        if (count > 0){
            return "\nYou are in a long, dimly lit corridor.\nThere is a door at the end of the hall.\n";
        } else {
            return "\nYou are in a long, dimly lit corridor.\nThere is a door at the end of the hall. A\nbrick on the side of the wall is sticking out.\n";
        }
    }

    public boolean isAValidDirection(String[] directions) {
        String direction = "";
        for (String ans : directions) {
            if (ans.equals("straight") || ans.equals("back")) {
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
            if (ans.equals("brick")) {
                return true;
            }
        }
        return false;
    }

    public void takeItemAction(String[] words, MainCharacter hero) {
        count++;
        System.out.println("\nYou move the brick out of the wall to\nbetray the presence of a note.\n");
        String input = kb.nextLine();
        String inputSplit[] = input.split(" ");
        while (input.indexOf("read") < 0) {
            System.out.println("\nYou can do no such thing.\n");
            input = kb.nextLine();
            inputSplit = input.split(" ");
        }
        System.out.println("\nYou unroll the note. It says:\n\nDear hero,\n    I have walked these halls before\nand I must warn you of the\ndangers ahead. You will not survive\nthe next room without a good weapon.\nI suggest you find yourself one.\n");
        System.out.println("Type anything when you've finished reading.");
        input = kb.nextLine();
        System.out.println("\nHaving a weapon is probably a good idea.\nCheck your status to see if you have one\nin your inventory before proceeding.\n");
    }

    public void takeMonsterAction(String[] words, MainCharacter hero) {
    }
}