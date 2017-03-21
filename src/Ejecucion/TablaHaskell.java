/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejecucion;

import Constante.Constante;
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
            var.Nombre = var.Nombre.toLowerCase();
            Tabla.add(var);        
    }

    public static void InsertarFuncion(Variable funcion) {
        Variable existe = BuscarFuncion(funcion.Nombre);
        if (existe != null) {
            BorrarFuncion(existe);
        }
        funcion.Nombre = funcion.Nombre.toLowerCase();
        Tabla.add(funcion);
    }

    public static void BorrarFuncion(Variable nombre) {

        Tabla.remove(nombre);

    }

    public static Variable BuscarVariable(String nombre) {
        int i = Tabla.size() - 1;
        Variable aux = null;
        while (i >= 0) {
            if (Tabla.get(i).Nombre.toLowerCase().equals(nombre.toLowerCase()) && Tabla.get(i).Rol.equals(Constante.TVariable)) {
                aux = Tabla.get(i);
                break;
            }
            i--;
        }
        return aux;
    }

    public static Variable BuscarFuncion(String nombre) {
        int i = 0;
        Variable aux = null;
        while (i < Tabla.size()) {
            Variable funcion = Tabla.get(i);
            if (funcion.Nombre.toLowerCase().equals(nombre.toLowerCase()) && funcion.Rol.equals(Constante.TMetodo)) {
                aux = Tabla.get(i);
                break;
            }
            i++;
        }
        return aux;
    }

    public static boolean ExisteVariableTope(String nombre) {
        return Tabla.get(Tabla.size() - 1).Nombre.toLowerCase().equals(nombre.toLowerCase());
    }

    public static void BorrarVariable(String nombre) {
        int i = Tabla.size()-1;
        while (i >= 0) {
            if (Tabla.get(i).Nombre.toLowerCase().equals(nombre.toLowerCase()) && Tabla.get(i).Rol.equals(Constante.TVariable)) {
                Tabla.remove(i);
                break;
            }
            i--;
        }
    }
}
