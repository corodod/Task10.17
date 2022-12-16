package ru.vsu.cs.shishkin.swing_app;
/*Для набора треугольников, заданных тремя вершинами, найти такие, которые
  одновременно лежат во всех 4-х четвертях (на координатной сетке).
  Треугольник описывает как три точки (отдельный тип – структура или класс),
  каждая точка – как две координаты (отдельный тип – структура или класс).

  definePointSide
*/
import ru.vsu.cs.shishkin.console_app.Test;

import java.util.Locale;

public class Main {
    public static void main(String[] args) throws Exception{
        Locale.setDefault(Locale.ROOT);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override//хз нужно оно тут или нет00000000000000000000000000000000000000000000000000000000000000000000000000000
            public void run() {
                new FrameMain().setVisible(true);
            }
        });
        //Test.runTest();
    }
}