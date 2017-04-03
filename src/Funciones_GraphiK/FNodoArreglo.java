/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones_GraphiK;

import java.util.ArrayList;

/**
 *
 * @author Titus
 */
public class FNodoArreglo {
    public ArrayList<FNodoExpresion> Arreglo = null;
    public int Dimensiones;
    public String Tipo;

    public FNodoArreglo(ArrayList<FNodoExpresion> arreglo) {
        this.Arreglo = arreglo;
        this.Dimensiones = arreglo.size();
        //this.Tipo = ObtenerTipo(arreglo);
        //ComprobarNiveles(this.Arreglo);
    }
}
