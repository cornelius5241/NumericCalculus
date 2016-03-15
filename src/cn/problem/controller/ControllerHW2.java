package cn.problem.controller;

import cn.problem.data.NumericCalculus;
import cn.problem.data.QR;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Jama.*;
import org.jboss.weld.util.collections.Arrays2;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.channels.NotYetBoundException;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by Cornelius on 01.03.2016.
 */
public class ControllerHW2 {

    @FXML
    TextField systemDimensionTextField;
    @FXML
    TextField systemPrecisionTextField;
    @FXML
    TextArea vectorOutputTextArea;

    private double matrix_A[][];
    private double vector_s[];
    private double vector_b[];
    private double matrix_Q[][];
    private Matrix liniar_solution_QR;
    private double user_QR_solution[];

    public void startVectorOnPush(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/hw2/vector.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage vectorStage=new Stage();
        vectorStage.setTitle("Calcul numeric tema 2 problema 1");
        assert root != null;
        vectorStage.setScene(new Scene(root, 700 , 450));
        vectorStage.show();
    }

    public void startQROnPush(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/hw2/qrdesc.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage qrStage=new Stage();
        qrStage.setTitle("Calcul numeric tema 2 problema 2");
        assert root != null;
        qrStage.setScene(new Scene(root, 700 , 450));
        qrStage.show();
    }

    public void startLiniarSysOnPush(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/hw2/liniarSystem.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage qrStage=new Stage();
        qrStage.setTitle("Calcul numeric tema 2 problema 3");
        assert root != null;
        qrStage.setScene(new Scene(root, 700 , 450));
        qrStage.show();
    }

    public void startErrorsOnPush(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/hw2/errors.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage qrStage=new Stage();
        qrStage.setTitle("Calcul numeric tema 2 problema 4");
        assert root != null;
        qrStage.setScene(new Scene(root, 700 , 450));
        qrStage.show();
    }

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

    public void calculateVectorOnPush(){

        vector_b=QR.mulMatrixVector(matrix_A,vector_s);

        vectorOutputTextArea.appendText("\n\nVectorul b rezultat este:    \n");
        for (double aVector_b : vector_b) {
            vectorOutputTextArea.appendText(String.valueOf(aVector_b) + " ;  ");
        }
    }

    public void calculateQRDescOnPush(){

        vector_b= QR.mulMatrixVector(matrix_A,vector_s);
        matrix_Q= QR.calculateQR(matrix_A,vector_b,getEpsilon());
        vectorOutputTextArea.setText("Matricea Q: \n"+NumericCalculus.simpleToString(matrix_Q)+"\n");
    }

    public void calculateLiniarSystemOnPush(){

        double time_result_lib=0,time_result,start_lib_time=0,finish_lib_time,start_user_time=0,finnish_user_time;

        vector_b=QR.mulMatrixVector(matrix_A,vector_s);
        liniar_solution_QR=QR.calculateLiniarSystemQRlibrary(matrix_A,vector_b);
        //vectorOutputTextArea.appendText("\n\nSolutia folosind biblioteca VAMA:\n\n");
        //for (int i = 0; i < liniar_solution_QR.getRowDimension(); i++) {
            //vectorOutputTextArea.appendText(String.valueOf(liniar_solution_QR.get(i,0))+"  ;  ");
        //}

        if(getSysDimension()>=250)
        {
            start_lib_time=System.currentTimeMillis();
        }
        matrix_Q=QR.calculateQR(matrix_A,vector_b,getEpsilon());
        //vectorOutputTextArea.appendText("\n\nMatricea Q: \n"+NumericCalculus.simpleToString(matrix_Q)+"\n");

        if(getSysDimension()>=250)
        {
            finish_lib_time=System.currentTimeMillis();
            start_user_time=System.currentTimeMillis();
            time_result_lib=finish_lib_time-start_lib_time;
        }
        user_QR_solution=QR.calculateLiniarSystemQR(matrix_A,vector_b);
        //vectorOutputTextArea.appendText("\n\nSolutia 2:\n\n");
        //for (int i = 0; i < liniar_solution_QR.getRowDimension(); i++) {
            //vectorOutputTextArea.appendText(String.valueOf(user_QR_solution[i]+"  ;  "));
        //}

        if(getSysDimension()>=250)
        {
            finnish_user_time=System.currentTimeMillis();
            time_result=finnish_user_time-start_user_time;
            vectorOutputTextArea.appendText("\n\n\n Time results:\n\n Lib JAMA: "+String.valueOf(time_result_lib)+"\nUser: "+String.valueOf(time_result));
        }
    }


    public void randomOnPushButton(){
        matrix_A = NumericCalculus.randomMatrix(getSysDimension(),getSysDimension());
        vector_s = NumericCalculus.randomMatrix(1,getSysDimension())[0];

        //vectorOutputTextArea.setText("Matricea A: \n"+NumericCalculus.simpleToString(matrix_A)+"\n");

        //vectorOutputTextArea.appendText("\n\nVectorul s:    \n");
        //for (int i = 0; i < vector_s.length; i++) {
            //vectorOutputTextArea.appendText(String.valueOf(vector_s[i])+"  ;  ");
        //}

    }

    public void fromFileOnPushButton(){

    }

    public void calculateErrorsOnPush(){
        double time_result_lib,time_result,start_lib_time,finish_lib_time,start_user_time,finnish_user_time;

        matrix_A = NumericCalculus.randomMatrix(getSysDimension(),getSysDimension());
        vector_s = NumericCalculus.randomMatrix(1,getSysDimension())[0];
        vector_b = QR.mulMatrixVector(matrix_A,vector_s);
        double[][] A_init=new double[getSysDimension()][getSysDimension()];
        double[] b_init=new double[getSysDimension()];
        //double[] s_init=new double[getSysDimension()];
        for (int i = 0; i < getSysDimension(); i++) {
            //s_init[i]=vector_s[i];
            b_init[i]=vector_b[i];
            System.arraycopy(matrix_A[i], 0, A_init[i], 0, getSysDimension());
        }


        start_lib_time=System.currentTimeMillis();
        liniar_solution_QR=QR.calculateLiniarSystemQRlibrary(matrix_A,vector_b);
        finish_lib_time=System.currentTimeMillis();
        time_result_lib=finish_lib_time-start_lib_time;

        start_user_time=System.currentTimeMillis();
        matrix_Q=QR.calculateQR(matrix_A,vector_b,getEpsilon());
        user_QR_solution=QR.calculateLiniarSystemQR(matrix_A,vector_b);
        finnish_user_time=System.currentTimeMillis();
        time_result=finnish_user_time-start_user_time;

        vectorOutputTextArea.setText("Eroarea 1: \n"+String.valueOf(QR.firstError(A_init,user_QR_solution,b_init))+"\n\n");
        vectorOutputTextArea.appendText("Eroarea 2: \n"+String.valueOf(QR.secondError(A_init,liniar_solution_QR,b_init))+"\n\n");
        vectorOutputTextArea.appendText("Eroarea 3: \n"+String.valueOf(QR.thirdError(user_QR_solution,vector_s))+"\n\n");
        vectorOutputTextArea.appendText("Eroarea 4: \n"+String.valueOf(QR.fourthError(liniar_solution_QR,vector_s))+"\n\n");

        vectorOutputTextArea.appendText("\n\n\n Time results:\n\n Lib JAMA: "+String.valueOf(time_result_lib)+"\nUser: "+String.valueOf(time_result));

    }
}
