package Nodes;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class WalkToGrots extends Node {
    //Cave Entrnace = 70792
    //To second layer = 70794



    Tile[] toGrotwormLair = new Tile[] {
            new Tile(3003,3219,0), new Tile(2997, 3224, 0), new Tile(2993, 3231, 0), new Tile(2990, 3236, 0)};
    Tile[] throughLevelOne = new Tile[] {
            new Tile(1206,6371,0), new Tile(1197,6372,0), new Tile(1188,6370,0), new Tile(1179,6370,0),
            new Tile(1168,6368,0), new Tile(1160,6368,0), new Tile(1151,6366,0), new Tile(1134,6364,0),
            new Tile(1125,6366,0), new Tile(1115,6361,0), new Tile(1106,6360,0), new Tile(1099,6360,0),
            new Tile(1091,6360,0)};
    Tile[] throughLevelTwo = new Tile[] {
            new Tile(1340,6488,0), new Tile(1332,6485,0), new Tile(1325,6475,0), new Tile(1321,6470,0)};


    Tile caveEntranceTile = new Tile(2990,3236,0);
    Tile secondCaveEntranceTile = new Tile(1091,6361,0);
    Tile endingTile = new Tile(1321,6470,0);



    @Override
     public boolean activate(){
        return (Calculations.distanceTo(endingTile)>=4);
    }

    @Override
    public void execute(){

        SceneObject caveEntrance = SceneEntities.getNearest(70792);
        SceneObject secondLayer = SceneEntities.getNearest(70794);

        Walking.newTilePath(toGrotwormLair).traverse();
        if(caveEntranceTile.isOnScreen()&&Calculations.distanceTo(caveEntranceTile)<=3){
            caveEntrance.interact("Enter");
            Task.sleep(1000,1500);
        }
        Walking.newTilePath(throughLevelOne).traverse();
        if(Calculations.distanceTo(secondCaveEntranceTile)<=3&&!Players.getLocal().isMoving()){
            secondLayer.interact("Enter");
            Task.sleep(1000,1500);
        }
        Walking.newTilePath(throughLevelTwo).traverse();
        Task.sleep(500,1000);
    }
}
