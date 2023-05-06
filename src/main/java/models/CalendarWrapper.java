package models;

import java.util.List;

/**
 * Classe que envolve os eventos do calendário e contém informações sobre o número de eventos
 * que se sobrepõem e o número de eventos que têm demasiados participantes.
 */
public class CalendarWrapper {

    private List<String> overlappedEvents;
    private List<String> overCrowdedEvents;
    private List<CalendarModel> events;

    /**
     * Define o número de eventos que se sobrepõem.
     * 
     * @param overlappedEventsCounter o número de eventos que se sobrepõem.
     */
    public void setOverlappedEvents(List<String> overlappedEvents) {
        this.overlappedEvents = overlappedEvents;
    }

    /**
     * Obtém o número de eventos que se sobrepõem.
     * 
     * @return o número de eventos que se sobrepõem.
     */
    public List<String> getOverlappedEvents() {
        return this.overlappedEvents;
    }

    /**
     * Define o número de eventos que têm demasiados participantes.
     * 
     * @param overCrowdedEventsCounter o número de eventos que têm demasiados participantes.
     */
    public void setOverCrowdedEvents(List<String> overCrowdedEvents) {
        this.overCrowdedEvents = overCrowdedEvents;
    }

    /**
     * Obtém o número de eventos que têm demasiados participantes.
     * 
     * @return o número de eventos que têm demasiados participantes.
     */
    public List<String> getOverCrowdedEvents() {
        return this.overCrowdedEvents;
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
