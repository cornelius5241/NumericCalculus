package cn.problem.controller;

import cn.problem.data.rm.SparseMatrix;
import cn.problem.data.rmr.LiniarSystem;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Cornelius on 29.03.2016.
 */
public class ControllerHW5 {

    private static final File sys_file_1 = new File("E:\\FII\\CN\\NumericCalculusHW\\src\\cn\\problem\\data\\rmr\\m_rar_2016_1.txt");
    private static final File sys_file_2 = new File("E:\\FII\\CN\\NumericCalculusHW\\src\\cn\\problem\\data\\rmr\\m_rar_2016_2.txt");
    private static final File sys_file_3 = new File("E:\\FII\\CN\\NumericCalculusHW\\src\\cn\\problem\\data\\rmr\\m_rar_2016_3.txt");
    private static final File sys_file_4 = new File("E:\\FII\\CN\\NumericCalculusHW\\src\\cn\\problem\\data\\rmr\\m_rar_2016_4.txt");

    SparseMatrix system1;
    SparseMatrix system2;
    SparseMatrix system3;
    SparseMatrix system4;

    ArrayList<Double> result_1;
    ArrayList<Double> result_2;
    ArrayList<Double> result_3;
    ArrayList<Double> result_4;

    @FXML
    TextArea outputTextArea;
    @FXML
    ProgressIndicator system1ProgressIndicator;
    @FXML
    ProgressIndicator system2ProgressIndicator;
    @FXML
    ProgressIndicator system3ProgressIndicator;
    @FXML
    ProgressIndicator system4ProgressIndicator;

    public void loadFilesOnPush() {
        system1ProgressIndicator.setProgress (0.25);
        system1=new SparseMatrix ();
        system1ProgressIndicator.setProgress (0.50);
        system1.loadFromFile (sys_file_1);
        system1ProgressIndicator.setProgress (0.75);

        system2ProgressIndicator.setProgress (0.25);
        system2=new SparseMatrix ();
        system2ProgressIndicator.setProgress (0.50);
        system2.loadFromFile (sys_file_2);
        system2ProgressIndicator.setProgress (0.75);

        system3ProgressIndicator.setProgress (0.25);
        system3=new SparseMatrix ();
        system3ProgressIndicator.setProgress (0.50);
        system3.loadFromFile (sys_file_3);
        system3ProgressIndicator.setProgress (0.75);

        system4ProgressIndicator.setProgress (0.25);
        system4=new SparseMatrix ();
        system4ProgressIndicator.setProgress (0.50);
        system4.loadFromFile (sys_file_4);
        system4ProgressIndicator.setProgress (0.75);

        system1ProgressIndicator.setProgress (1);
        system2ProgressIndicator.setProgress (1);
        system3ProgressIndicator.setProgress (1);
        system4ProgressIndicator.setProgress (1);
    }

    public void checkMatrixOnPush(){
        if(LiniarSystem.testFirstDiag (system1)){
            outputTextArea.setText ("Testat matricea din primul sistem: Rezultat pozitiv.\n");
        }
        else outputTextArea.setText ("Testat matricea din primul sistem: Rezultat negativ.\n");

        if(LiniarSystem.testFirstDiag (system2)){
            outputTextArea.appendText ("Testat matricea din al doilea sistem: Rezultat pozitiv.\n");
        }
        else outputTextArea.appendText ("Testat matricea din al doilea sistem: Rezultat negativ.\n");

        if(LiniarSystem.testFirstDiag (system3)){
            outputTextArea.appendText ("Testat matricea din al treilea sistem: Rezultat pozitiv.\n");
        }
        else outputTextArea.appendText ("Testat matricea din al treilea sistem: Rezultat negativ.\n");

        if(LiniarSystem.testFirstDiag (system4)){
            outputTextArea.appendText ("Testat matricea din al patrulea sistem: Rezultat pozitiv.\n");
        }
        else outputTextArea.appendText ("Testat matricea din al patrulea sistem: Rezultat negativ.\n");



    }

    public void approxSolOnPush(){
        result_1=LiniarSystem.SOR (system1);
        outputTextArea.setText ("Calculat solutia primului sistem.\n");
        result_2=LiniarSystem.SOR (system2);
        outputTextArea.appendText ("Calculat solutia celui de-al doilea sistem.\n");
        result_3=LiniarSystem.SOR (system3);
        outputTextArea.appendText ("Calculat solutia celui de-al treilea sistem.\n");
        result_4=LiniarSystem.SOR (system4);
        outputTextArea.appendText ("Calculat solutia celui de-al patrulea sistem.\n");
    }

    public void checkSolutionOnPush(){
        double result;

        result=LiniarSystem.checkSolution (system1,result_1);
        outputTextArea.setText ("Calculat norma primului sistem: "+result+"\n");

        result=LiniarSystem.checkSolution (system2,result_2);
        outputTextArea.appendText ("Calculat norma celui de-al doilea sistem: "+result+"\n");

        result=LiniarSystem.checkSolution (system3,result_3);
        outputTextArea.appendText ("Calculat norma celui de-al treilea sistem: "+result+"\n");

        result=LiniarSystem.checkSolution (system4,result_4);
        outputTextArea.appendText ("Calculat norma celui de-al patrulea sistem: "+result+"\n");
    }

}
