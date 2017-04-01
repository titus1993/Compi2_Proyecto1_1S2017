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
public class FSi {
    public Ambito Si;
    public Ambito Sino;
    public Ambito Ambito;
    public FNodoExpresion Condicion;
    
    public FSi(FNodoExpresion condicion, Ambito si, Ambito sino, Ambito ambito){
        this.Si = si;
        this.Sino = sino;
        this.Condicion = condicion;
        this.Ambito = ambito;
    }
}
