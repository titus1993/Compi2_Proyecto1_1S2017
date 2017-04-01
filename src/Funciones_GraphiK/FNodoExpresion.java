/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones_GraphiK;

import Constante.Constante;
import Ejecucion_GraphiK.ControlArchivo;
import Ejecucion_GraphiK.Objeto;
import Funciones_GraphiK.*;
import java.util.ArrayList;
import Interface.TitusNotificaciones;

/**
 *
 * @author Titus
 */
public class FNodoExpresion {

    public FNodoExpresion Izquierda, Derecha;
    public String Tipo, Nombre;
    public int Fila, Columna;
    public int Entero;
    public double Decimal;
    public char Caracter;
    public String Cadena;
    public boolean Bool;
    public FLlamadaObjeto Objeto;
    public Objeto Obj;
    //public FLlamadaMetodo Metodo;
    //public FArreglo Arreglo;

    public FNodoExpresion(FNodoExpresion nodo) {
        this.Izquierda = nodo.Izquierda;
        this.Derecha = nodo.Derecha;
        this.Bool = nodo.Bool;
        this.Cadena = nodo.Cadena;
        this.Caracter = nodo.Caracter;
        this.Columna = nodo.Columna;
        this.Decimal = nodo.Decimal;
        this.Entero = nodo.Entero;
        this.Fila = nodo.Fila;
        this.Nombre = nodo.Nombre;
        this.Objeto = nodo.Objeto;
        this.Tipo = nodo.Tipo;
    }

    public FNodoExpresion(FNodoExpresion izq, FNodoExpresion der, String tipo, String nombre, int fila, int columna, Object valor) {
        this.Izquierda = izq;
        this.Derecha = der;
        this.Tipo = tipo;
        this.Nombre = nombre;
        this.Fila = fila;
        this.Columna = columna;

        switch (tipo) {
            case Constante.TEntero:
                this.Entero = Integer.parseInt(valor.toString());
                this.Cadena = valor.toString();
                break;

            case Constante.TDecimal:
                this.Decimal = Double.parseDouble(valor.toString());
                this.Cadena = valor.toString();
                break;

            case Constante.TCaracter:
                this.Caracter = valor.toString().charAt(0);
                this.Cadena = valor.toString();
                break;

            case Constante.TCadena:
                this.Cadena = (String) valor;
                break;

            case Constante.TBool:
                this.Bool = Boolean.parseBoolean(valor.toString());
                this.Cadena = valor.toString();
                if (this.Bool) {
                    this.Entero = 1;
                    this.Decimal = 1;
                } else {
                    this.Entero = 0;
                    this.Decimal = 0;
                }
                break;

            case Constante.TAls:
                this.Objeto = (FLlamadaObjeto) valor;
                break;

            case Constante.TObjeto:
                this.Obj = (Objeto) valor;
                break;
        }
    }

    public FNodoExpresion ResolverExpresion(Objeto tabla) {
        return ResolverExpresion(this, tabla);
    }

