package luciekopecka;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Dotaznik {
    protected ArrayList<Otazka> otazky;
    protected String nazev = null;

    public Dotaznik() {
        this.otazky = new ArrayList<Otazka>();
    }

    public Dotaznik(ArrayList<Otazka> otazky, String nazev) {
        this.otazky = otazky;
        this.nazev = nazev;
    }

    /**
     * Pokusí se načíst celý dotazník pomocí zadaného souboru
     * @param filePath Cesta k souboru s dotazníkem
     * @return Pokud se ze souboru podařilo načíst true a pokud ne false
     */
    public int nactiSoubor(String filePath) {
        try {
            File inputFile = new File(filePath);
            Scanner reader = new Scanner(inputFile);
            String data = PomocneFunkce.getNextNonCommentLine(reader);
            if(!this.setNazevFromInput(data))
                return -1;
            for(int i = 0; i < 1000; i++) {
                Otazka otazka = new Otazka();
                if(!otazka.setOtazkaFromScanner(reader))
                    return (i > 0) ? 1 : -1;
                else
                    this.otazky.add(otazka);
            }
            return -1;
        }
        catch(FileNotFoundException e) {
            return -2;
        }
    }

    /**
     * Vypíše do konzole postupně všechny otázky z dotazníku i s jejich odpověďmi
     */
    public void vypsatDoKonzole() {
        System.out.println("Název:" + this.nazev);
        for (Otazka currentOtazka : this.otazky) {
            System.out.println("Otázka:" + currentOtazka.getTextOtazky());
            System.out.println("Hodnota:" + currentOtazka.getHodnota());
            ArrayList<Odpoved> currentOdpovedi = currentOtazka.getOdpovedi();
            for (Odpoved odpoved : currentOdpovedi) {
                if (odpoved.isSpravne())
                    System.out.println("Správně:" + odpoved.getTextOdpovedi());
                else
                    System.out.println("Špatně:" + odpoved.getTextOdpovedi());
            }
            System.out.print("\n");
        }
    }

    public ArrayList<Otazka> getOtazky() {
        return otazky;
    }
    
    public Otazka getOtazkaOnIndex(int index) {
        return otazky.get(index);
    }

    public void setOtazky(ArrayList<Otazka> otazky) {
        this.otazky = otazky;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    /**
     * Zamíchá všechny otázky i odpovědi dotazníku
     */
    public void randomizeOtazky() {
        for(Otazka otazka : this.otazky) {
            Collections.shuffle(otazka.odpovedi);
        }
        Collections.shuffle(this.otazky);
    }

    /**
     * Pokusí se nastavit název dotazníku podle zadaného řádku
     * @param inputRadek Řádek s názvem dotazníku ze Scanneru
     * @return Pokud se z řádku podařilo načíst true a pokud ne false
     */
    public boolean setNazevFromInput(String inputRadek) {
        if(inputRadek == null)
            return false;
        String[] arrayInput = inputRadek.split(":");
        if(arrayInput.length == 2 && arrayInput[0].equals("Název")) {
            this.nazev = arrayInput[1];
            return true;
        }
        return false;
    }
}
