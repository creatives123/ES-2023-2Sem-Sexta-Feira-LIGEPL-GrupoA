package models;

/**
 * A classe CalendarModel representa um evento de calendário a ser exibido na interface do usuário.
 * 
 * Cada evento de calendário é identificado por um ID exclusivo, um título, uma hora de início, uma hora de fim e uma cor (para indicar conflitos ou sobreposições).
 * 
 * Além disso, cada evento de calendário está associado a um objeto Horario que descreve os detalhes do evento (curso, unidade curricular, etc.).
 */
public class CalendarModel {
    
    private int id;
    private String title;
    private String start;
    private String end;
    private String color;
    private Horario horario;
    
    /**
     * Retorna a cor do evento de calendário.
     * 
     * @return uma string representando a cor do evento de calendário
     */
    public String getColor() {
        return color;
    }

    /**
     * Define o objeto Horario associado a este evento de calendário.
     * 
     * @param h o objeto Horario a ser associado a este evento de calendário
     */
    public void setHorario(Horario h) {
        this.horario = h;
    }

    /**
     * Retorna o objeto Horario associado a este evento de calendário.
     * 
     * @return o objeto Horario associado a este evento de calendário
     */
    public Horario getHorario() {
        return this.horario;
    }
    
    /**
     * Define a cor do evento de calendário.
     * 
     * @param color uma string representando a cor do evento de calendário
     */
    public void setColor(String color) {
        this.color = color;
    }
    
    /**
     * Retorna o ID do evento de calendário.
     * 
     * @return o ID do evento de calendário
     */
    public int getId() {
        return id;
    }
    
    /**
     * Define o ID do evento de calendário.
     * 
     * @param id o ID do evento de calendário
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Retorna o título do evento de calendário.
     * 
     * @return o título do evento de calendário
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Define o título do evento de calendário.
     * 
     * @param title o título do evento de calendário
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Retorna a hora de início do evento de calendário.
     * 
     * @return uma string representando a hora de início do evento de calendário
     */
    public String getStart() {
        return start;
    }
    
    /**
     * Define a hora de início do evento de calendário.
     * 
     * @param start uma string representando a hora de início do evento de calendário
     */
    public void setStart(String start) {
        this.start = start;
    }
    
    /**
     * Retorna a hora de fim do evento de calendário.
     * 
     * @return uma string representando a hora de fim do evento de calendário
     */
    public String getEnd() {
        return end;
    }
    
    /**
     * Define a hora de fim do evento de calendário.
     * 
     * @param end uma string representando a hora de fim do evento de calendário
     */
    public void setEnd(String end) {
        this.end = end;
    }

}