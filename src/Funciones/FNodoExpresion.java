/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import Constante.Constante;
import java.util.ArrayList;
import Ejecucion.*;

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
                if(variable.Valor != null){
                    FNodoExpresion val = (FNodoExpresion)variable.Valor;
                    val = val.ResolverExpresion();
                    nodo = val;
                }else{
                    //variable no inicializada
                }
            } else {
                //error no existe variable
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
                break;

            case Constante.TDesc:
                break;

            case Constante.TLength:
                nodo = FLength(metodo.Parametros.get(0));
                break;

            case Constante.TAumento:
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
                break;

        }
        return nodo;
    }

    public FNodoExpresion FIndiceLista(ArrayList<FNodoExpresion> parametros) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);

        /*if(parametros.size()==2){
            FNodoExpresion pos1 = 
        }else if(parametros.size() == 3){
            
        }
        FNodoExpresion auxder = der.ResolverExpresion();
         if (auxder.Tipo.equals(Constante.TArreglo)) {
            nodo = FSum2(auxder);
        } else {
            //error, tiene que ser lista
        }*/
        return nodo;
    }

    public FNodoExpresion FIndiceLista2(FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxder = der.ResolverExpresion();
        return nodo;
    }

    public FNodoExpresion FSucc(FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxder.Tipo.equals(Constante.TDecimal)) {
            auxder.Numero = auxder.Numero + 1;
            nodo = auxder;
        } else {
            //error en suma tipo de dato
        }
        return nodo;
    }

    public FNodoExpresion FDecc(FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxder.Tipo.equals(Constante.TDecimal)) {
            auxder.Numero = auxder.Numero - 1;
            nodo = auxder;
        } else {
            //error en suma tipo de dato
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
        }
        return nodo;
    }

    public FNodoExpresion FSum2(FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, der.Fila, der.Columna, null);

        double valor = 0;

        FNodoExpresion aux = der.ResolverExpresion();
        if (aux.Tipo.equals(Constante.TDecimal)) {
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
                    if (anterior.Tipo.equals(Constante.TDecimal) && hijoaux.Tipo.equals(Constante.TDecimal)) {
                        if (anterior.Numero < hijoaux.Numero) {
                            anterior = hijoaux;
                        }
                    } else if (anterior.Tipo.equals(Constante.TCaracter) && hijoaux.Tipo.equals(Constante.TCaracter)) {
                        if (anterior.Caracter < hijoaux.Caracter) {
                            anterior = hijoaux;
                        }
                    } else {
                        //error diferentes tipos de datos
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
                    if (anterior.Tipo.equals(Constante.TDecimal) && hijoaux.Tipo.equals(Constante.TDecimal)) {
                        if (anterior.Numero > hijoaux.Numero) {
                            anterior = hijoaux;
                        }
                    } else if (anterior.Tipo.equals(Constante.TCaracter) && hijoaux.Tipo.equals(Constante.TCaracter)) {
                        if (anterior.Caracter > hijoaux.Caracter) {
                            anterior = hijoaux;
                        }
                    } else {
                        //error diferentes tipos de datos
                    }
                }
            }
            nodo = anterior;
        } else {
            nodo = aux;
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
        }
        return nodo;
    }

    public FNodoExpresion FAsc2(FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, der.Fila, der.Columna, null);
        FNodoExpresion aux = der.ResolverExpresion();

        if (aux.Tipo.equals(Constante.TArreglo)) {
            for (int i = 0; i < aux.Arreglo.Arreglo.size() - 1; i++) {
                FNodoExpresion a = aux.Arreglo.Arreglo.get(i);
                if (a.Tipo.equals(Constante.TArreglo)) {

                    for (int j = 0; j < a.Arreglo.Arreglo.size() - 1; j++) {
                        if (a.Arreglo.Arreglo.get(j).Tipo.equals(Constante.TDecimal) && a.Arreglo.Arreglo.get(j + 1).equals(Constante.TDecimal)) {
                            if (true) {
                                //aquie me quede
                            }
                        } else if (a.Arreglo.Arreglo.get(j).Tipo.equals(Constante.TCaracter) && a.Arreglo.Arreglo.get(j + 1).equals(Constante.TCaracter)) {

                        } else {
                            //error de tipos
                        }
                    }
                }
            }

            for (FNodoExpresion a : aux.Arreglo.Arreglo) {
                if (a.Tipo.equals(Constante.TArreglo)) {
                    int j = 0;
                    for (FNodoExpresion b : a.Arreglo.Arreglo) {

                    }
                }
            }
        } else {
            //error
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
        }
        return nodo;
    }

    public FNodoExpresion FProduct2(FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, der.Fila, der.Columna, null);

        double valor = 1;

        FNodoExpresion aux = der.ResolverExpresion();
        if (aux.Tipo.equals(Constante.TDecimal)) {
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
        }
        return nodo;
    }

    public FNodoExpresion FImpr(FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, der.Fila, der.Columna, null);
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxder.Tipo.equals(Constante.TArreglo)) {
            FNodoExpresion exp = new FNodoExpresion(null, null, Constante.TArreglo, Constante.TArreglo, der.Fila, der.Columna, new FArreglo(new ArrayList<>()));
            boolean estado = true;
            for (int i = 0; i < der.Arreglo.Arreglo.size(); i++) {
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
        }
        return nodo;
    }

    public FNodoExpresion FPar(FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, der.Fila, der.Columna, null);
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxder.Tipo.equals(Constante.TArreglo)) {
            FNodoExpresion exp = new FNodoExpresion(null, null, Constante.TArreglo, Constante.TArreglo, der.Fila, der.Columna, new FArreglo(new ArrayList<>()));
            boolean estado = false;
            for (int i = 0; i < der.Arreglo.Arreglo.size(); i++) {
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
        }
        return nodo;
    }

    public FNodoExpresion FLength(FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, der.Fila, der.Columna, null);
        if (der.ResolverExpresion().Tipo.equals(Constante.TArreglo)) {
            FNodoExpresion exp = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, der.Fila, der.Columna, der.Arreglo.Arreglo.size());
            nodo = exp;
        } else {
            //error, tiene que ser lista
        }
        return nodo;
    }

    public FNodoExpresion Suma(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxizq = izq.ResolverExpresion();
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxizq.Tipo.equals(Constante.TDecimal) && auxder.Tipo.equals(Constante.TDecimal)) {
            nodo = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, auxizq.Numero + auxder.Numero);
        } else {
            //error en suma tipo de dato
        }
        return nodo;
    }

    public FNodoExpresion Resta(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        if (izq != null) {
            FNodoExpresion auxizq = izq.ResolverExpresion();
            FNodoExpresion auxder = der.ResolverExpresion();
            if (auxizq.Tipo.equals(Constante.TDecimal) && auxder.Tipo.equals(Constante.TDecimal)) {
                nodo = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, auxizq.Numero - auxder.Numero);
            } else {
                //error en suma tipo de dato
            }
        } else {
            FNodoExpresion auxder = der.ResolverExpresion();
            if (auxder.Tipo.equals(Constante.TDecimal)) {
                nodo = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, -auxder.Numero);
            } else {
                //error en suma tipo de dato
            }
        }

        return nodo;
    }

    public FNodoExpresion Multiplicacion(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxizq = izq.ResolverExpresion();
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxizq.Tipo.equals(Constante.TDecimal) && auxder.Tipo.equals(Constante.TDecimal)) {
            nodo = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, auxizq.Numero * auxder.Numero);
        } else {
            //error en suma tipo de dato
        }
        return nodo;
    }

    public FNodoExpresion Division(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxizq = izq.ResolverExpresion();
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxizq.Tipo.equals(Constante.TDecimal) && auxder.Tipo.equals(Constante.TDecimal)) {
            if (auxder.Numero != 0) {
                nodo = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, auxizq.Numero / auxder.Numero);
            } else {
                //error divison por cero
            }
        } else {
            //error en suma tipo de dato
        }
        return nodo;
    }

    public FNodoExpresion Modulo(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxizq = izq.ResolverExpresion();
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxizq.Tipo.equals(Constante.TDecimal) && auxder.Tipo.equals(Constante.TDecimal)) {
            if (auxder.Numero != 0) {
                nodo = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, auxizq.Numero % auxder.Numero);
            } else {
                //error divison por cero
            }
        } else {
            //error en suma tipo de dato
        }
        return nodo;
    }

    public FNodoExpresion Raiz(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxizq = izq.ResolverExpresion();
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxizq.Tipo.equals(Constante.TDecimal) && auxder.Tipo.equals(Constante.TDecimal)) {

            if (auxizq.Numero != 0) {
                nodo = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, Math.pow(auxder.Numero, 1 / auxizq.Numero));
            } else {
                //error por cero
            }

        } else {
            //error en suma tipo de dato
        }
        return nodo;
    }

    public FNodoExpresion Potencia(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxizq = izq.ResolverExpresion();
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxizq.Tipo.equals(Constante.TDecimal) && auxder.Tipo.equals(Constante.TDecimal)) {
            if (auxder.Numero != 0) {
                nodo = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, Math.pow(auxizq.Numero, auxder.Numero));
            } else {
                //error divison por cero
            }
        } else {
            //error en suma tipo de dato
        }
        return nodo;
    }

    public FNodoExpresion Mayor(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxizq = izq.ResolverExpresion();
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxizq.Tipo.equals(Constante.TDecimal) && auxder.Tipo.equals(Constante.TDecimal)) {
            if (auxizq.Numero > auxder.Numero) {
                nodo = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, 0, 0, true);
            } else {
                nodo = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, 0, 0, false);
            }
        } else {
            //error en suma tipo de dato
        }
        return nodo;
    }

    public FNodoExpresion Menor(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxizq = izq.ResolverExpresion();
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxizq.Tipo.equals(Constante.TDecimal) && auxder.Tipo.equals(Constante.TDecimal)) {
            if (auxizq.Numero < auxder.Numero) {
                nodo = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, 0, 0, true);
            } else {
                nodo = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, 0, 0, false);
            }
        } else {
            //error en suma tipo de dato
        }
        return nodo;
    }

    public FNodoExpresion Igualacion(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxizq = izq.ResolverExpresion();
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxizq.Tipo.equals(Constante.TDecimal) && auxder.Tipo.equals(Constante.TDecimal)) {
            if (auxizq.Numero == auxder.Numero) {
                nodo = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, 0, 0, true);
            } else {
                nodo = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, 0, 0, false);
            }
        } else {
            //error en suma tipo de dato
        }
        return nodo;
    }

    public FNodoExpresion MayorIgual(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxizq = izq.ResolverExpresion();
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxizq.Tipo.equals(Constante.TDecimal) && auxder.Tipo.equals(Constante.TDecimal)) {
            if (auxizq.Numero >= auxder.Numero) {
                nodo = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, 0, 0, true);
            } else {
                nodo = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, 0, 0, false);
            }
        } else {
            //error en suma tipo de dato
        }
        return nodo;
    }

    public FNodoExpresion MenorIgual(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxizq = izq.ResolverExpresion();
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxizq.Tipo.equals(Constante.TDecimal) && auxder.Tipo.equals(Constante.TDecimal)) {
            if (auxizq.Numero <= auxder.Numero) {
                nodo = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, 0, 0, true);
            } else {
                nodo = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, 0, 0, false);
            }
        } else {
            //error en suma tipo de dato
        }
        return nodo;
    }

    public FNodoExpresion Diferenciacion(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        FNodoExpresion auxizq = izq.ResolverExpresion();
        FNodoExpresion auxder = der.ResolverExpresion();
        if (auxizq.Tipo.equals(Constante.TDecimal) && auxder.Tipo.equals(Constante.TDecimal)) {
            if (auxizq.Numero != auxder.Numero) {
                nodo = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, 0, 0, true);
            } else {
                nodo = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, 0, 0, false);
            }
        } else {
            //error en suma tipo de dato
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
        }
        return nodo;
    }
}
