/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones_GraphiK;

/**
 *
 * @author Titus
 */
public class FDonde {
    public String Nombre;
    public FNodoExpresion Donde;
    public FNodoExpresion Valor;
    public int Fila, Columna;
    
    public FDonde(String nombre, FNodoExpresion donde, FNodoExpresion val, int fila, int columna){
        this.Nombre = nombre;
        this.Donde = donde;
        this.Valor = val;
        this.Fila = fila;
        this.Columna = columna;
    }
}
