/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones_Haskell;

import Ejecucion_Haskell.Haskell_Ejecucion;
import Ejecucion_Haskell.Ambito;
import Ejecucion_Haskell.TablaHaskell;
import Ejecucion_Haskell.Variable;
import Constante.Constante;
import java.util.ArrayList;
import Interface.TitusNotificaciones;

/**
 *
 * @author Titus
 */
public class FNodoExpresion {

    public FNodoExpresion Izquierda, Derecha;
    public String Tipo, Nombre, Cadena;
    public int Fila, Columna;
    public double Numero;
    public boolean Bool;
    public char Caracter;
    public FLlamadaMetodo Metodo;
    public FArreglo Arreglo;

    public FNodoExpresion(FNodoExpresion izq, FNodoExpresion der, String tipo, String nombre, int fila, int columna, Object valor) {
        this.Izquierda = izq;
        this.Derecha = der;
        this.Tipo = tipo;
        this.Nombre = nombre;
        this.Fila = fila;
        this.Columna = columna;

        switch (tipo) {
            case Constante.TDecimal:
                this.Numero = Double.parseDouble(valor.toString());
                break;

            case Constante.TCaracter:
                this.Caracter = (char) valor;
                break;

            case Constante.TCadena:
                this.Cadena = (String) valor;
                break;

            case Constante.TBool:
                this.Bool = Boolean.parseBoolean(valor.toString());
                break;

            case Constante.TMetodo:
                this.Metodo = (FLlamadaMetodo) valor;
                break;

            case Constante.TArreglo:
                this.Arreglo = (FArreglo) valor;
                break;

            case Constante.TMixto:
                this.Numero = 0;
                this.Caracter = '\0';

        }
    }

    public FNodoExpresion(FNodoExpresion nodo) {
        this.Izquierda = nodo.Izquierda;
        this.Derecha = nodo.Derecha;
        this.Tipo = nodo.Tipo;
        this.Nombre = nodo.Nombre;
        this.Cadena = nodo.Cadena;
        this.Fila = nodo.Fila;
        this.Columna = nodo.Columna;
        this.Numero = nodo.Numero;
        this.Bool = nodo.Bool;
        this.Caracter = nodo.Caracter;
        this.Metodo = nodo.Metodo;
        this.Arreglo = nodo.Arreglo;
    }

    public FNodoExpresion ResolverExpresion() {
        return ResolverExpresion(this);
    }

