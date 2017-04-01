/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones_GraphiK;

import Ejecucion_GraphiK.Ambito;

/**
 *
 * @author Titus
 */
public class FDeclaracion {

    public Ambito Ambito;
    public Ambito Padre;
    public FArreglo Arreglo;
    public String Tipo, Nombre, Visibilidad;
    public String TipoDecla;
    public FNodoExpresion Valor;

    public FDeclaracion(String visibilidad, String tipodecla, String tipo, String nombre, Ambito ambito, Object valor) {
        this.Visibilidad = visibilidad;
        this.Ambito = ambito;
        this.Padre = null;
        this.Tipo = tipo;
        this.Nombre = nombre;
        this.TipoDecla = tipodecla;
        
        if(this.TipoDecla.equals(Constante.Constante.TVariable)){
            this.Valor = (FNodoExpresion)valor;
        }else{
            this.Arreglo = (FArreglo)valor;
        }
    }
}
