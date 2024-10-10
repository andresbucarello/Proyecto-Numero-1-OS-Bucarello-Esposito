/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author andres
 */
public class ImportantConstants {

    public final static String[] companies = {
        "MSI",
        "HP"
    };

    public final static String[] workesType = {
        "Productor PB",
        "Productor CPU",
        "Productor RAM",
        "Productor FA",
        "Productor TG",
        "Ensamblador",
        "Project Manager",
        "Director"
    };

    public final static int[] hourlyWages = {
        20,
        26,
        40,
        16,
        34,
        50,
        40,
        60
    };

    public final static int[][][] productionTimes = {
        {{1, 4}, {1, 4}, {1, 1}, {5, 1}, {1, 2}, {1, 2}},
        {{1, 3}, {1, 3}, {2, 1}, {3, 1}, {1, 3}, {1, 2}}
    };

    public final static int[][] composicionDeComputadoras = {
        {2, 3, 4, 6, 5},
        {1, 1, 2, 4, 3}
    };

    public final static int[] frecuenciaTG = {6, 2};

    public final static int[][] ingresosPorComputadora = {
        {180000, 250000},
        {90000, 140000}
    };

}
