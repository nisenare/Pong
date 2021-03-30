package modelo;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Jugador del juego Pong es un rectángulo de color 
 * @author nisenare
 */
public class Jugador extends Rectangle implements IMovible {
   
   private int puntaje; //empieza en 0 (duh)
   private double vy; //velocidad en y
   private final Scene escena; //referencia a la escena principal
   private final TipoJugador tipo;
   
   public enum TipoJugador {
      J1,
      J2,
      CPU // aun en desuso
   }
   /**
    * Un objeto Jugador es un Rectangle
    * @param tipo J1, J2, CPU (determina el lado del jugador)
    * @param escena del juego
    */
   public Jugador(TipoJugador tipo, Scene escena) {
      super(10, 60, Color.WHITE);
      this.tipo = tipo;
      this.escena = escena;
      puntaje = 0;
      vy = 0;
      setSprite();
   }
   
   /**
    * Devuelve el puntaje actual del jugador
    * @return puntaje
    */
   public int getPuntaje() {
      return puntaje;
   }
   
   /**
    * Un objeto Jugador es un Rectangle
    * @param ancho del rectangle del jugador
    * @param alto del rectangle del jugador
    * @param tipo J1, J2, CPU (determina el lado del jugador)
    */
   public Jugador(int ancho, int alto, TipoJugador tipo, Scene escena) {
      super(ancho, alto, Color.WHITE);
      this.tipo = tipo;
      this.escena = escena;
      puntaje = 0;
      vy = 0;
      setSprite();
   }
   
   private void setSprite() {
      switch (tipo) {
         case J1:
            setX(20);
            break;
         case J2:
            setX(escena.getWidth() - 20 - getWidth());
            break;
         default:
            break;
      }
      setY(escena.getHeight() / 2 - getHeight() / 2);
   }
   
   /**
    * Setea la velocidad vertical
    * @param vy en píxeles
    */
   public void setAvanceY(int vy) {
      this.vy = vy;
   }
   
   /**
    * Mueve al jugador a velocidad actualmente seteada
    */
   public void mover() {
      setY(getY() + vy);
   }
   
   /**
    * Aumenta el puntaje en 1
    */
   public void addPunto() {
      puntaje += 1;
   }
   
   /**
    * Devuelve el puntaje a 0
    */
   public void resetPuntaje() {
      puntaje = 0;
   }
}