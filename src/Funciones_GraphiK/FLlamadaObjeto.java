/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones_GraphiK;

import Constante.Constante;
import Ejecucion_GraphiK.Objeto;
import Ejecucion_GraphiK.Variable;
import Interface.TitusNotificaciones;

/**
 *
 * @author Titus
 */
public class FLlamadaObjeto {

    public FLlamadaObjeto Hijo;
    public String Tipo, Nombre;
    public int Fila, Columna;
    public FLlamadaMetodo LlamadaMetodo;
    public FLlamadaArreglo LlamadaArreglo;

    public FLlamadaObjeto(String tipo, String nombre, int fila, int columna, Object valor) {
        this.Tipo = tipo;
        this.Nombre = nombre;
        this.Fila = fila;
        this.Columna = columna;
        this.LlamadaMetodo = null;
        this.LlamadaArreglo = null;

        if (tipo.equals(Constante.TVariableArreglo)) {
            this.LlamadaArreglo = (FLlamadaArreglo) valor;
        } else if (tipo.equals(Constante.TMetodo)) {
            this.LlamadaMetodo = (FLlamadaMetodo) valor;
        }
        this.Hijo = null;
    }

    public void InsertarHijo(FLlamadaObjeto hijo) {
        if (this.Hijo == null) {
            this.Hijo = hijo;
        } else {
            this.Hijo.InsertarHijo(hijo);
        }
    }

    public Variable Ejecutar(Objeto objeto) {
        if (this.Tipo.equals(Constante.TVariable)) {
            Variable actual = objeto.TablaVariables.BuscarVariable(this.Nombre);
            if (actual != null) {
                //exite la variable en el ambito actual ahora verificamos si tiene hijos
                if (this.Hijo != null) {
                    if (!actual.Tipo.equals(Constante.TCadena) || !actual.Tipo.equals(Constante.TCaracter) || !actual.Tipo.equals(Constante.TEntero) || !actual.Tipo.equals(Constante.TDecimal) || !actual.Tipo.equals(Constante.TBool)) {
                        Objeto nuevo = ((FNodoExpresion) actual.Valor).Obj;
                        if (nuevo != null) {
                            return Hijo.Ejecutar(nuevo);
                        } else {
                            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "La variable " + this.Hijo.Nombre + " esta nula", this.Hijo.Fila, this.Hijo.Columna);
                        }
                    } else {
                        TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "La variable " + actual.Nombre + " no es un Als", Fila, Columna);
                    }
                } else {
                    //devolvemos el valor de la variable
                    if (actual.Valor != null) {
                        return actual;
                    }
                }
            } else {
                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se encontro la variable " + this.Nombre, this.Fila, this.Columna);
            }
        } else if (this.Tipo.equals(Constante.TMetodo)) {
            Variable actual = objeto.TablaVariables.BuscarFuncion(LlamadaMetodo);
            if (actual != null) {
                //primero ejecutamos el metodo
                FMetodo metodo = (FMetodo) actual.Valor;
                metodo.EjecutarMetodo(LlamadaMetodo, objeto, actual);
                //metodo.Ejecutar(objeto)
                //exite el metodo en el ambito actual ahora verificamos si tiene hijos
                if (this.Hijo != null) {
                    if (!actual.Tipo.equals(Constante.TCadena) || !actual.Tipo.equals(Constante.TCaracter) || !actual.Tipo.equals(Constante.TEntero) || !actual.Tipo.equals(Constante.TDecimal) || !actual.Tipo.equals(Constante.TBool)) {
                        Objeto nuevo = ((FNodoExpresion) actual.Valor).Obj;
                        if (nuevo != null) {
                            return Hijo.Ejecutar(nuevo);
                        } else {
                            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "La variable " + this.Hijo.Nombre + " esta nula", this.Hijo.Fila, this.Hijo.Columna);
                        }
                    } else {
                        TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "La variable " + actual.Nombre + " no es un Als", Fila, Columna);
                    }
                } else {
                    //devolvemos el valor de la variable
                    if (actual.Valor != null) {
                        return actual;
                    }
                }
            } else {
                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se encontro el metodo " + this.Nombre, this.Fila, this.Columna);
            }
        }
        return null;
    }
}
