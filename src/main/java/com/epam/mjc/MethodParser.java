package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
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

    public List<MethodSignature.Argument> parseArguments (String strArguments) {
        String delimiterMethod = ",";
        String delimiter = " ";
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        String[] arrStrArg = strArguments.split(delimiterMethod);
        for (String value:arrStrArg) {
            String[] partArg = value.trim().split(delimiter);
            arguments.add(new MethodSignature.Argument(partArg[0],partArg[1]));
        }
        return arguments;
    }
}
