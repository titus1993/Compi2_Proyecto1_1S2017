/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones_GraphiK;

import Constante.Constante;
import Ejecucion_GraphiK.Objeto;
import Ejecucion_GraphiK.Simbolo;
import Ejecucion_GraphiK.Variable;
import Interface.TitusNotificaciones;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Titus
 */
public class FDatos {

    public FDatos() {

    }

    public void EjecutarDatos(Simbolo sim, Objeto Tabla, Objeto Padre) {
        Variable metododatos = Padre.TablaVariables.BuscarDatos();
        if (metododatos != null) {
            if (TitusNotificaciones.ModeloDatos != null) {
                FMetodo mdatos = (FMetodo) metododatos.Valor;
                FProcesar procesar = (FProcesar) mdatos.Ambito.TablaSimbolo.get(0).Valor;
                FDonde donde = (FDonde) mdatos.Ambito.TablaSimbolo.get(1).Valor;

                if (donde.Nombre.equals(Constante.TDonde)) {
                    EjecutarDonde(procesar.Valor, donde, Padre);
                } else if (donde.Nombre.equals(Constante.TDondeCada)) {
                    EjecutarDondeCada(procesar.Valor, donde, Tabla);
                }else{
                    EjecutarDondeTodo(procesar.Valor, donde, Tabla);
                }
            } else {
                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No existen datos para analizar", sim.Fila, sim.Columna);
            }

        } else {
            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No existe el metodo Datos", sim.Fila, sim.Columna);
        }
    }

