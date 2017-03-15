/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejecucion;

/**
 *
 * @author Titus
 */
public class Simbolo {
    public int Fila, Columna;
    public String Nombre, Rol, Tipo;
    public Ambito Ambito;
    public Object Valor;
    
    
    public Simbolo(String tipo, String nombre, String rol, int fila, int columna, Ambito ambito, Object valor){     
        this.Tipo = tipo;
        this.Nombre = nombre;
        this.Rol = rol;
        this.Fila = fila;
        this.Columna = columna;
        this.Ambito = ambito;
        this.Valor = valor;
    }
}
