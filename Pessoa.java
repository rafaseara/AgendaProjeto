package consultas;
 
public class Pessoa {
	private String nomeCompleto;
	private String dataNascimento;
	private String sexo;
	private String documento;
	private String telefone;
	private String contatoEmergencia;
	private String alergiaMedicamento;
	private String historicoMedico;
 
	public Pessoa(String nomeCompleto, String dataNascimento, String sexo, String documento, String telefone,
			String contatoEmergencia, String alergiaMedicamento, String historicoMedico) {
		super();
		this.nomeCompleto = nomeCompleto;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.documento = documento;
		this.telefone = telefone;
		this.contatoEmergencia = contatoEmergencia;
		this.alergiaMedicamento = alergiaMedicamento;
		this.historicoMedico = historicoMedico;
	}
	
	public String getNomeCompleto() {
		return nomeCompleto;
	}
 
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
 
	public String getDataNascimento() {
		return dataNascimento;
	}
 
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
 
	public String getSexo() {
		return sexo;
	}
 
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
 
	public String getDocumento() {
		return documento;
	}
 
	public void setDocumento(String documento) {
		this.documento = documento;
	}
 
	public String getTelefone() {
		return telefone;
	}
 
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
 
	public String getContatoEmergencia() {
		return contatoEmergencia;
	}
 
	public void setContatoEmergencia(String contatoEmergencia) {
		this.contatoEmergencia = contatoEmergencia;
	}
 
	public String getAlergiaMedicamento() {
		return alergiaMedicamento;
	}
 
	public void setAlergiaMedicamento(String alergiaMedicamento) {
		this.alergiaMedicamento = alergiaMedicamento;
	}
 
	public String getHistoricoMedico() {
		return historicoMedico;
	}
 
	public void setHistoricoMedico(String historicoMedico) {
		this.historicoMedico = historicoMedico;
	}
}