/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejecucion;

import Constante.Constante;
import Interface.TitusNotificaciones;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Titus
 */
public class TablaHaskell {

    public static List<Variable> Tabla = new LinkedList<Variable>();

    public TablaHaskell() {

    }

    public static void IniciarTabla() {
        InsertarVariable(new Variable(Constante.TVacio, "%", Constante.TVariable, 0, 0, null, null));
    }

    public static void InsertarVariable(Variable var) {
        if (BuscarVariable(var.Nombre) == null) {
            Tabla.add(var);
        }
    }

    public static void InsertarFuncion(Variable funcion) {
        Variable existe = ExisteFuncion(funcion.Nombre);
        if (existe != null) {
            BorrarFuncion(existe);
        }
        Tabla.add(funcion);
    }

    public static Variable ExisteFuncion(String nombre) {
        Variable a = null;
        for (Variable var : Tabla) {
            if (var.Rol.equals(Constante.TMetodo) && var.Nombre.equals(nombre)) {
                a = var;
                break;
            }
        }
        return a;
    }

    public static void BorrarFuncion(Variable nombre) {
        Tabla.remove(nombre);
    }

    public static Variable BuscarVariable(String nombre) {
        int i = 0;
        Variable aux = null;
        while (i < Tabla.size()) {
            if (Tabla.get(i).Nombre.equals(nombre) && Tabla.get(i).Rol.equals(Constante.TVariable)) {
                aux = Tabla.get(i);
                break;
            }
            i++;
        }
        return aux;
    }
}
