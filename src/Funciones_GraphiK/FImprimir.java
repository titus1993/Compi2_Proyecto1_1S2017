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
    public int Fila, Columna;
    
    public FImprimir(FNodoExpresion valor){
        this.Ambito = new Ambito(Constante.TImprimir, new ArrayList<Simbolo>());
        this.Valor = valor;
    }
    
    public void Imprimir(Objeto tabla, Objeto padre){
        FNodoExpresion solucion = Valor.ResolverExpresion(padre, 1);
        if(solucion.Tipo.equals(Constante.TVariableArreglo)){
            solucion = solucion.PosArreglo;
        }
        if(solucion == null){
            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se puede imprimir un valor nulo", Fila, Columna);
        }
        if(TitusNotificaciones.ContarErrores()){
            if(solucion.Tipo.equals(Constante.TCadena)){
                TitusNotificaciones.ImprimirConsola(solucion.Cadena);
            }else if(solucion.Tipo.equals(Constante.TEntero)){
                TitusNotificaciones.ImprimirConsola(String.valueOf(solucion.Entero));
            }else if(solucion.Tipo.equals(Constante.TDecimal)){
                TitusNotificaciones.ImprimirConsola(String.valueOf(solucion.Decimal));
            }else if(solucion.Tipo.equals(Constante.TCaracter)){
                TitusNotificaciones.ImprimirConsola(String.valueOf(solucion.Caracter));
            }else if(solucion.Tipo.equals(Constante.TBool)){
                TitusNotificaciones.ImprimirConsola(String.valueOf(solucion.Bool));
            }else{
                TitusNotificaciones.ImprimirConsola(String.valueOf(solucion.Nombre));
                //TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Se esperaba un tipo String", solucion.Fila, solucion.Columna);
            }
        }        
    }
}
