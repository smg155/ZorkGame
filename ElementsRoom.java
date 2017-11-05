import java.util.*;

public class ElementsRoom implements Room
{
    private static HashMap<String, Room> directionsAndRooms = null;
    private boolean elementsDone = false;
    private boolean waterDone = false;
    private boolean fireDone = false;
    private boolean earthDone = false;
    private boolean airDone = false;
    private boolean weaponsDone = false;
    private Scanner kb = new Scanner(System.in);
    private static Monster gru = null;
    private static Monster skeleton = null;
    private static Monster eel = null;
    private static Monster dragon = null;
    private String input = "", item = "";
    private static ElementsRoom instance = null;

    private ElementsRoom() {
        if (directionsAndRooms == null) {
            directionsAndRooms = new HashMap<String, Room>();
            directionsAndRooms.put("straight", new FinalRoom());
            directionsAndRooms.put("back", Gardens.getInstance());
        }
        if (gru == null) {
            gru = new Monster("Gru", 25, 1, new Item("potion", 0));
        }
        if (skeleton == null) {
            skeleton = new Monster("Skeleton", 30, 1, new Item("potion", 0));
        }
        if (eel == null) {
            eel = new Monster("Eel", 90, 4, new Item("superpotion", 0));
        }
        if (dragon == null) {
            dragon = new Monster("Dragon", 60, 6, new Item("superpotion", 0));
        }
    }

    public static Room getInstance() {
        if (instance == null) {
            instance = new ElementsRoom();
        }
        return instance;
    }

    public String getRoomDescription(MainCharacter hero)
    {
        if (!elementsDone) {
            return "\nThis room is reminiscent of the inside of a castle.\nThe walls are grey brick. Straight in front\nof you across a long hallway is an enormous door with\nmultiple locks on it. On either side of the hallway\nare 4 small rooms. Above each, there is an engraving of words:\n\"Water\", \"Earth\", \"Fire\", and \"Air\".\n";
        } else if (!weaponsDone) {
            return "\nThe doors to the elemental chambers close, and the locks on\nthe large door in front of you click open. One more\nlock remains. You notice that in the door there\nare four indentations that look strangely like the weapons\nyou just picked up.\n";
        } else {
            return "\nThe enormous door (straight in front of you) is\ncompletely unlocked and opens to reveal another room.\n";
        }
    }

    public boolean isAValidDirection(String[] directions)
    {
        for (String ans : directions) {
            if (ans.equals("straight") || ans.equals("back")) {
                return true;
            }
        }
        return false;
    }

    public Room getNextRoom(String[] direction)
    {
        String dir = "";
        for (String ans : direction) {
            if (ans.equals("straight") || ans.equals("back")) {
                dir = ans;
            }
        }
        return directionsAndRooms.get(dir);
    }

