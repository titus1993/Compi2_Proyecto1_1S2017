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
public class TablaGraphik {

    public ArrayList<Variable> Tabla;

    public TablaGraphik() {
        this.Tabla = new ArrayList<>();
        
    }

    public void ApartarInicio(){
        Tabla.add(null);
    }
    
    public void InsertarVariable(Variable var) {
        var.Nombre = var.Nombre.toLowerCase();
        Tabla.add(var);
    }

    public void SacarVariable() {
        Tabla.remove(Tabla.size() - 1);
    }

    public Variable BuscarFuncion(FLlamadaMetodo metodo) {
        int i = 0;
        Variable aux = null;
        while (i < Tabla.size()) {
            Variable funcion = Tabla.get(i);
            if (funcion.Nombre.toLowerCase().equals(metodo.Nombre.toLowerCase()) && funcion.Rol.equals(Constante.TMetodo)) {
                
                aux = Tabla.get(i);
                return aux;
            }
            i++;
        }
        
        //si no existe en la tabla actual buscamos en los heredas
        Variable heredas = BuscarHerencia();
        if(heredas != null){
            aux = heredas.Padre.TablaVariables.BuscarFuncion(metodo);
        }
        //ahora buscamos en la herencia
        
        return aux;
    }
    
    public Variable BuscarHerencia() {
        int i = 0;
        Variable aux = null;
        while (i < Tabla.size()) {
            Variable funcion = Tabla.get(i);
            if (funcion.Nombre.toLowerCase().equals(Constante.THereda) && funcion.Rol.equals(Constante.THereda)) {
                aux = Tabla.get(i);
                break;
            }
            i++;
        }
        return aux;
    }

    public void BorrarVariable(String nombre) {
        int i = Tabla.size() - 1;
        while (i >= 0) {
            if (Tabla.get(i).Nombre.toLowerCase().equals(nombre.toLowerCase()) && Tabla.get(i).Rol.equals(Constante.TVariable)) {
                Tabla.remove(i);
                break;
            }
            i--;
        }
    }

    public boolean BuscarNombre(String nombre) {
        int cont = Tabla.size() - 1;
        boolean encontrado = false;
        while (cont >= 0 && TitusNotificaciones.ContarErrores() && !encontrado) {
            if (Tabla.get(cont).Nombre.equals(nombre)) {
                encontrado = !encontrado;
            }
            cont--;
        }
        return encontrado;
    }

    public Variable BuscarVariable(String nombre) {
        int cont = Tabla.size() - 1;
        boolean encontrado = false;
        Variable variable = null;
        while (cont >= 0 && TitusNotificaciones.ContarErrores() && !encontrado) {
            if (Tabla.get(cont).Nombre.toLowerCase().equals(nombre.toLowerCase()) && (Tabla.get(cont).Rol.equals(Constante.TVariable) || Tabla.get(cont).Rol.equals(Constante.TVariableArreglo))) {
                variable = Tabla.get(cont);
                encontrado = !encontrado;
                break;
            }
            cont--;
        }
        return variable;
    }

    //si no funciona enviar la tabla para resolverlo
    public Variable BuscarMetodo(String nombre, FLlamadaMetodo lista) {
        int cont = Tabla.size() - 1;
        boolean encontrado = false;
        Variable variable = null;
        while (cont >= 0 && TitusNotificaciones.ContarErrores() && !encontrado) {
            if (Tabla.get(cont).Nombre.equals(nombre) && Tabla.get(cont).Rol.equals(Constante.TMetodo)) {
                FMetodo funcion = (FMetodo) Tabla.get(cont).Valor;

                if (funcion.Parametros.size() == lista.Parametros.size()) {
                    int i = 0;
                    boolean estado = true;
                    while (i < funcion.Parametros.size() && estado) {
                        FNodoExpresion exp = (FNodoExpresion) lista.Parametros.get(i);//.resolver expresion
                        if (!(exp.Tipo.equals(funcion.Parametros.get(i).Tipo))) {
                            estado = false;
                        }
                        i++;
                    }
                    if (estado == true) {
                        encontrado = !encontrado;
                        variable = Tabla.get(cont);
                    }
                }
            }
            cont--;
        }
        return variable;
    }

    public boolean BuscarMetodo(String nombre, FMetodo lista) {
        int cont = Tabla.size() - 1;
        boolean encontrado = false;

        while (cont >= 0 && TitusNotificaciones.ContarErrores() && !encontrado) {
            if (Tabla.get(cont).Nombre.equals(nombre) && Tabla.get(cont).Rol.equals(Constante.TMetodo)) {
                FMetodo metodo = (FMetodo) Tabla.get(cont).Valor;

                if (metodo.Parametros.size() == lista.Parametros.size()) {
                    int i = 0;
                    boolean estado = true;
                    while (i < metodo.Parametros.size() && estado) {
                        if (!(lista.Parametros.get(i).Tipo == metodo.Parametros.get(i).Tipo)) {
                            estado = false;
                        }
                        i++;
                    }
                    if (estado) {
                        encontrado = !encontrado;
                    }
                }
            }
            cont--;
        }
        return encontrado;
    }

    public void SacarVariable(String nombre) {
        int cont = Tabla.size() - 1;
        boolean encontrado = false;
        while (cont >= 0 && TitusNotificaciones.ContarErrores() && !encontrado) {
            if (Tabla.get(cont).Nombre.equals(nombre) && (Tabla.get(cont).Rol.equals(Constante.TVariable) || Tabla.get(cont).Rol.equals(Constante.TVariableArreglo))) {
                encontrado = !encontrado;
                Tabla.remove(cont);
            }
            cont--;
        }
    }

    public boolean IsTerminar() {
        return Tabla.get(Tabla.size() - 1).Rol.equals(Constante.TTerminar);
    }

    public boolean IsContinuar() {
        return Tabla.get(Tabla.size() - 1).Rol.equals(Constante.TContinuar);
    }

    public boolean ExisteVariableTope(String nombre) {
        return Tabla.get(Tabla.size() - 1).Nombre.toLowerCase().equals(nombre.toLowerCase());
    }

    public Variable ObtenerTope() {
        return Tabla.get(Tabla.size() - 1);
    }

    public boolean IsRertorno() {
        return Tabla.get(Tabla.size() - 1).Rol.equals(Constante.TRetornar);
    }
}
