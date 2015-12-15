package dev.sanket.minwindow;

import java.util.HashSet;
import java.util.Set;

public class SourceString
{
    private String source;

    public SourceString(String source)
    {
        this.source = source;
    }

    public String getMinimumWindowFor(String windowCharacters) throws Exception
    {
        if (this.source == null)
        {
            throw new Exception("Source string is null");
        }

        if (windowCharacters == null)
        {
            throw new Exception("Window character string is null");
        }

        Set<Character> uniqueChars = new HashSet<Character>();

        prepareUniqueCharacterSet(windowCharacters, uniqueChars);

        int startIndex = 0;

        startIndex = getNextUniqueCharOccurance(this.source, uniqueChars,
                startIndex);

        int endIndex = startIndex;
        Set<Character> foundChars = new HashSet<Character>();

        String minWindow = null;

        while (endIndex < this.source.length())
        {
            Character currentChar = Character.valueOf(this.source
                    .charAt(endIndex));

            if (uniqueChars.contains(currentChar))
            {
                if (!foundChars.add(currentChar))
                {
                    while (currentChar.equals(Character.valueOf(this.source
                            .charAt(startIndex))) && (startIndex < endIndex))
                    {
                        startIndex++;
                    }
                }

                if (uniqueChars.size() == foundChars.size())
                {
                    if ((minWindow == null)
                            || (minWindow.length() > endIndex - startIndex + 1))
                    {
                        minWindow = this.source.substring(startIndex,
                                endIndex + 1);
                    }

                    startIndex++;

                    startIndex = getNextUniqueCharOccurance(this.source,
                            uniqueChars, startIndex);

                    endIndex = startIndex;
                    foundChars.clear();

                    continue;
                }
            }

            endIndex++;
        }

        if (minWindow == null)
        {
            throw new Exception("Window not found");
        }

        return minWindow;
    }

    private int getNextUniqueCharOccurance(String input,
            Set<Character> uniqueChars, int startIndex)
    {
        while ((startIndex < input.length())
                && !uniqueChars.contains(input.charAt(startIndex)))
        {
            startIndex++;
        }

        return startIndex;
    }

    private void prepareUniqueCharacterSet(String unique,
            Set<Character> characterSet)
    {
        for (char ch : unique.toCharArray())
        {
            characterSet.add(Character.valueOf(ch));
        }
    }
}