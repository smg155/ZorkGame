import java.util.*;

public class FinalRoom implements Room
{
    private static HashMap<String, Room> directionsAndRooms;
    private Scanner kb = new Scanner(System.in);
    private Monster kraken = new Monster("Kraken", 300, 10, new Item("", 0));

    public FinalRoom() {
        directionsAndRooms = new HashMap<String, Room>();
        directionsAndRooms.put("up", new EndRoom());
    }

    public String getRoomDescription(MainCharacter hero)
    {
        return "\nYou walk to into a large, dark cavern. Trees\nsurround a deep, subterranean lake, casting long shadows\nacross the pine needle-embedded ground.\n";
    }

    public boolean isAValidDirection(String[] directions)
    {
        if (kraken.getHealth() > 0) {
            return false;
        } 
        for (String ans : directions) {
            if (ans.equals("up")) {
                return true;
            }
        }
        return false;
    }

    public Room getNextRoom(String[] direction)
    {
        String dir = "";
        for (String ans : direction) {
            if (ans.equals("up")) {
                dir = ans;
            }
        }
        return directionsAndRooms.get(dir);
    }

    public boolean isItemWord(String[] words, MainCharacter hero)
    {
        for (String ans : words) {
            if (ans.equals("trees") || ans.equals("tree")) {
                return true;
            }
        }
        return false;
    }

    public boolean isMonsterWord(String[] words, MainCharacter hero)
    {
        for (String ans : words) {
            if (ans.equals("lake")) {
                return true;
            }
        }
        return false;
    }

    public void takeItemAction(String[] words, MainCharacter hero)
    {
        System.out.println("\nThe trees are black... the bark, the roots, the leaves,\neverything is black. How odd.\n");
    }

    public void takeMonsterAction(String[] words, MainCharacter hero)
    {
        System.out.println("\nYou approach the lake. It begins to bubble, and\ntentacles rise up from the water. This is the\nfinal monster! The Kraken is going to attack you!\n");

        String input = kb.nextLine();
        words = input.split(" ");
        String item = "";

        while (input.indexOf("attack") < 0) {
            for (String ans : words) {
                if (ans.equals("status")) {
                    System.out.println(hero.getStatus());
                }
            }
            System.out.println("\nYou can't escape this thing! You have to attack!\nIt's the last monster!\n");
            input = kb.nextLine();
            words = input.split(" ");
        }

        while (kraken.getHealth() > 0 && hero.getHealth() > 0) {
            for (String ans : words) {
                if (hero.hasItem(ans) && (!ans.equals("potion") || !ans.equals("superpotion"))) {
                    item = ans;
                    hero.attack(kraken, hero.getItem(item));
                    System.out.println("\nYou attacked the Kraken.\n");
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
            if (kraken.getHealth() > 0){
                kraken.attack(hero);
                System.out.println("\nThe Kraken attacked you!\n");
            }
            System.out.println(hero.getStatus());
            System.out.println("\nKraken HP: " + kraken.getHealth() + "\n");
            if (hero.getHealth() > 0) {
                if (kraken.getHealth() > 0) {
                    input = kb.nextLine();
                    words = input.split(" ");
                }
            }
        }
        if (kraken.getHealth() <= 0) {
            System.out.println("\nYou defeated the Kraken! The room explodes with light\nas the terrible monster dies and sinks deep into\nthe bottom of the lake. The trees turn green again\nand the top of the cavern opens to the light. You can\nfinally go up and escape this godforsaken dungeon!\n");
            hero.addItem(kraken.getMonsterItem());
        } else if (hero.getHealth() <= 0) {
            System.out.println("You were killed fighting valiantly in battle.\nZork is a tough land that gets even the best\nof us. Thank you for playing.");
        } else { 
            input = kb.nextLine();
            words = input.split(" ");
        }
    }
}