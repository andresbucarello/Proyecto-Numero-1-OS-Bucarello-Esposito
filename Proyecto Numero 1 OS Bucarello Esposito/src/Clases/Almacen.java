/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Main.ImportantConstants;
import java.util.concurrent.Semaphore;

/**
 *
 * @author andresbucarello
 */
public class Almacen {
    
    private int[] componentes;
    private int[] capacidadMaxima;
    private int computadorasNormales;
    private int computadorasTG;
   
    public Almacen(int cantidadAlmacenPB,int cantidadAlmacenCPU,int cantidadAlmacenRAM,int cantidadAlmacenFA,int cantidadAlmacenTG){
        this.capacidadMaxima=new int[]{
            cantidadAlmacenPB,
            cantidadAlmacenCPU,
            cantidadAlmacenRAM,
            cantidadAlmacenFA,
            cantidadAlmacenTG
        };
        this.componentes=new int[6];
    }
    
    public void almacenarTrabajo(int tipoEmpleado, int cantidadDeTrabajoGenerado) {
        if (tipoEmpleado >= 0 && tipoEmpleado <= 5) {
            if (tipoEmpleado == 5) {
                this.getComponentes()[tipoEmpleado] += cantidadDeTrabajoGenerado;
            } else if (this.getComponentes()[tipoEmpleado] < this.getCapacidadMaxima()[tipoEmpleado]) {
                if (this.getComponentes()[tipoEmpleado] + cantidadDeTrabajoGenerado <= this.getCapacidadMaxima()[tipoEmpleado]) {
                    this.getComponentes()[tipoEmpleado] += cantidadDeTrabajoGenerado;
                } else {
                    this.getComponentes()[tipoEmpleado] = this.getCapacidadMaxima()[tipoEmpleado];
                }
            }
        }
    }
    
    @Override
    public String toString() {
        String str = "Informacion Almacen\n\n";
        for (int i = 0; i <= 5; i++) {
            str += "-" + ImportantConstants.workesType[i] + " almacenamiento: " + this.componentes[i] + "\n";
            if (i != 5) {
                str += "-" + ImportantConstants.workesType[i] + " almacenamiento maximo: " + this.capacidadMaxima[i] + "\n";
            }
        }
        return str;
    }
    
    /**
     * @return the componentes
     */
    public int[] getComponentes() {
        return componentes;
    }

    /**
     * @param componentes the componentes to set
     */
    public void setComponentes(int[] componentes) {
        this.componentes = componentes;
    }

    /**
     * @return the capacidadMaxima
     */
    public int[] getCapacidadMaxima() {
        return capacidadMaxima;
    }

    /**
     * @param capacidadMaxima the capacidadMaxima to set
     */
    public void setCapacidadMaxima(int[] capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
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
    
    public void increaseStandarChapters() {
        this.computadorasNormales++;
    }

    public void increasePlotTwistChapters() {
        this.computadorasTG++;
    }
    
    public void resetComputadoras() {
        this.getComponentes()[5] = 0;
    }
}