package sample;

public class Memento
{
    private Word last_changed_word;
    private Boolean inserted;

    public Memento(Word last_changed_word, Boolean inserted)
    {
        this.last_changed_word = last_changed_word;
        this.inserted = inserted;
    }

    Word get_word()
    {
        return this.last_changed_word;
    }

    public void removed()
    {
        this.inserted = false;
    }

    public void inserted()
    {
        this.inserted = true;
    }

    public Boolean was_inserted()
    {
        return inserted;
    }
}
