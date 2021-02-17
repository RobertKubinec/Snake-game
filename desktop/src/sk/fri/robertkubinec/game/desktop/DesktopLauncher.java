package sk.fri.robertkubinec.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import sk.fri.robertkubinec.game.Hra;

/**
 * 
 * @author Robert Kubinec
 */

public class DesktopLauncher {

    /**
     * DesktopLauncher spusta celu hru
     * @param arg
     */
    public static void main(String[] arg) {

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 640; //sirka hracej plochy
        config.height = 480; //vyska hracej plochy
        config.resizable = true; //moznost maximalizacie okna
        config.fullscreen = false; //nemoznost hrat vo fullscreene
        config.title = "SNAKE GAME"; //nazov herneho okna
        config.pauseWhenMinimized = true; //pauza hry, ked sa okno minimalizuje

        new LwjglApplication(new Hra(), config);
    }
}
