package com.epam.mjc;

import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        String delimiter = " ";
        String headArg = "(";
        int indstartStr = 0;
        int indEndMethodName = signatureString.indexOf(headArg);
        int indAccessMod = 0;
        int indReturnType = 1;
        // parse head method
        String headMethod = signatureString.substring(indstartStr,indEndMethodName);
        String[] arrHeadMethod = headMethod.split(delimiter);
        String returnType;
        String accessModifier;
        if (arrHeadMethod.length == 2) {
            indReturnType = 0;
            accessModifier = "";
            returnType = arrHeadMethod[indReturnType];
        }
        else {
            returnType = arrHeadMethod[indReturnType];
            accessModifier = arrHeadMethod[indAccessMod];
        }
        String methodName = arrHeadMethod[arrHeadMethod.length - 1];
        // parse arguments
        String strArgumentsMethod = signatureString.substring(indEndMethodName + 1,signatureString.length() - 1);
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        if (strArgumentsMethod.trim().length() != 0) arguments = parseArguments(strArgumentsMethod);
        // create object
        MethodSignature methodSignature = new MethodSignature(methodName,arguments);
        methodSignature.setAccessModifier(accessModifier);
        methodSignature.setReturnType(returnType);
        return methodSignature;
}
