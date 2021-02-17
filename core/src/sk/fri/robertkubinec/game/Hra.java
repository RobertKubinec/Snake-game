package sk.fri.robertkubinec.game;

import com.badlogic.gdx.Game;

/**
 *
 * @author Robert Kubinec
 */
public class Hra extends Game {

    @Override
    public void create() {
        /**
         * Vytvarame hernu obrazovku, ktorej vlastnosti obsahuje trieda ObrazovkaHry
         */
        setScreen(new ObrazovkaHry());
    }
}