    private FNodoExpresion ResolverExpresion(FNodoExpresion nodo, Objeto tabla) {
        FNodoExpresion aux = new FNodoExpresion(null, null, Constante.TError, Constante.TError, nodo.Fila, nodo.Columna, null);
        switch (nodo.Tipo) {
            case Constante.TMas:
                aux = Suma(nodo.Izquierda.ResolverExpresion(tabla), nodo.Derecha.ResolverExpresion(tabla));
                break;

            case Constante.TMenos:
                if (nodo.Izquierda != null) {
                    aux = Resta(nodo.Izquierda.ResolverExpresion(tabla), nodo.Derecha.ResolverExpresion(tabla));
                } else {
                    aux = Resta(nodo.Derecha.ResolverExpresion(tabla));
                }
                break;

            case Constante.TPor:
                aux = Suma(nodo.Izquierda.ResolverExpresion(tabla), nodo.Derecha.ResolverExpresion(tabla));
                break;

            case Constante.TDivision:
                aux = Suma(nodo.Izquierda.ResolverExpresion(tabla), nodo.Derecha.ResolverExpresion(tabla));
                break;

            case Constante.TPotenciaG:
                aux = Suma(nodo.Izquierda.ResolverExpresion(tabla), nodo.Derecha.ResolverExpresion(tabla));
                break;

            case Constante.TAumento:
                aux = Aumento(nodo.Izquierda.ResolverExpresion(tabla));
                break;

            case Constante.TDisminucion:
                aux = Aumento(nodo.Izquierda.ResolverExpresion(tabla));
                break;

            case Constante.TMayor:
                aux = Mayor(nodo.Izquierda.ResolverExpresion(tabla), nodo.Derecha.ResolverExpresion(tabla));
                break;

            case Constante.TMenor:
                aux = Menor(nodo.Izquierda.ResolverExpresion(tabla), nodo.Derecha.ResolverExpresion(tabla));
                break;

            case Constante.TMayorIgual:
                aux = MayorIgual(nodo.Izquierda.ResolverExpresion(tabla), nodo.Derecha.ResolverExpresion(tabla));
                break;

            case Constante.TMenorIgual:
                aux = MenorIgual(nodo.Izquierda.ResolverExpresion(tabla), nodo.Derecha.ResolverExpresion(tabla));
                break;

            case Constante.TIgualacion:
                aux = Igual(nodo.Izquierda.ResolverExpresion(tabla), nodo.Derecha.ResolverExpresion(tabla));
                break;

            case Constante.TDiferenciacion:
                aux = Diferente(nodo.Izquierda.ResolverExpresion(tabla), nodo.Derecha.ResolverExpresion(tabla));
                break;

            case Constante.TOr:
                aux = Or(nodo.Izquierda.ResolverExpresion(tabla), nodo.Derecha.ResolverExpresion(tabla));
                break;

            case Constante.TXor:
                aux = Xor(nodo.Izquierda.ResolverExpresion(tabla), nodo.Derecha.ResolverExpresion(tabla));
                break;

            case Constante.TAnd:
                aux = And(nodo.Izquierda.ResolverExpresion(tabla), nodo.Derecha.ResolverExpresion(tabla));
                break;

            case Constante.TNot:
                aux = Not(nodo.Derecha.ResolverExpresion(tabla));
                break;

            case Constante.TEntero:
                aux = new FNodoExpresion(nodo);
                break;

            case Constante.TDecimal:
                aux = new FNodoExpresion(nodo);
                break;

            case Constante.TCaracter:
                aux = new FNodoExpresion(nodo);
                break;

            case Constante.TCadena:
                aux = new FNodoExpresion(nodo);
                break;

            case Constante.TBool:
                aux = new FNodoExpresion(nodo);
                break;

            case Constante.TArreglo:
                break;

            case Constante.TAls:
                break;

            case Constante.TVariable:
                break;

            case Constante.TVariableArreglo:
                break;

            case Constante.TNuevo:
                aux = new FNodoExpresion(nodo);
                break;
        }
        return aux;
    }

    public Objeto Nuevo(Archivo archivo) {
        Objeto nuevo = new Objeto();
        FAls als = nuevo.BuscarAls(this.Tipo, archivo);
        if (als != null) {
            return new Objeto(als);
        }
        return null;
    }

    public FNodoExpresion Suma(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion aux = new FNodoExpresion(null, null, Constante.TError, Constante.TError, Fila, Columna, null);

        switch (izq.Tipo) {
            case Constante.TEntero:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, izq.Entero + der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Entero + der.Decimal);

                        break;

                    case Constante.TCaracter:
                        aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, izq.Entero + der.Caracter);

                        break;

                    case Constante.TCadena:
                        aux = new FNodoExpresion(null, null, Constante.TCadena, Constante.TCadena, Fila, Columna, String.valueOf(izq.Entero) + der.Cadena);

                        break;

