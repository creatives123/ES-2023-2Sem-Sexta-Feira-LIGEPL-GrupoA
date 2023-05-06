package models;

import java.util.List;
import java.util.Map;

/**
 * Classe que envolve os eventos do calendário e contém informações sobre o número de eventos
 * que se sobrepõem e o número de eventos que têm demasiados participantes.
 */
public class CalendarWrapper {

    private Map<String, Integer> overlappedEvents;
    private Map<String, Integer> overCrowdedEvents;
    private List<CalendarModel> events;

    /**
     * Define o número de eventos que se sobrepõem.
     * 
     * @param overlappedEventsCounter o número de eventos que se sobrepõem.
     */
    public void setOverlappedEvents(Map<String, Integer> overlappedEvents) {
        this.overlappedEvents = overlappedEvents;
    }

    /**
     * Obtém o número de eventos que se sobrepõem.
     * 
     * @return o número de eventos que se sobrepõem.
     */
    public Map<String, Integer> getOverlappedEvents() {
        return this.overlappedEvents;
    }

    /**
     * Define o número de eventos que têm demasiados participantes.
     * 
     * @param overCrowdedEventsCounter o número de eventos que têm demasiados participantes.
     */
    public void setOverCrowdedEvents(Map<String, Integer> overCrowdedEvents) {
        this.overCrowdedEvents = overCrowdedEvents;
    }

    /**
     * Obtém o número de eventos que têm demasiados participantes.
     * 
     * @return o número de eventos que têm demasiados participantes.
     */
    public Map<String, Integer> getOverCrowdedEvents() {
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
