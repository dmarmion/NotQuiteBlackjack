package model.interfaces;

import java.util.Collection;
import java.util.Deque;

import view.interfaces.BlackEngineCallback;

/**
 * Assignment interface for FP providing main Card Game model functionality
 * 
 * @author Caspar Ryan
 * 
 */
public interface BlackEngine
{
   public static final int BUST_LEVEL = 42;

   /**
    * <pre>Deal cards to the player as follows
    * 
    * 1. deal a card to the player
    * 2. call {@link view.interfaces.BlackEngineCallback#nextCard(Player, PlayingCard , BlackEngine)}
    * 3. continue looping until the player busts (default value of GameEngine.BUST_TOTAL=42)
    * 4. call {@link view.interfaces.BlackEngineCallback#bustCard(Player, PlayingCard, BlackEngine)}
    * 5. call {@link view.interfaces.BlackEngineCallback#result(Player, int, BlackEngine)}
    *    with final result for player (the pre bust total) 
    * 6. update the player with final result so it can be retrieved later
    * 
    * @param player
    *            the current player who will have their result set at the end of the hand
    * @param delay
    *            the delay between cards being dealt (in milliseconds (ms))
    *            
    * @throws IllegalArgumentException thrown when delay param is {@literal <} 0 or {@literal >} 1000
    * </pre>
    */
   public abstract void dealPlayer(Player player, int delay) throws IllegalArgumentException;

   /**
    * <pre>Same as dealPlayer() other than the two notes below but deals for the house and calls the
    * house versions of the callback methods on BlackEngineCallback, no player parameter is required. 
    * 
    * IMPORTANT NOTE 1: At the end of the round but before calling calling {@link BlackEngineCallback#houseResult(int, BlackEngine)} 
    * this method should iterate all players and call {@link BlackEngine#applyWinLoss(Player, int)} 
    * to update each player's points
    * 
    * IMPORTANT NOTE 2: After calling {@link BlackEngineCallback#houseResult(int, BlackEngine)}  
    * this method should also call {@link Player#resetBet()} on each player in preparation for 
    * the next round
    * 
    * @param delay
    *            the delay between cards being dealt (in milliseconds (ms))
    *            
    * @throws IllegalArgumentException thrown when delay param is {@literal <} 0
    * 
    * @see BlackEngine#dealPlayer(Player, int)
    * </pre>
    */
   public abstract void dealHouse(int delay) throws IllegalArgumentException;

   /**
    * <pre>
    * A player's bet is settled by this method 
    * i.e. win or loss is applied to update betting points 
    * based on a comparison of the player result and the provided houseResult
    * 
    * NOTE: This method is usually called from {@link BlackEngine#dealHouse(int)} 
    * as described above but is included in the public interface to facilitate testing
    * 
    * @param player - the Player to apply win/loss to
    * @param houseResult - a DicePair containing the house result
    * </pre>
    */
   public abstract void applyWinLoss(Player player, int houseResult);

   /**
    *  <b>NOTE:</b> id is unique and if another player with same id is added 
    *  it replaces the previous player
    *  
    * @param player - to add to game
    */
   public abstract void addPlayer(Player player);

   /**
    * @param id - id of player to retrieve (null if not found)
    * @return the Player or null if Player does not exist
    */
   public abstract Player getPlayer(String id);

   /**
    * @param player - to remove from game
    * @return true if the player existed and was removed
    */
   public abstract boolean removePlayer(Player player);

   /**
    * the implementation should forward the call to the player class so the bet is set per player
    * @see Player#setBet(int)
    * 
    * @param player
    *            the player who is placing the bet
    * @param bet
    *            the bet in points
    * @return true if the player had sufficient points and the bet was valid and placed
    */
   public abstract boolean placeBet(Player player, int bet);

   /**
    * @param blackEngineCallback
    * <pre> a client specific implementation of BlackEngineCallback used to perform display updates etc.
    * Callbacks should be called in the order they were added
    * <b>NOTE:</b> you will use a different implementation of the GameEngineCallback 
    *       for the console (assignment 1) and GUI (assignment 2) versions
    * </pre>
    * @see view.interfaces.BlackEngineCallback
    */
   public abstract void addGameEngineCallback(BlackEngineCallback blackEngineCallback);

   /**
    * @param blackEngineCallback - instance to be removed if no longer needed
    * @return true if the blackEngineCallback existed
    * @see view.interfaces.BlackEngineCallback
    */
   public abstract boolean removeGameEngineCallback(BlackEngineCallback blackEngineCallback);

   /**
    * <pre>
    * @return an unmodifiable collection (or a shallow copy) of all Players
    * Collection is SORTED in ascending order by player id</pre>
    * @see model.interfaces.Player
    */
   public abstract Collection<Player> getAllPlayers();

   /**
    * A debug method to return a "HALF" deck of cards containing 28 unique cards (8 through to Ace) in<br>
    * random/shuffled order (i.e. should return a new deck that is random WRT previous one)
    * 
    * @return a Deque (specific type of Collection) of PlayingCard
    * @see model.interfaces.PlayingCard
    */
   public abstract Deque<PlayingCard> getShuffledHalfDeck();
}