import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Driver Class for VM Implementation.
 */
public class VirtualMachine {

    public static void main(String[] args) {

        if(args.length != 1){
            System.out.println("Usage : java VirtualMachine \"[Dir Path]\"");
        }
        else{
            Path inputDirPath = Paths.get(args[0]);
            Path outputFilePath = inputDirPath.resolve(inputDirPath.getFileName() + ".asm");

            CodeWriter codeWriter = new CodeWriter();

            try (Stream<Path> entries = Files.list(inputDirPath)) {

                List<String> finalOutput = new ArrayList<>();

                String bootstrapCode = "@256\n" + "D=A\n" + "@SP\n" + "M=D\n" + codeWriter.callTranslation("Sys.init", 0);
                finalOutput.add(bootstrapCode);//Adding Bootstrap Code

                List<String> finalCode = entries
                        .filter(entry -> entry.getFileName().toString().endsWith(".vm"))//filtering vm files
                        .map(entry -> processFile(entry, codeWriter))//translating each vm file to asm file
                        .flatMap(Collection::stream)//merging all files into one file
                        .collect(Collectors.toList());

                finalOutput.addAll(finalCode);

                Files.write(outputFilePath, finalOutput, Charset.defaultCharset());
            }catch(IOException ex){
                System.out.println("No such directory exists : " + inputDirPath);
            }
        }
    }

    /**
     * This function parses and translates one file at a time
     * @param inputFilePath path of the input file which needs to be translated
     * @param codeWriter instances of code writer which translates commands
     * @return list of translated commands
     */
    private static List<String> processFile(Path inputFilePath, CodeWriter codeWriter) {

        List<String> fileOutput = new ArrayList<>();

        String inputFileName = inputFilePath.getFileName().toString();
        String inputFileNameWithoutExt = inputFileName.substring(0,inputFileName.lastIndexOf("."));

        try(Stream<String> lines = Files.lines(inputFilePath, Charset.defaultCharset())) {
            String[] functionName = new String[1];//Array of length 1 to store function name
            functionName[0] = "";//Initial Value of function which is appended to labels

            fileOutput = removeComments(lines).map(a -> {
                int len = a.split("\\s+").length;
                String strParts[] = a.split("\\s+");

                if (len == 1) {
                    switch (strParts[0]) {
                        case "return"://return command
                            return codeWriter.returnTranslation();
                        default://arithmetic and logic commands
                            return codeWriter.arithmeticAndLogicTranslation(strParts[0]);
                    }
                } else if (len == 2) {
                    switch (strParts[0]) {
                        case "label"://label commands
                            return codeWriter.symbolTranslation(functionName[0] + "$" + strParts[1]);
                        case "goto"://goto commands
                            return codeWriter.gotoTranslation(functionName[0] + "$" + strParts[1]);
                        case "if-goto"://if commands
                            return codeWriter.ifTranslation(functionName[0] + "$" + strParts[1]);
                        default:
                            return "INVALID COMMAND: " + a;
                    }
                } else if (len == 3) {
                    switch (strParts[0]) {
                        case "call"://function call
                            return codeWriter.callTranslation(strParts[1], Integer.parseInt(strParts[2]));
                        case "function"://function definition
                            functionName[0] = strParts[1];
                            return codeWriter.functionTranslation(strParts[1], Integer.parseInt(strParts[2]));
                        default://memory access commands
                            return codeWriter.pushPopTranslation(strParts[0], strParts[1], Integer.parseInt(strParts[2]), inputFileNameWithoutExt);
                    }
                } else
                    return "INVALID COMMAND: " + a;

            }).collect(Collectors.toList());
        }catch (IOException ex){
            System.out.println("No such file exists : " + inputFilePath);
        }
        return fileOutput;
    }

    /**
     * Method to remove comments from source file
     * @param input stream which contains all the lines of the file
     * @return stream with removed comments
     */
    private static Stream<String> removeComments(Stream<String> input){
        return input.filter(a -> !a.trim().startsWith("//"))
                .filter(a -> !a.trim().isEmpty())
                .map(a -> a.substring(0, a.contains("//") ? a.indexOf("//") : a.length()).trim());
    }
}
