/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejecucion_GraphiK;

/**
 *
 * @author Titus
 */
public class Variable {
    public String Tipo, Nombre, Rol, Visibilidad, Lenguaje;
    public int Fila, Columna;
    public Ambito Ambito;
    public Object Valor;
    public Objeto Padre;
    
    public Variable(String lenguaje, String visibilidad, String tipo, String nombre, String rol, int fila, int columna, Ambito ambito, Object valor, Objeto padre){
        this.Visibilidad = visibilidad;
        this.Tipo = tipo;
        this.Nombre = nombre;
        this.Rol = rol;
        this.Fila = fila;
        this.Columna = columna;
        this.Ambito = ambito;
        this.Valor = valor;        
        this.Lenguaje = lenguaje;
        this.Padre = padre;
    }
}
