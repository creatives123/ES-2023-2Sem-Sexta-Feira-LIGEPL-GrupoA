package models;

import java.util.List;

/**
 * Classe que envolve os eventos do calendário e contém informações sobre o número de eventos
 * que se sobrepõem e o número de eventos que têm demasiados participantes.
 */
public class CalendarWrapper {

    private int overlappedEventsCounter;
    private int overCrowdedEventsCounter;
    private List<CalendarModel> events;

    /**
     * Define o número de eventos que se sobrepõem.
     * 
     * @param overlappedEventsCounter o número de eventos que se sobrepõem.
     */
    public void setOverlappedEventsCounter(int overlappedEventsCounter) {
        this.overlappedEventsCounter = overlappedEventsCounter;
    }

    /**
     * Obtém o número de eventos que se sobrepõem.
     * 
     * @return o número de eventos que se sobrepõem.
     */
    public int getOverlappedEventsCounter() {
        return this.overlappedEventsCounter;
    }

    /**
     * Define o número de eventos que têm demasiados participantes.
     * 
     * @param overCrowdedEventsCounter o número de eventos que têm demasiados participantes.
     */
    public void setOverCrowdedEventsCounter(int overCrowdedEventsCounter) {
        this.overCrowdedEventsCounter = overCrowdedEventsCounter;
    }

    /**
     * Obtém o número de eventos que têm demasiados participantes.
     * 
     * @return o número de eventos que têm demasiados participantes.
     */
    public int getOverCrowdedEventsCounter() {
        return this.overCrowdedEventsCounter;
    }

    /**
     * Define a lista de eventos do calendário.
     * 
     * @param events a lista de eventos do calendário.
     */
    public void setEvents(List<CalendarModel> events) {
        this.events = events;
    }

    /**
     * Obtém a lista de eventos do calendário.
     * 
     * @return a lista de eventos do calendário.
     */
    public List<CalendarModel> getEvents() {
        return this.events;
    }
}
