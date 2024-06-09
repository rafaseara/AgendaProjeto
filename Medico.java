package consultas;

public class Medico extends Pessoa {
    private TipoConsulta especialidade;
    private String horarioAtendimento;
    private boolean disponibilidade;

    public Medico(String nomeCompleto, String dataNascimento, String sexo, String documento, String telefone, TipoConsulta especialidade, String horarioAtendimento, boolean disponibilidade) {
        super(nomeCompleto, dataNascimento, sexo, documento, telefone);
        this.especialidade = especialidade;
        this.horarioAtendimento = horarioAtendimento;
        this.disponibilidade = disponibilidade;
    }

	public TipoConsulta getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(TipoConsulta especialidade) {
		this.especialidade = especialidade;
	}

	public String getHorarioAtendimento() {
		return horarioAtendimento;
	}

	public void setHorarioAtendimento(String horarioAtendimento) {
		this.horarioAtendimento = horarioAtendimento;
	}

	public boolean isDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	
    
    
	
	
}