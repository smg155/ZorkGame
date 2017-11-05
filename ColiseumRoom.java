import java.util.*;

public class ColiseumRoom implements Room
{
    private static HashMap<String, Room> directionsAndRooms = null;
    private Scanner kb = new Scanner(System.in);
    private static Monster angler = null;
    private int count = 0;
    private static ColiseumRoom instance = null;

    private ColiseumRoom() {
        if (directionsAndRooms == null) {
            directionsAndRooms = new HashMap<String, Room>();
            directionsAndRooms.put("straight", Gardens.getInstance());
            directionsAndRooms.put("right", NothingRoom.getInstance());
            directionsAndRooms.put("back", FirstHallway.getInstance());
        }
        if (angler == null) {
            angler = new Monster("angler", 90, 4, new Item("superpotion", 0));
        }
    }

    public static Room getInstance() {
        if (instance == null) {
            instance = new ColiseumRoom();
        }
        return instance;
    }

    public String getRoomDescription(MainCharacter hero) {
        if (angler.getHealth() <= 0) {
            return "\nYou are in an enormous room with marble\ncolumns spread randomly through it. The doors\nin the room all close. The massive structures\ncast eerie shadows upon the floor. You notice the corpse of\na warrior resting its bones against the wall.\nThere is a door across the room straight in\nfront of you and a door to the right of you.\n"; 
        } else {
            return "\nYou walk into an enormous room with marble\ncolumns spread randomly through it. The\nmonolithic structures cast eerie shadows upon the floor.\nYou notice the corpse of a warrior resting its bones against the wall.\nCreepy. A faint light shines in the distance. It moves\nswiftly in between the columns.\n";
        }
    }

    public boolean isAValidDirection(String[] directions) {   
        if (angler.getHealth() > 0) {
            return false;
        }
        for (String ans : directions) {
            if (ans.equals("straight") || ans.equals("right") || ans.equals("back")) {
                return true;
            }
        }
        return false;
    }

    public Room getNextRoom(String[] direction) {
        String dir = "";
        for (String ans : direction) {
            if (ans.equals("right") || ans.equals("straight") || ans.equals("back")) {
                dir = ans;
            }
        }
        return directionsAndRooms.get(dir);
    }

    public boolean isItemWord(String[] words, MainCharacter hero) {
        for (String ans : words) {
            if (ans.equals("corpse") || ans.equals("skeleton")) {
                return true;
            }
        }
        return false;
    }

    public boolean isMonsterWord(String[] words, MainCharacter hero) {
        for (String ans : words) {
            if (ans.equals("light")) { 
                return true;
            }
        }
        return false;
    }

    public void takeItemAction(String[] words, MainCharacter hero) {
        System.out.println("\nYou walk up to the corpse. It looks like he\nwas eaten to death. That's a very sad way to go.\n");
    }

    public void takeMonsterAction(String[] words, MainCharacter hero) {
        System.out.println("\nYou follow the faint light, weaving in and out of\nthe dark columns. It almost looks like a floating\nfire. Suddenly it moves toward you! It's going\nto attack!\n");

        String item = "";
        String input = "";
        input = kb.nextLine();
        words = input.split(" ");
        while (input.indexOf("attack") < 0) {
            for (String ans : words) {
                if (ans.equals("status")) {
                    System.out.println(hero.getStatus());
                }
            }
            System.out.println("\nYou can't escape this thing! You have to attack!\n");
            input = kb.nextLine();
            words = input.split(" ");
        }

        while (angler.getHealth() > 0 && hero.getHealth() > 0) {
            for (String ans : words) {
                if (hero.hasItem(ans) && (!ans.equals("potion") || !ans.equals("superpotion"))) {
                    item = ans;
                    hero.attack(angler, hero.getItem(item));
                    System.out.println("\nYou attacked the Angler.\n");
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
            if (angler.getHealth() > 0){
                angler.attack(hero);
                System.out.println("\nThe Angler attacked you!\n");
            }
            System.out.println(hero.getStatus());
            System.out.println("\nAngler HP: " + angler.getHealth() + "\n");
            if (hero.getHealth() > 0) {
                if (angler.getHealth() > 0) {
                    input = kb.nextLine();
                    words = input.split(" ");
                }
            }
        }
        if (angler.getHealth() <= 0) {
            System.out.println("\nYou defeated the Angler!\nIt dropped a superpotion. You added it to your satchel.\nThe doors in the room open.\n");
            hero.addItem(angler.getMonsterItem());
        } else if (hero.getHealth() <= 0) {
            System.out.println("You were killed fighting valiantly in battle.\nZork is a tough land that gets even the best\nof us. Thank you for playing.");
        } else { 
            input = kb.nextLine();
            words = input.split(" ");
        }
    }
}
