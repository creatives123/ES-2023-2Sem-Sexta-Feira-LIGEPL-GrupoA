package Models;

import java.util.List;

public class Horario {
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
    
    private Horario(Builder builder) {
        this.curso = builder.curso;
        this.unidadeCurricular = builder.unidadeCurricular;
        this.turno = builder.turno;
        this.turma = builder.turma;
        this.inscritos = builder.inscritos;
        this.diaSemana = builder.diaSemana;
        this.horaInicio = builder.horaInicio;
        this.horaFim = builder.horaFim;
        this.dataAula = builder.dataAula;
        this.sala = builder.sala;
        this.lotacao = builder.lotacao;
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
    public static class Builder {
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
        
        public Builder(List<String> curso2, String uc, String turno2, List<String> turma2, int inscritosTurno,
                String diaSemana2, String horaInicio2, String horaFim2, String dataAula2, String sala2,
                int capacidade) {
        }

        public Builder curso(List<String> curso) {
            this.curso = curso;
            return this;
        }
        
        public Builder unidadeCurricular(String unidadeCurricular) {
            this.unidadeCurricular = unidadeCurricular;
            return this;
        }
        
        public Builder turno(String turno) {
            this.turno = turno;
            return this;
        }
        
        public Builder turma(List<String> turma) {
            this.turma = turma;
            return this;
        }
        
        public Builder inscritos(int inscritos) {
            this.inscritos = inscritos;
            return this;
        }
        
        public Builder diaSemana(String diaSemana) {
            this.diaSemana = diaSemana;
            return this;
        }
        
        public Builder horaInicio(String horaInicio) {
            this.horaInicio = horaInicio;
            return this;
        }
        
        public Builder horaFim(String horaFim) {
            this.horaFim = horaFim;
            return this;
        }
        
        public Builder dataAula(String dataAula) {
            this.dataAula = dataAula;
            return this;
        }
        
        public Builder sala(String sala) {
            this.sala = sala;
            return this;
        }
        
        public Builder lotacao(int lotacao) {
            this.lotacao = lotacao;
            return this;
        }
        
        public Horario build() {
            if (curso == null || unidadeCurricular == null || turno == null || turma == null || diaSemana == null || horaInicio == null || horaFim == null) {
                throw new IllegalStateException("Campos obrigatórios não foram definidos");
            }
            return new Horario(this);
        }
    }
}