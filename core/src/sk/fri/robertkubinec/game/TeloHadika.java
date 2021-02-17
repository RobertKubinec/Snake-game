package sk.fri.robertkubinec.game;

import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author Robert Kubinec
 */
public class TeloHadika extends JednoPolicko {

    /**
     * Klucove slovo extends 
     * Trieda dedi metody a premenne z predka, ktorym je trieda JednoPolicko
     * @param poziciaX
     * @param poziciaY
     */
    public TeloHadika(int poziciaX, int poziciaY) {
        /**
         * Klucove slovo super, volame konstruktor z predka
         */
        super(poziciaX, poziciaY, new Texture("telo.jpg"));

    }
}
