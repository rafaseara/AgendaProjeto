package application;

import java.util.Scanner;
import consultas.AgendaConsultorio;

public class Consultorio {
    private AgendaConsultorio agenda;
    private Scanner scanner;

    public Consultorio() {
        agenda = new AgendaConsultorio();
        scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
        	System.out.println();
            System.out.println("\nAgenda Consultório");
            System.out.println("1. Adicionar Paciente");
            System.out.println("2. Listar Pacientes");
            System.out.println("3. Adicionar Médico");
            System.out.println("4. Listar Medicos");
            System.out.println("5. Agendar Consulta");
            System.out.println("6. Listar Consultas");
            System.out.println("7. Excluir Contato");
            System.out.println("8. Sair");
            System.out.println();
            System.out.print("Escolha uma opção: ");

            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    agenda.adicionarPaciente();
                    break;
                case 2:
                    agenda.listarPacientes();
                    break;
                case 3:
                    agenda.adicionarMedico();
                    break;
                case 4:
                    agenda.listarMedicos();
                    break;
                case 5:
                    agenda.agendarConsulta();
                    break;
                case 6:
                    agenda.listarConsultas();
                    break;
                case 7:
                    agenda.excluirContato();
                    break;
                case 8:
                    System.out.println("Você selecionou a opção sair");
                    return;
                default:
                    System.out.println("Opção inválida, tente novamente!");
            }
        }
    }

    public static void main(String[] args) {
        Consultorio consultorio = new Consultorio();
        consultorio.run();
    }
}