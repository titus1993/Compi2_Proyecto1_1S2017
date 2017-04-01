/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones_GraphiK;

import Ejecucion_GraphiK.Ambito;

/**
 *
 * @author Titus
 */
public class FAls {
    public Ambito Ambito;
    public String Herencia;
    public String Nombre;
    public Archivo ArchivoPadre;
    
    public FAls(String nombre, String herencia, Ambito ambito){
        this.Nombre = nombre;
        this.Herencia = herencia;
        this.Ambito = ambito;
    }
}
