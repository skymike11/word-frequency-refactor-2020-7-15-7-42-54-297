import java.util.*;

import java.util.stream.Collectors;

import static java.util.stream.Collectors.reducing;

public class WordFrequencyGame {
    public String getResult(String sentence) {

        Map<String, Integer> wordMap = getWordMapBySentence(sentence);

        return formatString(wordMap);
    }

    private String formatString(Map<String, Integer> wordMap) {
        List<Input> list = wordMap.entrySet().stream().sorted((entry1, entry2) -> entry2.getValue() - entry1.getValue())
                .map(word -> new Input(word.getKey(), word.getValue())).collect(Collectors.toList());

        return list.stream().map(word -> word.getValue() + " " + word.getWordCount()).collect(Collectors.joining("\n"));
    }

    private Map<String, Integer> getWordMapBySentence(String sentence) {
        String[] words = sentence.split("\\s+");

        return Arrays.stream(words).collect(Collectors.groupingBy(item -> item, reducing(0, e -> 1, Integer::sum)));
    }

}
