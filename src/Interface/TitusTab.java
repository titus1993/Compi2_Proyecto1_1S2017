/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.StringReader;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JOptionPane;

import Analisis.Haskell.Haskell_Sintactico;
import Analisis.Haskell.Haskell_Lexico;
import java.awt.Component;
import java.util.ArrayList;
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
        //TextLineNumber texto = new TextLineNumber(TextBox);
        //pane.setRowHeaderView(texto);
        TextBox = new JTextPane();
        JScrollPane pane = new JScrollPane(TextBox);
        this.add(pane, BorderLayout.CENTER);
    }

    public void Analizar() {
        AnalizarHaskell();
    }

    public void AnalizarHaskell(){
        try {
            
            Haskell_Lexico scan = new Haskell_Lexico(new BufferedReader(new StringReader(TextBox.getText())));
            //scan.yyreset(new BufferedReader(new StringReader(TextBox.getText())));
            Haskell_Sintactico parser = new Haskell_Sintactico(scan);
            parser.parse();
            
            if(parser.Ejecucion != null){
                if(JOptionPane.showConfirmDialog(this, "Desea cargar las funciones a memoria.", "Cargar funciones", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
                    parser.Ejecucion.LlenarTabla();
                    TitusNotificaciones.ImprimirConsola("Funciones cargadas correctamente");
                    
                }
            }else{
                
            }
        } catch (Exception ex) {
            Logger.getLogger(TitusTab.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
