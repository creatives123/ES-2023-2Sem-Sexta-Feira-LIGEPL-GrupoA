package models;

import java.util.List;

public class CalendarWrapper {
    private int overlappedEventsCounter;
    private int overCrowdedEventsCounter;
    private List<CalendarModel> events;

    public void setOverlappedEventsCounter(int overlappedEventsCounter) {
        this.overlappedEventsCounter = overlappedEventsCounter;
    }

    public int getOverlappedEventsCounter() {
        return this.overlappedEventsCounter;
    }

    public void setOverCrowdedEventsCounter(int overCrowdedEventsCounter) {
        this.overCrowdedEventsCounter = overCrowdedEventsCounter;
    }

    public int getOverCrowdedEventsCounter() {
        return this.overCrowdedEventsCounter;
    }

    public void setEvents(List<CalendarModel> events) {
        this.events = events;
    }

    public List<CalendarModel> getEvents() {
        return this.events;
    }
}