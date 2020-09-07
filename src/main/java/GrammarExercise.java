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
        final SortedSet<String> commonWords = getCommonWords(firstWords, secondWords);
        final SortedSet<String> commonWordsWithSpace = getCommonWordsWithSpace(commonWords);
        return new ArrayList<>(commonWordsWithSpace);
    }

    private static SortedSet<String> getCommonWords(String[] firstWords, String[] secondWords) {
        final Set<String> firstWordsSet = Arrays.stream(firstWords).collect(Collectors.toSet());
        final SortedSet<String> commonWords = new TreeSet<>();
        for (String secondWord : secondWords) {
            if (firstWordsSet.contains(secondWord)) {
                commonWords.add(secondWord);
            }
        }
        return commonWords;
    }

    private static SortedSet<String> getCommonWordsWithSpace(SortedSet<String> commonWords) {
        SortedSet<String> commonWordsWithSpace = new TreeSet<>();
        for (String commonWord : commonWords) {
            final String[] commonWordSplit = commonWord.split("");
            commonWordsWithSpace.add(getWordWithSpace(commonWordSplit));
        }
        return commonWordsWithSpace;
    }

    private static String getWordWithSpace(String[] commonWord) {
        StringBuilder wordWithSpace = new StringBuilder();
        Arrays.stream(commonWord).forEach((word) -> wordWithSpace.append(word).append(" "));
        return wordWithSpace.toString().trim();
    }
}
