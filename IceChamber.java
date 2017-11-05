import java.util.*;

public class IceChamber implements Room
{
    private static HashMap<String, Room> directionsAndRooms = null;
    private static Monster skeleton = null;
    private Scanner kb = new Scanner(System.in);
    private static IceChamber instance = null;

    private IceChamber() {
        if (directionsAndRooms == null) {
            directionsAndRooms = new HashMap<String, Room>();
            directionsAndRooms.put("back", EntranceRoom.getInstance());
            directionsAndRooms.put("left", FountainRoom.getInstance());
        }
        if (skeleton == null) {
            skeleton = new Monster("Skeleton", 30, 1, new Item("scimitar", 4));
        }
    }

    public static Room getInstance() {
        if (instance == null) {
            instance = new IceChamber();
        }
        return instance;
    }

    public String getRoomDescription(MainCharacter hero) {
        if (skeleton.getHealth() > 0) {
            return "\nThis room is very cold... Your breath\nturns into vapor as it hits the frigid air.\nThe walls are covered in ice, and the\nfloor is a little slippery.\nThere is a door to your left and a\nmonster behind an icy rock in the middle\nof the room.\n";
        } else {
            return "\nThis room is very cold... Your breath\nturns into vapor as it hits the frigid air.\nThe walls are covered in ice, and the\nfloor is a little slippery. There is a door to your left.\n";
        }
    }

    public boolean isAValidDirection(String[] directions) {   
        String direction = "";
        for (String ans : directions) {
            if (ans.equals("back") || ans.equals("left")) {
                direction = ans;
            }
        }
        return directionsAndRooms.containsKey(direction);
    }

    public Room getNextRoom(String[] direction) {
        String dir = "";
        for (String ans : direction) {
            if (ans.equals("back") || ans.equals("left")) {
                dir = ans;
            }
        }
        return directionsAndRooms.get(dir);
    }

    public boolean isItemWord(String[] words, MainCharacter hero) {
        return false;
    }

    public boolean isMonsterWord(String[] words, MainCharacter hero) {
        if (skeleton.getHealth() <= 0) {
            return false;
        }
        if (words[0].equals("attack")) {
            return true;
        }
        return false;
    }

    public void takeItemAction(String[] words, MainCharacter hero) {
    }

    public void takeMonsterAction(String[] words, MainCharacter hero) {
        String item = "";
        String input = "";
        while (skeleton.getHealth() > 0 && hero.getHealth() > 0) {
            for (String ans : words) {
                if (hero.hasItem(ans) && (!ans.equals("potion") || !ans.equals("superpotion"))) {
                    item = ans;
                    hero.attack(skeleton, hero.getItem(item));
                    System.out.println("\nYou attacked the Skeleton.\n");
                } else if (ans.equals("use")) {
                    if (words.length > 1) {
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
            if (skeleton.getHealth() > 0) {
                skeleton.attack(hero);
                System.out.println("\nThe Skeleton attacked you!\n");
            }
            System.out.println(hero.getStatus());
            System.out.println("\nSkeleton HP: " + skeleton.getHealth() + "\n");
            if (hero.getHealth() > 0) {
                if (skeleton.getHealth() > 0) {
                    input = kb.nextLine();
                    words = input.split(" ");
                }
            }
        }
        if (skeleton.getHealth() <= 0) {
            System.out.println("\nYou defeated the Skeleton!\nIt dropped its scimitar. You added it to your satchel.\n");
            hero.addItem(skeleton.getMonsterItem());
        } else {
            System.out.println("You were killed fighting valiantly in battle.\nZork is a tough land that gets even the best\nof us. Thank you for playing.");
        }
    }
}