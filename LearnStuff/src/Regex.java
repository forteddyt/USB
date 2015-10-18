public class Regex {

    public static boolean hasYeses(String input) {
        // Any combination of yes inputs
        input.toUpperCase();
        return input.matches("[Yy][(es)(ES)\\s(\\.*)(\\?*)(\\!*)]*");
    }

    public static boolean isProperWord(String input) {
        // first letter is capitalized and only a single word
        return input.matches("[A-Z][^\\s|A-Z|\\W]+");
    }

    public static boolean isScrabble(String input) {
        // has 1-7 letters only
        return input.matches("[A-Za-z]{1,7}");
    }

    public static boolean isMoneyAmount(String input) {
        // is in the format of $_____.##
        return input.matches("\\$\\d+\\.\\d\\d");
    }

    public static boolean isPalindrome(String input) {
        // Checks if input is a palindrome for 8 or 9 letter words
        input = input.toLowerCase();
        return input.matches("(.)(.)(.)(.)(.)?\\4\\3\\2\\1");
    }

    public static void main(String args[]) {
        System.out.println("");
    }
}