/*
 * InicioFrame.java
 * Author: Stephano Bravo
 */

package stuff;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;

import frames.JuegoFrame;

public class MemoriaBot {

    private int dificultad;
    private ArrayList<Integer> numeros = new ArrayList<>();
    private ArrayList<JButton> buttons;
    public static ArrayList<JButton> revelar = new ArrayList<>(), libre = new ArrayList<>();
    private static JButton clickeado = null;
    private static int temporal = -1;
    public static boolean ganador1 = false;
    public static boolean ganador2 = false;
    private boolean smort = false;

    public MemoriaBot(int dificultad, ArrayList<JButton> buttons, boolean smart) {
        this.dificultad = dificultad;
        this.buttons = buttons;
        this.smort = smart;
        
        libre.addAll(buttons);

        for (int i = 0; i < 16; i++) {
            numeros.add(-1);
        }
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public void play() {
        if (dificultad == 0) {
            if (JuegoFrame.contador == 0) {
                for (JButton b : buttons) {
                    b.setEnabled(false);
                }
                PrimerIntentoFacil();
            } else if (JuegoFrame.contador == 1) {
                SegundoIntentoFacil();
            }
        } else if (dificultad == 1) {
            if (JuegoFrame.contador == 0) {
                for (JButton b : buttons) {
                    b.setEnabled(false);
                }
                PrimerIntentoMedio();
            } else if (JuegoFrame.contador == 1) {
                SegundoIntentoMedio();
            }
        } else if (dificultad == 2) {
            if (JuegoFrame.contador == 0) {
                for (JButton b : buttons) {
                    b.setEnabled(false);
                }
                PrimerIntentoDificil();
            } else if (JuegoFrame.contador == 1) {
                SegundoIntentoDificl();
            }
        } else if (dificultad == 3) {
            if (JuegoFrame.contador == 0) {
                for (JButton b : buttons) {
                    b.setEnabled(false);
                }
                PrimerIntentoMuyDificil();
            } else if (JuegoFrame.contador == 1) {
                SegundoIntentoMuyDificil();
            }
        } else if(dificultad == 4) {
            if (JuegoFrame.contador == 0) {
                for (JButton b : buttons) {
                    b.setEnabled(false);
                }
                PrimerIntentoImposible();
            } else if (JuegoFrame.contador == 1) {
                SegundoIntentoImposible();
            }
        }

    }

    public void setNumber(int position, int number) {
        numeros.set(position, number);
    }

    public ArrayList<Integer> getnumeros() {
        return numeros;
    }

    public int getNumber(int position) {
        return numeros.get(position);
    }

    public void PrimerIntentoFacil() {
        int rnd = (int) (Math.random() * libre.size());

        JButton b = libre.get(rnd);


        if (!revelar.contains(b)) {
            b.setEnabled(true);
            b.doClick();
            clickeado = b;
            if(smort) {
                temporal = numeros.get(buttons.indexOf(b));
            }
        }
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {

            @Override
            public void run() {
                play();
            }
        };
        t.schedule(tt, 1500);

    }

    public void PrimerIntentoMedio() {
        boolean b = false;
        int number = -1;
        temporal = -1;

        for (int i : numeros) {
            if (i != -1 && i != 50) {
                if (numeros.indexOf(i) != numeros.lastIndexOf(i)) {
                    b = true;
                    number = i;
                }
            }
        }
        if (b) {

            JButton bt = buttons.get(numeros.indexOf(number));
            // numeros.set(numeros.indexOf(number), -1);
            temporal = number;
            bt.setEnabled(true);
            bt.doClick();

            clickeado = bt;

            Timer t = new Timer();
            TimerTask tt = new TimerTask() {

                @Override
                public void run() {
                    play();
                }
            };
            t.schedule(tt, 1500);

        } else {
            PrimerIntentoFacil();
        }

    }

    public void PrimerIntentoDificil() {
        PrimerIntentoMedio();
    }

    public void PrimerIntentoMuyDificil() {
        if(!ganador1) {
            PrimerIntentoFacil();
        } else {
            PrimerIntentoDificil();
        }
    }

    public void PrimerIntentoImposible() {
        if(!ganador1 || !ganador2) {
            PrimerIntentoFacil();
        } else {
            PrimerIntentoDificil();
        }
    }

    public void SegundoIntentoFacil() {
        int rnd = (int) (Math.random() * libre.size());
        JButton b = libre.get(rnd);

        if (libre.size() == 1) {
            libre.get(0).setEnabled(true);
            libre.get(0).doClick();
        }

        if (!revelar.contains(b)) {
            if (b != clickeado) {
                b.setEnabled(true);
                b.doClick();
            } else
                SegundoIntentoFacil();
        } else
            SegundoIntentoFacil();
    }

    public void SegundoIntentoMedio() {
        if (temporal != -1) {
            if (numeros.lastIndexOf(temporal) != -1 && !revelar.contains(buttons.get(numeros.lastIndexOf(temporal)))
                    && numeros.indexOf(temporal) != numeros.lastIndexOf(temporal)) {
                JButton b2 = buttons.get(numeros.lastIndexOf(temporal));
                JButton b1 = buttons.get(numeros.indexOf(temporal));
                JButton b = null;
                if(b1 != clickeado) {
                    b = b1;
                } else if(b2 != clickeado) {
                    b = b2;
                }

                if (b != clickeado) {
                    b.setEnabled(true);
                    b.doClick();

                    if (numeros.contains(temporal)) {
                        numeros.set(numeros.indexOf(temporal), -1);
                        if (numeros.contains(temporal)) {
                            numeros.set(numeros.lastIndexOf(temporal), -1);
                        }
                    }
                    temporal = -1;
                    clickeado = null;
                }
            } else {
                SegundoIntentoFacil();
            }

        } else {
            SegundoIntentoFacil();
        }

    }

    public void SegundoIntentoDificl() {
        SegundoIntentoMedio();
    }

    public void SegundoIntentoMuyDificil() {
        if(!ganador1) {
            int index = JuegoFrame.integers.indexOf(JuegoFrame.integers.get(buttons.indexOf(clickeado)));
            int lastindex = JuegoFrame.integers.lastIndexOf(JuegoFrame.integers.get(index));

            JButton jb1 = buttons.get(index);
            JButton jb2 = buttons.get(lastindex);

            if (clickeado == jb1) {
                jb2.setEnabled(true);
                jb2.doClick();
            } else if(clickeado == jb2) {
                jb1.setEnabled(true);
                jb1.doClick();
            }
        } else {
            ganador1 = false;
            SegundoIntentoDificl();
        }
    }

    public void SegundoIntentoImposible() {
        if(!ganador1 || !ganador2) {
            int index = JuegoFrame.integers.indexOf(JuegoFrame.integers.get(buttons.indexOf(clickeado)));
            int lastindex = JuegoFrame.integers.lastIndexOf(JuegoFrame.integers.get(index));

            JButton jb1 = buttons.get(index);
            JButton jb2 = buttons.get(lastindex);

            if (clickeado == jb1) {
                jb2.setEnabled(true);
                jb2.doClick();
            } else if(clickeado == jb2) {
                jb1.setEnabled(true);
                jb1.doClick();
            }
        } else {
            ganador1 = false;
            ganador2 = false;
            SegundoIntentoDificl();
        }
    }

    // Revelar carta
    public void revelarCarta(JButton jb) {
        libre.remove(jb);
        revelar.add(jb);
    }

    // Limpiar todo
    public void delete() {
        numeros.clear();
        dificultad = 0;
        revelar.clear();
        libre.clear();
        clickeado = null;
        buttons = null;
    }
}
