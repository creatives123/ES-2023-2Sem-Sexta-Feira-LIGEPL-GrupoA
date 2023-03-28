# ES
Trabalho de Engenharia de Software (2022/2023 – 2º Semestre)<br>
Licenciatura em Engenharia Informática/Licenciatura em Informática e Gestão de Empresas<br>
Departamento de Ciências e Tecnologias da Informação<br>
Escola de Tecnologias e Arquitetura<br>
Iscte - Instituto Universitário de Lisboa

## Membros do grupo

| Nome do Aluno  | Nr. Aluno |
| ------------- | ------------- |
| Alexandre Rodrigo Machado Torres | 95983 |
| Alexandre Maggiolo Gordino Pinto Monteiro | 90284 |
| Alexandre Miguel Nobre Ramos Leitão | 95257 |
| Maria Pacheco Costa | 93319 |
| António Gallis Rocha | 88000 |
| Eduardo Guerreiro Carneiro | 97934 |

## Objectivo do projecto
  - Desenvolver uma aplicação Java para suporte à gestão de horários do Iscte.

### Descrição
  1) O Carregamento de um horário a partir de um ficheiro com representação CSV, a conversão desse horário para representação JSON e gravar em ficheiro o horário com a representação JSON. Deverá também permitir o processo inverso, ou seja, a partir de um ficheiro com representação JSON, converter esse horário para representação CSV e gravar em ficheiro o horário com a representação CSV. A representação CSV do horário segue a seguinte estrutura (o ficheiro horario-exemplo.csv disponível na plataforma Moodle contém um exemplo de horário): Curso, Unidade Curricular, Turno, Turma, Inscritos no turno, Dia da semana, Hora início da aula, Hora fim da aula, Data da aula, Sala atribuída à aula, Lotação da sala. A estrutura da representação JSON do horário deverá ser definida pelo grupo de trabalho. Note que o utilizador deve poder indicar a localização do ficheiro (para carregar ou gravar) e o ficheiro poderá estar localmente, no sistema de ficheiros do computador, ou remotamente na web (por exemplo, no GitHub);
  
  2) O carregamento de um horário a partir de um ficheiro (CSV ou JSON) e a representação desse horário em HTML, por forma a permitir a visualização e navegação do horário num browser web, por dia, semana e mês (alternativamente o grupo poderá implementar esta parte da GUI em Java). Para a visualização semanal do horário, poderá ser usada como referência a visualização e navegação dos horários na plataforma Fénix;

  3) A criação do horário de um estudante na aplicação a partir dos dados do seu horário na plataforma Fénix. O utilizador deverá poder indicar os dados do horário pré-definido do estudante, através da indicação do Uniform Resource Identifier (URI) Webcal do horário do estudante na plataforma Fénix. O URI do horário de um estudante pode ser obtido através dos seguintes menus, na plataforma Fénix: Separador “Pessoal”, secção “Área Pessoal”, opção “Calendário pessoal web”. O URI terá uma estrutura do tipo webcal://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=… . A aplicação deverá carregar o horário do estudante diretamente a partir da plataforma Fénix, usando um cliente HTTP e tratando a representação webcal do horário obtido no Fénix;

  4) A criação de um horário para um novo estudante na aplicação. O utilizador deverá poder escolher livremente um (sub)conjunto de Unidades Curriculares (UCs) existentes num horário que tenha sido carregado para a aplicação, associar este (sub)conjunto de UCs ao estudante e desta forma definir o horário do estudante. Deverá ser permitido gravar o horário do estudante em ficheiro com representação CSV ou JSON;

  5) Visualização das aulas em sobreposição e em sobrelotação (identificação de cada uma das ocorrências e dos totais de ocorrências), a partir de um horário carregado para a aplicação.
