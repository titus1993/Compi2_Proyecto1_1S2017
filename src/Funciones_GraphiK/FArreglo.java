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
public class FArreglo {
    public FNodoExpresion Arreglo = null;
    public ArrayList<FNodoExpresion> Dimensiones = null;    
    
    public String Nombre, Tipo;
    
    public FArreglo(String tipo, String nombre, ArrayList<FNodoExpresion> dimensiones, FNodoExpresion arreglo){
        this.Tipo = tipo;
        this.Nombre = nombre;
        this.Dimensiones = dimensiones;
        this.Arreglo = arreglo;
    }
}
