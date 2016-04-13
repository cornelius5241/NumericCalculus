package cn.problem.data.rmr;

import cn.problem.data.rm.Node;
import cn.problem.data.rm.SparseMatrix;

import java.util.ArrayList;

/**
 * Created by Cornelius on 29.03.2016.
 */
public class LiniarSystem {

    private static double precision=Math.pow (10,-9);

    public static boolean testFirstDiag(SparseMatrix A){
        System.out.println("[Log "+System.currentTimeMillis()+"][HW5]LiniarSystem - test diagonal elements to be not null.\n");

        int size = A.getSize ();
        for (int i = 0; i < size; i++) {
                if(A.getNodeValue (i,i)==0)return false;
        }
        return true;
    }

    public static ArrayList<Double> SOR(SparseMatrix A){
        System.out.println("[Log "+System.currentTimeMillis()+"][HW5]LiniarSystem - Start SOR Algorithm.\n");

        if(testFirstDiag (A)==false) return null;

        SparseMatrix L=inferiorMatrix (A);
        SparseMatrix U=superiorMatrix (A);
        ArrayList<Double> D = diag (A);
        int n=A.getSize ();
        ArrayList<Double> x=new ArrayList<>(n);
        ArrayList<Double> x_c=new ArrayList<>(n);
        ArrayList<Double> x_p=new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            x_c.add(i,0.0);
            x_p.add(i,0.0);
            x.add (i,0.0);
        }

        int k=0,kmax=1000;
        double delta=0;

        do{
            //for (int i = 0; i < n; i++) {
            //    x_p.set(i,x_c.get (i));
            //}

            for (int i = 0; i <n ; i++) {

                double left_sum=0;

                for (Node node:L.getMatrix ()[i]) {
                    //left_sum+=x_c.get(node.getI ())*node.getValue();
                    left_sum+=x.get (node.getI ())*node.getValue ();
                }

                double right_sum=0;
                for (Node node:U.getMatrix ()[i]) {
                    //right_sum+=x_p.get (node.getI ())*node.getValue ();
                    right_sum+=x.get (node.getI ())*node.getValue ();
                }

                //double value = (-0.2)*x_p.get(i)+1.2*(A.getVector().get(i)-left_sum-right_sum )/D.get(i);
                double value = (-0.2)*x.get(i)+1.2*(A.getVector().get(i)-left_sum-right_sum )/D.get(i);
                delta+= Math.abs (value-x.get (i) );
                //x_c.set (i,value);

                x.set (i,value);
            }

            //delta=delta (x_c,x_p);

            k++;

        }while(delta>=precision && k<=kmax && delta<= Math.pow (10,8));

        if(delta<precision) {
            System.out.println("[Log "+System.currentTimeMillis()+"][HW5]LiniarSystem - found approximated solution.\n");
            //System.out.println("[Log "+System.currentTimeMillis()+"][HW5]Solution:\n"+ x_c.toString ());
        }else System.out.println("[Log "+System.currentTimeMillis()+"][HW5]LiniarSystem - divergent.\n");

        return x;
    }

    private static double delta(ArrayList<Double> x_c,ArrayList<Double> x_p){
        //System.out.println("[Log "+System.currentTimeMillis()+"][HW5]LiniarSystem - delta calculus.\n");
        double result=0.0;

        for (int i = 0; i < x_c.size (); i++) {
          result+=Math.abs ( x_c.get (i)-x_p.get (i));
        }

        return result;
    }

    public static SparseMatrix inferiorMatrix(SparseMatrix A){
        //System.out.println("[Log "+System.currentTimeMillis()+"][HW5]LiniarSystem - calculate inferior matrix.\n");
        SparseMatrix result=new SparseMatrix(A.getSize(),20);

        int size = A.getSize ();
        for (int i = 0; i < size; i++) {
            for (Node node : A.getMatrix ( )[i]) {
                if(node.getI()<i)
                    result.getMatrix ()[i].add (new Node(node.getValue (),node.getI ()));
            }
        }
        return result;
    }

    public static SparseMatrix superiorMatrix(SparseMatrix A){
        //System.out.println("[Log "+System.currentTimeMillis()+"][HW5]LiniarSystem - calculate superior matrix.\n");
        SparseMatrix result=new SparseMatrix(A.getSize(),20);

        int size = A.getSize ();
        for (int i = 0; i < size; i++) {
            for (Node node : A.getMatrix ( )[i]) {
                if(node.getI()>i)
                    result.getMatrix ()[i].add (new Node(node.getValue (),node.getI ()));
            }
        }

        return result;
    }

    public static ArrayList<Double> diag(SparseMatrix A){
        //System.out.println("[Log "+System.currentTimeMillis()+"][HW5]LiniarSystem - calculate diagonal.\n");
        ArrayList<Double> diag=new ArrayList<> (A.getSize ());

        int size = A.getSize ();
        for (int i = 0; i < size; i++) {
            diag.add (A.getNodeValue (i,i));
        }

        return diag;
    }

    public static double checkSolution(SparseMatrix A,ArrayList<Double> x){
        System.out.println("[Log "+System.currentTimeMillis()+"][HW5]LiniarSystem - check solution.\n");
        double norm=0;

        for (int i = 0; i <A.getSize () ; i++) {
            double Ax=0;
            for (Node n:A.getMatrix()[i]) {
               Ax+=x.get (n.getI ())*n.getValue ();
            }
            norm+= Math.abs (Ax-A.getVector ().get(i));
        }

        return norm;
    }
}
