/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejecucion_GraphiK;

import Constante.Constante;
import Funciones_GraphiK.*;
import Interface.TitusNotificaciones;
import java.util.ArrayList;

/**
 *
 * @author Titus
 */
public class Arreglo {

    public FNodoExpresion Arreglo = null;
    public ArrayList<FNodoExpresion> Posiciones = new ArrayList<>();
    public ArrayList<FNodoExpresion> Dimensiones = new ArrayList<>();
    public String Nombre, Tipo, Visibilidad;
    public Integer Tamanio = 1;
    public int Fila, Columna;
    int posllenado = 0;

    public Arreglo(String visibilidad, String tipo, String nombre, ArrayList<FNodoExpresion> dimensiones, int fila, int columna, Objeto tabla) {
        this.Visibilidad = visibilidad;
        this.Tipo = tipo;
        this.Nombre = nombre;

        //calculamos el total de posiciones
        for (FNodoExpresion i : dimensiones) {
            FNodoExpresion exp = i.ResolverExpresion(tabla);
            if (exp.Tipo.equals(Constante.TEntero)) {
                if (exp.Entero > 0) {
                    Tamanio *= exp.Entero;
                    this.Dimensiones.add(exp);
                } else {
                    TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "La dimension tiene que ser mayor a 0", Fila, Columna);
                    break;
                }
            } else {
                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "El valor de la dimension tiene que ser del tipo entero", exp.Fila, exp.Columna);
                break;
            }
        }

        if (TitusNotificaciones.ContarErrores()) {
            for (int i = 0; i < Tamanio; i++) {
                this.Posiciones.add(null);
            }
        }
    }
    
    public Arreglo(FNodoExpresion exp, Objeto Tabla){
        FNodoExpresion nuevo = exp.ResolverExpresion(Tabla);
        if(nuevo.Tipo.equals(Constante.TArreglo)){
            CrearDimensiones(nuevo);            
        }
        
        //calculamos el total de posiciones
        for (FNodoExpresion i : this.Dimensiones) {
            FNodoExpresion ex = i.ResolverExpresion(Tabla);
            if (ex.Tipo.equals(Constante.TEntero)) {
                if (ex.Entero > 0) {
                    Tamanio *= ex.Entero;
                    this.Dimensiones.add(ex);
                } else {
                    TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "La dimension tiene que ser mayor a 0", Fila, Columna);
                    break;
                }
            } else {
                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "El valor de la dimension tiene que ser del tipo entero", ex.Fila, ex.Columna);
                break;
            }
        }

        if (TitusNotificaciones.ContarErrores()) {
            for (int i = 0; i < Tamanio; i++) {
                this.Posiciones.add(null);
            }
        }
    }
    
    public void InsertarDatos(FNodoExpresion datosarreglo, Objeto Tabla) {
        posllenado = 0;
        FNodoExpresion datos = datosarreglo.ResolverExpresion(Tabla);
        if (datos.Tipo.equals(Constante.TArreglo)) {
            if (ComprobarDimensiones(datos, 0)) {
                InsertarDatos2(datos);
            }
        } else if (datos.Tipo.equals(Constante.TVariableArreglo)) {
            AsignarArreglo(datos.ArregloResuelto);
        } else {
            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "Se espearaba un arreglo para la asignacion, no un tipo " + datosarreglo.Tipo, Fila, Columna);
        }
    }

    public void AsignarArreglo(Arreglo nuevo) {
        boolean estado = true;
        if (this.Dimensiones.size() == nuevo.Dimensiones.size()) {
            int i = 0;
            while (i < Dimensiones.size()) {
                if (Dimensiones.get(i).Entero == nuevo.Dimensiones.get(i).Entero) {

                } else {
                    estado = false;
                    TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No tienen el mismo tamaño las dimensiones", Fila, Columna);
                    break;
                }
                i++;
            }

            if (estado && this.Tipo.equals(nuevo.Tipo)) {
                this.Posiciones = nuevo.Posiciones;
            }else{
                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se puede asignar un arrelgo de tipo "+nuevo.Tipo + " a uno de tipo "+this.Tipo, Fila, Columna);
            }
        } else {
            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se puede asignar el arreglo porque tienen diferentes dimensiones", Fila, Columna);
        }
    }

    public void InsertarDatos2(FNodoExpresion datosarreglo) {
        for (FNodoExpresion exp : datosarreglo.Arreglo.Arreglo) {
            if (!exp.Tipo.equals(Constante.TArreglo)) {
                if (this.Tipo.equals(exp.Tipo)) {
                    this.Posiciones.remove(posllenado);
                    this.Posiciones.add(posllenado, exp);
                    posllenado++;
                } else {
                    TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se puede ingresar un valor " + exp.Tipo + " a un arreglo de tipo " + this.Tipo, Fila, Columna);
                }
            } else {
                InsertarDatos2(exp);
            }
        }
    }

    public boolean ComprobarDimensiones(FNodoExpresion datosarreglo, int nivel) {
        boolean estado = true;

        if (datosarreglo.Tipo.equals(Constante.TArreglo)) {
            if (datosarreglo.Arreglo.Dimensiones == this.Dimensiones.get(nivel).Entero) {
                estado = ComprobarDimensiones(datosarreglo.Arreglo.Arreglo.get(0), nivel + 1);
            } else {
                estado = false;
                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "El arreglo no tiene las mismas dimensiones que el valor que se le quiere asignar", Fila, Columna);
            }
        }
        return estado;
    }
    
    public void CrearDimensiones(FNodoExpresion arreglo){
        if (arreglo.Tipo.equals(Constante.TArreglo)) {
            FNodoExpresion dim = new FNodoExpresion(null, null, Constante.TEntero, Constante.TEntero, 0, 0, String.valueOf(arreglo.Arreglo.Arreglo.size()));
            this.Dimensiones.add(dim);
            if (arreglo.Arreglo.Arreglo.get(0).Tipo.equals(Constante.TArreglo)) {
                CrearDimensiones(arreglo.Arreglo.Arreglo.get(0));
            }else{
                this.Tipo = arreglo.Arreglo.Arreglo.get(0).Tipo;
            }
        }
    }
}
