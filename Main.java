package wendel2;

import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

// Classe base Aluno
class Aluno {
    private String nome;
    private String curso;
    private LocalDate nascimento;
    private int aulasAssistidas;

    public Aluno(String nome, String curso, LocalDate nascimento, int aulasAssistidas) {
        this.nome = nome;
        this.curso = curso;
        this.nascimento = nascimento;
        this.aulasAssistidas = aulasAssistidas;
    }

    public String saudacao() {
        return "[Aluno] E aí! Eu sou " + nome + ", to fazendo o curso de " + curso + ". Bora estudar juntos!";
    }

    public int calcularIdade() {
        return Period.between(nascimento, LocalDate.now()).getYears();
    }

    public String relacaoAulas() {
        return "Você assistiu " + aulasAssistidas + " aulas até agora!";
    }

    public void receberAula(String aula) {
        // Mostra a aula recebida em um diálogo gráfico
        JOptionPane.showMessageDialog(null, "[Aluno] " + nome + " recebeu a aula: " + aula);
    }

    public String getNome() {
        return nome;
    }

    public String getCurso() {
        return curso;
    }
}

// Classe Professor que herda de Aluno
class Professor extends Aluno {
    private String formacao;
    private String area;

    public Professor(String nome, String curso, LocalDate nascimento, int aulasAssistidas, String formacao, String area) {
        super(nome, curso, nascimento, aulasAssistidas);
        this.formacao = formacao;
        this.area = area;
    }

    @Override
    public String saudacao() {
        return "[Professor] Bom dia! Sou o professor " + getNome() +
                ", formado em " + formacao + " e especializado em " + area +
                ". É um prazer recebê-los na aula de " + getCurso() + ".";
    }

    public String ministraAula(String nomeAula) {
        return "Aula ministrada: " + nomeAula + " | Professor: " + getNome() + " | Área: " + area;
    }
}

// Classe principal com interface gráfica
public class Main {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Entrada de dados do Aluno
        String nomeAluno = JOptionPane.showInputDialog("Digite o nome do aluno:");
        String cursoAluno = JOptionPane.showInputDialog("Digite o curso do aluno:");
        String nascimentoAlunoStr = JOptionPane.showInputDialog("Digite a data de nascimento do aluno (dd/MM/yyyy):");
        int aulasAssistidasAluno = Integer.parseInt(JOptionPane.showInputDialog("Quantas aulas o aluno já assistiu?"));

        Aluno aluno = new Aluno(nomeAluno, cursoAluno, LocalDate.parse(nascimentoAlunoStr, formatter), aulasAssistidasAluno);

        // Entrada de dados do Professor
        String nomeProfessor = JOptionPane.showInputDialog("Digite o nome do professor:");
        String cursoProfessor = JOptionPane.showInputDialog("Digite o curso do professor:");
        String nascimentoProfessorStr = JOptionPane.showInputDialog("Digite a data de nascimento do professor (dd/MM/yyyy):");
        int aulasAssistidasProfessor = Integer.parseInt(JOptionPane.showInputDialog("Quantas aulas o professor já assistiu?"));
        String formacaoProfessor = JOptionPane.showInputDialog("Qual a formação do professor?");
        String areaProfessor = JOptionPane.showInputDialog("Qual a área de especialização do professor?");

        Professor professor = new Professor(
                nomeProfessor,
                cursoProfessor,
                LocalDate.parse(nascimentoProfessorStr, formatter),
                aulasAssistidasProfessor,
                formacaoProfessor,
                areaProfessor
        );

        // Exibindo saudações
        JOptionPane.showMessageDialog(null, aluno.saudacao());
        JOptionPane.showMessageDialog(null, professor.saudacao());

        // Informações do aluno
        JOptionPane.showMessageDialog(null,
                "Aluno: " + nomeAluno + "\n" +
                aluno.relacaoAulas() + "\n" +
                "Idade: " + aluno.calcularIdade() + " anos.");

        // Professor ministra aula
        String nomeAula = JOptionPane.showInputDialog("Digite o nome da aula a ser ministrada:");
        String aulaMinistrada = professor.ministraAula(nomeAula);

        // Aluno recebe aula
        aluno.receberAula(aulaMinistrada);

        JOptionPane.showMessageDialog(null, "Fim do programa.");
    }
}
