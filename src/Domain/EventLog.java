package Domain;

import java.util.ArrayList;

public class EventLog
{
    public EventLog(ArrayList<ArrayList<EventGame>> events) {
        this.events = events;
    }

    ArrayList<ArrayList<EventGame>> events;
}
