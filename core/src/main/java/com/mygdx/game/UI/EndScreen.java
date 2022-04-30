package com.mygdx.game.UI;
import static com.mygdx.game.PirateGame.prefs;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Components.Pirate;
import com.mygdx.game.Entitys.Player;
import com.mygdx.game.Managers.EntityManager;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Managers.ResourceManager;
import com.mygdx.game.PirateGame;

import java.util.ArrayList;

import static com.mygdx.utils.Constants.VIEWPORT_HEIGHT;

/**
 * Contains widgets defining the game end screen.
 */
public class EndScreen extends Page {
    Label wonText;
    Label playerStats;

    public EndScreen(PirateGame game) {
        super(game);
    }

    /**
     * Set game end screen status to report a win.
     */
    public void win() {
        wonText.setText("Congrats You Have Won");
    }

    /**
     * Create game end screen widgets, initialised to game loss status.
     */
    @Override
    protected void CreateActors() {
        Table t = new Table();
        t.setBackground(new TextureRegionDrawable(ResourceManager.getTexture("menuBG.jpg")));

        float space = VIEWPORT_HEIGHT * 0.25f;
        t.setFillParent(true);
        actors.add(t);
        //ADD LOSE TEXT AND WIN TEXT
        wonText = new Label("You have lost", parent.skin);
        wonText.setFontScale(2);
        t.top();
        t.add(wonText).top().spaceBottom(space);
        t.row();
        playerStats = new Label("Player Stats:\n", parent.skin);
        t.add(playerStats).spaceBottom(space);
        t.row();
        TextButton b = new TextButton("Exit", parent.skin);
        b.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
                System.exit(0);
            }
        });
        t.add(b).size(100, 25).top().spaceBottom(space* 0.5f);;
        //AYMAN RESTART CHANGE:
        t.row();
        TextButton r = new TextButton("Restart", parent.skin);
        r.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Restart");
                //EntityManager.cleanUp();
                //parent.dispose();
                //parent = new PirateGame();
                //GameManager.setInitialized(false);
                //parent.menu = new MenuScreen(parent);
                GameManager.restartGame();
                parent.setScreen(parent.game);
                //INSTEAD OF GOING TO MENU WE GO TO GAME AND RESET
                //ALL POS AND STATS USING A RESASH OF INIT AND SPAWN
                //parent.setScreen(parent.game);
                //parent.game.dispose();

                //GameManager.Initialize(prefs.getString("difficulty"));
                //reset all entities:
                //bit below works but adds more assets instead of removing and restarting
                //empty all arrays:
                //factions
                //colleges
                //ballCache
                //DOESNT WORK FSR
                //MAYBE CREATE NEW PARENT.GAMESCREEN THEN CALL THAT WITH CURRENT GAME PREFS/SETTINGS
                //INSTEAD OF GOING BACK TO MENU SCREEN:
                //System.out.println(GameManager.getShips(0));
                //GameManager.resetShips();
                //System.out.println(GameManager.getShip());
                //parent.setScreen(parent.menu);
            }
        });
        t.add(r);
    }

    @Override
    protected void update() {
        super.update();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
            System.exit(0);
        }
    }

    /**
     * Get player stats such as plunder etc. and display game end screen.
     */
    @Override
    public void show() {
        super.show();
        Player p = GameManager.getPlayer();
        //AYMAN POINTS CHANGE:
        int time = (int) (parent.game.timer);
        int totalPoint = (p.getComponent(Pirate.class).getPoints()) - (time/2);
        String stats = String.format("Health: %s\nAmmo: %s\nPlunder: %s\nPoints: %s\nTime: %s", p.getHealth(), p.getAmmo(), p.getPlunder(),totalPoint, time);
        playerStats.setText(stats);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        Table t = (Table) actors.get(0);
        t.setBackground(new TextureRegionDrawable(ResourceManager.getTexture("menuBG.jpg"))); // prevent the bg being stretched
    }
}
