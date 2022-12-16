package ru.vsu.cs.shishkin.console_app;

import ru.vsu.cs.shishkin.main_logic.MainLogicTask;
import ru.vsu.cs.shishkin.swing_app.ArrayUtils;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Arrays;

public class Test {
    public static String testPath1In=".\\input01.txt", testPath1Out=".\\output01.txt", testPath1OutReady=".\\output01Ready.txt";
    public static String testPath2In=".\\input02.txt", testPath2Out=".\\output02.txt", testPath2OutReady=".\\output02Ready.txt";
    public static String testPath3In=".\\input03.txt", testPath3Out=".\\output03.txt", testPath3OutReady=".\\output03Ready.txt";

    public static void runTest() throws FileNotFoundException {
        testAnswer(testPath1In,testPath1Out,testPath1OutReady);
        testAnswer(testPath2In,testPath2Out,testPath2OutReady);
        testAnswer(testPath3In,testPath3Out,testPath3OutReady);
    }
    public static void testAnswer(String in, String out,String outReady) throws FileNotFoundException {
        double[][] data = ArrayUtils.readDoubleArray2FromFile(in);// двумерный массив инпут который есть множесто
        //assert data != null;// Такая штука чтобы норм было, джава сама написала уже не нужна
        if(data == null){
            System.out.println("Данных нет");
        }else{
            double[][] result = MainLogicTask.makeChoiceFromPoints(data);//почти всегда сюда будет заходить
            ArrayUtils.writeArrayToFile(out,result);// в файл тут запишет результат
            boolean flag = testAnswerCheck(""+ Paths.get(outReady).toAbsolutePath(),""+Paths.get(out).toAbsolutePath());// здесь видимо хуйня
            if(flag) System.out.println("Все хорошо");
            else System.out.println("Все плохо");
        }
    }
    public static boolean testAnswerCheck(String pathTest, String pathAns){// pathTest  адрес файла где ответ лежит
        int[] testAnswer = ArrayUtils.readIntArrayFromFile(pathTest);
        int[] resultAnswer = ArrayUtils.readIntArrayFromFile(pathAns);
        System.out.println("148");
        System.out.println(Arrays.toString(testAnswer));
        System.out.println("148");
        System.out.println(Arrays.toString(resultAnswer));
        System.out.println("148");
        return Arrays.equals(testAnswer,resultAnswer);
    }
}

