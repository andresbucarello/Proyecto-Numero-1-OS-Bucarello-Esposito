/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Clases.Almacen;
import Clases.Director;
import Clases.Empresa;
import Clases.ProjectManager;
import Clases.Trabajador;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.Semaphore;

/**
 *
 * @author andresbucarello
 */
public class Helpers {

    public void deleteWorker(int nroEmpresa, int workerType) {
        Empresa empresa = nroEmpresa == 0 ? App.getInstance().getEmpresaMSI() : App.getInstance().getEmpresaHP();

        // Verifica si hay empleados para eliminar
        if (empresa.getCantidadTrabajadoresActuales() > 1) {
            Trabajador[] employees = getTrabajadoresDeArea(empresa, workerType);

            if (employees != null) {
                // Buscar el último empleado no nulo
                for (int i = employees.length - 1; i >= 0; i--) {
                    if (employees[i] != null) {
                        // Interrumpe el hilo del empleado
                        employees[i].interrupt();

                        // Elimina el empleado del arreglo
                        employees[i] = null;

                        // Actualiza la cantidad de empleados
                        empresa.setCantidadTrabajadoresActuales(empresa.getCantidadTrabajadoresActuales() - 1);
                        break;
                    }
                }
            }
        } 
    }

    private Trabajador[] getTrabajadoresDeArea(Empresa empresa, int workerType) {
        switch (workerType) {
            case 0:
                return empresa.getProductoresPB();
            case 1:
                return empresa.getProductoresCPU();
            case 2:
                return empresa.getProductoresRAM();
            case 3:
                return empresa.getProductoresFA();
            case 4:
                return empresa.getProductoresTG();
            case 5:
                return empresa.getEnsambladores();
            default:
                return null;
        }
    }

    public void sumarTrabajador(int nroEmpresa, int tipoEmpleado) {

        Empresa empresa = nroEmpresa == 0 ? App.getInstance().getEmpresaMSI() : App.getInstance().getEmpresaHP();

        // Se verifica si la cantidad actual de empleados es menor que la cantidad
        // máxima permitida
        if (empresa.getCantidadTrabajadoresActuales() < empresa.getCantidadTrabajadoresMaximos()) {
            Trabajador[] trabajadoresDelArea = getTrabajadoresDeArea(empresa, tipoEmpleado);

            // Se crea nuevo empleado
            int cantidadDeTrabajosPorCiclo = ImportantConstants.productionTimes[nroEmpresa][tipoEmpleado][1];
            int cantidadDeDiasxCiclo = ImportantConstants.productionTimes[nroEmpresa][tipoEmpleado][0];
            int salario = ImportantConstants.hourlyWages[tipoEmpleado];
            Trabajador trabajador = new Trabajador(empresa.getNombre(), tipoEmpleado, cantidadDeDiasxCiclo, cantidadDeTrabajosPorCiclo, salario, empresa.getAlmacen(), empresa.getSemaforo());

            // Se inicia el hilo del nuevo empleado
            trabajador.start();

            // Se buscar la primera posición vacía en el arreglo y añadir allí el nuevo
            // empleado
            for (int i = 0; i < trabajadoresDelArea.length; i++) {
                if (trabajadoresDelArea[i] == null) {
                    trabajadoresDelArea[i] = trabajador;
                    empresa.setCantidadTrabajadoresActuales(empresa.getCantidadTrabajadoresActuales() + 1);
                    // Actualizar la cantidad de empleados
                    break;
                }
            }
        } 
    }

    public static Empresa getEmpresa(String empresa) {
        return "MSI".equals(empresa) ? App.getInstance().getEmpresaMSI() : App.getInstance().getEmpresaHP();
    }

    public static void calcularIngresos(String nombreEmpresa) {
        Empresa empresa = getEmpresa(nombreEmpresa);
        int nroEmpresa = 0;
        if (nombreEmpresa == "HP") {
            nroEmpresa = 1;
        }

        int earning = (empresa.getComputadorasNormales() * ImportantConstants.ingresosPorComputadora[nroEmpresa][0]) + (empresa.getComputadorasTG() * ImportantConstants.ingresosPorComputadora[nroEmpresa][1]);
        empresa.setIngresos(empresa.getIngresos()+earning);

    }

    public static void calcularGanancias(String nombreEmpresa) {
        Empresa empresa = getEmpresa(nombreEmpresa);
        int profit = empresa.getGanancias() - empresa.getCostos();
        empresa.setGanancias(profit);
    }

    public static void cargarParametros() {
        String selectedPath = "test//params.txt";
        File selectedFile = new File(selectedPath);
        String datosDelArchivo = leer(selectedFile);

        int[] params = getParametrosGenerales(datosDelArchivo);

        App.setDuracionDelDia(params[0]);
        App.setDiasParaLaEntrega(params[1]);

        App app = App.getInstance();

        Empresa empresaHP = crearEmpresa("HP", params);
        Empresa empresaMSI = crearEmpresa("MSI", params);

        app.setEmpresaHP(empresaHP);
        app.setEmpresaMSI(empresaMSI);
    }

