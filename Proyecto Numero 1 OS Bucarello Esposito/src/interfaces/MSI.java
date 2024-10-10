/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaces;


import Main.Helpers;
import Clases.Trabajador;
import Main.App;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Erika A. Hernández Z.
 */
public class MSI extends javax.swing.JFrame {

    private Point initialClick;
    private final App app = App.getInstance();
    private int maxEmployees;
    private int actualEmployees;
    private static MSI msi;
    private Helpers helper = new Helpers();
    private JButton[] decreaseBtn = new JButton[6];
    private JButton[] increaseBtn = new JButton[6];
    private int[] values = {
        countNonNullEmployees(this.app.getEmpresaMSI().getProductoresPB()),
        countNonNullEmployees(this.app.getEmpresaMSI().getProductoresCPU()),
        countNonNullEmployees(this.app.getEmpresaMSI().getProductoresRAM()),
        countNonNullEmployees(this.app.getEmpresaMSI().getProductoresFA()),
        countNonNullEmployees(this.app.getEmpresaMSI().getProductoresTG()),
        countNonNullEmployees(this.app.getEmpresaMSI().getEnsambladores())
    };

    private void updateBtnStatus() {
        updateValues();

        if (this.actualEmployees == this.maxEmployees) {
            for (JButton btn : increaseBtn) {
                btn.setEnabled(false);
                btn.setFocusable(false);
            }
        } else {
            for (JButton btn : increaseBtn) {
                btn.setEnabled(true);
                btn.setFocusable(true);
            }
        }

        for (int i = 0; i < this.values.length; i++) {
            if (this.values[i] == 1) {
                this.decreaseBtn[i].setEnabled(false);
                this.decreaseBtn[i].setFocusable(false);
            } else {
                this.decreaseBtn[i].setEnabled(true);
                this.decreaseBtn[i].setFocusable(true);

            }
        }
    }

    private void updateValues() {
        
        values[0] = countNonNullEmployees(this.app.getEmpresaMSI().getProductoresPB());
        values[1] = countNonNullEmployees(this.app.getEmpresaMSI().getProductoresCPU());
        values[2] = countNonNullEmployees(this.app.getEmpresaMSI().getProductoresRAM());
        values[3] = countNonNullEmployees(this.app.getEmpresaMSI().getProductoresFA());
        values[4] = countNonNullEmployees(this.app.getEmpresaMSI().getProductoresTG());
        values[5] = countNonNullEmployees(this.app.getEmpresaMSI().getEnsambladores());
    }

    public static synchronized MSI getInstance() {
        if (msi == null) {
            msi = new MSI();
        }
        return msi;
    }

