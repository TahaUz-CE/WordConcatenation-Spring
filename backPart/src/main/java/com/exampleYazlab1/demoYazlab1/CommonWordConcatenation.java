package com.exampleYazlab1.demoYazlab1;
import java.util.Arrays;
import java.util.*;

public class CommonWordConcatenation {
    private Data text;

    public CommonWordConcatenation(Data text) {
        this.text = text;
    }

    ArrayList<String> textList = new ArrayList<String>();

    public void extractSentences() {
        String textStr = text.getText1();
        String[] sentences = textStr.split("-");

        for (String sentence : sentences) {
            textList.add(sentence.trim());
        }
    }

    private String compareWords(String word1, String word2) {
        if (word1.contains(word2))
            return word1;
        else if (word2.contains(word1))
            return word2;
        else return "";
    }

    public String finalControl() {
        String sentence = createMergedSentence();
        ArrayList<String> wordsList = new ArrayList<String>();
        System.out.println("finalControl Before:"+sentence);
        String[] wordsArray = sentence.split(" ");

        for (int i = 0; i < wordsArray.length; i++) {
            wordsList.add(wordsArray[i]);
        }
        for (int i = 0; i < wordsList.size() - 1; i++) {
            String extension = compareWords(wordsList.get(i),wordsList.get(i+1));
            if (extension.equals(wordsList.get(i))) {
                wordsList.remove(i+1);
            } else if (extension.equals(wordsList.get(i+1))) {
                wordsList.remove(i);
            }
        }
        return wordsList.toString().replaceAll("\\[|\\]", "").replaceAll(",\\s", " ");
    }

    public String createMergedSentence() {

        StringBuilder mergedSentence = new StringBuilder();

        for (int i = 0; i < textList.size(); i++) {
            String sentence = textList.get(i);

            if (i == 0) {
                // First sentence is included entirely
                mergedSentence.append(sentence);
            } else {
                // Get the common words from the previous sentence and the current sentence
                String[] prevWords = textList.get(i - 1).split(" ");
                String[] currWords = sentence.split(" ");
                boolean found = false;
                List<String> commonWords = new ArrayList<>();
                for (int j = 0; j < prevWords.length; j++) {
                    for (int k = 0; k < currWords.length; k++) {
                        if (prevWords[j].equalsIgnoreCase(currWords[k])) {
                            commonWords.add(prevWords[j].replaceAll("-", ""));
                            found = true;
                            break;
                        }
                    }
                }
                if(found){
                    // Append the current sentence, skipping common words that have already been included
                    String[] words = sentence.split(" ");
                    for (int j = 0; j < words.length; j++) {
                        if (!commonWords.contains(words[j].replaceAll("-", ""))) {
                            mergedSentence.append(" ").append(words[j]);
                        }
                    }
                }

            }
        }

        return mergedSentence.toString().trim();
    }
}

