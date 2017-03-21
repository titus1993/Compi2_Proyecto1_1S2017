/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones_Haskell;

import Ejecucion_Haskell.Ambito;
import java.util.ArrayList;

/**
 *
 * @author Titus
 */
public class FCase {
    public Ambito Ambito;
    public Ambito Padre;
    public ArrayList<FCaso> Casos;
    public FNodoExpresion Condicion;
    
    public FCase(FNodoExpresion condicion, ArrayList<FCaso> casos, Ambito ambito, Ambito padre){
        this.Condicion = condicion;
        this.Casos = casos;
        this.Ambito = ambito;
        this.Padre = padre;
    }
}
