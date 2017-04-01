/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejecucion_GraphiK;

import Funciones_GraphiK.Archivo;
import java.util.ArrayList;

/**
 *
 * @author Titus
 */
public class ControlArchivo {

    public static ArrayList<Archivo> Tabla = new ArrayList<>();

    public static void LimpiarTabla() {
        while (Tabla.size() > 0) {
            Tabla.remove(0);
        }
    }
    
    public static void InsertarArchivo(Archivo archivo){
        Tabla.add(archivo);
    }
    
    public static boolean BuscarArchivo(String nombre){
        boolean estado = false;
        for(Archivo archivo : Tabla){
            if(archivo.Nombre.equals(nombre)){
                estado = true;
                break;
            }
        }
        return estado;
    }
    
    public static Archivo ObtenerArchivo(String nombre){
        for(Archivo archivo : Tabla){
            if(archivo.Nombre.equals(nombre)){
                return archivo;
            }
        }
        return null;
    }
}