    public void EjecutarDonde(FNodoExpresion procesar, FDonde donde, Objeto Tabla) {
        FNodoExpresion posColumna = donde.Donde.ResolverExpresion(Tabla, 1);

        if (posColumna.Tipo.equals(Constante.TEntero)) {
            if (posColumna.Entero > 0 && posColumna.Entero <= TitusNotificaciones.ModeloDatos.getColumnCount()) {
                String[] titulo = {TitusNotificaciones.ModeloDatos.getColumnName(posColumna.Entero - 1), "Resultado"};
                ArrayList<String[]> contenido = new ArrayList<>();

                int i = 0;
                while (i < TitusNotificaciones.ModeloDatos.getRowCount()) {
                    FNodoExpresion condicion = donde.Valor.ResolverExpresion(Tabla, i);
                    FNodoExpresion resultado = procesar.ResolverExpresion(Tabla, i);
                    String dato = TitusNotificaciones.ModeloDatos.getValueAt(i, posColumna.Entero - 1).toString();

                    String result = null;
                    if (resultado.Tipo.equals(Constante.TEntero)) {
                        result = String.valueOf(resultado.Entero);
                    } else if (resultado.Tipo.equals(Constante.TDecimal)) {
                        result = String.valueOf(resultado.Decimal);
                    } else if (resultado.Tipo.equals(Constante.TCadena)) {
                        result = String.valueOf(resultado.Cadena);
                    } else if (resultado.Tipo.equals(Constante.TCaracter)) {
                        result = String.valueOf(resultado.Caracter);
                    } else if (resultado.Tipo.equals(Constante.TBool)) {
                        result = String.valueOf(resultado.Bool);
                    } else {
                        TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se obtuvo un valor valido", donde.Fila, donde.Columna);
                    }
                    if (condicion.Tipo.equals(Constante.TEntero) && TitusNotificaciones.ContarErrores()) {
                        try {
                            int val = Integer.parseInt(dato);
                            if (val == condicion.Entero) {
                                String[] cont = {dato, result};
                                contenido.add(cont);
                            }
                        } catch (Exception e) {
                            try {
                                double val = Double.parseDouble(dato);
                                if (val == condicion.Decimal) {
                                    String[] cont = {dato, result};
                                    contenido.add(cont);
                                }
                            } catch (Exception ex) {
                                if (dato.toLowerCase().equals(condicion.Cadena)) {
                                    String[] cont = {dato, result};
                                    contenido.add(cont);
                                }
                            }
                        }
                    } else if (condicion.Tipo.equals(Constante.TDecimal)) {
                        try {
                            int val = Integer.parseInt(dato);
                            if (val == condicion.Entero) {
                                String[] cont = {dato, result};
                                contenido.add(cont);
                            }
                        } catch (Exception e) {
                            try {
                                double val = Double.parseDouble(dato);
                                if (val == condicion.Decimal) {
                                    String[] cont = {dato, result};
                                    contenido.add(cont);
                                }
                            } catch (Exception ex) {
                                if (dato.toLowerCase().equals(condicion.Cadena)) {
                                    String[] cont = {dato, result};
                                    contenido.add(cont);
                                }
                            }
                        }
                    } else if (condicion.Tipo.equals(Constante.TCadena)) {
                        try {
                            int val = Integer.parseInt(dato);
                            if (val == condicion.Entero) {
                                String[] cont = {dato, result};
                                contenido.add(cont);
                            }
                        } catch (Exception e) {
                            try {
                                double val = Double.parseDouble(dato);
                                if (val == condicion.Decimal) {
                                    String[] cont = {dato, result};
                                    contenido.add(cont);
                                }
                            } catch (Exception ex) {
                                if (dato.toLowerCase().equals(condicion.Cadena)) {
                                    String[] cont = {dato, result};
                                    contenido.add(cont);
                                }
                            }
                        }
                    } else {

                    }
                    i++;
                }

                /* String[] columnas = {"Tipo de error", "Descripcion", "Linea", "Columna"};*/
                DefaultTableModel ModeloDatos = new DefaultTableModel(titulo, 0);
                for (String[] c : contenido) {
                    if (titulo.length == c.length) {
                        ModeloDatos.addRow(c);
                    }
                }
                JTable Datos = new JTable(ModeloDatos);
                JScrollPane DatosPanel = new JScrollPane(Datos);
                DatosPanel.setName("Datos");
                //Interface.notificacion.add(DatosPanel);
                if (TitusNotificaciones.ContarErrores()) {
                    JFrame v = new JFrame();
                    v.setSize(200, 400);
                    v.setVisible(true);
                    v.add(DatosPanel);
                    int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
                    int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
                    v.setBounds((ancho / 2) - (v.getWidth() / 2), (alto / 2) - (v.getHeight() / 2), v.getWidth(), v.getHeight());
                }

            } else {
                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "Posicion de la columna fuera de rango", 0, 0);
            }

        } else {
            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "El valor de donde tiene que ser de tipo entero", 0, 0);
        }

    }

    public void EjecutarDondeCada(FNodoExpresion procesar, FDonde donde, Objeto Tabla) {
        FNodoExpresion posColumna = donde.Donde.ResolverExpresion(Tabla, 1);

        if (posColumna.Tipo.equals(Constante.TEntero)) {
            if (posColumna.Entero > 0 && posColumna.Entero <= TitusNotificaciones.ModeloDatos.getColumnCount()) {
                String[] titulo = {TitusNotificaciones.ModeloDatos.getColumnName(posColumna.Entero - 1), "Resultado"};
                ArrayList<String[]> contenido = new ArrayList<>();

                int i = 0;
                while (i < TitusNotificaciones.ModeloDatos.getRowCount()) {
                    FNodoExpresion resultado = procesar.ResolverExpresion(Tabla, i);
                    String dato = TitusNotificaciones.ModeloDatos.getValueAt(i, posColumna.Entero - 1).toString();

                    String result = null;
                    if (resultado.Tipo.equals(Constante.TEntero)) {
                        result = String.valueOf(resultado.Entero);
                    } else if (resultado.Tipo.equals(Constante.TDecimal)) {
                        result = String.valueOf(resultado.Decimal);
                    } else if (resultado.Tipo.equals(Constante.TCadena)) {
                        result = String.valueOf(resultado.Cadena);
                    } else if (resultado.Tipo.equals(Constante.TCaracter)) {
                        result = String.valueOf(resultado.Caracter);
                    } else if (resultado.Tipo.equals(Constante.TBool)) {
                        result = String.valueOf(resultado.Bool);
                    } else {
                        TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se obtuvo un valor valido", donde.Fila, donde.Columna);
                    }
                    String[] cont = {dato, result};
                    contenido.add(cont);
                    i++;
                }

                /* String[] columnas = {"Tipo de error", "Descripcion", "Linea", "Columna"};*/
                DefaultTableModel ModeloDatos = new DefaultTableModel(titulo, 0);
                for (String[] c : contenido) {
                    if (titulo.length == c.length) {
                        ModeloDatos.addRow(c);
                    }
                }
                JTable Datos = new JTable(ModeloDatos);
                JScrollPane DatosPanel = new JScrollPane(Datos);
                DatosPanel.setName("Datos");
                //Interface.notificacion.add(DatosPanel);
                if (TitusNotificaciones.ContarErrores()) {
                    JFrame v = new JFrame();
                    v.setSize(200, 400);
                    v.setVisible(true);
                    v.add(DatosPanel);
                    int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
                    int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
                    v.setBounds((ancho / 2) - (v.getWidth() / 2), (alto / 2) - (v.getHeight() / 2), v.getWidth(), v.getHeight());
                }

            } else {
                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "Posicion de la columna fuera de rango", 0, 0);
            }

        } else {
            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "El valor de donde tiene que ser de tipo entero", 0, 0);
        }

    }

    public void EjecutarDondeTodo(FNodoExpresion procesar, FDonde donde, Objeto Tabla) {
        FNodoExpresion posColumna = donde.Donde.ResolverExpresion(Tabla, 1);

        if (posColumna.Tipo.equals(Constante.TEntero)) {
            if (posColumna.Entero > 0 && posColumna.Entero <= TitusNotificaciones.ModeloDatos.getColumnCount()) {
                String[] titulo = {TitusNotificaciones.ModeloDatos.getColumnName(posColumna.Entero - 1), "Resultado"};
                ArrayList<String[]> contenido = new ArrayList<>();

                int i = 0;
                double total = 0;
                while (i < TitusNotificaciones.ModeloDatos.getRowCount()) {
                    FNodoExpresion resultado = procesar.ResolverExpresion(Tabla, i);
                    String dato = TitusNotificaciones.ModeloDatos.getValueAt(i, posColumna.Entero - 1).toString();

                    String result = null;
                    if (resultado.Tipo.equals(Constante.TEntero)) {
                        result = String.valueOf(resultado.Entero);
                        total += Double.parseDouble(result);
                    } else if (resultado.Tipo.equals(Constante.TDecimal)) {
                        result = String.valueOf(resultado.Decimal);
                        total += resultado.Decimal;
                    } else {
                        TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se obtuvo un valor valido", donde.Fila, donde.Columna);
                    }
                    String[] cont = {dato, result};
                    contenido.add(cont);
                    i++;
                }

                String[] columnas = {"Todo", String.valueOf(total)};
                DefaultTableModel ModeloDatos = new DefaultTableModel(titulo, 0);

                ModeloDatos.addRow(columnas);

                JTable Datos = new JTable(ModeloDatos);
                JScrollPane DatosPanel = new JScrollPane(Datos);
                DatosPanel.setName("Datos");
                //Interface.notificacion.add(DatosPanel);
                if (TitusNotificaciones.ContarErrores()) {
                    JFrame v = new JFrame();
                    v.setSize(200, 400);
                    v.setVisible(true);
                    v.add(DatosPanel);
                    int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
                    int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
                    v.setBounds((ancho / 2) - (v.getWidth() / 2), (alto / 2) - (v.getHeight() / 2), v.getWidth(), v.getHeight());
                }

            } else {
                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "Posicion de la columna fuera de rango", 0, 0);
            }

        } else {
            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "El valor de donde tiene que ser de tipo entero", 0, 0);
        }

    }
}
