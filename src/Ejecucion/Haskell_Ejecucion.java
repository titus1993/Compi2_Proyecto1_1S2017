/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejecucion;

import Constante.Constante;
import Funciones.*;
import Interface.TitusNotificaciones;

/**
 *
 * @author Titus
 */
public class Haskell_Ejecucion {

    public Ambito Ambito;
    public String Ruta;

    public Haskell_Ejecucion(Ambito ambito) {
        this.Ambito = ambito;
    }

    public void LlenarTabla() {
        for (Simbolo sim : Ambito.TablaSimbolo) {
            Variable var = new Variable(sim.Tipo, sim.Nombre, sim.Rol, sim.Fila, sim.Columna, sim.Ambito, sim.Valor);
            TablaHaskell.InsertarFuncion(var);
        }
    }

    public void EjecutarConsola(Simbolo accion) {
        if (accion.Rol.equals(Constante.TVariable)) {
            Variable variable = TablaHaskell.BuscarVariable(accion.Nombre);
            if (variable == null) {
                FNodoExpresion exp = (FNodoExpresion)accion.Valor;
                exp = exp.ResolverExpresion();
                if(exp.Tipo != Constante.TError){
                    Variable porcentaje = TablaHaskell.BuscarVariable("%");
                    porcentaje.Valor = exp;
                    TablaHaskell.InsertarVariable(new Variable(Constante.TVacio, accion.Nombre, Constante.TVariable, accion.Fila, accion.Columna,accion.Ambito, exp));
                    switch (exp.Tipo) {
                        case Constante.TDecimal:
                            TitusNotificaciones.ImprimirConsola(String.valueOf(exp.Numero));
                            break;

                        case Constante.TCaracter:
                            TitusNotificaciones.ImprimirConsola(String.valueOf(exp.Caracter));
                            break;

                        case Constante.TArreglo:
                            TitusNotificaciones.ImprimirConsola(exp.Arreglo.ObtenerCadena());
                            break;
                    }
                }else{
                    TitusNotificaciones.InsertarError(exp.Nombre, exp.Cadena, exp.Fila, exp.Columna);
                }                
            } else {
                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "La variable " + variable.Nombre + " ya esta declarada", variable.Fila, variable.Columna);
            }
        } else if (accion.Rol.equals(Constante.TMetodo)) {
            Variable variable = TablaHaskell.BuscarVariable("%");
            if (variable != null) {
                FLlamadaMetodo metodo = (FLlamadaMetodo) accion.Valor;
                FNodoExpresion valor = new FNodoExpresion(null, null, "", "", 0, 0, null);
                valor = valor.LlamadaMetodo(metodo);
                valor = valor.ResolverExpresion();
                if (!valor.Tipo.equals(Constante.TError)) {
                    variable.Valor = valor;
                    switch (valor.Tipo) {
                        case Constante.TDecimal:
                            TitusNotificaciones.ImprimirConsola(String.valueOf(valor.Numero));
                            break;

                        case Constante.TCaracter:
                            TitusNotificaciones.ImprimirConsola(String.valueOf(valor.Caracter));
                            break;

                        case Constante.TArreglo:
                            TitusNotificaciones.ImprimirConsola(valor.Arreglo.ObtenerCadena());
                            break;
                    }
                }
            }
        }
    }
}
