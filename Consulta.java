package consultas;

public class Consulta {
    private Medico medico;
    private Paciente paciente;
    private String data;
    private String hora;

    public Consulta(Medico medico, Paciente paciente, String data, String hora) {
        this.medico = medico;
        this.paciente = paciente;
        this.data = data;
        this.hora = hora;
    }

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
    
}