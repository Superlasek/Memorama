/*
 * OpcionesFrame.java
 * Author: Stephano Bravo
 */

package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class OpcionesFrame extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JLabel lblBot, lblJugarBot, lblBotDificultad, lblNivel;
    private String[] Stringlist = { "Frutas", "Animales", "Chisyosos"};
    private String[] DificultadList = { "Fácil", "Medio", "Difícil", "Muy difícil", "Imposible"};
    private JComboBox<String> list, list2;
    private JButton btnConfirmar;
    public static String seleccion = "Frutas";
    public static int diff = 0;
    public static boolean smart = false;
    public static boolean bot = false, jugador = false;
    private JRadioButton rbtnCheck1, rbtnCheck2, rbtnCheck3, rbtnCheck4, rbtnCheck5, rbtnCheck6;

    public OpcionesFrame(String title) {

        setTitle(title);
        setSize(500, 500);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(150, 350, 200, 50);
        btnConfirmar.addActionListener(this);
        add(btnConfirmar);

        // Label de seleccion de tema
        lblNivel = new JLabel("Selecciona el tema");
        lblNivel.setBounds(150, 20, 200, 20);
        add(lblNivel);

        list = new JComboBox<String>(Stringlist);
        list.setBounds(150, 50, 200, 30);
        list.addActionListener(this);
        switch (seleccion) {
            case "Frutas":
                list.setSelectedIndex(0);
                break;
            case "Animales":
                list.setSelectedIndex(1);
                break;
            case "Chisyosos":
                list.setSelectedIndex(2);
            case "Conrad":
                list.setSelectedIndex(3);
                break;
            default:
                list.setSelectedIndex(0);
        }
        add(list);

        list2 = new JComboBox<String>(DificultadList);
        list2.setBounds(150, 250, 200, 30);
        list2.addActionListener(this);
        /*switch (diff) {
            case 0:
                list2.setSelectedIndex(0);
                break;
            case 1:
                list2.setSelectedIndex(1);
                break;
            case 2:
                list2.setSelectedIndex(2);
                break;
            case 3:
                list2.setSelectedIndex(3);
                break;
            default:
                list2.setSelectedIndex(0);
        } */
        list2.setSelectedIndex(diff);
        add(list2);

        lblBot = new JLabel("Bot");
        lblBot.setBounds(150, 100, 100, 20);
        add(lblBot);

        lblBotDificultad = new JLabel("Dificultad");
        lblBotDificultad.setBounds(150, 220, 100, 20);
        add(lblBotDificultad);

        // Boton para activar inteligencia artificial
        rbtnCheck1 = new JRadioButton("Sí");
        rbtnCheck1.setBounds(150, 130, 50, 20);
        rbtnCheck1.addActionListener(this);
        if (bot) {
            rbtnCheck1.setSelected(true);
        }
        add(rbtnCheck1);

        rbtnCheck2 = new JRadioButton("No");
        rbtnCheck2.setBounds(200, 130, 60, 20);
        if (!bot) {
            rbtnCheck2.setSelected(true);
        }
        rbtnCheck2.addActionListener(this);
        add(rbtnCheck2);

        lblJugarBot = new JLabel("¿Bot como jugador?");
        lblJugarBot.setBounds(150, 160, 200, 20);
        add(lblJugarBot);

        rbtnCheck3 = new JRadioButton("1");
        rbtnCheck3.setBounds(150, 190, 40, 20);
        rbtnCheck3.addActionListener(this);
        if (jugador) {
            rbtnCheck3.setSelected(true);
        }
        add(rbtnCheck3);

        rbtnCheck4 = new JRadioButton("2");
        rbtnCheck4.setBounds(200, 190, 60, 20);
        if (!jugador) {
            rbtnCheck4.setSelected(true);
        }
        rbtnCheck4.addActionListener(this);
        add(rbtnCheck4);

        rbtnCheck5 = new JRadioButton("Inteligente");
        rbtnCheck5.setBounds(150, 290, 100, 20);
        if (smart) {
            rbtnCheck5.setSelected(true);
        }
        add(rbtnCheck5);

        rbtnCheck6 = new JRadioButton("Ingenuo");
        rbtnCheck6.setBounds(250, 290, 80, 20);
        if(!smart) {
            rbtnCheck6.setSelected(true);
        }
        add(rbtnCheck6);

        if(diff != 0) {
            rbtnCheck5.setEnabled(true);
            rbtnCheck6.setEnabled(true);
        } else {
            rbtnCheck5.setEnabled(false);
            rbtnCheck6.setEnabled(false);
        }

        ButtonGroup group = new ButtonGroup();
        ButtonGroup group2 = new ButtonGroup();
        ButtonGroup group3 = new ButtonGroup();

        group.add(rbtnCheck1);
        group.add(rbtnCheck2);

        group2.add(rbtnCheck3);
        group2.add(rbtnCheck4);

        group3.add(rbtnCheck5);
        group3.add(rbtnCheck6);

        setVisible(true);

    }

    // Los eventos de los radio buttons y botones
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnConfirmar) {
            dispose();
            seleccion = (String) list.getSelectedItem();
            diff = list2.getSelectedIndex();
            if (rbtnCheck1.isSelected()) {
                bot = true;
            } else {
                bot = false;
            }
            if (rbtnCheck3.isSelected()) {
                jugador = true;
            } else {
                jugador = false;
            }
            if (rbtnCheck5.isSelected()) {
                smart = true;
            } else {
                smart = false;
            }
        } else if(e.getSource() == list2) {
            if(list2 != null) {
                if(rbtnCheck5 != null && rbtnCheck6 != null) {
                    if(list2.getSelectedIndex() != 0) {
                        rbtnCheck5.setEnabled(true);
                        rbtnCheck6.setEnabled(true);
                    } else {
                        rbtnCheck5.setEnabled(false);
                        rbtnCheck6.setEnabled(false);
                    }
                } else {
                    // PROBANDO...
                    System.out.println("rbtnCheck5 o rbtnCheck6 es null");
                }
            }
        }
    }

}
