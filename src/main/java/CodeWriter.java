/**
 * Generates Assembly Code for given VM Commands
 */
public class CodeWriter {

    private int counter;//counter for symbols

    public CodeWriter() {
        counter = 0;
    }

    /**
     * Produces asm commands for arithmetic and logic operators
     * @param command which needs to be translated
     * @return asm translation of the command
     */
    public String arithmeticAndLogicTranslation(String command){

        switch (command){
            case "add" :
                return binaryArithmeticAndLogic("M+D");
            case "sub":
                return binaryArithmeticAndLogic("M-D");
            case "neg":
                return unaryArithmeticAndLogic("-M");
            case "eq":
                return binaryRelation("JNE");
            case "gt":
                return binaryRelation("JLE");
            case "lt":
                return binaryRelation("JGE");
            case "and":
                return binaryArithmeticAndLogic("D&M");
            case "or":
                return binaryArithmeticAndLogic("D|M");
            case "not":
                return unaryArithmeticAndLogic("!M");
            default:
                return "INVALID COMMAND " + command;
        }
    }

    /**
     * Produces asm translation of memory commands
     * @param command which needs to be translated
     * @param segment of memory on which command operates
     * @param index within segment
     * @param fileName to be used in symbol for static variables
     * @return string of asm commands
     */
    public String pushPopTranslation(String command, String segment, int index, String fileName){
        if("push".equals(command)){
            switch (segment){
                case "constant":
                    return "@" + index + "\n" +
                            "D=A\n" +
                            "@SP\n" +
                            "A=M\n" +
                            "M=D\n" +
                            "@SP\n" +
                            "M=M+1";
                case "argument":
                    return pushCommand("ARG", index);
                case "local":
                    return pushCommand("LCL", index);
                case "static":
                    return pushMemorySegment(fileName + "." + index);
                case "this":
                    return pushCommand("THIS", index);
                case "that":
                    return pushCommand("THAT", index);
                case "pointer":
                    return pushCommandFixedMemory(3 + index);
                case "temp":
                    return pushCommandFixedMemory(5 + index);
                default:
                    return "INVALID SEGMENT " + segment;
            }

        } else if ("pop".equals(command)) {
            switch (segment) {
                case "argument":
                    return popCommand("ARG", index);
                case "local":
                    return popCommand("LCL", index);
                case "static":
                    return popStaticCommand(fileName + "." + index);
                case "this":
                    return popCommand("THIS", index);
                case "that":
                    return popCommand("THAT", index);
                case "pointer":
                    return popCommandFixedMemory(3 + index);
                case "temp":
                    return popCommandFixedMemory(5 + index);
                default:
                    return "INVALID SEGMENT " + segment;
            }
        } else
            return "INVALID COMMAND " + command;
    }

    /**
     * Translates symbols
     * @param symbol which is translated
     * @return String containing asm command
     */
    public String symbolTranslation(String symbol){
        return "(" + symbol + ")";
    }

    /**
     * Translates go to command
     * @param symbol to goto
     * @return String containing asm command
     */
    public String gotoTranslation(String symbol) {
        return "@" + symbol + "\n" +
                "0;JMP";
    }

    /**
     * Translates and if command
     * @param symbol to goto
     * @return String containing asm commands
     */
    public String ifTranslation(String symbol) {
        return "@SP\n" +
                "AM=M-1\n" +
                "D=M\n" +
                "@" + symbol + "\n" +
                "D;JNE";
    }

    /**
     * Translates function calls
     * @param function name of the function
     * @param nArgs number of arguments
     * @return asm commands
     */
    public String callTranslation(String function, int nArgs) {
        ++counter;
        return  pushSymbol("returnAddress" + counter) + "\n" +
                pushMemorySegment("LCL") + "\n" +
                pushMemorySegment("ARG") + "\n" +
                pushMemorySegment("THIS") + "\n" +
                pushMemorySegment("THAT") + "\n" +
                "@" + nArgs + "\n" +
                "D=A\n" +
                "@5\n" +
                "D=D+A\n" +
                "@SP\n" +
                "D=M-D\n" +
                "@ARG\n" +
                "M=D\n" +
                "@SP\n" +
                "D=M\n" +
                "@LCL\n" +
                "M=D\n" +
                "@" + function + "\n" +
                "0;JMP\n" +
                "(returnAddress" + counter + ")";
    }

    /**
     * Translates function definitions
     * @param function name of the function
     * @param nLocals number of local variables
     * @return asm commands
     */
    public String functionTranslation(String function, int nLocals){
        ++counter;
        return "(" + function + ")" + "\n" +
                "@" + nLocals + "\n" +
                "D=A\n" +
                "@End_Local" + counter + "\n" +
                "D;JEQ\n" +
                "(Initialize_Local" + counter + ")\n" +
                "@SP\n" +
                "A=M\n" +
                "M=0\n" +
                "@SP\n" +
                "M=M+1\n" +
                "D=D-1\n" +
                "@Initialize_Local" + counter + "\n" +
                "D;JGT\n" +
                "(End_Local" + counter + ")";
    }

