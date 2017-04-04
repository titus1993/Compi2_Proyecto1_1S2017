/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones_GraphiK;

import Constante.Constante;
import Ejecucion_GraphiK.*;
import Interface.TitusNotificaciones;

/**
 *
 * @author Titus
 */
public class FAsignacion {

    public Ambito Ambito;
    public Ambito Padre;
    public FArreglo Arreglo;
    public String TipoAsigna;
    public String Tipo;
    public FLlamadaObjeto Nombre;
    public FNodoExpresion Valor;

    public FAsignacion(String tipoasigna, String tipo, FLlamadaObjeto nombre, Ambito ambito, Object valor) {
        this.Ambito = ambito;
        this.Padre = null;
        this.TipoAsigna = tipoasigna;
        this.Nombre = nombre;
        this.Tipo = tipo;

        //if (this.TipoAsigna.equals(Constante.TVariable)) {
        this.Valor = (FNodoExpresion) valor;
        /*} else if (Tipo.equals(Constante.TAsignacion)) {
            
        } else {
            this.Arreglo = (FArreglo) valor;
        }*/
    }

    public void EjecutarAsignacion(Objeto Tabla, Simbolo instruccion, Objeto padre) {
        if (TipoAsigna.equals(Constante.TVariable)) {
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
                                FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TObjeto, o.Nombre, instruccion.Fila, instruccion.Columna, o);
                                nuevavariable = Nombre.Ejecutar(Tabla, padre,1);///////////////////--->casteo
                                if (o.Nombre.equals(nuevavariable.Tipo)) {

                                    if (nuevavariable != null) {
                                        nuevavariable.Valor = nodo;

                                    } else {
                                        TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No existe la variable " + instruccion.Nombre, instruccion.Fila, instruccion.Columna);
                                    }
                                } else {
                                    TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se puede asignar un tipo " + o.Nombre + " a un tipo " + nuevavariable.Tipo, instruccion.Fila, instruccion.Columna);
                                }
                            } else {
                                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se pudo crear el als" + exp.Nombre, instruccion.Fila, instruccion.Columna);
                            }
                        } else {
                            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No existe el als " + exp.Nombre, instruccion.Fila, instruccion.Columna);
                        }
                    } else {//sino es cualquier otro valor

                        nuevavariable = Nombre.Ejecutar(Tabla, padre, 1);///////////////////--->casteo

                        if (nuevavariable != null) {
                            if (nuevavariable.Rol.equals(Constante.TVariableArreglo)) {
                                Arreglo arr = ((FNodoExpresion) nuevavariable.Valor).ArregloResuelto;
                                arr.InsertarDatos(exp, padre);
                            } else {
                                if (exp.Tipo.equals(Constante.TVariableArreglo)) {
                                    exp = exp.PosArreglo;
                                }
                                if (nuevavariable.Tipo.equals(exp.Tipo) || exp.Tipo.equals(Constante.TObjeto) && nuevavariable.Tipo.equals(exp.Nombre)) {
                                    nuevavariable.Valor = exp;//--------------------------------------hacer casteo
                                } else {
                                    if (exp.Tipo.equals(Constante.TObjeto) && nuevavariable.Tipo.equals(exp.Nombre)) {
                                        TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se puede asignar un tipo " + exp.Nombre + " a un tipo " + nuevavariable.Tipo, instruccion.Fila, instruccion.Columna);
                                    } else {
                                        TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se puede asignar un tipo " + exp.Tipo + " a un tipo " + nuevavariable.Tipo, instruccion.Fila, instruccion.Columna);

                                    }
                                }
                            }
                        } else {
                            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No existe la variable " + instruccion.Nombre, instruccion.Fila, instruccion.Columna);
                        }

                    }

                }
            } else if (this.Tipo.equals(Constante.TAumento)) {
                nuevavariable = Nombre.Ejecutar(Tabla, padre,1);///////////////////--->casteo);

                if (nuevavariable != null) {
                    if (nuevavariable.Tipo.equals(Constante.TEntero) || nuevavariable.Tipo.equals(Constante.TDecimal) || nuevavariable.Tipo.equals(Constante.TCaracter)) {
                        if (nuevavariable.Valor != null) {
                            FNodoExpresion val = (FNodoExpresion) nuevavariable.Valor;
                            if (val.Tipo.equals(Constante.TVariableArreglo)) {
                                val = val.PosArreglo;
                            }
                            if (val.Tipo.equals(Constante.TVariableArreglo)) {
                                val = val.PosArreglo;
                            }
                            if (nuevavariable.Tipo.equals(Constante.TEntero)) {
                                val.Entero += 1;
                            } else if (nuevavariable.Tipo.equals(Constante.TDecimal)) {
                                val.Decimal += 1;
                            } else {
                                val.Caracter += 1;
                            }
                        } else {
                            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "La variable no esta inicializada", instruccion.Fila, instruccion.Columna);
                        }
                    } else {
                        TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se puede ++ a un tipo " + nuevavariable.Tipo, instruccion.Fila, instruccion.Columna);
                    }
                } else {
                    TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No existe la variable " + instruccion.Nombre, instruccion.Fila, instruccion.Columna);
                }
            } else if (this.Tipo.equals(Constante.TDisminucion)) {
                nuevavariable = Nombre.Ejecutar(Tabla, padre,1);///////////////////--->casteo);

                if (nuevavariable != null) {
                    if (nuevavariable.Tipo.equals(Constante.TEntero) || nuevavariable.Tipo.equals(Constante.TDecimal) || nuevavariable.Tipo.equals(Constante.TCaracter)) {
                        if (nuevavariable.Valor != null) {
                            FNodoExpresion val = (FNodoExpresion) nuevavariable.Valor;
                            if (val.Tipo.equals(Constante.TVariableArreglo)) {
                                val = val.PosArreglo;
                            }
                            if (val.Tipo.equals(Constante.TVariableArreglo)) {
                                val = val.PosArreglo;
                            }
                            if (nuevavariable.Tipo.equals(Constante.TEntero)) {
                                val.Entero -= 1;
                            } else if (nuevavariable.Tipo.equals(Constante.TDecimal)) {
                                val.Decimal -= 1;
                            } else {
                                val.Caracter -= 1;
                            }
                        } else {
                            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "La variable no esta inicializada", instruccion.Fila, instruccion.Columna);
                        }
                    } else {
                        TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se puede ++ a un tipo " + nuevavariable.Tipo, instruccion.Fila, instruccion.Columna);
                    }
                } else {
                    TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No existe la variable " + instruccion.Nombre, instruccion.Fila, instruccion.Columna);
                }
            }

        } else if (TipoAsigna.equals(Constante.TVariableArreglo)) {
            Variable nuevavariable;
            if (Valor != null) {
                FNodoExpresion exp = (FNodoExpresion) Valor;
                exp = exp.ResolverExpresion(padre, 1);
                //comprobamos si hay errores
                if (TitusNotificaciones.ContarErrores()) {
                    //comprobar si es un nuevo objeto
                    if (exp.Tipo.equals(Constante.TNuevo)) {
                        TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se puede crear als en un arreglo", instruccion.Fila, instruccion.Columna);
                    } else {//sino es cualquier otro valor
                        nuevavariable = Nombre.Ejecutar(Tabla, padre,1);///////////////////--->casteo);

                        if (nuevavariable != null) {
                            if (nuevavariable.Rol.equals(Constante.TVariableArreglo)) {
                                Arreglo arr = ((FNodoExpresion) nuevavariable.Valor).ArregloResuelto;
                                arr.InsertarValor(this.Nombre.LlamadaArreglo, padre, exp);

                            } else {
                                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "La variable no es un arreglo", instruccion.Fila, instruccion.Columna);
                            }
                        } else {
                            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No existe la variable " + instruccion.Nombre, instruccion.Fila, instruccion.Columna);
                        }

                    }

                }
            } else if (this.Tipo.equals(Constante.TAumento)) {
                nuevavariable = Nombre.Ejecutar(Tabla, padre,1);///////////////////--->casteo);

                if (nuevavariable != null) {
                    if (nuevavariable.Tipo.equals(Constante.TEntero) || nuevavariable.Tipo.equals(Constante.TDecimal) || nuevavariable.Tipo.equals(Constante.TCaracter)) {
                        if (nuevavariable.Valor != null) {
                            FNodoExpresion val = (FNodoExpresion) nuevavariable.Valor;
                            val.ArregloResuelto.InsertarValorAumento(this.Nombre.LlamadaArreglo, Tabla);

                        } else {
                            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "La variable no esta inicializada", instruccion.Fila, instruccion.Columna);
                        }
                    } else {
                        TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se puede ++ a un tipo " + nuevavariable.Tipo, instruccion.Fila, instruccion.Columna);
                    }
                } else {
                    TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No existe la variable " + instruccion.Nombre, instruccion.Fila, instruccion.Columna);
                }
            } else if (this.Tipo.equals(Constante.TDisminucion)) {
                nuevavariable = Nombre.Ejecutar(Tabla, padre,1);///////////////////--->casteo);

                if (nuevavariable != null) {
                    if (nuevavariable.Tipo.equals(Constante.TEntero) || nuevavariable.Tipo.equals(Constante.TDecimal) || nuevavariable.Tipo.equals(Constante.TCaracter)) {
                        if (nuevavariable.Valor != null) {
                            FNodoExpresion val = (FNodoExpresion) nuevavariable.Valor;
                            val.ArregloResuelto.InsertarValorDecremento(this.Nombre.LlamadaArreglo, Tabla);

                        } else {
                            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "La variable no esta inicializada", instruccion.Fila, instruccion.Columna);
                        }
                    } else {
                        TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se puede -- a un tipo " + nuevavariable.Tipo, instruccion.Fila, instruccion.Columna);
                    }
                } else {
                    TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No existe la variable " + instruccion.Nombre, instruccion.Fila, instruccion.Columna);
                }
            }
        }
    }
}
