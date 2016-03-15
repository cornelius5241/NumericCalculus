package cn.problem.controller;

import cn.problem.data.NumericCalculus;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class ControllerHW1 {

    @FXML
    TextField firstProblemResultTextField;
    @FXML
    TextField secondProblemSumResultTextField;
    @FXML
    TextField secondProblemMulResultTextField;
    @FXML
    TextField thirdProblemResultTextField;
    @FXML
    TextField multiplicationArg1;
    @FXML
    TextField multiplicationArg2;
    @FXML
    TextField multiplicationArg3;
    @FXML
    ScrollPane imagineScrollPane;
    @FXML
    TextField tanArg1;
    @FXML
    TextField tanArg2;
    @FXML
    TextField rowsTextField;
    @FXML
    TextField columnsTextField;
    @FXML
    TextArea resultTextArea;

    private Stage fileMatrixStage;

    public void precisionButtonOnPush(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/hw1/precision.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage precisionStage=new Stage();
        precisionStage.setTitle("Calcul numeric tema 1 problema 1");
        precisionStage.setScene(new Scene(root, 700 , 450));
        precisionStage.show();
    }

    public void associativityButtonOnPush(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/hw1/associativity.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage associativityStage=new Stage();
        associativityStage.setTitle("Calcul numeric tema 1 problema 2");
        associativityStage.setScene(new Scene(root, 700 , 650));
        associativityStage.show();
    }

    public void tanApproximationButtonOnPush(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/hw1/tanApproximation.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage tanApproximationStage=new Stage();
        tanApproximationStage.setTitle("Calcul numeric tema 1 problema 3");
        tanApproximationStage.setScene(new Scene(root, 700, 800));

        tanApproximationStage.show();

    }

    public void fileMatrixButtonOnPush(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/hw1/fileMatrix.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileMatrixStage=new Stage();
        fileMatrixStage.setTitle("Calcul numeric tema 1 problema 4");
        fileMatrixStage.setScene(new Scene(root, 550 , 450));

        fileMatrixStage.show();

    }

    public void startPrecision(){
        String result = Double.toString(NumericCalculus.calculatePrecision());
        firstProblemResultTextField.setText(result);

    }

    public void startAssociativitySum(){
        String result = NumericCalculus.sumAssociativity();
        secondProblemSumResultTextField.setText(result);
    }

    public void startAssociativityMultiplication(){
        String result = NumericCalculus.mulAssociativity(Double.parseDouble(multiplicationArg1.getText()), Double.parseDouble(multiplicationArg2.getText()), Double.parseDouble(multiplicationArg3.getText()));
        secondProblemMulResultTextField.setText(result);
    }

    public void startTanApproximationProblem(){

        String result= NumericCalculus.tanApproximation(parseTanArgument(),(Double.parseDouble(tanArg2.getText())));
        thirdProblemResultTextField.setText(result);
    }

    private double parseTanArgument(){
        if(tanArg1.getText().matches("[pP][iI]")) {
            return Math.PI;
        }
        else if(tanArg1.getText().matches("-[pP][iI]")){
            return -Math.PI;
        }
        else {
            if(Pattern.matches("[pP][iI][/*][1234567890]+",tanArg1.getText() )){
                if(tanArg1.getText().charAt(2)=='/'){
                    return Math.PI/Double.parseDouble(tanArg1.getText().substring(3));
                }
                else {
                    return Math.PI*Double.parseDouble(tanArg1.getText().substring(3));
                }
            }else
                return Double.parseDouble(tanArg1.getText());
        }
    }

    public void createRandomMatrix(){
        int rows=0;
        int columns=0;
        try{
            rows=Integer.parseInt(rowsTextField.getText());
            columns=Integer.parseInt(columnsTextField.getText());
        }
        catch (Exception e){
            System.out.println("[Log "+System.currentTimeMillis()+"Error parsing size of matrix.");
        }

        resultTextArea.setText(NumericCalculus.createRandomMatrix(rows,columns));
    }

    public void saveMatrixToFile(){
        if(resultTextArea.getText()!=""){
            File file;

            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);

            file = fileChooser.showSaveDialog(fileMatrixStage);

            if(file != null){
                SaveFile(resultTextArea.getText(), file);
            }
        }
    }

    public void loadMatrixFromFile(){
        double[][] result=null;
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Open Resource File");
        File file =fileChooser.showOpenDialog(fileMatrixStage);
        if (file != null){
            result=NumericCalculus.fileParsing(file);
            resultTextArea.setText(NumericCalculus.simpleToString(result));
        }
    }

    private void SaveFile(String content, File file){
        try {
            FileWriter fileWriter = null;
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println("[Log "+System.currentTimeMillis()+"Error IOException file."+file.getName());
        }

    }

}
