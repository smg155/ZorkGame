
public interface Room 
{    
    public String getRoomDescription(MainCharacter hero);
    
    public boolean isAValidDirection(String[] directions);
    
    public Room getNextRoom(String[] direction);
    
    public boolean isItemWord(String[] words, MainCharacter hero);
    
    public boolean isMonsterWord(String[] words, MainCharacter hero);
    
    public void takeItemAction(String[] words, MainCharacter hero);
    
    public void takeMonsterAction(String[] words, MainCharacter hero);
}