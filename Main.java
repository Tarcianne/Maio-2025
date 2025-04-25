package wendel;

import java.time.LocalDate;
import java.time.Period;

// Classe base Aluno
class Aluno {
    private String nome;
    private String curso;
    private LocalDate nascimento;
    private int aulasAssistidas;

    // Construtor da classe Aluno
    public Aluno(String nome, String curso, LocalDate nascimento, int aulasAssistidas) {
        this.nome = nome;
        this.curso = curso;
        this.nascimento = nascimento;
        this.aulasAssistidas = aulasAssistidas;
    }

    // Método de saudação
    public void saudacao() {
        System.out.println("[Aluno] E aí! Eu sou " + nome + ", to fazendo o curso de " + curso + ". Bora estudar juntos!");
    }

    // Método que calcula idade com base na data de nascimento
    public int calcularIdade() {
        return Period.between(nascimento, LocalDate.now()).getYears();
    }

    // Método que retorna quantas aulas o aluno já assistiu
    public String relacaoAulas() {
        return "Você assistiu " + aulasAssistidas + " aulas até agora!";
    }

    // Método que simula o aluno recebendo uma aula
    public void receberAula(String aula) {
        System.out.println("[Aluno] " + nome + " recebeu a aula: " + aula);
    }

    // Getter para acessar o nome do aluno (usado na classe Professor)
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

    // Construtor da classe Professor
    public Professor(String nome, String curso, LocalDate nascimento, int aulasAssistidas, String formacao, String area) {
        super(nome, curso, nascimento, aulasAssistidas);
        this.formacao = formacao;
        this.area = area;
    }

    // Sobrescreve o método saudacao() com um estilo mais formal
    @Override
    public void saudacao() {
        System.out.println("[Professor] Bom dia. Sou o professor " + getNome() +
                ", formado em " + formacao + " e especializado em " + area +
                ". É um prazer recebê-los na aula de " + getCurso() + ".");
    }

    // Método exclusivo do professor para ministrar aula
    public String ministraAula(String nomeAula) {
        return "Aula ministrada: " + nomeAula + " | Professor: " + getNome() + " | Área: " + area;
    }
}

// Classe principal onde tudo acontece
public class Main {
    public static void main(String[] args) {
        // Cria o objeto Aluno
        Aluno aluno = new Aluno("João", "Java Básico", LocalDate.of(2000, 5, 10), 12);

        // Cria o objeto Professor
        Aluno professor = new Professor("Carlos", "Java Avançado", LocalDate.of(1985, 3, 15), 5, "Computação", "Desenvolvimento de Software");

        System.out.println("=== SAUDAÇÕES ===");
        aluno.saudacao();       // Saudação do Aluno
        professor.saudacao();   // Saudação do Professor

        System.out.println("\n=== DETALHES DO ALUNO ===");
        System.out.println(aluno.relacaoAulas());
        System.out.println("Idade do aluno: " + aluno.calcularIdade() + " anos.");

        System.out.println("\n=== AULA MINISTRADA ===");
        // Verifica se professor é realmente um Professor
        if (professor instanceof Professor prof) {
            // Professor ministra aula
            String aula = prof.ministraAula("Herança em Java");

            // Aluno recebe essa aula
            aluno.receberAula(aula);
        }

        System.out.println("\n=== FIM DO PROGRAMA ===");
    }
}
