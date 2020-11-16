package br.com.psm.funcionario.dto.ouput;

import br.com.psm.funcionario.entity.FuncionarioEntity;

import java.time.format.DateTimeFormatter;

public class FuncionarioOutputDto {

    private Long id;
    private String nome;
    private String dataNascimento;
    private String documento;
    private String cargo;
    private String ativo;

    public FuncionarioOutputDto() {
    }

    public FuncionarioOutputDto(FuncionarioEntity funcionarioEntity) {
        this.id = funcionarioEntity.getId();
        this.nome = funcionarioEntity.getNome();
        this.dataNascimento = funcionarioEntity.getDataAniversario().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.documento = funcionarioEntity.getDocumento();
        this.cargo = funcionarioEntity.getCargo().getNome();
        this.ativo = funcionarioEntity.getAtivo() ? "ATIVO" : "INATIVO";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }
}
