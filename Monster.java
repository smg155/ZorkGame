public class Monster extends Character
{
    private Item monsterItem;
    
    public Monster(String cname, int HP, int lev, Item item) {
        super(cname, HP, lev);
        monsterItem = item;
    }
    
    public Item getMonsterItem() {
        return monsterItem;
    }
    
    public void attack(MainCharacter hero) {
        int damage = getLevel() + 5;
        hero.decreaseHealth(damage);
    }
}