/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones_GraphiK;

import Constante.Constante;
import Ejecucion_GraphiK.Variable;
import Interface.TitusNotificaciones;
import java.util.ArrayList;

/**
 *
 * @author Titus
 */
public class EjecutarHaskell {

    public EjecutarHaskell() {

    }

    public Variable Ejecutar(ArrayList<FNodoExpresion> parametros, String nombre, FLlamadaMetodo llamada) {
        Variable temp = new Variable(Constante.Graphik, Constante.TPublico, Constante.TError, Constante.Haskell, Constante.TVariable, llamada.Fila, llamada.Columna, null, null, null);

        ArrayList<Funciones_Haskell.FNodoExpresion> nuevospara = new ArrayList<>();

        for (FNodoExpresion a : parametros) {
            Funciones_Haskell.FNodoExpresion nuevop = ConvertiraHaskell(a);
            if (nuevop != null) {
                nuevospara.add(nuevop);
            }
        }

        Funciones_Haskell.FLlamadaMetodo metodo = new Funciones_Haskell.FLlamadaMetodo(nombre, nuevospara, llamada.Fila, llamada.Columna);

        Funciones_Haskell.FNodoExpresion resolucion = new Funciones_Haskell.FNodoExpresion();

        resolucion = resolucion.EjecutarFuncion(metodo);
        if (resolucion == null) {
            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se pudo ejecutar el metodo haskell o no existe, " + llamada.Nombre, llamada.Fila, llamada.Columna);
        } else {
            FNodoExpresion regreso = ConvertiraGrafica(resolucion);
            if (regreso != null) {
                temp.Tipo = regreso.Tipo;
                temp.Valor = regreso;
            } else {
                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se pudo convertir el resultado de haskell a graphik", llamada.Fila, llamada.Columna);
            }
        }
        return temp;
    }

    public Funciones_Haskell.FNodoExpresion ConvertiraHaskell(FNodoExpresion exp) {
        Funciones_Haskell.FNodoExpresion aux = null;
        switch (exp.Tipo) {
            case Constante.TEntero:
                aux = new Funciones_Haskell.FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, exp.Entero);
                break;

            case Constante.TDecimal:
                aux = new Funciones_Haskell.FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, exp.Decimal);
                break;

            case Constante.TCaracter:
                aux = new Funciones_Haskell.FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, exp.Caracter);
                break;

            case Constante.TCadena:
                aux = new Funciones_Haskell.FNodoExpresion(null, null, Constante.TCadena, Constante.TCadena, 0, 0, exp.Cadena);
                break;

            case Constante.TBool:
                aux = new Funciones_Haskell.FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, exp.Entero);
                break;

            case Constante.TVariableArreglo:
                Funciones_Haskell.FArreglo a = new Funciones_Haskell.FArreglo(new ArrayList<>());
                for (FNodoExpresion e : exp.ArregloResuelto.Posiciones) {
                    if (e == null) {
                        a.Arreglo.add(new Funciones_Haskell.FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, 0));
                    } else {
                        if(e.Tipo.equals(Constante.TEntero)){
                            a.Arreglo.add(new Funciones_Haskell.FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, exp.Entero));
                        }else{
                            a.Arreglo.add(new Funciones_Haskell.FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, exp.Decimal));
                        }
                        
                    }
                }
                aux = new Funciones_Haskell.FNodoExpresion(null, null, Constante.TArreglo, Constante.TArreglo, 0, 0, a);
            default:
                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se puede convertir a un valor de haskell un tipo " + exp.Tipo, exp.Fila, exp.Columna);
                break;
        }

        return aux;
    }

    public FNodoExpresion ConvertiraGrafica(Funciones_Haskell.FNodoExpresion exp) {
        FNodoExpresion nodo = null;
        switch (exp.Tipo) {
            case Constante.TDecimal:
                nodo = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, exp.Fila, exp.Columna, exp.Numero);
                break;

            case Constante.TCaracter:
                nodo = new FNodoExpresion(null, null, Constante.TCaracter, Constante.TCaracter, exp.Fila, exp.Columna, exp.Caracter);
                break;

            case Constante.TBool:
                nodo = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, exp.Fila, exp.Columna, exp.Bool);
                break;

            case Constante.TMixto:
                nodo = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, exp.Fila, exp.Columna, exp.Numero);
                break;

            case Constante.TCadena:
                nodo = new FNodoExpresion(null, null, Constante.TCadena, Constante.TCadena, exp.Fila, exp.Columna, exp.Cadena);
                break;

        }
        return nodo;
    }
}
