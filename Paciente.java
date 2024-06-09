package consultas;

public class Paciente extends Pessoa {
    private int numeroConsultas;
    private String dataConsulta;

    public Paciente(String nomeCompleto, String dataNascimento, String sexo, String documento, String telefone,
                    String contatoEmergencia, String alergiaMedicamento, String historicoMedico, String dataConsulta) {
        super(nomeCompleto, dataNascimento, sexo, documento, telefone, contatoEmergencia, alergiaMedicamento, historicoMedico);
        this.dataConsulta = dataConsulta;
        this.numeroConsultas = 0; 
    }

    public String getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public int getNumeroConsultas() {
        return numeroConsultas;
    }

    public void incrementarConsultas() {
        this.numeroConsultas++;
    }

    @Override
    public String getHistoricoMedico() {
        return numeroConsultas > 1 ? "Possui histórico médico" : "Não possui histórico médico";
    }

}