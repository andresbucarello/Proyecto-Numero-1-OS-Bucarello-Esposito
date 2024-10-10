/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Main.App;
import java.util.concurrent.Semaphore;

/**
 *
 * @author andresbucarello
 */
public class Empresa {
    
    
    
    
    
    
    private String nombre;
    private int cantidadTrabajadoresMaximos; // Ver si 20 o 22
    private int cantidadTrabajadoresActuales=0;
    private Trabajador[] productoresPB;
    private Trabajador[] productoresCPU;
    private Trabajador[] productoresRAM;
    private Trabajador[] productoresFA;
    private Trabajador[] productoresTG;
    private Trabajador[] ensambladores;
    private ProjectManager projectManager;
    private Director director;
    private Almacen almacen;
    private Semaphore semaforo;
    private int costos=0;
    private int ingresos=0;
    private int ganancias=0;
    
    private static int dias=0;
    private static int diasParaEntrega= App.getInstance().getDiasParaLaEntrega();
    private int computadorasNormales=0;
    private int computadorasTG=0;
    private int triggerTG=0;
    
    public Empresa(String nombre,int cantidadTrabajadoresMaximos,Trabajador[] productoresPB,Trabajador[] productoresCPU,Trabajador[] productoresRAM,Trabajador[] productoresFA,Trabajador[] productoresTG,Trabajador[] ensambladores,ProjectManager projectManager,Director director,Almacen almacen,Semaphore semaforo){
        this.nombre=nombre;
        this.cantidadTrabajadoresMaximos=cantidadTrabajadoresMaximos;
        this.productoresPB=productoresPB;
        this.productoresCPU=productoresCPU;
        this.productoresRAM=productoresRAM;
        this.productoresFA=productoresFA;
        this.productoresTG=productoresTG;
        this.ensambladores=ensambladores;
        this.projectManager=projectManager;
        this.director=director;
        this.almacen=almacen;
        this.semaforo=semaforo;
        cantidadTrabajadoresActuales();
    }
    
    public void start() {

        for (int i = 0; i < this.getProductoresPB().length; i++) {
            if (this.getProductoresPB()[i] != null) {
                this.getProductoresPB()[i].start();
            }
        }
        for (int i = 0; i < this.getProductoresCPU().length; i++) {
            if (this.getProductoresCPU()[i] != null) {
                this.getProductoresCPU()[i].start();
            }
        }
        for (int i = 0; i < this.getProductoresRAM().length; i++) {
            if (this.getProductoresRAM()[i] != null) {
                this.getProductoresRAM()[i].start();
            }
        }
        for (int i = 0; i < this.getProductoresFA().length; i++) {
            if (this.getProductoresFA()[i] != null) {
                this.getProductoresFA()[i].start();
            }
        }
        for (int i = 0; i < this.getProductoresTG().length; i++) {
            if (this.getProductoresTG()[i] != null) {
                this.getProductoresTG()[i].start();
            }
        }
        for (int i = 0; i < this.getEnsambladores().length; i++) {
            if (this.getEnsambladores()[i] != null) {
                this.getEnsambladores()[i].start();
            }
        }
        this.getProjectManager().start();
        this.getDirector().start();

    }
    
    public void cantidadTrabajadoresActuales() {
        int totalEmployees = 0;

        // Contar empleados no nulos en cada arreglo
        totalEmployees += contarNoNulos(productoresPB);
        totalEmployees += contarNoNulos(productoresCPU);
        totalEmployees += contarNoNulos(productoresRAM);
        totalEmployees += contarNoNulos(productoresFA);
        totalEmployees += contarNoNulos(productoresTG);
        totalEmployees += contarNoNulos(ensambladores);

        this.setCantidadTrabajadoresActuales(totalEmployees);
    }
    
