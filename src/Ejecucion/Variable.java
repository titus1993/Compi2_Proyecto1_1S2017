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
public class Variable {
    public String Tipo, Nombre, Rol;
    public int Fila, Columna;
    public Object valor;
    //public Ambito Ambito;
    
    public Variable(){
        this.Tipo = "";
        this.Nombre = "";
        this.Rol = "";
        this.Fila = 0;
        this.Columna = 0;
        //this.Ambito = null;
    }
}
