/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Analisis.Consola.Consola_Lexico;
import Analisis.Consola.Consola_Sintactico;
import Constante.Constante;
import Ejecucion_GraphiK.ControlArchivo;
import Ejecucion_Haskell.Haskell_Ejecucion;
import Ejecucion_Haskell.TablaHaskell;
import Ejecucion_Haskell.Variable;
import Funciones_Haskell.FFuncion;
import com.opencsv.CSVReader;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Titus
 */
public class TitusNotificaciones {

    public static JTextArea Consola = new JTextArea();
    public static JTextField Comando = new JTextField();
    public static TabControl ControlTab;
    public static JTable TablaErrores;
    public static DefaultTableModel Error;
    public static ChartPanel Grafica;
    public static JFreeChart Ejes;
    public static XYSeriesCollection ColeccionDatos;
    public static JPanel PanelGrafica;
    public static int contador = 0;
    public static JTree Arbol;
    public static DefaultMutableTreeNode Raiz;
    public static DefaultTableModel ModeloDatos;
    public static JTable Datos;
    public static JScrollPane DatosPanel = new JScrollPane();

    public TitusNotificaciones() {

    }

    public static void Iniciar(TabControl control) {
        ControlTab = control;
        IniciarConsola();
        IniciarErrores();
        IniciarGrafica();
        IniciarArbol();
    }

    public static void Limpiar() {
        LimpiarConsola();
        LimpiarGrafico();
        LimpiarTabla();
        ControlArchivo.LimpiarTabla();
        contador = 0;
    }

    public static void IniciarDatos() {
        DatosPanel.setName("Datos");
    }

    public static void CargarDatos(String archivo, JPanel contenedor) {

        String[] cadena = archivo.split("\n");
        int t = 0;

        String[] Titulo = null;
        String[] Contenido = null;
        ArrayList<String[]> a = new ArrayList<>();
        for (String fila : cadena) {
            if (t == 0) {
                Titulo = fila.split(",");
                t++;
                int i = 0;
                for (i = 0; i < Titulo.length; i++) {
                    Titulo[i] = Titulo[i].replace("[", "");
                    Titulo[i] = Titulo[i].replace("]", "");
                    Titulo[i] = Titulo[i].replace("{", "");
                    Titulo[i] = Titulo[i].replace("}", "");
                    Titulo[i] = Titulo[i].replace("\"", "");
                }
            } else {
                Contenido = fila.split(",");
                if (Titulo.length == Contenido.length) {
                    int i = 0;
                    for (i = 0; i < Titulo.length; i++) {
                        Contenido[i] = Contenido[i].replace("[", "");
                        Contenido[i] = Contenido[i].replace("]", "");
                        Contenido[i] = Contenido[i].replace("{", "");
                        Contenido[i] = Contenido[i].replace("}", "");
                        Contenido[i] = Contenido[i].replace("\"", "");
                    }
                    a.add(Contenido);
                }

            }
        }

        /* String[] columnas = {"Tipo de error", "Descripcion", "Linea", "Columna"};*/
        ModeloDatos = new DefaultTableModel(Titulo, 0);
        for (String[] c : a) {
            if (Titulo.length == c.length) {
                ModeloDatos.addRow(c);
            }
        }
        Datos = new JTable(ModeloDatos);
        DatosPanel = new JScrollPane(Datos);
        DatosPanel.setName("Datos");
        if (Interface.notificacion.getComponentCount() == 3) {
            Interface.notificacion.add(DatosPanel);
        } else {
            Interface.notificacion.remove(3);
            Interface.notificacion.add(DatosPanel);
        }
    }

    public static void IniciarArbol() {
        Raiz = new DefaultMutableTreeNode("Haskell");
        Arbol = new JTree(Raiz);

    }

