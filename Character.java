import java.util.*;

public class Character
{
    private int HP = 0;
    private int level = 0;
    private String name = "";

    public Character(String cname, int health, int lev) {
        name = cname;
        HP =  health;
        level = lev;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return HP;
    }

    public int getLevel() {
        return level;
    }
    
    public void increaseHealth(int num) {
        int maxIncrease = 100 - getHealth();
        if (num > maxIncrease) {
            HP += maxIncrease;
        } else {
            HP += num;
        }
    }

    public void decreaseHealth(int num) {
        if (HP - num <= 0) {
            HP = 0;
        } else {
            HP -= num;
        }
    }
}