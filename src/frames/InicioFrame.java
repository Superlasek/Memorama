/*
* InicioFrame.java
* Author: Stephano Bravo
*/

package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class InicioFrame extends JFrame implements ActionListener{

    private static final long serialVersionUID = 1L;
    private JButton btnIniciar, btnOpciones, btnFinalizar;
    private JLabel lblMemorama;

    public InicioFrame(String title) {

        // Frame inicializacion
        setTitle(title);
        setSize(400, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Label "lblMemorama"
        lblMemorama = new JLabel("Memorama");
        lblMemorama.setBounds(170, 10, 100, 50);
        add(lblMemorama);

        // Botones
        btnIniciar = new JButton("Iniciar");
        btnIniciar.setBounds(100, 70, 200, 50);
        btnIniciar.addActionListener(this);

        btnOpciones = new JButton("Opciones");
        btnOpciones.setBounds(100, 130, 200, 50);
        btnOpciones.addActionListener(this);

        btnFinalizar = new JButton("Salir");
        btnFinalizar.setBounds(100, 190, 200, 50);
        btnFinalizar.addActionListener(this);

        // Añadir los botones al frame
        add(btnIniciar);
        add(btnOpciones);
        add(btnFinalizar);
        setVisible(true);

    }

    // Eventos de los botones
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnIniciar) {
            new JuegoFrame("Memorama | Juego");
            dispose();
        } else if(e.getSource() == btnOpciones) {
            new OpcionesFrame("Memorama | Opciones");
        } else if(e.getSource() == btnFinalizar) {
            dispose();
        }
    }
}
