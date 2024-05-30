package luciekopecka;

import java.util.ArrayList;
import java.util.Scanner;

public class Otazka {
    protected String textOtazky;
    protected int hodnota;
    protected ArrayList<Odpoved> odpovedi;

    public Otazka() {
        this.odpovedi = new ArrayList<Odpoved>();
    }

    public Otazka(String textOtazky, int hodnota, ArrayList<Odpoved> odpovedi) {
        this.textOtazky = textOtazky;
        this.hodnota = hodnota;
        this.odpovedi = odpovedi;
    }

    public String getTextOtazky() {
        return textOtazky;
    }

    public void setTextOtazky(String textOtazky) {
        this.textOtazky = textOtazky;
    }

    public int getHodnota() {
        return hodnota;
    }

    public void setHodnota(int hodnota) {
        this.hodnota = hodnota;
    }

    public ArrayList<Odpoved> getOdpovedi() {
        return odpovedi;
    }
    
    public int getCorrectOdpovedIndex() {
        for(int i = 0; i < this.odpovedi.size(); i++) {
            if(this.odpovedi.get(i).isSpravne()) {
                return i;
            }
        }
        return -1;
    }

    public void setOdpovedi(ArrayList<Odpoved> odpovedi) {
        this.odpovedi = odpovedi;
    }

    /**
     * Nastaví pomocí Scanneru text, hodnotu i odpovědi otázky
     * @param reader Scanner který čte zadaný soubor
     * @return Pokud se ze Scanneru podařilo načíst true a pokud ne false
     */
    public boolean setOtazkaFromScanner(Scanner reader) {
        String data = PomocneFunkce.getNextNonCommentLine(reader);
        if(!this.setTextOtazkyFromInput(data))
            return false;
        data = PomocneFunkce.getNextNonCommentLine(reader);
        if(!this.setHodnotaFromInput(data))
            return false;
        for(int i = 0; i < 1000; i++) {
            Odpoved odpoved = new Odpoved();
            if(!odpoved.setOdpovedFromScanner(reader))
                return (i > 1 && this.areOdpovediRight());
            else
                this.odpovedi.add(odpoved);

        }
        return true;
    }

    /**
     * Pokusí se nastavit text otázky podle zadaného řádku
     * @param inputRadek Řádek s textem otázky ze Scanneru
     * @return Pokud se z řádku podařilo načíst true a pokud ne false
     */
    public boolean setTextOtazkyFromInput(String inputRadek) {
        if(inputRadek == null)
            return false;
        String[] arrayInput = inputRadek.split(":");
        if(arrayInput.length == 2 && arrayInput[0].contains("Otázka 1zN")) {
            this.textOtazky = arrayInput[1];
            return true;
        }
        return false;
    }

    /**
     * Pokusí se nastavit hodnotu otázky podle zadaného řádku
     * @param inputRadek Řádek s hodnotou otázky ze Scanneru
     * @return Pokud se z řádku podařilo načíst true a pokud ne false
     */
    public boolean setHodnotaFromInput(String inputRadek) {
        if(inputRadek == null)
            return false;
        String[] arrayInput = inputRadek.split(":");
        if(arrayInput.length == 2 && arrayInput[0].equals("Hodnota")) {
            try {
                this.hodnota = Integer.parseInt(arrayInput[1]);
                return true;
            }
            catch (java.lang.NumberFormatException e) {
            }
        }
        return false;
    }

    /**
     * Otestuje, zda se u otázky vyskytuje přesně jedna správná odpoveď a více než jedna špatná odpověď
     * @return Správná forma odpověďí true a špatná false
     */
    public boolean areOdpovediRight() {
        int countTrue = 0, countFalse = 0;
        for(Odpoved odpoved : this.odpovedi) {
            if(odpoved.isSpravne())
                countTrue++;
            else
                countFalse++;
        }
        return (this.odpovedi.size() <=6 && countTrue == 1 && countFalse > 0);
    }
}

