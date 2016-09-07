package com.joebee.prompter;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

import com.joebee.model.Player;
import com.joebee.model.Players;
import com.joebee.model.Team;
import com.joebee.model.Teams;

public class TeamBuilder {

  private Players mPlayers;
  private BufferedReader mReader;
  private Map<String, String> mMenu;
  private Teams mTeams;
  
  //Constructor
  public TeamBuilder(Players players, Teams teams) {
    mTeams = teams;
    mPlayers = players;
    mReader = new BufferedReader(new InputStreamReader(System.in));
    mMenu = new HashMap<String, String>();
    mMenu.put("1", "Create a new Team");
    mMenu.put("2", "Add Player to Team"); 
    mMenu.put("3", "Remove a Player from a Team");
    mMenu.put("4", "Height Report per Team");
    mMenu.put("5", "League Balance Report - Experience");
    mMenu.put("6", "Print Team Roster");
    mMenu.put("7", "Quit");
//    run();

}

  private String promptAction() throws IOException {

    for (Map.Entry<String, String> option : mMenu.entrySet()) {
      System.out.printf("%s - %s %n",
                        option.getKey(),
                        option.getValue());
    }
    System.out.printf("What do you want to do? %n");
       
    String choice = mReader.readLine();
      
    return choice.trim().toLowerCase();
    } 
  

    public void run() {
    String choice = "";
    do {
      try {
        choice = promptAction();
        switch(choice) {
          case "1":
            promptNewTeam();
            break;
          case "2":
          Team chosenTeam = promptTeamList();
          
          
          
          mPlayers.printPlayers();
          Player player = promptPlayerList(mPlayers);
          addPlayer(chosenTeam, player);
          break;
//            String artist = promptArtist();
//            Song artistSong = promptSongForArtist(artist);
//            mSongQueue.add(artistSong);
//            System.out.printf("You chose:  %s %n", artistSong);
//            break;
          case "3":
           chosenTeam = promptTeamList();
            if (chosenTeam.getTeamRoster().size() == 0) {
               System.out.printf("%nThere are no players on this team!%n" + "Please, start by adding players to this team first!%n");
            break;
            }
            
            printSortedTeamPlayers(chosenTeam);
            ArrayList<Player> teamPlayers = chosenTeam.getTeamRoster();
            player = promptPlayerList(teamPlayers);
            removePlayer(chosenTeam, player);
            break;
          
            case "4":
            chosenTeam = promptTeamList();
            runHeightReport(chosenTeam);
            break;
            
            case "5":
            chosenTeam = promptTeamList();
            runLeagueBalanceReport(chosenTeam);
            break;
          
            case "6":
            chosenTeam = promptTeamList();
            printRoster(chosenTeam);
            break;
          
            case "7":
            break;
          
          default:
            System.out.printf("Not on the list:  " + choice + ". Try again.  %n");
        }
      } catch(IOException ioe) {
        System.out.println("Problem with input");
        ioe.printStackTrace();
      }
    }while(!choice.equals("7"));
  }

                
  private void removePlayer(Team chosenTeam, Player player) {
    
        mTeams.removePlayer(chosenTeam, player);
    
        mPlayers.getPlayers().add(player);
        System.out.printf("%n%s %s was removed from the team \"%s\".%n", player.getFirstName(), player.getLastName(), chosenTeam.getName());
        printSortedTeamPlayers(chosenTeam);
    }
              
   private void addPlayer(Team chosenTeam, Player player) {
        mTeams.addPlayer(chosenTeam, player);
        if (mTeams.getNumberOfTeams() == 0) {
         promptNewTeam(); 
        }
       
        mPlayers.getPlayers().remove(player);
       
        System.out.printf("%n%s %s was added to the team \"%s\".%n", player.getFirstName(), player.getLastName(), chosenTeam.getName());

        printSortedTeamPlayers(chosenTeam);
    }
              
     private void printRoster(Team chosenTeam) {
        System.out.printf("%nTeam Roster for team \"%s\".%n", chosenTeam.getName());
        if (chosenTeam.getTeamRoster().size() == 0) {
            System.out.println("\nZero Players on this team!\n");
            return;
        }
        System.out.printf("%nCoach - %s.%n", chosenTeam.getCoach());
        System.out.printf("");


        printSortedTeamPlayers(chosenTeam);
        System.out.println();
    }

    private void printSortedTeamPlayers(Team chosenTeam) {
        String teamName = chosenTeam.getName();
        System.out.printf("Current list of players for \"%s\":%n", teamName);

        Collections.sort(chosenTeam.getTeamRoster(), (p1, p2) -> p1.compareTo(p2));

        for (Player player : chosenTeam.getTeamRoster()) {
          String previouslyExperienced = player.previouslyExperienced();
        System.out.printf("Player #%d: %s %s - Standing at %d inches tall, and is %s%n", chosenTeam.getTeamRoster().indexOf(player) + 1, player.getFirstName(), player.getLastName(), player.getHeightInInches(), player.previouslyExperienced());
        }
//        System.out.println();
    }
              
