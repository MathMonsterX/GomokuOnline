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
    private final String userName;
    private final String password;
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
    public void addGameStats( int time, int gameResult, Player opponent, Date datePlayed )
    {
        stats.addGameStats( time, gameResult, opponent, datePlayed );
    }
    
    @Override
    public String toString()
    {
        return userName + ":" + password + ":" 
                + stats.toString();
    }
    
    private class Stats {
        private ArrayList<Game> games;
        
        public Stats() 
        {
            games = new ArrayList();
        }
        public void addGameStats( int time, int gameResult, Player opponent, Date datePlayed )
        {
            games.add( new Game( time, gameResult, opponent, datePlayed ) );
        }
        public int numGames()
        {
            return games.size();
        }
        public ArrayList getGames()
        {
            return games;
        }
        public Game getGameAt( int i )
        {
            return games.get(i);
        }
        
        public Player getGameOpponent( int i )
        {
            Game g = getGameAt(i);
            return g.getOpponent();
        }
        public Date getGameDate( int i )
        {
            Game g = getGameAt(i);
            return g.getDatePlayed();
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
        
        @Override
        public String toString()
        {
            StringBuilder str = new StringBuilder();
            str.append("[");
            for( Game g:games )
            {
                str.append( g.toString() );
            }
            str.append("]");
            return str.toString();
        }
        
        
        private class Game{
            long time;
            int gameResult;
            Player opponent;
            Date datePlayed;
            
            public Game( int time, int gameResult, Player opponent, Date datePlayed )
            {
                this.time = time;
                this.gameResult = gameResult;
                this.opponent = opponent;
                this.datePlayed = datePlayed;
            }
            
            public long getTime()
            {
                return time;
            }
            public int getGameResult()
            {
                return gameResult;
            }
            public Player getOpponent()
            {
                return opponent;
            }
            public Date getDatePlayed()
            {
                return datePlayed;
            }
            
            @Override
            public String toString()
            {
                return datePlayed + " " + time + " " + opponent + " " + gameResult;
            }
        }
        
    }
    
    
}
