/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejecucion;

import Constante.Constante;
import Funciones.*;
import Interface.TitusNotificaciones;
import java.util.ArrayList;

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

    public FNodoExpresion EjecutarInstrucciones(Ambito instrucciones) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        for (Simbolo sim : instrucciones.TablaSimbolo) {
            switch (sim.Rol) {
                case Constante.TIf:
                    nodo = EjecutarIf(sim);
                    break;

                case Constante.TCase:
                    nodo = EjecutarCase(sim);
                    break;

                case Constante.TVariable:
                    nodo = EjecutarDeclaracion(sim);
                    break;

                case Constante.TMetodo:
                    nodo = EjecutarMetodo(sim);
                    break;
            }
        }
        return nodo;
    }

    public FNodoExpresion EjecutarMetodo(Simbolo si) {
        FLlamadaMetodo metodo = (FLlamadaMetodo) si.Valor;
        FNodoExpresion valor = new FNodoExpresion(null, null, "", "", 0, 0, null);
        valor = valor.LlamadaMetodo(metodo);
        return valor;
    }

    public FNodoExpresion EjecutarDeclaracion(Simbolo var) {
        FNodoExpresion valor = (FNodoExpresion) var.Valor;
        valor = valor.ResolverExpresion();

        if (TitusNotificaciones.Error.getRowCount() == 0) {
            TablaHaskell.InsertarVariable(new Variable(Constante.TVacio, var.Nombre, Constante.TVariable, var.Fila, var.Columna, new Ambito(var.Nombre), valor));
        }
        return valor;
    }

    public FNodoExpresion EjecutarIf(Simbolo si) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, si.Fila, si.Columna, null);
        FIf fsi = (FIf) si.Valor;
        FNodoExpresion condicion = fsi.Condicion.ResolverExpresion();
        if (TitusNotificaciones.Error.getRowCount() == 0) {
            if (condicion.Tipo.equals(Constante.TBool)) {
                if (condicion.Bool) {
                    nodo = EjecutarInstrucciones(fsi.Ambito);
                    SacarAmbito(fsi.Ambito.TablaSimbolo);
                } else {
                    nodo = EjecutarInstrucciones(fsi.Else);
                    SacarAmbito(fsi.Else.TablaSimbolo);
                }
            } else {
                //error no es bool el resultado
            }
        }
        return nodo;
    }

    public FNodoExpresion EjecutarCase(Simbolo fcase) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TMixto, Constante.TMixto, fcase.Fila, fcase.Columna, 0);
        FCase fselecciona = (FCase) fcase.Valor;
        FNodoExpresion condicion = fselecciona.Condicion.ResolverExpresion();

        if (TitusNotificaciones.Error.getRowCount() == 0) {
            int cont = 0;

            while (cont < fselecciona.Casos.size()) {
                FCaso caso = fselecciona.Casos.get(cont);
                if ((caso.Valor.Tipo.equals(Constante.TDecimal) || caso.Valor.Tipo.equals(Constante.TMixto)) && (condicion.Tipo.equals(Constante.TDecimal) || condicion.Tipo.equals(Constante.TMixto))) {
                    if (caso.Valor.Numero == condicion.Numero) {
                        nodo = EjecutarInstrucciones(caso.Ambito);
                        SacarAmbito(caso.Ambito.TablaSimbolo);
                        break;
                    }
                } else if ((caso.Valor.Tipo.equals(Constante.TCaracter) || caso.Valor.Tipo.equals(Constante.TMixto)) && (condicion.Tipo.equals(Constante.TCaracter) || condicion.Tipo.equals(Constante.TMixto))) {
                    if (caso.Valor.Caracter == condicion.Caracter) {
                        nodo = EjecutarInstrucciones(caso.Ambito);
                        SacarAmbito(caso.Ambito.TablaSimbolo);
                        break;
                    }
                } else {
                    //error de tipo
                }
                cont++;
            }
        }

        return nodo;
    }

    public void SacarAmbito(ArrayList<Simbolo> ambito) {
        int cont = ambito.size() - 1;
        while (cont >= 0) {
            if (ambito.get(cont).Rol == Constante.TVariable) {
                if (TablaHaskell.ExisteVariableTope(ambito.get(cont).Nombre)) {
                    TablaHaskell.BorrarVariable(ambito.get(cont).Nombre);
                }
            }
            cont--;
        }
    }

    public void EjecutarConsola(Simbolo accion) {
        if (accion.Rol.equals(Constante.TVariable)) {
            Variable variable = TablaHaskell.BuscarVariable(accion.Nombre);
            if (variable == null) {
                FNodoExpresion exp = (FNodoExpresion) accion.Valor;
                exp = exp.ResolverExpresion();
                if (exp.Tipo != Constante.TError) {
                    Variable porcentaje = TablaHaskell.BuscarVariable("%");
                    porcentaje.Valor = exp;
                    TablaHaskell.InsertarVariable(new Variable(Constante.TVacio, accion.Nombre, Constante.TVariable, accion.Fila, accion.Columna, accion.Ambito, exp));
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

                        case Constante.TMixto:
                            TitusNotificaciones.ImprimirConsola(String.valueOf(exp.Numero));
                    }
                } else{
                    TitusNotificaciones.ImprimirConsola(exp.Cadena);
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

                        case Constante.TMixto:
                            TitusNotificaciones.ImprimirConsola(String.valueOf(valor.Numero));
                            
                    }
                }else{
                    TitusNotificaciones.ImprimirConsola(valor.Cadena);
                }
            }
        }
    }
}
