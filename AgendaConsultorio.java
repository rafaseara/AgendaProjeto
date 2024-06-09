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

        String sexo;
        do {
            System.out.print("Digite o sexo (f/m): ");
            sexo = scanner.nextLine();
        } while (!sexo.equals("f") && !sexo.equals("m"));

        System.out.print("Digite seu RG ou CPF (99.999.999-9/999.999.999-99): ");
        String documento = scanner.nextLine();

        System.out.print("Digite algum telefone: ((11) XXXX-XXXX): ");
        String telefone = scanner.nextLine();

        System.out.print("Digite algum contato de emergência caso necessário: ((11) XXXX-XXXX): ");
        String contatoEmergencia = scanner.nextLine();
        
        
        String alergiaMedicamentos;
        do {
            System.out.print("Possui alguma alergia a medicamento? (sim/não): ");
            alergiaMedicamentos = scanner.nextLine();
        } while (!alergiaMedicamentos.equals("sim") && !alergiaMedicamentos.equals("não"));
         
        String tipoMedicamentos= "";
        if (alergiaMedicamentos.equalsIgnoreCase("sim")) {
            System.out.print("Insira o nome do medicamento: ");
            tipoMedicamentos = scanner.nextLine();
        }

        Paciente paciente = new Paciente(nomeCompleto, dataNascimento, sexo, documento, telefone, contatoEmergencia, tipoMedicamentos);
        pacientes.add(paciente);
        System.out.println();
        System.out.println("Paciente adicionado com sucesso!");
    }

    public void listarPacientes() {
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado");
        } else {
            for (int i = 0; i < pacientes.size(); i++) {
                System.out.println((i + 1) + ". " + pacientes.get(i).getNomeCompleto());
            }
        }
    }

    public void adicionarMedico() {
        System.out.println();
        System.out.print("Digite o nome completo do médico: ");
        String nomeCompleto = scanner.nextLine();

        System.out.print("Digite a data de nascimento: ");
        String dataNascimento = scanner.nextLine();
        
        String sexo;
        do {
            System.out.print("Digite o sexo (f/m): ");
            sexo = scanner.nextLine();
        } while (!sexo.equals("f") && !sexo.equals("m"));

        System.out.print("Digite algum documento: ");
        String documento = scanner.nextLine();

        System.out.print("Digite algum telefone: ");
        String telefone = scanner.nextLine();

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

        String horarioAtendimento;
        do {
            System.out.print("Digite o horário de atendimento (formato: HH:mm): ");
            horarioAtendimento = scanner.nextLine();
        } while (!horarioAtendimento.matches("^([01]?[0-9]|2[0-3]):[0-5][0-9]$"));
        
        String resposta;
        boolean disponibilidade = false;
        do {
            System.out.print("O médico está disponível? (sim/não): ");
            resposta = scanner.nextLine();
            if (resposta.equals("sim")) {
                disponibilidade = true;
            } else if (resposta.equals("não")) {
                disponibilidade = false;
            } 
        } while (!resposta.equals("sim") && !resposta.equals("não"));

        Medico medico = new Medico(nomeCompleto, dataNascimento, sexo, documento, telefone, especialidade, horarioAtendimento, disponibilidade);
        medicos.add(medico);
        System.out.println();
        System.out.println("Médico adicionado com sucesso!");
    }

    public void listarMedicos() {
        if (medicos.isEmpty()) {
            System.out.println("Nenhum médico cadastrado!");
        } else {
        	for (int i = 0; i < medicos.size(); i++) {
                Medico medico = medicos.get(i);
                String disponibilidadeStr = medico.isDisponibilidade() ? "Disponível" : "Indisponível";
                System.out.println((i + 1) + ". " + medico.getNomeCompleto() + " - " + disponibilidadeStr);
            }
        }
    }
    
    public boolean isMedicoDisponivel(Medico medico, String data, String hora) {
        // Verificar se há alguma consulta para o médico na data especificada
    	
    	if (!medico.isDisponibilidade()) {
            return false;
        }
    	
        for (Consulta consulta : consultas) {
            if (consulta.getMedico().equals(medico) && consulta.getData().equals(data)) {
                // Obter a hora da consulta
                String horaConsulta = consulta.getHora();
                // Calcular a próxima hora após a consulta
                int horaConsultaInt = Integer.parseInt(horaConsulta.substring(0, 2));
                int proximaHoraConsultaInt = (horaConsultaInt + 1) % 24; // Incrementar uma hora
                // Formatar a próxima hora após a consulta
                String proximaHoraConsulta = String.format("%02d", proximaHoraConsultaInt) + horaConsulta.substring(2);
                // Comparar a hora atual com a próxima hora após a consulta
                if (hora.compareTo(proximaHoraConsulta) < 0) {
                    // A hora especificada é antes da próxima consulta
                    return false; // médico ocupado
                }
            }
        }
        return true;
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
        System.out.println("\u001B[1mAS CONSULTAS DEMORAM EM MÉDIA UMA HORA\u001B[0m");
        System.out.println();
        System.out.println("Lista de Médicos:");
        System.out.println();

        for (int i = 0; i < medicos.size(); i++) {
            Medico medico = medicos.get(i);
            System.out.println((i + 1) + ". " + medico.getNomeCompleto() + " - " + (medico.isDisponibilidade() ? "Está Disponível" : "Está Indisponível"));
        }

        System.out.print("Escolha o médico pelo número correspondente: ");
        int medicoIndex = Integer.parseInt(scanner.nextLine());

        if (medicoIndex < 1 || medicoIndex > medicos.size()) {
            System.out.println("Índice de médico inválido.");
            return;
        }

        Medico medico = medicos.get(medicoIndex - 1);
        if (!medico.isDisponibilidade()) {
            System.out.println("O médico selecionado não está disponível para consulta.");
            return;
        }
        
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
        paciente.incrementarConsultas();
        
        String data;
        do {
        	System.out.print("Digite a data da consulta (formato: dd/MM/yyyy): ");
            data = scanner.nextLine();
        } while (!data.matches("^\\d{2}/\\d{2}/\\d{4}$"));

        String hora;
        do {
        	System.out.print("Digite a hora da consulta (formato: HH:mm): ");
            hora = scanner.nextLine();
        } while (!hora.matches("^([01]?[0-9]|2[0-3]):[0-5][0-9]$"));
        
        if (!isMedicoDisponivel(medico, data, hora)) {
            System.out.println("O médico já tem uma consulta agendada nesse horário. Por favor, escolha outro horário.");
            return;
        }

        Consulta consulta = new Consulta(medico, paciente, data, hora);
        consultas.add(consulta);
        System.out.println("Consulta agendada com sucesso!");
    }

    public void listarConsultas() {
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta agendada");
        } else {
            int numeroConsulta = 1;
            for (Consulta consulta : consultas) {
                Paciente paciente = consulta.getPaciente();
                System.out.println(numeroConsulta + ". Médico: " + consulta.getMedico().getNomeCompleto() +
                                   ", Paciente: " + paciente.getNomeCompleto() +
                                   ", Data: " + consulta.getData() +
                                   ", Hora: " + consulta.getHora() +
                                   ", Histórico Médico: " + paciente.getHistoricoMedico());
                numeroConsulta++;
            }
        }
    }
    
    public void editar() {
        while (true) {
        	System.out.println(); 
            System.out.println("Selecione a opção que deseja excluir:");
            System.out.println("1. Paciente");
            System.out.println("2. Médico");
            System.out.println("3. Consulta");
            System.out.println("4. Voltar");

            System.out.print("Digite o número correspondente à opção desejada: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    editarPaciente();
                    break;
                case "2":
                    editarMedico();
                    break;
                case "3":
                    editarConsulta();
                    break;
                case "4":
                    return; 
                default:
                    System.out.println("Opção inválida! Por favor, selecione uma opção válida.");
            }
        }
    }
    
    private void editarPaciente() {
    	    if (pacientes.isEmpty()) {
    	        System.out.println("Nenhum paciente cadastrado.");
    	        return;
    	    }

    	    listarPacientes();
    	    
    	    System.out.print("Digite o número do paciente a ser editado: ");
    	    int pacienteIndex = Integer.parseInt(scanner.nextLine());
    	    
    	    if (pacienteIndex < 1 || pacienteIndex > pacientes.size()) {
    	        System.out.println("Índice de paciente inválido.");
    	        return;
    	    }

    	    Paciente paciente = pacientes.get(pacienteIndex - 1);

    	    System.out.println("Selecione o campo a ser editado:");
    	    System.out.println("1. Nome Completo");
    	    System.out.println("2. Data de Nascimento");
    	    System.out.println("3. Sexo");
    	    System.out.println("4. Documento");
    	    System.out.println("5. Telefone");
    	    System.out.println("6. Contato de Emergência");
    	    System.out.println("7. Alergia a Medicamentos");
    	    System.out.println("8. Voltar");

    	    System.out.print("Digite o número correspondente ao campo desejado: ");
    	    String campo = scanner.nextLine();

    	    switch (campo) {
    	        case "1":
    	            System.out.print("Digite o novo nome completo: ");
    	            paciente.setNomeCompleto(scanner.nextLine());
    	            break;
    	        case "2":
    	            System.out.print("Digite a nova data de nascimento: ");
    	            paciente.setDataNascimento(scanner.nextLine());
    	            break;
    	        case "3":
    	            System.out.print("Digite o novo sexo (f/m): ");
    	            paciente.setSexo(scanner.nextLine());
    	            break;
    	        case "4":
    	            System.out.print("Digite o novo documento: ");
    	            paciente.setDocumento(scanner.nextLine());
    	            break;
    	        case "5":
    	            System.out.print("Digite o novo telefone: ");
    	            paciente.setTelefone(scanner.nextLine());
    	            break;
    	        case "6":
    	            System.out.print("Digite o novo contato de emergência: ");
    	            paciente.setContatoEmergencia(scanner.nextLine());
    	            break;
    	        case "7":
    	            System.out.print("Possui alergia a medicamentos? (sim/não): ");
    	            String alergia = scanner.nextLine();
    	            
    	            if (alergia.equalsIgnoreCase("sim")) {
    	                System.out.print("Insira o nome do medicamento: ");
    	                paciente.setAlergiaMedicamentos(scanner.nextLine());
    	            } else {
    	                paciente.setAlergiaMedicamentos("não");
    	            }
    	            
    	            break;
    	        case "8":
    	            return;
    	        default:
    	            System.out.println("Opção inválida! Por favor, selecione uma opção válida.");
    	            return;
    	    }

    	    System.out.println("Informação do paciente atualizada com sucesso!");
    }
    
    private void editarMedico() {
        if (medicos.isEmpty()) {
            System.out.println("Nenhum médico cadastrado.");
            return;
        }

        listarMedicos();
        System.out.print("Digite o número do médico a ser editado: ");
        int medicoIndex = Integer.parseInt(scanner.nextLine());
        
        if (medicoIndex < 1 || medicoIndex > medicos.size()) {
            System.out.println("Índice de médico inválido.");
            return;
        }

        Medico medico = medicos.get(medicoIndex - 1);

        System.out.println("Selecione o campo a ser editado:");
        System.out.println("1. Nome Completo");
        System.out.println("2. Data de Nascimento");
        System.out.println("3. Sexo");
        System.out.println("4. Documento");
        System.out.println("5. Telefone");
        System.out.println("6. Especialidade");
        System.out.println("7. Horário de Atendimento");
        System.out.println("8. Disponibilidade");
        System.out.println("9. Voltar");

        System.out.print("Digite o número correspondente ao campo desejado: ");
        String campo = scanner.nextLine();

        switch (campo) {
            case "1":
                System.out.print("Digite o novo nome completo: ");
                medico.setNomeCompleto(scanner.nextLine());
                break;
            case "2":
                System.out.print("Digite a nova data de nascimento: ");
                medico.setDataNascimento(scanner.nextLine());
                break;
            case "3":
                System.out.print("Digite o novo sexo (f/m): ");
                medico.setSexo(scanner.nextLine());
                break;
            case "4":
                System.out.print("Digite o novo documento: ");
                medico.setDocumento(scanner.nextLine());
                break;
            case "5":
                System.out.print("Digite o novo telefone: ");
                medico.setTelefone(scanner.nextLine());
                break;
            case "6":
                TipoConsulta especialidade = null;
                while (especialidade == null) {
                    System.out.println("Escolha a nova especialidade:");
                    TipoConsulta[] especialidades = TipoConsulta.values();
                    for (int i = 0; i < especialidades.length; i++) {
                        System.out.println((i + 1) + ". " + especialidades[i]);
                    }
                    System.out.print("Digite o número correspondente à especialidade: ");
                    int especialidadeIndex = Integer.parseInt(scanner.nextLine()) - 1;
                    
                    if (especialidadeIndex >= 0 && especialidadeIndex < especialidades.length) {
                        especialidade = especialidades[especialidadeIndex];
                    } else {
                        System.out.println("Número inválido, tente novamente.");
                    }
                }
                medico.setEspecialidade(especialidade);
                break;
            case "7":
                System.out.print("Digite o novo horário de atendimento: ");
                medico.setHorarioAtendimento(scanner.nextLine());
                break;
            case "8":
                System.out.print("O médico está disponível? (true/false): ");
                medico.setDisponibilidade(Boolean.parseBoolean(scanner.nextLine()));
                break;
            case "9":
                return;
            default:
                System.out.println("Opção inválida! Por favor, selecione uma opção válida.");
                return;
        }

        System.out.println("Informação do médico atualizada com sucesso!");
    }
    
    	private void editarConsulta() {
    	    if (consultas.isEmpty()) {
    	        System.out.println("Nenhuma consulta agendada.");
    	        return;
    	    }

    	    listarConsultas();
    	    System.out.print("Digite o número da consulta a ser editada: ");
    	    int consultaIndex = Integer.parseInt(scanner.nextLine());
    	    if (consultaIndex < 1 || consultaIndex > consultas.size()) {
    	        System.out.println("Índice de consulta inválido.");
    	        return;
    	    }

    	    Consulta consulta = consultas.get(consultaIndex - 1);

    	    System.out.println("Selecione o campo a ser editado:");
    	    System.out.println("1. Data");
    	    System.out.println("2. Hora");
    	    System.out.println("3. Paciente");
    	    System.out.println("4. Médico");
    	    System.out.println("5. Voltar");

    	    System.out.print("Digite o número correspondente ao campo desejado: ");
    	    String campo = scanner.nextLine();

    	    switch (campo) {
    	        case "1":
    	            System.out.print("Digite a nova data (formato dd/MM/yyyy): ");
    	            consulta.setData(scanner.nextLine());
    	            break;
    	        case "2":
    	            System.out.print("Digite a nova hora (formato HH:mm): ");
    	            consulta.setHora(scanner.nextLine());
    	            break;
    	        case "3":
    	            listarPacientes();
    	            System.out.print("Digite o número do novo paciente: ");
    	            int novoPacienteIndex = Integer.parseInt(scanner.nextLine());
    	            if (novoPacienteIndex < 1 || novoPacienteIndex > pacientes.size()) {
    	                System.out.println("Índice de paciente inválido.");
    	                return;
    	            }
    	            consulta.setPaciente(pacientes.get(novoPacienteIndex - 1));
    	            break;
    	        case "4":
    	            listarMedicos();
    	            System.out.print("Digite o número do novo médico: ");
    	            int novoMedicoIndex = Integer.parseInt(scanner.nextLine());
    	            if (novoMedicoIndex < 1 || novoMedicoIndex > medicos.size()) {
    	                System.out.println("Índice de médico inválido.");
    	                return;
    	            }
    	            consulta.setMedico(medicos.get(novoMedicoIndex - 1));
    	            break;
    	        case "5":
    	            return;
    	        default:
    	            System.out.println("Opção inválida! Por favor, selecione uma opção válida.");
    	    }
    	}
    
    public void excluir() {
        while (true) {
        	System.out.println(); 
            System.out.println("Selecione a opção que deseja excluir:");
            System.out.println("1. Paciente");
            System.out.println("2. Médico");
            System.out.println("3. Consulta");
            System.out.println("4. Voltar");

            System.out.print("Digite o número correspondente à opção desejada: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    excluirPaciente();
                    break;
                case "2":
                    excluirMedico();
                    break;
                case "3":
                    excluirConsulta();
                    break;
                case "4":
                    return; 
                default:
                    System.out.println("Opção inválida! Por favor, selecione uma opção válida.");
            }
        }
    }

    private void excluirPaciente() {
        while (true) {
            if (pacientes.isEmpty()) {
                System.out.println("Nenhum paciente cadastrado.");
                return;
            }
            
            System.out.println() ;          
            System.out.println("Digite o número do paciente a ser excluído (ou 0 para voltar): ");
            System.out.println("0. Voltar");
            listarPacientes();
            System.out.print("Opção: ");
            String input = scanner.nextLine();
            if (input.equals("0")) return;

            int pacienteIndex = Integer.parseInt(input);
            if (pacienteIndex < 1 || pacienteIndex > pacientes.size()) {
                System.out.println("Índice de paciente inválido.");
            } else {
                pacientes.remove(pacienteIndex - 1);
                System.out.println("Paciente excluído com sucesso!");
                return;
            }
        }
    }

    private void excluirMedico() {
        while (true) {
            if (medicos.isEmpty()) {
                System.out.println("Nenhum médico cadastrado.");
                return;
            }

            System.out.println() ;          
            System.out.println("Digite o número do médico a ser excluído (ou 0 para voltar): ");
            System.out.println("0. Voltar");
            listarMedicos();
            System.out.print("Opção: ");
            String input = scanner.nextLine();
            if (input.equals("0")) return;

            int medicoIndex = Integer.parseInt(input);
            if (medicoIndex < 1 || medicoIndex > medicos.size()) {
                System.out.println("Índice de médico inválido.");
            } else {
                medicos.remove(medicoIndex - 1);
                System.out.println("Médico excluído com sucesso!");
                return;
            }
        }
    }

    private void excluirConsulta() {
        while (true) {
            if (consultas.isEmpty()) {
                System.out.println("Nenhuma consulta agendada.");
                return;
            }

            System.out.println() ;          
            System.out.println("Digite o número da consulta a ser excluída (ou 0 para voltar): ");
            System.out.println("0. Voltar");
            listarConsultas();
            System.out.print("Opção: ");
            String input = scanner.nextLine();
            if (input.equals("0")) return;

            int consultaIndex = Integer.parseInt(input);
            if (consultaIndex < 1 || consultaIndex > consultas.size()) {
                System.out.println("Índice de consulta inválido.");
            } else {
                consultas.remove(consultaIndex - 1);
                System.out.println("Consulta excluída com sucesso!");
                return;
            }
        }
    }
}