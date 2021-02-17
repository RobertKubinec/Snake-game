package sk.fri.robertkubinec.game.jedlo;

import com.badlogic.gdx.graphics.Texture;
import sk.fri.robertkubinec.game.Jedlo;

/**
 *
 * @author Robert Kubinec
 */
public class Blesk extends Jedlo {

    /**
     * Klucove slovo extends
     * Trieda dedi metody a premenne z predka, ktorym je trieda Jedlo
     * @param poziciaX
     * @param poziciaY
     */
    public Blesk(int poziciaX, int poziciaY) {
        /**
         * Klucove slovo super, volame konstruktor z predka
         */
        super(poziciaX, poziciaY, new Texture("blesk.jpg"));
    }

}
