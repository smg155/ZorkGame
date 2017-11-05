import java.util.*;

public class Item
{
    private String itemName = "";
    private int pow = 0;
    
    public Item(String name, int power) {
        itemName = name;
        pow = power;
    }
    
    public String getItemName() {
        return itemName;
    }
    
    public int getItemPower() {
        return pow;
    }
}