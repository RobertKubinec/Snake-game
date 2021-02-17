package sk.fri.robertkubinec.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import java.util.ArrayList;
import java.util.Random;
import sk.fri.robertkubinec.game.jedlo.Blesk;
import sk.fri.robertkubinec.game.jedlo.Ceresna;
import sk.fri.robertkubinec.game.jedlo.Jablko;
import sk.fri.robertkubinec.game.jedlo.Slimak;

/**
 *
 * @author Robert Kubinec
 */
public class ObrazovkaHry extends ScreenAdapter {

    /**
     * Atributy triedy ObrazovkaHry
     */
    public static final int SIRKA = 20;
    public static final int VYSKA = 15;
    private SpriteBatch batch;
    private SpriteBatch batch2;
    private Texture pozadie;
    private Texture koniec;
    private Music hudba;
    private Had had;
    private float cas = 0;
    private int zjedene;
    private ArrayList<Jedlo> jedlo;
    private BitmapFont text;

    /**
     * Konstruktor triedy ObrazovkaHry
     */
    public ObrazovkaHry() {

        this.pozadie = new Texture("background.jpg"); //inicializacia hlavneho pozadia hry
        this.koniec = new Texture("gameover.png"); //inicializacia pozadia pri skonceni hry
        this.batch = new SpriteBatch(); //vytvorenie hlavneho platna
        this.batch2 = new SpriteBatch(); //vytvorenie platna pre koniec hry
        this.jedlo = new ArrayList<>();
        this.had = new Had(this);
        this.pridajJedlo();

    }

    /**
     * V metode render sa nastavuju zakladne vlastnosti hernej obrazovky
     *
     * @param delta
     */
    @Override
    public void render(float delta) {
        this.cas += delta;
        this.text = new BitmapFont();
        this.batch.begin();
        this.batch.draw(this.pozadie, 0, 0); //vykreslenie hlavnej obrazovky
        this.text.draw(this.batch, "Skore : " + this.had.getSkore(), 20, 20); //zobrazenie nahraneho skore na hlavnej obrazovke
        this.hudba = Gdx.audio.newMusic(Gdx.files.internal("snakemusic.mp3")); //pridane hudby do hry 
        float hlasitost = (float)0.05; //intenzita hlasitosti hudby v hre
        this.hudba.setVolume(hlasitost); //nastavenie hlasitosti v hre
        this.hudba.setLooping(true); //nastavenie opakovania skladby v hre
        this.hudba.play(); //spustenie skladby zaroven pri spusteni hernej obrazovky

        /**
         * Ak had nabura do svojho tela, hra sa skonci. Vykresli sa obrazovka
         * pre koniec hry
         */
        if (this.had.isKoniecHry()) {
            this.batch2.begin(); //inicializacia obrazovky pre koniec hry
            this.batch2.draw(this.koniec, 60, 0); //vykresli sa obrazovka pre koniec hry
            this.text.draw(this.batch2, "Skore : " + this.had.getSkore(), 20, 20); //na obrazovke pre koniec hry sa zobrazi skore, ktore hrac dosiahol
            this.hudba.pause();
            this.batch2.end();

            /**
             * Ovladanie pohybu hada po hlavnej obrazovke hry. Priradenie
             * ovladania smeru sipkam na klavesnici.
             */
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.had.setSmer(SmerHadika.DOHORA);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.had.setSmer(SmerHadika.DODOLA);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.had.setSmer(SmerHadika.DOPRAVA);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.had.setSmer(SmerHadika.DOLAVA);
        }

        if (this.cas > 1 / this.had.getRychlost()) {
            this.cas = 0;

            this.had.pohniSa();

        }
        //Vykresli sa had na hracej obrazovke
        this.had.vykresli(this.batch);
        //Vykresli sa jedlo na hracej obrazovke
        for (Jedlo jedlo : this.jedlo) {
            jedlo.vykresli(this.batch);
        }

        this.batch.end();

    }

    /**
     * Metoda pridajJedlo pridava jedlo do hlavnej obrazovky hry. Jedlo je po
     * hlavnej obrazovke hry rozmiestnene nahodne
     */
    public void pridajJedlo() {
        Random r = new Random();
        int o = r.nextInt(5);
        int s = r.nextInt(ObrazovkaHry.SIRKA);
        int v = r.nextInt(ObrazovkaHry.VYSKA);

        switch (o) {

            case 1:
                this.jedlo.add(new Ceresna(s, v));
                break;
            case 2:
                this.jedlo.add(new Blesk(s, v));
                break;
            case 3:
                this.jedlo.add(new Slimak(s, v));
                break;
            default:
                this.jedlo.add(new Jablko(s, v));
                break;
        }
    }

    /**
     *
     * @return
     */
    public ArrayList<Jedlo> getJedlo() {
        return this.jedlo;
    }

    @Override
    public void dispose() {
        this.pozadie.dispose();
        this.batch.dispose();
        this.batch2.dispose();
    }

}
