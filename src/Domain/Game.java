package Domain;

import java.util.Date;

public class Game
{
    Date date;
    int hour;
    Team guestGroup;
    Team homeGroup;
    String field;
    int score;
    EventLog eventLog;

    public Game(Date date, int hour, Team guestGroup, Team homeGroup, String field, int score, EventLog eventLog) {
        this.date = date;
        this.hour = hour;
        this.guestGroup = guestGroup;
        this.homeGroup = homeGroup;
        this.field = field;
        this.score = score;
        this.eventLog = eventLog;
    }

}
