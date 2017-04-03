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

    public void ApartarInicio() {
        Tabla.add(null);
    }

    public void InsertarVariable(Variable var) {
        var.Nombre = var.Nombre.toLowerCase();
        Tabla.add(var);
    }

    public void SacarVariable() {
        Tabla.remove(Tabla.size() - 1);
    }

    public Variable BuscarFuncion(FLlamadaMetodo metodo, ArrayList<FNodoExpresion> parametros) {
        int i = 0;
        boolean encontrado = false;
        Variable aux = null;
        while (i < Tabla.size() && !encontrado) {
            Variable funcion = Tabla.get(i);
            if (funcion.Nombre.equals(metodo.Nombre) && funcion.Rol.equals(Constante.TMetodo)) {
                FMetodo m =(FMetodo)funcion.Valor;
                
                if(m.Parametros.size() == parametros.size()){
                    int j=0;
                    boolean estado = true;
                    while(j < m.Parametros.size() && estado){
                        FNodoExpresion exp = parametros.get(j);
                        if(!(exp.Tipo.equals(m.Parametros.get(j).Tipo) || (exp.Tipo.equals(Constante.TObjeto) && exp.Nombre.equals(m.Parametros.get(j).Tipo)))){
                            estado = false;
                        }
                        j++;
                    }
                    if(estado){
                        encontrado = !encontrado;
                        return funcion;
                    }
                }
            }
            i++;
        }

        //si no existe en la tabla actual buscamos en los heredas
        if (!encontrado) {
            Objeto herencia = BuscarHerencia();
            if(herencia != null){
                aux = BuscarFuncionHerencia(metodo, herencia, parametros);
            }
        }

        return aux;
    }
    
    public Variable BuscarFuncionHerencia(FLlamadaMetodo metodo, Objeto herencia, ArrayList<FNodoExpresion> parametros) {
        int i = 0;
        boolean encontrado = false;
        Variable aux = null;
        while (i < herencia.TablaVariables.Tabla.size() && !encontrado) {
            Variable funcion = herencia.TablaVariables.Tabla.get(i);
            if (funcion.Nombre.equals(metodo.Nombre) && funcion.Rol.equals(Constante.TMetodo) && (funcion.Visibilidad.equals(Constante.TPublico) || funcion.Visibilidad.equals(Constante.TProtegido))) {
                FMetodo m =(FMetodo)funcion.Valor;
                
                if(m.Parametros.size() == parametros.size()){
                    int j=0;
                    boolean estado = true;
                    while(j < m.Parametros.size() && estado){
                        FNodoExpresion exp = parametros.get(j);
                        if(!(exp.Tipo.equals(m.Parametros.get(j).Tipo) || (exp.Tipo.equals(Constante.TObjeto) && exp.Nombre.equals(m.Parametros.get(j).Tipo)))){
                            estado = false;
                        }
                        j++;
                    }
                    if(estado){
                        encontrado = !encontrado;
                        return funcion;
                    }
                }
            }
            i++;
        }

        //si no existe en la tabla actual buscamos en los heredas
        if (!encontrado) {
            Objeto h = herencia.TablaVariables.BuscarHerencia();
            if(h != null){
                aux = h.TablaVariables.BuscarFuncionHerencia(metodo, h, parametros);
            }
        }

        return aux;
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
        if (!encontrado) {
            Objeto herencia = BuscarHerencia();
            if(herencia != null){
                variable = BuscarVariableHerencia(herencia, nombre);
            }
        }
        return variable;
    }

    public Variable BuscarVariableHerencia(Objeto herencia, String nombre) {
        int cont = herencia.TablaVariables.Tabla.size() - 1;
        boolean encontrado = false;

        Variable variable = null;
        while (cont >= 0 && TitusNotificaciones.ContarErrores() && !encontrado) {
            Variable h = herencia.TablaVariables.Tabla.get(cont);
            if (h.Nombre.equals(nombre) && (h.Rol.equals(Constante.TVariable) || h.Rol.equals(Constante.TVariableArreglo)) && (h.Visibilidad.equals(Constante.TPublico) || h.Visibilidad.equals(Constante.TProtegido))) {
                variable = h;
                encontrado = !encontrado;
                break;
            }
            cont--;
        }
        
        if (!encontrado) {
            Objeto her = herencia.TablaVariables.BuscarHerencia();
            if(her != null){
                variable = BuscarVariableHerencia(her, nombre);
            }
        }

        return variable;
    }

    public Objeto BuscarHerencia() {
        int i = 0;
        Objeto aux = null;
        while (i < Tabla.size()) {
            Variable funcion = Tabla.get(i);
            if (funcion.Nombre.equals(Constante.THereda) && funcion.Rol.equals(Constante.THereda)) {
                aux = (Objeto) Tabla.get(i).Valor;
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
