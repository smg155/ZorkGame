import java.util.*;

public class ZorkGame
{
    public static void main(String[]args)
    {
        Scanner kb = new Scanner(System.in);
        String input = "";
        String[] inputSplit = new String[20];
        int count = 0;
        
        //VARIABLE AND OBJECT FOR BEATING GAME
        boolean beatenGame = false;
        Room endRoom = new EndRoom();
        //____________________________

        System.out.println();
        System.out.println("Welcome to ZORK, a text-based adventure game!\nGreatness awaits you down in the dungeons\nbelow. Get ready to start your journey, hero.");
        System.out.println("\nThis is ZORK");
        System.out.println("What is your name, chosen one?");
        String nameOfCharacter = kb.next();
        MainCharacter hero = new MainCharacter(nameOfCharacter, 100, 1);
        System.out.println();
        System.out.println("This is your character:\nName: " + hero.getName() + "\nHP: " + hero.getHealth() + "\nLevel: " + hero.getLevel() + "\nYou also have a trusty satchel to accompany you on this arduous journey.\n");
        System.out.println("Directions:\nTo go to antoher room, most times, type \"go (right, left, straight, back)\"\nTo attack a monster, type \"attack with (item name from your satchel)\"\nTo show your status, type \"status\"\nTo get a description of your surroundings, type \"description\"\nTo use an item, type \"use (item name)\"\nTo quit your game, type \"quit\"\n\nWhen typing in commands in each room,\npay close atention to the words used in the text.\nThose words will often give you a clue as to\nwhich verbs and nouns should be used. In\naddition, type commands with a verb and\nan adjective, adverb, or noun. This will ensure\nthat what you type works with the program.\n");
        System.out.println("\nIf you want to go back into a previous room, type \"go back\".\nIf you do so, you will be taken to the room directly behind the\nroom you are in, not necessarily the room you were just in.\nWhen you go back, you will be oriented such that you came\ninto the room from the normal entrance.\n");
        System.out.println("Let us begin. Type anything to start your adventure.\n");
        input = kb.next();
        System.out.println("");
        kb.nextLine();

        Room currentRoom = GrassyRoom.getInstance();
        System.out.println(currentRoom.getRoomDescription(hero));
        input = kb.nextLine();
        inputSplit = input.split(" ");
        System.out.println();
        while(!inputSplit[0].equals("quit")) {
            while (!currentRoom.isAValidDirection(inputSplit)) {
                if (currentRoom.isItemWord(inputSplit, hero)) {
                    currentRoom.takeItemAction(inputSplit, hero);
                } else if (currentRoom.isMonsterWord(inputSplit, hero)) {
                    currentRoom.takeMonsterAction(inputSplit, hero);
                } else if (inputSplit[0].equals("use")) {
                    if (inputSplit.length > 1){
                        if (hero.hasItem(inputSplit[1]) && inputSplit[1].equals("potion")) {
                            hero.increaseHealth(50);
                            System.out.println("\nYou restored 50 health points!\n");
                            hero.removeItem("potion");
                        } else if (hero.hasItem(inputSplit[1]) && inputSplit[1].equals("superpotion")) {
                            hero.increaseHealth(75);
                            System.out.println("\nYou restored 75 health points!\n");
                            hero.removeItem("superpotion");
                        }  else {
                            System.out.println("\nOak's voice echoes in your head:\n" + hero.getName() + ", there is a time and place for everything, but not now.\n");
                            count++;
                            break;
                        }
                    } else {
                        System.out.println("\nOak's voice echoes in your head:\n" + hero.getName() + ", there is a time and place for everything, but not now.\n");
                        count++;
                        break;
                    }
                } else if (inputSplit[0].equals("status")) {
                    System.out.println(hero.getStatus());
                } else if (inputSplit[0].equals("description")) {
                    System.out.println(currentRoom.getRoomDescription(hero));
                } else if (inputSplit[0].equals("quit")) {
                    break;
                } else {
                    if (hero.getHealth() <= 0) {
                        inputSplit[0] = "quit";
                        break;
                    } else {
                        System.out.println("\nThat is invalid. Try again.\n");
                    }
                }
                input = kb.nextLine();
                inputSplit = input.split(" ");
                System.out.println();
            }
            if (!inputSplit[0].equals("quit")) {
                if (count == 0) {
                    currentRoom = currentRoom.getNextRoom(inputSplit);
                    
                    if (currentRoom.getRoomDescription(hero).equals(endRoom.getRoomDescription(hero))) {
                        System.out.println(currentRoom.getRoomDescription(hero));
                        inputSplit[0] = "quit";
                        beatenGame = true;
                        break;
                    }
                    
                    System.out.println(currentRoom.getRoomDescription(hero));
                    input = kb.nextLine();
                    inputSplit = input.split(" ");
                } else {
                    count = 0;
                    input = kb.nextLine();
                    inputSplit = input.split(" ");
                }
            }
        }
        if (beatenGame) {
            System.out.println("\nYou finally finished the dungeon. Congratulations.\nYour wits have served you well.\n");
        }
    }
}
