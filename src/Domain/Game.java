package Domain;

import DataAccess.GameDA;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//    List<Referee> refereeList;
    Referee referee1;
    Referee referee2;
    Referee mainReferee;

    public Game(Team guestGroup, Team homeGroup) throws Exception
    {
        if (guestGroup == null || homeGroup == null)
            throw new Exception("one of the params is null!");
        this.guestGroup = guestGroup;
        this.homeGroup = homeGroup;
        this.eventLog = new EventLog();
//        refereeList = new ArrayList<>();
        this.referee1 = null;
        this.referee2 = null;
        this.mainReferee = null;
    }

    public Referee getReferee1() {
        return referee1;
    }

    public void setReferee1(Referee referee1) {
        this.referee1 = referee1;
    }

    public Referee getReferee2() {
        return referee2;
    }

    public void setReferee2(Referee referee2) {
        this.referee2 = referee2;
    }

    public Referee getMainReferee() {
        return mainReferee;
    }

    public void setMainReferee(Referee mainReferee) {
        this.mainReferee = mainReferee;
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

//    static Game getGameFromDB(String field, String hour, String date)
//    {
//        Map<String, String> map = new HashMap<>();
//        map.put("fieldName", field);
//        map.put("hour", hour);
//        map.put("date", date);
//        GameDA gda = GameDA.getInstance();
//        return gda.get(map);
//    }
}