    public int contarNoNulos(Trabajador[] empleados) {
        int count = 0;
        for (Trabajador trabajador : empleados) {
            if (trabajador != null) {
                count++;
            }
        }
        return count;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the cantidadTrabajadoresMaximos
     */
    public int getCantidadTrabajadoresMaximos() {
        return cantidadTrabajadoresMaximos;
    }

    /**
     * @param cantidadTrabajadoresMaximos the cantidadTrabajadoresMaximos to set
     */
    public void setCantidadTrabajadoresMaximos(int cantidadTrabajadoresMaximos) {
        this.cantidadTrabajadoresMaximos = cantidadTrabajadoresMaximos;
    }

    /**
     * @return the cantidadTrabajadoresActuales
     */
    public int getCantidadTrabajadoresActuales() {
        return cantidadTrabajadoresActuales;
    }

    /**
     * @param cantidadTrabajadoresActuales the cantidadTrabajadoresActuales to set
     */
    public void setCantidadTrabajadoresActuales(int cantidadTrabajadoresActuales) {
        this.cantidadTrabajadoresActuales = cantidadTrabajadoresActuales;
    }

    /**
     * @return the productoresPB
     */
    public Trabajador[] getProductoresPB() {
        return productoresPB;
    }

    /**
     * @param productoresPB the productoresPB to set
     */
    public void setProductoresPB(Trabajador[] productoresPB) {
        this.productoresPB = productoresPB;
    }

    /**
     * @return the productoresCPU
     */
    public Trabajador[] getProductoresCPU() {
        return productoresCPU;
    }

    /**
     * @param productoresCPU the productoresCPU to set
     */
    public void setProductoresCPU(Trabajador[] productoresCPU) {
        this.productoresCPU = productoresCPU;
    }

    /**
     * @return the productoresRAM
     */
    public Trabajador[] getProductoresRAM() {
        return productoresRAM;
    }

    /**
     * @param productoresRAM the productoresRAM to set
     */
    public void setProductoresRAM(Trabajador[] productoresRAM) {
        this.productoresRAM = productoresRAM;
    }

    /**
     * @return the productoresFA
     */
    public Trabajador[] getProductoresFA() {
        return productoresFA;
    }

    /**
     * @param productoresFA the productoresFA to set
     */
    public void setProductoresFA(Trabajador[] productoresFA) {
        this.productoresFA = productoresFA;
    }

    /**
     * @return the productoresTG
     */
    public Trabajador[] getProductoresTG() {
        return productoresTG;
    }

    /**
     * @param productoresTG the productoresTG to set
     */
    public void setProductoresTG(Trabajador[] productoresTG) {
        this.productoresTG = productoresTG;
    }

    /**
     * @return the ensambladores
     */
    public Trabajador[] getEnsambladores() {
        return ensambladores;
    }

    /**
     * @param ensambladores the ensambladores to set
     */
    public void setEnsambladores(Trabajador[] ensambladores) {
        this.ensambladores = ensambladores;
    }

    /**
     * @return the projectManager
     */
    public ProjectManager getProjectManager() {
        return projectManager;
    }

    /**
     * @param projectManager the projectManager to set
     */
    public void setProjectManager(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    /**
     * @return the director
     */
    public Director getDirector() {
        return director;
    }

    /**
     * @param director the director to set
     */
    public void setDirector(Director director) {
        this.director = director;
    }

    /**
     * @return the almacen
     */
    public Almacen getAlmacen() {
        return almacen;
    }

    /**
     * @param aAlmacen the almacen to set
     */
    public void setAlmacen(Almacen aAlmacen) {
        almacen = aAlmacen;
    }

    /**
     * @return the semaforo
     */
    public Semaphore getSemaforo() {
        return semaforo;
    }

    /**
     * @param semaforo the semaforo to set
     */
    public void setSemaforo(Semaphore semaforo) {
        this.semaforo = semaforo;
    }

    /**
     * @return the costos
     */
    public int getCostos() {
        return costos;
    }

    /**
     * @param costos the costos to set
     */
    public void setCostos(int costos) {
        this.costos = costos;
    }

    /**
     * @return the ingresos
     */
    public int getIngresos() {
        return ingresos;
    }

    /**
     * @param ingresos the ingresos to set
     */
    public void setIngresos(int ingresos) {
        this.ingresos = ingresos;
    }

    /**
     * @return the ganancias
     */
    public int getGanancias() {
        return ganancias;
    }

    /**
     * @param ganancias the ganancias to set
     */
    public void setGanancias(int ganancias) {
        this.ganancias = ganancias;
    }

    /**
     * @return the dias
     */
    public static int getDias() {
        return dias;
    }

    /**
     * @param aDias the dias to set
     */
    public static void setDias(int aDias) {
        dias = aDias;
    }

    /**
     * @return the diasParaEntrega
     */
    public static int getDiasParaEntrega() {
        return diasParaEntrega;
    }
    
    public void restarDiasParaEntrega() {
        this.diasParaEntrega--;
    }

    /**
     * @param aDiasParaEntrega the diasParaEntrega to set
     */
    public static void setDiasParaEntrega(int aDiasParaEntrega) {
        diasParaEntrega = aDiasParaEntrega;
    }

    /**
     * @return the computadorasNormales
     */
    public int getComputadorasNormales() {
        return computadorasNormales;
    }

    /**
     * @param computadorasNormales the computadorasNormales to set
     */
    public void setComputadorasNormales(int computadorasNormales) {
        this.computadorasNormales = computadorasNormales;
    }

    /**
     * @return the computadorasTG
     */
    public int getComputadorasTG() {
        return computadorasTG;
    }

    /**
     * @param computadorasTG the computadorasTG to set
     */
    public void setComputadorasTG(int computadorasTG) {
        this.computadorasTG = computadorasTG;
    }

    /**
     * @return the triggerTG
     */
    public int getTriggerTG() {
        return triggerTG;
    }

    /**
     * @param triggerTG the triggerTG to set
     */
    public void setTriggerTG(int triggerTG) {
        this.triggerTG = triggerTG;
    }
    
    
}