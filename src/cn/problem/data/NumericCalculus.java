package cn.problem.data;

import Jama.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.regex.Matcher;

/**
 * Created by Cornelius on 21.02.2016.
 */
public class NumericCalculus {

    public static double precision=0.0;

    public static double calculatePrecision(){
        double u=1.0;
        while(1.0+u != 1.0){
            u/=10.0;
            System.out.println("[Log "+System.currentTimeMillis()+"][HW1]Precision calculus - Intermediate result: ["+u+"]");
        }
        u*=10.0;
        System.out.println("[Log "+System.currentTimeMillis()+"][HW1]Precision calculus - Final result: ["+u+"]");
        precision=u;
        return u;
    }

    public static String tanApproximation(double x, double e){

        if(precision==0.0)calculatePrecision();

        double math_tan;

        if (x == Math.PI/2 || x == Math.PI) {
            math_tan = Double.POSITIVE_INFINITY;
            System.out.println("[Log "+System.currentTimeMillis()+"][HW1]Tangent approximation[Math.tan] - Intermediate result: right argument= ["+math_tan+"]");
            return "[Rezultat partial] Valoare rezultat Math.tan(x): "+math_tan+"!";
        }
        else if (x == -Math.PI / 2 || x == -Math.PI) {
            math_tan = Double.NEGATIVE_INFINITY;
            System.out.println("[Log "+System.currentTimeMillis()+"][HW1]Tangent approximation[Math.tan] - Intermediate result: right argument= ["+math_tan+"]");
            return  "[Rezultat partial] Valoare rezultat Math.tan(x): "+math_tan+"!";
        }
        boolean flag=false;
        while(x<-Math.PI/2){
            x+=Math.PI/2;
            flag=true;
        }

        while(x>Math.PI/2)
            x-=Math.PI/2;

        if(flag)
            math_tan=-Math.tan(-x);
        else
            math_tan=Math.tan(x);

        if(e>0)
            e=-e;
        double mic=Math.pow(10,e);

        int k=50;

        double[] a = new double[k];
        a[1] = x;
        for (int i = 2; i < k; ++i)
        {
            a[i] = -Math.pow(x, 2);
        }

        double[] b = new double[k];
        for (int i = 1; i < k; i++)
        {
            b[i] = (i - 1) * 2 + 1;
        }

        double f_last=b[0];
        double f_current;
        if(f_last==0)
            f_last=mic;

        double c_last=f_last;
        double c_current;

        double d_last=0.0;
        double d_current;

        double delta;

        int j=1;

        do{
            d_current=b[j]+a[j]*d_last;
            if(d_current==0)
                d_current=mic;

            c_current=b[j]+a[j]/c_last;
            if(c_current==0)
                c_current=mic;

            d_current=1.0/d_current;
            delta=c_current*d_current;
            f_current=delta*f_last;

            c_last=c_current;
            d_last=d_current;
            f_last=f_current;
            j++;
        }while(Math.abs(delta-1)>=precision);

        System.out.println("[Log "+System.currentTimeMillis()+"][HW1]Tangent approximation[Lentz] - Intermediate result: left argument= ["+f_current+"]");
        System.out.println("[Log "+System.currentTimeMillis()+"][HW1]Tangent approximation[Math.tan] - Intermediate result: right argument= ["+math_tan+"]");

        System.out.println("[Log "+System.currentTimeMillis()+"][HW1]Tangent approximation - Final result:["+(math_tan==f_current?"True":"False")+"]");

        if(f_current==math_tan)
            return "[Rezultat adevarat]: "+f_current+" = "+math_tan+" !";
        else  return "[Rezultat fals]: "+f_current+" != "+math_tan+" !";

    }

    public static String sumAssociativity(){

        if(precision==0.0)calculatePrecision();

        double a=1.0;
        double b=precision/10.0;
        double c=precision/10.0;
        double left;
        double right;

        left=(a+b)+c;
        right=a+(b+c);

        System.out.println("[Log "+System.currentTimeMillis()+"][HW1]Sum associativity - Intermediate result: left argument= ["+left+"]");
        System.out.println("[Log "+System.currentTimeMillis()+"][HW1]Sum associativity - Intermediate result: right argument= ["+right+"]");

        System.out.println("[Log "+System.currentTimeMillis()+"][HW1]Sum associativity - Final result: ");
        if(right==left)
            System.out.print("True, left argument=right argument\n");
        else
            System.out.print("False, left argument!=right argument\n");

        if(left==right)
            return "[Rezultat adevarat]: "+left+" = "+right+" !";
        else  return "[Rezultat fals]: "+left+" != "+right+" !";
    }

