import java.util.*;

public class NothingRoom implements Room
{
    private static HashMap<String, Room> directionsAndRooms = null;
    private static int count = 0;
    private Scanner kb = new Scanner(System.in);
    private boolean done = false;
    private static NothingRoom instance = null;

    private NothingRoom() {
        if (directionsAndRooms == null) {
            directionsAndRooms = new HashMap<String, Room>();
            directionsAndRooms.put("straight", Library.getInstance());
            directionsAndRooms.put("back", ColiseumRoom.getInstance());
        }
        if (count == 0) {
            count = 1;
        }
    }

    public static Room getInstance() {
        if (instance == null) {
            instance = new NothingRoom();
        }
        return instance;
    }

    public String getRoomDescription(MainCharacter hero)
    {
        if (count == 1) {
            return "\nThere is nothing in this room. It is four bleak\nwalls made of concrete. There is a door straight\nin front of you.\n";
        } else if (done) {
            return "\nThe cornucopia has vanished and the room is back to\nits bleak state. There is a door straight in\nfront of you.\n";
        } else if (count == 2) {
            return "\nThe room changed! For some mysterious reason, something\nis here. There is a human-sized cornucopia in the middle\nof the room!\n";
        } else {
            return "\nThe cornucopia has vanished and the room is back to\nits bleak state. There is a door straight in\nfront of you.\n";
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
                count++;
            }
        }
        return directionsAndRooms.get(dir);
    }

    public boolean isItemWord(String[] words, MainCharacter hero)
    {
        if (count == 1) {
            return false;
        }
        if (done) {
            return false;
        }
        for (String ans : words) {
            if (ans.equals("cornucopia")) {
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
        System.out.println("\nOddly enough, the cornucopia only has three things inside\nof it: a potion, a rose, and a lance.\n");

        boolean item1 = false, item2 = false, item3 = false;
        String input = kb.nextLine();
        words = input.split(" ");

        while(!done && input.indexOf("leave") < 0) {
            for (String ans : words) {
                if (ans.equals("rose")) {
                    System.out.println("\nYou picked up the rose and put it in your\nsatchel. Maybe it will be useful at some point.\n");
                    hero.addItem(new Item("rose", 0));
                    item1 = true;
                } else if (ans.equals("potion")) {
                    System.out.println("\nA potion is always useful. You added it to your bag.\n");
                    hero.addItem(new Item("potion", 0));
                    item2 = true;
                } else if (ans.equals("lance")) {
                    System.out.println("\nYou picked up the lance and added it your satchel.\n");
                    hero.addItem(new Item("lance", 3));
                    item3 = true;
                }
            }
            if (item1 && item2 && item3) {
                System.out.println("\nYou have all the items from the cornucopia.\n");
                done = true;
            } else if (item2 && item3) {
                System.out.println("\nThere's still a rose. You can leave if you don't want it.\n");
                input = kb.nextLine();
                words = input.split(" ");
            } else if (item1 && item2) {
                System.out.println("\nThere's still a lance. You can leave if you don't want it.\n");
                input = kb.nextLine();
                words = input.split(" ");
            } else if (item1 && item3) {
                System.out.println("\nThere's still a potion. You can leave if you don't want it.\n");
                input = kb.nextLine();
                words = input.split(" ");
            } else if (item1) {
                System.out.println("\nThere's still a potion and a lance.\nYou can leave if you don't want them");
                input = kb.nextLine();
                words = input.split(" ");
            } else if (item2) {
                System.out.println("\nThere's still a rose and a lance.\nYou can leave if you don't want them.\n");
                input = kb.nextLine();
                words = input.split(" ");
            } else if (item3) {
                System.out.println("\nThere's still a potion and a rose.\nYou can leave if you don't want them.\n");
                input = kb.nextLine();
                words = input.split(" ");
            } else if (!item1 && !item2 && !item3) {
                System.out.println("\nThat doesn't work.\n");
                input = kb.nextLine();
                words = input.split(" ");
            }
        }
        System.out.println("\nYou leave the cornucopia.\n");
        done = true;
    }

    public void takeMonsterAction(String[] words, MainCharacter hero)
    {
    }
}
