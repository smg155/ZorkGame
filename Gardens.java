import java.util.*;

public class Gardens implements Room
{
    private static HashMap<String, Room> directionsAndRooms = null;
    private static int count = 0;
    private Scanner kb = new Scanner(System.in);
    private static Gardens instance = null;

    private Gardens() {
        if (directionsAndRooms == null) {
            directionsAndRooms = new HashMap<String, Room>();
            directionsAndRooms.put("left", ElementsRoom.getInstance());
            directionsAndRooms.put("back", ColiseumRoom.getInstance());
        }
        if (count == 0) {
            count = 1;
        }
    }

    public static Room getInstance() {
        if (instance == null) {
            instance = new Gardens();
        }
        return instance;
    }

    public String getRoomDescription(MainCharacter hero)
    {
        if (count > 1) {
            return "\nYou are in a beautiful garden - similar to\nthe style of French gardens in the 19th and 20th\ncenturies: formal and symmetrical. Bushes, flowers, trees,\nand vines dot the indoor landscape. Small insects and amphibians\nmake constant sounds in the room. There is a door\nto the left of you.\n";
        } else {
            return "\nThere is a beautiful, yet dead, garden in here - similar to\nthe style of French gardens in the 19th and\n20th centuries: formal and symmetrical. Bushes, flowers, and\ntrees dot the indoor landscape. Only one part seems off.\nA single rose flower is planted on the left side of the\ngarden. On the right side, there is a hole where the\nother should be. There is a door to the left.\n";
        }
    }

    public boolean isAValidDirection(String[] directions)
    {
        for (String ans : directions) {
            if (ans.equals("left") || ans.equals("back")) {
                return true;
            }
        }
        return false;
    }

    public Room getNextRoom(String[] direction)
    {
        String dir = "";
        for (String ans : direction) {
            if (ans.equals("left") || ans.equals("back")) {
                dir = ans;
            }
        }
        return directionsAndRooms.get(dir);
    }

    public boolean isItemWord(String[] words, MainCharacter hero)
    {
        if (count > 1) {
            return false;
        }
        for (String ans : words) {
            if (ans.equals("place") || ans.equals("rose") || ans.equals("hole")) {
                return true;
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
        System.out.println("\nYou approach the hole where the rose should be.\n");

        String input = kb.nextLine();
        words = input.split(" ");

        while (count <= 1 && input.indexOf("leave") < 0) {
            for (String ans : words) {
                if (ans.equals("place") || ans.equals("plant")) {
                    if (hero.hasItem("rose")) {
                        System.out.println("\nYou planted the rose. The garden revives itself and a vine\npulls a magnificent sword from the ground.\n");
                        input = kb.nextLine();
                        words = input.split(" ");
                        while (input.indexOf("sword") < 0) {
                            System.out.println("\nYou should take the sword.\n");
                            input = kb.nextLine();
                            words = input.split(" ");
                        }
                        System.out.println("\nYou got the Mjolnir, a magic sword from legends!\n");
                        hero.addItem(new Item("mjolnir", 17));
                        count++;
                    } else {
                        System.out.println("\nYou know what they say: you need a rose\nto plant a rose. You can leave if you want.\n");
                    }
                }
            }
            if (input.indexOf("leave") < 0) {
                if (count > 1) {
                    System.out.println("You leave the rose to thrive in its new home.\n");
                } else if (input.indexOf("plant") < 0 && input.indexOf("place") < 0) {
                    System.out.println("\nYou can't do that. You can leave if you want.\n");
                    input = kb.nextLine();
                    words = input.split(" ");
                } else {
                    input = kb.nextLine();
                    words = input.split(" ");
                }
            }
        }
        if (count <= 1) {
            System.out.println("\nYou leave the hole unplanted.\n");
        }
    }

    public void takeMonsterAction(String[] words, MainCharacter hero)
    {
    }
}
