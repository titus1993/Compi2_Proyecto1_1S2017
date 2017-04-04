/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.swing.JTabbedPane;

/**
 *
 * @author Titus
 */
public class TabControl extends JTabbedPane {

    int cont = 1;

    public TabControl() {
        //AgregarNuevaTab();
    }

    public void AgregarNuevaTab(String nombre, String tipo, String texto, String ruta) {
        TitusTab tab;
        if (tipo.equals(Constante.Constante.Graphik)) {
            tab = new TitusTab(nombre, tipo, texto, ruta);
        } else {
            tab = new TitusTab(nombre, tipo, texto, ruta);
        }
        this.addTab(tab.Nombre, null, tab);
        this.setSelectedIndex(this.getComponentCount() - 1);
    }

    public void AgregarNuevaTab(String tipo) {
        TitusTab tab;
        if (tipo.equals(Constante.Constante.Graphik)) {
            tab = new TitusTab("Nuevo Documento " + cont + ".gk", tipo, "", "");
        } else {
            tab = new TitusTab("Nuevo Documento " + cont + ".hk", tipo, "", "");
        }

        cont++;
        this.addTab(tab.Nombre, null, tab);
        this.setSelectedIndex(this.getComponentCount() - 1);
    }

    public void CerrarTab() {
        TitusTab tab = (TitusTab) getSelectedComponent();
        if (tab != null) {
            System.out.println(tab.Nombre);
            remove(getSelectedIndex());
        }
    }

    public void Analizar() {
        TitusTab tab = (TitusTab) getSelectedComponent();
        if (tab != null) {
            tab.Analizar();
        }
    }

    public boolean guardarTab() throws UnsupportedEncodingException, IOException {
        boolean estado = false;
        TitusTab tab = (TitusTab) getSelectedComponent();
        if (tab != null) {
            estado = tab.guardarArchivo();
        }
        Titular();
        this.repaint();
        this.updateUI();

        return estado;
    }

    public void guardarTabComo() throws UnsupportedEncodingException, IOException {

        TitusTab tab = (TitusTab) getSelectedComponent();
        if (tab != null) {
            tab.guardarComoArchivo();
        }
        Titular();
        this.repaint();
        this.updateUI();
    }

    public void Titular() {
        int i = 0;
        while (i < this.getComponentCount()) {
            TitusTab tab = (TitusTab) getComponent(i);
            this.setTitleAt(i, tab.Nombre);
            i++;
        }
    }
}