                    case Constante.TBool:
                        aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, izq.Entero + der.Entero);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede +, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TDecimal:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Decimal + der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Decimal + der.Decimal);

                        break;

                    case Constante.TCaracter:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Decimal + der.Caracter);

                        break;

                    case Constante.TCadena:
                        aux = new FNodoExpresion(null, null, Constante.TCadena, Constante.TCadena, Fila, Columna, String.valueOf(izq.Decimal) + der.Cadena);

                        break;

                    case Constante.TBool:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Decimal + der.Entero);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede +, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TCaracter:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, izq.Caracter + der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Caracter + der.Decimal);

                        break;

                    case Constante.TCaracter:
                        aux = new FNodoExpresion(null, null, Constante.TCadena, Constante.TCadena, Fila, Columna, String.valueOf(izq.Caracter) + String.valueOf(der.Caracter));

                        break;

                    case Constante.TCadena:
                        aux = new FNodoExpresion(null, null, Constante.TCadena, Constante.TCadena, Fila, Columna, String.valueOf(izq.Caracter) + der.Cadena);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede +, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TCadena:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TCadena, Constante.TCadena, Fila, Columna, izq.Cadena + String.valueOf(der.Entero));
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TCadena, Constante.TCadena, Fila, Columna, izq.Cadena + String.valueOf(der.Decimal));
                        break;

                    case Constante.TCaracter:
                        aux = new FNodoExpresion(null, null, Constante.TCadena, Constante.TCadena, Fila, Columna, izq.Cadena + String.valueOf(der.Caracter));

                        break;

                    case Constante.TCadena:
                        aux = new FNodoExpresion(null, null, Constante.TCadena, Constante.TCadena, Fila, Columna, izq.Cadena + der.Cadena);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede +, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TBool:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, izq.Entero + der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Entero + der.Decimal);

                        break;

                    case Constante.TBool:
                        aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, izq.Bool || der.Bool);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede +, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

        }
        return aux;
    }

    public FNodoExpresion Resta(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion aux = new FNodoExpresion(null, null, Constante.TError, Constante.TError, Fila, Columna, null);

        switch (izq.Tipo) {
            case Constante.TEntero:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, izq.Entero - der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Entero - der.Decimal);

                        break;

                    case Constante.TCaracter:
                        aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, izq.Entero - der.Caracter);

                        break;

                    case Constante.TBool:
                        aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, izq.Entero - der.Entero);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede -, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TDecimal:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Decimal - der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Decimal - der.Decimal);

                        break;

                    case Constante.TCaracter:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Decimal - der.Caracter);

                        break;

                    case Constante.TBool:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Decimal - der.Entero);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede -, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TCaracter:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, izq.Caracter - der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Caracter - der.Decimal);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede -, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TBool:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, izq.Entero - der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Entero - der.Decimal);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede -, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

        }
        return aux;
    }

    public FNodoExpresion Resta(FNodoExpresion der) {
        FNodoExpresion aux = new FNodoExpresion(null, null, Constante.TError, Constante.TError, Fila, Columna, null);

        switch (der.Tipo) {
            case Constante.TEntero:
                aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, -der.Entero);
                break;

            case Constante.TDecimal:
                aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, -der.Decimal);

                break;

            case Constante.TCaracter:
                aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, -der.Caracter);

                break;

            case Constante.TBool:
                aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, -der.Entero);
                break;

            default:
                TitusNotificaciones.InsertarError(Constante.TError, "No se puede -, " + der.Tipo, Fila, Columna);
                break;
        }
        return aux;
    }

    public FNodoExpresion Multiplicacion(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion aux = new FNodoExpresion(null, null, Constante.TError, Constante.TError, Fila, Columna, null);

        switch (izq.Tipo) {
            case Constante.TEntero:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, izq.Entero * der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Entero * der.Decimal);

                        break;

                    case Constante.TCaracter:
                        aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, izq.Entero * der.Caracter);

                        break;

                    case Constante.TBool:
                        aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, izq.Entero * der.Entero);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede *, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TDecimal:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Decimal * der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Decimal * der.Decimal);

                        break;

                    case Constante.TCaracter:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Decimal * der.Caracter);

                        break;

                    case Constante.TBool:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Decimal * der.Entero);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede *, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TCaracter:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, izq.Caracter * der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Caracter * der.Decimal);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede *, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TBool:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, izq.Entero * der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Entero * der.Decimal);

                        break;

                    case Constante.TBool:
                        aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, izq.Bool && der.Bool);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede *, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

        }
        return aux;
    }

    public FNodoExpresion Division(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion aux = new FNodoExpresion(null, null, Constante.TError, Constante.TError, Fila, Columna, null);

        switch (izq.Tipo) {
            case Constante.TEntero:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Entero / der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Entero / der.Decimal);

                        break;

                    case Constante.TCaracter:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Entero / der.Caracter);

                        break;

                    case Constante.TBool:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Entero / der.Entero);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede /, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TDecimal:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Decimal / der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Decimal / der.Decimal);

                        break;

                    case Constante.TCaracter:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Decimal / der.Caracter);

                        break;

                    case Constante.TBool:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Decimal / der.Entero);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede /, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TCaracter:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Caracter / der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Caracter / der.Decimal);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede /, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TBool:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Entero / der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Entero / der.Decimal);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede /, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

        }
        return aux;
    }

    public FNodoExpresion Potencia(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion aux = new FNodoExpresion(null, null, Constante.TError, Constante.TError, Fila, Columna, null);

        switch (izq.Tipo) {
            case Constante.TEntero:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, Math.pow(izq.Entero, der.Entero));
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, Math.pow(izq.Entero, der.Decimal));

                        break;

                    case Constante.TCaracter:
                        aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, Math.pow(izq.Entero, der.Caracter));

                        break;

                    case Constante.TBool:
                        aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, Math.pow(izq.Entero, der.Entero));

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede ^, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TDecimal:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, Math.pow(izq.Decimal, der.Entero));
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, Math.pow(izq.Decimal, der.Decimal));

                        break;

                    case Constante.TCaracter:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, Math.pow(izq.Decimal, der.Caracter));

                        break;

                    case Constante.TBool:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, Math.pow(izq.Decimal, der.Entero));

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede ^, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TCaracter:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, Math.pow(izq.Caracter, der.Entero));
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, Math.pow(izq.Caracter, der.Decimal));

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede ^, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TBool:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, Math.pow(izq.Entero, der.Entero));
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, Math.pow(izq.Entero, der.Decimal));

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede ^, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

        }
        return aux;
    }

    public FNodoExpresion Aumento(FNodoExpresion izq) {
        FNodoExpresion aux = new FNodoExpresion(null, null, Constante.TError, Constante.TError, Fila, Columna, null);

        switch (izq.Tipo) {
            case Constante.TEntero:
                aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, izq.Entero + 1);
                break;

            case Constante.TDecimal:
                aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Decimal + 1);
                break;

            case Constante.TCaracter:
                aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, izq.Caracter + 1);
                break;

            default:
                TitusNotificaciones.InsertarError(Constante.TError, "No se puede ++, " + izq.Tipo, Fila, Columna);
                break;

        }
        return aux;
    }

    public FNodoExpresion Disminucion(FNodoExpresion izq) {
        FNodoExpresion aux = new FNodoExpresion(null, null, Constante.TError, Constante.TError, Fila, Columna, null);

        switch (izq.Tipo) {
            case Constante.TEntero:
                aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, izq.Entero - 1);
                break;

            case Constante.TDecimal:
                aux = new FNodoExpresion(null, null, Constante.TDecimal, Constante.TDecimal, Fila, Columna, izq.Decimal - 1);
                break;

            case Constante.TCaracter:
                aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, izq.Caracter - 1);
                break;

            default:
                TitusNotificaciones.InsertarError(Constante.TError, "No se puede ++, " + izq.Tipo, Fila, Columna);
                break;

        }
        return aux;
    }

    public FNodoExpresion Mayor(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion aux = new FNodoExpresion(null, null, Constante.TError, Constante.TError, Fila, Columna, null);

        switch (izq.Tipo) {
            case Constante.TEntero:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Entero > der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Entero > der.Decimal);

                        break;

                    case Constante.TCaracter:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Entero > der.Caracter);

                        break;

                    case Constante.TBool:
                        aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, izq.Entero > der.Entero);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede >, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TDecimal:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Decimal > der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Decimal > der.Decimal);

                        break;

                    case Constante.TCaracter:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Decimal > der.Caracter);

                        break;

                    case Constante.TBool:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Decimal > der.Entero);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede >, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TCaracter:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Caracter > der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Caracter > der.Decimal);

                        break;

                    case Constante.TCaracter:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Caracter > der.Caracter);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede >, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TBool:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Entero > der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Entero > der.Decimal);

                        break;

                    case Constante.TBool:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Entero > der.Entero);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede >, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

        }
        return aux;
    }

    public FNodoExpresion Menor(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion aux = new FNodoExpresion(null, null, Constante.TError, Constante.TError, Fila, Columna, null);

        switch (izq.Tipo) {
            case Constante.TEntero:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Entero < der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Entero < der.Decimal);

                        break;

                    case Constante.TCaracter:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Entero < der.Caracter);

                        break;

                    case Constante.TBool:
                        aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, izq.Entero < der.Entero);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede <, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TDecimal:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Decimal < der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Decimal < der.Decimal);

                        break;

                    case Constante.TCaracter:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Decimal < der.Caracter);

                        break;

                    case Constante.TBool:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Decimal < der.Entero);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede <, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TCaracter:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Caracter < der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Caracter < der.Decimal);

                        break;

                    case Constante.TCaracter:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Caracter < der.Caracter);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede >, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TBool:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Entero < der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Entero < der.Decimal);

                        break;

                    case Constante.TBool:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Entero < der.Entero);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede <, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

        }
        return aux;
    }

    public FNodoExpresion Igual(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion aux = new FNodoExpresion(null, null, Constante.TError, Constante.TError, Fila, Columna, null);

        switch (izq.Tipo) {
            case Constante.TEntero:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Entero == der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Entero == der.Decimal);

                        break;

                    case Constante.TCaracter:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Entero == der.Caracter);

                        break;

                    case Constante.TBool:
                        aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, izq.Entero == der.Entero);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede ==, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TDecimal:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Decimal == der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Decimal == der.Decimal);

                        break;

                    case Constante.TCaracter:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Decimal == der.Caracter);

                        break;

                    case Constante.TBool:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Decimal == der.Entero);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede ==, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TCaracter:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Caracter == der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Caracter == der.Decimal);

                        break;

                    case Constante.TCaracter:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Caracter == der.Caracter);

                        break;

                    case Constante.TCadena:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, String.valueOf(izq.Caracter).equals(der.Cadena));

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede ==, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TCadena:
                switch (der.Tipo) {
                    case Constante.TCadena:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Cadena.equals(der.Cadena));
                        break;

                    case Constante.TCaracter:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Cadena.equals(String.valueOf(der.Caracter)));

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede ==, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TBool:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Entero == der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Entero == der.Decimal);

                        break;

                    case Constante.TBool:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Bool == der.Bool);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede ==, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

        }
        return aux;
    }

    public FNodoExpresion Diferente(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion aux = new FNodoExpresion(null, null, Constante.TError, Constante.TError, Fila, Columna, null);

        switch (izq.Tipo) {
            case Constante.TEntero:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Entero != der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Entero != der.Decimal);

                        break;

                    case Constante.TCaracter:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Entero != der.Caracter);

                        break;

                    case Constante.TBool:
                        aux = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, Fila, Columna, izq.Entero != der.Entero);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede !=, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TDecimal:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Decimal != der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Decimal != der.Decimal);

                        break;

                    case Constante.TCaracter:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Decimal != der.Caracter);

                        break;

                    case Constante.TBool:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Decimal != der.Entero);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede !=, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TCaracter:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Caracter != der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Caracter != der.Decimal);

                        break;

                    case Constante.TCaracter:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Caracter != der.Caracter);

                        break;

                    case Constante.TCadena:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, !String.valueOf(izq.Caracter).equals(der.Cadena));

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede ==, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TCadena:
                switch (der.Tipo) {
                    case Constante.TCadena:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, !izq.Cadena.equals(der.Cadena));
                        break;

                    case Constante.TCaracter:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, !izq.Cadena.equals(String.valueOf(der.Caracter)));

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede !=, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            case Constante.TBool:
                switch (der.Tipo) {
                    case Constante.TEntero:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Entero != der.Entero);
                        break;

                    case Constante.TDecimal:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Entero != der.Decimal);

                        break;

                    case Constante.TBool:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Bool != der.Bool);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede !=, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

        }
        return aux;
    }

    public FNodoExpresion MayorIgual(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion aux = new FNodoExpresion(null, null, Constante.TError, Constante.TError, Fila, Columna, null);
        FNodoExpresion mayor = Mayor(izq, der);
        FNodoExpresion igual = Igual(izq, der);

        switch (mayor.Tipo) {
            case Constante.TBool:
                switch (igual.Tipo) {
                    case Constante.TBool:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, mayor.Bool || igual.Bool);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede >=, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;
        }
        return aux;
    }

    public FNodoExpresion MenorIgual(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion aux = new FNodoExpresion(null, null, Constante.TError, Constante.TError, Fila, Columna, null);
        FNodoExpresion menor = Menor(izq, der);
        FNodoExpresion igual = Igual(izq, der);

        switch (menor.Tipo) {
            case Constante.TBool:
                switch (igual.Tipo) {
                    case Constante.TBool:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, menor.Bool || igual.Bool);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede <=, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;
        }
        return aux;
    }

    public FNodoExpresion Or(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion aux = new FNodoExpresion(null, null, Constante.TError, Constante.TError, Fila, Columna, null);

        switch (izq.Tipo) {
            case Constante.TBool:
                switch (der.Tipo) {
                    case Constante.TBool:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Bool || der.Bool);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede ||, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            default:
                TitusNotificaciones.InsertarError(Constante.TError, "No se puede ||, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                break;
        }
        return aux;
    }

    public FNodoExpresion And(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion aux = new FNodoExpresion(null, null, Constante.TError, Constante.TError, Fila, Columna, null);

        switch (izq.Tipo) {
            case Constante.TBool:
                switch (der.Tipo) {
                    case Constante.TBool:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, izq.Bool && der.Bool);

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede &&, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            default:
                TitusNotificaciones.InsertarError(Constante.TError, "No se puede &&, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                break;
        }
        return aux;
    }

    public FNodoExpresion Not(FNodoExpresion der) {
        FNodoExpresion aux = new FNodoExpresion(null, null, Constante.TError, Constante.TError, Fila, Columna, null);

        switch (der.Tipo) {
            case Constante.TBool:
                aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, !der.Bool);

                break;

            default:
                TitusNotificaciones.InsertarError(Constante.TError, "No se puede !, " + der.Tipo, Fila, Columna);
                break;
        }

        return aux;
    }

    public FNodoExpresion Xor(FNodoExpresion izq, FNodoExpresion der) {
        FNodoExpresion aux = new FNodoExpresion(null, null, Constante.TError, Constante.TError, Fila, Columna, null);

        switch (izq.Tipo) {
            case Constante.TBool:
                switch (der.Tipo) {
                    case Constante.TBool:
                        aux = new FNodoExpresion(null, null, Constante.TBool, Constante.TBool, Fila, Columna, (!izq.Bool && der.Bool) || (izq.Bool && !der.Bool));

                        break;

                    default:
                        TitusNotificaciones.InsertarError(Constante.TError, "No se puede &|, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                        break;
                }
                break;

            default:
                TitusNotificaciones.InsertarError(Constante.TError, "No se puede &|, " + izq.Tipo + " con " + der.Tipo, Fila, Columna);
                break;
        }
        return aux;
    }
}
