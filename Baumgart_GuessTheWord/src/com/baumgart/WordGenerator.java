package com.baumgart;

//ITDEV-110
//Assignment 10
//Brent Baumgart

import java.util.*;

public class WordGenerator {

    private final List<Word> words;
    private final Random random;

    public WordGenerator() {
        this.words = new ArrayList<>();
        this.random = new Random();

        init();
    }

    private void init() {
        words.add(new Word("cat"));
        words.add(new Word("turkey"));
        words.add(new Word("phone"));
        words.add(new Word("friday"));
        words.add(new Word("ordinary"));
        words.add(new Word("outside"));
        words.add(new Word("paralyzed"));
        words.add(new Word("development"));
        words.add(new Word("java"));
        words.add(new Word("python"));
        words.add(new Word("javascript"));
        words.add(new Word("dictate"));
        words.add(new Word("negligence"));
    }

    public Word getRandomWord() {
        //gets random word and resets the encryption in case word has already been chosen
        Word word = words.get(random.nextInt(words.size()));
        word.reset();
        return word;
    }


}
