package consultas;

import java.util.ArrayList;
import java.util.Scanner;

public class AgendaConsultorio {
	
    private ArrayList<Paciente> pacientes;
    private ArrayList<Medico> medicos;
    private ArrayList<Consulta> consultas;
    private Scanner scanner;

    public AgendaConsultorio() {
        pacientes = new ArrayList<>();
        medicos = new ArrayList<>();
        consultas = new ArrayList<>();
        scanner = new Scanner(System.in); 
    }

    public void adicionarPaciente() {
        System.out.println();
        System.out.print("Digite o nome completo do paciente: ");
        String nomeCompleto = scanner.nextLine();

        System.out.print("Digite a data de nascimento: ");
        String dataNascimento = scanner.nextLine();

        System.out.print("Digite o sexo (f/m): ");
        String sexo = scanner.nextLine();

        System.out.print("Digite algum documento: ");
        String documento = scanner.nextLine();

        System.out.print("Digite algum telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("Digite algum contato de emergência caso necessário: ");
        String contatoEmergencia = scanner.nextLine();

        System.out.print("Possui alguma alergia a medicamento? (sim/não): ");
        String alergiaMedicamentos = scanner.nextLine();
        String tipoMedicamentos = "não";
        if (alergiaMedicamentos.equals("sim")) {
            System.out.print("Insira o nome do medicamento: ");
            tipoMedicamentos = scanner.nextLine();
        }

        Paciente paciente = new Paciente(nomeCompleto, dataNascimento, sexo, documento, telefone, contatoEmergencia,
                tipoMedicamentos, "", "");
        pacientes.add(paciente);
        System.out.println();
        System.out.print("Paciente adicionado com sucesso!");
    }

    public void listarPacientes() {
        if (pacientes.isEmpty()) {
            System.out.println();
            System.out.println("Nenhum paciente cadastrado");
        } else {
            System.out.println();
            for (int i = 0; i < pacientes.size(); i++) {
                System.out.println((i + 1) + ". " + pacientes.get(i).getNomeCompleto());
            }	
        }
    }

    public void adicionarMedico() {
        System.out.println();
        System.out.print("Digite o nome completo do médico: ");
        String nomeCompleto = scanner.nextLine();

        TipoConsulta especialidade = null;
        while (especialidade == null) {
            System.out.println("Escolha a especialidade:");
            TipoConsulta[] especialidades = TipoConsulta.values();
            for (int i = 0; i < especialidades.length; i++) {
                System.out.println((i + 1) + ". " + especialidades[i]);
            }
            System.out.print("Digite o número correspondente à especialidade: ");
            int especialidadeIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (especialidadeIndex >= 0 && especialidadeIndex < especialidades.length) {
                especialidade = especialidades[especialidadeIndex];
            } else {
                System.out.println();
                System.out.println("Número inválido, tente novamente.");
            }
        }

        System.out.print("Digite o horário de atendimento (formato: HH:mm): ");
        String horarioAtendimento = scanner.nextLine();

        System.out.print("O médico está disponível? (true/false): ");
        boolean disponibilidade = Boolean.parseBoolean(scanner.nextLine());

        Medico medico = new Medico(nomeCompleto, especialidade, horarioAtendimento, disponibilidade);
        medicos.add(medico);
        System.out.println();
        System.out.println("Médico adicionado com sucesso!");
    }

    public void listarMedicos() {
        if (medicos.isEmpty()) {
            System.out.println();
            System.out.println("Nenhum médico cadastrado!");
        } else {
            System.out.println();
            for (int i = 0; i < medicos.size(); i++) {
                System.out.println((i + 1) + ". " + medicos.get(i).getNomeCompleto());
            }
        }
    }

    public void agendarConsulta() {
        if (medicos.isEmpty() && pacientes.isEmpty()) {
            System.out.println();
            System.out.println("Nenhum paciente e médico cadastrado. Por favor, adicione pacientes e médicos antes de agendar consultas.");
            return;
        } else if (pacientes.isEmpty()) {
            System.out.println();
            System.out.println("Nenhum paciente cadastrado. Por favor, adicione pacientes antes de agendar consultas.");
            return;
        } else if (medicos.isEmpty()){
            System.out.println();
            System.out.println("Nenhum médico cadastrado. Por favor, adicione médicos antes de agendar consultas.");
            return;
        }

        System.out.println();
        System.out.println("Lista de Médicos:");
        System.out.println();

        for (int i = 0; i < medicos.size(); i++) {
            System.out.println((i + 1) + ". " + medicos.get(i).getNomeCompleto());
        }

        System.out.print("Escolha o médico pelo número correspondente: ");
        int medicoIndex = Integer.parseInt(scanner.nextLine());

        if (medicoIndex < 1 || medicoIndex > medicos.size()) {
            System.out.println("Índice de médico inválido.");
            return;
        }

        Medico medico = medicos.get(medicoIndex - 1);

        System.out.println();
        System.out.println("Lista de Pacientes:");
        System.out.println();
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println((i + 1) + ". " + pacientes.get(i).getNomeCompleto());
        }
        System.out.print("Escolha o paciente pelo número correspondente: ");
        int pacienteIndex = Integer.parseInt(scanner.nextLine());

        if (pacienteIndex < 1 || pacienteIndex > pacientes.size()) {
            System.out.println("Índice de paciente inválido.");
            return;
        }

        Paciente paciente = pacientes.get(pacienteIndex - 1);
        paciente.incrementarConsultas(); // Incrementa o número de consultas para o paciente

        System.out.print("Digite a data da consulta (formato: dd/MM/yyyy): ");
        String data = scanner.nextLine();
        System.out.print("Digite a hora da consulta (formato: HH:mm): ");
        String hora = scanner.nextLine();

        Consulta consulta = new Consulta(medico, paciente, data, hora);
        consultas.add(consulta);
        System.out.println("Consulta agendada com sucesso!");
    }

