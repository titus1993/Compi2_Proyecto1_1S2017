/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import javax.swing.JTabbedPane;

/**
 *
 * @author Titus
 */
public class TabControl extends JTabbedPane{
    int cont = 1;
    
    public TabControl(){
        AgregarNuevaTab();
    }
    
    public void AgregarNuevaTab(){
        TitusTab tab = new TitusTab("Nuevo Documento " + cont);
        cont++;
        this.addTab(tab.Nombre, null, tab);
    }
    
}
