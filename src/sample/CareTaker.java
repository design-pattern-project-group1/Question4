package sample;

import java.util.ArrayList;
import java.util.List;

public class CareTaker
{
    private List<Memento> memento_list = new ArrayList<Memento>();

    public void add(Memento text)
    {
        memento_list.add(text);
    }

    public Memento get(int index)
    {
        return memento_list.get(index);
    }
}
