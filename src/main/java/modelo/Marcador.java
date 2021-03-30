package modelo;

import java.io.InputStream;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Toma la cuenta de los puntajes de los jugadores
 * @author nicos
 */
public class Marcador extends StackPane implements IMovible {
   private Scene escena; //REFERENCIA
   private Text texto;
   private Font fuente;
   
   /**
    * Marcador del juego Pong
    * @param escena 
    */
   public Marcador(Scene escena) {
      this.escena = escena; //new FileInputStream("src/main/java/resources/bit5x3.ttf")
      try  {
         InputStream in = getClass().getResourceAsStream("/bit5x3.ttf");
         fuente = Font.loadFont(in, 45);
         in.close();
      } catch (Exception ex) {
         fuente = Font.font("Verdana", 45);
      }
      setWidth(110);
      setHeight(30);
      texto = new Text();
      texto.setText(0 + "   " + 0);
      texto.setFont(fuente);
      texto.setTextAlignment(TextAlignment.CENTER);
      texto.setFill(Color.WHITE);
      setLayoutX(escena.getWidth() / 2 - getWidth() / 2);
      setLayoutY(20);
      getChildren().add(texto);
   }
   
   /**
    * Comportamiento del marcador
    */
   public void mover() {
      int puntaje1 = ((Jugador) ((Pane)escena.getRoot()).
      getChildren().get(0)).getPuntaje();
      int puntaje2 = ((Jugador) ((Pane)escena.getRoot()).
      getChildren().get(1)).getPuntaje();
      
      if (puntaje1 < 10) {
         texto.setText(puntaje1 + "   " + puntaje2);
      } else if (puntaje1 >= 10 || puntaje2 >= 10) {
         texto.setText(puntaje1 + "  " + puntaje2);
      } else if (puntaje1 >= 100 || puntaje2 >= 100) {
         ((Jugador) ((Pane)escena.getRoot()).
         getChildren().get(0)).resetPuntaje();
         ((Jugador) ((Pane)escena.getRoot()).
         getChildren().get(1)).resetPuntaje();
      }
   }
}
