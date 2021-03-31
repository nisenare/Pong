package modelo;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Pelota cuadrada para el juego de Pong
 * @author nisenare
 */
public class Pelota extends Rectangle implements IMovible {
   
   private double vx; //componente de velocidad en X
   private double vy; //componente d velocidad en Y
   private double direccion; //ángulo de direccion al iniciar
   private final Scene escena; //referencia a la escena del juego
   private final double ANGULO_MAX = 4 * Math.PI / 9; // 80 grados
   
   /**
    * La pelota es un objeto Rectangle
    * @param velocidad
    * @param escena 
    */
   public Pelota(double velocidad, Scene escena) {
      super(10, 10, Color.WHITE);
      this.escena = escena;
      setPosInicial();
      setDirInicial("L");
      setVelocidad(velocidad);
   }
   
   /**
    * Modela la física de la pelota
    */
   @Override
   public void mover() {
      double posNuevaX = getX() + vx,
            posNuevaY = getY() + vy;
      //referencias a ambos jugadores
      Rectangle jug1 = (Rectangle) ((Pane)escena.getRoot()).
      getChildren().get(0);
      Rectangle jug2 = (Rectangle) ((Pane)escena.getRoot()).
      getChildren().get(1);
      
      //cuando la pelota llega a los bordes laterales
      if (getBoundsInLocal().getMinX() < 0) {
         vx = Math.sqrt(vx * vx + vy * vy);
         vy = 0;
         ((Jugador)jug2).addPunto(); //gana jugador 2
         setPosInicial();
         return;
      } else if (getBoundsInLocal().getMaxX() >= escena.getWidth()) {
         vx = -Math.sqrt(vx * vx + vy * vy);
         vy = 0;
         ((Jugador)jug1).addPunto(); //gana jugador 1
         setPosInicial();
         return;
      }
      
      //cuando toca los bordes de arriba y abajo
      if (getBoundsInLocal().getMinY() < 0) {
         posNuevaY *= -1;
         vy *= -1;
      } else if (getBoundsInLocal().getMaxY() >= escena.getHeight()) {
         posNuevaY -= 2 * ((posNuevaY + getHeight()) - escena.getHeight());
         vy *= -1;
      }
      
      //comportamiento de la pelota al tocar las raquetas
      double interX, interY, //intersecciones en X e Y
            distCentro, velActual,
            nuevoAngulo;
      if (intersects(jug1.getBoundsInLocal())) { //raqueta izquierda
         interX = jug1.getBoundsInLocal().getMaxX();
         interY = getBoundsInLocal().getCenterY();
         distCentro = jug1.getBoundsInLocal().getCenterY() - interY;
         distCentro = distCentro / (jug1.getHeight() / 2 + getHeight());
         velActual = Math.sqrt(vx * vx + vy * vy);
         nuevoAngulo = distCentro * ANGULO_MAX;
         vx = velActual * Math.cos(nuevoAngulo);
         vy = velActual * -Math.sin(nuevoAngulo);
         posNuevaX = interX + velActual * Math.cos(nuevoAngulo);
         posNuevaY = interY - getHeight() / 2 + velActual * Math.sin(nuevoAngulo);
      } else if (intersects(jug2.getBoundsInLocal())) { //raqueta derecha
         interX = jug2.getX();
         interY = getBoundsInLocal().getCenterY();
         distCentro = jug2.getBoundsInLocal().getCenterY() - interY;
         distCentro = distCentro / (jug2.getHeight() / 2 + getHeight());
         velActual = Math.sqrt(vx * vx + vy * vy);
         nuevoAngulo = distCentro * ANGULO_MAX;
         vx = -velActual * Math.cos(nuevoAngulo);
         vy = -velActual * Math.sin(nuevoAngulo);
         posNuevaX = interX - getWidth() - velActual * Math.cos(nuevoAngulo);
         posNuevaY = interY - getHeight() / 2 - velActual * Math.sin(nuevoAngulo);
      }
      setX(posNuevaX);
      setY(posNuevaY);
   }
   
   private void setDirInicial(String lado) {
      switch (lado) {
         case "L":
            direccion = Math.PI;
            break;
         case "R":
            direccion = 0;
         default:
            break;
      }
   }
   
   private void setVelocidad(double velocidad) {
      vx = velocidad * Math.cos(direccion);
      vy = velocidad * Math.sin(direccion);
   }
   
   private void setPosInicial() {
      setY(escena.getHeight() / 2 - getHeight() / 2);
      setX(escena.getWidth() / 2 - getWidth() / 2);
   }
}