    public MSI() {
        try {
            // Código para el Look and Feel
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        initializeValues();


        this.decreaseBtn[0] = decreaseScripts;
        this.decreaseBtn[1] = decreaseScenary;
        this.decreaseBtn[2] = decreaseAnimation;
        this.decreaseBtn[3] = decreaseDubbing;
        this.decreaseBtn[4] = decreacePlotTwist;
        this.decreaseBtn[5] = decreaceAssembler;
        this.increaseBtn[0] = increaseScripts;
        this.increaseBtn[1] = increaseScenary;
        this.increaseBtn[2] = increaseAnimation;
        this.increaseBtn[3] = increaseDubbing;
        this.increaseBtn[4] = increasePlotTwist;
        this.increaseBtn[5] = increaseAssembler;

        updateBtnStatus();
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
                                // Aquí van tus actualizaciones de la UI
                                almacenPB
                                        .setText(String.valueOf(app.getEmpresaMSI().getAlmacen().getComponentes()[0]));
                                almacenCPU
                                        .setText(String.valueOf(app.getEmpresaMSI().getAlmacen().getComponentes()[1]));
                                almacenRAM
                                        .setText(String.valueOf(app.getEmpresaMSI().getAlmacen().getComponentes()[2]));
                                almacenFA
                                        .setText(String.valueOf(app.getEmpresaMSI().getAlmacen().getComponentes()[3]));
                                almacenTG
                                        .setText(String.valueOf(app.getEmpresaMSI().getAlmacen().getComponentes()[4]));
                                almacenEnsamblador
                                        .setText(String.valueOf(app.getEmpresaMSI().getAlmacen().getComponentes()[5]));

                                projectManagerStatus
                                        .setText(app.getEmpresaMSI().getProjectManager().getEstadoActual());

                                diasRestantes.setText(
                                        String.valueOf(app.getEmpresaMSI().getDiasParaEntrega()));

                                contadorDiasDeLaSimulacion.setText(String.valueOf(app.getEmpresaMSI().getDias()));

                                strikeCounter.setText(String
                                        .valueOf(app.getEmpresaMSI().getProjectManager().getStrikes()));
                                cashPenality.setText(String.valueOf(Integer.parseInt(strikeCounter.getText()) * 100));
                                directorStatus.setText(app.getEmpresaMSI().getDirector().getEstadoActual());

                                

                                computadorasNormales.setText(
                                        String.valueOf(app.getEmpresaMSI().getComputadorasNormales())
                                );
                                computadorasTG.setText(
                                        String.valueOf(app.getEmpresaMSI().getComputadorasTG())
                                );

                                

                                ganancia.setText(formatNumberAsK((int) app.getEmpresaMSI().getIngresos() -  (int) app.getEmpresaMSI().getCostos()));
                                costos.setText(formatNumberAsK((int) app.getEmpresaMSI().getCostos()));
                                ingresos.setText(formatNumberAsK((int) app.getEmpresaMSI().getIngresos()));

                            }
                        });

                        // Pausar el hilo separado, no el EDT
                        Thread.sleep(app.getDuracionDelDia() / 48);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // Opcionalmente, podrías salir del bucle si el hilo es interrumpido
                        break;
                    }
                }
            }
        });

        // Iniciar el hilo
        updateThread.start();
    }

    private void initializeValues() {
        if (this.app.getEmpresaMSI() != null) {
            this.maxEmployees = this.app.getEmpresaMSI().getCantidadTrabajadoresMaximos();
            this.actualEmployees = this.app.getEmpresaMSI().getCantidadTrabajadoresActuales();
            this.scriptsValues
                    .setText(String.valueOf(countNonNullEmployees(this.app.getEmpresaMSI().getProductoresPB())));
            this.scenaryValue
                    .setText(String.valueOf(countNonNullEmployees(this.app.getEmpresaMSI().getProductoresCPU())));
            this.animationValues.setText(
                    String.valueOf(countNonNullEmployees(this.app.getEmpresaMSI().getProductoresRAM())));
            this.dubbingValues
                    .setText(String.valueOf(countNonNullEmployees(this.app.getEmpresaMSI().getProductoresFA())));
            this.plotTwistValues.setText(
                    String.valueOf(countNonNullEmployees(this.app.getEmpresaMSI().getProductoresTG())));
            this.assemblerValues
                    .setText(String.valueOf(countNonNullEmployees(this.app.getEmpresaMSI().getEnsambladores())));
            this.cantidadMaximaTrabajadores.setText(String.valueOf(this.maxEmployees) + "     trabajadores");
        }
    }

    private int countNonNullEmployees(Trabajador[] employees) {
        int count = 0;
        for (Trabajador Trabajador : employees) {
            if (Trabajador != null) {
                count++;
            }
        }
        return count;
    }

    public String formatNumberAsK(int number) {
        // Se onverte el número a miles
        double thousands = number / 1000.0;

        // Se redondea a dos dígitos significativos
        double rounded = Math.round(thousands * 100.0) / 100.0;

        // Se convierte a cadena y se añade 'K'
        return rounded + "K";
    }

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        disponibilidadAlmacen = new javax.swing.JLabel();
        jpanelAlmacenPB = new javax.swing.JPanel();
        scriptTitle1 = new javax.swing.JLabel();
        scriptsLimit1 = new javax.swing.JLabel();
        almacenPB = new javax.swing.JTextField();
        jpanelAlmacenCPU = new javax.swing.JPanel();
        scenaryTitle1 = new javax.swing.JLabel();
        scenaryLimit1 = new javax.swing.JLabel();
        almacenCPU = new javax.swing.JTextField();
        jpanelAlmacenRAM = new javax.swing.JPanel();
        animationsTitle1 = new javax.swing.JLabel();
        animationsLimit1 = new javax.swing.JLabel();
        almacenRAM = new javax.swing.JTextField();
        jpanelAlmacenFA = new javax.swing.JPanel();
        dubbingTitle1 = new javax.swing.JLabel();
        dubbingLimit1 = new javax.swing.JLabel();
        almacenFA = new javax.swing.JTextField();
        jpanelAlmacenTG = new javax.swing.JPanel();
        plotTwistLimit1 = new javax.swing.JLabel();
        plotTwistTitle1 = new javax.swing.JLabel();
        almacenTG = new javax.swing.JTextField();
        jpanelAlmacenEnsambladores = new javax.swing.JPanel();
        plotTwistLimit3 = new javax.swing.JLabel();
        assemblerTitle1 = new javax.swing.JLabel();
        almacenEnsamblador = new javax.swing.JTextField();
        textCantidadTrabajadores = new javax.swing.JLabel();
        modificarRAM = new javax.swing.JPanel();
        animationsTitle = new javax.swing.JLabel();
        animationValues = new javax.swing.JTextField();
        decreaseAnimation = new javax.swing.JButton();
        increaseAnimation = new javax.swing.JButton();
        plotTwist2 = new javax.swing.JPanel();
        assemblerTitle = new javax.swing.JLabel();
        increaseAssembler = new javax.swing.JButton();
        assemblerValues = new javax.swing.JTextField();
        decreaceAssembler = new javax.swing.JButton();
        modificarTG = new javax.swing.JPanel();
        plotTwistTitle = new javax.swing.JLabel();
        increasePlotTwist = new javax.swing.JButton();
        plotTwistValues = new javax.swing.JTextField();
        decreacePlotTwist = new javax.swing.JButton();
        modificarPB = new javax.swing.JPanel();
        scriptsTitle = new javax.swing.JLabel();
        increaseScripts = new javax.swing.JButton();
        scriptsValues = new javax.swing.JTextField();
        decreaseScripts = new javax.swing.JButton();
        driveTitle27 = new javax.swing.JLabel();
        modificarCPU = new javax.swing.JPanel();
        scenaryTitle = new javax.swing.JLabel();
        scenaryValue = new javax.swing.JTextField();
        increaseScenary = new javax.swing.JButton();
        decreaseScenary = new javax.swing.JButton();
        modificarFA = new javax.swing.JPanel();
        dubbingTitle = new javax.swing.JLabel();
        decreaseDubbing = new javax.swing.JButton();
        dubbingValues = new javax.swing.JTextField();
        increaseDubbing = new javax.swing.JButton();
        driveTitle9 = new javax.swing.JLabel();
        driveTitle10 = new javax.swing.JLabel();
        driveTitle11 = new javax.swing.JLabel();
        driveTitle17 = new javax.swing.JLabel();
        costos = new javax.swing.JTextField();
        ingresos = new javax.swing.JTextField();
        ganancia = new javax.swing.JTextField();
        driveTitle18 = new javax.swing.JLabel();
        directorStatus = new javax.swing.JTextField();
        driveTitle14 = new javax.swing.JLabel();
        driveTitle12 = new javax.swing.JLabel();
        driveTitle13 = new javax.swing.JLabel();
        driveTitle16 = new javax.swing.JLabel();
        projectManagerStatus = new javax.swing.JTextField();
        strikeCounter = new javax.swing.JTextField();
        cashPenality = new javax.swing.JTextField();
        driveTitle15 = new javax.swing.JLabel();
        driveTitle20 = new javax.swing.JLabel();
        computadorasTG = new javax.swing.JTextField();
        computadorasNormales = new javax.swing.JTextField();
        driveTitle24 = new javax.swing.JLabel();
        driveTitle = new javax.swing.JLabel();
        driveTitle19 = new javax.swing.JLabel();
        diasRestantes = new javax.swing.JTextField();
        contadorDiasDeLaSimulacion = new javax.swing.JTextField();
        driveTitle23 = new javax.swing.JLabel();
        btn_nuevo_pedido = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btn_nuevo_almacen = new javax.swing.JPanel();
        icono4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btn_reporte = new javax.swing.JPanel();
        icono5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btn_cargar_guardar = new javax.swing.JPanel();
        icono7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btn_nueva_ruta = new javax.swing.JPanel();
        icono3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cantidadMaximaTrabajadores = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setMaximizedBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        disponibilidadAlmacen.setFont(new java.awt.Font("Noteworthy", 1, 18)); // NOI18N
        disponibilidadAlmacen.setForeground(new java.awt.Color(204, 0, 0));
        disponibilidadAlmacen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        disponibilidadAlmacen.setText("Disponibilidad del Almacen");
        jPanel1.add(disponibilidadAlmacen, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 248, -1));

        jpanelAlmacenPB.setBackground(new java.awt.Color(0, 0, 0));
        jpanelAlmacenPB.setForeground(new java.awt.Color(60, 63, 65));
        jpanelAlmacenPB.setPreferredSize(new java.awt.Dimension(218, 44));

        scriptTitle1.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        scriptTitle1.setForeground(new java.awt.Color(255, 255, 255));
        scriptTitle1.setText("Placa Base:");

        scriptsLimit1.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        scriptsLimit1.setForeground(new java.awt.Color(255, 255, 255));
        scriptsLimit1.setText("/25");
        scriptsLimit1.setFocusable(false);

        almacenPB.setBackground(new java.awt.Color(0, 0, 0));
        almacenPB.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        almacenPB.setForeground(new java.awt.Color(255, 255, 255));
        almacenPB.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        almacenPB.setText("0");
        almacenPB.setBorder(null);
        almacenPB.setFocusable(false);
        almacenPB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                almacenPBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpanelAlmacenPBLayout = new javax.swing.GroupLayout(jpanelAlmacenPB);
        jpanelAlmacenPB.setLayout(jpanelAlmacenPBLayout);
        jpanelAlmacenPBLayout.setHorizontalGroup(
            jpanelAlmacenPBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelAlmacenPBLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(scriptTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(almacenPB, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scriptsLimit1)
                .addGap(16, 16, 16))
        );
        jpanelAlmacenPBLayout.setVerticalGroup(
            jpanelAlmacenPBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelAlmacenPBLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelAlmacenPBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scriptTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(scriptsLimit1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(almacenPB))
                .addContainerGap())
        );

        jPanel1.add(jpanelAlmacenPB, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 222, -1));

        jpanelAlmacenCPU.setBackground(new java.awt.Color(0, 0, 0));
        jpanelAlmacenCPU.setForeground(new java.awt.Color(60, 63, 65));
        jpanelAlmacenCPU.setPreferredSize(new java.awt.Dimension(218, 44));

        scenaryTitle1.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        scenaryTitle1.setForeground(new java.awt.Color(255, 255, 255));
        scenaryTitle1.setText("CPU:");
        scenaryTitle1.setPreferredSize(new java.awt.Dimension(70, 21));

        scenaryLimit1.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        scenaryLimit1.setForeground(new java.awt.Color(255, 255, 255));
        scenaryLimit1.setText("/20");
        scenaryLimit1.setFocusable(false);

        almacenCPU.setBackground(new java.awt.Color(0, 0, 0));
        almacenCPU.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        almacenCPU.setForeground(new java.awt.Color(255, 255, 255));
        almacenCPU.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        almacenCPU.setText("0");
        almacenCPU.setBorder(null);
        almacenCPU.setFocusable(false);
        almacenCPU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                almacenCPUActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpanelAlmacenCPULayout = new javax.swing.GroupLayout(jpanelAlmacenCPU);
        jpanelAlmacenCPU.setLayout(jpanelAlmacenCPULayout);
        jpanelAlmacenCPULayout.setHorizontalGroup(
            jpanelAlmacenCPULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelAlmacenCPULayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(scenaryTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(almacenCPU, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scenaryLimit1)
                .addGap(15, 15, 15))
        );
        jpanelAlmacenCPULayout.setVerticalGroup(
            jpanelAlmacenCPULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelAlmacenCPULayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelAlmacenCPULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(almacenCPU, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(scenaryLimit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scenaryTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.add(jpanelAlmacenCPU, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 222, 40));

        jpanelAlmacenRAM.setBackground(new java.awt.Color(0, 0, 0));
        jpanelAlmacenRAM.setForeground(new java.awt.Color(60, 63, 65));
        jpanelAlmacenRAM.setPreferredSize(new java.awt.Dimension(218, 44));

        animationsTitle1.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        animationsTitle1.setForeground(new java.awt.Color(255, 255, 255));
        animationsTitle1.setText("RAM:");
        animationsTitle1.setPreferredSize(new java.awt.Dimension(70, 21));

        animationsLimit1.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        animationsLimit1.setForeground(new java.awt.Color(255, 255, 255));
        animationsLimit1.setText("/55");
        animationsLimit1.setFocusable(false);

        almacenRAM.setBackground(new java.awt.Color(0, 0, 0));
        almacenRAM.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        almacenRAM.setForeground(new java.awt.Color(255, 255, 255));
        almacenRAM.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        almacenRAM.setText("0");
        almacenRAM.setBorder(null);
        almacenRAM.setFocusable(false);
        almacenRAM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                almacenRAMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpanelAlmacenRAMLayout = new javax.swing.GroupLayout(jpanelAlmacenRAM);
        jpanelAlmacenRAM.setLayout(jpanelAlmacenRAMLayout);
        jpanelAlmacenRAMLayout.setHorizontalGroup(
            jpanelAlmacenRAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelAlmacenRAMLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(animationsTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(almacenRAM, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(animationsLimit1)
                .addGap(14, 14, 14))
        );
        jpanelAlmacenRAMLayout.setVerticalGroup(
            jpanelAlmacenRAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelAlmacenRAMLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelAlmacenRAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(almacenRAM, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(animationsTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(animationsLimit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.add(jpanelAlmacenRAM, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, 222, 40));

        jpanelAlmacenFA.setBackground(new java.awt.Color(0, 0, 0));
        jpanelAlmacenFA.setForeground(new java.awt.Color(60, 63, 65));
        jpanelAlmacenFA.setPreferredSize(new java.awt.Dimension(218, 44));

        dubbingTitle1.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        dubbingTitle1.setForeground(new java.awt.Color(255, 255, 255));
        dubbingTitle1.setText("Fuentes A:");
        dubbingTitle1.setPreferredSize(new java.awt.Dimension(70, 21));

        dubbingLimit1.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        dubbingLimit1.setForeground(new java.awt.Color(255, 255, 255));
        dubbingLimit1.setText("/35");
        dubbingLimit1.setFocusable(false);

        almacenFA.setBackground(new java.awt.Color(0, 0, 0));
        almacenFA.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        almacenFA.setForeground(new java.awt.Color(255, 255, 255));
        almacenFA.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        almacenFA.setText("0");
        almacenFA.setBorder(null);
        almacenFA.setFocusable(false);
        almacenFA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                almacenFAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpanelAlmacenFALayout = new javax.swing.GroupLayout(jpanelAlmacenFA);
        jpanelAlmacenFA.setLayout(jpanelAlmacenFALayout);
        jpanelAlmacenFALayout.setHorizontalGroup(
            jpanelAlmacenFALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelAlmacenFALayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dubbingTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(almacenFA, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dubbingLimit1)
                .addGap(14, 14, 14))
        );
        jpanelAlmacenFALayout.setVerticalGroup(
            jpanelAlmacenFALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelAlmacenFALayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelAlmacenFALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dubbingLimit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(almacenFA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dubbingTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.add(jpanelAlmacenFA, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, 222, 40));

        jpanelAlmacenTG.setBackground(new java.awt.Color(0, 0, 0));
        jpanelAlmacenTG.setForeground(new java.awt.Color(60, 63, 65));
        jpanelAlmacenTG.setFocusable(false);
        jpanelAlmacenTG.setPreferredSize(new java.awt.Dimension(218, 44));

        plotTwistLimit1.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        plotTwistLimit1.setForeground(new java.awt.Color(255, 255, 255));
        plotTwistLimit1.setText("/10");
        plotTwistLimit1.setFocusable(false);

        plotTwistTitle1.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        plotTwistTitle1.setForeground(new java.awt.Color(255, 255, 255));
        plotTwistTitle1.setText("Tarjetas G:");
        plotTwistTitle1.setPreferredSize(new java.awt.Dimension(70, 21));

        almacenTG.setBackground(new java.awt.Color(0, 0, 0));
        almacenTG.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        almacenTG.setForeground(new java.awt.Color(255, 255, 255));
        almacenTG.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        almacenTG.setText("0");
        almacenTG.setBorder(null);
        almacenTG.setFocusable(false);
        almacenTG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                almacenTGActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpanelAlmacenTGLayout = new javax.swing.GroupLayout(jpanelAlmacenTG);
        jpanelAlmacenTG.setLayout(jpanelAlmacenTGLayout);
        jpanelAlmacenTGLayout.setHorizontalGroup(
            jpanelAlmacenTGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelAlmacenTGLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(plotTwistTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(almacenTG, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plotTwistLimit1)
                .addGap(14, 14, 14))
        );
        jpanelAlmacenTGLayout.setVerticalGroup(
            jpanelAlmacenTGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelAlmacenTGLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelAlmacenTGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(plotTwistTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addGroup(jpanelAlmacenTGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(plotTwistLimit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(almacenTG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel1.add(jpanelAlmacenTG, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, 222, 40));

        jpanelAlmacenEnsambladores.setBackground(new java.awt.Color(0, 0, 0));
        jpanelAlmacenEnsambladores.setForeground(new java.awt.Color(60, 63, 65));
        jpanelAlmacenEnsambladores.setFocusable(false);
        jpanelAlmacenEnsambladores.setPreferredSize(new java.awt.Dimension(218, 44));

        plotTwistLimit3.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        plotTwistLimit3.setForeground(new java.awt.Color(51, 51, 51));
        plotTwistLimit3.setFocusable(false);

        assemblerTitle1.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        assemblerTitle1.setForeground(new java.awt.Color(255, 255, 255));
        assemblerTitle1.setText("Ensambladores:");
        assemblerTitle1.setPreferredSize(new java.awt.Dimension(70, 21));

        almacenEnsamblador.setBackground(new java.awt.Color(0, 0, 0));
        almacenEnsamblador.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        almacenEnsamblador.setForeground(new java.awt.Color(255, 255, 255));
        almacenEnsamblador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        almacenEnsamblador.setText("0");
        almacenEnsamblador.setBorder(null);
        almacenEnsamblador.setFocusable(false);
        almacenEnsamblador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                almacenEnsambladorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpanelAlmacenEnsambladoresLayout = new javax.swing.GroupLayout(jpanelAlmacenEnsambladores);
        jpanelAlmacenEnsambladores.setLayout(jpanelAlmacenEnsambladoresLayout);
        jpanelAlmacenEnsambladoresLayout.setHorizontalGroup(
            jpanelAlmacenEnsambladoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelAlmacenEnsambladoresLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(assemblerTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(plotTwistLimit3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(almacenEnsamblador, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jpanelAlmacenEnsambladoresLayout.setVerticalGroup(
            jpanelAlmacenEnsambladoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelAlmacenEnsambladoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelAlmacenEnsambladoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(plotTwistLimit3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(almacenEnsamblador)
                    .addComponent(assemblerTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jpanelAlmacenEnsambladores, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 360, 222, -1));

        textCantidadTrabajadores.setFont(new java.awt.Font("Noteworthy", 1, 18)); // NOI18N
        textCantidadTrabajadores.setForeground(new java.awt.Color(204, 0, 0));
        textCantidadTrabajadores.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textCantidadTrabajadores.setText("Cantidad de Trabajadores");
        jPanel1.add(textCantidadTrabajadores, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 227, -1));

        modificarRAM.setBackground(new java.awt.Color(0, 0, 0));
        modificarRAM.setForeground(new java.awt.Color(255, 255, 255));
        modificarRAM.setPreferredSize(new java.awt.Dimension(257, 44));

        animationsTitle.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        animationsTitle.setForeground(new java.awt.Color(255, 255, 255));
        animationsTitle.setText("Prod. RAM:");

        animationValues.setBackground(new java.awt.Color(0, 0, 0));
        animationValues.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        animationValues.setForeground(new java.awt.Color(255, 255, 255));
        animationValues.setText("0");
        animationValues.setBorder(null);
        animationValues.setFocusable(false);
        animationValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                animationValuesActionPerformed(evt);
            }
        });

        decreaseAnimation.setBackground(new java.awt.Color(153, 0, 0));
        decreaseAnimation.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreaseAnimation.setForeground(new java.awt.Color(255, 255, 255));
        decreaseAnimation.setText("-");
        decreaseAnimation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaseAnimationMouseClicked(evt);
            }
        });
        decreaseAnimation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseAnimationActionPerformed(evt);
            }
        });

        increaseAnimation.setBackground(new java.awt.Color(153, 0, 0));
        increaseAnimation.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increaseAnimation.setForeground(new java.awt.Color(255, 255, 255));
        increaseAnimation.setText("+");
        increaseAnimation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseAnimationMouseClicked(evt);
            }
        });
        increaseAnimation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseAnimationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout modificarRAMLayout = new javax.swing.GroupLayout(modificarRAM);
        modificarRAM.setLayout(modificarRAMLayout);
        modificarRAMLayout.setHorizontalGroup(
            modificarRAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarRAMLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(animationsTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(decreaseAnimation)
                .addGap(18, 18, 18)
                .addComponent(animationValues, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(increaseAnimation)
                .addGap(15, 15, 15))
        );
        modificarRAMLayout.setVerticalGroup(
            modificarRAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarRAMLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modificarRAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(decreaseAnimation)
                    .addComponent(animationValues, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(increaseAnimation)
                    .addComponent(animationsTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        jPanel1.add(modificarRAM, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 204, 215, 40));

        plotTwist2.setBackground(new java.awt.Color(0, 0, 0));
        plotTwist2.setForeground(new java.awt.Color(255, 255, 255));
        plotTwist2.setPreferredSize(new java.awt.Dimension(257, 44));

        assemblerTitle.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        assemblerTitle.setForeground(new java.awt.Color(255, 255, 255));
        assemblerTitle.setText("Ensambladores:");

        increaseAssembler.setBackground(new java.awt.Color(153, 0, 0));
        increaseAssembler.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increaseAssembler.setForeground(new java.awt.Color(255, 255, 255));
        increaseAssembler.setText("+");
        increaseAssembler.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseAssemblerMouseClicked(evt);
            }
        });
        increaseAssembler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseAssemblerActionPerformed(evt);
            }
        });

        assemblerValues.setBackground(new java.awt.Color(0, 0, 0));
        assemblerValues.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        assemblerValues.setForeground(new java.awt.Color(255, 255, 255));
        assemblerValues.setText("0");
        assemblerValues.setBorder(null);
        assemblerValues.setFocusable(false);
        assemblerValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assemblerValuesActionPerformed(evt);
            }
        });

        decreaceAssembler.setBackground(new java.awt.Color(153, 0, 0));
        decreaceAssembler.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreaceAssembler.setForeground(new java.awt.Color(255, 255, 255));
        decreaceAssembler.setText("-");
        decreaceAssembler.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaceAssemblerMouseClicked(evt);
            }
        });
        decreaceAssembler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaceAssemblerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout plotTwist2Layout = new javax.swing.GroupLayout(plotTwist2);
        plotTwist2.setLayout(plotTwist2Layout);
        plotTwist2Layout.setHorizontalGroup(
            plotTwist2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plotTwist2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(assemblerTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(decreaceAssembler)
                .addGap(18, 18, 18)
                .addComponent(assemblerValues, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(increaseAssembler)
                .addGap(16, 16, 16))
        );
        plotTwist2Layout.setVerticalGroup(
            plotTwist2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plotTwist2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(plotTwist2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(increaseAssembler)
                    .addComponent(assemblerValues, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(decreaceAssembler)
                    .addComponent(assemblerTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        jPanel1.add(plotTwist2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 215, -1));

        modificarTG.setBackground(new java.awt.Color(0, 0, 0));
        modificarTG.setForeground(new java.awt.Color(255, 255, 255));
        modificarTG.setPreferredSize(new java.awt.Dimension(257, 44));

        plotTwistTitle.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        plotTwistTitle.setForeground(new java.awt.Color(255, 255, 255));
        plotTwistTitle.setText("Prod. TG:");

        increasePlotTwist.setBackground(new java.awt.Color(153, 0, 0));
        increasePlotTwist.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increasePlotTwist.setForeground(new java.awt.Color(255, 255, 255));
        increasePlotTwist.setText("+");
        increasePlotTwist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increasePlotTwistMouseClicked(evt);
            }
        });
        increasePlotTwist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increasePlotTwistActionPerformed(evt);
            }
        });

        plotTwistValues.setBackground(new java.awt.Color(0, 0, 0));
        plotTwistValues.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        plotTwistValues.setForeground(new java.awt.Color(255, 255, 255));
        plotTwistValues.setText("0");
        plotTwistValues.setBorder(null);
        plotTwistValues.setFocusable(false);
        plotTwistValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plotTwistValuesActionPerformed(evt);
            }
        });

        decreacePlotTwist.setBackground(new java.awt.Color(153, 0, 0));
        decreacePlotTwist.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreacePlotTwist.setForeground(new java.awt.Color(255, 255, 255));
        decreacePlotTwist.setText("-");
        decreacePlotTwist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreacePlotTwistMouseClicked(evt);
            }
        });
        decreacePlotTwist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreacePlotTwistActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout modificarTGLayout = new javax.swing.GroupLayout(modificarTG);
        modificarTG.setLayout(modificarTGLayout);
        modificarTGLayout.setHorizontalGroup(
            modificarTGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modificarTGLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(plotTwistTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(decreacePlotTwist)
                .addGap(18, 18, 18)
                .addComponent(plotTwistValues, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(increasePlotTwist)
                .addGap(16, 16, 16))
        );
        modificarTGLayout.setVerticalGroup(
            modificarTGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarTGLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modificarTGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(increasePlotTwist)
                    .addComponent(plotTwistValues, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(decreacePlotTwist)
                    .addComponent(plotTwistTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        jPanel1.add(modificarTG, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 304, 215, 40));

        modificarPB.setBackground(new java.awt.Color(0, 0, 0));
        modificarPB.setForeground(new java.awt.Color(60, 63, 65));

        scriptsTitle.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        scriptsTitle.setForeground(new java.awt.Color(255, 255, 255));
        scriptsTitle.setText("Prod. PB:");

        increaseScripts.setBackground(new java.awt.Color(153, 0, 0));
        increaseScripts.setFont(new java.awt.Font("Noteworthy", 1, 16)); // NOI18N
        increaseScripts.setForeground(new java.awt.Color(255, 255, 255));
        increaseScripts.setText("+");
        increaseScripts.setBorderPainted(false);
        increaseScripts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseScriptsMouseClicked(evt);
            }
        });
        increaseScripts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseScriptsActionPerformed(evt);
            }
        });

        scriptsValues.setBackground(new java.awt.Color(0, 0, 0));
        scriptsValues.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        scriptsValues.setForeground(new java.awt.Color(255, 255, 255));
        scriptsValues.setText("0");
        scriptsValues.setBorder(null);
        scriptsValues.setFocusable(false);
        scriptsValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scriptsValuesActionPerformed(evt);
            }
        });

        decreaseScripts.setBackground(new java.awt.Color(153, 0, 0));
        decreaseScripts.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreaseScripts.setForeground(new java.awt.Color(255, 255, 255));
        decreaseScripts.setText("-");
        decreaseScripts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaseScriptsMouseClicked(evt);
            }
        });
        decreaseScripts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseScriptsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout modificarPBLayout = new javax.swing.GroupLayout(modificarPB);
        modificarPB.setLayout(modificarPBLayout);
        modificarPBLayout.setHorizontalGroup(
            modificarPBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarPBLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(scriptsTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(36, 36, 36)
                .addComponent(decreaseScripts)
                .addGap(18, 18, 18)
                .addComponent(scriptsValues, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(increaseScripts)
                .addGap(11, 11, 11))
        );
        modificarPBLayout.setVerticalGroup(
            modificarPBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarPBLayout.createSequentialGroup()
                .addGroup(modificarPBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(scriptsTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scriptsValues, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(decreaseScripts))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(modificarPBLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(increaseScripts, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(modificarPB, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        driveTitle27.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        driveTitle27.setForeground(new java.awt.Color(255, 255, 255));
        driveTitle27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        driveTitle27.setText("Máximo:");
        jPanel1.add(driveTitle27, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, 60, 36));

        modificarCPU.setBackground(new java.awt.Color(0, 0, 0));
        modificarCPU.setForeground(new java.awt.Color(60, 63, 65));
        modificarCPU.setPreferredSize(new java.awt.Dimension(257, 44));

        scenaryTitle.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        scenaryTitle.setForeground(new java.awt.Color(255, 255, 255));
        scenaryTitle.setText("Prod. CPU:");

        scenaryValue.setBackground(new java.awt.Color(0, 0, 0));
        scenaryValue.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        scenaryValue.setForeground(new java.awt.Color(255, 255, 255));
        scenaryValue.setText("0");
        scenaryValue.setBorder(null);
        scenaryValue.setFocusable(false);
        scenaryValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scenaryValueActionPerformed(evt);
            }
        });

        increaseScenary.setBackground(new java.awt.Color(153, 0, 0));
        increaseScenary.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increaseScenary.setForeground(new java.awt.Color(255, 255, 255));
        increaseScenary.setText("+");
        increaseScenary.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseScenaryMouseClicked(evt);
            }
        });
        increaseScenary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseScenaryActionPerformed(evt);
            }
        });

        decreaseScenary.setBackground(new java.awt.Color(153, 0, 0));
        decreaseScenary.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreaseScenary.setForeground(new java.awt.Color(255, 255, 255));
        decreaseScenary.setText("-");
        decreaseScenary.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaseScenaryMouseClicked(evt);
            }
        });
        decreaseScenary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseScenaryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout modificarCPULayout = new javax.swing.GroupLayout(modificarCPU);
        modificarCPU.setLayout(modificarCPULayout);
        modificarCPULayout.setHorizontalGroup(
            modificarCPULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarCPULayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(scenaryTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(decreaseScenary)
                .addGap(18, 18, 18)
                .addComponent(scenaryValue, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(increaseScenary)
                .addGap(14, 14, 14))
        );
        modificarCPULayout.setVerticalGroup(
            modificarCPULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarCPULayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modificarCPULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modificarCPULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(increaseScenary)
                        .addComponent(scenaryValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(decreaseScenary))
                    .addComponent(scenaryTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.add(modificarCPU, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 215, 40));

        modificarFA.setBackground(new java.awt.Color(0, 0, 0));
        modificarFA.setForeground(new java.awt.Color(255, 255, 255));
        modificarFA.setPreferredSize(new java.awt.Dimension(257, 44));

        dubbingTitle.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        dubbingTitle.setForeground(new java.awt.Color(255, 255, 255));
        dubbingTitle.setText("Prod. FA:");

        decreaseDubbing.setBackground(new java.awt.Color(153, 0, 0));
        decreaseDubbing.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreaseDubbing.setForeground(new java.awt.Color(204, 204, 204));
        decreaseDubbing.setText("-");
        decreaseDubbing.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaseDubbingMouseClicked(evt);
            }
        });
        decreaseDubbing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseDubbingActionPerformed(evt);
            }
        });

        dubbingValues.setBackground(new java.awt.Color(0, 0, 0));
        dubbingValues.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        dubbingValues.setForeground(new java.awt.Color(255, 255, 255));
        dubbingValues.setText("0");
        dubbingValues.setBorder(null);
        dubbingValues.setFocusable(false);
        dubbingValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dubbingValuesActionPerformed(evt);
            }
        });

        increaseDubbing.setBackground(new java.awt.Color(153, 0, 0));
        increaseDubbing.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increaseDubbing.setForeground(new java.awt.Color(255, 255, 255));
        increaseDubbing.setText("+");
        increaseDubbing.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseDubbingMouseClicked(evt);
            }
        });
        increaseDubbing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseDubbingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout modificarFALayout = new javax.swing.GroupLayout(modificarFA);
        modificarFA.setLayout(modificarFALayout);
        modificarFALayout.setHorizontalGroup(
            modificarFALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarFALayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(dubbingTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(decreaseDubbing)
                .addGap(18, 18, 18)
                .addComponent(dubbingValues, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(increaseDubbing)
                .addGap(15, 15, 15))
        );
        modificarFALayout.setVerticalGroup(
            modificarFALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarFALayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modificarFALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(decreaseDubbing)
                    .addComponent(dubbingValues, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(increaseDubbing)
                    .addComponent(dubbingTitle))
                .addContainerGap())
        );

        jPanel1.add(modificarFA, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 254, 215, 40));

        driveTitle9.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        driveTitle9.setForeground(new java.awt.Color(255, 255, 255));
        driveTitle9.setText("Ingresos:");
        driveTitle9.setFocusable(false);
        jPanel1.add(driveTitle9, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 320, 84, -1));

        driveTitle10.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        driveTitle10.setForeground(new java.awt.Color(255, 255, 255));
        driveTitle10.setText("Costos:");
        driveTitle10.setFocusable(false);
        jPanel1.add(driveTitle10, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 290, 84, -1));

        driveTitle11.setFont(new java.awt.Font("Noteworthy", 1, 18)); // NOI18N
        driveTitle11.setForeground(new java.awt.Color(204, 0, 0));
        driveTitle11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        driveTitle11.setText("Resumen Economico");
        driveTitle11.setFocusable(false);
        jPanel1.add(driveTitle11, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 260, 230, -1));

        driveTitle17.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        driveTitle17.setForeground(new java.awt.Color(255, 255, 255));
        driveTitle17.setText("Ganancia:");
        driveTitle17.setFocusable(false);
        jPanel1.add(driveTitle17, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 350, 84, -1));

        costos.setBackground(new java.awt.Color(0, 0, 0));
        costos.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        costos.setForeground(new java.awt.Color(255, 255, 255));
        costos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        costos.setText("0");
        costos.setBorder(null);
        costos.setFocusable(false);
        costos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                costosActionPerformed(evt);
            }
        });
        jPanel1.add(costos, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 290, 140, -1));

        ingresos.setBackground(new java.awt.Color(0, 0, 0));
        ingresos.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        ingresos.setForeground(new java.awt.Color(255, 255, 255));
        ingresos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ingresos.setText("0");
        ingresos.setBorder(null);
        ingresos.setFocusable(false);
        ingresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresosActionPerformed(evt);
            }
        });
        jPanel1.add(ingresos, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 320, 140, 20));

        ganancia.setBackground(new java.awt.Color(0, 0, 0));
        ganancia.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        ganancia.setForeground(new java.awt.Color(255, 255, 255));
        ganancia.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ganancia.setText("0");
        ganancia.setBorder(null);
        ganancia.setFocusable(false);
        ganancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gananciaActionPerformed(evt);
            }
        });
        jPanel1.add(ganancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 350, 140, -1));

        driveTitle18.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        driveTitle18.setForeground(new java.awt.Color(255, 255, 255));
        driveTitle18.setText("Estado:");
        driveTitle18.setFocusable(false);
        jPanel1.add(driveTitle18, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 80, -1, -1));

        directorStatus.setBackground(new java.awt.Color(0, 0, 0));
        directorStatus.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        directorStatus.setForeground(new java.awt.Color(255, 255, 255));
        directorStatus.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        directorStatus.setText("0");
        directorStatus.setBorder(null);
        directorStatus.setFocusable(false);
        directorStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                directorStatusActionPerformed(evt);
            }
        });
        jPanel1.add(directorStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 80, 140, -1));

        driveTitle14.setFont(new java.awt.Font("Noteworthy", 1, 18)); // NOI18N
        driveTitle14.setForeground(new java.awt.Color(204, 0, 0));
        driveTitle14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        driveTitle14.setText("Director");
        driveTitle14.setFocusable(false);
        jPanel1.add(driveTitle14, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 230, -1));

        driveTitle12.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        driveTitle12.setForeground(new java.awt.Color(255, 255, 255));
        driveTitle12.setText("Estado:");
        driveTitle12.setFocusable(false);
        jPanel1.add(driveTitle12, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, 70, -1));

        driveTitle13.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        driveTitle13.setForeground(new java.awt.Color(255, 255, 255));
        driveTitle13.setText("Faltas:");
        driveTitle13.setFocusable(false);
        jPanel1.add(driveTitle13, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 180, 77, -1));

        driveTitle16.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        driveTitle16.setForeground(new java.awt.Color(255, 255, 255));
        driveTitle16.setText("Penalización ($):");
        driveTitle16.setFocusable(false);
        jPanel1.add(driveTitle16, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 210, -1, -1));

        projectManagerStatus.setBackground(new java.awt.Color(0, 0, 0));
        projectManagerStatus.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        projectManagerStatus.setForeground(new java.awt.Color(255, 255, 255));
        projectManagerStatus.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        projectManagerStatus.setText("Por comenzar");
        projectManagerStatus.setBorder(null);
        projectManagerStatus.setFocusable(false);
        projectManagerStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectManagerStatusActionPerformed(evt);
            }
        });
        jPanel1.add(projectManagerStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 150, 140, -1));

        strikeCounter.setBackground(new java.awt.Color(0, 0, 0));
        strikeCounter.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        strikeCounter.setForeground(new java.awt.Color(255, 255, 255));
        strikeCounter.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        strikeCounter.setText("0");
        strikeCounter.setBorder(null);
        strikeCounter.setFocusable(false);
        strikeCounter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                strikeCounterActionPerformed(evt);
            }
        });
        jPanel1.add(strikeCounter, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 180, 140, -1));

        cashPenality.setBackground(new java.awt.Color(0, 0, 0));
        cashPenality.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        cashPenality.setForeground(new java.awt.Color(255, 255, 255));
        cashPenality.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cashPenality.setText("0");
        cashPenality.setBorder(null);
        cashPenality.setFocusable(false);
        cashPenality.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashPenalityActionPerformed(evt);
            }
        });
        jPanel1.add(cashPenality, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 210, 140, -1));

        driveTitle15.setFont(new java.awt.Font("Noteworthy", 1, 18)); // NOI18N
        driveTitle15.setForeground(new java.awt.Color(204, 0, 0));
        driveTitle15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        driveTitle15.setText("Project Manager");
        driveTitle15.setFocusable(false);
        jPanel1.add(driveTitle15, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 120, 230, -1));

        driveTitle20.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        driveTitle20.setForeground(new java.awt.Color(255, 255, 255));
        driveTitle20.setText("Computadoras con TG:");
        driveTitle20.setFocusable(false);
        jPanel1.add(driveTitle20, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 450, 126, -1));

        computadorasTG.setBackground(new java.awt.Color(0, 0, 0));
        computadorasTG.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        computadorasTG.setForeground(new java.awt.Color(255, 255, 255));
        computadorasTG.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        computadorasTG.setText("0");
        computadorasTG.setBorder(null);
        computadorasTG.setFocusable(false);
        computadorasTG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                computadorasTGActionPerformed(evt);
            }
        });
        jPanel1.add(computadorasTG, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 450, 99, -1));

        computadorasNormales.setBackground(new java.awt.Color(0, 0, 0));
        computadorasNormales.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        computadorasNormales.setForeground(new java.awt.Color(255, 255, 255));
        computadorasNormales.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        computadorasNormales.setText("0");
        computadorasNormales.setBorder(null);
        computadorasNormales.setFocusable(false);
        computadorasNormales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                computadorasNormalesActionPerformed(evt);
            }
        });
        jPanel1.add(computadorasNormales, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 420, 99, 20));

        driveTitle24.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        driveTitle24.setForeground(new java.awt.Color(255, 255, 255));
        driveTitle24.setText("Computadoras normales:");
        driveTitle24.setFocusable(false);
        jPanel1.add(driveTitle24, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 420, -1, -1));

        driveTitle.setFont(new java.awt.Font("Noteworthy", 1, 18)); // NOI18N
        driveTitle.setForeground(new java.awt.Color(204, 0, 0));
        driveTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        driveTitle.setText("Existencia");
        jPanel1.add(driveTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 390, 230, -1));

        driveTitle19.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        driveTitle19.setForeground(new java.awt.Color(255, 255, 255));
        driveTitle19.setText("Días para la entrega:");
        driveTitle19.setFocusable(false);
        jPanel1.add(driveTitle19, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 177, 21));

        diasRestantes.setEditable(false);
        diasRestantes.setBackground(new java.awt.Color(0, 0, 0));
        diasRestantes.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        diasRestantes.setForeground(new java.awt.Color(255, 255, 255));
        diasRestantes.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        diasRestantes.setText("0");
        diasRestantes.setBorder(null);
        diasRestantes.setFocusable(false);
        diasRestantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diasRestantesActionPerformed(evt);
            }
        });
        jPanel1.add(diasRestantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(384, 450, 80, -1));

        contadorDiasDeLaSimulacion.setEditable(false);
        contadorDiasDeLaSimulacion.setBackground(new java.awt.Color(0, 0, 0));
        contadorDiasDeLaSimulacion.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        contadorDiasDeLaSimulacion.setForeground(new java.awt.Color(255, 255, 255));
        contadorDiasDeLaSimulacion.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        contadorDiasDeLaSimulacion.setText("0");
        contadorDiasDeLaSimulacion.setBorder(null);
        contadorDiasDeLaSimulacion.setFocusable(false);
        contadorDiasDeLaSimulacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contadorDiasDeLaSimulacionActionPerformed(evt);
            }
        });
        jPanel1.add(contadorDiasDeLaSimulacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(384, 430, 80, -1));

        driveTitle23.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        driveTitle23.setForeground(new java.awt.Color(255, 255, 255));
        driveTitle23.setText("Días:");
        driveTitle23.setFocusable(false);
        jPanel1.add(driveTitle23, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 430, 68, -1));

        btn_nuevo_pedido.setBackground(new java.awt.Color(153, 0, 0));
        btn_nuevo_pedido.setForeground(new java.awt.Color(153, 0, 0));
        btn_nuevo_pedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_nuevo_pedidoMouseClicked(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(153, 0, 0));
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
                .addGap(37, 37, 37)
                .addComponent(jLabel5)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        btn_nuevo_pedidoLayout.setVerticalGroup(
            btn_nuevo_pedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_nuevo_pedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(btn_nuevo_pedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 40));

        btn_nuevo_almacen.setBackground(new java.awt.Color(153, 0, 0));
        btn_nuevo_almacen.setForeground(new java.awt.Color(153, 0, 0));
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

        jLabel7.setBackground(new java.awt.Color(153, 0, 0));
        jLabel7.setFont(new java.awt.Font("Noteworthy", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Configuración");
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
                .addGap(22, 22, 22)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(icono4)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        btn_nuevo_almacenLayout.setVerticalGroup(
            btn_nuevo_almacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_nuevo_almacenLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(icono4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(btn_nuevo_almacenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(btn_nuevo_almacen, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 160, 40));

        btn_reporte.setBackground(new java.awt.Color(153, 0, 0));
        btn_reporte.setForeground(new java.awt.Color(153, 0, 0));
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

        jLabel8.setBackground(new java.awt.Color(153, 0, 0));
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
                .addGap(10, 10, 10)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(icono5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btn_reporteLayout.setVerticalGroup(
            btn_reporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_reporteLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(icono5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(btn_reporteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(btn_reporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 80, 40));

        btn_cargar_guardar.setBackground(new java.awt.Color(255, 51, 51));
        btn_cargar_guardar.setForeground(new java.awt.Color(153, 0, 0));
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

        jLabel10.setBackground(new java.awt.Color(153, 0, 0));
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
                .addGroup(btn_cargar_guardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btn_cargar_guardarLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(icono7))
                    .addGroup(btn_cargar_guardarLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel10)))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        btn_cargar_guardarLayout.setVerticalGroup(
            btn_cargar_guardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_cargar_guardarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(icono7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(btn_cargar_guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 180, 40));

        btn_nueva_ruta.setBackground(new java.awt.Color(153, 0, 0));
        btn_nueva_ruta.setForeground(new java.awt.Color(153, 0, 0));
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

        jLabel6.setBackground(new java.awt.Color(153, 0, 0));
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
                .addGap(55, 55, 55)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(icono3)
                .addContainerGap(72, Short.MAX_VALUE))
        );
        btn_nueva_rutaLayout.setVerticalGroup(
            btn_nueva_rutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_nueva_rutaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(icono3, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(btn_nueva_ruta, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 160, 40));

        cantidadMaximaTrabajadores.setFont(new java.awt.Font("Noteworthy", 1, 12)); // NOI18N
        cantidadMaximaTrabajadores.setForeground(new java.awt.Color(255, 255, 255));
        cantidadMaximaTrabajadores.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel1.add(cantidadMaximaTrabajadores, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 430, 111, 36));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cargar_guardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cargar_guardarMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_btn_cargar_guardarMouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jLabel10MouseClicked

    private void icono7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono7MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_icono7MouseClicked

    private void btn_reporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_reporteMouseClicked
        // TODO add your handling code here:
        try {
            this.helper.write();
            JOptionPane.showMessageDialog(this, "El archivo ha sido guardado exitosamente!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al escribir el archivo");
        }
    }//GEN-LAST:event_btn_reporteMouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        try {
            this.helper.write();
            JOptionPane.showMessageDialog(this, "El archivo ha sido guardado exitosamente!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al escribir el archivo");
        }
    }//GEN-LAST:event_jLabel8MouseClicked

    private void icono5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_icono5MouseClicked

    private void btn_nuevo_almacenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_nuevo_almacenMouseClicked
        // TODO add your handling code here:
        ConfiguracionParametros v2 = new ConfiguracionParametros();
        v2.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_nuevo_almacenMouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        ConfiguracionParametros v2 = new ConfiguracionParametros();
        v2.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void icono4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono4MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_icono4MouseClicked

    private void btn_nueva_rutaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_nueva_rutaMouseClicked
        // TODO add your handling code here:
        HP v3 = new HP();
        v3.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_nueva_rutaMouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        HP v3 = new HP();
        v3.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jLabel6MouseClicked

    private void icono3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono3MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_icono3MouseClicked

    private void btn_nuevo_pedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_nuevo_pedidoMouseClicked
        // TODO add your handling code here:
        Dashboard dashboard = Dashboard.getInstance();
        dashboard.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_nuevo_pedidoMouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        Dashboard dashboard = Dashboard.getInstance();
        dashboard.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void increaseScriptsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increaseScriptsMouseClicked
        // TODO add your handling code here:
        if (this.canIncreaseQuantity(0)) {
            
            this.scriptsValues.setText(increaseQuantity(this.scriptsValues.getText(), increaseScripts));
            helper.sumarTrabajador(0, 0);
        }
        updateBtnStatus();
    }//GEN-LAST:event_increaseScriptsMouseClicked

    private void increaseScriptsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseScriptsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseScriptsActionPerformed

    private void scriptsValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scriptsValuesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scriptsValuesActionPerformed

    private void decreaseScriptsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreaseScriptsMouseClicked
        // TODO add your handling code here:
        if (canDecreaseQuantity(0)) {
            
            this.scriptsValues.setText(decreaseQuantity(this.scriptsValues.getText(), this.decreaseScripts));
            helper.deleteWorker(0, 0);
        }
        updateBtnStatus();
    }//GEN-LAST:event_decreaseScriptsMouseClicked

    private void decreaseScriptsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaseScriptsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_decreaseScriptsActionPerformed

    private void scenaryValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scenaryValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scenaryValueActionPerformed

    private void increaseScenaryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increaseScenaryMouseClicked
        // TODO add your handling code here:
        if (canIncreaseQuantity(1)) {
            this.scenaryValue.setText(increaseQuantity(this.scenaryValue.getText(), increaseScenary));
            
            helper.sumarTrabajador(0, 1);
        }
        updateBtnStatus();
    }//GEN-LAST:event_increaseScenaryMouseClicked

    private void increaseScenaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseScenaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseScenaryActionPerformed

    private void decreaseScenaryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreaseScenaryMouseClicked
        // TODO add your handling code here:
        if (canDecreaseQuantity(1)) {
            
            this.scenaryValue.setText(decreaseQuantity(this.scenaryValue.getText(), decreaseScenary));
            helper.deleteWorker(0, 1);
        }
        updateBtnStatus();
    }//GEN-LAST:event_decreaseScenaryMouseClicked

    private void decreaseScenaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaseScenaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_decreaseScenaryActionPerformed

    private void animationValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_animationValuesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_animationValuesActionPerformed

    private void decreaseAnimationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreaseAnimationMouseClicked
        // TODO add your handling code here:
        if (canDecreaseQuantity(2)) {
            
            this.animationValues.setText(decreaseQuantity(this.animationValues.getText(), decreaseAnimation));
            helper.deleteWorker(0, 2);
        }
        updateBtnStatus();
    }//GEN-LAST:event_decreaseAnimationMouseClicked

    private void decreaseAnimationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaseAnimationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_decreaseAnimationActionPerformed

    private void increaseAnimationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increaseAnimationMouseClicked
        // TODO add your handling code here:
        if (canIncreaseQuantity(2)) {
            
            this.animationValues.setText(increaseQuantity(this.animationValues.getText(), increaseAnimation));
            helper.sumarTrabajador(0, 2);
        }
        updateBtnStatus();
    }//GEN-LAST:event_increaseAnimationMouseClicked

    private void increaseAnimationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseAnimationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseAnimationActionPerformed

    private void decreaseDubbingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreaseDubbingMouseClicked
        // TODO add your handling code here:
        if (canDecreaseQuantity(3)) {
            
            this.dubbingValues.setText(decreaseQuantity(this.dubbingValues.getText(), decreaseDubbing));
            helper.deleteWorker(0, 3);
        }
        updateBtnStatus();
    }//GEN-LAST:event_decreaseDubbingMouseClicked

    private void decreaseDubbingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaseDubbingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_decreaseDubbingActionPerformed

    private void dubbingValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dubbingValuesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dubbingValuesActionPerformed

    private void increaseDubbingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increaseDubbingMouseClicked
        // TODO add your handling code here:
        if (canIncreaseQuantity(3)) {
            
            this.dubbingValues.setText(increaseQuantity(this.dubbingValues.getText(), increaseDubbing));
            helper.sumarTrabajador(0, 3);
        }
        updateBtnStatus();
    }//GEN-LAST:event_increaseDubbingMouseClicked

    private void increaseDubbingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseDubbingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseDubbingActionPerformed

    private void increasePlotTwistMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increasePlotTwistMouseClicked
        // TODO add your handling code here:
        if (canIncreaseQuantity(4)) {
            
            this.plotTwistValues.setText(increaseQuantity(this.plotTwistValues.getText(), increasePlotTwist));
            helper.sumarTrabajador(0, 4);
        }
        updateBtnStatus();
    }//GEN-LAST:event_increasePlotTwistMouseClicked

    private void increasePlotTwistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increasePlotTwistActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increasePlotTwistActionPerformed

    private void plotTwistValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotTwistValuesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_plotTwistValuesActionPerformed

    private void decreacePlotTwistMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreacePlotTwistMouseClicked
        // TODO add your handling code here:
        if (canDecreaseQuantity(4)) {
            
            this.plotTwistValues.setText(decreaseQuantity(this.plotTwistValues.getText(), decreacePlotTwist));
            helper.deleteWorker(0, 4);
        }
        updateBtnStatus();
    }//GEN-LAST:event_decreacePlotTwistMouseClicked

    private void decreacePlotTwistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreacePlotTwistActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_decreacePlotTwistActionPerformed

    private void increaseAssemblerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increaseAssemblerMouseClicked
        // TODO add your handling code here:
        if (canIncreaseQuantity(5)) {
            
            this.assemblerValues.setText(increaseQuantity(this.assemblerValues.getText(), increaseAssembler));
            helper.sumarTrabajador(0, 5);
        }
        updateBtnStatus();
    }//GEN-LAST:event_increaseAssemblerMouseClicked

    private void increaseAssemblerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseAssemblerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseAssemblerActionPerformed

    private void assemblerValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assemblerValuesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_assemblerValuesActionPerformed

    private void decreaceAssemblerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreaceAssemblerMouseClicked
        // TODO add your handling code here:
        if (canDecreaseQuantity(5)) {
            
            this.assemblerValues.setText(decreaseQuantity(this.assemblerValues.getText(), decreaceAssembler));
            helper.deleteWorker(0, 5);
        }
        updateBtnStatus();
    }//GEN-LAST:event_decreaceAssemblerMouseClicked

    private void decreaceAssemblerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaceAssemblerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_decreaceAssemblerActionPerformed

    private void almacenPBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_almacenPBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_almacenPBActionPerformed

    private void almacenCPUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_almacenCPUActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_almacenCPUActionPerformed

    private void almacenRAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_almacenRAMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_almacenRAMActionPerformed

    private void almacenFAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_almacenFAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_almacenFAActionPerformed

    private void almacenTGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_almacenTGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_almacenTGActionPerformed

    private void almacenEnsambladorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_almacenEnsambladorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_almacenEnsambladorActionPerformed

    private void diasRestantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diasRestantesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_diasRestantesActionPerformed

    private void contadorDiasDeLaSimulacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contadorDiasDeLaSimulacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contadorDiasDeLaSimulacionActionPerformed

    private void costosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_costosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_costosActionPerformed

    private void ingresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ingresosActionPerformed

    private void gananciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gananciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gananciaActionPerformed

    private void directorStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_directorStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_directorStatusActionPerformed

    private void projectManagerStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projectManagerStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_projectManagerStatusActionPerformed

    private void strikeCounterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_strikeCounterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_strikeCounterActionPerformed

    private void cashPenalityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashPenalityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cashPenalityActionPerformed

    private void computadorasTGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_computadorasTGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_computadorasTGActionPerformed

    private void computadorasNormalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_computadorasNormalesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_computadorasNormalesActionPerformed

    private String increaseQuantity(String actualValue, JButton btn) {
        int intValue = 0;
        try {
            intValue = Integer.parseInt(actualValue);
            if (actualEmployees < maxEmployees) {
                intValue++;
                actualEmployees++;
            }
            return String.valueOf(intValue);
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir el valor a int: " + e.getMessage());
            return actualValue; // Retorna el valor actual en caso de error
        }
    }

    private String decreaseQuantity(String actualValue, JButton btn) {
        int intValue = 0;
        try {
            intValue = Integer.parseInt(actualValue);
            if (intValue > 1) {
                intValue--;
                actualEmployees--;
                return String.valueOf(intValue);
            } else {
                return String.valueOf(intValue);
            }
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir el valor a int: " + e.getMessage());
        }
        return null;
    }

    private boolean canDecreaseQuantity(int type) {
        updateValues();
        return values[type] > 1;
    }

    private boolean canIncreaseQuantity(int type) {
        updateValues();
        return actualEmployees < maxEmployees;
    }

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
            java.util.logging.Logger.getLogger(MSI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MSI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MSI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MSI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new MSI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField almacenCPU;
    private javax.swing.JTextField almacenEnsamblador;
    private javax.swing.JTextField almacenFA;
    private javax.swing.JTextField almacenPB;
    private javax.swing.JTextField almacenRAM;
    private javax.swing.JTextField almacenTG;
    private javax.swing.JTextField animationValues;
    private javax.swing.JLabel animationsLimit1;
    private javax.swing.JLabel animationsTitle;
    private javax.swing.JLabel animationsTitle1;
    private javax.swing.JLabel assemblerTitle;
    private javax.swing.JLabel assemblerTitle1;
    private javax.swing.JTextField assemblerValues;
    private javax.swing.JPanel btn_cargar_guardar;
    private javax.swing.JPanel btn_nueva_ruta;
    private javax.swing.JPanel btn_nuevo_almacen;
    private javax.swing.JPanel btn_nuevo_pedido;
    private javax.swing.JPanel btn_reporte;
    private javax.swing.JLabel cantidadMaximaTrabajadores;
    private javax.swing.JTextField cashPenality;
    private javax.swing.JTextField computadorasNormales;
    private javax.swing.JTextField computadorasTG;
    private javax.swing.JTextField contadorDiasDeLaSimulacion;
    private javax.swing.JTextField costos;
    private javax.swing.JButton decreaceAssembler;
    private javax.swing.JButton decreacePlotTwist;
    private javax.swing.JButton decreaseAnimation;
    private javax.swing.JButton decreaseDubbing;
    private javax.swing.JButton decreaseScenary;
    private javax.swing.JButton decreaseScripts;
    private javax.swing.JTextField diasRestantes;
    private javax.swing.JTextField directorStatus;
    private javax.swing.JLabel disponibilidadAlmacen;
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
    private javax.swing.JLabel driveTitle19;
    private javax.swing.JLabel driveTitle20;
    private javax.swing.JLabel driveTitle23;
    private javax.swing.JLabel driveTitle24;
    private javax.swing.JLabel driveTitle27;
    private javax.swing.JLabel driveTitle9;
    private javax.swing.JLabel dubbingLimit1;
    private javax.swing.JLabel dubbingTitle;
    private javax.swing.JLabel dubbingTitle1;
    private javax.swing.JTextField dubbingValues;
    private javax.swing.JTextField ganancia;
    private javax.swing.JLabel icono3;
    private javax.swing.JLabel icono4;
    private javax.swing.JLabel icono5;
    private javax.swing.JLabel icono7;
    private javax.swing.JButton increaseAnimation;
    private javax.swing.JButton increaseAssembler;
    private javax.swing.JButton increaseDubbing;
    private javax.swing.JButton increasePlotTwist;
    private javax.swing.JButton increaseScenary;
    private javax.swing.JButton increaseScripts;
    private javax.swing.JTextField ingresos;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jpanelAlmacenCPU;
    private javax.swing.JPanel jpanelAlmacenEnsambladores;
    private javax.swing.JPanel jpanelAlmacenFA;
    private javax.swing.JPanel jpanelAlmacenPB;
    private javax.swing.JPanel jpanelAlmacenRAM;
    private javax.swing.JPanel jpanelAlmacenTG;
    private javax.swing.JPanel modificarCPU;
    private javax.swing.JPanel modificarFA;
    private javax.swing.JPanel modificarPB;
    private javax.swing.JPanel modificarRAM;
    private javax.swing.JPanel modificarTG;
    private javax.swing.JPanel plotTwist2;
    private javax.swing.JLabel plotTwistLimit1;
    private javax.swing.JLabel plotTwistLimit3;
    private javax.swing.JLabel plotTwistTitle;
    private javax.swing.JLabel plotTwistTitle1;
    private javax.swing.JTextField plotTwistValues;
    private javax.swing.JTextField projectManagerStatus;
    private javax.swing.JLabel scenaryLimit1;
    private javax.swing.JLabel scenaryTitle;
    private javax.swing.JLabel scenaryTitle1;
    private javax.swing.JTextField scenaryValue;
    private javax.swing.JLabel scriptTitle1;
    private javax.swing.JLabel scriptsLimit1;
    private javax.swing.JLabel scriptsTitle;
    private javax.swing.JTextField scriptsValues;
    private javax.swing.JTextField strikeCounter;
    private javax.swing.JLabel textCantidadTrabajadores;
    // End of variables declaration//GEN-END:variables
}
