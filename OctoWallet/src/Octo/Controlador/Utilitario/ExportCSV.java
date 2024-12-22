package Octo.Controlador.Utilitario;


import Octo.Modelo.Entidad.Activo;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Locale;

public class ExportCSV {

    //El metodo exportToCSV recibe una lista de activos y exporta los datos de estos a un archivo CSV en el directorio de Descargas.
    public static void exportToCSV(List<Activo> acts) throws IOException {
        Path downloadsPath = Paths.get(System.getProperty("user.home"), getDownloadsFolderName());
        Path exportFilePath = downloadsPath.resolve("misActivos.csv");
        File exportFile = exportFilePath.toFile();
        if(exportFile.exists()){
            Files.delete(exportFilePath);
        }
        List<List<String>> filas = getFilas2(acts);
        FileWriter csvWriter = new FileWriter(downloadsPath+"/misActivos.csv"); //
        csvWriter.append("Tipo");
        csvWriter.append(",");
        csvWriter.append("Nomenclatura");
        csvWriter.append(",");
        csvWriter.append("Saldo");
        csvWriter.append('\n');
        for (List<String> datos_fila : filas) {
            csvWriter.append(String.join(",", datos_fila));
            csvWriter.append('\n');
        }
        csvWriter.close();
    }
    public static List<List<String>>  getFilas2(List<Activo> activos){
        List<List<String>> filas = new ArrayList<>();
        for (Activo act : activos) {
            filas.add(getFilas(act));
        }
        return filas;
    }
    public static List<String> getFilas(Activo act) {
        List<String> fila = new ArrayList<>();
        fila.add(act.getMoneda().getTipo());
        fila.add(String.valueOf(act.getMoneda().getNomenclatura()));
        fila.add(Double.toString(act.getSaldo()));
        return fila;
    }
    private static String getDownloadsFolderName() {
        String language = Locale.getDefault().getLanguage();
        switch (language) {
            case "es":
                return "Descargas";
            case "fr":
                return "Téléchargements";
            case "en":
                return "Downloads";
            default:
                return "Downloads";
        }
    }
}
