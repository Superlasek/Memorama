/*
 * JuegoFrame.java
 * Author: Stephano Bravo
 */

package frames;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import stuff.ImagenesConnector;
import stuff.MemoriaBot;
import stuff.Randomizer;

public class JuegoFrame extends JFrame implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static ArrayList<JButton> buttons = new ArrayList<JButton>();
    public static ArrayList<Integer> integers = new ArrayList<Integer>();
    private int temp, temp2, puntuacion1 = 0, puntuacion2 = 0;
    public static int contador = 0;
    private boolean jugador1 = true, jugador2 = false;
    private JButton btnReiniciar, btnMenu;
    private JEditorPane jugador1Nombre, jugador2Nombre;
    private JLabel lbljugadorPuntacion1, lbljugadorPuntacion2, lblNombres, actualJugador, lblTextoJugador1, lblTextoJugador2;
    private Timer tw = new Timer();
    private MemoriaBot mb;
    private int dificultad = OpcionesFrame.diff;
    private boolean smart = OpcionesFrame.smart;

    private Icon iconr, icon1, icon2, icon3, icon4, icon5, icon6, icon7, icon8;

    public JuegoFrame(String title) {

        new ImagenesConnector();

        iconr = (Icon) new ImageIcon(ImagenesConnector.getImgURLr());
        icon1 = (Icon) new ImageIcon(ImagenesConnector.getImgURL1());
        icon2 = (Icon) new ImageIcon(ImagenesConnector.getImgURL2());
        icon3 = (Icon) new ImageIcon(ImagenesConnector.getImgURL3());
        icon4 = (Icon) new ImageIcon(ImagenesConnector.getImgURL4());
        icon5 = (Icon) new ImageIcon(ImagenesConnector.getImgURL5());
        icon6 = (Icon) new ImageIcon(ImagenesConnector.getImgURL6());
        icon7 = (Icon) new ImageIcon(ImagenesConnector.getImgURL7());
        icon8 = (Icon) new ImageIcon(ImagenesConnector.getImgURL8());

        // Inicializacion frame
        setTitle(title);
        setSize(1200, 900);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Ventana para introducir lblNombres de jugadores
/*        if (OpcionesFrame.bot) {
            if (OpcionesFrame.jugador) {
                String name = JOptionPane.showInputDialog("Introduce tu nombre");
                jugador1Nombre.setText(name);
            } else {
                String name = JOptionPane.showInputDialog("Introduce tu nombre");
                jugador2Nombre.setText(name);
            }
        } else {
            String name = JOptionPane.showInputDialog("Introduce el nombre del jugador 1");
            jugador1Nombre.setText(name);
            name = JOptionPane.showInputDialog("Introduce el nombre del jugador 2");
            jugador2Nombre.setText(name);
        }*/

        btnReiniciar = new JButton("Nueva partida");
        btnReiniciar.setBounds(920, 300, 200, 50);
        btnReiniciar.addActionListener(this);
        add(btnReiniciar);

        btnMenu = new JButton("Inicio");
        btnMenu.setBounds(920, 400, 200, 50);
        btnMenu.addActionListener(this);
        add(btnMenu);

        jugador1Nombre = new JEditorPane();
        jugador1Nombre.setBounds(920, 150, 150, 20);
        if (OpcionesFrame.bot) {
            if (OpcionesFrame.jugador) {
                jugador1Nombre.setText("Bot");
                jugador1Nombre.setEditable(false);
            }
        }
        add(jugador1Nombre);

        jugador2Nombre = new JEditorPane();
        jugador2Nombre.setBounds(920, 190, 150, 20);
        if (OpcionesFrame.bot) {
            if (!OpcionesFrame.jugador) {
                jugador2Nombre.setText("Bot");
                jugador2Nombre.setEditable(false);
            }
        }
        add(jugador2Nombre);

        lbljugadorPuntacion1 = new JLabel();
        lbljugadorPuntacion1.setBounds(1080, 150, 30, 20);
        lbljugadorPuntacion1.setText("" + 0);
        add(lbljugadorPuntacion1);

        lbljugadorPuntacion2 = new JLabel();
        lbljugadorPuntacion2.setBounds(1080, 190, 30, 20);
        lbljugadorPuntacion2.setText("" + 0);
        add(lbljugadorPuntacion2);

        lblNombres = new JLabel("Jugadores:");
        lblNombres.setBounds(920, 120, 100, 20);
        add(lblNombres);

        actualJugador = new JLabel(">");
        actualJugador.setBounds(900, 150, 20, 20);
        add(actualJugador);

        // Jugador 1
        lblTextoJugador1 = new JLabel("< Jugador 1 >");
        lblTextoJugador1.setBounds(920, 70, 150, 20);
        Font font = new Font("Arial", Font.BOLD, 20);
        lblTextoJugador1.setFont(font);
        lblTextoJugador1.setVisible(true);
        add(lblTextoJugador1);

        // Jugador 2
        lblTextoJugador2 = new JLabel("< Jugador 2 >");
        lblTextoJugador2.setBounds(920, 70, 150, 20);
        lblTextoJugador2.setFont(font);
        lblTextoJugador2.setVisible(false);
        add(lblTextoJugador2);


        new Randomizer(integers);

        for (int i = 0; i < 16; i++) {

            buttons.add(new JButton(iconr));

            if (i <= 3) {
                buttons.get(i).setBounds(20 + i * 220, 0, 200, 200);
            } else if (i <= 7) {
                buttons.get(i).setBounds(20 + (i - 4) * 220, 220, 200, 200);
            } else if (i <= 11) {
                buttons.get(i).setBounds(20 + (i - 8) * 220, 440, 200, 200);
            } else if (i <= 15) {
                buttons.get(i).setBounds(20 + (i - 12) * 220, 660, 200, 200);
            }

            buttons.get(i).addActionListener(this);

            add(buttons.get(i));
        }

        setVisible(true);

        if (OpcionesFrame.bot) {
            mb = new MemoriaBot(dificultad, buttons, smart);

            if (OpcionesFrame.jugador) {
                mb.play();
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnReiniciar) {

            if (OpcionesFrame.bot) {
                mb.delete();
                mb = new MemoriaBot(dificultad, buttons, smart);

                if (OpcionesFrame.jugador) {
                    mb.play();
                }
            }
            tw.cancel();
            tw = new Timer();
            new Randomizer(integers);
            for (int i = 0; i < integers.size(); i++) {
                buttons.get(i).setIcon(iconr);
                buttons.get(i).setEnabled(true);
            }
            contador = 0;

            jugador1 = true;
            jugador2 = false;

            actualJugador.setBounds(900, 150, 20, 20);
            lblTextoJugador1.setVisible(false);
            lblTextoJugador2.setVisible(false);

            puntuacion1 = 0;
            puntuacion2 = 0;
            lbljugadorPuntacion1.setText("" + puntuacion1);
            lbljugadorPuntacion2.setText("" + puntuacion2);
            setVisible(true);

        } else if (e.getSource() == btnMenu) {
            new InicioFrame("Inicio");
            if(OpcionesFrame.bot) {
                mb.delete();
            }
            buttons.clear();
            dispose();

        } else {
            for (int i = 0; i < integers.size(); i++) {
                if (e.getSource() == buttons.get(i)) {

                    if (contador == 0) {

                        contador = 1;
                        temp = integers.get(i);
                        temp2 = i;
                        if (integers.get(i) == 0) {
                            buttons.get(i).setIcon(icon1);
                        } else if (integers.get(i) == 1) {
                            buttons.get(i).setIcon(icon2);
                        } else if (integers.get(i) == 2) {
                            buttons.get(i).setIcon(icon3);
                        } else if (integers.get(i) == 3) {
                            buttons.get(i).setIcon(icon4);
                        } else if (integers.get(i) == 4) {
                            buttons.get(i).setIcon(icon5);
                        } else if (integers.get(i) == 5) {
                            buttons.get(i).setIcon(icon6);
                        } else if (integers.get(i) == 6) {
                            buttons.get(i).setIcon(icon7);
                        } else if (integers.get(i) == 7) {
                            buttons.get(i).setIcon(icon8);
                        } else if (integers.get(i) == 50) {
                            contador = 0;
                        }

                        if(OpcionesFrame.bot) {
                            if(dificultad == 1) {
                                if(isBot()) {
                                    mb.setNumber(i, integers.get(i));
                                }
                            } else if(dificultad >= 2){
                                mb.setNumber(i, integers.get(i));
                            }
                        }

                    } else if (contador == 1) {

                        contador = 2;

                        if (integers.get(i) == 0) {
                            buttons.get(i).setIcon(icon1);
                        } else if (integers.get(i) == 1) {
                            buttons.get(i).setIcon(icon2);
                        } else if (integers.get(i) == 2) {
                            buttons.get(i).setIcon(icon3);
                        } else if (integers.get(i) == 3) {
                            buttons.get(i).setIcon(icon4);
                        } else if (integers.get(i) == 4) {
                            buttons.get(i).setIcon(icon5);
                        } else if (integers.get(i) == 5) {
                            buttons.get(i).setIcon(icon6);
                        } else if (integers.get(i) == 6) {
                            buttons.get(i).setIcon(icon7);
                        } else if (integers.get(i) == 7) {
                            buttons.get(i).setIcon(icon8);
                        } else if (integers.get(i) == 50) {
                            contador = 1;
                        }

                        if(OpcionesFrame.bot) {
                            if(dificultad == 1) {
                                if(isBot()) {
                                    mb.setNumber(i, integers.get(i));
                                }
                            } else if(dificultad >= 2){
                                mb.setNumber(i, integers.get(i));
                            }
                        }

                        if (temp == integers.get(i)) {
                            if (!(temp2 == i)) {
                                if (!(integers.get(i) == 50)) {
                                    integers.set(i, 50);
                                    integers.set(temp2, 50);
                                    if (jugador1) {
                                        contador = 0;
                                        puntuacion1++;
                                        lbljugadorPuntacion1.setText("" + puntuacion1);
                                        setVisible(true);

                                        if (OpcionesFrame.bot) {
                                            mb.revelarCarta(buttons.get(i));
                                            mb.revelarCarta(buttons.get(temp2));

                                            if (isBot()) {
                                                if (puntuacion1 + puntuacion2 != 8) {
                                                    if(MemoriaBot.ganador1) {
                                                        MemoriaBot.ganador2 = true;
                                                    }
                                                    MemoriaBot.ganador1 = true;
                                                    mb.play();
                                                }
                                            }
                                        }
                                    }
                                    if (jugador2) {
                                        contador = 0;
                                        puntuacion2++;
                                        lbljugadorPuntacion2.setText("" + puntuacion2);
                                        setVisible(true);

                                        if (OpcionesFrame.bot) {
                                            mb.revelarCarta(buttons.get(i));
                                            mb.revelarCarta(buttons.get(temp2));

                                            mb.setNumber(i, 50);
                                            mb.setNumber(temp2, 50);

                                            if (isBot()) {
                                                if (puntuacion1 + puntuacion2 != 8) {
                                                    if(MemoriaBot.ganador1) {
                                                        MemoriaBot.ganador2 = true;
                                                    }
                                                    MemoriaBot.ganador1 = true;
                                                    mb.play();
                                                }
                                            }
                                        }
                                    }
                                    if (puntuacion1 + puntuacion2 == 8) {
                                        for (JButton jButton : buttons) {
                                            jButton.setEnabled(true);
                                        }

                                        if (puntuacion1 < puntuacion2) {
                                            JOptionPane.showMessageDialog(null,
                                                    jugador2Nombre.getText() + " ha ganado!", "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
                                        } else if (puntuacion2 < puntuacion1) {
                                            JOptionPane.showMessageDialog(null,
                                                    jugador1Nombre.getText() + " ha ganado!", "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
                                        } else if (puntuacion1 == puntuacion2) {
                                            JOptionPane.showMessageDialog(null, "empate!");
                                        }
                                    }
                                }
                            } else
                                contador = 1;
                        } else {

                            TimerTask tt = new TimerTask() {

                                @Override
                                public void run() {
                                    for (JButton b : buttons) {
                                        b.setEnabled(true);
                                    }
                                    contador = 0;
                                    for (int i = 0; i < integers.size(); i++) {
                                        if (!(integers.get(i) == 50)) {
                                            buttons.get(i).setIcon(iconr);
                                        }
                                    }
                                    if (jugador1) {
                                        jugador1 = false;
                                        jugador2 = true;

                                        actualJugador.setBounds(900, 190, 20, 20);
                                        lblTextoJugador2.setVisible(true);
                                        lblTextoJugador1.setVisible(false);
                                        setVisible(true);



                                        if (OpcionesFrame.bot) {
                                            if (!OpcionesFrame.jugador) {
                                                mb.play();
                                            }
                                        }

                                    } else if (jugador2) {
                                        jugador1 = true;
                                        jugador2 = false;

                                        actualJugador.setBounds(900, 150, 20, 20);
                                        lblTextoJugador1.setVisible(true);
                                        lblTextoJugador2.setVisible(false);
                                        setVisible(true);

                                        if (OpcionesFrame.bot) {
                                            if (OpcionesFrame.jugador) {
                                                mb.play();
                                            }
                                        }

                                    }

                                }
                            };
                            if (integers.get(i) == 50) {
                                contador = 1;
                            } else {
                                tw.schedule(tt, 1500);
                            }
                        }

                    }

                }

            }
        }

    }

    public boolean isBot() {
        if (jugador1 && OpcionesFrame.jugador) {
            return true;
        } else if (jugador2 && !OpcionesFrame.jugador) {
            return true;
        } else
            return false;

    }


}
