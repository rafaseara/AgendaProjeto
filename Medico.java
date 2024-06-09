package consultas;

public class Medico extends Pessoa {
    private TipoConsulta especialidade;
    private String horarioAtendimento;
    private boolean disponibilidade;

    public Medico(String nomeCompleto, String dataNascimento, String sexo, int documento, int telefone, TipoConsulta especialidade, String horarioAtendimento, boolean disponibilidade) {
        super(nomeCompleto, dataNascimento, sexo, documento, telefone);
        this.especialidade = especialidade;
        this.horarioAtendimento = horarioAtendimento;
        this.disponibilidade = disponibilidade;
    }
    
    public void setEspecialidade(TipoConsulta especialidade) {
		this.especialidade = especialidade;
	}

	public void setHorarioAtendimento(String horarioAtendimento) {
		this.horarioAtendimento = horarioAtendimento;
	}

	public void setDisponibilidade(boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	public TipoConsulta getEspecialidade() {
        return especialidade;
    }

    public String getHorarioAtendimento() {
        return horarioAtendimento;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }
	
	
}