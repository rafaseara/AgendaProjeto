package consultas;
 
public class Medico{
	private String nomeCompleto;
	private TipoConsulta especialidade;
	private String horarioAtendimento;
	private boolean disponibilidade;
	
	public Medico(String nomeCompleto, TipoConsulta especialidade,
			String horarioAtendimento, boolean disponibilidade) {
		this.nomeCompleto = nomeCompleto;
		this.especialidade = especialidade;
		this.horarioAtendimento = horarioAtendimento;
		this.disponibilidade = disponibilidade;
	}
 
	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
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