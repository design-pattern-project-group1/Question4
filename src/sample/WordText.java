package sample;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class WordText // Originator
{
    private ArrayList<Word> words = new ArrayList<>();
    private Path path;

    public void setPath(Path path)
    {
        this.path = path;
    }

    Memento add_word(Word word)
    {
        this.words.add(word);
        return saveMemento(word, true);
    }

    public void add_words() throws IOException
    {
        String[] one_line_strings;
        List<String> all_text = Files.readAllLines(this.path, StandardCharsets.UTF_8);

        for (String str: all_text)
        {
            one_line_strings = str.split(" ");
            for (String s: one_line_strings )
            {
                words.add(new Word(s));
            }
        }
    }

    ArrayList<Word> get_words()
    {
        return words;
    }

    Memento saveMemento(Word last_changed_word, Boolean inserted)
    {
        return new Memento(last_changed_word, inserted);
    }

    void restoreMemento(Memento memento)
    {
        if (memento.was_inserted())
        {
            this.words.remove(memento.get_word());
            memento.removed();
        }
        else
            {
                add_word(memento.get_word());
                memento.inserted();
            }
    }

    public Path get_path()
    {
        return path;
    }
}
