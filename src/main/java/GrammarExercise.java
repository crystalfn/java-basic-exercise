import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class GrammarExercise {
    public static void main(String[] args) {
        //需要从命令行读入
        Scanner scanner = new Scanner(System.in);
        String firstWordList = scanner.nextLine();
        String secondWordList = scanner.nextLine();

        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);
        //按要求输出到命令行
        System.out.println(result);

    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        //在这编写实现代码
        final String[] firstWords = firstWordList.toUpperCase().split(",");
        final String[] secondWords = secondWordList.toUpperCase().split(",");
        isValidInput(firstWords);
        isValidInput(secondWords);
        final SortedSet<String> commonWords = getCommonWords(firstWords, secondWords);
        return new ArrayList<>(commonWords);
    }

    private static void isValidInput(String[] words) {
        Arrays.stream(words).forEach(word -> {
            if (word.equals("") || !word.matches("^[A-Z]+$")) {
                throw new RuntimeException("Invalid input!");
            }
        });
    }

    private static SortedSet<String> getCommonWords(String[] firstWords, String[] secondWords) {
        final Set<String> secondWordsSet = Arrays.stream(secondWords).collect(Collectors.toSet());
        final SortedSet<String> commonWords = new TreeSet<>();
        for (String firstWord : firstWords) {
            if (secondWordsSet.contains(firstWord)) {
                commonWords.add(getWordWithSpace(firstWord));
            }
        }
        return commonWords;
    }

    private static String getWordWithSpace(String commonWord) {
        StringBuilder wordWithSpace = new StringBuilder();
        Arrays.stream(commonWord.split("")).forEach(word -> wordWithSpace.append(word).append(" "));
        return wordWithSpace.toString().trim();
    }
}
