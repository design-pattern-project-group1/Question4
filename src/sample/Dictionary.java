package sample;

import java.io.*;

public class Dictionary
{
    static File file;
    static String[] dictionary_words;
    static int num_words = 0;
    private static final Dictionary INSTANCE = new Dictionary();

    private Dictionary()
    {
        file = new File("/Users/eandualem/Desktop/DP Project/TextEditor/src/sample/words.json");
        BufferedReader br = null;
        String st;

        try
        {
            br = new BufferedReader(new FileReader(file));
            while ((st = br.readLine()) != null)
            {
                dictionary_words = st.split("\",\"");
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static Dictionary getInstance()
    {
        return INSTANCE;
    }


    public static Boolean check(String str)
    {
        for (String s: dictionary_words)
        {
            if( s.equals(str.toLowerCase())) return false;
        }

        return true;
    }
}
