/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Main.App;
import Main.Helpers;
import Main.ImportantConstants;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andresbucarello
 */
public class Trabajador extends Thread {

    private String empresa;
    private int tipoEmpleado;
    private int cantidadDeDiasxCiclo;
    private int cantidadDeTrabajosPorCiclo;
    private App app = App.getInstance();
    private Almacen almacen;
    private Semaphore semaforo;

    private int sueldoPorHora;
    
    private float dailyProgress;
    private float totalWork;

    public Trabajador(String empresa, int tipoEmpleado, int cantidadDeDiasxCiclo, int cantidadDeTrabajosPorCiclo, int sueldoPorHora, Almacen almacen, Semaphore semaforo) {
        this.empresa = empresa;
        this.tipoEmpleado = tipoEmpleado;
        this.cantidadDeDiasxCiclo = cantidadDeDiasxCiclo;
        this.cantidadDeTrabajosPorCiclo = cantidadDeTrabajosPorCiclo;
        this.sueldoPorHora = sueldoPorHora;
        this.almacen = almacen;
        this.semaforo = semaforo;
        this.dailyProgress= (float) cantidadDeTrabajosPorCiclo / cantidadDeDiasxCiclo;
        this.totalWork=0;
    }
    
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                this.pagar();
                this.addDailyProduction();
                this.trabajar();
                sleep(App.getDuracionDelDia());
            } catch (InterruptedException ex) {
                // Interrupción manejada, preparándose para terminar el hilo
                Logger.getLogger(Trabajador.class.getName()).log(Level.INFO,
                        "Hilo de Employee interrumpido, terminando...");
                break;
            }
        }
    }

    private void trabajar() {
        if (getTotalWork() >= 1) {
            try {
                this.getSemaforo().acquire();
                int workToUpload = (int) Math.floor(this.getTotalWork());

                if (this.tipoEmpleado != 5) {
                    this.getAlmacen().almacenarTrabajo(getTipoEmpleado(), workToUpload);
                } else {
                    this.crearComputadora();
                }
                this.setTotalWork(Math.max(0, this.getTotalWork() - workToUpload));
                this.getSemaforo().release();
            } catch (InterruptedException ex) {
                Logger.getLogger(Trabajador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void addDailyProduction() {
        if (this.getTipoEmpleado() == 5 && !(this.evaluarPosibilidadDeArmar())) {
            this.setTotalWork(0);
        }
        this.setTotalWork(this.getTotalWork() + this.getDailyProgress());
    }

    private void crearComputadora() {
        Empresa empresa = Helpers.getEmpresa(this.getEmpresa());
        int company = 0;
        if ("HP".equalsIgnoreCase(empresa.getNombre())) {
            company = 1;
            System.out.println(" ########### HP ############");
        }else{
            System.out.println(" ########### MSI ############");
        }

        if (this.evaluarPosibilidadDeArmar()) {
            // Evaluar si el siguiente capítulo debe ser un plot twist antes de incrementar
            // numChapters
            System.out.println("Trigger");
            System.out.println(empresa.getComputadorasNormales());
            System.out.println(ImportantConstants.frecuenciaTG[company]);
            boolean siguienteComputadoraTG = (empresa.getTriggerTG() == ImportantConstants.frecuenciaTG[company]);
            
            System.out.println(siguienteComputadoraTG);

            if (siguienteComputadoraTG) {
                for (int i = 0; i < empresa.getAlmacen().getComponentes().length - 1; i++) {
                    empresa.getAlmacen().getComponentes()[i] = Math.max(0, empresa.getAlmacen().getComponentes()[i] - ImportantConstants.composicionDeComputadoras[company][i]);
                }
                System.out.println("Supuestamente soy computadora TG");
                System.out.println(empresa.getTriggerTG());
                empresa.setComputadorasTG(empresa.getComputadorasTG() + 1);
                empresa.setTriggerTG(0);
            } else {
                // Computadora regular
                
                for (int i = 0; i < empresa.getAlmacen().getComponentes().length - 2; i++) {
                    empresa.getAlmacen().getComponentes()[i] = Math.max(0, empresa.getAlmacen().getComponentes()[i] - ImportantConstants.composicionDeComputadoras[company][i]);
                }
                System.out.println("Soy computadora normal");
                System.out.println(empresa.getTriggerTG());
                empresa.setComputadorasNormales(empresa.getComputadorasNormales() + 1);
                empresa.setTriggerTG(empresa.getTriggerTG() + 1);
                System.out.println(empresa.getTriggerTG());
            }
            this.getAlmacen().getComponentes()[5] += 1;
        }
    }

    private boolean evaluarPosibilidadDeArmar() {

        Empresa empresa = Helpers.getEmpresa(this.getEmpresa());
        int company = 0;
        if (empresa.getNombre() == "HP") {
            company = 1;
        }

        // Requisito minimo para un cap (cap regular)
        for (int i = 0; i < empresa.getAlmacen().getComponentes().length - 2; i++) {
            // Si no hay la cantidad minima entonces el assembler no puede tranbajar
            if (empresa.getAlmacen().getComponentes()[i] < ImportantConstants.composicionDeComputadoras[company][i]) {
                return false;
            }
        }
        // si es plottwist
        boolean siguienteComputadoraTG = (empresa.getComputadorasNormales() == ImportantConstants.frecuenciaTG[company]);

        if (siguienteComputadoraTG) {
            // Verifica si NO hay para hacer un plottwist
            if (empresa.getAlmacen().getComponentes()[4] < ImportantConstants.composicionDeComputadoras[company][4]) {
                return false;
            }
        }
        return true;
    }

    public void pagar() {
        Empresa empresa = Helpers.getEmpresa(this.getEmpresa());
        empresa.setCostos(empresa.getCostos() + (this.getSueldoPorHora() * 24));
    }

    /**
     * @return the empresa
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * @return the tipoEmpleado
     */
    public int getTipoEmpleado() {
        return tipoEmpleado;
    }

    /**
     * @param tipoEmpleado the tipoEmpleado to set
     */
    public void setTipoEmpleado(int tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    /**
     * @return the cantidadDeDiasxCiclo
     */
    public int getCantidadDeDiasxCiclo() {
        return cantidadDeDiasxCiclo;
    }

    /**
     * @param cantidadDeDiasxCiclo the cantidadDeDiasxCiclo to set
     */
    public void setCantidadDeDiasxCiclo(int cantidadDeDiasxCiclo) {
        this.cantidadDeDiasxCiclo = cantidadDeDiasxCiclo;
    }

    /**
     * @return the cantidadDeTrabajosPorCiclo
     */
    public int getCantidadDeTrabajosPorCiclo() {
        return cantidadDeTrabajosPorCiclo;
    }

    /**
     * @param cantidadDeTrabajosPorCiclo the cantidadDeTrabajosPorCiclo to set
     */
    public void setCantidadDeTrabajosPorCiclo(int cantidadDeTrabajosPorCiclo) {
        this.cantidadDeTrabajosPorCiclo = cantidadDeTrabajosPorCiclo;
    }

    /**
     * @return the app
     */
    public App getApp() {
        return app;
    }

    /**
     * @param app the app to set
     */
    public void setApp(App app) {
        this.app = app;
    }

    /**
     * @return the almacen
     */
    public Almacen getAlmacen() {
        return almacen;
    }

    /**
     * @param almacen the almacen to set
     */
    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
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
     * @return the sueldoPorHora
     */
    public int getSueldoPorHora() {
        return sueldoPorHora;
    }

    /**
     * @param sueldoPorHora the sueldoPorHora to set
     */
    public void setSueldoPorHora(int sueldoPorHora) {
        this.sueldoPorHora = sueldoPorHora;
    }

    /**
     * @return the dailyProgress
     */
    public float getDailyProgress() {
        return dailyProgress;
    }

    /**
     * @param dailyProgress the dailyProgress to set
     */
    public void setDailyProgress(float dailyProgress) {
        this.dailyProgress = dailyProgress;
    }

    /**
     * @return the totalWork
     */
    public float getTotalWork() {
        return totalWork;
    }

    /**
     * @param totalWork the totalWork to set
     */
    public void setTotalWork(float totalWork) {
        this.totalWork = totalWork;
    }
}
