/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gomokuonlineserver;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Sheyla
 */
public class Player implements Comparable<Player> {
    private  String userName;
    private  String password;
    private boolean playerState;
    private Stats stats;

    /**
     * The constructor for the Player class. 
     * @param userName  The user name of the Player
     * @param password 
     */
    public Player( String userName, String password )
    {
        this.userName = userName;
        this.password = password;
        this.stats = new Stats();
    }
    /**
     * Returns the user name of the Player.
     * @return Returns a string object containing the Player object's user name.
     */
    public String getUserName() {
        return userName;
    }
    /**
     * Returns the password of the Player.
     * @return Returns a string object containing the Player object's password.
     */
    public String getPassword(){
        return this.password;
    }
    /**
     * Meant to show whether a player is available or busy.
     * @param playerState Set to true if a player is available. False is player is not available.
     */
    public void setPlayerState( boolean playerState )
    {
        this.playerState = playerState;
    }
    /**
     * Returns the state of the player, whether they are busy or available.
     * @return Returns false if the player is busy, true if the player is available.
     */
    public boolean getPlayerState()
    {
        return playerState;
    }
    /**
     * Returns a collection of values derived from the Stats located in the Player class. 
     * @return 
     */
    public String getStats(){
        return "Games Played: " + this.stats.numGames() 
                + " Wins: "+this.stats.getGamesWon() 
                +" Losses: " + this.stats.getGamesLost() 
                +" Average Gametime: " +this.stats.getAvgGameTime();
    }
    /**
     * Allows stats for a game to be added into a Player object.
     * @param datePlayed    The date which the game was played.
     * @param time  The time it took to play the game.
     * @param opponent The Player whom the user played against.
     * @param gameResult Indicates whether the Player won or lost the game.
     */
    public void addGameStats( Date datePlayed, long time, Player opponent, int gameResult )
    {
        stats.addGameStats( time, gameResult, opponent, datePlayed );
    }
    
    @Override
    public String toString()
    {
        return userName + " " + password + "\n" 
                + stats.toString();
    }

    @Override
    public int compareTo(Player other) {
        return this.userName.compareTo(other.getUserName());
    }
    
    @Override
    public boolean equals(Object o){
        if(o == null)
            return false;
        if(o instanceof Player){
            return this.compareTo((Player)o) == 0;
        }
        return false;
    }

    
    
    private class Stats {
        private ArrayList<Game> games;
        
        /**
         * The constructor for the Stats object. 
         * Contains a list of games.
         */
        public Stats() 
        {
            games = new ArrayList();
        }
        /**
         * Adds a new game into the array list containing each game played by the player.
         * @param time  The time it took to play the game.
         * @param gameResult    Whether the player won or lost the game.
         * @param opponent  The Player whom the user played against.
         * @param datePlayed The date that the game was played.
         */
        public void addGameStats( long time, int gameResult, Player opponent, Date datePlayed )
        {
            games.add( new Game( time, gameResult, opponent, datePlayed ) );
        }
        /**
         * Returns the total number of games played by the Player.
         * @return Returns an int, indicating the total number of games played.
         */
        public int numGames()
        {
            return games.size();
        }
        /**
         * Returns an arraylist of Game objects.
         * @return 
         */
        public ArrayList getGames()
        {
            return games;
        }
        /**
         * Returns a game at a particular index in the games arraylist.
         * @param i The index
         * @return 
         */
        public Game getGameAt( int i )
        {
            return games.get(i);
        }
        /**
         * Returns the opponent of a game at a particular index of the arraylist.
         * @param i The index
         * @return 
         */
        public Player getGameOpponent( int i )
        {
            Game g = getGameAt(i);
            return g.getOpponent();
        }
        /**
         * Returns the date that a game was played at a particular index in the games index.
         * @param i The index
         * @return 
         */
        public Date getGameDate( int i )
        {
            Game g = getGameAt(i);
            return g.getDatePlayed();
        }
        /**
         * Returns the average game time among all the 
         * games located in the games arraylist.
         * @return A long indicating the average time played among all games.
         */
        public long getAvgGameTime()
        {
            long totTime = 0;
            for( Game g : games )
            {
                totTime += g.getTime();
            }
            return totTime/games.size();
        }
        /**
         * Returns the total number of games that the Player has won.
         * @return Integer indicating the number of games won.
         */
        public int getGamesWon()
        {
            int totWon = 0;
            for( Game g : games )
            {
                if( g.getGameResult() == 1 ){ totWon += 1; }
            }
            return totWon;
        }
        /**
         * Returns the total number of games that the Player has lost.
         * @return Integer indicating the number of games lost.
         */
        public int getGamesLost()
        {
            int totLost = 0;
            for( Game g : games )
            {
                if( g.getGameResult() == 0 ){ totLost += 1; }
            }
            return totLost;
        }
        /**
         * Returns the total number of games that the Player has tied.
         * @return Integer indicating the number of games tied.
         */
        public int getGamesTied()
        {
            int totTied = 0;
            for( Game g : games)
            {
                if( g.getGameResult() == 2 ){ totTied += 1; }
            }
            return totTied;
        }
        
        @Override
        public String toString()
        {
            StringBuilder str = new StringBuilder();
            str.append(games.size() + "\n");
            for( Game g:games )
            {
                str.append( g.toString() + "\n" );
            }
            return str.toString();
        }
        
        
        private class Game{
            long time;
            int gameResult;
            Player opponent;
            Date datePlayed;
            
            /**
             * The constructor for the Game object.
             * @param time The time taken to play the Game
             * @param gameResult The result of the Game, 0 if lost, 1 if won, 2 if tied.
             * @param opponent The Player whom the user played against.
             * @param datePlayed The date that the Game was played.
             */
            public Game( long time, int gameResult, Player opponent, Date datePlayed )
            {
                this.time = time;
                this.gameResult = gameResult;
                this.opponent = opponent;
                this.datePlayed = datePlayed;
            }
            /**
             * Returns the time taken to play the Game.
             * @return Long value containing the time taken to play the game.
             */
            public long getTime()
            {
                return time;
            }
            /**
             * Returns the result of the Game. 0 if lost, 1 is won, 2 if tied.
             * @return Integer containing the result of the Game.
             */
            public int getGameResult()
            {
                return gameResult;
            }
            /**
             * Returns the opponent that the user played against in the game.
             * @return Player object containing the opponent.
             */
            public Player getOpponent()
            {
                return opponent;
            }
            /**
             * Returns the date that the Game was played.
             * @return Date object containing the date played.
             */
            public Date getDatePlayed()
            {
                return datePlayed;
            }
            
            @Override
            public String toString()
            {
                return datePlayed.toString() + " " + time + " " + opponent.getUserName() + " " + gameResult;
            }
        }
        
    }
    
    
}
