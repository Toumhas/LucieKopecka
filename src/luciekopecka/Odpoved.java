package luciekopecka;

import java.util.Scanner;

public class Odpoved {
    protected String textOdpovedi;
    protected boolean spravne;

    public Odpoved(String textOdpovedi, boolean spravne) {
        this.textOdpovedi = textOdpovedi;
        this.spravne = spravne;
    }

    public Odpoved() {

    }

    public String getTextOdpovedi() {
        return textOdpovedi;
    }

    public void setTextOdpovedi(String textOdpovedi) {
        this.textOdpovedi = textOdpovedi;
    }

    public boolean isSpravne() {
        return spravne;
    }

    public void setSpravne(boolean spravne) {
        this.spravne = spravne;
    }

    /**
     * Nastaví pomocí Scanneru text a správnost otázky
     * @param reader Scanner který čte zadaný soubor
     * @return Pokud se ze Scanneru podařilo načíst true a pokud ne false
     */
    public boolean setOdpovedFromScanner(Scanner reader) {
        String data = PomocneFunkce.getNextNonCommentLine(reader);
        return this.setTextAndSpravneFromInput(data);
    }

    /**
     * Pokusí se nastavit text i správnost odpovědi ze zadaného řádku
     * @param inputRadek Řádek s textem a správností otázky
     * @return Pokud se z řádku podařilo načíst true a pokud ne false
     */
    public boolean setTextAndSpravneFromInput(String inputRadek) {
        if(inputRadek == null)
            return false;
        String[] arrayInput = inputRadek.split(":");
        if(arrayInput.length == 2) {
            this.textOdpovedi = arrayInput[1];
            if(arrayInput[0].equals("Správně")) {
                this.spravne = true;
                return true;
            }
            if(arrayInput[0].equals("Špatně")) {
                this.spravne = false;
                return true;
            }
        }
        return false;
    }
}
