package Domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Game
{
    Date date;
    int hour;
    Team guestGroup;
    Team homeGroup;
    String field;
    int score;
    EventLog eventLog;
    int seasonID;
    int LeagueID;
    List<Referee> refereeList;

    public Game(Team guestGroup, Team homeGroup) throws Exception
    {
        if (guestGroup == null || homeGroup == null)
            throw new Exception("one of the params is null!");
        this.guestGroup = guestGroup;
        this.homeGroup = homeGroup;
        this.eventLog = new EventLog();
        refereeList = new ArrayList<>();
    }

    public java.sql.Date getDate() {
        return date;
    }

    public int getHour() {
        return hour;
    }

    public Team getGuestGroup() {
        return guestGroup;
    }

    public Team getHomeGroup() {
        return homeGroup;
    }

    public void setGuestGroup(Team guestGroup) {
        this.guestGroup = guestGroup;
    }

    public void setHomeGroup(Team homeGroup) {
        this.homeGroup = homeGroup;
    }

    public String getField() {
        return field;
    }

    public int getScore() {
        return score;
    }

    public EventLog getEventLog() {
        return eventLog;
    }

    public void setEventLogID(int eventLogID) {
        this.eventLog.setID(eventLogID);
    }

    public int getSeasonID() {
        return seasonID;
    }

    public int getLeagueID() {
        return LeagueID;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setSeasonID(int seasonID) {
        this.seasonID = seasonID;
    }

    public void setLeagueID(int leagueID) {
        LeagueID = leagueID;
    }

    public int getHour(int hourRS) {
        return this.hour;
    }
}
