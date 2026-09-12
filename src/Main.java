import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Plano> planosDisponiveis = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarPlanos();

        int opcao = -1;
        while (opcao != 0) {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> registrarConsumo();
                case 3 -> alterarPlano();
                case 4 -> listarClientes();
                case 0 -> System.out.println("Encerrando o sistema Telex. Até logo!");
                default -> System.out.println("Opção inválida! Tente novamente.\n");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n========== SISTEMA TELEX ==========");
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Registrar Consumo de Dados");
        System.out.println("3 - Upgrade / Downgrade de Plano");
        System.out.println("4 - Listar Clientes Cadastrados");
        System.out.println("0 - Sair");
        System.out.println("===================================");
    }

    // Fluxo: Cadastro
    private static void cadastrarCliente() {
        System.out.println("\n--- Cadastro de Cliente ---");
        System.out.print("Informe o CPF: ");
        String cpf = scanner.nextLine();

        if (buscarClientePorCpf(cpf) != null) {
            System.out.println("Erro: Já existe um cliente com este CPF!");
            return;
        }

        System.out.print("Informe o Nome: ");
        String nome = scanner.nextLine();

        System.out.println("\nPlanos disponíveis:");
        for (Plano p : planosDisponiveis) {
            System.out.println(p);
        }

        System.out.print("Informe o ID do plano desejado: ");
        int idPlano = Integer.parseInt(scanner.nextLine());

        Plano planoEscolhido = buscarPlanoPorId(idPlano);
        if (planoEscolhido == null) {
            System.out.println("Erro: Plano inválido! Cadastro cancelado.");
            return;
        }

        Cliente novoCliente = new Cliente(cpf, nome, planoEscolhido);
        clientes.add(novoCliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    // Fluxo: Consumo de Dados
    private static void registrarConsumo() {
        System.out.println("\n--- Registrar Consumo ---");
        System.out.print("Informe o CPF do cliente: ");
        String cpf = scanner.nextLine();

        Cliente cliente = buscarClientePorCpf(cpf);
        if (cliente == null) {
            System.out.println("Erro: Cliente não encontrado!");
            return;
        }

        System.out.print("Informe a quantidade de GB consumidos: ");
        double gb = Double.parseDouble(scanner.nextLine());

        cliente.registrarConsumo(gb);
        System.out.printf("Consumo registrado! Consumo total atual: %.2f GB\n", cliente.getConsumoGb());
    }

    // Fluxo: Upgrade / Downgrade
    private static void alterarPlano() {
        System.out.println("\n--- Upgrade / Downgrade de Plano ---");
        System.out.print("Informe o CPF do cliente: ");
        String cpf = scanner.nextLine();

        Cliente cliente = buscarClientePorCpf(cpf);
        if (cliente == null) {
            System.out.println("Erro: Cliente não encontrado!");
            return;
        }

        System.out.printf("Cliente: %s | Plano Atual: %s\n", cliente.getNome(), cliente.getPlano().getNome());
        System.out.print("Deseja realizar (U)pgrade ou (D)owngrade? ");
        String tipo = scanner.nextLine().trim().toUpperCase();

        int idAtual = cliente.getPlano().getId();

        if (tipo.equals("U")) {
            if (idAtual == planosDisponiveis.size()) {
                System.out.println("Aviso: O cliente já possui o plano máximo!");
                return;
            }
            Plano proximoPlano = buscarPlanoPorId(idAtual + 1);
            cliente.setPlano(proximoPlano);
            System.out.println("Upgrade concluído com sucesso para: " + proximoPlano.getNome());

        } else if (tipo.equals("D")) {
            if (idAtual == 1) {
                System.out.println("Aviso: O cliente já possui o plano mais básico!");
                return;
            }
            Plano planoAnterior = buscarPlanoPorId(idAtual - 1);
            cliente.setPlano(planoAnterior);
            System.out.println("Downgrade concluído com sucesso para: " + planoAnterior.getNome());

        } else {
            System.out.println("Opção de operação inválida!");
        }
    }

    private static void listarClientes() {
        System.out.println("\n--- Clientes Cadastrados ---");
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado no momento.");
            return;
        }
        for (Cliente c : clientes) {
            System.out.println(c);
        }
    }

    // Métodos auxiliares de busca
    private static Cliente buscarClientePorCpf(String cpf) {
        for (Cliente c : clientes) {
            if (c.getCpf().equalsIgnoreCase(cpf)) {
                return c;
            }
        }
        return null;
    }1

    private static Plano buscarPlanoPorId(int id) {
        for (Plano p : planosDisponiveis) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    private static void inicializarPlanos() {
        planosDisponiveis.add(new Plano(1, "Básico", 50, 49.90));
        planosDisponiveis.add(new Plano(2, "Intermediário", 100, 79.90));
        planosDisponiveis.add(new Plano(3, "Avançado", 200, 109.90));
        planosDisponiveis.add(new Plano(4, "Ultra", 500, 149.90));
    }
}