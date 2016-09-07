package com.joebee.model;

import java.util.List;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.Map;
import java.util.Collections;

public class Team implements Comparable<Team>  {
  private String mTeamName;
  private String mCoachName;
  private ArrayList<Player> mPlayers;
  
 public Team(String teamName, String coachName) {
    mPlayers = new ArrayList<Player>();
   
   setName(teamName);
   setCoach(coachName);
   
 }
  
  public void addPlayer(ArrayList<Player> teamPlayers) {
    if (mPlayers.size() == 0) {
      System.out.println("Please add a team first!");
     return;
    } else{ 
    mPlayers = teamPlayers;
    }
    }
  

  
 public ArrayList<Player> getTeamRoster() {
  return mPlayers; 
 }
  
  
  
    private void setCoach(String coachName){
    mCoachName = coachName;
  }
  
  public String getCoach(){
   return mCoachName; 
  }
  
  private void setName(String teamName){
    mTeamName = teamName;
  }
  
  public String getName(){
   return mTeamName; 
  }

  
   @Override
    public int compareTo(Team team){
    Team other = team;
    if (this.equals(other)){
      
      return 0;
      }
    int compareTeams = mTeamName.compareTo(other.getName());
    return compareTeams;   
  }
  
  
//  
//   public void printPlayers() {
//        // Sort the list of players alphabetically
//        Collections.sort(mPlayers, (p1, p2) -> p1.compareTo(p2));
//
//        // Output the sorted list on the console
//        mPlayers.forEach(player -> System.out.printf("Player #%d: %s %s - Standing at %d inches tall, and is %s%n", mPlayers.indexOf(player) + 1, player.getFirstName(), player.getLastName(), player.getHeightInInches(), player.previouslyExperienced()));
//    }
  
      public Set<String> getHeightEvaluations() {
        return byHeightEvaluations().keySet();
    }


    public List<Player> getPlayersForHeight(String heightEvaluation) {
        List<Player> players = byHeightEvaluations().get(heightEvaluation);
        Collections.sort(players, (p1, p2) -> p1.compareTo(p2));
        return players;
    }
  
   public Map<String, List<Player>> byHeightEvaluations() {
        Map<String, List<Player>> byHeight = new TreeMap<>();
        for (Player player : mPlayers) {
            List<Player> playerHeightEvaluations = byHeight.get(player.getHeightEvaluation());
            if (playerHeightEvaluations == null) {
                playerHeightEvaluations = new ArrayList<>();
                byHeight.put(player.getHeightEvaluation(), playerHeightEvaluations);
            }
            playerHeightEvaluations.add(player);
        }
        return byHeight;
   }
  
}
