/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones_Haskell;

import java.util.ArrayList;
import Constante.Constante;
import Interface.TitusNotificaciones;

/**
 *
 * @author Titus
 */
public class FArreglo {

    public ArrayList<FNodoExpresion> Arreglo = null;
    public int Dimensiones;
    public String Tipo;

    public FArreglo(ArrayList<FNodoExpresion> arreglo) {
        this.Arreglo = arreglo;
        this.Dimensiones = arreglo.size();
        //this.Tipo = ObtenerTipo(arreglo);
        ComprobarNiveles(this.Arreglo);
    }

    public FArreglo(String cadena, int fila, int columna) {
        this.Arreglo = new ArrayList<>();
        cadena = cadena.substring(1, cadena.length() - 1);
        for (char c : cadena.toCharArray()) {
            this.Arreglo.add(new FNodoExpresion(null, null, Constante.TCaracter, Constante.TCaracter, fila, columna, c));
            fila++;
            columna++;
        }
        this.Dimensiones = this.Arreglo.size();
        this.Tipo = ObtenerTipo(this.Arreglo);
        ComprobarNiveles(this.Arreglo);
    }

    public String ObtenerCadena() {
        String cad = "";
        String aux = "";
        for (FNodoExpresion d : Arreglo) {
            FNodoExpresion auxd = d.ResolverExpresion();
            cad = aux + ObtenerCadena(auxd);
            aux = aux + ObtenerCadena(auxd) + ", ";
        }
        cad = "[" + cad + "]";
        return cad;
    }

    private String ObtenerCadena(FNodoExpresion exp) {
        String cad = "";
        if (exp.Tipo.equals(Constante.TArreglo)) {
            cad += "[";
            String aux = "";
            for (FNodoExpresion d : exp.Arreglo.Arreglo) {
                FNodoExpresion auxd = d.ResolverExpresion();
                cad = aux + ObtenerCadena(auxd);
                aux = aux + ObtenerCadena(auxd) + ", ";
            }
            cad = "[" + cad + "]";
        } else if (exp.Tipo.equals(Constante.TDecimal)) {
            cad += String.valueOf(exp.Numero);
        } else if (exp.Tipo.equals(Constante.TCaracter)) {
            cad += String.valueOf(exp.Caracter);
        }
        return cad;
    }

    private String ObtenerTipo(ArrayList<FNodoExpresion> arreglo) {
        if (arreglo.size() > 0) {
            FNodoExpresion nodo = arreglo.get(0);
            if (nodo.Tipo.equals(Constante.TArreglo)) {
                nodo.Arreglo.Tipo = ObtenerTipo(nodo.Arreglo.Arreglo);
                return nodo.Arreglo.Tipo;
            } else {
                return nodo.Tipo;
            }
        } else {
            return Constante.TVacio;
        }
    }

    private void ComprobarNiveles(ArrayList<FNodoExpresion> arreglo) {
        if (arreglo.size() > 0) {
            FNodoExpresion nodo = arreglo.get(0);
            if (Constante.TArreglo.equals(nodo.Tipo)) {//si tiene 2 niveles
                for (FNodoExpresion expresion : arreglo) {
                    if (expresion.Tipo.equals(Constante.TArreglo)) {
                        if (nodo.Arreglo.Dimensiones == expresion.Arreglo.Dimensiones) {
                            for (FNodoExpresion expresion2 : expresion.Arreglo.Arreglo) {
                                if (!Constante.TArreglo.equals(expresion2.Tipo)) {
                                    /*if (!this.Tipo.equals(expresion2.Tipo)) {
                                        TitusNotificaciones.ImprimirConsola("error en tipo de dato");
                                    }*/
                                } else {
                                    TitusNotificaciones.ImprimirConsola("el Arreglo cuenta con mas de 2 dimensiones");
                                }
                            }
                        } else {
                            TitusNotificaciones.ImprimirConsola("Las listas internas no son del mismo tamaño");
                        }

                    } else {
                        TitusNotificaciones.ImprimirConsola("No todas las dimensiones son listas");
                    }
                }
            } else {//si tiene un nivel
                for (FNodoExpresion expresion : arreglo) {
                    if (!expresion.Tipo.equals(Constante.TArreglo)) {
                        /*if (!this.Tipo.equals(expresion.Tipo)) {
                            TitusNotificaciones.ImprimirConsola("error en tipo de dato");
                        }*/
                    } else {
                        TitusNotificaciones.ImprimirConsola("Error de nivel 1");
                    }
                }
            }
        }

    }
}
