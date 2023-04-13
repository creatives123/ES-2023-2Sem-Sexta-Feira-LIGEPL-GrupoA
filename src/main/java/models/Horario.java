package models;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * A classe {@code Horario} representa um horário de aulas num curso
 * universitário.
 * Os horários são definidos por diversos atributos, como a unidade curricular,
 * o turno, a turma, a sala, entre outros.
 */
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

    /**
     * Cria um objeto {@code Horario} vazio.
     */
    public Horario() {
    }

    /**
     * Cria um objeto {@code Horario} a partir de uma linha num arquivo CSV.
     *
     * @param fields uma matriz de ‘strings’ com os campos do horário, na ordem:
     *               curso, unidade curricular, turno, turma, inscritos, dia da
     *               semana,
     *               hora de início, hora de fim, data da aula e sala.
     * @throws IOException se houver um erro na conversão de um campo numérico para
     *                     inteiro.
     */
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

    /**
     * Converte uma ‘string’ para um inteiro. Se a conversão falhar, retorna 0.
     *
     * @param s a string a ser convertida.
     * @return o valor inteiro correspondente à ‘string’, ou 0 se a conversão falhar.
     */
    private int checkInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return 0;
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