    /**
     * Translation of return statement
     * @return asm commands
     */
    public String returnTranslation(){
        return "@LCL\n" +
                "D=M\n" +
                "@R13\n" +
                "M=D\n" +
                "@5\n" +
                "D=D-A\n" +
                "A=D\n" +
                "D=M\n" +
                "@R14\n" +
                "M=D\n" +
                "@SP\n" +
                "AM=M-1\n" +
                "D=M\n" +
                "@ARG\n" +
                "A=M\n" +
                "M=D\n" +
                "D=A+1\n" +
                "@SP\n" +
                "M=D\n" +
                "@R13\n" +
                "AM=M-1\n" +
                "D=M\n" +
                "@THAT\n" +
                "M=D\n" +
                "@R13\n" +
                "AM=M-1\n" +
                "D=M\n" +
                "@THIS\n" +
                "M=D\n" +
                "@R13\n" +
                "AM=M-1\n" +
                "D=M\n" +
                "@ARG\n" +
                "M=D\n" +
                "@R13\n" +
                "AM=M-1\n" +
                "D=M\n" +
                "@LCL\n" +
                "M=D\n" +
                "@R14\n" +
                "A=M\n" +
                "0;JMP";
    }

    /**
     * Helper function which takes out the common code for binary arithmetic and logic commands
     * @param operation which is not common
     * @return string of asm commands
     */
    private String binaryArithmeticAndLogic(String operation){
        return "@SP\n" +
                "AM=M-1\n" +
                "D=M\n" +
                "A=A-1\n" +
                "M=" + operation;
    }

    /**
     * Helper function which takes out the common code for binary relation commands
     * @param operation which is not common
     * @return string of asm commands
     */
    private String binaryRelation(String operation){
        ++counter;
        return "@SP\n" +
                "AM=M-1\n" +
                "D=M\n" +
                "A=A-1\n" +
                "D=M-D\n" +
                "@FALSE" + counter + "\n" +
                "D;"+ operation +"\n" +
                "@SP\n" +
                "A=M-1\n" +
                "M=-1\n" +
                "@CONTINUE" + counter + "\n" +
                "0;JMP\n" +
                "(FALSE" + counter + ")\n" +
                "@SP\n" +
                "A=M-1\n" +
                "M=0\n" +
                "(CONTINUE" + counter + ")";
    }

    /**
     * Helper function which takes out the common code for unary logic and arithmetic commands
     * @param operation which is not common
     * @return string of asm commands
     */
    private String unaryArithmeticAndLogic(String operation){
        return "@SP\n" +
                "A=M-1\n" +
                "M=" + operation;
    }

    /**
     * Helper function which takes out the common code for ARG, LCL, THIS and THAT for pop commands
     * @param segment of memory
     * @param index within segment
     * @return string of asm commands
     */
    private String popCommand(String segment, int index){
        return "@" + segment + "\n" +
                "D=M\n" +
                "@" + index + "\n" +
                "D=D+A\n" +
                "@R13\n" +
                "M=D\n" +
                "@SP\n" +
                "AM=M-1\n" +
                "D=M\n" +
                "@R13\n" +
                "A=M\n" +
                "M=D";
    }

    /**
     * Helper function which takes out the common code for ARG, LCL, THIS and THAT push commands
     * @param segment of memory
     * @param index within segment
     * @return string of asm commands
     */
    private String pushCommand(String segment, int index){
        return "@" + segment + "\n" +
                "D=M\n" +
                "@" + index + "\n" +
                "A=D+A\n" +
                "D=M\n" +
                "@SP\n" +
                "A=M\n" +
                "M=D\n" +
                "@SP\n" +
                "M=M+1";
    }

    /**
     * Helper function which takes out the common code for pointer, temp push commands
     * @param index of memory
     * @return string of asm commands
     */
    private String pushCommandFixedMemory(int index){
        return "@R" + index + "\n" +
                "D=M\n" +
                "@SP\n" +
                "A=M\n" +
                "M=D\n" +
                "@SP\n" +
                "M=M+1";
    }

    /**
     * Helper function which takes out the common code for pointer, temp pop commands
     * @param index of memory
     * @return string of asm commands
     */
    private String popCommandFixedMemory(int index){
        return "@SP\n" +
                "AM=M-1\n" +
                "D=M\n" +
                "@R" + index + "\n" +
                "M=D";
    }

    /**
     * Helper function to translate push symbol or virtual memory segments LCL,ARG, THIS and THAT
     * @param symbol for static commands
     * @return string of asm commands
     */
    private String pushMemorySegment(String symbol){
        return "@" + symbol + "\n" +
                "D=M\n" +
                "@SP\n" +
                "A=M\n" +
                "M=D\n" +
                "@SP\n" +
                "M=M+1";
    }

    /**
     * Helper function to translate push symbol or virtual memory segments LCL,ARG, THIS and THAT
     * @param symbol for static commands
     * @return string of asm commands
     */
    private String pushSymbol(String symbol){
        return "@" + symbol + "\n" +
                "D=A\n" +
                "@SP\n" +
                "A=M\n" +
                "M=D\n" +
                "@SP\n" +
                "M=M+1";
    }

    /**
     * Helper function to translate static pop commands
     * @param symbol for static commands
     * @return string of asm commands
     */
    private String popStaticCommand(String symbol){
        return "@SP\n" +
                "AM=M-1\n" +
                "D=M\n" +
                "@" + symbol + "\n" +
                "M=D";
    }
}
