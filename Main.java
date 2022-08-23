package ejercicioclase10;

import java.io.IOException;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        // Programa para leer un archivo de texto con varias rutas https tipo get que lee si tiene emails y passwords
        // y los entrega en otro archivo.
        System.out.println("\nPrograma para leer un archivo de texto con varias rutas HTTP tipo GET que lee si tiene EMAILS y PASSWORDS " +
                "y los entrega en otro archivo.");
        String pathAnalytics = "C:\\Users\\user\\Desktop\\rutasGet.txt";
        String pathOutFiles = "C:\\Users\\user\\Desktop\\things.txt";
        try {
            byte[] file = HttpGetPass.readPath(pathAnalytics);
            String[] dataString = HttpGetPass.getFileString(file);
            Vector<String> emails = HttpGetPass.getEmail(dataString);
            Vector<String> passwords = HttpGetPass.getPass(dataString);
            HttpGetPass.fileOut(pathOutFiles, emails, passwords);
            HttpGetPass.readData(pathOutFiles);

        } catch (IOException e) {
            System.out.println("A ocurrido un error por: " + e.getMessage());
        }
    }
}
