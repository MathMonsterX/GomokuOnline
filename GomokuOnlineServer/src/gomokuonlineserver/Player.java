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
public class Player {
    private String userName;
    private String password;
    private int playerState;
    private Stats stats;

    public Player( String userName, String password )
    {
        this.userName = userName;
        this.password = password;
        this.stats = new Stats();
    }
    public void setPlayerState( int playerState )
    {
        this.playerState = playerState;
    }
    public int getPlayerState()
    {
        return playerState;
    }
    
    public void addGameStats( int time, int gameResult, Player opponent, Date dateTimePlayed )
    {
        stats.addGameStats( time, gameResult, opponent, dateTimePlayed );
    }
    
    
    
    private class Stats {
        private ArrayList<Game> games;
        
        public Stats() 
        {
            games = new ArrayList();
        }
        public void addGameStats( int time, int gameResult, Player opponent, Date dateTimePlayed )
        {
            games.add( new Game( time, gameResult, opponent, dateTimePlayed ) );
        }
        
        public Game getGame( int i )
        {
            return games.get(i);
        }
        
        public Player getGameOpponent( int i )
        {
            Game g = getGame(i);
            return g.getOpponent();
        }
        public Date getGameDate( int i )
        {
            Game g = getGame(i);
            return g.getDateTimePlayed();
        }
        
        public long getAvgGameTime()
        {
            long totTime = 0;
            for( Game g : games )
            {
                totTime += g.getTime();
            }
            return totTime/games.size();
        }
        
        public int getGamesWon()
        {
            int totWon = 0;
            for( Game g : games )
            {
                if( g.getGameResult() == 1 ){ totWon += 1; }
            }
            return totWon;
        }
        
        public int getGamesLost()
        {
            int totLost = 0;
            for( Game g : games )
            {
                if( g.getGameResult() == 1 ){ totLost += 1; }
            }
            return totLost;
        }
        
        public int getGamesTied()
        {
            int totTied = 0;
            for( Game g : games)
            {
                if( g.getGameResult() == 1 ){ totTied += 2; }
            }
            return totTied;
        }
        
        
        private class Game{
            long time;
            int gameResult;
            Player opponent;
            Date dateTimePlayed;
            
            public Game( int time, int gameResult, Player opponent, Date dateTimePlayed )
            {
                this.time = time;
                this.gameResult = gameResult;
                this.opponent = opponent;
                this.dateTimePlayed = dateTimePlayed;
            }
            
            private long getTime()
            {
                return time;
            }
            private int getGameResult()
            {
                return gameResult;
            }
            private Player getOpponent()
            {
                return opponent;
            }
            private Date getDateTimePlayed()
            {
                return dateTimePlayed;
            }
        }
        
    }
    
    
}
