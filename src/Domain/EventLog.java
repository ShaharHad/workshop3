package Domain;

import java.util.ArrayList;

public class EventLog
{
    static int idCount;
    int id;
    ArrayList<ArrayList<EventGame>> events;

    public EventLog()
    {
        idCount++;
        this.id = idCount;
        this.events = new ArrayList<>();
    }

    public int getID() {
        return id;
    }

    public void setID(int eventLogID) {
        this.id = eventLogID;
    }
}