    public void listarConsultas() {
        if (consultas.isEmpty()) {
            System.out.println();
            System.out.println("Nenhuma consulta agendada");
        } else {
            System.out.println();
            int numeroConsulta = 1;
            for (Consulta consulta : consultas) {
                Paciente paciente = consulta.getPaciente();
                System.out.println(numeroConsulta + ". Médico: " + consulta.getMedico().getNomeCompleto() +
                                   ", Paciente: " + paciente.getNomeCompleto() +
                                   ", Data: " + consulta.getData() +
                                   ", Hora: " + consulta.getHora() +
                                   ", Histórico Médico: " + paciente.getHistoricoMedico() +
                                   ", Alergia a Medicamentos: " + paciente.getAlergiaMedicamento());
                numeroConsulta++;
            }
        }
    }

    public void excluirContato() {
        while (true) {
            System.out.println("\nExcluir Contato");
            System.out.println("1. Excluir Paciente");
            System.out.println("2. Excluir Médico");
            System.out.println("3. Voltar ao Menu");
            System.out.print("Escolha uma opção: ");

            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    excluirPaciente();
                    break;
                case 2:
                    excluirMedico();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opção inválida, tente novamente!");
            }
        }
    }

    private void excluirPaciente() {
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado");
            return;
        }
        System.out.println("Escolha um paciente pela posição na lista para excluir:");
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println((i + 1) + ". " + pacientes.get(i).getNomeCompleto());
        }
        int pacienteIndex = Integer.parseInt(scanner.nextLine());
        if (pacienteIndex >= 0 && pacienteIndex < pacientes.size()) {
            Paciente removido = pacientes.remove(pacienteIndex);
            System.out.println("Paciente " + removido.getNomeCompleto() + " excluído com sucesso!");
        } else {
            System.out.println("Índice de paciente inválido.");
        }
    }

    private void excluirMedico() {
        if (medicos.isEmpty()) {
            System.out.println("Nenhum médico cadastrado");
            return;
        }
        System.out.println("Escolha um médico pela posição na lista para excluir:");
        for (int i = 0; i < medicos.size(); i++) {
            System.out.println((i + 1) + ". " + medicos.get(i).getNomeCompleto());
        }
        int medicoIndex = Integer.parseInt(scanner.nextLine());
        if (medicoIndex >= 0 && medicoIndex < medicos.size()) {
            Medico removido = medicos.remove(medicoIndex);
            System.out.println("Médico " + removido.getNomeCompleto() + " excluído com sucesso!");
        } else {
            System.out.println("Índice de médico inválido.");
        }
    }

    public void sair() {
        scanner.close();
    }
}