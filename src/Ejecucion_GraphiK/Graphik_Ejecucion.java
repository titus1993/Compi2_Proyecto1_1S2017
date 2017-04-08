/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejecucion_GraphiK;

import Analisis.Graphik.Graphik_Lexico;
import Analisis.Graphik.Graphik_Sintactico;
import Constante.Constante;
import Funciones_GraphiK.*;
import Interface.TitusNotificaciones;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Titus
 */
public class Graphik_Ejecucion {

    Archivo Archivo;
    ArrayList<Objeto> Objetos;
    Objeto Inicio;
    String Ruta;
    String Nombre;

    public Graphik_Ejecucion(Archivo archivo, String ruta) {
        this.Archivo = archivo;
        this.Objetos = new ArrayList<>();

        File file = new File(ruta);
        this.Ruta = file.getParent() + "\\";
        this.Nombre = file.getName();

        this.Archivo.Nombre = this.Nombre;

        try {
            if (!ControlArchivo.BuscarArchivo(Archivo.Nombre)) {
                ControlArchivo.InsertarArchivo(this.Archivo);
                
            }
            BuscarImportar();
        } catch (Exception ex) {
            Logger.getLogger(Graphik_Ejecucion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Ejecutar() {
        if (TitusNotificaciones.ContarErrores()) {
            
            Variable inicio = Inicio.TablaVariables.Tabla.get(0);
            
            if (inicio != null && inicio.Nombre == Constante.TInicio && inicio.Rol.equals(Constante.TMetodo)) {
                FMetodo Start = (FMetodo)inicio.Valor;
                FLlamadaMetodo m = new FLlamadaMetodo(Constante.TInicio, new ArrayList<FNodoExpresion>(), 0, 0, Constante.TInicio);
                Start.EjecutarMetodo(m, Inicio, inicio, Inicio, 1);
                Start.Ambito = Start.Ambito;
                Start.Ambito = Start.Ambito;
            } else {
                TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "No existe un metodo inicio", 0, 0);
            }
        }
    }


    public void BuscarImportar() throws Exception {
        for (String nombre : Archivo.Graphik) {
            String nuevaruta = Ruta + nombre + ".gk";

            if (!ControlArchivo.BuscarArchivo(nombre + ".gk")) {
                File selectedFile = new File(nuevaruta);

                if (selectedFile.exists() && TitusNotificaciones.ContarErrores()) {
                    BufferedReader in = new BufferedReader(new FileReader(selectedFile));

                    String cadena = "";
                    while (in.ready()) {
                        cadena += in.readLine() + "\n";
                    }
                    Graphik_Lexico scan = new Graphik_Lexico(new BufferedReader(new StringReader(cadena)));
                    Graphik_Sintactico parser = new Graphik_Sintactico(scan);
                    parser.parse();

                    if (parser.Ejecucion != null && TitusNotificaciones.ContarErrores()) {
                        Archivo archivo = parser.Ejecucion;
                        Graphik_Ejecucion ejecucion = new Graphik_Ejecucion(archivo, nuevaruta);

                        //Archivo.importaciones.add(ejecucion.Archivo);
                    } else {
                        JOptionPane.showMessageDialog(null, "Se encontraron errores.", "Error Graphik", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "No existe el archivo " + nombre + ".gk", 1, 1);
                }
            }
        }
    }

    //empezamos a cargar a memoria todo lo que traiga el primer archivo y vamos resolviendo las variables globales si tienen datos
    public void PrimerPasada() {
        Simbolo objeto = Archivo.Als.get(0);

        FAls als = (FAls) objeto.Valor;

        //creamos el objeto que tendra el inicio
        Objeto nuevoals = new Objeto(als);

        //apartamos el espacio para el metodo inicio
        //nuevoals.ApartarInicio();

        //primero metemos las variables y metodos del als
        
        this.Inicio = nuevoals;
    }
}
