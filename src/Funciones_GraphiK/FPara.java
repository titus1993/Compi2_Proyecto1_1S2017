/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones_GraphiK;

import Constante.Constante;
import Ejecucion_GraphiK.*;
import Interface.TitusNotificaciones;
import java.util.ArrayList;

/**
 *
 * @author Titus
 */
public class FPara {

    public Simbolo AccionSiguiente;
    public Simbolo AccionAnterior;
    public FNodoExpresion Condicion;
    public Ambito Ambito;

    public FPara(Simbolo anterior, FNodoExpresion condicion, Simbolo siguiente, Ambito ambito) {
        this.AccionAnterior = anterior;
        this.Condicion = condicion;
        this.AccionSiguiente = siguiente;
        this.Ambito = ambito;
    }

    public void EjecutarPara(Objeto Tabla, Simbolo instruccion, Objeto padre) {
        //verificamos si la accion posterior es declaracion o asignacion
        if (AccionAnterior.Rol.equals(Constante.TDeclaracion)) {
            FDeclaracion decla = (FDeclaracion) AccionAnterior.Valor;
            decla.EjecutarDeclaracion(Tabla, AccionAnterior, padre);

        } else {
            FAsignacion asigna = (FAsignacion) AccionAnterior.Valor;
            asigna.EjecutarAsignacion(Tabla, AccionAnterior, padre);
        }

        FNodoExpresion condicion = this.Condicion.ResolverExpresion(padre);
        if (condicion.Tipo.equals(Constante.TVariableArreglo)) {
            condicion = condicion.PosArreglo;
        }

        if (TitusNotificaciones.ContarErrores()) {
            if (condicion.Tipo.equals(Constante.TBool)) {
                while (TitusNotificaciones.ContarErrores() && condicion.Bool && !Tabla.TablaVariables.IsRertorno() && !Tabla.TablaVariables.IsTerminar()) {
                    condicion = Condicion.ResolverExpresion(padre);
                    if (condicion.Tipo.equals(Constante.TVariableArreglo)) {
                        condicion = condicion.PosArreglo;
                    }
                    FMetodo metodo1 = new FMetodo(Constante.TPublico, new ArrayList<Simbolo>(), this.Ambito, 0, 0, Constante.TVacio, Constante.TPara);

                    if (condicion.Bool) {
                        metodo1.EjecutarInstrucciones(this.Ambito.TablaSimbolo, Tabla, padre);

                    }

                    if (Tabla.TablaVariables.IsContinuar()) {
                        Tabla.TablaVariables.SacarVariable();
                    }
                    condicion = Condicion.ResolverExpresion(padre);
                    if (condicion.Tipo.equals(Constante.TVariableArreglo)) {
                        condicion = condicion.PosArreglo;
                    }
                    //sacamos el ambito del para
                    metodo1.SacarAmbito(this.Ambito.TablaSimbolo, Tabla);
                    //realizamos la operacion posterior
                    if (condicion.Bool) {
                        FAsignacion asig = (FAsignacion) AccionSiguiente.Valor;
                        asig.EjecutarAsignacion(Tabla, AccionSiguiente, padre);
                    }

                }
                if (Tabla.TablaVariables.IsTerminar()) {
                    Tabla.TablaVariables.SacarVariable();
                }

                //sacamos la variable si fue declaracion
                if (AccionAnterior.Rol.equals(Constante.TDeclaracion)) {
                    FDeclaracion decla = (FDeclaracion) AccionAnterior.Valor;
                    if (Tabla.TablaVariables.ExisteVariableTope(decla.Nombre)) {
                        Tabla.TablaVariables.SacarVariable();
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
