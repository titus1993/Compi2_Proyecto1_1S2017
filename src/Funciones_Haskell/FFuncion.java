/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones_Haskell;

import Ejecucion_Haskell.Ambito;
import Ejecucion_Haskell.Simbolo;
import java.util.ArrayList;

/**
 *
 * @author Titus
 */
public class FFuncion {
    public Ambito Ambito;
    public ArrayList<Simbolo> Parametros;
    
    public FFuncion(ArrayList<Simbolo> parametros, Ambito ambito){
        this.Parametros = parametros;
        this.Ambito = ambito;
    }
}
