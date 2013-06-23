package GrotwormReset;

import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.util.Random;

import java.awt.*;


@Manifest(name = "GrotwormReset", authors = "tails111", description = "Resets the Grotworm Accounts")
public class GrotwormReset extends ActiveScript implements PaintListener {

    private Tree script = new Tree(new Node[]{
            new TeleportToBank(),
            new WalkToBank(),
       //     new BankHandler(),
       //     new TeleportToGrots(),
       //     new WalkToGrots()
    });

    @Override
    public void onRepaint(Graphics g){

        final String profit = "Banking";

        g.setColor(Color.WHITE);
        g.drawString(profit, 250, 250);

    }

    @Override
    public void onStart(){
    }

    @Override
    public int loop(){
        final Node stateNode = script.state();
        if(stateNode != null && Game.isLoggedIn()){
            script.set(stateNode);
            final Node setNode = script.get();
            if(setNode != null){
                getContainer().submit(setNode);
                setNode.join();
            }
        }
        return Random.nextInt(250, 450);
    }

}
