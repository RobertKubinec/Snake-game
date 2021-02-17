package sk.fri.robertkubinec.game;

import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author Robert Kubinec
 */
public class HlavaHadika extends JednoPolicko {

    /**
     * Klucove slovo extends 
     * Trieda dedi metody a premenne z predka, ktorym je trieda JednoPolicko
     * @param poziciaX
     * @param poziciaY
     */
    public HlavaHadika(int poziciaX, int poziciaY) {
        /**
         * Klucove slovo super, volame konstruktor z predka
         */
        super(poziciaX, poziciaY, new Texture("hlava.png"));
    }

}
