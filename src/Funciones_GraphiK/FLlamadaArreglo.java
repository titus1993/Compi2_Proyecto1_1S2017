/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones_GraphiK;

import java.util.ArrayList;

/**
 *
 * @author Titus
 */
public class FLlamadaArreglo {
    String Nombre;
    ArrayList<FNodoExpresion> Dimensiones;
    
    public FLlamadaArreglo(String nombre, ArrayList<FNodoExpresion> dimensiones){
        this.Nombre = nombre;
        this.Dimensiones = dimensiones;
    }
}
