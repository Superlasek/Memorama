/*
 * InicioFrame.java
 * Author: Stephano Bravo
 */

package stuff;

import java.util.ArrayList;

public class Randomizer {

    int contador;

    public Randomizer(ArrayList<Integer> al) {
        al.clear();
        for(al.size(); al.size() < 16;) {
            // Generar un numero aleatorio entre 0 y 7
            int r = (int) (Math.random() * 8);
            for(int i3 = 0; i3 < al.size(); i3++) {
                // Si el arraylist no esta vacio
                if(al.size() != 0) {
                    if(al.get(i3) != null) {
                        if(al.get(i3) == r) {
                            contador++;
                        }
                    }
                }
            }
            if(contador <= 1) {
                al.add(r);
                contador = 0;
            } else contador = 0;
        }
    }
}