    public boolean isItemWord(String[] words, MainCharacter hero)
    {
        if (!elementsDone) {
            for (String ans: words) {
                if (ans.equals("rooms") || ans.equals("room")) {
                    return true;
                }
            }
        } else {
            for (String ans : words) {
                if (ans.equals("indentations") || ans.equals("weapon") || ans.equals("weapons")) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isMonsterWord(String[] words, MainCharacter hero)
    {
        return false;
    }

    public void takeItemAction(String[] words, MainCharacter hero)
    {
        if (!elementsDone) {
            System.out.println("\nYou approach the rooms. Each room appears to be\nthemed a different element. Which room would\nyou like to explore?\n");

            String roomOrCommand = "";
            boolean tookItem = true;
            input = kb.nextLine();
            words = input.split(" ");
            while (input.indexOf("water") < 0 && input.indexOf("fire") < 0 && input.indexOf("air") < 0 && input.indexOf("earth") < 0 && input.indexOf("description") < 0 && input.indexOf("use") < 0 && input.indexOf("status") < 0 && input.indexOf("leave") < 0) {
                System.out.println("\nYou should check out one of those rooms.\n");
                input = kb.nextLine();
                words = input.split(" ");
            }

            if (input.indexOf("leave") < 0) {
                while (input.indexOf("leave") < 0 && !elementsDone) {
                    roomOrCommand = "";
                    for (String ans : words) {
                        if (ans.toUpperCase().equals("FIRE") || ans.toUpperCase().equals("WATER") || ans.toUpperCase().equals("EARTH") || ans.toUpperCase().equals("AIR") || ans.toUpperCase().equals("DESCRIPTION") || ans.toUpperCase().equals("USE") || ans.toUpperCase().equals("STATUS")) {
                            roomOrCommand = ans.toUpperCase();
                        }
                    }
                    if (roomOrCommand.equals("FIRE") && !fireDone) {
                        System.out.println("\nYou walk into the room labled \"Fire\". In its\n4 corners, brass goblets hold flames of every\ndifferent color and size. In the center of the room\nthere is a pedestal that holds a moon-shaped blade.\n");
                        input = kb.nextLine();
                        words = input.split(" ");
                        while (input.indexOf("leave") < 0 && input.indexOf("blade") < 0) {
                            System.out.println("\nCheck out the blade on the pedestal. You can leave the room if you want.\n");
                            input = kb.nextLine();
                            words = input.split(" ");
                        }
                        for (String ans : words) {
                            if (ans.equals("leave")) {
                                tookItem = false;
                            }
                        } 
                        if (tookItem) {
                            System.out.println("\nYou took the moonblade! You added it to your satchel.\nWatch out! A Gru appeared from nowhere!\n");
                            hero.addItem(new Item("moonblade", 5));
                            String[] fireWords = {"fire"};
                            takeMonsterAction(fireWords, hero);
                            fireDone = true;
                        }
                    } else if (roomOrCommand.equals("WATER") && !waterDone) {
                        System.out.println("\nYou walk into the water room. A stream flows\nthrough the floor. At the end of it, lies\na beautiful blue weapon.\n");
                        input = kb.nextLine();
                        words = input.split(" ");
                        while (input.indexOf("leave") < 0 && input.indexOf("weapon") < 0 && input.indexOf("blue") < 0) {
                            System.out.println("\nCheck out the weapon at the end of the stream. You can leave the room if you want.\n");
                            input = kb.nextLine();
                            words = input.split(" ");
                        }
                        for (String ans : words) {
                            if (ans.equals("leave")) {
                                tookItem = false;
                            }
                        } 
                        if (tookItem) {
                            System.out.println("\nYou took the tidalblade! You added it to your satchel.\nWatch out! An Eel appeared from nowhere!\n");
                            hero.addItem(new Item("tidalblade", 5));
                            String[] waterWords = {"water"};
                            takeMonsterAction(waterWords, hero);
                            waterDone = true;
                        }
                    } else if (roomOrCommand.equals("EARTH") && !earthDone) {
                        System.out.println("\nYou walk into the room labeled \"Earth\". Rocks and\nboulders lay across the room. On top of a\nmound of the brown rocks sits a large hammer.\n");
                        input = kb.nextLine();
                        words = input.split(" ");
                        while (input.indexOf("leave") < 0 && input.indexOf("weapon") < 0 && input.indexOf("hammer") < 0) {
                            System.out.println("\nCheck out the weapon on top of the mound. You can leave the room if you want.\n");
                            input = kb.nextLine();
                            words = input.split(" ");
                        }
                        for (String ans : words) {
                            if (ans.equals("leave")) {
                                tookItem = false;
                            }
                        } 
                        if (tookItem) {
                            System.out.println("\nYou took the hammer! You added it to your satchel.\nWatch out! A Skeleton appeared from nowhere!\n");
                            hero.addItem(new Item("hammer", 5));
                            String[] earthWords = {"earth"};
                            takeMonsterAction(earthWords, hero);
                            earthDone = true;
                        }
                    } else if (roomOrCommand.equals("AIR") && !airDone) {
                        System.out.println("\nYou walk into the air room. You feel wind hit\nyour face as you notice a small daggar\nfloating in mid air in the center of\nthe room.\n");
                        input = kb.nextLine();
                        words = input.split(" ");
                        while (input.indexOf("leave") < 0 && input.indexOf("weapon") < 0 && input.indexOf("daggar") < 0) {
                            System.out.println("\nCheck out the weapon floating in the air. You can leave the room if you want.\n");
                            input = kb.nextLine();
                            words = input.split(" ");
                        }
                        for (String ans : words) {
                            if (ans.equals("leave")) {
                                tookItem = false;
                            }
                        } 
                        if (tookItem) {
                            System.out.println("\nYou took the daggar! You added it to your satchel.\nWatch out! A Dragon appeared from nowhere!\n");
                            hero.addItem(new Item("daggar", 5));
                            String[] airWords = {"air"};
                            takeMonsterAction(airWords, hero);
                            airDone= true;
                        }
                    } else if (roomOrCommand.equals("STATUS")) {
                        System.out.println(hero.getStatus());
                    } else if (roomOrCommand.equals("DESCRIPTION")) {
                        System.out.println("\nThere are four rooms here labeled \"Water\", \"Earth\", \"Fire\", and \"Air\".\n");
                    } else if (roomOrCommand.equals("USE")) {
                        if (words.length > 1) {
                            for (int index = 0; index < words.length; index++) {
                                if (words[index].equals("use")) {
                                    item = words[index + 1];
                                    if (item.equals("potion") && hero.hasItem("potion")) {
                                        hero.useItem("potion");
                                        hero.removeItem("potion");
                                        System.out.println("\nYou restored 50 health points!\n");
                                    } else if (item.equals("superpotion") && hero.hasItem("superpotion")) {
                                        hero.useItem("superpotion");
                                        hero.removeItem("superpotion");
                                        System.out.println("\nYou restored 75 health points!\n");
                                    } else if (item.equals("bread") && hero.hasItem("bread")) {
                                        hero.useItem("bread");
                                        hero.removeItem("bread");
                                        System.out.println("\nYou restored 10 health points!\n");
                                    } else {
                                        System.out.println("\nOak's voice echoes in your head:\n" + hero.getName() + ", there is a time and place for everything, but not now.\n");
                                    }
                                }
                            }
                        } else {
                            System.out.println("\nOak's voice echoes in your head:\n" + hero.getName() + ", there is a time and place for everything, but not now.\n");
                        }
                    } else {
                        System.out.println("\nYou can't do that...\n");
                    }
                    if (tookItem) {
                        if (airDone && waterDone && fireDone && earthDone) {
                            elementsDone = true;
                            System.out.println("\nSuddenly, the words above each room glow with\na bright, blue light. You exit the room just as\neach doorway closes itself.\n");
                        } else {
                            input = kb.nextLine();
                            words = input.split(" ");
                        }
                    } else {
                        break;
                    }
                }
            }
            if (hero.getHealth() > 0) {
                System.out.println("\nYou leave the elemental rooms.\n");
            }
        } else if (!weaponsDone) {
            System.out.println("\nYou approach the large door. The indentations fit\nyour weapons exactly. Maybe you can put\nthem there.\n");
            input = kb.nextLine();
            words = input.split(" ");
            String answer = "";
            for(String ans : words) {
                if (ans.equals("put") || ans.equals("place")) {
                    answer = ans;
                }
            }
            if (answer.equals("place") || answer.equals("put")) {
                System.out.println("\nYou put the four elemental weapons in the indentations.\nThey begin to glow and suddenly the merge together! You\ncreated a new weapon: the element! The final lock on the\ndoor opens.\n");
                hero.removeItem("moonblade");
                hero.removeItem("hammer");
                hero.removeItem("daggar");
                hero.removeItem("tidalblade");
                hero.addItem(new Item("element", 14));
                weaponsDone = true;
            } else {
                System.out.println("\nYou didn't do anything. You step back from the door.\n");
            }
        }
    }

    public void takeMonsterAction(String[] words, MainCharacter hero) 
    {
        if (words[0].equals("fire")) {
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
                System.out.println("\nYou defeated the Gru!\nIt dropped a potion. You add it to\nyour satchel and leave the room.\n");
                hero.addItem(gru.getMonsterItem());
            } else {
                System.out.println("You were killed fighting valiantly in battle.\nZork is a tough land that gets even the best\nof us. Thank you for playing.");
            }
        } else if (words[0].equals("water")) {
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

            while (eel.getHealth() > 0 && hero.getHealth() > 0) {
                for (String ans : words) {
                    if (hero.hasItem(ans) && (!ans.equals("potion") || !ans.equals("superpotion"))) {
                        item = ans;
                        hero.attack(eel, hero.getItem(item));
                        System.out.println("\nYou attacked the Eel.\n");
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
                if (eel.getHealth() > 0){
                    eel.attack(hero);
                    System.out.println("\nThe Eel attacked you!\n");
                }
                System.out.println(hero.getStatus());
                System.out.println("\nEel HP: " + eel.getHealth() + "\n");
                if (hero.getHealth() > 0) {
                    if (eel.getHealth() > 0) {
                        input = kb.nextLine();
                        words = input.split(" ");
                    }
                }
            }
            if (eel.getHealth() <= 0) {
                System.out.println("\nYou defeated the Eel!\nIt dropped a superpotion. You add it to your satchel and leave the room.\n");
                hero.addItem(eel.getMonsterItem());
            } else {
                System.out.println("You were killed fighting valiantly in battle.\nZork is a tough land that gets even the best\nof us. Thank you for playing.");
            }
        } else if (words[0].equals("earth")) {
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
                System.out.println("\nYou defeated the Skeleton!\nIt dropped a potion. You add it to your satchel and leave the room.\n");
                hero.addItem(skeleton.getMonsterItem());
            } else {
                System.out.println("You were killed fighting valiantly in battle.\nZork is a tough land that gets even the best\nof us. Thank you for playing.");
            }
        } else {
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

            while (dragon.getHealth() > 0 && hero.getHealth() > 0) {
                for (String ans : words) {
                    if (hero.hasItem(ans) && (!ans.equals("potion") || !ans.equals("superpotion"))) {
                        item = ans;
                        hero.attack(dragon, hero.getItem(item));
                        System.out.println("\nYou attacked the Dragon.\n");
                    } else if (ans.equals("use")) {
                        if (words.length > 0) {
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
                if (dragon.getHealth() > 0) {
                    dragon.attack(hero);
                    System.out.println("\nThe Dragon attacked you!\n");
                }
                System.out.println(hero.getStatus());
                System.out.println("\nDragon HP: " + dragon.getHealth() + "\n");
                if (hero.getHealth() > 0) {
                    if (dragon.getHealth() > 0) {
                        input = kb.nextLine();
                        words = input.split(" ");
                    }
                }
            }
            if (dragon.getHealth() <= 0) {
                System.out.println("\nYou defeated the Dragon!\nIt dropped a superpotion. You add it to your satchel and leave the room.\n");
                hero.addItem(dragon.getMonsterItem());
            } else {
                System.out.println("You were killed fighting valiantly in battle.\nZork is a tough land that gets even the best\nof us. Thank you for playing.");
            }
        }
    }
}