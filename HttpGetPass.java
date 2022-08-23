package ejercicioclase10;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class HttpGetPass {
    private static final String REGEX_EMAIL = "[\\w.]+@[a-z]+.[a-z]+";
    private static final String REGEX_PASSWORD = "&[a-zA-Z]{1,10}=([\\w*\"#$])+";

    // 1. leer un archivo txt con rutas que contienen en su interior emails con su contraseña
    public static byte[] readPath(String path) throws IOException {
        InputStream fileIn = new FileInputStream(path);
        return fileIn.readAllBytes();
    }

    // 2. convertir los datos byte a un archivo string
    public static String[] getFileString(byte[] file) {
        String dataString = "";
        for (byte data : file ) {
            dataString += (char) data;
        }
        return dataString.split("\\R");
    }

    // 3. extraer los correos y las contraseñas
    // extrae los correos
    public static Vector<String> getEmail(String[] data) {
        Pattern pattern = Pattern.compile(REGEX_EMAIL);
        Vector<String> emails = new Vector<>();
        for (String d : data) {
            Matcher matcher = pattern.matcher(d);
            if (matcher.find()) {
                emails.add(matcher.group());
            }
        }
        return emails;
    }
    // extrae las contraseñas
    public static Vector<String> getPass(String[] data) {
        Pattern pattern = Pattern.compile(REGEX_PASSWORD);
        Vector<String> pass = new Vector<>();
        for (String p : data) {
            Matcher matcher = pattern.matcher(p);
            if (matcher.find()) {
                String[] password = matcher.group().split("=");
                pass.add(password[1]);
            }
        }
        return pass;
    }

    // 4. Guardar los datos extraidos en un HashMap, cuyo codigo será el correo y el valor la contraseña.
    public static HashMap<String, String> emailsAndPasswords(Vector<String> emails, Vector<String> passwords) {
        HashMap<String, String> emailsAndPasswords = new HashMap<>();
        for (int i = 0; i < emails.size(); i++) {
            emailsAndPasswords.put(emails.get(i), passwords.get(i));
        }
        return emailsAndPasswords;
    }

    // 5. Crear un nuevo archivo con los datos almacenados en el HashMap
    public static void  fileOut(String pathOut, Vector<String> emails, Vector<String> passwords) throws IOException {
        File file = new File(pathOut);
        HashMap<String, String> emailsAndPasswords = emailsAndPasswords(emails, passwords);

        if (!file.exists()) {
            file.createNewFile();
        }
        // Hacemos una copia del archivo.
        PrintStream fileCopy = new PrintStream(file);
        for (Map.Entry<String, String> data : emailsAndPasswords.entrySet()){
            fileCopy.println("[*] " + data.getKey() + " => " + data.getValue());
        }
    }
    // 5. Leer el nuevo archivo.
    public static void readData(String pathFile) throws IOException {
        InputStream getPath = new FileInputStream(pathFile);
        byte[] data = getPath.readAllBytes();

        System.out.println("+===================================================+");
        System.out.println("|=======  Emails y contrasenas en las rutas  =======|");
        System.out.println("+===================================================+");
        for (byte d : data) {
            System.out.print((char) d);
        }
        System.out.println("+===================================================+");
    }
}
