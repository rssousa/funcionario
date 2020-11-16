package br.com.psm.funcionario.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "funcionario_departamento")
public class FuncionarioDepartamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private FuncionarioEntity funcionario;

    @ManyToOne
    @JoinColumn(name = "departamento_id", nullable = false)
    private DepartamentoEntity departamento;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    private Boolean chefe;

    @PrePersist
    public void prePersist() {
        this.dataInicio = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public FuncionarioEntity getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioEntity idFuncionario) {
        this.funcionario = idFuncionario;
    }

    public DepartamentoEntity getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoEntity idDepartamento) {
        this.departamento = idDepartamento;
    }

    public Boolean getChefe() {
        return chefe;
    }

    public void setChefe(Boolean chefe) {
        this.chefe = chefe;
    }
}
