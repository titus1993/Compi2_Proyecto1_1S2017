/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones_GraphiK;

import Ejecucion_GraphiK.Simbolo;
import java.util.ArrayList;

/**
 *
 * @author Titus
 */
public class Archivo {
    public String Nombre;
    public ArrayList<Simbolo> Als;
    public ArrayList<String> Haskell;
    public ArrayList<String> Graphik;
    public ArrayList<Archivo> importaciones;
    
    public Archivo(String nombre, ArrayList<Simbolo> als, ArrayList<String> haskell, ArrayList<String> graphik){
        this.Nombre = nombre;
        this.Als = als;
        this.Haskell = haskell;
        this.Graphik = graphik;
        this.importaciones = new ArrayList<>();
    }
}