    public FNodoExpresion ResolverExpresion(FNodoExpresion raiz) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, raiz.Fila, raiz.Columna, null);
        switch (raiz.Tipo) {
            case Constante.TMas:
                nodo = Suma(raiz.Izquierda, raiz.Derecha);
                break;

            case Constante.TMenos:
                nodo = Resta(raiz.Izquierda, raiz.Derecha);
                break;

            case Constante.TPor:
                nodo = Multiplicacion(raiz.Izquierda, raiz.Derecha);
                break;

            case Constante.TDivision:
                nodo = Division(raiz.Izquierda, raiz.Derecha);
                break;

            case Constante.TPotenciaH:
                nodo = Potencia(raiz.Izquierda, raiz.Derecha);
                break;

            case Constante.TModulo:
                nodo = Modulo(raiz.Izquierda, raiz.Derecha);
                break;

            case Constante.TRaiz:
                nodo = Raiz(raiz.Izquierda, raiz.Derecha);
                break;

            case Constante.TMayor:
                nodo = Mayor(raiz.Izquierda, raiz.Derecha);
                break;

            case Constante.TMenor:
                nodo = Menor(raiz.Izquierda, raiz.Derecha);
                break;

            case Constante.TMayorIgual:
                nodo = MayorIgual(raiz.Izquierda, raiz.Derecha);
                break;

            case Constante.TMenorIgual:
                nodo = MenorIgual(raiz.Izquierda, raiz.Derecha);
                break;

            case Constante.TIgualacion:
                nodo = Igualacion(raiz.Izquierda, raiz.Derecha);
                break;

            case Constante.TDiferenciacion:
                nodo = Diferenciacion(raiz.Izquierda, raiz.Derecha);
                break;

            case Constante.TOr:
                nodo = Or(raiz.Izquierda, raiz.Derecha);
                break;

            case Constante.TAnd:
                nodo = And(raiz.Izquierda, raiz.Derecha);
                break;

            case Constante.TMetodo:
                nodo = LlamadaMetodo(raiz.Metodo);
                break;

            case Constante.TDecimal:
                nodo = new FNodoExpresion(raiz);
                break;

            case Constante.TCaracter:
                nodo = new FNodoExpresion(raiz);
                break;

            case Constante.TBool:
                nodo = new FNodoExpresion(raiz);
                break;

            case Constante.TMixto:
                nodo = new FNodoExpresion(raiz);
                break;

            case Constante.TCadena:
                nodo = new FNodoExpresion(raiz);
                break;

            case Constante.TArreglo:
                FNodoExpresion aux = new FNodoExpresion(null, null, Constante.TArreglo, Constante.TArreglo, raiz.Fila, raiz.Columna, new FArreglo(new ArrayList<>()));
                for (FNodoExpresion exp : raiz.Arreglo.Arreglo) {
                    aux.Arreglo.Arreglo.add(exp.ResolverExpresion());
                }
                aux.Arreglo.Dimensiones = aux.Arreglo.Arreglo.size();
                nodo = aux;
                break;

            case Constante.TVariable:
                nodo = BuscarVariable(raiz);
                break;
        }
        return nodo;
    }

    public FNodoExpresion BuscarVariable(FNodoExpresion id) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        if (id.Tipo.equals(Constante.TVariable)) {
            Variable variable = TablaHaskell.BuscarVariable(id.Nombre);
            if (variable != null) {
                if (variable.Valor != null) {
                    FNodoExpresion val = (FNodoExpresion) variable.Valor;
                    val = val.ResolverExpresion();
                    nodo = val;
                } else {
                    //variable no inicializada
                    TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "La variable " + variable.Nombre + " esta nula", Fila, Columna);
                }
            } else {
                //error no existe variable
                TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "La variable " + variable.Nombre + " no existe", Fila, Columna);
            }
        } else {

        }

        return nodo;
    }

    public FNodoExpresion LlamadaMetodo(FLlamadaMetodo metodo) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);

        switch (metodo.Nombre) {
            case Constante.TSum:
                nodo = FSum(metodo.Parametros.get(0));
                break;

            case Constante.TProduct:
                nodo = FProduct(metodo.Parametros.get(0));
                break;

            case Constante.TRevers:
                nodo = FRevers(metodo.Parametros.get(0));
                break;

            case Constante.TImpr:
                nodo = FImpr(metodo.Parametros.get(0));
                break;

            case Constante.TPar:
                nodo = FPar(metodo.Parametros.get(0));
                break;

            case Constante.TAsc:
                nodo = FAsc(metodo.Parametros.get(0));
                break;

            case Constante.TDesc:
                nodo = FDes(metodo.Parametros.get(0));
                break;

            case Constante.TLength:
                nodo = FLength(metodo.Parametros.get(0));
                break;

            case Constante.TAumento:
                nodo = FConcatenacion(metodo.Parametros);
                break;

            case Constante.TIndiceLista:
                nodo = FIndiceLista(metodo.Parametros);
                break;

            case Constante.TMin:
                nodo = FMin(metodo.Parametros.get(0));
                break;

            case Constante.TMax:
                nodo = FMax(metodo.Parametros.get(0));
                break;

            case Constante.TSucc:
                nodo = FSucc(metodo.Parametros.get(0));
                break;

            case Constante.TDecc:
                nodo = FDecc(metodo.Parametros.get(0));
                break;

            case Constante.TCalcular:
                nodo = metodo.Parametros.get(0).ResolverExpresion();
                break;

            default:
                nodo = EjecutarFuncion(metodo);
                break;

        }
        return nodo;
    }

    private FNodoExpresion EjecutarFuncion(FLlamadaMetodo llamadametodo) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);

        Variable variable = TablaHaskell.BuscarFuncion(llamadametodo.Nombre);
        if (variable != null) {
            FFuncion funcion = (FFuncion) variable.Valor;

            if (funcion.Parametros.size() == llamadametodo.Parametros.size()) {
                //metemos el retorno
                //TablaHaskell.InsertarVariable(new Variable(Constante.TRetornar, Constante.TRetornar, Constante.TRetornar, llamadametodo.Fila, llamadametodo.Columna, new Ambito(llamadametodo.Nombre), new FNodoExpresion(null, null, Constante.TMixto, Constante.TMixto, llamadametodo.Fila, llamadametodo.Columna, 0)));

                //metemos los parametros
                for (int cont = 0; cont < funcion.Parametros.size(); cont++) {
                    FNodoExpresion valorparametro = llamadametodo.Parametros.get(cont).ResolverExpresion();
                    if (TitusNotificaciones.Error.getRowCount() == 0) {
                        Variable parametro = new Variable(Constante.TVacio, funcion.Parametros.get(cont).Nombre, Constante.TVariable, llamadametodo.Parametros.get(cont).Fila, llamadametodo.Parametros.get(cont).Columna, new Ambito(llamadametodo.Parametros.get(cont).Nombre), valorparametro);
                        TablaHaskell.InsertarVariable(parametro);
                    }
                }

                //ejecutamos el metodo
                if (TitusNotificaciones.Error.getRowCount() == 0) {
                    Haskell_Ejecucion he = new Haskell_Ejecucion(funcion.Ambito);
                    //metemos el resultado de la ejecucion
                    nodo = he.EjecutarInstrucciones(funcion.Ambito);

                    he.SacarAmbito(funcion.Ambito.TablaSimbolo);
                    he.SacarAmbito(funcion.Parametros);
                }
            } else {
                nodo.Nombre = Constante.TErrorSintactico;
                nodo.Cadena = "La funcion esperaba " + llamadametodo.Nombre + " " + String.valueOf(funcion.Parametros.size()) + "parametros";
                nodo.Fila = llamadametodo.Fila;
                nodo.Columna = llamadametodo.Columna;

                TitusNotificaciones.InsertarError(nodo.Nombre, nodo.Cadena, nodo.Fila, nodo.Columna);
            }
        } else {
            nodo.Nombre = Constante.TErrorSintactico;
            nodo.Cadena = "No existe la funcion " + llamadametodo.Nombre;
            nodo.Fila = llamadametodo.Fila;
            nodo.Columna = llamadametodo.Columna;

            TitusNotificaciones.InsertarError(nodo.Nombre, nodo.Cadena, nodo.Fila, nodo.Columna);
        }

        return nodo;
    }

    public FNodoExpresion FConcatenacion(ArrayList<FNodoExpresion> parametros) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);

        FNodoExpresion lista1 = parametros.get(0).ResolverExpresion();
        FNodoExpresion lista2 = parametros.get(1).ResolverExpresion();

        if (TitusNotificaciones.Error.getRowCount() == 0) {
            if (lista1.Tipo.equals(Constante.TArreglo)) {
                if (lista2.Tipo.equals(Constante.TArreglo)) {
                    if (lista1.Arreglo.Arreglo.size() == lista2.Arreglo.Arreglo.size() || !(lista1.Arreglo.Arreglo.size() == lista2.Arreglo.Arreglo.size())) {
                        FNodoExpresion nuevoarreglo = new FNodoExpresion(null, null, Constante.TArreglo, Constante.TArreglo, 0, 0, new FArreglo(new ArrayList<>()));

                        if (lista1.Arreglo.Arreglo.size() > 0 && lista1.Arreglo.Arreglo.get(0).Tipo.equals(Constante.TArreglo)) {
                            int cont = 0;
                            while (cont < lista1.Arreglo.Arreglo.size()) {
                                nuevoarreglo.Arreglo.Arreglo.add(FConcatenar(lista1.Arreglo.Arreglo.get(cont), lista2.Arreglo.Arreglo.get(cont)));
                                cont++;
                            }
                            nodo = nuevoarreglo;
                        } else {

                            for (FNodoExpresion a : lista1.Arreglo.Arreglo) {
                                nuevoarreglo.Arreglo.Arreglo.add(a.ResolverExpresion());
                            }

                            for (FNodoExpresion a : lista2.Arreglo.Arreglo) {
                                nuevoarreglo.Arreglo.Arreglo.add(a.ResolverExpresion());
                            }
                            nodo = nuevoarreglo;
                        }
                    } else {
                        //lista con diferentes dimensiones
                        TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "La lista tiene diferentes dimensiones", Fila, Columna);
                    }
                } else {
                    //error tipo de dato
                    TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error en los tipos de datos", Fila, Columna);
                }
            } else {
                //error tiene que ser arreglo       
                TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "El valor tiene que ser de tipo arreglo", Fila, Columna);
            }
        }

        return nodo;
    }

    public FNodoExpresion FConcatenar(FNodoExpresion lista1, FNodoExpresion lista2) {

        FNodoExpresion nuevoarreglo = new FNodoExpresion(null, null, Constante.TArreglo, Constante.TArreglo, 0, 0, new FArreglo(new ArrayList<>()));

        for (FNodoExpresion a : lista1.Arreglo.Arreglo) {
            nuevoarreglo.Arreglo.Arreglo.add(a.ResolverExpresion());
        }

        for (FNodoExpresion a : lista2.Arreglo.Arreglo) {
            nuevoarreglo.Arreglo.Arreglo.add(a.ResolverExpresion());
        }

        return nuevoarreglo;
    }

    public FNodoExpresion FIndiceLista(ArrayList<FNodoExpresion> parametros) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);

        FNodoExpresion lista = parametros.get(0).ResolverExpresion();
        FNodoExpresion indice1 = parametros.get(1).ResolverExpresion();

        if (TitusNotificaciones.Error.getRowCount() == 0) {
            if (lista.Tipo.equals(Constante.TArreglo)) {
                if (indice1.Tipo.equals(Constante.TDecimal) || indice1.Tipo.equals(Constante.TMixto)) {
                    if (indice1.Numero >= 0 && indice1.Numero < lista.Arreglo.Arreglo.size()) {
                        int i = (int) indice1.Numero;
                        nodo = lista.Arreglo.Arreglo.get(i);

                    } else {
                        //lista con diferentes dimensiones
                        TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "La lista tiene diferentes dimensiones", Fila, Columna);
                    }
                } else {
                    //error tipo de dato
                    TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error en los tipos de datos", Fila, Columna);
                }
            } else {
                //error tiene que ser arreglo       
                TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "El valor tiene que ser de tipo arreglo", Fila, Columna);
            }
        }

        return nodo;
    }

    public FNodoExpresion FSucc(FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxder.Tipo.equals(Constante.TDecimal) || auxder.Tipo.equals(Constante.TMixto)) {
            auxder.Numero = auxder.Numero + 1;
            nodo = auxder;
        } else {
            //error en suma tipo de dato
            TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato", Fila, Columna);
        }
        return nodo;
    }

    public FNodoExpresion FDecc(FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxder.Tipo.equals(Constante.TDecimal) || auxder.Tipo.equals(Constante.TMixto)) {
            auxder.Numero = auxder.Numero - 1;
            nodo = auxder;
        } else {
            //error en suma tipo de dato
            TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato", Fila, Columna);
        }
        return nodo;
    }

    public FNodoExpresion FSum(FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, der.Fila, der.Columna, null);
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxder.Tipo.equals(Constante.TArreglo)) {
            nodo = FSum2(auxder);
        } else {
            //error, tiene que ser lista
            TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato", Fila, Columna);
        }
        return nodo;
    }

    public FNodoExpresion FSum2(FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, der.Fila, der.Columna, null);

        double valor = 0;

        FNodoExpresion aux = der.ResolverExpresion();
        if (aux.Tipo.equals(Constante.TDecimal) || aux.Tipo.equals(Constante.TMixto)) {
            valor += aux.Numero;
        } else if (aux.Tipo.equals(Constante.TCaracter)) {
            valor += aux.Caracter;
        } else if (aux.Tipo.equals(Constante.TArreglo)) {
            for (FNodoExpresion b : aux.Arreglo.Arreglo) {
                FNodoExpresion auxb = b.ResolverExpresion();
                valor += FSum2(auxb).Numero;
            }
        } else {
            //error porque no es numerico el valor
            TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato", Fila, Columna);
        }
        nodo.Tipo = Constante.TDecimal;
        nodo.Nombre = Constante.TDecimal;
        nodo.Numero = valor;

        return nodo;
    }

    public FNodoExpresion FMax(FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, der.Fila, der.Columna, null);
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxder.Tipo.equals(Constante.TArreglo)) {
            nodo = FMax2(auxder);
        } else {
            //error, tiene que ser lista
            TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato tiene que ser lista", Fila, Columna);
        }
        return nodo;
    }

    public FNodoExpresion FMax2(FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, der.Fila, der.Columna, null);
        FNodoExpresion aux = der.ResolverExpresion();

        if (aux.Tipo.equals(Constante.TArreglo)) {
            FNodoExpresion anterior = null;
            for (FNodoExpresion hijo : aux.Arreglo.Arreglo) {
                FNodoExpresion auxhijo = hijo.ResolverExpresion();
                FNodoExpresion hijoaux = FMax2(auxhijo);
                if (anterior == null) {
                    anterior = hijoaux;
                } else {
                    if ((anterior.Tipo.equals(Constante.TDecimal) || anterior.Tipo.equals(Constante.TMixto)) && (hijoaux.Tipo.equals(Constante.TDecimal) || hijoaux.Tipo.equals(Constante.TMixto))) {
                        if (anterior.Numero < hijoaux.Numero) {
                            anterior = hijoaux;
                        }
                    } else if ((anterior.Tipo.equals(Constante.TCaracter) || anterior.Tipo.equals(Constante.TMixto)) && (hijoaux.Tipo.equals(Constante.TCaracter) || hijoaux.Tipo.equals(Constante.TMixto))) {
                        if (anterior.Caracter < hijoaux.Caracter) {
                            anterior = hijoaux;
                        }
                    } else {
                        //error diferentes tipos de datos
                        TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato", Fila, Columna);
                    }
                }
            }
            nodo = anterior;
        } else {
            nodo = aux;
        }
        return nodo;
    }

    public FNodoExpresion FMin(FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, der.Fila, der.Columna, null);
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxder.Tipo.equals(Constante.TArreglo)) {
            nodo = FMin2(auxder);
        } else {
            //error, tiene que ser lista
            TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato", Fila, Columna);
        }
        return nodo;
    }

    public FNodoExpresion FMin2(FNodoExpresion der) {
        FNodoExpresion nodo;
        FNodoExpresion aux = der.ResolverExpresion();

        if (aux.Tipo.equals(Constante.TArreglo)) {
            FNodoExpresion anterior = null;
            for (FNodoExpresion hijo : aux.Arreglo.Arreglo) {
                FNodoExpresion auxhijo = hijo.ResolverExpresion();
                FNodoExpresion hijoaux = FMin2(auxhijo);
                if (anterior == null) {
                    anterior = hijoaux;
                } else {
                    if ((anterior.Tipo.equals(Constante.TDecimal) || anterior.Tipo.equals(Constante.TMixto)) && (hijoaux.Tipo.equals(Constante.TDecimal) || hijoaux.Tipo.equals(Constante.TMixto))) {
                        if (anterior.Numero > hijoaux.Numero) {
                            anterior = hijoaux;
                        }
                    } else if ((anterior.Tipo.equals(Constante.TCaracter) || anterior.Tipo.equals(Constante.TMixto)) && (hijoaux.Tipo.equals(Constante.TCaracter) || hijoaux.Tipo.equals(Constante.TMixto))) {
                        if (anterior.Caracter > hijoaux.Caracter) {
                            anterior = hijoaux;
                        }
                    } else {
                        //error diferentes tipos de datos
                        TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato", Fila, Columna);
                    }
                }
            }
            nodo = anterior;
        } else {
            nodo = aux;
        }
        return nodo;
    }

    public FNodoExpresion FDes(FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, der.Fila, der.Columna, null);
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxder.Tipo.equals(Constante.TArreglo)) {
            nodo = FDes2(auxder);
        } else {
            //error, tiene que ser lista
            TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato tiene que ser lista", Fila, Columna);
        }
        return nodo;
    }

    public FNodoExpresion FDes2(FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, der.Fila, der.Columna, null);
        FNodoExpresion aux = der.ResolverExpresion();

        if (aux.Tipo.equals(Constante.TArreglo)) {
            if (aux.Arreglo.Arreglo.size() > 0) {
                if (aux.Arreglo.Arreglo.get(0).Tipo.equals(Constante.TArreglo)) {
                    FNodoExpresion h = new FNodoExpresion(null, null, Constante.TArreglo, Constante.TArreglo, 0, 0, new FArreglo(new ArrayList<>()));
                    for (FNodoExpresion x : aux.Arreglo.Arreglo) {
                        FNodoExpresion hijo = new FNodoExpresion(null, null, Constante.TArreglo, Constante.TArreglo, 0, 0, new FArreglo(new ArrayList<>()));
                        int size = x.Arreglo.Arreglo.size();
                        for (int i = 0; i < size; i++) {
                            FNodoExpresion hijomayor = FMin(x);
                            for (int j = 0; j < x.Arreglo.Arreglo.size(); j++) {
                                FNodoExpresion hijoaux = x.Arreglo.Arreglo.get(j);
                                if (hijomayor.Tipo.equals(Constante.TDecimal) || hijomayor.Tipo.equals(Constante.TMixto)) {
                                    if (hijomayor.Numero == hijoaux.Numero) {
                                        x.Arreglo.Arreglo.remove(j);
                                        break;
                                    }
                                } else if (hijomayor.Tipo.equals(Constante.TCaracter)) {
                                    if (hijomayor.Caracter == hijoaux.Caracter) {
                                        x.Arreglo.Arreglo.remove(j);
                                        break;
                                    }
                                }
                            }
                            hijo.Arreglo.Arreglo.add(hijomayor);

                        }
                        h.Arreglo.Arreglo.add(hijo);
                    }
                    nodo = h;

                } else {
                    FNodoExpresion hijo = new FNodoExpresion(null, null, Constante.TArreglo, Constante.TArreglo, 0, 0, new FArreglo(new ArrayList<>()));
                    int size = aux.Arreglo.Arreglo.size();
                    for (int i = 0; i < size; i++) {
                        FNodoExpresion hijomayor = FMin(aux);
                        for (int j = 0; j < aux.Arreglo.Arreglo.size(); j++) {
                            FNodoExpresion hijoaux = aux.Arreglo.Arreglo.get(j);
                            if (hijomayor.Tipo.equals(Constante.TDecimal) || hijomayor.Tipo.equals(Constante.TMixto)) {
                                if (hijomayor.Numero == hijoaux.Numero) {
                                    aux.Arreglo.Arreglo.remove(j);
                                    break;
                                }
                            } else if (hijomayor.Tipo.equals(Constante.TCaracter)) {
                                if (hijomayor.Caracter == hijoaux.Caracter) {
                                    aux.Arreglo.Arreglo.remove(j);
                                    break;
                                }
                            }
                        }
                        hijo.Arreglo.Arreglo.add(hijomayor);

                    }
                    nodo = hijo;
                }
            }
        } else {
            //error
            TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato tiene que ser lista", Fila, Columna);
        }
        return nodo;
    }

    public FNodoExpresion FAsc(FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, der.Fila, der.Columna, null);
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxder.Tipo.equals(Constante.TArreglo)) {
            nodo = FAsc2(auxder);
        } else {
            //error, tiene que ser lista
            TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato tiene que ser lista", Fila, Columna);
        }
        return nodo;
    }

    public FNodoExpresion FAsc2(FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, der.Fila, der.Columna, null);
        FNodoExpresion aux = der.ResolverExpresion();

        if (aux.Tipo.equals(Constante.TArreglo)) {
            if (aux.Arreglo.Arreglo.size() > 0) {
                if (aux.Arreglo.Arreglo.get(0).Tipo.equals(Constante.TArreglo)) {
                    FNodoExpresion h = new FNodoExpresion(null, null, Constante.TArreglo, Constante.TArreglo, 0, 0, new FArreglo(new ArrayList<>()));
                    for (FNodoExpresion x : aux.Arreglo.Arreglo) {
                        FNodoExpresion hijo = new FNodoExpresion(null, null, Constante.TArreglo, Constante.TArreglo, 0, 0, new FArreglo(new ArrayList<>()));
                        int size = x.Arreglo.Arreglo.size();
                        for (int i = 0; i < size; i++) {
                            FNodoExpresion hijomayor = FMax(x);
                            for (int j = 0; j < x.Arreglo.Arreglo.size(); j++) {
                                FNodoExpresion hijoaux = x.Arreglo.Arreglo.get(j);
                                if (hijomayor.Tipo.equals(Constante.TDecimal) || hijomayor.Tipo.equals(Constante.TMixto)) {
                                    if (hijomayor.Numero == hijoaux.Numero) {
                                        x.Arreglo.Arreglo.remove(j);
                                        break;
                                    }
                                } else if (hijomayor.Tipo.equals(Constante.TCaracter)) {
                                    if (hijomayor.Caracter == hijoaux.Caracter) {
                                        x.Arreglo.Arreglo.remove(j);
                                        break;
                                    }
                                }
                            }
                            hijo.Arreglo.Arreglo.add(hijomayor);

                        }
                        h.Arreglo.Arreglo.add(hijo);
                    }
                    nodo = h;

                } else {
                    FNodoExpresion hijo = new FNodoExpresion(null, null, Constante.TArreglo, Constante.TArreglo, 0, 0, new FArreglo(new ArrayList<>()));
                    int size = aux.Arreglo.Arreglo.size();
                    for (int i = 0; i < size; i++) {
                        FNodoExpresion hijomayor = FMax(aux);
                        for (int j = 0; j < aux.Arreglo.Arreglo.size(); j++) {
                            FNodoExpresion hijoaux = aux.Arreglo.Arreglo.get(j);
                            if (hijomayor.Tipo.equals(Constante.TDecimal) || hijomayor.Tipo.equals(Constante.TMixto)) {
                                if (hijomayor.Numero == hijoaux.Numero) {
                                    aux.Arreglo.Arreglo.remove(j);
                                    break;
                                }
                            } else if (hijomayor.Tipo.equals(Constante.TCaracter)) {
                                if (hijomayor.Caracter == hijoaux.Caracter) {
                                    aux.Arreglo.Arreglo.remove(j);
                                    break;
                                }
                            }
                        }
                        hijo.Arreglo.Arreglo.add(hijomayor);

                    }
                    nodo = hijo;
                }
            }
        } else {
            //error
            TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato tiene que ser lista", Fila, Columna);
        }
        return nodo;
    }

    public FNodoExpresion FProduct(FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, der.Fila, der.Columna, null);
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxder.Tipo.equals(Constante.TArreglo)) {
            nodo = FProduct2(auxder);
        } else {
            //error, tiene que ser lista
            TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato tiene que ser lista", Fila, Columna);
        }
        return nodo;
    }

    public FNodoExpresion FProduct2(FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, der.Fila, der.Columna, null);

        double valor = 1;

        FNodoExpresion aux = der.ResolverExpresion();
        if (aux.Tipo.equals(Constante.TDecimal) || aux.Tipo.equals(Constante.TMixto)) {
            valor *= aux.Numero;
        } else if (aux.Tipo.equals(Constante.TCaracter)) {
            valor *= aux.Caracter;
        } else if (aux.Tipo.equals(Constante.TArreglo)) {
            for (FNodoExpresion b : aux.Arreglo.Arreglo) {
                FNodoExpresion auxb = b.ResolverExpresion();
                valor *= FProduct2(auxb).Numero;
            }
        } else {
            //error porque no es numerico el valor
            TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato", Fila, Columna);
        }
        nodo.Tipo = Constante.TDecimal;
        nodo.Nombre = Constante.TDecimal;
        nodo.Numero = valor;

        return nodo;
    }

    public FNodoExpresion FRevers(FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, der.Fila, der.Columna, null);
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxder.Tipo.equals(Constante.TArreglo)) {
            FNodoExpresion exp = new FNodoExpresion(null, null, Constante.TArreglo, Constante.TArreglo, der.Fila, der.Columna, new FArreglo(new ArrayList<>()));
            for (int i = auxder.Arreglo.Arreglo.size() - 1; i >= 0; i--) {
                FNodoExpresion auxexp = auxder.Arreglo.Arreglo.get(i).ResolverExpresion();
                exp.Arreglo.Arreglo.add(auxexp);
            }
            exp.Arreglo.Dimensiones = exp.Arreglo.Arreglo.size();
            nodo = exp;
        } else {
            //error, tiene que ser lista
            TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato tiene que ser lista", Fila, Columna);
        }
        return nodo;
    }

    public FNodoExpresion FImpr(FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, der.Fila, der.Columna, null);
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxder.Tipo.equals(Constante.TArreglo)) {
            FNodoExpresion exp = new FNodoExpresion(null, null, Constante.TArreglo, Constante.TArreglo, der.Fila, der.Columna, new FArreglo(new ArrayList<>()));
            boolean estado = true;
            for (int i = 0; i < auxder.Arreglo.Arreglo.size(); i++) {
                if (estado) {
                    FNodoExpresion auxexp = auxder.Arreglo.Arreglo.get(i).ResolverExpresion();
                    exp.Arreglo.Arreglo.add(auxexp);
                }
                estado = !estado;
            }
            exp.Arreglo.Dimensiones = exp.Arreglo.Arreglo.size();
            nodo = exp;
        } else {
            //error, tiene que ser lista
            TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato tiene que ser lista", Fila, Columna);
        }
        return nodo;
    }

    public FNodoExpresion FPar(FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, der.Fila, der.Columna, null);
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxder.Tipo.equals(Constante.TArreglo)) {
            FNodoExpresion exp = new FNodoExpresion(null, null, Constante.TArreglo, Constante.TArreglo, der.Fila, der.Columna, new FArreglo(new ArrayList<>()));
            boolean estado = false;
            for (int i = 0; i < auxder.Arreglo.Arreglo.size(); i++) {
                if (estado) {
                    FNodoExpresion auxexp = auxder.Arreglo.Arreglo.get(i).ResolverExpresion();
                    exp.Arreglo.Arreglo.add(auxexp);
                }
                estado = !estado;
            }
            exp.Arreglo.Dimensiones = exp.Arreglo.Arreglo.size();
            nodo = exp;
        } else {
            //error, tiene que ser lista
            TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato tiene que ser lista", Fila, Columna);
        }
        return nodo;
    }

    public FNodoExpresion FLength(FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, der.Fila, der.Columna, null);
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxder.Tipo.equals(Constante.TArreglo)) {
            FNodoExpresion exp = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, auxder.Fila, auxder.Columna, auxder.Arreglo.Arreglo.size());
            nodo = exp;
        } else {
            //error, tiene que ser lista
             TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato tiene que ser lista", Fila, Columna);
        }
        return nodo;
    }

    public FNodoExpresion Suma(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxizq = izq.ResolverExpresion();
        FNodoExpresion auxder = der.ResolverExpresion();
        if ((auxizq.Tipo.equals(Constante.TDecimal) || auxizq.Tipo.equals(Constante.TMixto)) && (auxder.Tipo.equals(Constante.TDecimal) || auxder.Tipo.equals(Constante.TMixto))) {
            nodo = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, auxizq.Numero + auxder.Numero);
        } else {
            //error en suma tipo de dato
             TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato", Fila, Columna);
        }
        return nodo;
    }

    public FNodoExpresion Resta(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        if (izq != null) {
            FNodoExpresion auxizq = izq.ResolverExpresion();
            FNodoExpresion auxder = der.ResolverExpresion();
            if ((auxizq.Tipo.equals(Constante.TDecimal) || auxizq.Tipo.equals(Constante.TMixto)) && (auxder.Tipo.equals(Constante.TDecimal) || auxder.Tipo.equals(Constante.TMixto))) {
                nodo = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, auxizq.Numero - auxder.Numero);
            } else {
                //error en suma tipo de dato
                TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato", Fila, Columna);
            }
        } else {
            FNodoExpresion auxder = der.ResolverExpresion();
            if ((auxder.Tipo.equals(Constante.TDecimal) || auxder.Tipo.equals(Constante.TMixto))) {
                nodo = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, -auxder.Numero);
            } else {
                //error en suma tipo de dato
                TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato", Fila, Columna);
            }
        }

        return nodo;
    }

    public FNodoExpresion Multiplicacion(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxizq = izq.ResolverExpresion();
        FNodoExpresion auxder = der.ResolverExpresion();
        if ((auxizq.Tipo.equals(Constante.TDecimal) || auxizq.Tipo.equals(Constante.TMixto)) && (auxder.Tipo.equals(Constante.TDecimal) || auxder.Tipo.equals(Constante.TMixto))) {
            nodo = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, auxizq.Numero * auxder.Numero);
        } else {
            //error en suma tipo de dato
            TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato", Fila, Columna);
        }
        return nodo;
    }

    public FNodoExpresion Division(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxizq = izq.ResolverExpresion();
        FNodoExpresion auxder = der.ResolverExpresion();
        if ((auxizq.Tipo.equals(Constante.TDecimal) || auxizq.Tipo.equals(Constante.TMixto)) && (auxder.Tipo.equals(Constante.TDecimal) || auxder.Tipo.equals(Constante.TMixto))) {
            if (auxder.Numero != 0) {
                nodo = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, auxizq.Numero / auxder.Numero);
            } else {
                //error divison por cero
                TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error division por cero", Fila, Columna);
            }
        } else {
            //error en suma tipo de dato
            TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato", Fila, Columna);
        }
        return nodo;
    }

    public FNodoExpresion Modulo(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxizq = izq.ResolverExpresion();
        FNodoExpresion auxder = der.ResolverExpresion();
        if ((auxizq.Tipo.equals(Constante.TDecimal) || auxizq.Tipo.equals(Constante.TMixto)) && (auxder.Tipo.equals(Constante.TDecimal) || auxder.Tipo.equals(Constante.TMixto))) {
            if (auxder.Numero != 0) {
                nodo = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, auxizq.Numero % auxder.Numero);
            } else {
                //error divison por cero
                TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error division por cero", Fila, Columna);
            }
        } else {
            //error en suma tipo de dato
            TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato", Fila, Columna);
        }
        return nodo;
    }

    public FNodoExpresion Raiz(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxizq = izq.ResolverExpresion();
        FNodoExpresion auxder = der.ResolverExpresion();
        if ((auxizq.Tipo.equals(Constante.TDecimal) || auxizq.Tipo.equals(Constante.TMixto)) && (auxder.Tipo.equals(Constante.TDecimal) || auxder.Tipo.equals(Constante.TMixto))) {

            if (auxizq.Numero != 0) {
                nodo = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, Math.pow(auxder.Numero, 1 / auxizq.Numero));
            } else {
                //error por cero
                TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error division por cero", Fila, Columna);
            }

        } else {
            //error en suma tipo de dato
            TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato", Fila, Columna);
        }
        return nodo;
    }

    public FNodoExpresion Potencia(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxizq = izq.ResolverExpresion();
        FNodoExpresion auxder = der.ResolverExpresion();
        if ((auxizq.Tipo.equals(Constante.TDecimal) || auxizq.Tipo.equals(Constante.TMixto)) && (auxder.Tipo.equals(Constante.TDecimal) || auxder.Tipo.equals(Constante.TMixto))) {
            if (auxder.Numero != 0) {
                nodo = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, Math.pow(auxizq.Numero, auxder.Numero));
            } else {
                //error divison por cero
                TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error divison por cero", Fila, Columna);
            }
        } else {
            //error en suma tipo de dato
            TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato", Fila, Columna);
        }
        return nodo;
    }

    public FNodoExpresion Mayor(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxizq = izq.ResolverExpresion();
        FNodoExpresion auxder = der.ResolverExpresion();
        if ((auxizq.Tipo.equals(Constante.TDecimal) || auxizq.Tipo.equals(Constante.TMixto)) && (auxder.Tipo.equals(Constante.TDecimal) || auxder.Tipo.equals(Constante.TMixto))) {
            if (auxizq.Numero > auxder.Numero) {
                nodo = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, 0, 0, true);
            } else {
                nodo = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, 0, 0, false);
            }
        } else {
            //error en suma tipo de dato
             TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato", Fila, Columna);
        }
        return nodo;
    }

    public FNodoExpresion Menor(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxizq = izq.ResolverExpresion();
        FNodoExpresion auxder = der.ResolverExpresion();
        if ((auxizq.Tipo.equals(Constante.TDecimal) || auxizq.Tipo.equals(Constante.TMixto)) && (auxder.Tipo.equals(Constante.TDecimal) || auxder.Tipo.equals(Constante.TMixto))) {
            if (auxizq.Numero < auxder.Numero) {
                nodo = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, 0, 0, true);
            } else {
                nodo = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, 0, 0, false);
            }
        } else {
            //error en suma tipo de dato
            TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato", Fila, Columna);
        }
        return nodo;
    }

    public FNodoExpresion Igualacion(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxizq = izq.ResolverExpresion();
        FNodoExpresion auxder = der.ResolverExpresion();
        if ((auxizq.Tipo.equals(Constante.TDecimal) || auxizq.Tipo.equals(Constante.TMixto)) && (auxder.Tipo.equals(Constante.TDecimal) || auxder.Tipo.equals(Constante.TMixto))) {
            if (auxizq.Numero == auxder.Numero) {
                nodo = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, 0, 0, true);
            } else {
                nodo = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, 0, 0, false);
            }
        } else {
            //error en suma tipo de dato
            TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato", Fila, Columna);
        }
        return nodo;
    }

    public FNodoExpresion MayorIgual(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxizq = izq.ResolverExpresion();
        FNodoExpresion auxder = der.ResolverExpresion();
        if ((auxizq.Tipo.equals(Constante.TDecimal) || auxizq.Tipo.equals(Constante.TMixto)) && (auxder.Tipo.equals(Constante.TDecimal) || auxder.Tipo.equals(Constante.TMixto))) {
            if (auxizq.Numero >= auxder.Numero) {
                nodo = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, 0, 0, true);
            } else {
                nodo = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, 0, 0, false);
            }
        } else {
            //error en suma tipo de dato
             TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato", Fila, Columna);
        }
        return nodo;
    }

    public FNodoExpresion MenorIgual(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxizq = izq.ResolverExpresion();
        FNodoExpresion auxder = der.ResolverExpresion();
        if ((auxizq.Tipo.equals(Constante.TDecimal) || auxizq.Tipo.equals(Constante.TMixto)) && (auxder.Tipo.equals(Constante.TDecimal) || auxder.Tipo.equals(Constante.TMixto))) {
            if (auxizq.Numero <= auxder.Numero) {
                nodo = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, 0, 0, true);
            } else {
                nodo = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, 0, 0, false);
            }
        } else {
            //error en suma tipo de dato
             TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato", Fila, Columna);
        }
        return nodo;
    }

    public FNodoExpresion Diferenciacion(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxizq = izq.ResolverExpresion();
        FNodoExpresion auxder = der.ResolverExpresion();
        if ((auxizq.Tipo.equals(Constante.TDecimal) || auxizq.Tipo.equals(Constante.TMixto)) && (auxder.Tipo.equals(Constante.TDecimal) || auxder.Tipo.equals(Constante.TMixto))) {
            if (auxizq.Numero != auxder.Numero) {
                nodo = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, 0, 0, true);
            } else {
                nodo = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, 0, 0, false);
            }
        } else {
            //error en suma tipo de dato
             TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato", Fila, Columna);
        }
        return nodo;
    }

    public FNodoExpresion Or(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxizq = izq.ResolverExpresion();
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxizq.Tipo.equals(Constante.TBool) && auxder.Tipo.equals(Constante.TBool)) {
            if (auxizq.Bool || auxder.Bool) {
                nodo = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, 0, 0, true);
            } else {
                nodo = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, 0, 0, false);
            }
        } else {
            //error en suma tipo de dato
             TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato", Fila, Columna);
        }
        return nodo;
    }

    public FNodoExpresion And(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxizq = izq.ResolverExpresion();
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxizq.Tipo.equals(Constante.TBool) && auxder.Tipo.equals(Constante.TBool)) {
            if (auxizq.Bool && auxder.Bool) {
                nodo = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, 0, 0, true);
            } else {
                nodo = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, 0, 0, false);
            }
        } else {
            //error en suma tipo de dato
             TitusNotificaciones.InsertarError(Constante.TErrorSintactico, "Error tipo de dato", Fila, Columna);
        }
        return nodo;
    }
}
