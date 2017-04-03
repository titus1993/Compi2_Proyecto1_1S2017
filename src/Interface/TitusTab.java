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
import javax.swing.JOptionPane;

import Analisis.Haskell.Haskell_Sintactico;
import Analisis.Haskell.Haskell_Lexico;
import Analisis.Graphik.*;
import Ejecucion_GraphiK.*;
import Funciones_GraphiK.Archivo;
import Funciones_Haskell.FNodoExpresion;
import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;

/**
 *
 * @author Titus
 */
public class TitusTab extends JPanel {

    public RSyntaxTextArea TextBox;
    public String Nombre = "";
    public String Ruta = "";
    public String Tipo = "";

    public TitusTab(String nombre, String tipo, String texto, String ruta) {
        Nombre = nombre;
        Tipo = tipo;
        Ruta = ruta;
        this.setName(nombre);
        IniciarComponentes();
        TextBox.setText(texto);
    }

    private void IniciarComponentes() {
        this.setLayout(new BorderLayout());
        //TextLineNumber texto = new TextLineNumber(TextBox);
        //pane.setRowHeaderView(texto);
        TextBox = new RSyntaxTextArea("hol");
        TextBox.setCodeFoldingEnabled(true);
        RTextScrollPane pane = new RTextScrollPane(TextBox);

        if (Tipo.equals(Constante.Constante.Graphik)) {
            AbstractTokenMakerFactory atmf = (AbstractTokenMakerFactory) TokenMakerFactory.getDefaultInstance();
            atmf.putMapping("text/Graphik", "Interface.GraphikSyntax");

            TextBox.setSyntaxEditingStyle("text/Graphik");
            TextBox.setCurrentLineHighlightColor(new Color(227, 242, 253, 200));
            TextBox.setFadeCurrentLineHighlight(true);
            TextBox.setBorder(BorderFactory.createEmptyBorder());

            pane.setViewportBorder(BorderFactory.createEmptyBorder());
            SyntaxScheme scheme = TextBox.getSyntaxScheme();
            scheme.getStyle(Token.RESERVED_WORD).foreground = Color.BLUE;
            scheme.getStyle(Token.LITERAL_STRING_DOUBLE_QUOTE).foreground = Color.GREEN;
            scheme.getStyle(Token.IDENTIFIER).foreground = Color.ORANGE;
            scheme.getStyle(Token.COMMENT_EOL).foreground = Color.GRAY;
            scheme.getStyle(Token.COMMENT_MULTILINE).foreground = Color.GRAY;
            scheme.getStyle(Token.LITERAL_NUMBER_DECIMAL_INT).foreground = Color.BLACK;
            scheme.getStyle(Token.LITERAL_NUMBER_FLOAT).foreground = Color.BLACK;
            scheme.getStyle(Token.LITERAL_CHAR).foreground = Color.GREEN;
            scheme.getStyle(Token.RESERVED_WORD_2).foreground = Color.BLACK;
        } else {
            TextBox.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        }

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
                JOptionPane.showMessageDialog(this, "Se encontraron errores.", "Error Haskel", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            Logger.getLogger(TitusTab.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void prueba() {
        for (FNodoExpresion i = new FNodoExpresion(null); i.Numero < 10; i.Numero++) {

        }
    }

    public void AnalizarGraphik() {
        try {
            TitusNotificaciones.LimpiarTabla();
            ControlArchivo.LimpiarTabla();
            
            Graphik_Lexico scan = new Graphik_Lexico(new BufferedReader(new StringReader(TextBox.getText())));
            Graphik_Sintactico parser = new Graphik_Sintactico(scan);
            parser.archivonombre = Nombre;
            parser.parse();

            if (parser.Ejecucion != null && TitusNotificaciones.ContarErrores()) {
                Archivo archivo = parser.Ejecucion;
                TitusNotificaciones.LimpiarConsola();
                Graphik_Ejecucion ejecucion = new Graphik_Ejecucion(archivo, Ruta);
                
                ejecucion.PrimerPasada();                
                
                ejecucion.Ejecutar();
                if(!TitusNotificaciones.ContarErrores()){
                    JOptionPane.showMessageDialog(this, "Se encontraron errores.", "Error Graphik", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Se encontraron errores.", "Error Graphik", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            Logger.getLogger(TitusTab.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
