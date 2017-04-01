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
public class FAsignacion {
    public Ambito Ambito;
    public Ambito Padre;
    public FArreglo Arreglo;
    public String TipoAsigna;
    public String Tipo;
    public FLlamadaObjeto Nombre;
    public FNodoExpresion Valor;

    public FAsignacion(String tipoasigna, String tipo , FLlamadaObjeto nombre, Ambito ambito, Object valor) {
        this.Ambito = ambito;
        this.Padre = null;
        this.TipoAsigna = tipoasigna;
        this.Nombre = nombre;
        this.Tipo = tipo;
        
        if(this.TipoAsigna.equals(Constante.Constante.TVariable)){
            this.Valor = (FNodoExpresion)valor;
        }else{
            this.Arreglo = (FArreglo)valor;
        }
    }
}
