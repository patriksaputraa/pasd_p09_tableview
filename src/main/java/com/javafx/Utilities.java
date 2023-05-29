package com.javafx;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Utilities {
    static boolean confirmationAlert(String title, String head, String content) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(head);
        alert.setContentText(content);
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            System.out.println("Pengguna setuju...");
            return true;
        } else {
            System.out.println("Pengguna menolak...");
            return false;
        }
    }

    static boolean informationAlert(String title, String head, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(head);
        alert.setContentText(content);
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            System.out.println("Pengguna setuju...");
            return true;
        } else {
            System.out.println("Pengguna menolak...");
            return false;
        }
    }

    static void warningAlert(String title, String head, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(head);
        alert.setContentText(content);
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            System.out.println("Pengguna setuju...");
        } else {
            System.out.println("Pengguna menolak...");
        }
    }
}
