import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordleGame {
    private static final int MAX_TRIES = 6;
    private static final int WORD_LENGTH = 5;
    private String[] fileWords;
    private String secretWord;
    private int remainingAttempts;
    private List<String> triesHistory;

    public WordleGame(String[] fileWords) {
        this.fileWords = fileWords;
        this.secretWord = selectRandomWord(fileWords);
        this.remainingAttempts = MAX_TRIES;
        this.triesHistory = new ArrayList<>();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido a Wordle!");

        while (remainingAttempts > 0) {
            System.out.println("Intentos restantes: " + remainingAttempts);
            showTriesHistory();

            String userGuess = getUserInput(scanner);
            triesHistory.add(userGuess);

            if (userGuess.equals(secretWord)) {
                System.out.println("¡Felicidades! Has adivinado la palabra: " + secretWord);
                WordleFileManager.saveGameHistory(triesHistory);
                scanner.close();
                return;
            } else {
                System.out.println(WordleFeedback.feedBackString(userGuess, secretWord));
                remainingAttempts--;
            }
        }

        if (remainingAttempts == 0) {
            System.out.println("Se acabaron los intentos. La palabra era: " + secretWord);
            WordleFileManager.saveGameHistory(triesHistory);
        }

        scanner.close();
    }



    private void showTriesHistory() {
        for (String attempt : triesHistory) {
            System.out.println(attempt);
        }
    }

    private String getUserInput(Scanner scanner) {
        String input;
        do {
            System.out.print("Introduce una palabra de " + WORD_LENGTH + " letras: ");
            input = scanner.nextLine().toLowerCase();
            if (input.length() != WORD_LENGTH) {
                System.out.println("Error: La palabra debe tener " + WORD_LENGTH + " letras.");
            }
        } while (input.length() != WORD_LENGTH);
        return input;
    }

    private String selectRandomWord(String[] words) {
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }
}
