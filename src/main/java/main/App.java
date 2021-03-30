package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Pong;

/**
 * JavaFX App
 */
public class App extends Application {
   
   Button saludo;
   VBox raiz;
   @Override
   public void start(Stage stage) {
      raiz = new VBox();
      saludo = new Button("Play");
      accionSaludo(stage);
      raiz.getChildren().add(saludo);
      raiz.setAlignment(Pos.CENTER);
      Scene escena = new Scene(raiz, 400, 400);
      stage.setTitle("Pong");
      stage.setScene(escena);
      stage.setScene(escena);
      stage.show();
   }

   public static void main(String[] args) {
      launch(args);
   }
   
   private void accionSaludo(Stage principal) {
      saludo.setOnAction((ActionEvent e) -> {
         new Pong(principal);
      });
   }

}