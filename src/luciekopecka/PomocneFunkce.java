package luciekopecka;

import java.util.Scanner;

public class PomocneFunkce {
    /**
     * Pomocí scanneru vrátí první nezakomentovaný řádek
     * @param reader Scanner který čte zadaný soubor
     * @return Řádek nebo null pokud žádný neexistuje
     */
    public static String getNextNonCommentLine(Scanner reader) {
        String returnData;
        for(int i = 0; i < 1000000; i++) {
            if(reader.hasNextLine()) {
                returnData = reader.nextLine();
                if(returnData.equals(""))
                    return returnData;
                if(returnData.charAt(0) == '#')
                    continue;
                return returnData;
            }
            else {
                return null;
            }
        }
        return null;
    }
    
    public static void setButtonVisibilty(javax.swing.JButton button, boolean visible) {
        button.setOpaque(visible);
        button.setContentAreaFilled(visible);
        button.setBorderPainted(visible);
        button.setText("");
    }
}

