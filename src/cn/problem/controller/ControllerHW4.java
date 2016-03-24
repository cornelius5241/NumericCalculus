package cn.problem.controller;

import cn.problem.data.rm.SparseMatrix;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Cornelius on 22.03.2016.
 */
public class ControllerHW4 {

    private static final File file_a = new File("E:\\FII\\CN\\NumericCalculusHW\\src\\cn\\problem\\data\\rm\\a.txt");
    private static final File file_b = new File("E:\\FII\\CN\\NumericCalculusHW\\src\\cn\\problem\\data\\rm\\b.txt");
    private static final File file_a_plus_b = new File("E:\\FII\\CN\\NumericCalculusHW\\src\\cn\\problem\\data\\rm\\aplusb.txt");
    private static final File file_a_ori_b = new File("E:\\FII\\CN\\NumericCalculusHW\\src\\cn\\problem\\data\\rm\\aorib.txt");

    SparseMatrix A;
    SparseMatrix B;
    SparseMatrix A_plus_B;
    SparseMatrix A_ori_B;

    @FXML
    TextArea outputTextArea;
    @FXML
    ProgressIndicator matrixAProgressIndicator;
    @FXML
    ProgressIndicator matrixBProgressIndicator;


    //load matrix A and B from the files
    public void loadFilesOnPush(){

        A=new SparseMatrix ();
        A.loadFromFile (file_a);
        matrixAProgressIndicator.setProgress (0.25);
        B=new SparseMatrix ();
        B.loadFromFile (file_b);
        matrixBProgressIndicator.setProgress (0.25);
        A_plus_B=new SparseMatrix ();
        A_plus_B.loadFromFile (file_a_plus_b);
        matrixAProgressIndicator.setProgress (0.77);
        matrixBProgressIndicator.setProgress (0.77);
        A_ori_B=new SparseMatrix ();
        A_ori_B.loadFromFile (file_a_ori_b);
        matrixAProgressIndicator.setProgress (1);
        matrixBProgressIndicator.setProgress (1);

        String result="Testare elemente de pe linii:\n";

        result+=("Matricea A: \n");
        result+=(A.testNrOfElements(10));
        result+=("Matricea B: \n");
        result+=(B.testNrOfElements(10));

        //outputTextArea.setText (A.simpleToString ());
        //outputTextArea.appendText (B.simpleToString ());

        outputTextArea.setText(result);

    }

    public void aPlusBOnPush(){
        SparseMatrix aplusb=SparseMatrix.sum (A,B);
        outputTextArea.setText("Rezultatul adunarii este: \n\n");
        //outputTextArea.appendText(aplusb.simpleToString ());
        outputTextArea.appendText("Rezultatul testarii este: \n\n");
        outputTextArea.setText("Rezultatul testarii pt A+B  este: "+SparseMatrix.testMatrix(aplusb,A_plus_B)+" \n\n");

    }

    public void aMulBOnPush(){
        SparseMatrix amulb=SparseMatrix.multiplication (A,B);
        outputTextArea.setText("Rezultatul inmultirii este: \n\n");
        //outputTextArea.appendText (amulb.simpleToString ());
        outputTextArea.appendText("Rezultatul testarii este: \n\n");
        outputTextArea.setText("Rezultatul testarii pt A*B  este: "+SparseMatrix.testMatrix(amulb,A_ori_B)+" \n\n");
    }

    public void aMulVOnpush(){
        ArrayList<Double> aV=SparseMatrix.mulMatrixVector(A);
        outputTextArea.setText("Rezultatul testarii pt A  este: "+SparseMatrix.testVector (A.getVector (),aV)+" \n\n");

        ArrayList<Double> bV=SparseMatrix.mulMatrixVector(B);
        outputTextArea.appendText("Rezultatul testarii pt B este: "+SparseMatrix.testVector (B.getVector (),bV)+" \n\n");

        ArrayList<Double> aplusbV=SparseMatrix.mulMatrixVector(A_plus_B);
        outputTextArea.appendText("Rezultatul testarii pt A+B este: "+SparseMatrix.testVector (A_plus_B.getVector (),aplusbV)+" \n\n");

        ArrayList<Double> aoribV=SparseMatrix.mulMatrixVector(A_ori_B);
        outputTextArea.appendText("Rezultatul testarii pt A*B este: "+SparseMatrix.testVector (A_ori_B.getVector (),aoribV)+"\n\n");
    }

}
