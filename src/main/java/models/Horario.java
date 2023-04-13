package models;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Horario implements Serializable {
    private List<String> curso;
    private String unidadeCurricular;
    private String turno;
    private List<String> turma;
    private int inscritos;
    private String diaSemana;
    private String horaInicio;
    private String horaFim;
    private String dataAula;
    private String sala;
    private int lotacao;
    
    public Horario(String[] fields) throws IOException {
        this.curso = Arrays.asList(fields[0].split(","));
        this.unidadeCurricular = fields[1];
        this.turno = fields[2];
        this.turma = Arrays.asList(fields[3].split(","));
        this.inscritos = checkInt(fields[4]);
        this.diaSemana = fields[5];
        this.horaInicio = fields[6];
        this.horaFim = fields[7];
        this.dataAula = fields[8];
        this.sala = fields[9];
        this.lotacao = checkInt(fields[10]);
    }
    
    private int checkInt(String str) throws IOException{
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IOException("Número Inválido " + str);
        }
    }
    
    public List<String> getCurso() {
        return curso;
    }

    public void setCurso(List<String> curso) {
        this.curso = curso;
    }

    public String getUnidadeCurricular() {
        return unidadeCurricular;
    }

    public void setUnidadeCurricular(String unidadeCurricular) {
        this.unidadeCurricular = unidadeCurricular;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public List<String> getTurma() {
        return turma;
    }

    public void setTurma(List<String> turma) {
        this.turma = turma;
    }

    public int getInscritos() {
        return inscritos;
    }

    public void setInscritos(int inscritos) {
        this.inscritos = inscritos;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public String getDataAula() {
        return dataAula;
    }

    public void setDataAula(String dataAula) {
        this.dataAula = dataAula;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public int getLotacao() {
        return lotacao;
    }

    public void setLotacao(int lotacao) {
        this.lotacao = lotacao;
    }
}