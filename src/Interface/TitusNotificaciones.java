/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 *
 * @author Titus
 */
public class TitusNotificaciones {
    public static JTextPane Consola = new JTextPane();
    public static JTextField Comando = new JTextField();
    public static TabControl ControlTab;
        
    public TitusNotificaciones(){
        
    }
    public static void Iniciar(TabControl control){
        ControlTab = control;
        IniciarConsola();
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
                    TitusTab aux = (TitusTab)ControlTab.getSelectedComponent();
                    //aqui va el codigo para ejecutar por tabs
                    
                    ImprimirConsola(aux.TextBox.getText());
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
    
    
}
