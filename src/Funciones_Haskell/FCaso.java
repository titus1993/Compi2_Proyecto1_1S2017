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
public class FCaso {
    public FNodoExpresion Valor;
    public Ambito Ambito;
    public Ambito Padre;
    
    public FCaso(FNodoExpresion valor, Ambito ambito){
        this.Valor = valor;
        this.Ambito = ambito;
        this.Padre = null;
    }
}
