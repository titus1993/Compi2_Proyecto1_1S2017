/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.StringReader;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import Analisis.Haskell.Haskell_Sintactico;
import Analisis.Haskell.Haskell_Lexico;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Titus
 */
public class TitusTab extends JPanel {

    public JTextPane TextBox;
    public String Nombre = "";
    public String Ruta = "";

    public TitusTab(String nombre) {
        Nombre = nombre;
        this.setName(nombre);
        IniciarComponentes();
    }

    private void IniciarComponentes() {
        this.setLayout(new BorderLayout());
        TextBox = new JTextPane();
        JScrollPane pane = new JScrollPane(TextBox);
        this.add(pane, BorderLayout.CENTER);
    }

    public void Analizar() {
        AnalizarHaskell();
    }

    public void AnalizarHaskell(){
        try {
            System.out.println("Inicia Analisis");
            Haskell_Lexico scan = new Haskell_Lexico(new BufferedReader(new StringReader(TextBox.getText())));
            Haskell_Sintactico parser = new Haskell_Sintactico(scan);
            parser.parse();
            System.out.println("Finaliza la generación de C3D...");
        } catch (Exception ex) {
            Logger.getLogger(TitusTab.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
