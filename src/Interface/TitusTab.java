/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import org.fife.ui.rsyntaxtextarea.*;
import org.fife.ui.rtextarea.*;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.StringReader;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JOptionPane;

import Analisis.Haskell.Haskell_Sintactico;
import Analisis.Haskell.Haskell_Lexico;
import Analisis.Graphik.*;
import Funciones_Haskell.FNodoExpresion;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Titus
 */
public class TitusTab extends JPanel {

    public RSyntaxTextArea TextBox;
    public String Nombre = "";
    public String Ruta = "";
    public String Tipo = "";

    public TitusTab(String nombre, String tipo, String texto) {
        Nombre = nombre;
        Tipo = tipo;
        this.setName(nombre);
        IniciarComponentes();        
        TextBox.setText(texto);
    }

    private void IniciarComponentes() {
        this.setLayout(new BorderLayout());
        //TextLineNumber texto = new TextLineNumber(TextBox);
        //pane.setRowHeaderView(texto);
        TextBox = new RSyntaxTextArea("hol");
        TextBox.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        TextBox.setCodeFoldingEnabled(true);
        RTextScrollPane pane = new RTextScrollPane(TextBox);
        this.add(pane, BorderLayout.CENTER);
    }

    public void Analizar() {
        if (Tipo.equals(Constante.Constante.Graphik)) {
            AnalizarGraphik();
        } else {
            AnalizarHaskell();
        }
    }

    public void AnalizarHaskell() {
        try {
            TitusNotificaciones.LimpiarTabla();
            Haskell_Lexico scan = new Haskell_Lexico(new BufferedReader(new StringReader(TextBox.getText())));
            Haskell_Sintactico parser = new Haskell_Sintactico(scan);
            parser.parse();

            if (parser.Ejecucion != null) {
                if (JOptionPane.showConfirmDialog(this, "Desea cargar las funciones a memoria.", "Cargar funciones", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                    parser.Ejecucion.LlenarTabla();
                    TitusNotificaciones.ImprimirConsola("Funciones cargadas correctamente");

                }
            } else {

            }
        } catch (Exception ex) {
            Logger.getLogger(TitusTab.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void prueba(){
        for(FNodoExpresion i =new FNodoExpresion(null); i.Numero<10; i.Numero++){
            
        }
    }
    
    public void AnalizarGraphik() {
        try {
            TitusNotificaciones.LimpiarTabla();
            Graphik_Lexico scan = new Graphik_Lexico(new BufferedReader(new StringReader(TextBox.getText())));
            Graphik_Sintactico parser = new Graphik_Sintactico(scan);
            parser.parse();

            /*if (parser.Ejecucion != null) {
                if (JOptionPane.showConfirmDialog(this, "Desea cargar las funciones a memoria.", "Cargar funciones", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                    parser.Ejecucion.LlenarTabla();
                    TitusNotificaciones.ImprimirConsola("Funciones cargadas correctamente");

                }
            } else {
*/
        } catch (Exception ex) {
            Logger.getLogger(TitusTab.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
