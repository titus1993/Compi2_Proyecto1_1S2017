/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Titus
 */
public class Ventana extends JFrame {

    JPanel panel;

    public Ventana() {
        setTitle("Como Hacer Graficos con Java");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        init();
    }

    private void init() {
        panel = new JPanel();
        getContentPane().add(panel);
        // Fuente de Datos
        final XYSeries firefox = new XYSeries( "Firefox" );
      firefox.add(1, 1);
      firefox.add( 2.0 , 2 );
      firefox.add( 3.0 , 3.0 );
      final XYSeries chrome = new XYSeries( "Chrome" );
      chrome.add( -1 , 1 );
      chrome.add( -2.0 , 2 );
      chrome.add( -3.0 , 3 );
      final XYSeries iexplorer = new XYSeries( "InternetExplorer" );
      iexplorer.add( 1,-1 );
      iexplorer.add( 2,-2 );
      iexplorer.add( 3,-3 );
      final XYSeriesCollection dataset = new XYSeriesCollection( );
      dataset.addSeries( firefox );
      dataset.addSeries( chrome );
      dataset.addSeries( iexplorer );

      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         "Browser usage statastics", 
         "Category",
         "Score", 
         dataset,
         PlotOrientation.VERTICAL, 
         true, true, false);

        // Mostrar Grafico
        ChartPanel chartPanel = new ChartPanel(xylineChart);
        panel.add(chartPanel);
        TitusNotificaciones.IniciarGrafica();
    }

    public static void main(String args[]) {
        new Ventana().setVisible(true);
    }
}
