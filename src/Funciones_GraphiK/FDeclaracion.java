/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones_GraphiK;

import Constante.Constante;
import Ejecucion_GraphiK.Ambito;
import Ejecucion_GraphiK.Arreglo;
import Ejecucion_GraphiK.Objeto;
import Ejecucion_GraphiK.Simbolo;
import Ejecucion_GraphiK.Variable;
import Interface.TitusNotificaciones;

/**
 *
 * @author Titus
 */
public class FDeclaracion {

    public Ambito Ambito;
    public Ambito Padre;
    public FArreglo Arreglo;
    public String Tipo, Nombre, Visibilidad;
    public String TipoDecla;
    public FNodoExpresion Valor;
    public int Fila, Columna;

    public FDeclaracion(String visibilidad, String tipodecla, String tipo, String nombre, Ambito ambito, Object valor, int fila, int columna) {
        this.Visibilidad = visibilidad;
        this.Ambito = ambito;
        this.Padre = null;
        this.Tipo = tipo;
        this.Nombre = nombre;
        this.TipoDecla = tipodecla;
        this.Fila = fila;
        this.Columna = columna;

        if (this.TipoDecla.equals(Constante.TVariable)) {
            this.Valor = (FNodoExpresion) valor;
        } else {
            this.Arreglo = (FArreglo) valor;
        }
    }

    public void EjecutarDeclaracion(Objeto Tabla, Simbolo instruccion, Objeto padre) {
        if (TipoDecla.equals(Constante.TVariable)) {
            Variable nuevavariable;
            if (Valor != null) {
                FNodoExpresion exp = (FNodoExpresion) Valor;
                exp = exp.ResolverExpresion(padre, 1);
                if (exp.Tipo.equals(Constante.TVariableArreglo)) {
                    exp = exp.PosArreglo;
                }
                //comprobamos si hay errores
                if (TitusNotificaciones.ContarErrores()) {
                    //comprobar si es un nuevo objeto
                    if (exp.Tipo.equals(Constante.TNuevo)) {
                        Objeto o = new Objeto();
                        //buscamos el als padre
                        Ambito buscarpadre = instruccion.Ambito;
                        while (buscarpadre.Padre != null) {
                            buscarpadre = buscarpadre.Padre;
                        }
                        //obtenemos el als al que pertenece
                        FAls alspadre = (FAls) buscarpadre.TablaSimbolo.get(0).Valor;
                        FAls als = o.BuscarAls(exp.Nombre, alspadre.ArchivoPadre);
                        if (als != null) {

                            o = new Objeto(als);
                            if (o != null) {
                                if (o.Nombre.equals(Tipo)) {
                                    FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TObjeto, o.Nombre, instruccion.Fila, instruccion.Columna, o);
                                    nuevavariable = new Variable(Constante.Graphik, Visibilidad, Tipo, Nombre, Constante.TVariable, instruccion.Fila, instruccion.Columna, instruccion.Ambito, nodo, Tabla);
                                    Tabla.TablaVariables.InsertarVariable(nuevavariable);
                                } else {
                                    TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se puede asignar un tipo " + o.Nombre + " a un tipo " + instruccion.Tipo, instruccion.Fila, instruccion.Columna);
                                }
                            } else {
                                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se pudo crear el als" + exp.Nombre, instruccion.Fila, instruccion.Columna);
                            }
                        } else {
                            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No existe el als " + exp.Nombre, instruccion.Fila, instruccion.Columna);
                        }
                    } else {//sino es cualquier otro valor
                        if (exp.Tipo.equals(Constante.TVariableArreglo)) {
                            exp = exp.PosArreglo;
                        }

                        //creamora el alss la variable para el als
                        //hacemos el casteo implicito
                        switch (instruccion.Tipo) {
                            case Constante.TEntero:
                                switch (exp.Tipo) {
                                    case Constante.TEntero:
                                        break;

                                    case Constante.TBool:
                                        exp.Entero = exp.Entero;
                                        exp.Tipo = Constante.TEntero;
                                        break;

                                    case Constante.TCaracter:
                                        exp.Entero = exp.Caracter;
                                        exp.Tipo = Constante.TEntero;
                                        break;

                                    default:
                                        TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "No se puede asignar un tipo " + exp.Tipo + " a " + instruccion.Tipo, instruccion.Fila, instruccion.Columna);
                                        break;
                                }
                                break;

                            case Constante.TDecimal:
                                switch (exp.Tipo) {
                                    case Constante.TDecimal:
                                        break;

                                    case Constante.TEntero:
                                        exp.Decimal = exp.Entero;
                                        exp.Tipo = Constante.TDecimal;
                                        break;

                                    case Constante.TBool:
                                        exp.Decimal = exp.Entero;
                                        exp.Tipo = Constante.TDecimal;
                                        break;

                                    case Constante.TCaracter:
                                        exp.Decimal = exp.Caracter;
                                        exp.Tipo = Constante.TDecimal;
                                        break;

                                    default:
                                        TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "No se puede asignar un tipo " + exp.Tipo + " a " + instruccion.Tipo, instruccion.Fila, instruccion.Columna);
                                        break;
                                }
                                break;

                            case Constante.TCadena:
                                switch (exp.Tipo) {
                                    case Constante.TEntero:
                                        exp.Cadena = String.valueOf(exp.Entero);
                                        exp.Tipo = Constante.TCadena;
                                        break;

                                    case Constante.TBool:
                                        exp.Cadena = String.valueOf(exp.Entero);
                                        exp.Tipo = Constante.TCadena;
                                        break;

                                    case Constante.TCaracter:
                                        exp.Cadena = String.valueOf(exp.Caracter);
                                        exp.Tipo = Constante.TCadena;
                                        break;

                                    case Constante.TDecimal:
                                        exp.Cadena = String.valueOf(exp.Decimal);
                                        exp.Tipo = Constante.TCadena;
                                        break;

                                    case Constante.TCadena:
                                        break;

                                    default:
                                        TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "No se puede asignar un tipo " + exp.Tipo + " a " + instruccion.Tipo, instruccion.Fila, instruccion.Columna);
                                        break;
                                }
                                break;

                            case Constante.TCaracter:
                                switch (exp.Tipo) {
                                    case Constante.TEntero:
                                        exp.Caracter = (char) exp.Entero;
                                        exp.Tipo = Constante.TCaracter;
                                        break;

                                    case Constante.TCaracter:
                                        break;

                                    default:
                                        TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "No se puede asignar un tipo " + exp.Tipo + " a " + instruccion.Tipo, instruccion.Fila, instruccion.Columna);
                                        break;
                                }
                                break;

                            case Constante.TBool:
                                switch (exp.Tipo) {
                                    case Constante.TBool:
                                        break;

                                    default:
                                        TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "No se puede asignar un tipo " + exp.Tipo + " a " + instruccion.Tipo, instruccion.Fila, instruccion.Columna);
                                        break;

                                }
                                break;

