/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones_GraphiK;

import Ejecucion_GraphiK.*;

/**
 *
 * @author Titus
 */
public class FPara {
    public Simbolo AccionSiguiente;
    public Simbolo AccionAnterior;
    public FNodoExpresion Condicion;
    public Ambito Ambito;
    
    public FPara(Simbolo anterior, FNodoExpresion condicion, Simbolo siguiente, Ambito ambito){
        this.AccionAnterior = anterior;
        this.Condicion = condicion;
        this.AccionSiguiente = siguiente;
        this.Ambito = ambito;
    }
}
