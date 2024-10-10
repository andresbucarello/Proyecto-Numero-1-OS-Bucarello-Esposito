/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Main.App;
import Main.Helpers;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.Semaphore;

/**
 *
 * @author andresbucarello
 */
public class Director extends Trabajador{
    
    private String estadoActual;
    private App app=App.getInstance();
    
    public Director(String empresa,int tipoEmpleado,int cantidadDeDiasxCiclo,int cantidadDeTrabajosPorCiclo,int sueldoPorHora,Almacen almacen,Semaphore semaforo) {
        super(empresa,tipoEmpleado,cantidadDeDiasxCiclo,cantidadDeTrabajosPorCiclo,sueldoPorHora,almacen,semaforo);
        this.estadoActual="Inactivo";
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                this.pagar();
                int dayDuration = app.getDuracionDelDia();
                int oneHour = dayDuration / 24;
                // Se determina cuanto son 35 minutos.
                int thirtyFiveMinutes = (int) (oneHour * (35.0 / 60.0));
                int remainingMinutes = oneHour - thirtyFiveMinutes;

                // Se buscan los días restantes.
                int remainingDays = this.getEmpresa() == "MSI" ? app.getEmpresaMSI().getDiasParaEntrega()
                        : app.getEmpresaHP().getDiasParaEntrega();

                if (remainingDays <= 0) {
                    this.setEstadoActual("Enviando Computadoras");

                    this.getSemaforo().acquire();
                    this.enviarComputadoras();
                    this.getSemaforo().release();

                } // Si es un dia diferente al 0 entonces hace sus labores administrativas y
                  // supervisa al PM
                else {
                    Random rand = new Random();
                    int randomHour = rand.nextInt(24);

                    for (int i = 0; i < 24; i++) {
                        if (i == randomHour) {
                            vigilarProjectManager();
                            Thread.sleep(thirtyFiveMinutes);
                            vigilarProjectManager();
                            // Basta con solo 2 revisadas porque solo puede cambiar 2 veces el status del PM
                            // en 1 hora.
                            Thread.sleep(remainingMinutes);
                        } else {
                            this.estadoActual = "Administrando";
                            Thread.sleep(oneHour);
                        }
                    }
                }
                Thread.sleep(dayDuration);
            } catch (InterruptedException ex) {
                Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void resetearDiasParaLaEntrega() {
        Empresa empresa = Helpers.getEmpresa(this.getEmpresa());
        empresa.setDiasParaEntrega(app.getDiasParaLaEntrega());
    }
    
    private void enviarComputadoras() {
        try {
            this.setEstadoActual("Enviando computadoras");

            // Esperar un día completo (simulado)
            Thread.sleep(app.getDuracionDelDia());
            // Se reinicia el deadline
            this.resetearDiasParaLaEntrega();

            Empresa empresa = Helpers.getEmpresa(this.getEmpresa());

            // Enviamos los computadores
            empresa.getAlmacen().resetComputadoras(); // <-
            
            Helpers.calcularIngresos(this.getEmpresa());
            Helpers.calcularGanancias(this.getEmpresa());

            // Settiamos los valores actuales a 0
            empresa.setComputadorasNormales(0);
            empresa.setComputadorasTG(0);


        } catch (InterruptedException ex) {
            Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void vigilarProjectManager() {
        this.estadoActual = "Vigilando al Project Manager";
        Empresa empresa = Helpers.getEmpresa(this.getEmpresa());

        if ("Viendo Anime".equals(empresa.getProjectManager().getEstadoActual())) {

            try {
                // Pedimos permiso al mutex para poder reducir el salario del PM al costo total
                this.getSemaforo().acquire();
                empresa.getProjectManager().addStrike();
                empresa.setCostos(empresa.getCostos() - 100);
                // Se calcula las ganancias
                this.getSemaforo().release();

            } catch (InterruptedException ex) {
                Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * @return the estadoActual
     */
    public String getEstadoActual() {
        return estadoActual;
    }

    /**
     * @param estadoActual the estadoActual to set
     */
    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
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
      
}