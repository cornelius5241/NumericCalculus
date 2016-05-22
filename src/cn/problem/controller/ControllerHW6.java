package cn.problem.controller;

import cn.problem.data.rm.SparseMatrix;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.File;

/**
 * Created by Cornelius on 19.04.2016.
 */
public class ControllerHW6 {

    private static final File file = new File("E:\\FII\\CN\\NumericCalculusHW\\src\\cn\\problem\\data\\vvp\\m_rar_sim_2016.txt");


    @FXML
    TextField linesTextField;
    @FXML
    TextField columnsTextField;
    @FXML
    TextField epsilonTextField;

    SparseMatrix randomSM;
    SparseMatrix fileSM;

    private int getN(){
        int dimension=10;

        try{
            dimension=Integer.parseInt(columnsTextField.getText());
        }catch (Exception e){
            System.out.println("[Log "+System.currentTimeMillis()+"][HW1] Vector - error parsing dimension");
        }
        return dimension;
    }

    private int getP(){
        int dimension=10;

        try{
            dimension=Integer.parseInt(linesTextField.getText());
        }catch (Exception e){
            System.out.println("[Log "+System.currentTimeMillis()+"][HW1] Vector - error parsing dimension");
        }
        return dimension;
    }

    private double getEpsilon(){
        int precision=10;
        try{
            precision=Integer.parseInt(epsilonTextField.getText());
        }catch (Exception e){
            System.out.println("[Log "+System.currentTimeMillis()+"][HW1] Vector - error parsing precision");
        }

        return Math.pow(10,-precision);
    }

    public void generateMatrixOnPush(){
        randomSM=new SparseMatrix ();
        if(getN ()==getP ())
        randomSM.generateRandomMatrix(getP (),1);
    }

    public void loadFromFileOnPush(){
        fileSM =new SparseMatrix ();
        fileSM.loadFromFileOnlyMatrix (file);
    }
}
