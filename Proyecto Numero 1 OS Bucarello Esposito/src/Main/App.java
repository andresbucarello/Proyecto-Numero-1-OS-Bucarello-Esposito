/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Clases.Empresa;
import interfaces.Dashboard;
import interfaces.ManejadorGraficos;
/**
 *
 * @author andresbucarello
 */
public class App {

    private static int duracionDelDia;
    private static int diasParaLaEntrega;
    
    private Empresa empresaHP;
    private Empresa empresaMSI;
    private static ManejadorGraficos manejadorGraficos;


    private static App app;
    
    /**
     * Devuelve una instancia única de la aplicación.
     *
     * @return La instancia única de la aplicación.
     */
    public static synchronized App getInstance() {
        if (getApp() == null) {
            setApp(new App());
        }
        return getApp();
    }
    
    public void start() {
        
        Helpers.cargarParametros();
        
        
        // Inicia la simulacion
        getEmpresaHP().start();
        getEmpresaMSI().start();
        manejadorGraficos = new ManejadorGraficos();
        System.out.println("1");


        Dashboard home = new Dashboard();
        home.setVisible(true);
    }

    /**
     * @return the duracionDelDia
     */
    public static int getDuracionDelDia() {
        return duracionDelDia;
    }

    /**
     * @param aDuracionDelDia the duracionDelDia to set
     */
    public static void setDuracionDelDia(int aDuracionDelDia) {
        duracionDelDia = aDuracionDelDia;
    }

    /**
     * @return the diasParaLaEntrega
     */
    public static int getDiasParaLaEntrega() {
        return diasParaLaEntrega;
    }

    /**
     * @param aDiasParaLaEntrega the diasParaLaEntrega to set
     */
    public static void setDiasParaLaEntrega(int aDiasParaLaEntrega) {
        diasParaLaEntrega = aDiasParaLaEntrega;
    }

    /**
     * @return the empresaHP
     */
    public Empresa getEmpresaHP() {
        return empresaHP;
    }

    /**
     * @param empresaHP the empresaHP to set
     */
    public void setEmpresaHP(Empresa empresaHP) {
        this.empresaHP = empresaHP;
    }

    /**
     * @return the empresaMSI
     */
    public Empresa getEmpresaMSI() {
        return empresaMSI;
    }

    /**
     * @param empresaMSI the empresaMSI to set
     */
    public void setEmpresaMSI(Empresa empresaMSI) {
        this.empresaMSI = empresaMSI;
    }

    /**
     * @return the chartManager
     */
    public static ManejadorGraficos getChartManager() {
        return manejadorGraficos;
    }

    /**
     * @param aChartManager the chartManager to set
     */
    public static void setChartManager(ManejadorGraficos aChartManager) {
        manejadorGraficos = aChartManager;
    }

    /**
     * @return the app
     */
    public static App getApp() {
        return app;
    }

    /**
     * @param aApp the app to set
     */
    public static void setApp(App aApp) {
        app = aApp;
    }
    
    
    

}