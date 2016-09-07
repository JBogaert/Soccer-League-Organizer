import com.joebee.model.Player;
import com.joebee.model.Players;
import com.joebee.model.Teams;
import com.joebee.prompter.TeamBuilder;

public class LeagueManager {

    public static void main(String[] args) {
        Players players = new Players();
        Teams teams = new Teams(players);

        TeamBuilder teamBuilder = new TeamBuilder(players, teams);
        teamBuilder.run();
    }

}