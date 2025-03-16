import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WordleFileManager {
    public static String[] loadWords(String filename) {
        File file = new File(filename);
        System.out.println("Intentando cargar palabras desde: " + file.getAbsolutePath());

        List<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        if (words.isEmpty()) {
            System.out.println("Advertencia: No se encontraron palabras en '" + filename + "'.");
        }

        return words.toArray(new String[0]);
    }



    public static void saveGameHistory(List<String> history) {
        if (history.isEmpty()) {
            System.out.println("No hay intentos para guardar.");
            return;
        }

        File file = new File("data/partidas.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write("Nueva partida:\n");
            for (String attempt : history) {
                bw.write(attempt + "\n");
            }
            bw.write("-----------------------------\n");
            System.out.println("Partida guardada en: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error al guardar la partida: " + e.getMessage());
        }
    }
}
