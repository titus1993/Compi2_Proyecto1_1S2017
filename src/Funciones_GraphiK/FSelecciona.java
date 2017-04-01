/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones_GraphiK;

import java.util.ArrayList;
import Ejecucion_GraphiK.*;
/**
 *
 * @author Titus
 */
public class FSelecciona {
    public ArrayList<FCaso> Casos;
    public Ambito Ambito;    
    public FCaso Defecto;
    
    public FSelecciona(ArrayList<FCaso> casos, FCaso defecto, Ambito ambito){
        this.Casos = casos;
        this.Defecto = defecto;
        this.Ambito = ambito;
    }
}
