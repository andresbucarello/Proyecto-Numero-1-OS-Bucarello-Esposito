/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaces;

import Main.App;
import Main.Helpers;
import java.awt.Point;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import org.jfree.chart.plot.PlotOrientation;

/**
 *
 * @author Erika Hernández
 */
public class Dashboard extends javax.swing.JFrame {

    private Point initialClick;
    private final App app = App.getInstance();
    private XYSeries computadorasMSI;
    private XYSeries computadorasHP;
    private Timer updateTimer;
    private static Dashboard instance;
    private Helpers helpers = new Helpers();

    /**
     * Obtiene la instancia única de Dashboard. Si no existe, la crea. Si
     * existe, retorna la existente.
     *
     * @return la instancia única de Dashboard.
     */
    public static synchronized Dashboard getInstance() {
        if (instance == null) {
            instance = new Dashboard();
        }
        return instance;
    }

    /**
     * Constructor privado para prevenir la instanciación.
     */
    public Dashboard() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        //JPanelJChart.setLayout(new java.awt.BorderLayout());
        //JPanelJChart.add(app.getChartManager().getChartPanel(), java.awt.BorderLayout.CENTER);
        //JPanelJChart.validate();
        this.start();
    }

    private void start() {
        // Crear un nuevo hilo para el bucle infinito
        Thread updateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        // Ejecutar las actualizaciones de la UI en el EDT
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {

                                profit1.setText(formatNumberAsK((int) app.getEmpresaHP().getIngresos() - (int) app.getEmpresaHP().getCostos()));
                                cost1.setText(formatNumberAsK((int) app.getEmpresaHP().getCostos()));
                                earning1.setText(formatNumberAsK((int) app.getEmpresaHP().getIngresos()));

                                profit.setText(formatNumberAsK((int) app.getEmpresaMSI().getIngresos() - (int) app.getEmpresaMSI().getCostos()));
                                cost2.setText(formatNumberAsK((int) app.getEmpresaMSI().getCostos()));
                                earning.setText(formatNumberAsK((int) app.getEmpresaMSI().getIngresos()));

                                totalDays.setText(String.valueOf(app.getEmpresaHP().getDias()));
                                currentDeadline.setText(String.valueOf(app.getEmpresaHP().getDiasParaEntrega()));

                            }
                        });

                        // Pausar el hilo separado, no el EDT
                        Thread.sleep(app.getDuracionDelDia() / 48);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        });

        // Iniciar el hilo
        updateThread.start();
    }

    public String formatNumberAsK(int number) {
        // Se onverte el número a miles
        double thousands = number / 1000.0;

        // Se redondea a dos dígitos significativos
        double rounded = Math.round(thousands * 100.0) / 100.0;

        // Se convierte a cadena y se añade 'K'
        return rounded + "K";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        JPanelJChart = new javax.swing.JPanel();
        driveTitle9 = new javax.swing.JLabel();
        driveTitle10 = new javax.swing.JLabel();
        currentDeadline = new javax.swing.JTextField();
        earning = new javax.swing.JTextField();
        profit = new javax.swing.JTextField();
        driveTitle17 = new javax.swing.JLabel();
        driveTitle11 = new javax.swing.JLabel();
        driveTitle12 = new javax.swing.JLabel();
        driveTitle13 = new javax.swing.JLabel();
        cost1 = new javax.swing.JTextField();
        driveTitle14 = new javax.swing.JLabel();
        earning1 = new javax.swing.JTextField();
        driveTitle18 = new javax.swing.JLabel();
        profit1 = new javax.swing.JTextField();
        driveTitle15 = new javax.swing.JLabel();
        driveTitle16 = new javax.swing.JLabel();
        cost2 = new javax.swing.JTextField();
        totalDays = new javax.swing.JTextField();
        driveTitle = new javax.swing.JLabel();
        btn_nuevo_pedido = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btn_cargar_guardar = new javax.swing.JPanel();
        icono7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btn_nueva_ruta = new javax.swing.JPanel();
        icono3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btn_nuevo_almacen = new javax.swing.JPanel();
        icono4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btn_reporte = new javax.swing.JPanel();
        icono5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setMaximizedBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setMinimumSize(new java.awt.Dimension(1130, 720));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, -1, -1));

        javax.swing.GroupLayout JPanelJChartLayout = new javax.swing.GroupLayout(JPanelJChart);
        JPanelJChart.setLayout(JPanelJChartLayout);
        JPanelJChartLayout.setHorizontalGroup(
            JPanelJChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        JPanelJChartLayout.setVerticalGroup(
            JPanelJChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );

        jPanel1.add(JPanelJChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 750, 320));

        driveTitle9.setFont(new java.awt.Font("Noteworthy", 1, 18)); // NOI18N
        driveTitle9.setForeground(new java.awt.Color(255, 255, 255));
        driveTitle9.setText("HP");
        driveTitle9.setFocusable(false);
        jPanel1.add(driveTitle9, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 470, -1, -1));

        driveTitle10.setFont(new java.awt.Font("Noteworthy", 1, 14)); // NOI18N
        driveTitle10.setForeground(new java.awt.Color(255, 255, 255));
        driveTitle10.setText("Costos:");
        driveTitle10.setFocusable(false);
        jPanel1.add(driveTitle10, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 510, -1, -1));

        currentDeadline.setBackground(new java.awt.Color(0, 0, 0));
        currentDeadline.setFont(new java.awt.Font("Noteworthy", 1, 18)); // NOI18N
        currentDeadline.setForeground(new java.awt.Color(255, 255, 255));
        currentDeadline.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        currentDeadline.setText("0");
        currentDeadline.setBorder(null);
        currentDeadline.setFocusable(false);
        currentDeadline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currentDeadlineActionPerformed(evt);
            }
        });
        jPanel1.add(currentDeadline, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 90, 100, -1));

        earning.setBackground(new java.awt.Color(0, 0, 0));
        earning.setFont(new java.awt.Font("Noteworthy", 1, 14)); // NOI18N
        earning.setForeground(new java.awt.Color(255, 255, 255));
        earning.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        earning.setText("0");
        earning.setBorder(null);
        earning.setFocusable(false);
        earning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                earningActionPerformed(evt);
            }
        });
        jPanel1.add(earning, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 540, 110, -1));

        profit.setBackground(new java.awt.Color(0, 0, 0));
        profit.setFont(new java.awt.Font("Noteworthy", 1, 14)); // NOI18N
        profit.setForeground(new java.awt.Color(255, 255, 255));
        profit.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        profit.setText("0");
        profit.setBorder(null);
        profit.setFocusable(false);
        profit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profitMouseClicked(evt);
            }
        });
        profit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profitActionPerformed(evt);
            }
        });
        jPanel1.add(profit, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 570, 110, -1));

        driveTitle17.setFont(new java.awt.Font("Noteworthy", 1, 14)); // NOI18N
        driveTitle17.setForeground(new java.awt.Color(255, 255, 255));
        driveTitle17.setText("Ganancias:");
        driveTitle17.setFocusable(false);
        jPanel1.add(driveTitle17, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 570, -1, -1));

        driveTitle11.setFont(new java.awt.Font("Noteworthy", 1, 14)); // NOI18N
        driveTitle11.setForeground(new java.awt.Color(255, 255, 255));
        driveTitle11.setText("Ingresos:");
        driveTitle11.setFocusable(false);
        jPanel1.add(driveTitle11, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 540, -1, -1));

        driveTitle12.setFont(new java.awt.Font("Noteworthy", 1, 18)); // NOI18N
        driveTitle12.setForeground(new java.awt.Color(255, 255, 255));
        driveTitle12.setText("DÍAS PARA LA ENTREGA: ");
        driveTitle12.setFocusable(false);
        jPanel1.add(driveTitle12, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, -1, -1));

        driveTitle13.setFont(new java.awt.Font("Noteworthy", 1, 14)); // NOI18N
        driveTitle13.setForeground(new java.awt.Color(255, 255, 255));
        driveTitle13.setText("Ingresos:");
        driveTitle13.setFocusable(false);
        jPanel1.add(driveTitle13, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 540, -1, -1));

        cost1.setBackground(new java.awt.Color(0, 0, 0));
        cost1.setFont(new java.awt.Font("Noteworthy", 1, 14)); // NOI18N
        cost1.setForeground(new java.awt.Color(255, 255, 255));
        cost1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cost1.setText("0");
        cost1.setBorder(null);
        cost1.setFocusable(false);
        cost1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cost1ActionPerformed(evt);
            }
        });
        jPanel1.add(cost1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 510, 100, -1));

        driveTitle14.setFont(new java.awt.Font("Noteworthy", 1, 14)); // NOI18N
        driveTitle14.setForeground(new java.awt.Color(255, 255, 255));
        driveTitle14.setText("Costos:");
        driveTitle14.setFocusable(false);
        jPanel1.add(driveTitle14, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 510, -1, -1));

        earning1.setBackground(new java.awt.Color(0, 0, 0));
        earning1.setFont(new java.awt.Font("Noteworthy", 1, 14)); // NOI18N
        earning1.setForeground(new java.awt.Color(255, 255, 255));
        earning1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        earning1.setText("0");
        earning1.setBorder(null);
        earning1.setFocusable(false);
        earning1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                earning1ActionPerformed(evt);
            }
        });
        jPanel1.add(earning1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 540, 100, -1));

        driveTitle18.setFont(new java.awt.Font("Noteworthy", 1, 14)); // NOI18N
        driveTitle18.setForeground(new java.awt.Color(255, 255, 255));
        driveTitle18.setText("Ganancias:");
        driveTitle18.setFocusable(false);
        jPanel1.add(driveTitle18, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 570, -1, -1));

        profit1.setBackground(new java.awt.Color(0, 0, 0));
        profit1.setFont(new java.awt.Font("Noteworthy", 1, 14)); // NOI18N
        profit1.setForeground(new java.awt.Color(255, 255, 255));
        profit1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        profit1.setText("0");
        profit1.setBorder(null);
        profit1.setFocusable(false);
        profit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profit1ActionPerformed(evt);
            }
        });
        jPanel1.add(profit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 570, 100, -1));

        driveTitle15.setFont(new java.awt.Font("Noteworthy", 1, 18)); // NOI18N
        driveTitle15.setForeground(new java.awt.Color(255, 255, 255));
        driveTitle15.setText("MSI");
        driveTitle15.setFocusable(false);
        jPanel1.add(driveTitle15, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 470, -1, -1));

        driveTitle16.setFont(new java.awt.Font("Noteworthy", 1, 18)); // NOI18N
        driveTitle16.setForeground(new java.awt.Color(255, 255, 255));
        driveTitle16.setText("DÍAS: ");
        driveTitle16.setFocusable(false);
        jPanel1.add(driveTitle16, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, -1, -1));

        cost2.setBackground(new java.awt.Color(0, 0, 0));
        cost2.setFont(new java.awt.Font("Noteworthy", 1, 14)); // NOI18N
        cost2.setForeground(new java.awt.Color(255, 255, 255));
        cost2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cost2.setText("0");
        cost2.setBorder(null);
        cost2.setFocusable(false);
        cost2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cost2ActionPerformed(evt);
            }
        });
        jPanel1.add(cost2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 510, 110, -1));

        totalDays.setBackground(new java.awt.Color(0, 0, 0));
        totalDays.setFont(new java.awt.Font("Noteworthy", 1, 18)); // NOI18N
        totalDays.setForeground(new java.awt.Color(255, 255, 255));
        totalDays.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalDays.setText("0");
        totalDays.setBorder(null);
        totalDays.setFocusable(false);
        totalDays.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalDaysActionPerformed(evt);
            }
        });
        jPanel1.add(totalDays, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 100, -1));

        driveTitle.setFont(new java.awt.Font("Noteworthy", 1, 18)); // NOI18N
        driveTitle.setForeground(new java.awt.Color(255, 255, 255));
        driveTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        driveTitle.setText("Visualización de estadísticas ");
        jPanel1.add(driveTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, -1, -1));

        btn_nuevo_pedido.setBackground(new java.awt.Color(102, 102, 102));
        btn_nuevo_pedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_nuevo_pedidoMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Noteworthy", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Dashboard");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_nuevo_pedidoLayout = new javax.swing.GroupLayout(btn_nuevo_pedido);
        btn_nuevo_pedido.setLayout(btn_nuevo_pedidoLayout);
        btn_nuevo_pedidoLayout.setHorizontalGroup(
            btn_nuevo_pedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_nuevo_pedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(51, 51, 51)
                .addComponent(jLabel5)
                .addContainerGap(59, Short.MAX_VALUE))
        );
        btn_nuevo_pedidoLayout.setVerticalGroup(
            btn_nuevo_pedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_nuevo_pedidoLayout.createSequentialGroup()
                .addGroup(btn_nuevo_pedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btn_nuevo_pedidoLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(btn_nuevo_pedidoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(btn_nuevo_pedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 40));

        btn_cargar_guardar.setBackground(new java.awt.Color(51, 51, 51));
        btn_cargar_guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_cargar_guardarMouseClicked(evt);
            }
        });

        icono7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        icono7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icono7MouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Noteworthy", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("MSI");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_cargar_guardarLayout = new javax.swing.GroupLayout(btn_cargar_guardar);
        btn_cargar_guardar.setLayout(btn_cargar_guardarLayout);
        btn_cargar_guardarLayout.setHorizontalGroup(
            btn_cargar_guardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_cargar_guardarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icono7)
                .addGap(74, 74, 74)
                .addComponent(jLabel10)
                .addContainerGap(88, Short.MAX_VALUE))
        );
        btn_cargar_guardarLayout.setVerticalGroup(
            btn_cargar_guardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_cargar_guardarLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(icono7, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
            .addGroup(btn_cargar_guardarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(btn_cargar_guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 200, 40));

        btn_nueva_ruta.setBackground(new java.awt.Color(51, 51, 51));
        btn_nueva_ruta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_nueva_rutaMouseClicked(evt);
            }
        });

        icono3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        icono3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icono3MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Noteworthy", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("HP");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_nueva_rutaLayout = new javax.swing.GroupLayout(btn_nueva_ruta);
        btn_nueva_ruta.setLayout(btn_nueva_rutaLayout);
        btn_nueva_rutaLayout.setHorizontalGroup(
            btn_nueva_rutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_nueva_rutaLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(icono3)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addContainerGap(96, Short.MAX_VALUE))
        );
        btn_nueva_rutaLayout.setVerticalGroup(
            btn_nueva_rutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_nueva_rutaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(icono3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_nueva_rutaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        jPanel1.add(btn_nueva_ruta, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 200, 40));

        btn_nuevo_almacen.setBackground(new java.awt.Color(51, 51, 51));
        btn_nuevo_almacen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_nuevo_almacenMouseClicked(evt);
            }
        });

        icono4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        icono4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icono4MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Noteworthy", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Configuracion");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_nuevo_almacenLayout = new javax.swing.GroupLayout(btn_nuevo_almacen);
        btn_nuevo_almacen.setLayout(btn_nuevo_almacenLayout);
        btn_nuevo_almacenLayout.setHorizontalGroup(
            btn_nuevo_almacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_nuevo_almacenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icono4)
                .addGap(32, 32, 32)
                .addComponent(jLabel7)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        btn_nuevo_almacenLayout.setVerticalGroup(
            btn_nuevo_almacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_nuevo_almacenLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(icono4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_nuevo_almacenLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        jPanel1.add(btn_nuevo_almacen, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 0, 200, 40));

        btn_reporte.setBackground(new java.awt.Color(51, 51, 51));
        btn_reporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_reporteMouseClicked(evt);
            }
        });

        icono5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        icono5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icono5MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Noteworthy", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Guardar");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_reporteLayout = new javax.swing.GroupLayout(btn_reporte);
        btn_reporte.setLayout(btn_reporteLayout);
        btn_reporteLayout.setHorizontalGroup(
            btn_reporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_reporteLayout.createSequentialGroup()
                .addGroup(btn_reporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btn_reporteLayout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(icono5))
                    .addGroup(btn_reporteLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel8)))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        btn_reporteLayout.setVerticalGroup(
            btn_reporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_reporteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(icono5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(btn_reporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 0, 200, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
//        Dashboard v4 = new Dashboard();
//        v4.setVisible(true);
//        this.dispose();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void btn_nuevo_pedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_nuevo_pedidoMouseClicked
        // TODO add your handling code here:
//        Dashboard v4 = new Dashboard();
//        v4.setVisible(true);
//        this.dispose();
    }//GEN-LAST:event_btn_nuevo_pedidoMouseClicked

    private void icono3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono3MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_icono3MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        HP v2 = new HP();
        v2.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void cartoonPlayMusic(String path) {
        try {
            URL url = this.getClass().getResource(path);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    private void btn_nueva_rutaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_nueva_rutaMouseClicked
        // TODO add your handling code here:
        HP v2 = new HP();
        v2.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_nueva_rutaMouseClicked

    private void icono4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono4MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_icono4MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        ConfiguracionParametros v3 = new ConfiguracionParametros();
        v3.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void btn_nuevo_almacenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_nuevo_almacenMouseClicked
        // TODO add your handling code here:
        ConfiguracionParametros v3 = new ConfiguracionParametros();
        v3.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_nuevo_almacenMouseClicked

    private void icono7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono7MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_icono7MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
        MSI v1 = new MSI();
        v1.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void btn_cargar_guardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cargar_guardarMouseClicked
        // TODO add your handling code here:
        MSI v1 = new MSI();
        v1.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_cargar_guardarMouseClicked

    private void btn_reporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_reporteMouseClicked
        // TODO add your handling code here:
        try {
            this.helpers.write();
            JOptionPane.showMessageDialog(this, "El archivo ha sido guardado exitosamente!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al escribir el archivo");
        }
    }//GEN-LAST:event_btn_reporteMouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jLabel8MouseClicked

    private void icono5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono5MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_icono5MouseClicked

    private void currentDeadlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_currentDeadlineActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_currentDeadlineActionPerformed

    private void earningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_earningActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_earningActionPerformed

    private void profitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_profitActionPerformed

    private void cost1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cost1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cost1ActionPerformed

    private void earning1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_earning1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_earning1ActionPerformed

    private void profit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profit1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_profit1ActionPerformed

    private void cost2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cost2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cost2ActionPerformed

    private void totalDaysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalDaysActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalDaysActionPerformed

    private void profitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profitMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_profitMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);

//                              
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanelJChart;
    private javax.swing.JPanel btn_cargar_guardar;
    private javax.swing.JPanel btn_nueva_ruta;
    private javax.swing.JPanel btn_nuevo_almacen;
    private javax.swing.JPanel btn_nuevo_pedido;
    private javax.swing.JPanel btn_reporte;
    private javax.swing.JTextField cost1;
    private javax.swing.JTextField cost2;
    private javax.swing.JTextField currentDeadline;
    private javax.swing.JLabel driveTitle;
    private javax.swing.JLabel driveTitle10;
    private javax.swing.JLabel driveTitle11;
    private javax.swing.JLabel driveTitle12;
    private javax.swing.JLabel driveTitle13;
    private javax.swing.JLabel driveTitle14;
    private javax.swing.JLabel driveTitle15;
    private javax.swing.JLabel driveTitle16;
    private javax.swing.JLabel driveTitle17;
    private javax.swing.JLabel driveTitle18;
    private javax.swing.JLabel driveTitle9;
    private javax.swing.JTextField earning;
    private javax.swing.JTextField earning1;
    private javax.swing.JLabel icono3;
    private javax.swing.JLabel icono4;
    private javax.swing.JLabel icono5;
    private javax.swing.JLabel icono7;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField profit;
    private javax.swing.JTextField profit1;
    private javax.swing.JTextField totalDays;
    // End of variables declaration//GEN-END:variables
}
