package ru.vsu.cs.shishkin.main_logic;

public class MainLogicTask {
    public static double[][] makeChoiceFromPoints(double[][] data){
        int count = 0;
        boolean[] indexes = new boolean[data.length];
        for(int i=0;i<data.length;i++){
            double[] points = data[i];
            double[] p1 = {points[0],points[1]};
            double[] p2 = {points[2],points[3]};
            double[] p3 = {points[4],points[5]};
            if(checkAllPart(p1,p2,p3)){
                count+=1;
                indexes[i] = true;//000000000000000000000000000000000000000000000000000000000000000000000000000000000000
            }else{
                indexes[i] = false;
            }
            //System.out.println(indexes[i]);
            /*

             */
        }
        double[][] resArr = new double[count][];// count количество здравых треугльников, обозначаем их количество
        int index = 0;
        for(int i=0;i<indexes.length;i++){
            if(index<count && indexes[i]){
                resArr[index]=data[i];
                index++;
            }
        }
        return resArr;
        //System.out.println(resArr);00000000000000000000000000000000000000000000000000
    }

    public static int znak(double member){
        if(member>=0){
            return 1;
        }else {
            return -1;
        }
    }

    public static boolean checkAllPart(double[] p1, double[] p2, double[] p3){//p0 это иксы треугольника а p1 это его игрики
        return ((-1) * znak(p1[0]) * znak(p2[0]) + (-1) * znak(p1[0]) * znak(p3[0]) + (-1) * znak(p2[0]) * znak(p3[0]) + (-1) * znak(p1[1]) * znak(p2[1]) + (-1) * znak(p1[1]) * znak(p3[1]) + (-1) * znak(p2[1]) * znak(p3[1])) == 2;
    }
}
