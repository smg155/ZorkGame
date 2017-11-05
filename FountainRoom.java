import java.util.*;
import java.lang.*;

public class FountainRoom implements Room
{
    private static HashMap<String, Room> directionsAndRooms = null;
    private static int count = 0;
    private Scanner kb = new Scanner(System.in);
    private static FountainRoom instance = null;

    private FountainRoom() {
        if (directionsAndRooms == null){
            directionsAndRooms = new HashMap<String, Room>();
            directionsAndRooms.put("left", ColiseumRoom.getInstance());
            directionsAndRooms.put("back", IceChamber.getInstance());
        }
        if (count == 0) {
            count = 1;
        }
    }

    public static Room getInstance() {
        if (instance == null) {
            instance = new FountainRoom();
        }
        return instance;
    }

    public String getRoomDescription(MainCharacter hero) {
        if (count > 1) {
            return "\nA magnificent, four-tier fountain is stationed in the center of the room.\nWater flows calmly from it. The walls, floor, and ceiling are\nclear glass, but nothing seems to be behind them. There\nis a door to your left.\n";
        } else {
            return "\nYou walk into a small room, and the doors close behind you.\nA magnificent, four-tier fountain is stationed in\nthe center of the room. No water flows from it.\nThe walls, floor, and ceiling are clear glass,\nbut nothing seems to be behind them.\nUnfortunateley, the door to your left is locked,\nbut the room seems to be without a key.\nThe fountain has four levers. Maybe they do something.\n"; 
        }
    }

    public boolean isAValidDirection(String[] directions) {
        if (count == 1) {
            return false;
        }
        for (String ans : directions) {
            if (ans.equals("left") || ans.equals("back")) {
                return true;
            }
        }
        return false;
    }

    public Room getNextRoom(String[] direction) {
        String dir = "";
        for (String ans : direction) {
            if (ans.equals("left") || ans.equals("back")) {
                dir = ans;
            }
        }
        return directionsAndRooms.get(dir);
    }

    public boolean isItemWord(String[] words, MainCharacter hero) {
        for (String ans : words) {
            if (ans.equals("levers") || ans.equals("lever")) {
                return true;
            }
        }
        return false;
    }

    public boolean isMonsterWord(String[] words, MainCharacter hero) {
        return false;
    }

    public void takeItemAction(String[] words, MainCharacter hero) {
        boolean solved = true, done = false;
        boolean lev1Pulled = false, lev2Pulled = false, lev3Pulled = false, lev4Pulled = false;
        String foo = "";

        System.out.println("\nYou approach the levers by the fountain.\nYou notice they each have a trident insignia on them.\nMaybe you can pull one...\n");
        String input = kb.nextLine();
        words = input.split(" ");

        while (input.indexOf("lever") < 0) {
            System.out.println("\nMaybe you should try pulling one.\nThey're labeled \"lever 1\", \"lever 2\", \"lever 3\", and \"lever 4\".\n");
            input = kb.nextLine();
            words = input.split(" ");
        }
        while (!done) {
            for (String ans : words) {
                if (ans.equals("1") || ans.equals("2") || ans.equals("3") || ans.equals("4")) {
                    foo = ans;
                }
            }
            if (foo.equals("1")) {
                if (!lev2Pulled && !lev3Pulled && !lev4Pulled) {
                    solved = false;
                }
                System.out.println("\nThe 3rd tier on the fountain turned to reveal 4 engraved tridents.\n");
                lev1Pulled = true;
            } else if (foo.equals("2")) {
                if (lev1Pulled || lev3Pulled) {
                    solved = false;
                }
                System.out.println("\nThe bottom tier of the fountain turned to reveal 2 engraved tridents.\n");
                lev2Pulled = true;
            } else if (foo.equals("3")) {
                if (lev1Pulled) {
                    solved = false;
                }
                System.out.println("\nThe top tier of the fountain moved to reveal 3 engraved tridents.\n");
                lev3Pulled = true;
            } else if (foo.equals("4")) {
                if (lev1Pulled || lev2Pulled || lev3Pulled) {
                    solved = false;
                }
                System.out.println("\nThe 2nd tier on the fountain turned to reveal a single engraved trident.\n");
                lev4Pulled = true;
            } else {
                System.out.println("\nYou might want to try again.\n");
            }

            if (lev1Pulled && lev2Pulled && lev3Pulled && lev4Pulled) {
                if (!solved) {
                    System.out.println("\nNothing happened and the fountain reset\nitself. Maybe another order would work?\n");
                    lev1Pulled = false;
                    lev2Pulled = false;
                    lev3Pulled = false;
                    lev4Pulled = false;
                    foo = "";
                    solved = true;
                    input = kb.nextLine();
                    words = input.split(" ");
                } else {
                    done = true;
                } 
            } else {
                foo = "";
                input = kb.nextLine();
                words = input.split(" ");
            }
        }
        System.out.println("\nThe order worked! The fountain begins to\nspout water from its top tier. A glimmering sword appears\nin the water.\n");
        input = kb.nextLine();
        words = input.split(" ");
        while (input.indexOf("sword") < 0) {
            System.out.println("\nYou should check out the sword.\n");
            input = kb.nextLine();
            words = input.split(" ");
        }
        System.out.println("\nYou found a mythical sword: the Wavetip! Its power is incredible. The doors unlock.\n");
        hero.addItem(new Item("wavetip", 6));
        count++;
    }

    public void takeMonsterAction(String[] words, MainCharacter hero) {
    }
}

