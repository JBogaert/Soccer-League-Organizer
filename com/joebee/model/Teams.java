package com.joebee.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Teams{
  private List<Team> mTeams;
  private Players mPlayers;
  private int mTotalPlayers;
  private int mAvailablePlayers;
  
  
 public Teams(Players players) {
   mAvailablePlayers = 0;
   mTeams = new ArrayList<Team>();
   mPlayers = players;
   mAvailablePlayers = mPlayers.getPlayers().size();
 }
  
 public ArrayList<Player> findTeamPlayers(Team chosenTeam) {
    ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < mTeams.size(); i++) {
        if (chosenTeam.equals(mTeams.get(i))) {
        players = mTeams.get(i).getTeamRoster();
            }
        }

        return players;
    }
  
 
 public int getTotalAvailablePlayers() {
  return mTotalPlayers; 
 }
   public void addTeam(Team team) {
  mTeams.add(team); 
 }
  
 public List<Team> getTeamList() {
  return mTeams; 
 }
  
public void setTeams(List<Team> teams){
 mTeams = teams; 
}
  
public int getNumberOfTeams() {
 int teamNumber = mTeams.size(); 
  return teamNumber;
}
  
public void addPlayer(Team team, Player player) {
      for(int i = 0; i < mTeams.size(); i++) {
            if (team.equals(mTeams.get(i))) {
                mTeams.get(i).getTeamRoster().add(player);
            }
        }
    }
  
  public void removePlayer(Team team, Player player) {
        for (int i = 0; i < mTeams.size(); i++) {
            if (team.equals(mTeams.get(i))) {
                mTeams.get(i).getTeamRoster().remove(player);
            }
        }
    }

  
  
   
  
}

 

  