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
   
   /**
    * Inicia una cantidad indefinida de timers
    * @param timers 
    */
   public static void startTimers(AnimationTimer... timers) {
      for (AnimationTimer t : timers) {
         t.start();
      }
   }
   
   /**
    * Para una cantidad indefinida de timers
    * @param timers
    */
   public static void stopTimers(AnimationTimer... timers) {
      for (AnimationTimer t : timers) {
         t.stop();
      }
   }
   
   /**
    * Retorna true si todos los timers están pausados. False si al menos uno no.
    * @param timers
    * @return boolean
    */
   public static boolean estanPausados(AnimationTimer... timers) {
      boolean retorno = true;
      for (AnimationTimer t : timers) {
         if (!((MoviblesTimer)t).isPausado())
            retorno = false;
      }
      return retorno;
   }
}