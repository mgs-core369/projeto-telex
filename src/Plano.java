public class Plano {

    // Declarando os atributos (campos) da classe Plano
    private int id;            // Identificador único do plano (ex: 1, 2, 3)
    private String nome;       // Nome comercial do plano (ex: "Fibra 100", "Ultra")
    private int velocidadeMb;  // Velocidade da conexão contratada
    private double valor;      // Mensalidade em reais (R$)

    //Declarando os construtores
    public Plano(int id, String nome, int velocidadeMb, double valor) {
        this.id = id;
        this.nome = nome;
        this.velocidadeMb = velocidadeMb;
        this.valor = valor;
    }

    //Declarando os getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getVelocidadeMb() {
        return velocidadeMb;
    }

    public double getValor() {
        return valor;
    }

    //Declarando a sobrescrita para deixar o código legível no terminal
    @Override
    public String toString() {
        return String.format("[%d] %s - %d MB | R$ %.2f", id, nome, velocidadeMb, valor);
    }
}