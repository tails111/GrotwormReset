package Nodes;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;


public class TeleportToGrots  extends Node {

    Tile portSarimLoad = new Tile(3011,3215,0);
    Area portSarim = new Area(new Tile(3007,3219,0), new Tile(3015,3213,0));
    Tile bankTile = new Tile(3093,3494,0);
    private int[] ITEMS = {8009,554,561,379};

    @Override
    public boolean activate(){
        return !portSarim.contains(Players.getLocal().getLocation()) &&
                Players.getLocal().getAnimation() == -1 &&
                Calculations.distanceTo(bankTile)<=6 && Inventory.containsAll(ITEMS);
    }

    @Override
    public void execute(){
        if(!Widgets.get(1092).getChild(0).visible()){
            Widgets.get(640).getChild(113).click(true);
            Task.sleep(750,1250);
        }
        if(Widgets.get(1092).getChild(0).visible()){
            Widgets.get(1092).getChild(48).click(true);
            do{
                Task.sleep(2000);
            }while(Calculations.distanceTo(portSarimLoad)<=5);
        }

    }
}
