/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejecucion;

/**
 *
 * @author Titus
 */
public class Haskell_Ejecucion {
    public Ambito Ambito;
    public String Ruta;
    
    public Haskell_Ejecucion(Ambito ambito){
        this.Ambito = ambito;
    }
    
    public void LlenarTabla(){
        for(Simbolo sim: Ambito.TablaSimbolo){
            Variable var = new Variable(sim.Tipo, sim.Nombre, sim.Rol, sim.Fila, sim.Columna, sim.Ambito, sim.Valor);
            TablaHaskell.InsertarFuncion(var);
        }
    }
}
