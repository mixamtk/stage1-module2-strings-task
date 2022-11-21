package com.epam.mjc;

import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        String replacement = " ";
        int lastPos = -1;
        boolean isDelimiter = false;
        char[] arrSource = source.toCharArray();
        char charPos;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < source.length(); i++){
            charPos = arrSource[i];
            for (String delimiter : delimiters) {
                if (delimiter.charAt(0) == charPos) {
                    if (lastPos != i-1) {
                        stringBuilder.append(replacement);
                    }
                    lastPos = i;
                    isDelimiter = true;
                }
            }
            if (!isDelimiter)  stringBuilder.append(charPos);
            isDelimiter = false;
        }
        return List.of(stringBuilder.toString().split(replacement));
    }
}
