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
            System.out.println("\nAgenda Consultório");
            System.out.println("1. Adicionar Paciente");
            System.out.println("2. Listar Pacientes");
            System.out.println("3. Adicionar Médico");
            System.out.println("4. Listar Medicos");
            System.out.println("5. Agendar Consulta");
            System.out.println("6. Listar Consultas");
            System.out.println("7. Editar");
            System.out.println("8. Excluir");
            System.out.println("9. Sair");
            System.out.println();
            System.out.print("Escolha uma opção: ");

            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                	System.out.println();
                	System.out.println("\u001B[1mVOCÊ ESCOLHEU ADICIONAR UM PACIENTE!\u001B[0m");
                    agenda.adicionarPaciente();
                    break;
                case 2:
                	System.out.println();
                	System.out.println("\u001B[1mVOCÊ ESCOLHEU LISTAR OS PACIENTES!\u001B[0m");
                    agenda.listarPacientes();
                    break;
                case 3:
                	System.out.println();
                	System.out.println("\u001B[1mVOCÊ ESCOLHEU ADICIONAR UM MÉDICO!\u001B[0m");
                    agenda.adicionarMedico();
                    break;
                case 4:
                	System.out.println();
                	System.out.println("\u001B[1mVOCÊ ESCOLHEU LISTAR OS MÉDICOS!\u001B[0m");
                    agenda.listarMedicos();
                    break;
                case 5:
                	System.out.println();
                	System.out.println("\u001B[1mVOCÊ ESCOLHEU AGENDAR UMA CONSULTA!\u001B[0m");
                    agenda.agendarConsulta();
                    break;
                case 6:
                	System.out.println();
                	System.out.println("\u001B[1mVOCÊ ESCOLHEU LISTAR AS CONSULTAS\u001B[0m");
                    agenda.listarConsultas();
                    break;
                case 7:
                	System.out.println();
                	System.out.println("\u001B[1mVOCÊ ESCOLHEU EDITAR!\u001B[0m");
                    agenda.editar();
                    break;
                case 8:
                	System.out.println();
                	System.out.println("\u001B[1mVOCÊ ESCOLHEU EXCLUIR!\u001B[0m");
                    agenda.excluir();
                    break;
                case 9:
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