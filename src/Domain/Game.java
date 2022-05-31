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

    /**
     * constructor of Game class
     * @param guestGroup $guestGroup
     * @param homeGroup  $homeGroup
     */
    public Game(Team guestGroup, Team homeGroup) throws Exception {
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

    /**
     * function getDate
     *
     * @return date of Game type java.sql.Date
     */
    public java.sql.Date getDate() {
        return date;
    }

    /**
     * function getHour
     * @return hour of Game type int
     */
    public int getHour() {
        return hour;
    }

    /**
     * function getGuestGroup
     * @return guestGroup of Game type Team
     */
    public Team getGuestGroup() {
        return guestGroup;
    }

    /**
     * function getHomeGroup
     * @return homeGroup of Game type Team
     */
    public Team getHomeGroup() {
        return homeGroup;
    }

    /**
     * function setGuestGroup
     * @param guestGroup $guestGroup
     * @update guestGroup of Game type void
     */
    public void setGuestGroup(Team guestGroup) {
        this.guestGroup = guestGroup;
    }

    /**
     * function setHomeGroup
     * @param homeGroup $homeGroup
     * @update homeGroup of Game type void
     */
    public void setHomeGroup(Team homeGroup) {
        this.homeGroup = homeGroup;
    }

    /**
     * function getField
     * @return getField of Game type String
     */
    public String getField() {
        return field;
    }

    /**
     * function getScore
     * @return getScore of Game type int
     */
    public int getScore() {
        return score;
    }

    /**
     * function getEventLog
     * @return eventLog of Game type EventLog
     */
    public EventLog getEventLog() {
        return eventLog;
    }

    /**
     * function setEventLogID
     * @updete eventLogID of Game type void
     */
    public void setEventLogID(int eventLogID) {
        this.eventLog.setID(eventLogID);
    }

    /**
     * function getSeasonID
     * @return seasonID of Game type int
     */
    public int getSeasonID() {
        return seasonID;
    }

    /**
     * function getLeagueID
     * @return LeagueID of Game type int
     */
    public int getLeagueID() {
        return LeagueID;
    }

    /**
     * function setDate
     * @param date $date
     * @updete this.date of Game type void
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * function setHour
     * @param hour $hour
     * @updete this.hour of Game type void
     */
    public void setHour(int hour) {
        this.hour = hour;
    }

    /**
     * function setField
     * @param field $field
     * @updete this.hour of Game type void
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     * function setScore
     * @param score $score
     * @updete this.score of Game type void
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * function setSeasonID
     * @param seasonID $seasonID
     * @updete this.seasonID of Game type void
     */
    public void setSeasonID(int seasonID) {
        this.seasonID = seasonID;
    }

    /**
     * function setLeagueID
     * @param leagueID $leagueID
     * @updete LeagueID of Game type void
     */
    public void setLeagueID(int leagueID) {
        LeagueID = leagueID;
    }

    public int getHour(int hourRS) {
        return this.hour;
    }
}
