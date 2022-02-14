package com.baumgart;

//ITDEV-110
//Assignment 10
//Brent Baumgart

public class Word {

    //holds a word
    private String word;
    //holds encrypted word
    private String[] encryptedWord;

    public Word(String word) {
        this.word = word;
        this.encryptedWord = new String[this.word.length()];

    }

    public void decryptLetter(char letter) {
        for (int i = 0; i < word.length(); i++) {

            if (word.charAt(i) == letter) {
                encryptedWord[i] = String.valueOf(letter);
            }

        }
    }

    public boolean isDecrypted() {
        String toStr = arrayToString();
        return word.equals(toStr);
    }

    public String getWord() {
        return this.word;
    }

    public String getEncryptedWord() {
        return arrayToString();
    }


    public void reset() {
        encryptWord();
    }

    private void encryptWord() {
        //encrypt word
        for (int i = 0; i < this.word.length(); i++) {
            encryptedWord[i] = "_ ";
        }
    }

    //turns the String array into a String
    private String arrayToString() {
        StringBuilder holder = new StringBuilder();

        for (String letter : encryptedWord) {
            holder.append(letter);
        }

        return holder.toString();
    }

}
