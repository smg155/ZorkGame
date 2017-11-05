import java.util.*;

public class MainCharacter extends Character
{
    private ArrayList<Item> satchel = new ArrayList<Item>();
    private int health = 0;
    
    public MainCharacter(String cname, int HP, int lev) {
        super(cname, HP, lev);
        health = HP;
    }
    
    public ArrayList<Item> getSatchel() {
        return satchel;
    }
    
    public void addItem(Item item) {
        if (satchel.size() < 16) {
            satchel.add(item);
        } else {
            System.out.println("You have too many items in the satchel!");
        }
    }
    
    public void removeItem(String itemName) {
        Item z = null;
        for (Item item : satchel) {
            if (item.getItemName().equals(itemName)) {
                z = item;
            }
        }
        if (hasItem(itemName)) {
            satchel.remove(z);
        }
    }
    
    public boolean hasItem(String itemName) {
        for (Item a : satchel) {
            if (a.getItemName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }
    
    public Item getItem(String itemName) {
        for (Item b : satchel) {
            if (b.getItemName().equals(itemName)) {
                return b;
            }
        }
        return null;
    }
    
    public void useItem(String itemName) {
        if (itemName.equals("potion")) {
            this.increaseHealth(50);
        } else if (itemName.equals("superpotion")) {
            this.increaseHealth(75);
        } else if (itemName.equals("bread")) {
            this.increaseHealth(10);
        }
    }
    
     public String getStatus() {
        {
            String str = "";
            str += "\n<******Inventory******>\n";
            if (satchel.size() > 0) {
                for(int a = 0; a < satchel.size(); a++)
                {
                    str += (a + 1 + ". " + satchel.get(a).getItemName());
                    str += "\n";
                }
            } else { 
                str += "\nNothing in inventory!\n";
            }
            str += "\n";
            str += "<******Health******>\n\n";
            if (getHealth() > 90) {
                str += ("<||||||||||||||||||||> " + getHealth() + "\n");
            } else if (getHealth() > 80) {
                str += ("<||||||||||||||||||  > " + getHealth() + "\n");
            } else if (getHealth() > 70) {
                str += ("<||||||||||||||||    > " + getHealth() + "\n");
            } else if (getHealth() > 60) {
                str += ("<||||||||||||||      > " + getHealth() + "\n");
            } else if (getHealth() > 50) {
                str += ("<||||||||||||        > " + getHealth() + "\n");
            } else if (getHealth() > 40) {
                str += ("<||||||||||          > " + getHealth() + "\n");
            } else if (getHealth() > 30) {
                str += ("<||||||||            > " + getHealth() + "\n");
            } else if (getHealth() > 20) {
                str += ("<||||||              > " + getHealth() + "\n");
            } else if (getHealth() > 10) {
                str += ("<||||                > " + getHealth() + "\n");
            } else {
                str += ("<||                  > " + getHealth() + "\nGet a potion quick! Your health is dangerously low.");
            }
            return str;
        }
    }
    
    public void attack(Monster monster, Item weapon) {
        int damage = (weapon.getItemPower() * 2) + (getHealth() / 20);
        monster.decreaseHealth(damage);
    }
}