     private void runHeightReport(Team chosenTeam) {
        System.out.println("Height Report for team \"" + chosenTeam.getName() + "\"\n");

        if (chosenTeam.getTeamRoster().size() != 0) {
            
        for (String heightEvaluation : chosenTeam.getHeightEvaluations()) {
            List<Player> playersForHeight = chosenTeam.getPlayersForHeight(heightEvaluation);
            Collections.sort(playersForHeight, (p1, p2) -> p1.compareTo(p2));
            String heightEvalString;
            if (heightEvaluation.equals("Below Average")) {
                heightEvalString = "< 39";
            } else if (heightEvaluation.equals("Average")) {
                heightEvalString = "39 - 42";
            } else {
                heightEvalString = "> 42";
            }
            int playersCount = playersForHeight.size();
            System.out.printf(" " + heightEvaluation + "(" + heightEvalString + " in) - " + playersCount + "");

            for (Player player : playersForHeight) {
                System.out.printf("%n\t- " + player.getLastName() + ", " + player.getFirstName() + " (" + player.getHeightInInches() +" in)%n");

            }
            System.out.println();
        } 
    }else {
          System.out.println("Zero Players on this team!");
        }
     }
 
  
      private Player promptPlayerList(ArrayList<Player> teamPlayers) {
        int playersNumber = 0;
        do {
          System.out.printf("\nChoose the player by entering anumber: ");  
          int playersNumberAsString = readIntLine();
          
                playersNumber = playersNumberAsString;

            if (playersNumber <= 0 || playersNumber > teamPlayers.size()) {
                System.out.printf("Please, enter a number within the range of 1 to %d.%n", teamPlayers.size());
            }
        } while (playersNumber <= 0 || playersNumber > teamPlayers.size());


        return teamPlayers.get(playersNumber - 1);
    }
  
   private Player promptPlayerList(Players players) {

        String playersNumberAsString = "";
        int playersNumber = 0;
        do {

         System.out.printf("%nPlease choose the player by using his or her number: ");
         playersNumber = readIntLine();

            if (playersNumber <= 0 || playersNumber > players.getPlayers().size()) {
                System.out.printf("Please, enter a number within the range of 1 to %d.%n", players.getPlayers().size());
            }
        } while (playersNumber <= 0 || playersNumber > players.getPlayers().size());


        return players.getPlayers().get(playersNumber - 1);
    }

              
              
                  private Team promptTeamList() throws IOException {

                System.out.println("Choose a Team:");
                   
                    List<String> teamList = new ArrayList<>();
                    List<Team> teamArray = mTeams.getTeamList(); 
                    for(Team team : teamArray) {

                    String name = team.getName();
                    String coach = team.getCoach();
                  
                    String teamInfo = name + ", coached by " + coach + ".";
//                
                   
                  teamList.add(teamInfo);
                      }
                 int index = promptForIndex(teamList);
                
                    return mTeams.getTeamList().get(index);  
  }
  
  

  
    private int readIntLine() {
      int maxQuantity = 0;
      boolean done = false;
      while (!done) {
        try {
            maxQuantity = Integer.parseInt(mReader.readLine());
            done = true;
            } catch (NumberFormatException nfe) {
                     System.out.println("Sorry, you must enter a number! Please try again.");
            } catch (IOException ioe) {
                    System.out.println("This is an IOE Error");
            }
      } return maxQuantity;
  }
  
   private int promptForIndex(List<String> options) throws IOException {
    int counter = 1;
    for (String option : options) {
      System.out.printf("%d.)  %s %n", counter, option);
      counter++;
    }
    System.out.print("Your choice:   ");
    int choice = readIntLine();

    return choice - 1;
  }
  
  public void promptNewTeam() {
            try {         
            System.out.printf("%nWhat will your new team be called?%n");
            String teamName = mReader.readLine();
            System.out.printf("%nWho will coach this new team?%n");
            String coach = mReader.readLine();
            Team team = new Team(teamName, coach);
            mTeams.addTeam(team);
            System.out.printf("%nTeam %s added!  %n%n", team.getName());
              
            } catch (IOException ioe) {
              System.out.println("This is an error");
            }
  }
  
  public void runLeagueBalanceReport(Team chosenTeam) {
   int experiencedPlayers = 0;
   int inexperiencedPlayers = 0;
    for(Player p : chosenTeam.getTeamRoster()) {
      if(p.isPreviousExperience() == true) {
        experiencedPlayers++;
      }else {
        inexperiencedPlayers++;
      }
    }
    System.out.println("There are " + experiencedPlayers + " experienced players and " + inexperiencedPlayers + " non-experienced players on this team."); 
  
  
  }
    }






