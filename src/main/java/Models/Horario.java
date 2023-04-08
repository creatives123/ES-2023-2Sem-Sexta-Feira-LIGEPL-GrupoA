package Models;

import java.io.BufferedReader;
import java.util.List;

public class Horario {
    public String curso;
    public String unidadeCurricular;
    public String turno;
    public String turma;
    public int inscritos;
    public String diaSemana;
    public String horaInicio;
    public String horaFim;
    public String dataAula;
    public String sala;
    public int lotacao;

    public Horario(String curso, String unidadeCurricular, String turno, String turma,
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

    @Override
    public String toString() {
        return "Horario{" +
                "curso='" + curso + '\'' +
                ", unidadeCurricular='" + unidadeCurricular + '\'' +
                ", turno='" + turno + '\'' +
                ", turma='" + turma + '\'' +
                ", inscritosNoTurno=" + inscritos +
                ", diaDaSemana='" + diaSemana + '\'' +
                ", horaInicioDaAula='" + horaInicio + '\'' +
                ", horaFimDaAula='" + horaFim + '\'' +
                ", dataDaAula='" + dataAula + '\'' +
                ", sala='" + sala + '\'' +
                ", lotacaoDaSala=" + lotacao +
                '}';
    }

}