    public static String mulAssociativity(double a,double b, double c){
        double left;
        double right;

        left=(a*b)*c;
        right=a*(b*c);

        System.out.println("[Log "+System.currentTimeMillis()+"][HW1]Multiplication associativity - Intermediate result: left argument= ["+left+"]");
        System.out.println("[Log "+System.currentTimeMillis()+"][HW1]Multiplication associativity - Intermediate result: right argument= ["+right+"]");

        System.out.println("[Log "+System.currentTimeMillis()+"][HW1]Multiplication associativity - Final result: ");
        if(right==left)
            System.out.print("True, left argument=right argument\n");
        else
            System.out.print("False, left argument!=right argument\n");

        if(left==right)
            return "[Rezultat adevarat]: "+left+" = "+right+" !";
        else  return "[Rezultat fals]: "+left+" != "+right+" !";
    }

    public static String createRandomMatrix(int rows,int columns){
        double rangeMin= -50.0;
        double rangeMax=50.0;
        Random r = new Random();
        double result[][]=new double[rows][columns];

        for (int i = 0; i <rows ; i++) {
            for (int j = 0; j <columns ; j++) {
                result[i][j]=rangeMin + (rangeMax - rangeMin) * r.nextDouble();
            }
        }

        //System.out.println("[Log "+System.currentTimeMillis()+"][HW1]Create random matrix "+result.toString());

        return NumericCalculus.simpleToString(result);
    }

    public static double[][] randomMatrix(int rows,int columns){
        double rangeMin= -10.0;
        double rangeMax=10.0;
        DecimalFormat df = new DecimalFormat("#.###");
        Random r = new Random();
        double result[][]=new double[rows][columns];

        for (int i = 0; i <rows ; i++) {
            for (int j = 0; j <columns ; j++) {
                result[i][j]=rangeMin + (rangeMax - rangeMin) * r.nextDouble();
            }
        }

        //System.out.println("[Log "+System.currentTimeMillis()+"][HW2]Create random matrix "+simpleToString(result));

        return result;
    }

    public static double[][] fileParsing(File file) {
        double[][] result=null;

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            System.out.println("[Log "+System.currentTimeMillis()+"][HW1]File parsing - File not found "+file.getName());
        }
        assert fstream != null;
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        int lineIdx=-1;
        try {
            while ((strLine = br.readLine()) != null)   {

                String splittedLine[]=strLine.split(" ");
                if (lineIdx==-1){
                    if(splittedLine.length>=2)
                        result=new double[Integer.parseInt(splittedLine[0])][Integer.parseInt(splittedLine[1])];
                    else if(splittedLine.length==1)
                        result=new double[1][Integer.parseInt(splittedLine[0])];

                    //todo test if not integer values
                }else{
                    assert result != null;
                    if(lineIdx>=result.length)break;
                    for (int i = 0; i < splittedLine.length; i++) {
                        result[lineIdx][i]=Double.parseDouble(splittedLine[i]);
                        //todo test if not double values
                    }
                }
                lineIdx++;
            }
        } catch (IOException e) {
            System.out.println("[Log "+System.currentTimeMillis()+"][HW1]File parsing - IOException "+file.getName());
        }

        try {
            br.close();
        } catch (IOException e) {
            System.out.println("[Log "+System.currentTimeMillis()+"][HW1]File parsing - Error when closing file: "+file.getName());

        }
        System.out.println("[Log "+System.currentTimeMillis()+"][HW1]File parsing - File parsed.");
        return result;
    }

    public static String simpleToString(double[][] matrix){
        String result="";
        result+=matrix.length+" "+matrix[0].length+"\n";
        for (double[] aMatrix : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                result += aMatrix[j] + " ; ";
            }
            result += "\n";
        }
       //System.out.println("[Log "+System.currentTimeMillis()+"][HW1]Simple matrix to string "+result.toString());
        return result;
    }





}
