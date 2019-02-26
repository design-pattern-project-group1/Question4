package sample;

import java.io.IOException;

public class Word
{
    private String string;      // The word

    private boolean is_typo;    // Spelling

    public Word(String string) throws IOException
    {
        this.string = string;
        this.is_typo = Dictionary.check(this.string);
    }

    public String getString()
    {
        return string;
    }
    public boolean is_typo()
    {
        return is_typo;
    }

}
