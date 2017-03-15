/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import Constante.Constante;

/**
 *
 * @author Titus
 */
public class FNodoExpresion {

    public FNodoExpresion Izquierda, Derecha;
    public String Tipo, Nombre, Cadena;
    public int Fila, Columna;
    public double Numero;
    public boolean Bool;
    public char Caracter;
    public FLlamadaMetodo Metodo;
    public FArreglo Arreglo;

    public FNodoExpresion(FNodoExpresion izq, FNodoExpresion der, String tipo, String nombre, int fila, int columna, Object valor) {
        this.Izquierda = izq;
        this.Derecha = der;
        this.Tipo = tipo;
        this.Nombre = nombre;
        this.Fila = fila;
        this.Columna = columna;
        
        switch (tipo) {
            case Constante.TDecimal:
                this.Numero = Double.parseDouble(valor.toString());
                break;

            case Constante.TCaracter:
                this.Caracter = (char)valor;
                break;
                
            case Constante.TCadena:
                this.Cadena = (String)valor;
                break;
                
            case Constante.TBool:
                this.Bool = Boolean.parseBoolean(valor.toString());
                break;
                
            case Constante.TMetodo:
                this.Metodo = (FLlamadaMetodo)valor;
                break;
                
            case Constante.TArreglo:
                this.Arreglo = (FArreglo)valor;
                break;
            
        }
    }
    
    public FNodoExpresion(){
        
        this.Izquierda = null;
        this.Derecha = null;
        this.Tipo = "";
        this.Nombre = "";
        this.Cadena = "";
        this.Fila = 0;
        this.Columna = 0;
        this.Numero = 0;
        this.Bool = false;
        this.Caracter = '\0';
        this.Metodo = null;
    }

}
