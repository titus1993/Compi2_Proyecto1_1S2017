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
        //AgregarNuevaTab();
    }
    
    public void AgregarNuevaTab(String nombre, String tipo, String texto){
        TitusTab tab;
        if(tipo.equals(Constante.Constante.Graphik)){
            tab = new TitusTab(nombre, tipo, texto);
        }else{
            tab = new TitusTab(nombre, tipo, texto);
        }
        this.addTab(tab.Nombre, null, tab);
        this.setSelectedIndex(this.getComponentCount()-1);
    }
    
    public void AgregarNuevaTab(String tipo){
        TitusTab tab;
        if(tipo.equals(Constante.Constante.Graphik)){
            tab = new TitusTab("Nuevo Documento " + cont+".gk", tipo, "");
        }else{
            tab = new TitusTab("Nuevo Documento " + cont+".hk", tipo, "");
        }
        
        cont++;
        this.addTab(tab.Nombre, null, tab);
        this.setSelectedIndex(this.getComponentCount()-1);
    }
    
    public void CerrarTab(){
        TitusTab tab = (TitusTab) getSelectedComponent();
        if (tab != null) {
            System.out.println(tab.Nombre);
            remove(getSelectedIndex());
        }
    }
    public void Analizar(){
        TitusTab tab = (TitusTab) getSelectedComponent();
        if (tab != null) {
            tab.Analizar();
        }
    }
}
