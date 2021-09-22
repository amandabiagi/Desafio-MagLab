package the.biagi.luizalabs.model;

import the.biagi.luizalabs.viacep.EnderecoViaCep;

public class Endereco {


    private String cep;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;

    public Endereco(EnderecoViaCep enderecoViaCep) {
        this.cep = enderecoViaCep.getCep();
        this.rua = enderecoViaCep.getLogradouro();
        this.bairro = enderecoViaCep.getBairro();
        this.cidade = enderecoViaCep.getLocalidade();
        this.estado = enderecoViaCep.getUf();
    }

    public Endereco() {
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
