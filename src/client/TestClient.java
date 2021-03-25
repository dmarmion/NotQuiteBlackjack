package client;

import java.util.Deque;
import java.util.logging.Level;

import model.BlackEngineImpl;
import model.SimplePlayer;
import model.interfaces.BlackEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.BlackEngineCallbackGUI;
import view.BlackEngineCallbackImpl;

/**
 * Custom test client for A2
 */

@SuppressWarnings("unused")
public class TestClient {
   public static void main(String args[]) {
      final BlackEngine gameEngine = new BlackEngineImpl();
      
      // // create two test players
      // Player[] players = new Player[] {new SimplePlayer("2", "The Shark", 1000),
      //                                  new SimplePlayer("1", "The Loser", 500),
      //                                  new SimplePlayer("3", "The Risk-Taker", 1250)};
      // Player breaker = new SimplePlayer("3", "The Game Breaker", 750);

      // add logging callback
      gameEngine.addGameEngineCallback(new BlackEngineCallbackImpl());
      gameEngine.addGameEngineCallback(new BlackEngineCallbackGUI(gameEngine));

      // main loop to add players, place a bet and receive hand
      // for (Player player : players) {
      //    gameEngine.addPlayer(player);
      //    gameEngine.placeBet(player, 100);
      //    gameEngine.dealPlayer(player, 100);
      // }
   }

}