    public static void InsertarHijoArbol() {
        Raiz.removeAllChildren();

        int j = 0;

        while (j < TablaHaskell.Tabla.size()) {
            Variable a = TablaHaskell.Tabla.get(j);
            if (a.Rol.equals(Constante.TMetodo)) {
                FFuncion f = (FFuncion) a.Valor;
                DefaultMutableTreeNode funcion = new DefaultMutableTreeNode(a.Nombre);
                int i = 0;
                while (i < f.Parametros.size()) {
                    DefaultMutableTreeNode parametro = new DefaultMutableTreeNode(f.Parametros.get(i).Nombre);
                    funcion.add(parametro);
                    i++;
                }
                Raiz.add(funcion);
            }
            j++;
        }
        int i = 0;
        while (i < Arbol.getRowCount()) {
            Arbol.expandRow(i);
            i++;
        }
        DefaultTreeModel model = (DefaultTreeModel) Arbol.getModel();
        model.reload();        
    }

    public static void IniciarGrafica() {
        ColeccionDatos = new XYSeriesCollection();
        Ejes = ChartFactory.createXYLineChart("Grafica", "X", "Y", ColeccionDatos, PlotOrientation.VERTICAL, true, true, false);
        Grafica = new ChartPanel(Ejes);
        PanelGrafica = new JPanel(new BorderLayout());
        PanelGrafica.setName("Grafica");
        PanelGrafica.add(Grafica, BorderLayout.CENTER);
        PanelGrafica.setVisible(true);
    }

    public static void InsertarGrafico(ArrayList<Double> x, ArrayList<Double> y) {
        int i = 0;
        XYSeries funcion = new XYSeries("f" + contador);
        while (i < x.size()) {
            funcion.add(x.get(i), y.get(i));
            i++;
        }
        ColeccionDatos.addSeries(funcion);
        contador++;
    }

    public static void LimpiarGrafico() {
        ColeccionDatos.removeAllSeries();
    }

    public static void IniciarConsola() {
        Consola.setBackground(Color.BLACK);
        Consola.setForeground(Color.WHITE);
        Consola.setFont(new Font("", Font.PLAIN, 18));
        Consola.setEditable(false);

        Comando.setBackground(Color.BLACK);
        Comando.setForeground(Color.WHITE);
        Comando.setFont(new Font("", Font.PLAIN, 18));

        Comando.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int tecla = e.getKeyCode();
                if (tecla == 10) {
                    //TitusTab aux = (TitusTab)ControlTab.getSelectedComponent();
                    //aqui va el codigo para ejecutar por tabs
                    TitusNotificaciones.ImprimirConsola(Comando.getText());
                    AnalizarHaskell();
                    Comando.setText("");
                }
            }
        });
    }

    public static void ImprimirConsola(String texto) {
        Consola.setText(Consola.getText() + ">" + texto + "\n");
    }

    public static void LimpiarConsola() {
        Consola.setText("");
        Comando.setText("");
    }

    public static void IniciarErrores() {
        String[] columnas = {"Tipo de error", "Descripcion", "Linea", "Columna"};
        Error = new DefaultTableModel(columnas, 0);
        TablaErrores = new JTable(Error);
    }

    public static void InsertarError(String tipo, String descripcion, int linea, int columna) {
        String[] error = {tipo, descripcion, String.valueOf(linea + 1), String.valueOf(columna + 1)};
        Error.addRow(error);
    }

    public static boolean ContarErrores() {
        return Error.getRowCount() == 0;
    }

    public static void LimpiarTabla() {
        while (Error.getRowCount() > 0) {
            Error.removeRow(0);
        }
    }

    public static void AnalizarHaskell() {
        try {
            TitusNotificaciones.LimpiarTabla();
            Consola_Lexico scan = new Consola_Lexico(new BufferedReader(new StringReader(Comando.getText())));
            //scan.yyreset(new BufferedReader(new StringReader(TextBox.getText())));
            Consola_Sintactico parser = new Consola_Sintactico(scan);
            parser.parse();

            if (parser.simbolo != null) {
                Haskell_Ejecucion ejecutar = new Haskell_Ejecucion(null);
                ejecutar.EjecutarConsola(parser.simbolo);
                parser.simbolo = null;
            } else {
                TitusNotificaciones.ImprimirConsola("Error, revisar la seccion de errores");
            }
        } catch (Exception ex) {
            Logger.getLogger(TitusTab.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
