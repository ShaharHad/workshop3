package Domain;

import java.util.ArrayList;

public class EventLog
{
    static int idCount=0;
    int id;
    ArrayList<ArrayList<EventGame>> events;
    /**
     * constructor of  EventLog class
     */
    public EventLog()
    {
        idCount++;
        this.id = idCount;
        this.events = new ArrayList<>();
    }
    /**
     * function getID
     * @return id of EventLog type int
     */
    public int getID() {
        return id;
    }

    public void setID(int eventLogID) {
        this.id = eventLogID;
    }
}
