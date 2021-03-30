package modelo;

import javafx.animation.AnimationTimer;

/**
 * AnimationTimer para que los elementos del Pong puedan moverse al mismo tiempo
 * @author nisenare
 */
public class MoviblesTimer extends AnimationTimer {
   
   private final IMovible objeto;
   private volatile boolean pausado;
   
   /**
    * Mueve objetos a 60fps
    * @param objeto a mover
    */
   public MoviblesTimer(IMovible objeto) {
      this.objeto = objeto;
   }
   
   @Override
   public void handle(long now) {
      objeto.mover();
   }
   
   @Override
   public void start() {
      super.start();
      pausado = false;
   }
   
   @Override
   public void stop() {
      super.stop();
      pausado = true;
   }
   
   /**
    * ¿Está pausado el AnimationTimer?
    * @return true si está parada la animación
    */
   public boolean isPausado() {
      return pausado;
   }
   
}