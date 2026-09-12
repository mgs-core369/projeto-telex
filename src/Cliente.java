public class Cliente {
    private String cpf;
    private String nome;
    private double consumoGb;
    private Plano plano; // Relacionamento com a classe Plano

    public Cliente(String cpf, String nome, Plano plano) {
        this.cpf = cpf;
        this.nome = nome;
        this.plano = plano;
        this.consumoGb = 0.0; // Inicializa o consumo zerado
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public double getConsumoGb() {
        return consumoGb;
    }

    public Plano getPlano() {
        return plano;
    }

    // Método para registrar consumo (fluxo de consumo)
    public void registrarConsumo(double gb) {
        if (gb > 0) {
            this.consumoGb += gb;
        }
    }

    // Método para atualizar o plano (fluxo de upgrade/downgrade)
    public void setPlano(Plano novoPlano) {
        this.plano = novoPlano;
    }

    @Override
    public String toString() {
        return String.format("CPF: %s | Nome: %s | Consumo: %.2f GB | Plano Atual: %s (%d MB)",
                cpf, nome, consumoGb, plano.getNome(), plano.getVelocidadeMb());
    }
}