    public static Empresa crearEmpresa(String name, int[] params) {
        String nombre = name;
        Trabajador[][] trabajadores = new Trabajador[6][];
        Semaphore semaforo = new Semaphore(1);
        Almacen almacen = new Almacen(25, 20, 55, 35, 10);

        int numeroEmpresa = 0;
        int index = 2;
        int cantidadTrabajadoresMaximos = 20;
        if (nombre == "HP") {
            index = 8;
            numeroEmpresa = 1;
            cantidadTrabajadoresMaximos = 16;
        }
       

        for (int tipoEmpleado = 0; tipoEmpleado <= 5; tipoEmpleado++) {
            Trabajador[] empleadosPorArea = new Trabajador[cantidadTrabajadoresMaximos];
            
            for (int j = 0; j < params[index]; j++) {
                int cantidadDeTrabajosPorCiclo = ImportantConstants.productionTimes[numeroEmpresa][tipoEmpleado][0];
                int cantidadDeDiasxCiclo = ImportantConstants.productionTimes[numeroEmpresa][tipoEmpleado][1];
                int salario = ImportantConstants.hourlyWages[tipoEmpleado];
                empleadosPorArea[j] = new Trabajador(nombre, tipoEmpleado, cantidadDeDiasxCiclo, cantidadDeTrabajosPorCiclo, salario, almacen, semaforo);
                
            }
            index++;

            trabajadores[tipoEmpleado] = empleadosPorArea;
        }
        ProjectManager projectManager = new ProjectManager(nombre, 6, 1, 1, ImportantConstants.hourlyWages[6], almacen, semaforo);
        Director director = new Director(nombre, 7, 2, 1, ImportantConstants.hourlyWages[7], almacen, semaforo);
        Empresa empresa = new Empresa(nombre, cantidadTrabajadoresMaximos, trabajadores[0], trabajadores[1], trabajadores[2], trabajadores[3], trabajadores[4], trabajadores[5], projectManager, director, almacen, semaforo);

        return empresa;
    }

    public static int[] getParametrosGenerales(String datosDelArchivo) {
        int startIndex = datosDelArchivo.indexOf("[Parametros Generales]");
        int endIndex = datosDelArchivo.length();

        String cnSection = datosDelArchivo.substring(startIndex, endIndex);
        String[] lineas = cnSection.split("\n");

        int[] arrayParametrosGenerales = new int[14];
        int indexTemporal = 0;

        // Se Itera sobre las líneas, extrayendo los valores enteros
        for (String linea : lineas) {
            if (linea.contains("=")) {
                String[] parts = linea.split("=");
                if (parts.length == 2) {
                    try {
                        arrayParametrosGenerales[indexTemporal++] = Integer.parseInt(parts[1].trim());
                    } catch (NumberFormatException e) {
                        System.out.println("El valor no es entero");
                    }
                }
            }
        }
        return arrayParametrosGenerales;
    }

    public static String leer(File file) {
        String line;
        String data = "";

        try {
            if (!file.exists()) {
                file.createNewFile();

            } else {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);

                while ((line = br.readLine()) != null) {
                    if (!(line.isEmpty())) {
                        data += line + "\n";
                    }
                }
                br.close();
            }
            return data;
        } catch (Exception e) {
        }
        return data;
    }

    public static void write() {
        String selectedPath = "test//params.txt";
        File file = new File(selectedPath);

        String data = getActualParams();
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(file);
            fileWriter.write(data);
            fileWriter.close();
        } catch (Exception e) {

        }
    }

    public static String getActualParams() {
        App app = App.getInstance();
        String data = "[Parametros Generales]\n";
        int dayDuration = App.getDuracionDelDia();
        int deadline = App.getDiasParaLaEntrega();

        data += "DayDuration=" + dayDuration + "\n" + "Deadline=" + deadline + "\n\n";

        data += "[Empresa MSI]\n";
        Empresa empresaMSI = app.getEmpresaMSI();
        int ProductoresPB = empresaMSI.contarNoNulos(empresaMSI.getProductoresPB());
        int ProductoresCPU = empresaMSI.contarNoNulos(empresaMSI.getProductoresCPU());
        int ProductoresRAM = empresaMSI.contarNoNulos(empresaMSI.getProductoresRAM());
        int ProductoresFA = empresaMSI.contarNoNulos(empresaMSI.getProductoresFA());
        int ProductoresTG = empresaMSI.contarNoNulos(empresaMSI.getProductoresTG());
        int Ensambladores = empresaMSI.contarNoNulos(empresaMSI.getEnsambladores());

        data += "ProductoresPB=" + ProductoresPB + "\n" + "ProductoresCPU=" + ProductoresCPU + "\n" + "ProductoresRAM="
                + ProductoresRAM + "\n" + "ProductoresFA=" + ProductoresFA + "\n" + "ProductoresTG="
                + ProductoresTG + "\n" + "Ensambladores=" + Ensambladores + "\n\n";

        data += "[Empresa HP]\n";
        Empresa empresaHP = app.getEmpresaHP();
        ProductoresPB = empresaHP.contarNoNulos(empresaHP.getProductoresPB());
        ProductoresCPU = empresaHP.contarNoNulos(empresaHP.getProductoresCPU());
        ProductoresRAM = empresaHP.contarNoNulos(empresaHP.getProductoresRAM());
        ProductoresFA = empresaHP.contarNoNulos(empresaHP.getProductoresFA());
        ProductoresTG = empresaHP.contarNoNulos(empresaHP.getProductoresTG());
        Ensambladores = empresaHP.contarNoNulos(empresaHP.getEnsambladores());

        data += "ProductoresPB=" + ProductoresPB + "\n" + "ProductoresCPU=" + ProductoresCPU + "\n" + "ProductoresRAM="
                + ProductoresRAM + "\n" + "ProductoresFA=" + ProductoresFA + "\n" + "ProductoresTG="
                + ProductoresTG + "\n" + "Ensambladores=" + Ensambladores + "\n\n";

        return data;
    }

}
