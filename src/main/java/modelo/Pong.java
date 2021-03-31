package modelo;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.Jugador.TipoJugador;
import static modelo.MoviblesTimer.*;

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
   
   /**
    * Juego que requiere referencia al stage principal
    * @param principal 
    */
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
      startTimers(timer1, timer2, timer3, timer4);
   }
   
   private void pausaJuego() {
      if (estanPausados(timer1, timer2, timer3, timer4))
         startTimers(timer1,timer2,timer3,timer4);
      else
         stopTimers(timer1,timer2,timer3,timer4);
   }
   
   private void resetJuego() {
      stopTimers(timer1, timer2, timer3, timer4); //que el recolector de basura se apiado de estos
      newJuego();
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
