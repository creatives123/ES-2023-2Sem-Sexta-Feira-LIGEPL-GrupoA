package models;

public class CalendarModel {
    private int id;
    private String title;
    private String start;
    private String end;
    private String color;
    private Horario horario;
    
    public String getColor() {
        return color;
    }

    public void setHorario(Horario h) {
        this.horario = h;
    }

    public Horario getHorario() {
        return this.horario;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getStart() {
        return start;
    }
    
    public void setStart(String start) {
        this.start = start;
    }
    
    public String getEnd() {
        return end;
    }
    
    public void setEnd(String end) {
        this.end = end;
    }    
}