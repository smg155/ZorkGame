import java.util.*;
import java.lang.*;

public class FireChamber implements Room
{
    private static HashMap<String, Room> directionsAndRooms = null;
    private Scanner kb = new Scanner(System.in);
    private static Monster gru = null;
    private static FireChamber instance = null;

    private FireChamber() {
        if (directionsAndRooms == null) {
            directionsAndRooms = new HashMap<String, Room>();
            directionsAndRooms.put("back", EntranceRoom.getInstance());
            directionsAndRooms.put("right", FeastRoom.getInstance());
        }
        if (gru == null) {
            gru = new Monster("Gru", 25, 1, new Item("horn", 3));
        }
    }

    public static Room getInstance() {
        if (instance == null) {
            instance = new FireChamber();
        }
        return instance;
    }

    public String getRoomDescription(MainCharacter hero) {
        if (gru.getHealth() > 0) {
            return "\nThis room is very hot... The walls\nare glowing with an orange light and magma\nflows through paths in the floor. There's\na door to your right. Watch out! There's a\nmonster there it's coming to attack you!\n";
        } else {
            return "\nThis room is very hot... The walls\nare glowing with an orange light and magma\nflows through paths in the floor. There's\na door to your right.\n";
        }
    }

    public boolean isAValidDirection(String[] directions) {   
        if (gru.getHealth() > 0) {
            return false;
        }
        String direction = "";
        for (String ans : directions) {
            if (ans.equals("right") || ans.equals("back")) {
                direction = ans;
            }
        }
        return directionsAndRooms.containsKey(direction);
    }

    public Room getNextRoom(String[] direction) {
        String dir = "";
        for (String ans : direction) {
            if (ans.equals("right") || ans.equals("back")) {
                dir = ans;
            }
        }
        return directionsAndRooms.get(dir);
    }

    public boolean isItemWord(String[] words, MainCharacter hero) {
        return false;
    }

    public boolean isMonsterWord(String[] words, MainCharacter hero) {
        if (gru.getHealth() <= 0) {
            return false;
        }
        for (String ans : words) {
            if (ans.equals("attack")) {
                return true;
            }
        }
        return false;
    }

    public void takeItemAction(String[] words, MainCharacter hero) {
    }

    public void takeMonsterAction(String[] words, MainCharacter hero) {
        String item = "";
        String input = "";
        while (gru.getHealth() > 0 && hero.getHealth() > 0) {
            for (String ans : words) {
                if (hero.hasItem(ans) && (!ans.equals("potion") || !ans.equals("superpotion"))) {
                    item = ans;
                    hero.attack(gru, hero.getItem(item));
                    System.out.println("You attacked the Gru.");
                } else if (ans.equals("use")) {
                    if (words.length > 1){
                        for (int index = 0; index < words.length; index++) {
                            if (words[index].equals(ans)) {
                                item = words[index + 1];
                                if (item.equals("potion") && hero.hasItem("potion")) {
                                    hero.useItem(item);
                                    hero.removeItem("potion");
                                } else if (item.equals("superpotion") && hero.hasItem("superpotion")) {
                                    hero.useItem(item);
                                    hero.removeItem("superpotion");
                                } else if (item.equals("bread") && hero.hasItem("bread")) {
                                    hero.useItem(item);
                                    hero.removeItem("bread");
                                } else {
                                    System.out.println("\nThat's not going to work.\n");
                                }
                            }
                        }
                    } else {
                        System.out.println("\nUse what?\n");
                    }
                }
            }
            if (gru.getHealth() > 0) {
                gru.attack(hero);
                System.out.println("\nThe Gru attacked you!\n");
            }
            System.out.println(hero.getStatus());
            System.out.println("\nGru HP: " + gru.getHealth() + "\n");
            if (hero.getHealth() > 0) {
                if (gru.getHealth() > 0) {
                    input = kb.nextLine();
                    words = input.split(" ");
                }
            }
        }
        if (gru.getHealth() <= 0) {
            System.out.println("\nYou defeated the Gru!\nThe Gru's horn fell off. You added it to your satchel.\n");
            hero.addItem(gru.getMonsterItem());
        } else {
            System.out.println("You were killed fighting valiantly in battle.\nZork is a tough land that gets even the best\nof us. Thank you for playing.");
            //Fix way to quit game
        }
    }
}