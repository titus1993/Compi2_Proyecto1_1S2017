/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejecucion_Haskell;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Titus
 */
public class TablaVariables {
    public static List<Variable> Tabla = new LinkedList<Variable>();
    
    public TablaVariables(){
        
    }
    
    public static void InsertarVariable(Variable var){
        Tabla.add(var);
    }
}
