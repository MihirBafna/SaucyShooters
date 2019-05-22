import java.util.ArrayList;

public class Interactions {

    private Player player;
    private Inventory inventory;
    private ArrayList<Gun> groundGuns;
    private ArrayList<Crate> crates;

    public Interactions(Player player, ArrayList<Gun> groundGuns, ArrayList<Crate> crates) {
        this.player = player;
        inventory = player.getInventory();
        this.groundGuns = groundGuns;
        this.crates = crates;
    }

    public void openCrate() {
        for (Crate c : crates) {
            if (GameObject.collision(player.getCircle(), c.getRectangle())) {
            }
        }
    }

    public void addGun(Gun gun) {
        // if()
        for (int g = 0; g < inventory.getGuns().size(); g++) {
            if (inventory.getGuns().get(g) == null) {
                inventory.getGuns().set(g, gun);
                inventory.getGuns().get(g).setEquipped(false);
                return;
            }
        }
    }

}