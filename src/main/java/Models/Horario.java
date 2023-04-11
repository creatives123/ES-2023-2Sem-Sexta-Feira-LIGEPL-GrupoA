package Models;

import java.io.BufferedReader;
import java.util.List;

public class Horario {
    public List<String> curso;
    public String unidadeCurricular;
    public String turno;
    public List<String> turma;
    public int inscritos;
    public String diaSemana;
    public String horaInicio;
    public String horaFim;
    public String dataAula;
    public String sala;
    public int lotacao;
    public Horario(){};

    public Horario(List<String> curso, String unidadeCurricular, String turno, List<String> turma,
                   int inscritos, String diaSemana, String horaInicio, String horaFim,
                   String dataAula, String sala, int lotacao) {
        this.curso = curso;
        this.unidadeCurricular = unidadeCurricular;
        this.turno = turno;
        this.turma = turma;
        this.inscritos = inscritos;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.dataAula = dataAula;
        this.sala = sala;
        this.lotacao = lotacao;
    }

    public List<String> getCurso() {
        return curso;
    }

    public String getUnidadeCurricular() {
        return unidadeCurricular;
    }

    public String getTurno() {
        return turno;
    }

    public List<String> getTurma() {
        return turma;
    }

    public int getInscritos() {
        return inscritos;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public String getDataAula() {
        return dataAula;
    }

    public String getSala() {
        return sala;
    }

    public int getLotacao() {
        return lotacao;
    }

}
