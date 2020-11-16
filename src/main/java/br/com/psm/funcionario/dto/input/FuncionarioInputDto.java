package br.com.psm.funcionario.dto.input;

import br.com.psm.funcionario.entity.CargoEntity;
import br.com.psm.funcionario.entity.FuncionarioEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FuncionarioInputDto {

    private String nome;
    private String dataNascimento;
    private String documento;
    private Long cargo;
    private Long departamento;

    public FuncionarioInputDto() {
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

    public Long getCargo() {
        return cargo;
    }

    public void setCargo(Long cargo) {
        this.cargo = cargo;
    }

    public Long getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Long departamento) {
        this.departamento = departamento;
    }

    public FuncionarioEntity toEntity(CargoEntity cargo) {
        FuncionarioEntity funcionario = new FuncionarioEntity();
        funcionario.setNome(this.nome);
        funcionario.setDocumento(this.documento);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        funcionario.setDataAniversario(LocalDate.parse(this.dataNascimento, formatter));
        funcionario.setCargo(cargo);
        funcionario.setAtivo(Boolean.TRUE);
        return funcionario;
    }

}
