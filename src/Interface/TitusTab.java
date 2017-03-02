/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
/**
 *
 * @author Titus
 */
public class TitusTab extends JPanel {
    public JTextPane TextBox;
    public String Nombre = "";
    public String Ruta = "";
    
    public TitusTab(String nombre){
        Nombre = nombre;
        this.setName(nombre);
        IniciarComponentes();
    }    
    
    private void IniciarComponentes(){
        this.setLayout(new BorderLayout());
        TextBox = new JTextPane();
        JScrollPane pane = new JScrollPane(TextBox);
        this.add(pane, BorderLayout.CENTER);
    }
    
    
}
