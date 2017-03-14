/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import java.util.ArrayList;

/**
 *
 * @author Titus
 */
public class FLlamadaMetodo {
    public String Nombre;
    public ArrayList<FNodoExpresion> Parametros = new ArrayList<>();
    
    public FLlamadaMetodo(String nombre){
        this.Nombre = nombre;
    }
    
    public void InsertarParametro(FNodoExpresion parametro){
        Parametros.add(parametro);
    }
}
