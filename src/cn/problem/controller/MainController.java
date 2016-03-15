package cn.problem.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Cornelius on 28.02.2016.
 */
public class MainController {

    public void startHomework1onPush(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/hw1/main.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage hw1Stage=new Stage();
        hw1Stage.setTitle("Calcul numeric tema 1");
        assert root != null;
        hw1Stage.setScene(new Scene(root, 500, 450));
        hw1Stage.show();
    }

    public void startHomework2onPush(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/hw2/main.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage hw2Stage=new Stage();
        hw2Stage.setTitle("Calcul numeric tema 2");
        assert root != null;
        hw2Stage.setScene(new Scene(root, 700 , 450));
        hw2Stage.show();
    }

    public void startHomework3onPush(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/hw3/main.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage hw3Stage=new Stage();
        hw3Stage.setTitle("Calcul numeric tema 3");
        assert root != null;
        hw3Stage.setScene(new Scene(root, 700 , 450));
        hw3Stage.show();
    }
    public void startHomework4onPush(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/hw4/main.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage hw4Stage=new Stage();
        hw4Stage.setTitle("Calcul numeric tema 4");
        assert root != null;
        hw4Stage.setScene(new Scene(root, 700 , 450));
        hw4Stage.show();
    }
    public void startHomework5onPush(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/hw5/main.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage hw5Stage=new Stage();
        hw5Stage.setTitle("Calcul numeric tema 5");
        assert root != null;
        hw5Stage.setScene(new Scene(root, 700 , 450));
        hw5Stage.show();
    }
    public void startHomework6onPush(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/hw6/main.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage hw6Stage=new Stage();
        hw6Stage.setTitle("Calcul numeric tema 6");
        assert root != null;
        hw6Stage.setScene(new Scene(root, 700 , 450));
        hw6Stage.show();
    }
    public void startHomework7onPush(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/hw7/main.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage hw7Stage=new Stage();
        hw7Stage.setTitle("Calcul numeric tema 7");
        assert root != null;
        hw7Stage.setScene(new Scene(root, 700 , 450));
        hw7Stage.show();
    }
    public void startHomework8onPush(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/hw8/main.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage hw8Stage=new Stage();
        hw8Stage.setTitle("Calcul numeric tema 8");
        assert root != null;
        hw8Stage.setScene(new Scene(root, 700 , 450));
        hw8Stage.show();
    }

}
