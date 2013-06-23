package Nodes;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class BankHandler extends Node {

    private int[] BANKS = {42378,42377,42217};
    private int[] ITEMS = {8009,554,561,379};
    private int[] ITEMAMOUNTS = {1,5000,1000,13};

    Tile bankTile = new Tile(3093,3494,0);

    @Override
    public boolean activate(){
        return Calculations.distanceTo(bankTile)<=8 &&
                !Inventory.containsAll(ITEMS);
    }

    @Override
    public void execute(){
        SceneObject Booth = SceneEntities.getNearest(BANKS);

        if(!Bank.isOpen()){
            Booth.interact("Bank");
            Task.sleep(1000,1500);
        }
        Bank.depositInventory();
        for(int x = 0; x<= ITEMS.length-1; x++){
            Bank.withdraw(ITEMS[x], ITEMAMOUNTS[x]);
        }
        Bank.close();
        Task.sleep(500,1000);

    }
}