                            default:
                                if ((exp.Tipo.equals(Constante.TObjeto) && exp.Nombre.equals(instruccion.Tipo))) {

                                } else {
                                    TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "No se puede asignar un tipo " + exp.Tipo + " a " + instruccion.Tipo, instruccion.Fila, instruccion.Columna);
                                }

                                break;

                        }

                        nuevavariable = new Variable(Constante.Graphik, Visibilidad, Tipo, Nombre, Constante.TVariable, instruccion.Fila, instruccion.Columna, instruccion.Ambito, exp, Tabla);
                        Tabla.TablaVariables.InsertarVariable(nuevavariable);

                    }

                }
            } else {
                nuevavariable = new Variable(Constante.Graphik, Visibilidad, Tipo, Nombre, Constante.TVariable, instruccion.Fila, instruccion.Columna, instruccion.Ambito, null, Tabla);
                Tabla.TablaVariables.InsertarVariable(nuevavariable);
            }

        } else if (TipoDecla.equals(Constante.TVariableArreglo)) {
            if (Tipo.equals(Constante.TEntero) || Tipo.equals(Constante.TDecimal) || Tipo.equals(Constante.TCaracter) || Tipo.equals(Constante.TCadena) || Tipo.equals(Constante.TBool)) {
                Arreglo arreglo = new Arreglo(Visibilidad, Tipo, Nombre, Arreglo.Dimensiones, Fila, Columna, Tabla);
                if (Arreglo.Arreglo != null) {
                    arreglo.InsertarDatos(this.Arreglo.Arreglo, Tabla);
                }
                FNodoExpresion exp = new FNodoExpresion(null, null, TipoDecla, Nombre, Fila, Columna, arreglo);
                Variable nuevavariable = new Variable(Constante.Graphik, Visibilidad, Tipo, Nombre, Constante.TVariableArreglo, Fila, Columna, instruccion.Ambito, exp, Tabla);
                Tabla.TablaVariables.InsertarVariable(nuevavariable);
            } else {
                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se puede declarar un arreglo del tipo " + Tipo, Fila, Columna);
            }
        }
    }
}
