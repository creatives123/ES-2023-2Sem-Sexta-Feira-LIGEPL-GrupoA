package Config;
/**
 * Reune todas as configurações estáticas necessárias ao desenvolvimento do Projeto.  
 */
public class CSVConfig {
      /**
     * Array de ‘strings’ que define a ordem e nome dos campos do cabeçalho de um
     * ficheiro CSV válido contendo informações sobre
     * horários de aulas. Os campos são, pela ordem: "Curso", "Unidade Curricular",
     * "Turno", "Turma", "Inscritos no turno",
     * "Dia da semana", "Hora início da aula", "Hora fim da aula", "Data da aula",
     * "Sala atribuída à aula" e "Lotação da sala".
     */
      public static final String[] HEADER_FIELDS = { "Curso", "Unidade Curricular", "Turno", "Turma",
     "Inscritos no turno",
     "Dia da semana", "Hora início da aula", "Hora fim da aula", "Data da aula", "Sala atribuída à aula",
     "Lotação da sala" };

     /**
      * Delimitador para os campos dos ficheiros CSV.
      */
     public static final char SEPARATOR = ';';

     /**
      * Construtor vazio
      */
     private CSVConfig() {}
}
