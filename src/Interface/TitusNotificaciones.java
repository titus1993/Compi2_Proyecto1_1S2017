/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Analisis.Consola.Consola_Lexico;
import Analisis.Consola.Consola_Sintactico;
import Ejecucion.Haskell_Ejecucion;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Titus
 */
public class TitusNotificaciones {
    public static JTextPane Consola = new JTextPane();
    public static JTextField Comando = new JTextField();
    public static TabControl ControlTab;
    public static JTable TablaErrores;
    public static DefaultTableModel Error;
    public TitusNotificaciones(){
        
    }
    public static void Iniciar(TabControl control){
        ControlTab = control;
        IniciarConsola();
        IniciarErrores();
    }
    
    public static void IniciarConsola(){
        Consola.setBackground(Color.black);
        Consola.setForeground(Color.WHITE);        
        Consola.setFont(new Font("", Font.PLAIN, 18));
        Consola.setEditable(false);
        
        Comando.setBackground(Color.BLACK);
        Comando.setForeground(Color.WHITE);
        Comando.setFont(new Font("", Font.PLAIN, 18));
       
        Comando.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e){
                int tecla = e.getKeyCode();
                if(tecla == 10){
                    //TitusTab aux = (TitusTab)ControlTab.getSelectedComponent();
                    //aqui va el codigo para ejecutar por tabs
                    TitusNotificaciones.ImprimirConsola(Comando.getText());                    
                    AnalizarHaskell();
                    Comando.setText("");
                }
            }
        });
    }
    
    public static void ImprimirConsola(String texto){
        Consola.setText(Consola.getText()+ ">" + texto + "\n");
    }
    
    public static void LimpiarConsola(){
        Consola.setText("");
        Comando.setText("");
    }
    
    public static void IniciarErrores(){
        String[] columnas = {"Tipo de error","Descripcion","Linea","Columna"};
        Error = new DefaultTableModel(columnas, 0);
        TablaErrores = new JTable(Error);        
    }
    
    public static void InsertarError(String tipo, String descripcion, int linea, int columna){
        String[] error = {tipo, descripcion, String.valueOf(linea), String.valueOf(columna)};
        Error.addRow(error);
    }
    
    public static void LimpiarTabla(){
        while(Error.getRowCount() > 0){
            Error.removeRow(0);
        }
    }
    
    public static void AnalizarHaskell(){
        try {
            TitusNotificaciones.LimpiarTabla();
            Consola_Lexico scan = new Consola_Lexico(new BufferedReader(new StringReader(Comando.getText())));
            //scan.yyreset(new BufferedReader(new StringReader(TextBox.getText())));
            Consola_Sintactico parser = new Consola_Sintactico(scan);
            parser.parse();
            
            
            if(parser.simbolo != null){
                Haskell_Ejecucion ejecutar = new Haskell_Ejecucion(null);
                ejecutar.EjecutarConsola(parser.simbolo);
                parser.simbolo = null;
            }else{                
                TitusNotificaciones.ImprimirConsola("Error, revisar la seccion de errores");
            }
        } catch (Exception ex) {
            Logger.getLogger(TitusTab.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
