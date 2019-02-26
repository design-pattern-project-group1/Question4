package sample;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Model
{

    public Boolean save(WordText text)
    {
        FileWriter out = null;
        TextAdapter textAdapter = new TextAdapter();

        try ( final BufferedWriter writer = Files.newBufferedWriter(text.get_path(), StandardCharsets.UTF_8, StandardOpenOption.CREATE); )
        {
            writer.write(textAdapter.get_text(text));
            writer.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int number_of_typo(WordText text)
    {
        int num_of_typo = 0;
        for (Word w: text.get_words())
            if (w.is_typo()) num_of_typo++;

        return num_of_typo;
    }

    public List<String> words_with_typo(WordText text)
    {
        List<String> wwt = new ArrayList<>();

        for (Word w: text.get_words())
            if (w.is_typo()) wwt.add(w.getString());

        return wwt;
    }

    public void quit()
    {
        System.exit(0);
    }
}
