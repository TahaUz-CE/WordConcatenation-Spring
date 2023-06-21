package com.exampleYazlab1.demoYazlab1;
import java.util.Arrays;
public class WordConcatenationLight {
    public  String concatenateTexts(String[] texts) {
        String[][] words = new String[texts.length][];

        // Split each text into words
        for (int i = 0; i < texts.length; i++) {
            words[i] = texts[i].split(" ");
        }

        // Find the common word
        String commonWord = "";
        for (int i = 0; i < words[0].length; i++) {
            boolean isCommon = true;
            for (int j = 1; j < words.length; j++) {
                if (!Arrays.asList(words[j]).contains(words[0][i])) {
                    isCommon = false;
                    break;
                }
            }
            if (isCommon) {
                commonWord = words[0][i];
            }
        }
        if(!commonWord.equals("")){
            System.out.println("commonWord: " + commonWord);
        }

        // Concatenate the texts
        StringBuilder concatenatedText = new StringBuilder();

        boolean found = true;

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length; j++) {
                if(found){
                    concatenatedText.append(" " + words[i][j]);
                }

                if (words[i][j].equals(commonWord)) {
                    if(!found){
                        found = true;
                    }else{
                        j = words[i].length - 1;
                        found = false;
                    }
                }
            }
            if(found){
                found = false;
            }
        }
        if(commonWord.equals("")){
            return words.length + " cumlede ortak kelime bulunamadigi icin birlestirilemez!";
        }
        return concatenatedText.toString();
    }
}
