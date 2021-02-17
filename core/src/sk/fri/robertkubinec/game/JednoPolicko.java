package sk.fri.robertkubinec.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author Robert Kubinec
 */
public class JednoPolicko {

    /**
     * Atributy triedy JednoPolicko
     */
    private int poziciaX;
    private int poziciaY;
    private Texture obrazok;

    /**
     * Konstruktor triedy JednoPolicko
     * @param poziciaX
     * @param poziciaY
     * @param obrazok
     */
    public JednoPolicko(int poziciaX, int poziciaY, Texture obrazok) {
        this.poziciaX = poziciaX;
        this.poziciaY = poziciaY;
        this.obrazok = obrazok;
    }

    /**
     *
     * @return
     */
    public int getPoziciaX() {
        return this.poziciaX;
    }

    /**
     *
     * @return
     */
    public int getPoziciaY() {
        return this.poziciaY;
    }

    /**
     *
     * @return
     */
    public Texture getObrazok() {
        return this.obrazok;
    }

    /**
     *
     * @param poziciaX
     */
    public void setPoziciaX(int poziciaX) {
        this.poziciaX = poziciaX;
    }

    /**
     *
     * @param poziciaY
     */
    public void setPoziciaY(int poziciaY) {
        this.poziciaY = poziciaY;
    }

    /**
     *
     * @param obrazok
     */
    public void setObrazok(Texture obrazok) {
        this.obrazok = obrazok;
    }

    /**
     *
     * @param poziciaX
     * @param poziciaY
     */
    public void pohniSa(int poziciaX, int poziciaY) {
        this.poziciaX += poziciaX;
        this.poziciaY += poziciaY;

        if (this.poziciaX > ObrazovkaHry.SIRKA - 1) {
            this.poziciaX = 0;
        } else if (this.poziciaX < 0) {
            this.poziciaX = ObrazovkaHry.SIRKA - 1;
        }
        if (this.poziciaY > ObrazovkaHry.VYSKA - 1) {
            this.poziciaY = 0;
        } else if (this.poziciaY < 0) {
            this.poziciaY = ObrazovkaHry.VYSKA - 1;
        }

    }

    /**
     *
     * @param batch
     */
    public void vykresli(SpriteBatch batch) {
        batch.draw(this.obrazok, this.poziciaX * 32, this.poziciaY * 32);
    }

}
