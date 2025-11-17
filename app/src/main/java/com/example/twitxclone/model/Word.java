package com.example.twitxclone.model;

public class Word {

    public static final String WORD_KEY  = "WORDV";

    String word;

    public Word() {}

    public Word(String word) { this.word = word; }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
       this.word= word;

//        if (word.length()== 5){
//            String lower = word.toLowerCase();
//            lower. }
    }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                '}';
    }
}
