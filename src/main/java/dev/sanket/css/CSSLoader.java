package dev.sanket.css;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSSLoader
{
    public static String load(String cssFile)
    {
        StringBuilder css = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(cssFile)))
        {
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                css.append(line);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return css.toString();
    }
}
