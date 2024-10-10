/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Main.App;
import Main.Helpers;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andresbucarello
 */
public class ProjectManager extends Trabajador {

    private String estadoActual;
    private int strikes;

    public ProjectManager(String empresa, int tipoEmpleado, int cantidadDeDiasxCiclo, int cantidadDeTrabajosPorCiclo, int sueldoPorHora, Almacen almacen, Semaphore semaforo) {
        super(empresa, tipoEmpleado, cantidadDeDiasxCiclo, cantidadDeTrabajosPorCiclo, sueldoPorHora, almacen, semaforo);
        this.estadoActual="Inactivo";
        this.strikes=0;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Se obtiene la duración del día total directo de la variable de app.
                int dayDuration = App.getDuracionDelDia();

                // Duración de una hora en la simulación
                int oneHour = dayDuration / 24;

                // Se sabe que pasa 16 horas alterando ver anime y trabajar cada 30 mins.
                // Por lo tanto se loopea cada 30 mins (32 intervalos de 30 mins) para las
                // primeras 16 h.
                for (int i = 0; i < 32; i++) {
                    if (i % 2 == 0) {
                        this.setEstadoActual("Viendo Anime");

                    } else {
                        setEstadoActual("Trabajando");

                    }
                    // Duerme por la mitad de una hora de simulación
                    Thread.sleep(oneHour / 2);
                }
                // La segunda parte del día traba para actualizar 1 vez el contador
                // Porque se actualiza 1 vez por día el dayCounter
                setEstadoActual("Trabajando");
                Thread.sleep(oneHour * 8);

                // Culminado el día cobra su salario
                this.pagar();

                this.getSemaforo().acquire();

                this.actualizarDiasParaEntrega();
                this.pasoDia();

                this.getSemaforo().release();

            } catch (InterruptedException ex) {
                Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void actualizarDiasParaEntrega() {
        // Lógica para actualizar el contador de días restantes
        Empresa empresa = Helpers.getEmpresa(this.getEmpresa());
        int company = 0;
        if (empresa.getNombre() == "HP") {
            company = 1;
            if (company == 0) {
                if (this.getApp().getEmpresaMSI().getDiasParaEntrega() != 0) {
                    this.getApp().getEmpresaMSI().restarDiasParaEntrega();
                }
            } else {
                if (this.getApp().getEmpresaHP().getDiasParaEntrega() != 0) {
                    this.getApp().getEmpresaHP().restarDiasParaEntrega();
                }
            }
        }
    }

    private void pasoDia() {
        Empresa empresa = Helpers.getEmpresa(this.getEmpresa());
        int company = 0;
        if (empresa.getNombre() == "HP") {
            company = 1;
        }
        if (company == 0) {
            this.getApp().getEmpresaMSI().setDias(this.getApp().getEmpresaMSI().getDias());
        } else {
            this.getApp().getEmpresaHP().setDias(this.getApp().getEmpresaHP().getDias()+1);
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
     * @return the strikes
     */
    public int getStrikes() {
        return strikes;
    }

    /**
     * @param strikes the strikes to set
     */
    public void setStrikes(int strikes) {
        this.strikes = strikes;
    }

    public void addStrike() {
        this.strikes++;
    }

    public void resetStrikes() {
        this.strikes = 0;
    }
}
