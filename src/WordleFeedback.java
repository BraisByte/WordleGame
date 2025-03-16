public class WordleFeedback {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_GRAY = "\u001B[37m";

    public static String feedBackString(String guess, String secretWord) {
        StringBuilder feedback = new StringBuilder();
        for (int i = 0; i < guess.length(); i++) {
            char letter = guess.charAt(i);
            if (letter == secretWord.charAt(i)) {
                feedback.append(ANSI_GREEN).append(letter).append(ANSI_RESET);
            } else if (secretWord.contains(String.valueOf(letter))) {
                feedback.append(ANSI_YELLOW).append(letter).append(ANSI_RESET);
            } else {
                feedback.append(ANSI_GRAY).append(letter).append(ANSI_RESET);
            }
        }
        return feedback.toString();
    }
}
