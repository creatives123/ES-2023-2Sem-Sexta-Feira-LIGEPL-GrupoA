package models;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A classe {@code Horario} representa um {@link Horario} de aulas num curso
 * universitário.
 * Os {@code Horario}s são definidos por diversos atributos, como a unidade curricular,
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
    public Horario() {}

    /**
     * Cria um objeto {@code Horario} a partir de uma linha num arquivo CSV.
     *
     * @param fields uma matriz de ‘strings’ com os campos do {@link Horario}, na ordem:
     *               curso, unidade curricular, turno, turma, inscritos, dia da
     *               semana,
     *               hora de início, hora de fim, data da aula e sala.
     * @throws NumberFormatException se houver um erro na conversão de um campo numérico para
     *                     inteiro.
     */
    public Horario(String[] fields) throws NumberFormatException {
        this.curso = Arrays.asList(fields[0].split(","));
        this.unidadeCurricular = fields[1];
        this.turno = fields[2];
        this.turma = Arrays.asList(fields[3].split(","));
        this.inscritos = Integer.parseInt(fields[4]);
        this.diaSemana = fields[5];
        this.horaInicio = fields[6];
        this.horaFim = fields[7];
        this.dataAula = fields[8];
        this.sala = fields[9];
        this.lotacao =  Integer.parseInt(fields[10]);
    }

    /**
     * Devolve o Curso deste {@link Horario}
     * 
     * @return curso
     */
    public List<String> getCurso() {
        return curso;
    }

    /**
     * Atribui o Curso a este {@link Horario}
     * 
     * @param curso
     */
    public void setCurso(List<String> curso) {
        this.curso = curso;
    }

    /**
     * Devolve a Unidade Curricular deste {@link Horario}
     * 
     * @return unidadeCurricular
     */
    public String getUnidadeCurricular() {
        return unidadeCurricular;
    }

    /**
     * Atribui a Unidade Curricular a este {@link Horario}
     * 
     * @param unidadeCurricular
     */
    public void setUnidadeCurricular(String unidadeCurricular) {
        this.unidadeCurricular = unidadeCurricular;
    }

    /**
     * Devolve o Turno deste {@link Horario}
     * 
     * @return turno
     */
    public String getTurno() {
        return turno;
    }

    /**
     * Atribui o Turno a este {@link Horario}
     * 
     * @param turno
     */
    public void setTurno(String turno) {
        this.turno = turno;
    }

    /**
     * Devolve as Turmas deste {@link Horario}
     * 
     * @return turma
     */
    public List<String> getTurma() {
        return turma;
    }
    
    /**
     * Atribui a Turma a este {@link Horario}
     * 
     * @param turma
     */
    public void setTurma(List<String> turma) {
        this.turma = turma;
    }

    /**
     * Devolve o número de inscritos deste {@link Horario}
     * 
     * @return inscritos
     */
    public int getInscritos() {
        return inscritos;
    }

    /**
     * Atribui o número de Inscritos a este {@link Horario}
     * 
     * @param inscritos
     */
    public void setInscritos(int inscritos) {
        this.inscritos = inscritos;
    }

    /**
     * Devolve o Dia da Semana deste {@link Horario}
     * 
     * @return diaSemana
     */
    public String getDiaSemana() {
        return diaSemana;
    }

    /**
     * Atribui o Dia da Semana a este {@link Horario}
     * 
     * @param diaSemana
     */
    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    /**
     * Devolve a Hora de Início deste {@link Horario}
     * 
     * @return horaInicio
     */
    public String getHoraInicio() {
        return horaInicio;
    }

    /**
     * Atribui a Hora de Início a este {@link Horario}
     * 
     * @param horaInicio
     */
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * Devolve a Hora de Fim deste {@link Horario}
     * 
     * @return horaFim
     */
    public String getHoraFim() {
        return horaFim;
    }

    /**
     * Atribui a Hora de Fim a este {@link Horario}
     * 
     * @param horaFim
     */
    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    /**
     * Devolve a Data deste {@link Horario}
     * 
     * @return dataAula
     */
    public String getDataAula() {
        return dataAula;
    }

    /**
     * Atribui a Data a este {@link Horario}
     * 
     * @param dataAula
     */
    public void setDataAula(String dataAula) {
        this.dataAula = dataAula;
    }

    /**
     * Devolve a Sala deste {@link Horario}
     * 
     * @return sala
     */
    public String getSala() {
        return sala;
    }

    /**
     * Atribui a Sala a este {@link Horario}
     * 
     * @param sala
     */
    public void setSala(String sala) {
        this.sala = sala;
    }

    /**
     * Devolve Lotação da Sala deste {@link Horario}
     * 
     * @return lotacao
     */
    public int getLotacao() {
        return lotacao;
    }

    /**
     * Atribui a Lotação da Sala a este {@link Horario}
     * 
     * @param lotacao
     */
    public void setLotacao(int lotacao) {
        this.lotacao = lotacao;
    }

    @JsonIgnore
    public boolean isOverCrowded() {
        return this.inscritos > this.lotacao;
    }
}