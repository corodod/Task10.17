package ru.vsu.cs.shishkin.swing_app;

import ru.vsu.cs.shishkin.main_logic.MainLogicTask;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Arrays;

import static ru.vsu.cs.shishkin.console_app.ConsoleApp.countLengthColumnMatrix;

public class FrameMain extends JFrame {
    private JButton readFromFileButton;
    private JPanel panelMain;
    private JButton writeInFileButton;
    private JTable tableInput;
    private JTable tableOutput;

    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;

//    private int countLengthColumnMatrix(double[][] matrix){// здесь мы по 6 элементов берем
//        int c = 0;
//        for (double[] doubles : matrix) {
//            if (doubles.length == 6) c++;
//        }
//        if(c==matrix.length) return 1;
//        else return 0;
//    }
    //countLengthColumnMatrix

    public FrameMain(){

        final int[] numAnswer = {0};

        this.setTitle("FrameMain");
        this.setContentPane(panelMain);
        this.setPreferredSize(new Dimension(500,500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        JTableUtils.initJTableForArray(tableInput,40,false,false,true, true);
        JTableUtils.initJTableForArray(tableOutput,40,false,false,true, true);



        //кнопка чтения файлов
        readFromFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        //чтение двумерного массива из файла по выбранному пути
                        double[][] data = ArrayUtils.readDoubleArray2FromFile(fileChooserOpen.getSelectedFile().getPath());
                        //запись в таблицу считанного двумерного массива
                        JTableUtils.writeArrayToJTable(tableInput,data);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        //кнопка записи полученного результата в файл
        writeInFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        double[][] data = JTableUtils.readDoubleMatrixFromJTable(tableInput);
                        StringBuilder answer = new StringBuilder();
                        if(data!=null){
                            if(countLengthColumnMatrix(data)==1){
                                double[][] resData = MainLogicTask.makeChoiceFromPoints(data);//0000000000000000000000000000
                                //System.out.println(Arrays.deepToString(resData));//0000000000000000000000000000000000
                                JTableUtils.writeArrayToJTable(tableOutput,resData);
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
                        String path = fileChooserSave.getSelectedFile().getPath();
                        if (!path.toLowerCase().endsWith(".txt")) {
                            path += ".txt";
                        }
                        File file = new File(path);
                        PrintWriter pw = new PrintWriter(file);
                        pw.println(answer);
                        pw.close();
                        String answerStr = "Output file path: " + Paths.get(path).toAbsolutePath();
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
    }
}
