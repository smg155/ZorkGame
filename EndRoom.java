import java.util.*;

public class EndRoom implements Room
{
    private static HashMap<String, Room> directionsAndRooms;
    
    public EndRoom () {
        directionsAndRooms = new HashMap<String, Room>();
    }
    
    public String getRoomDescription(MainCharacter hero) {
        return "\nYou emerge from the dungeon and walk into the bright sun.\n";
    }
    
    public boolean isAValidDirection(String[] directions) {
        return false;
    }
    
    public Room getNextRoom(String[] direction) {
        return null;
    }
    
    public boolean isItemWord(String[] words, MainCharacter hero) {
        return false;
    }
    
    public boolean isMonsterWord(String[] words, MainCharacter hero) {
        return false;
    }
    
    public void takeItemAction(String[] words, MainCharacter hero) {
    }
    
    public void takeMonsterAction(String[] words, MainCharacter hero) {
    }
}
