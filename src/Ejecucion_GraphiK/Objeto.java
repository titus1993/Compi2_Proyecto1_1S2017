/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejecucion_GraphiK;

import Constante.Constante;
import Funciones_GraphiK.*;
import Interface.TitusNotificaciones;

/**
 *
 * @author Titus
 */
public class Objeto {

    public TablaGraphik TablaVariables = new TablaGraphik();
    public String Nombre;
    public Objeto Herencia;

    public Objeto() {

    }

    public Objeto(FAls simbolo) {
        this.Nombre = simbolo.Nombre;
        CrearObjeto(simbolo);
    }

    public void ApartarInicio() {
        TablaVariables.ApartarInicio();
    }

    public Objeto CrearObjeto(FAls simbolo) {

        for (Simbolo sim : simbolo.Ambito.TablaSimbolo) {
            //validasmo que no existan errores
            if (TitusNotificaciones.ContarErrores()) {
                if (sim.Rol.equals(Constante.TDeclaracion)) {
                    //obtenemos la declaracion
                    FDeclaracion decla = (FDeclaracion) sim.Valor;

                    if (decla.TipoDecla.equals(Constante.TVariable)) {
                        Variable nuevavariable;
                        if (decla.Valor != null) {
                            FNodoExpresion exp = (FNodoExpresion) decla.Valor;
                            exp = exp.ResolverExpresion(this);
                            //comprobamos si hay errores
                            if (TitusNotificaciones.ContarErrores()) {
                                //comprobar si es un nuevo objeto
                                if (exp.Tipo.equals(Constante.TNuevo)) {
                                    Objeto o = new Objeto();
                                    FAls als = o.BuscarAls(exp.Nombre, simbolo.ArchivoPadre);
                                    if (als != null) {

                                        o = new Objeto(als);
                                        if (o != null) {
                                            if (o.Nombre.equals(decla.Tipo)) {
                                                FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TObjeto, o.Nombre, sim.Fila, sim.Columna, o);
                                                nuevavariable = new Variable(Constante.Graphik, decla.Visibilidad, decla.Tipo, decla.Nombre, Constante.TVariable, sim.Fila, sim.Columna, sim.Ambito, nodo, this);
                                                TablaVariables.InsertarVariable(nuevavariable);
                                            } else {
                                                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se puede asignar un tipo " + o.Nombre + " a un tipo " + sim.Tipo, sim.Fila, sim.Columna);
                                            }
                                        } else {
                                            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se pudo crear el als" + exp.Nombre, sim.Fila, sim.Columna);
                                        }
                                    } else {
                                        TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No existe el als " + exp.Nombre, sim.Fila, sim.Columna);
                                    }
                                } else {//sino es cualquier otro valor
                                    if (exp.Tipo.equals(sim.Tipo)) {
                                        //creamora el alss la variable para el als

                                        nuevavariable = new Variable(Constante.Graphik, decla.Visibilidad, decla.Tipo, decla.Nombre, Constante.TVariable, sim.Fila, sim.Columna, sim.Ambito, exp, this);
                                        TablaVariables.InsertarVariable(nuevavariable);
                                    } else {
                                        TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "No se puede asignar un tipo " + exp.Tipo + " a " + sim.Tipo, sim.Fila, sim.Columna);
                                    }
                                }

                            }
                        } else {
                            nuevavariable = new Variable(Constante.Graphik, decla.Visibilidad, decla.Tipo, decla.Nombre, Constante.TVariable, sim.Fila, sim.Columna, sim.Ambito, null, this);
                            TablaVariables.InsertarVariable(nuevavariable);
                        }

                    } else if (sim.Rol.equals(Constante.TVariableArreglo)) {

                    }
                } else if (sim.Rol.equals(Constante.TMetodo)) {
                    if (sim.Nombre.equals(Constante.TInicio)) {
                        Variable inicio = new Variable(Constante.Graphik, sim.Visibilidad, sim.Tipo, sim.Nombre, sim.Rol, sim.Fila, sim.Columna, sim.Ambito, sim.Valor, this);
                        if (TablaVariables.Tabla.size() > 0 && !TablaVariables.Tabla.get(0).Nombre.equals(Constante.TInicio)) {
                            TablaVariables.Tabla.add(0, inicio);
                        } else {
                            TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Hay mas de un metodo inicio", sim.Fila, sim.Columna);
                        }
                    } else {
                        Variable metodo = new Variable(Constante.Graphik, sim.Visibilidad, sim.Tipo, sim.Nombre, sim.Rol, sim.Fila, sim.Columna, sim.Ambito, sim.Valor, this);
                        TablaVariables.InsertarVariable(metodo);
                    }
                }
            }
        }

        BuscarHeredas(simbolo);
        return this;
    }

    public void BuscarHeredas(FAls inicio) {
        Ambito ambitopadre = inicio.Ambito;
        //obtenemos el ambito del als
        while (ambitopadre.Padre != null) {
            ambitopadre = ambitopadre.Padre;
        }
        //obtenemos el als padre
        FAls nodoals = (FAls) ambitopadre.TablaSimbolo.get(0).Valor;

        //verficamos si hay heredas
        if (!nodoals.Herencia.equals("")) {
            //buscamos primero si hay un als en el mismo archivo

            FAls herencia = BuscarAls(nodoals.Herencia, nodoals.ArchivoPadre);
            if (herencia != null) {
                Herencia = new Objeto(herencia);
                Variable h = new Variable(Constante.Graphik, Constante.TPublico, Constante.THereda, Constante.THereda, Constante.THereda, 0, 0, null, Herencia, this);
                //agregamos el objeto de herencia
                this.TablaVariables.InsertarVariable(h);
                //ahora agregamos los objetos publicos y protegidos a la tabla

                //
            } else {
                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se encontro un als para heredar llamado " + nodoals.Herencia, 0, 0);
            }
        }
    }

    public FAls BuscarAls(String nombreals, Archivo archivo) {
        if (archivo != null) {
            for (Simbolo nuevoals : archivo.Als) {
                FAls als = (FAls) nuevoals.Valor;
                if (als.Nombre.equals(nombreals)) {
                    return als;
                }
            }
            for (String nombrearchivo2 : archivo.Graphik) {
                Archivo nuevoarchivo2 = ControlArchivo.ObtenerArchivo(nombrearchivo2 + ".gk");

                if (nuevoarchivo2 != null) {
                    return BuscarAls(nombreals, nuevoarchivo2);
                }
            }
        }

        return null;
    }

}
