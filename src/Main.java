public class Main {
    public static void main(String[] args) {
        String[] words = WordleFileManager.loadWords("data/palabras.txt");
        if (words.length == 0) {
            System.out.println("Error: No se encontraron palabras en el archivo. Asegúrate de que 'data/palabras.txt' existe y contiene palabras.");
            return;
        }
        WordleGame game = new WordleGame(words);
        game.start();
    }
}