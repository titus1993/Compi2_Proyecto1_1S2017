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
import java.util.ArrayList;

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

    public Variable Ejecutar(Objeto objeto, Objeto padre) {
        if (this.Tipo.equals(Constante.TVariable)) {
            Variable actual = objeto.TablaVariables.BuscarVariable(this.Nombre);
            if (actual != null) {
                //exite la variable en el ambito actual ahora verificamos si tiene hijos
                if (this.Hijo != null) {
                    if (!actual.Tipo.equals(Constante.TCadena) && !actual.Tipo.equals(Constante.TCaracter) && !actual.Tipo.equals(Constante.TEntero) && !actual.Tipo.equals(Constante.TDecimal) && !actual.Tipo.equals(Constante.TBool)) {
                        if ((FNodoExpresion) actual.Valor != null) {
                            Objeto nuevo = ((FNodoExpresion) actual.Valor).Obj;
                            if (nuevo != null) {
                                return Hijo.EjecutarHijo(nuevo, objeto);
                            } else {
                                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "La variable " + this.Hijo.Nombre + " esta nula", this.Hijo.Fila, this.Hijo.Columna);
                            }
                        } else {
                            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "La variable " + actual.Nombre + " esta nula", Fila, Columna);
                        }
                    } else {
                        TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "La variable " + actual.Nombre + " no es un Als", Fila, Columna);
                    }
                } else {
                    //devolvemos el valor de la variable

                    return actual;
                }
            } else {
                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se encontro la variable " + this.Nombre, this.Fila, this.Columna);
            }
        } else if (this.Tipo.equals(Constante.TMetodo)) {
            ArrayList<FNodoExpresion> listaparametros = new ArrayList<>();
            for(FNodoExpresion par: LlamadaMetodo.Parametros){
                FNodoExpresion nuevopar = par.ResolverExpresion(padre);
                listaparametros.add(nuevopar);
            }
            Variable actual = objeto.TablaVariables.BuscarFuncion(LlamadaMetodo, listaparametros);
            if (actual != null) {
                //primero ejecutamos el metodo
                FMetodo metodo = (FMetodo) actual.Valor;
                //obtenemos el return
                Variable retorno = metodo.EjecutarMetodo(LlamadaMetodo, objeto, actual, padre);

                //metodo.Ejecutar(objeto)
                //exite el metodo en el ambito actual ahora verificamos si tiene hijos
                if (this.Hijo != null) {
                    //comprobamos si es null para notificar error
                    if (retorno != null && !actual.Tipo.equals(Constante.TVacio)) {
                        if (!actual.Tipo.equals(Constante.TCadena) && !actual.Tipo.equals(Constante.TCaracter) && !actual.Tipo.equals(Constante.TEntero) && !actual.Tipo.equals(Constante.TDecimal) && !actual.Tipo.equals(Constante.TBool)) {

                            Objeto nuevo = ((FNodoExpresion) retorno.Valor).Obj;
                            if (nuevo != null) {
                                return Hijo.EjecutarHijo(nuevo, objeto);
                            } else {
                                if (this.Hijo.Hijo == null) {
                                    return retorno;
                                } else {
                                    TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "El metodo " + this.Hijo.Nombre + " esta nula", this.Hijo.Fila, this.Hijo.Columna);
                                }
                            }
                        } else {
                            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "El metodo " + actual.Nombre + " no retonra un Als, retorna un tipo " + actual.Tipo, Fila, Columna);
                        }
                    } else {
                        if (actual.Tipo.equals(Constante.TVacio)) {
                            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "El metodo " + actual.Nombre + " es de tipo vacio ", Fila, Columna);
                        } else {
                            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "El metodo " + actual.Nombre + " no retorno ningun valor ", Fila, Columna);
                        }
                    }

                } else {
                    return retorno;
                }
            } else {
                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se encontro el metodo " + this.Nombre, this.Fila, this.Columna);
            }
        }
        return null;
    }

    private Variable EjecutarHijo(Objeto objeto, Objeto padre) {
        if (this.Tipo.equals(Constante.TVariable)) {
            Variable actual = objeto.TablaVariables.BuscarVariable(this.Nombre);
            if (actual != null) {

                //exite la variable en el ambito actual ahora verificamos si tiene hijos
                if (actual.Visibilidad.equals(Constante.TPublico) || actual.Visibilidad.equals(Constante.TProtegido)) {
                    if (this.Hijo != null) {
                        if (!actual.Tipo.equals(Constante.TCadena) && !actual.Tipo.equals(Constante.TCaracter) && !actual.Tipo.equals(Constante.TEntero) && !actual.Tipo.equals(Constante.TDecimal) && !actual.Tipo.equals(Constante.TBool)) {
                            if ((FNodoExpresion) actual.Valor != null) {
                                Objeto nuevo = ((FNodoExpresion) actual.Valor).Obj;

                                return Hijo.Ejecutar(nuevo, objeto);

                            } else {
                                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "La variable " + actual.Nombre + " esta nula", Fila, Columna);
                            }
                        } else {
                            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "La variable " + actual.Nombre + " no es un Als", Fila, Columna);
                        }
                    } else {
                        //devolvemos el valor de la variable
                        return actual;
                    }
                } else {
                    TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se puede acceder a la variable " + actual.Visibilidad + " " + actual.Nombre, Fila, Columna);
                    return null;
                }
            } else {
                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se encontro la variable " + this.Nombre, this.Fila, this.Columna);
            }
        } else if (this.Tipo.equals(Constante.TMetodo)) {
            ArrayList<FNodoExpresion> listaparametros = new ArrayList<>();
            for(FNodoExpresion par: LlamadaMetodo.Parametros){
                FNodoExpresion nuevopar = par.ResolverExpresion(padre);
                listaparametros.add(nuevopar);
            }
            Variable actual = objeto.TablaVariables.BuscarFuncion(LlamadaMetodo, listaparametros);
            if (actual != null) {
                //primero ejecutamos el metodo
                FMetodo metodo = (FMetodo) actual.Valor;

                if (metodo.Visibilidad.equals(Constante.TPublico) || metodo.Visibilidad.equals(Constante.TProtegido)) {
                    //obtenemos el return
                    Variable retorno = metodo.EjecutarMetodo(LlamadaMetodo, objeto, actual, padre);

                    //metodo.Ejecutar(objeto)
                    //exite el metodo en el ambito actual ahora verificamos si tiene hijos
                    if (this.Hijo != null) {
                        //comprobamos si es null para notificar error
                        if (retorno != null && !actual.Tipo.equals(Constante.TVacio)) {
                            if (!actual.Tipo.equals(Constante.TCadena) && !actual.Tipo.equals(Constante.TCaracter) && !actual.Tipo.equals(Constante.TEntero) && !actual.Tipo.equals(Constante.TDecimal) && !actual.Tipo.equals(Constante.TBool)) {

                                Objeto nuevo = ((FNodoExpresion) retorno.Valor).Obj;
                                if (nuevo != null) {
                                    return Hijo.EjecutarHijo(nuevo, objeto);
                                } else {
                                    if (this.Hijo.Hijo == null) {
                                        return retorno;

                                    } else {
                                        TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "El metodo " + this.Hijo.Nombre + " esta nula", this.Hijo.Fila, this.Hijo.Columna);
                                    }
                                }
                            } else {
                                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "El metodo " + actual.Nombre + " no retonra un Als, retorna un tipo " + actual.Tipo, Fila, Columna);
                            }
                        } else {
                            if (actual.Tipo.equals(Constante.TVacio)) {
                                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "El metodo " + actual.Nombre + " es de tipo vacio ", Fila, Columna);
                            } else {
                                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "El metodo " + actual.Nombre + " no retorno ningun valor ", Fila, Columna);
                            }
                        }

                    } else {
                        return retorno;

                    }
                } else {
                    TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se puede acceder al metodo " + metodo.Visibilidad + " " + metodo.Nombre, Fila, Columna);
                    return null;
                }

            } else {
                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se encontro el metodo " + this.Nombre, this.Fila, this.Columna);
            }
        }
        return null;
    }
}
