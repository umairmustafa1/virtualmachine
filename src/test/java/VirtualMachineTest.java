import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Test Class for Virtual Machine
 */
public class VirtualMachineTest {
    /*
    @Test
    public void simpleAdd(){
        VirtualMachine.main(new String[]{"D:\\Dropbox\\UChicago\\52011\\Software\\nand2tetris\\projects\\07\\StackArithmetic\\SimpleAdd"});

        Path asmFilePath = Paths.get("D:\\Dropbox\\UChicago\\52011\\Software\\nand2tetris\\projects\\07\\StackArithmetic\\SimpleAdd\\SimpleAdd.asm");

        try(Stream<String> testLines = Files.lines(asmFilePath, Charset.defaultCharset())){
            System.out.println("SimpleAdd: " + testLines.count());
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void stackTest(){
        VirtualMachine.main(new String[]{"D:\\Dropbox\\UChicago\\52011\\Software\\nand2tetris\\projects\\07\\StackArithmetic\\StackTest"});

        Path asmFilePath = Paths.get("D:\\Dropbox\\UChicago\\52011\\Software\\nand2tetris\\projects\\07\\StackArithmetic\\StackTest\\StackTest.asm");

        try(Stream<String> testLines = Files.lines(asmFilePath, Charset.defaultCharset())){
            System.out.println("StackTest: " + testLines.count());
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void basicTest(){
        VirtualMachine.main(new String[]{"D:\\Dropbox\\UChicago\\52011\\Software\\nand2tetris\\projects\\07\\MemoryAccess\\BasicTest"});

        Path asmFilePath = Paths.get("D:\\Dropbox\\UChicago\\52011\\Software\\nand2tetris\\projects\\07\\MemoryAccess\\BasicTest\\BasicTest.asm");

        try(Stream<String> testLines = Files.lines(asmFilePath, Charset.defaultCharset())){
            System.out.println("BasicTest: " + testLines.count());
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void pointerTest(){
        VirtualMachine.main(new String[]{"D:\\Dropbox\\UChicago\\52011\\Software\\nand2tetris\\projects\\07\\MemoryAccess\\PointerTest"});

        Path asmFilePath = Paths.get("D:\\Dropbox\\UChicago\\52011\\Software\\nand2tetris\\projects\\07\\MemoryAccess\\PointerTest\\PointerTest.asm");

        try(Stream<String> testLines = Files.lines(asmFilePath, Charset.defaultCharset())){
            System.out.println("PointerTest: " + testLines.count());
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void staticTest(){
        VirtualMachine.main(new String[]{"D:\\Dropbox\\UChicago\\52011\\Software\\nand2tetris\\projects\\07\\MemoryAccess\\StaticTest"});

        Path asmFilePath = Paths.get("D:\\Dropbox\\UChicago\\52011\\Software\\nand2tetris\\projects\\07\\MemoryAccess\\StaticTest\\StaticTest.asm");

        try(Stream<String> testLines = Files.lines(asmFilePath, Charset.defaultCharset())){
            System.out.println("StaticTest: " + testLines.count());
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    */
    @Test
    public void basicLoopTest() {
        VirtualMachine.main(new String[]{"D:\\Dropbox\\UChicago\\52011\\Software\\nand2tetris\\projects\\08\\ProgramFlow\\BasicLoop"});

        Path asmFilePath = Paths.get("D:\\Dropbox\\UChicago\\52011\\Software\\nand2tetris\\projects\\08\\ProgramFlow\\BasicLoop\\BasicLoop.asm");

        try(Stream<String> testLines = Files.lines(asmFilePath, Charset.defaultCharset())){
            System.out.println("BasicLoop: " + testLines.count());
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void fibonacciSeriesTest() {
        VirtualMachine.main(new String[]{"D:\\Dropbox\\UChicago\\52011\\Software\\nand2tetris\\projects\\08\\ProgramFlow\\FibonacciSeries"});

        Path asmFilePath = Paths.get("D:\\Dropbox\\UChicago\\52011\\Software\\nand2tetris\\projects\\08\\ProgramFlow\\FibonacciSeries\\FibonacciSeries.asm");

        try(Stream<String> testLines = Files.lines(asmFilePath, Charset.defaultCharset())){
            System.out.println("FibonacciSeries: " + testLines.count());
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void simpleFunctionTest() {
        VirtualMachine.main(new String[]{"D:\\Dropbox\\UChicago\\52011\\Software\\nand2tetris\\projects\\08\\FunctionCalls\\SimpleFunction"});

        Path asmFilePath = Paths.get("D:\\Dropbox\\UChicago\\52011\\Software\\nand2tetris\\projects\\08\\FunctionCalls\\SimpleFunction\\SimpleFunction.asm");

        try(Stream<String> testLines = Files.lines(asmFilePath, Charset.defaultCharset())){
            System.out.println("SimpleFunction: " + testLines.count());
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void nestedCallTest() {
        VirtualMachine.main(new String[]{"D:\\Dropbox\\UChicago\\52011\\Software\\nand2tetris\\projects\\08\\FunctionCalls\\NestedCall\\"});

        Path asmFilePath = Paths.get("D:\\Dropbox\\UChicago\\52011\\Software\\nand2tetris\\projects\\08\\FunctionCalls\\NestedCall\\NestedCall.asm");

        try(Stream<String> testLines = Files.lines(asmFilePath, Charset.defaultCharset())){
            System.out.println("NestedCall: " + testLines.count());
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void fibonacciElementTest() {
        VirtualMachine.main(new String[]{"D:\\Dropbox\\UChicago\\52011\\Software\\nand2tetris\\projects\\08\\FunctionCalls\\FibonacciElement\\"});

        Path asmFilePath = Paths.get("D:\\Dropbox\\UChicago\\52011\\Software\\nand2tetris\\projects\\08\\FunctionCalls\\FibonacciElement\\FibonacciElement.asm");

        try(Stream<String> testLines = Files.lines(asmFilePath, Charset.defaultCharset())){
            System.out.println("FibonacciElement: " + testLines.count());
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void staticsTest() {
        VirtualMachine.main(new String[]{"D:\\Dropbox\\UChicago\\52011\\Software\\nand2tetris\\projects\\08\\FunctionCalls\\StaticsTest"});

        Path asmFilePath = Paths.get("D:\\Dropbox\\UChicago\\52011\\Software\\nand2tetris\\projects\\08\\FunctionCalls\\StaticsTest\\StaticsTest.asm");

        try(Stream<String> testLines = Files.lines(asmFilePath, Charset.defaultCharset())){
            System.out.println("StaticsTest: " + testLines.count());
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

}