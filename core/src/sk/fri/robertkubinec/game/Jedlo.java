
package sk.fri.robertkubinec.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author Robert Kubinec
 */
public class Jedlo extends JednoPolicko {

    private int poziciaX;
    private int poziciaY;
    private Texture obrazok;

    /**
     *
     * @param poziciaX
     * @param poziciaY
     * @param obrazok
     */
    public Jedlo(int poziciaX, int poziciaY, Texture obrazok) {
        super(poziciaX, poziciaY, obrazok);

        this.poziciaX = poziciaX;
        this.poziciaY = poziciaY;
        this.obrazok = obrazok;
    }

    /**
     *
     * @return
     */
    @Override
    public int getPoziciaX() {
        return this.poziciaX;
    }

    /**
     *
     * @return
     */
    @Override
    public int getPoziciaY() {
        return this.poziciaY;
    }

    /**
     *
     * @param poziciaX
     */
    @Override
    public void setPoziciaX(int poziciaX) {
        this.poziciaX = poziciaX;
    }

    /**
     *
     * @param poziciaY
     */
    @Override
    public void setPoziciaY(int poziciaY) {
        this.poziciaY = poziciaY;
    }

    /**
     *
     * @param batch
     */
    @Override
    public void vykresli(SpriteBatch batch) {
        batch.draw(this.obrazok, this.poziciaX * 32, this.poziciaY * 32);
    }

}
