/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import Main.App;
import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author andresbucarello
 */
/**
 * La clase ManejadorGraficos maneja la creación y actualización de gráficos
 * en tiempo real para visualizar las ganancias de MSI y HP.
 * Utiliza la biblioteca JFreeChart para crear un gráfico de líneas XY que se actualiza
 * periódicamente en función de la duración del día establecida en la aplicación.
 */

public class ManejadorGraficos {
    private final App app = App.getInstance();
    private XYSeries computadorasMSI;
    private XYSeries computadorasHP;
    private XYSeriesCollection dataset;
    private JFreeChart xyLineChart;
    private Timer updateTimer;

    /**
     * Constructor de ManejadorGraficos que inicializa las series de datos,
     * crea el gráfico y comienza el temporizador de actualización.
     */
    
    public ManejadorGraficos() {
        initializeSeries();
        initializeChart();
        startDataUpdateTimer();
    }
    
      /**
     * Inicializa las series de datos para MSI y HP
     * y las agrega al conjunto de datos para el gráfico.
     */

    private void initializeSeries() {
        computadorasMSI = new XYSeries("MSI");
        computadorasHP = new XYSeries("HP");
        dataset = new XYSeriesCollection();
        dataset.addSeries(computadorasMSI);
        dataset.addSeries(computadorasHP);
    }

    /**
     * Crea el gráfico XY Line usando JFreeChart y configura la apariencia.
     */
    
    private void initializeChart() {
        xyLineChart = ChartFactory.createXYLineChart(
                "Tiempo vs Ganancia", // Título del gráfico
                "Tiempo",             // Etiqueta eje X
                "Ganancia",           // Etiqueta eje Y
                dataset,              // Dataset
                PlotOrientation.VERTICAL,
                true,                 // Mostrar leyenda
                true,                 // Generar tooltips
                false                 // URLs
        );

        customizeChart();
    }

     /**
     * Personaliza la apariencia del gráfico XY Line.
     */
    
    private void customizeChart() {
        XYPlot plot = xyLineChart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        plot.setRenderer(renderer);
    }

     /**
     * Inicia un temporizador que actualiza las series de datos con un intervalo
     * que se obtiene de la duración del día en la aplicación.
     */
    
    private void startDataUpdateTimer() {
        int delay = app.getDuracionDelDia(); 
        updateTimer = new Timer(delay, e -> updateChartData());
        updateTimer.start();
    }

     /**
     * Actualiza las series de datos con las ganancias más recientes de MSI
     * y HP y las agrega al gráfico.
     */
    
    public void updateChartData() {
        // Se obtienen las nuevas ganancias
        double gananciasMSI = app.getEmpresaMSI().getGanancias(); 
        double gananciasHP = app.getEmpresaMSI().getGanancias();
        int newTimePoint = computadorasMSI.getItemCount() + 1;

        computadorasMSI.addOrUpdate(newTimePoint, gananciasMSI);
        computadorasHP.addOrUpdate(newTimePoint, gananciasHP);
    }

    public ChartPanel getChartPanel() {
        return new ChartPanel(xyLineChart);
    }

    public void stopUpdateTimer() {
        if (updateTimer != null) {
            updateTimer.stop();
        }
    }
}

