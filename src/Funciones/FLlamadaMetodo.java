/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import java.util.ArrayList;

/**
 *
 * @author Titus
 */
public class FLlamadaMetodo {
    public String Nombre;
    public int Fila, Columna;
    public ArrayList<FNodoExpresion> Parametros = new ArrayList<>();
    
    public FLlamadaMetodo(String nombre, ArrayList<FNodoExpresion> parametros, int fila, int columna){
        this.Nombre = nombre;
        this.Parametros = parametros;
        this.Fila  = fila;
        this.Columna = columna;
    }
    
    public void InsertarParametro(FNodoExpresion parametro){
        Parametros.add(parametro);        
    }
}
