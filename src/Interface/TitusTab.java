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
import Constante.Constante;
import Ejecucion_GraphiK.*;
import Funciones_GraphiK.Archivo;
import Funciones_Haskell.FNodoExpresion;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Titus
 */
public class TitusTab extends JPanel {

    public RSyntaxTextArea TextBox;
    public String Nombre = "";
    public String Ruta = "";
    public String Tipo = "";
    public boolean Modificado = false;
    public JFileChooser jFileChooser1 = new JFileChooser();

    public TitusTab(String nombre, String tipo, String texto, String ruta) {
        Nombre = nombre;
        Tipo = tipo;
        Ruta = ruta;
        this.setName(nombre);
        IniciarComponentes();
        TextBox.setText(texto);
        this.TextBox.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                Modificado = true;
            }

            public void removeUpdate(DocumentEvent e) {
                Modificado = true;
            }

            public void insertUpdate(DocumentEvent e) {
                Modificado = true;
            }

        });

        jFileChooser1.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter;
        jFileChooser1.setDialogTitle("Guardar Como");
        if (tipo.equals(Constante.Haskell)) {
            filter = new FileNameExtensionFilter("Haskell++", "hk");
        } else {
            filter = new FileNameExtensionFilter("GraphiK", "gk");
        }
        jFileChooser1.setFileFilter(filter);
    }

    private void IniciarComponentes() {
        this.setLayout(new BorderLayout());
        //TextLineNumber texto = new TextLineNumber(TextBox);
        //pane.setRowHeaderView(texto);
        TextBox = new RSyntaxTextArea("hol");
        TextBox.setCodeFoldingEnabled(true);
        RTextScrollPane pane = new RTextScrollPane(TextBox);

        if (Tipo.equals(Constante.Graphik)) {
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
        try {
            if (guardarArchivo()) {
                if (Tipo.equals(Constante.Graphik)) {
                    AnalizarGraphik();
                } else {
                    AnalizarHaskell();
                    TitusNotificaciones.InsertarHijoArbol();
                }
                if(TitusNotificaciones.ContarErrores()){
                    JOptionPane.showMessageDialog(this, "Analisis Finalizado ;)", "Exito", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TitusTab.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TitusTab.class.getName()).log(Level.SEVERE, null, ex);
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

    public boolean guardarArchivo() throws FileNotFoundException, UnsupportedEncodingException, IOException {
        boolean estado = false;
        if (this.Ruta.isEmpty()) {
            if (jFileChooser1.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                String ruta = jFileChooser1.getSelectedFile().toString();
                if (Tipo.equals(Constante.Graphik)) {
                    if (!ruta.endsWith(".gk")) {
                        ruta += ".gk";
                    }
                } else {
                    if (!ruta.endsWith(".hk")) {
                        ruta += ".hk";
                    }
                }
                Writer w = new BufferedWriter(new FileWriter(ruta));
                w.write(this.TextBox.getText());
                w.close();
                estado = true;
                Modificado = false;
                Ruta = ruta;
                Path p = Paths.get(ruta);
                Nombre = p.getFileName().toString();
                this.setName(Nombre);
                this.repaint();
            }
        } else {
            Writer w = new BufferedWriter(new FileWriter(Ruta));
            w.write(this.TextBox.getText());
            w.close();
            estado = true;
            Modificado = false;
        }

        return estado;
    }

    public void guardarComoArchivo() throws IOException {
        if (jFileChooser1.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String ruta = jFileChooser1.getSelectedFile().toString();
            if (Tipo.equals(Constante.Graphik)) {
                if (!ruta.endsWith(".gk")) {
                    ruta += ".gk";
                }
            } else {
                if (!ruta.endsWith(".hk")) {
                    ruta += ".hk";
                }
            }
            Writer w = new BufferedWriter(new FileWriter(ruta));
            w.write(this.TextBox.getText());
            w.close();
            Modificado = false;
            Ruta = ruta;
            Path p = Paths.get(ruta);
            Nombre = p.getFileName().toString();
            this.setName(Nombre);
            this.repaint();            
        }
    }

    public void AnalizarGraphik() {
        try {
            TitusNotificaciones.Limpiar();

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
                if (!TitusNotificaciones.ContarErrores()) {
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
