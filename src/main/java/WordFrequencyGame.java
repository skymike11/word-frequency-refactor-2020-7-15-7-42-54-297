import java.util.*;
import java.io.CharArrayWriter;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public String getResult(String sentence) {

        Map<String, Integer> wordMap = getWordMapBySentence(sentence);

        return formatString(wordMap);
    }

    private String formatString(Map<String, Integer> wordMap) {
        List<Input> list = wordMap.entrySet().stream().sorted((entry1, entry2)-> entry2.getValue() - entry1.getValue())
                .map(word -> new Input(word.getKey(), word.getValue())).collect(Collectors.toList());

        return list.stream().map(word -> word.getValue() + " " + word.getWordCount()).collect(Collectors.joining("\n"));
    }

    private Map<String, Integer> getWordMapBySentence(String sentence) {
        String[] words = sentence.split("\\s+");

        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            if (wordMap.containsKey(word)) {
                wordMap.put(word, wordMap.get(word) + 1);
            } else {
                wordMap.put(word, 1);
            }
        }
        return wordMap;
    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            } else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }
}
