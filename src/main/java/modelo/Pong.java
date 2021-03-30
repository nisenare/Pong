package modelo;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.Jugador.TipoJugador;

/**
 *
 * @author nisenare
 */
public class Pong {
   
   private Scene escena;
   private Pane raiz;
   private Jugador jugador1;
   private Jugador jugador2;
   private Pelota pelota;
   private Marcador marcador;
   private MoviblesTimer timer1,
         timer2, timer3, timer4;
   
   public Pong(Stage principal) {
      //ventana
      principal.setTitle("Pong");
      principal.setResizable(false);
      raiz = new Pane();
      raiz.setStyle("-fx-background-color: #0800FF;"); //CSS
      escena = new Scene(raiz, 500, 500);
      escena.setOnKeyReleased(teclaSoltada);
      escena.setOnKeyPressed(teclaOprimida);
      principal.setScene(escena);
      principal.show();
      
      //juego
      newJuego();
   }
   
   /**
    * Setea los objetos desde el inicio
    */
   private void newJuego() {
      jugador1 = new Jugador(TipoJugador.J1, escena);
      jugador2 = new Jugador(TipoJugador.J2, escena);
      pelota = new Pelota(8, escena);
      marcador = new Marcador(escena);
      raiz.getChildren().clear(); // en este orden
      raiz.getChildren().addAll(jugador1, jugador2, pelota, marcador);
      timer1 = new MoviblesTimer(jugador1);
      timer2 = new MoviblesTimer(jugador2);
      timer3 = new MoviblesTimer(pelota);
      timer4 = new MoviblesTimer(marcador);
      startTimers();
   }
   
   private void pausaJuego() {
      if (timer1.isPausado() && timer2.isPausado()
         && timer3.isPausado() && timer4.isPausado()) {
         startTimers();
      } else {
         stopTimers();
      }
   }
   
   private void resetJuego() {
      stopTimers(); //que el recolector de basura se apiado de estos
      newJuego();
   }
   
   private void startTimers() {
      timer1.start();
      timer2.start();
      timer3.start();
      timer4.start();
   }
   
   private void stopTimers() {
      timer1.stop();
      timer2.stop();
      timer3.stop();
      timer4.stop();
   }
   
   // -------- EVENTOS --------
   private EventHandler<KeyEvent> teclaSoltada = new EventHandler<KeyEvent>() {
     @Override
      public void handle(KeyEvent e) {
         switch (e.getCode()) {
            case W:
            case S:
               jugador1.setAvanceY(0);
               break;
            case UP:
            case DOWN:
               jugador2.setAvanceY(0);
               break;
            default:
               break;
      }
     }
   };
   
   private EventHandler<KeyEvent> teclaOprimida = new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent e) {
         switch(e.getCode()) {
            case W:
               jugador1.setAvanceY(-8);
               break;
            case S:
               jugador1.setAvanceY(8);
               break;
            case UP:
               jugador2.setAvanceY(-8);
               break;
            case DOWN:
               jugador2.setAvanceY(8);
               break;
            case P:
               pausaJuego();
               break;
            case R:
               resetJuego();
               break;
            default:
               break;
         }
      }
   };
}
