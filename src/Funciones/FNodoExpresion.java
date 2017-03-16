/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import Constante.Constante;

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
    }

    public FNodoExpresion ResolverExpresion() {
        return ResolverExpresion(this);
    }

    public FNodoExpresion ResolverExpresion(FNodoExpresion raiz) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, raiz.Fila, raiz.Columna, null);
        switch (raiz.Tipo) {
            case Constante.TMas:

                break;

            case Constante.TMenos:
                break;

            case Constante.TPor:
                break;

            case Constante.TDivision:
                break;

            case Constante.TPotenciaH:
                break;

            case Constante.TModulo:
                break;

            case Constante.TRaiz:
                break;

            case Constante.TMayor:
                break;

            case Constante.TMenor:
                break;

            case Constante.TMayorIgual:
                break;

            case Constante.TMenorIgual:
                break;

            case Constante.TIgualacion:
                break;

            case Constante.TDiferenciacion:
                break;

            case Constante.TOr:
                break;

            case Constante.TAnd:
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
                nodo = new FNodoExpresion(raiz);
                break;
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
                break;

            case Constante.TRevers:
                break;

            case Constante.TImpr:
                break;

            case Constante.TPar:
                break;

            case Constante.TAsc:
                break;

            case Constante.TDesc:
                break;

            case Constante.TLength:
                break;

            case Constante.TAumento:
                break;

            case Constante.TIndiceLista:
                break;

            default:
                break;

        }
        return nodo;
    }

    public FNodoExpresion FSum(FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        if (der.Tipo.equals(Constante.TArreglo)) {
            double valor = 0;
            for (FNodoExpresion a : der.Arreglo.Arreglo) {
                FNodoExpresion aux = a.ResolverExpresion();
                if (aux.Tipo.equals(Constante.TDecimal)) {
                    valor += aux.Numero;
                } else if (aux.Tipo.equals(Constante.TCaracter)) {
                    valor += aux.Caracter;
                } else {
                    //error porque no es numerico el valor
                    break;
                }
            }
        } else {
            //error, tiene que ser lista
        }
        return nodo;
    }

    public FNodoExpresion Suma(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        if (izq.Tipo.equals(Constante.TDecimal) && der.Tipo.equals(Constante.TDecimal)) {
            nodo = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, izq.Numero + der.Numero);
        } else {
            //error en suma tipo de dato
        }
        return nodo;
    }

    public FNodoExpresion Resta(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        if (izq.Tipo.equals(Constante.TDecimal) && der.Tipo.equals(Constante.TDecimal)) {
            nodo = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, izq.Numero - der.Numero);
        } else {
            //error en suma tipo de dato
        }
        return nodo;
    }

    public FNodoExpresion Multiplicacion(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        if (izq.Tipo.equals(Constante.TDecimal) && der.Tipo.equals(Constante.TDecimal)) {
            nodo = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, izq.Numero * der.Numero);
        } else {
            //error en suma tipo de dato
        }
        return nodo;
    }

    public FNodoExpresion Division(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        if (izq.Tipo.equals(Constante.TDecimal) && der.Tipo.equals(Constante.TDecimal)) {
            if (der.Numero != 0) {
                nodo = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, izq.Numero + der.Numero);
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
        if (izq.Tipo.equals(Constante.TDecimal) && der.Tipo.equals(Constante.TDecimal)) {
            if (der.Numero != 0) {
                nodo = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, izq.Numero % der.Numero);
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
        if (izq.Tipo.equals(Constante.TDecimal) && der.Tipo.equals(Constante.TDecimal)) {
            if (der.Numero != 0) {
                if (izq.Numero != 0) {
                    nodo = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, Math.pow(der.Numero, 1 / izq.Numero));
                } else {
                    //error por cero
                }
            } else {
                //error divison por cero
            }
        } else {
            //error en suma tipo de dato
        }
        return nodo;
    }

    public FNodoExpresion Potencia(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion nodo = new FNodoExpresion(null, null, Constante.TError, Constante.TError, 0, 0, null);
        if (izq.Tipo.equals(Constante.TDecimal) && der.Tipo.equals(Constante.TDecimal)) {
            if (der.Numero != 0) {
                nodo = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, 0, 0, Math.pow(izq.Numero, der.Numero));
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
        if (izq.Tipo.equals(Constante.TDecimal) && der.Tipo.equals(Constante.TDecimal)) {
            if (izq.Numero > der.Numero) {
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
        if (izq.Tipo.equals(Constante.TDecimal) && der.Tipo.equals(Constante.TDecimal)) {
            if (izq.Numero < der.Numero) {
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
        if (izq.Tipo.equals(Constante.TDecimal) && der.Tipo.equals(Constante.TDecimal)) {
            if (izq.Numero == der.Numero) {
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
        if (izq.Tipo.equals(Constante.TDecimal) && der.Tipo.equals(Constante.TDecimal)) {
            if (izq.Numero >= der.Numero) {
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
        if (izq.Tipo.equals(Constante.TDecimal) && der.Tipo.equals(Constante.TDecimal)) {
            if (izq.Numero <= der.Numero) {
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
        if (izq.Tipo.equals(Constante.TDecimal) && der.Tipo.equals(Constante.TDecimal)) {
            if (izq.Numero != der.Numero) {
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
        if (izq.Tipo.equals(Constante.TBool) && der.Tipo.equals(Constante.TBool)) {
            if (izq.Bool || der.Bool) {
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
        if (izq.Tipo.equals(Constante.TBool) && der.Tipo.equals(Constante.TBool)) {
            if (izq.Bool && der.Bool) {
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
