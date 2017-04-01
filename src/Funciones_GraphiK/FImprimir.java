/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones_GraphiK;

import Constante.Constante;
import Ejecucion_GraphiK.Ambito;
import Ejecucion_GraphiK.Objeto;
import Ejecucion_GraphiK.Simbolo;
import Interface.TitusNotificaciones;
import java.util.ArrayList;

/**
 *
 * @author Titus
 */
public class FImprimir {
    public Ambito Ambito;
    public FNodoExpresion Valor;
    
    public FImprimir(FNodoExpresion valor){
        this.Ambito = new Ambito(Constante.TImprimir, new ArrayList<Simbolo>());
        this.Valor = valor;
    }
    
    public void Imprimir(Objeto tabla){
        FNodoExpresion solucion = Valor.ResolverExpresion(tabla);
        if(TitusNotificaciones.ContarErrores()){
            if(solucion.Tipo.equals(Constante.TCadena)){
                TitusNotificaciones.ImprimirConsola(solucion.Cadena);
            }else{
                TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Se esperaba un tipo String", solucion.Fila, solucion.Columna);
            }
        }
        
    }
}
