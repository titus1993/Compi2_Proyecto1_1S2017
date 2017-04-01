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
public class FHacerMientras {
    public Ambito Ambito;
    public FNodoExpresion Condicion;
   
    public FHacerMientras(FNodoExpresion condicion, Ambito ambito){
        this.Ambito = ambito;
        this.Condicion = condicion;
    }
}
