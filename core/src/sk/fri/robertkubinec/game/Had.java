
package sk.fri.robertkubinec.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.Iterator;
import sk.fri.robertkubinec.game.jedlo.Blesk;
import sk.fri.robertkubinec.game.jedlo.Ceresna;
import sk.fri.robertkubinec.game.jedlo.Jablko;
import sk.fri.robertkubinec.game.jedlo.Slimak;

/**
 *
 * @author Robert Kubinec
 */
public class Had {
    
    /**
     * Atributy triedy Had 
     */

    private ArrayList<JednoPolicko> had;
    private SmerHadika smer;
    private float rychlost;
    private boolean zjedene;
    private ObrazovkaHry hra;
    private Texture koniec;
    private boolean koniecHry;
    private int skore;

    /**
     * Konstruktor triedy Had
     * Inicializacia atributov
     * @param hra
     */
    
    public Had(ObrazovkaHry hra) {
        this.had = new ArrayList<JednoPolicko>();
        this.smer = SmerHadika.DOHORA; //smer hadika na zaciatku pri spusteni hry
        this.rychlost = 5; //pociatocna rychlost hadika na zaciatku hry
        this.zjedene = false;
        this.hra = hra;
        this.koniecHry = false;
        this.skore = 0; //pociatocne skore na zaciatku hry
        
        /**
        * Vzhlad hadika na zaciatku hry
        * Hadik sa sklada z hlavy a jedneho clanku tela na zaciatku hry
        */
        
        this.had.add(new HlavaHadika(5, 5)); //hlava hadika na zaciatku hry
        this.had.add(new TeloHadika(5, 4)); //jeden clanok tela na zaciatku hry

    }

    /**
     *
     * @return
     */
    public ArrayList<JednoPolicko> getHad() {
        return this.had;
    }

    /**
     *
     * @return
     */
    public SmerHadika getSmer() {
        return this.smer;
    }

    /**
     *
     * @param had
     */
    public void setHad(ArrayList<JednoPolicko> had) {
        this.had = had;
    }

    /**
     *
     * @param smer
     */
    public void setSmer(SmerHadika smer) {
        this.smer = smer;
    }

    /**
     *
     * @param batch
     */
    public void vykresli(SpriteBatch batch) {
        for (JednoPolicko policko : this.had) {
            policko.vykresli(batch);
        }

    }
    /**
     * Metoda pohniSa
     * Vzdy sa posledny clanok hada presunie na poziciu hlavy hada
     */
    public void pohniSa() {
        JednoPolicko poslednyClanok = this.had.remove(this.had.size() - 1);
        this.had.add(1, poslednyClanok);
        JednoPolicko hlavaHadika = this.had.get(0);
        poslednyClanok.setPoziciaX(hlavaHadika.getPoziciaX());
        poslednyClanok.setPoziciaY(hlavaHadika.getPoziciaY());
        //Pohyb hlavy hada 4 zakladnymi smermi, hlavu potom nasleduju clanky hada
        switch (this.smer) {
            case DOHORA:
                hlavaHadika.pohniSa(0, 1);
                break;
            case DODOLA:
                hlavaHadika.pohniSa(0, -1);
                break;
            case DOPRAVA:
                hlavaHadika.pohniSa(1, 0);
                break;
            case DOLAVA:
                hlavaHadika.pohniSa(-1, 0);
                break;

        }
        
        /**
         * Iterator prechadza celu kolekciu jedlo
         */
        
        Iterator<Jedlo> iteratorJedla = this.hra.getJedlo().iterator();
        while (iteratorJedla.hasNext()) {
            Jedlo jedlo = iteratorJedla.next();
            //Zistujeme ci je hlava hada na rovnakej pozicii ako vykreslene jedlo
            if (hlavaHadika.getPoziciaX() == jedlo.getPoziciaX() && hlavaHadika.getPoziciaY() == jedlo.getPoziciaY()) {
                //Ak hlava hada prejde cez texturu jablka alebo ceresne, had sa predlzi
                if (jedlo instanceof Jablko || jedlo instanceof Ceresna) {
                    this.predlzSa(jedlo);
                    //Ak hlava hada prejde cez texturu blesku, had sa predlzi a zrychli
                } else if (jedlo instanceof Blesk) {
                    this.predlzSa(jedlo);
                    this.zrychli();
                    //Ak hlava hada prejde cez texturu slimaka, had sa predlzi a spomali
                } else if (jedlo instanceof Slimak) {
                    this.predlzSa(jedlo);
                    this.spomal();

                }
                //Ak hlava hada prejde cez texturu jedla, dana textura sa na danej pozicii vymaze
                iteratorJedla.remove();
                this.zjedene = true;

            }

        }

        if (this.zjedene) {
            this.hra.pridajJedlo();
            this.zjedene = false;
        }
        for (JednoPolicko policko : this.had) {
            if (policko.equals(hlavaHadika)) {
                continue;
            }

            /**
             * Ak sa hlava hada dostane do kolizie s inou castou hada, hra skonci
             * V podstate hra konci, ak je hlava hada na rovnakej pozicii s inym polickom hada
             */
            if (policko.getPoziciaX() == hlavaHadika.getPoziciaX()
                    && policko.getPoziciaY() == hlavaHadika.getPoziciaY()) {
                System.out.println("GAME OVER");
                System.out.println("PRE UKONCENIE HRY ZAVRI OKNO KRIZIKOM! ");
                this.koniecHry = true;

            }

        }
    }

    /**
     *
     * @return
     */
    public boolean isKoniecHry() {
        return this.koniecHry;
    }

    /**
     *
     * @return
     */
    public float getRychlost() {
        return this.rychlost;
    }

    /**
     * Metoda predlzSa
     * Na koniec hada sa prida novy clanok
     * @param ceresna
     */
    
    public void predlzSa(Jedlo ceresna) {
        JednoPolicko poslednyClanok = this.had.get(this.had.size() - 1);
        this.had.add(new TeloHadika(poslednyClanok.getPoziciaX(), poslednyClanok.getPoziciaY()));
        this.rychlost = 5;
        //zakazdym, ked had zje jedlo sa pripocita skore o 1
        this.skore++;
    }

    /**
     * Metoda getSkore
     * Metoda vracia aktualne skore
     * @return 
     */
    public int getSkore() {
        return this.skore;
    }

     /**
     * Metoda zrychli
     * Metoda,v ktorej nastavujeme rychlost na vacsiu, ako je povodna rychlost
     */
    public void zrychli() {
        this.rychlost = 10;
    }
    
    /**
     * Metoda zrychli
     * Metoda,v ktorej nastavujeme rychlost na mensiu, ako je povodna rychlost
     */
    public void spomal() {
        this.rychlost = 2;
    }

}
