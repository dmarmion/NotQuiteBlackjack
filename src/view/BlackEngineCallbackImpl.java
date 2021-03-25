package view;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.BlackEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.BlackEngineCallback;

/**
 * 
 * Skeleton/Partial example implementation of GameEngineCallback showing Java logging behaviour
 * 
 * @author Caspar Ryan
 * @see view.interfaces.BlackEngineCallback
 * 
 */
public class BlackEngineCallbackImpl implements BlackEngineCallback
{
   public static final Logger logger = Logger.getLogger(BlackEngineCallbackImpl.class.getName());

   // utility method to set output level of logging handlers
   public static Logger setAllHandlers(Level level, Logger logger, boolean recursive)
   {
      // end recursion?
      if (logger != null)
      {
         logger.setLevel(level);
         for (Handler handler : logger.getHandlers())
            handler.setLevel(level);
         // recursion
         setAllHandlers(level, logger.getParent(), recursive);
      }
      return logger;
   }

   public BlackEngineCallbackImpl()
   {
      // NOTE can also set the console to FINE in %JRE_HOME%\lib\logging.properties
      setAllHandlers(Level.INFO, logger, true); // modified from A1 submission
   }

   @Override
   public void nextCard(Player player, PlayingCard card, BlackEngine engine) {
      // intermediate results logged at Level.FINE
      logger.log(Level.INFO, String.format("Card Dealt to %s .. %s", player.getPlayerName(), card.toString()));
      // Note: this logging level was changed to INFO (from FINE as it was in my A1 submission)
   }

   public void bustCard(Player player, PlayingCard card, BlackEngine engine) {
	   logger.log(Level.INFO,
			   String.format("Card Dealt to %s .. %s ... YOU BUSTED!", player.getPlayerName(), card.toString()));
	// Note: this logging level was changed to INFO (from FINE as it was in my A1 submission)
   }
   
   @Override
   public void result(Player player, int result, BlackEngine engine)
   {
      // final results logged at Level.INFO
      logger.log(Level.INFO, String.format("%s, final result=%d", player.getPlayerName(), result));
   }

   public void nextHouseCard(PlayingCard card, BlackEngine engine) {
	   logger.log(Level.INFO, String.format("Card Dealt to House .. %s", card.toString()));
	// Note: this logging level was changed to INFO (from FINE as it was in my A1 submission)
   }
   
   public void houseBustCard(PlayingCard card, BlackEngine engine) {
	   logger.log(Level.INFO, String.format("Card Dealt to House .. %s ... HOUSE BUSTED!", card.toString()));
	// Note: this logging level was changed to INFO (from FINE as it was in my A1 submission)
   }
   
   public void houseResult(int result, BlackEngine engine) {
	   logger.log(Level.INFO, String.format("House, final result=%d", result));
	   logger.log(Level.INFO, String.format("Final Player Results%s", playersAsString(engine)));
   }
   
   private String playersAsString(BlackEngine engine) {
	   String result = "";
	   
	   for (Player player : engine.getAllPlayers()) {
		   result += "\n";
		   result += player.toString();
	   }
	   return result;
   }

}
