package sample;

import java.io.IOException;

public class WordBuilder
{
    private String temp_word = "";

    private Word word;

    public WordBuilder append(Character c)
    {
        this.temp_word += c;
        return this;
    }
    public Word build() throws IOException
    {
        this.word = new Word(this.temp_word);
        return this.word;
    }

    public void remove_last_char()
    {
        String new_temp_word = temp_word;
        temp_word = new_temp_word.substring(0, new_temp_word.length()-1);
    }

    public void clear()
    {
        temp_word = "";
    }
}
