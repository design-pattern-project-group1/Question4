package sample;

public class TextAdapter implements Text
{
    String adapted_text;

    @Override
    public String get_text(WordText text)
    {
        adapted_text = "";
        for ( Word w: text.get_words() )
        {
            adapted_text += w.getString() + " ";
        }

        return adapted_text;
    }
}
