/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import java.util.ArrayList;
import Constante.Constante;
/**
 *
 * @author Titus
 */
public class FArreglo {
    ArrayList<FNodoExpresion> Arreglo = null;
    
    public FArreglo(ArrayList<FNodoExpresion> arreglo){
        this.Arreglo = arreglo;
    }
    
    public FArreglo(String cadena, int fila, int columna){
        cadena = cadena.substring(1, cadena.length() - 1);
        for(char c: cadena.toCharArray()){
            this.Arreglo.add(new FNodoExpresion(null, null, Constante.TCaracter, Constante.TCaracter , fila, columna, c));
            fila++;
            columna++;
        }
    }
}
