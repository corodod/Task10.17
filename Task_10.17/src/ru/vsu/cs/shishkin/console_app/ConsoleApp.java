package ru.vsu.cs.shishkin.console_app;

import ru.vsu.cs.shishkin.main_logic.MainLogicTask;
import ru.vsu.cs.shishkin.swing_app.ArrayUtils;

import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;

public class ConsoleApp {
    static InputArgs inputArgs = new InputArgs();

    //метод чтения из консоли строки и преобразование её в набор строковых аргументов
    public static String[] readConsoleLineParameters(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String cmdLine = null;
        try{
            cmdLine=bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert cmdLine != null;
        return cmdLine.split("\\s");
    }

    //парсинг введённых в консоль данных
    public static InputArgs parseCmdArgs(String[] args) {
        String inputFilePathVar, outputFilePathVar;
        if(args.length==2){
            inputFilePathVar = args[0];
            outputFilePathVar = args[1];
        }else{
            inputFilePathVar = args[1];
            outputFilePathVar = args[3];
        }
        inputArgs.setInputFile(inputFilePathVar);
        inputArgs.setOutputFile(outputFilePathVar);
        return inputArgs;
    }

    //запуск решения на основе адресов файлов
    public static void runSolution(String[] pathsTest, int num) throws IOException {
        //метод задания адресов для чтения/записи файлов
        if(num==0){
            //случай когда выполняется консольный (не тестовый ввод)
            inputArgs=parseCmdArgs(pathsTest);
        }else{
            //случай для тестов
            inputArgs.setInputFile(pathsTest[0]);
            inputArgs.setOutputFile(pathsTest[1]);
        }
        //метод чтения файлов по заданным адресам
        doMainLogicConsole(inputArgs);
        printSuccessMessage(num);
    }

    public static int countLengthColumnMatrix(double[][] matrix){
        int c = 0;
        for (double[] doubles : matrix) {
            if (doubles.length == 6) c++;
        }
        if(c==matrix.length) return 1;
        else return 0;
    }

    //метод делает основную задачу по реализации
    public static void doMainLogicConsole(InputArgs inputArgs) throws FileNotFoundException {
        double[][] data = ArrayUtils.readDoubleArray2FromFile(inputArgs.getInputFile());
        StringBuilder answer = new StringBuilder();
        if(data!=null){
            if(countLengthColumnMatrix(data)==1){
                double[][] resData = MainLogicTask.makeChoiceFromPoints(data);
                for(int i=0;i<resData.length;i++){
                    answer.append(Arrays.toString(data[i])).append("\n");
                }
            }else{
                System.out.println("Матрица не цельная, нехватает данных");
                answer = new StringBuilder("Матрица не цельная, нехватает данных");
            }
        }else{
            System.out.println("Нет данных");
            answer = new StringBuilder("Нет данных");
        }
        writeInFileAnswer(answer,inputArgs.getOutputFile());
    }

    public static void writeInFileAnswer(StringBuilder ans, String path) throws FileNotFoundException {
        File file = new File(path);
        PrintWriter pw = new PrintWriter(file);
        pw.println(ans);
        pw.close();
        System.out.println("Output file path: " + Paths.get(path).toAbsolutePath());
    }

    //печать сообщения успешной работы
    public static void printSuccessMessage(int num){
        if(num==0){
            System.out.println("The main program is done");
        }else{
            System.out.println("Test " + num + " is done");
        }
        System.out.println();
    }


    public static void main(String[] args) throws IOException {
        System.out.println();
        //тестовая часть
        //runTestConsole();//test.runTest сделать ща также как и в

        System.out.println();
        System.out.print("Pls, enter your path: ");

        //создание заготовки строки для дальнейшего парсинга
        String[] argsCmd = readConsoleLineParameters();
        //решение
        runSolution(argsCmd,0);
    }
}
