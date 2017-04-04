/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones_GraphiK;

import Constante.Constante;
import Ejecucion_GraphiK.Ambito;
import Ejecucion_GraphiK.*;
import Interface.TitusNotificaciones;
import java.util.ArrayList;

/**
 *
 * @author Titus
 */
public class FSi {

    public Ambito Si;
    public Ambito Sino;
    public Ambito Ambito;
    public FNodoExpresion Condicion;

    public FSi(FNodoExpresion condicion, Ambito si, Ambito sino, Ambito ambito) {
        this.Si = si;
        this.Sino = sino;
        this.Condicion = condicion;
        this.Ambito = ambito;
    }

    public void EjecutarSi(Objeto Tabla, Simbolo instruccion, Objeto padre) {
        FNodoExpresion condicion = this.Condicion.ResolverExpresion(padre);
        if (condicion.Tipo.equals(Constante.TVariableArreglo)) {
                condicion = condicion.PosArreglo;
            }
        if (TitusNotificaciones.ContarErrores()) {
            if (condicion.Tipo.equals(Constante.TBool)) {
                if (condicion.Bool) {
                    FMetodo metodo = new FMetodo(Constante.TPublico, new ArrayList<Simbolo>(), this.Si, 0, 0, Constante.TVacio, Constante.TSi);
                    metodo.EjecutarInstrucciones(Si.TablaSimbolo, Tabla, padre);
                    metodo.SacarAmbito(Si.TablaSimbolo, Tabla);
                } else {
                    if (Sino != null) {
                        FMetodo metodo = new FMetodo(Constante.TPublico, new ArrayList<Simbolo>(), this.Sino, 0, 0, Constante.TVacio, Constante.TSi);
                        metodo.EjecutarInstrucciones(Sino.TablaSimbolo, Tabla, padre);
                        metodo.SacarAmbito(Sino.TablaSimbolo, Tabla);
                    }
                }
            } else {
                if (condicion.Tipo.equals(Constante.TObjeto)) {
                    TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "Se esperaba un tipo bool no un tipo " + condicion.Nombre, Condicion.Fila, Condicion.Columna);
                } else {
                    TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "Se esperaba un tipo bool no un tipo " + condicion.Tipo, Condicion.Fila, Condicion.Columna);
                }
            }
        }
    }
}
