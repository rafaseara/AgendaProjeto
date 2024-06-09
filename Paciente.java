package consultas;

public class Paciente extends Pessoa {
    private int contatoEmergencia;
    private String alergiaMedicamentos;
    private int numeroConsultas;

    public Paciente(String nomeCompleto, String dataNascimento, String sexo, int documento, int telefone, int contatoEmergencia, String alergiaMedicamentos) {
        super(nomeCompleto, dataNascimento, sexo, documento, telefone);
        this.contatoEmergencia = contatoEmergencia;
        this.alergiaMedicamentos = alergiaMedicamentos;
        this.numeroConsultas = 0;
    }
    
    public int getNumeroConsultas() {
		return numeroConsultas;
	}

	public void setNumeroConsultas(int numeroConsultas) {
		this.numeroConsultas = numeroConsultas;
	}

	public void setContatoEmergencia(int contatoEmergencia) {
		this.contatoEmergencia = contatoEmergencia;
	}

	public void setAlergiaMedicamentos(String alergiaMedicamentos) {
		this.alergiaMedicamentos = alergiaMedicamentos;
	}

	public int getContatoEmergencia() {
        return contatoEmergencia;
    }

    public String getAlergiaMedicamentos() {
        return alergiaMedicamentos;
    }

    public void incrementarConsultas() {
        numeroConsultas++;
    }

    public String getHistoricoMedico() {
        return "Número de consultas realizadas: " + numeroConsultas + ", Alergia a medicamentos: " + (alergiaMedicamentos.isEmpty() ? "Nenhuma" : alergiaMedicamentos);
    }
  
}