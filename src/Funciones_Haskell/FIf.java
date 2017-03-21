/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones_Haskell;

import Ejecucion_Haskell.Ambito;

/**
 *
 * @author Titus
 */
public class FIf {
    public FNodoExpresion Condicion;
    public Ambito Ambito;
    public Ambito Padre;
    public Ambito Else;
    
    public FIf(FNodoExpresion condicion, Ambito ambito, Ambito sino){
        this.Condicion = condicion;
        this.Ambito = ambito;
        this.Else = sino;
    }
            
}
