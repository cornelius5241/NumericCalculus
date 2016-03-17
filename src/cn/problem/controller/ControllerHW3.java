package cn.problem.controller;

import cn.problem.data.Gauss;
import cn.problem.data.NumericCalculus;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Created by Cornelius on 14.03.2016.
 */
public class ControllerHW3 {

    private double matrix[][];
    private double matrix_bar[][];
    private double inverse[][];

    @FXML
    TextArea outputTextArea;
    @FXML
    TextField systemDimensionTextField;
    @FXML
    TextField systemPrecisionTextField;

    private int getSysDimension(){
        int dimension=10;

        try{
            dimension=Integer.parseInt(systemDimensionTextField.getText());
        }catch (Exception e){
            System.out.println("[Log "+System.currentTimeMillis()+"][HW1] Vector - error parsing dimension");
        }
        return dimension;
    }

    private double getEpsilon(){
        int precision=10;
        try{
            precision=Integer.parseInt(systemPrecisionTextField.getText());
        }catch (Exception e){
            System.out.println("[Log "+System.currentTimeMillis()+"][HW1] Vector - error parsing precision");
        }

        return Math.pow(10,-precision);
    }

    public void randomOnPushButton(){
        matrix = NumericCalculus.randomMatrix(getSysDimension(),getSysDimension());
        outputTextArea.setText("Matricea A: \n"+NumericCalculus.simpleToString(matrix)+"\n");
    }

    public void startGaussianOnPush(){
//        matrix=new double[][]{
//                {1.0,0.0,2.0},
//                {0.0,1.0,0.0},
//                {1.0,1.0,1.0}
//        };
        double matrix_cpy[][]=new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, matrix_cpy[i], 0, matrix.length);
        }
        matrix_bar=Gauss.getBarredMatrix(matrix_cpy);
        outputTextArea.appendText("Matricea A barata: \n"+NumericCalculus.simpleToString(matrix_bar)+"\n");
        if(Gauss.testSingularMatrix(matrix_cpy,matrix_bar,getEpsilon())){
            outputTextArea.appendText("Matricea A este singulara.\n");
        }
        else {
            outputTextArea.appendText("Matricea A nu este singulara.\n");
            inverse=Gauss.findInverseMatrix(matrix_bar);
            outputTextArea.appendText("Inversa matricii A : \n"+NumericCalculus.simpleToString(inverse)+"\n");
            outputTextArea.appendText("Determinantul matricei A: "+Gauss.calcualteDeterminant(matrix,matrix.length)+"\n");
            outputTextArea.appendText("Norma matricii A : \n"+Gauss.calculateCorrectness(matrix,inverse)+"\n");

        }
    }

    public void calculateDeterminantOnPush(){

    }

    public void verifyCorrectnessOnPush(){

    }

}
