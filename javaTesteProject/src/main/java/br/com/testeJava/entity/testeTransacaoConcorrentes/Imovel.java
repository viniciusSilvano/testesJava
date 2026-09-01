package br.com.testeJava.entity.testeTransacaoConcorrentes;

import br.com.testeJava.entity.IEntidade;

import javax.persistence.*;
import java.util.List;

@Entity
@PersistenceUnit(name = "javaTesteProject")
@Table(name = "TB_IMOVEL")
public class Imovel implements IEntidade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_IMOVEL")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Cib> cibs;

    @Column
    private String situacaoCar;

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

    public List<Cib> getCibs() {
        return cibs;
    }

    public void setCibs(List<Cib> cibs) {
        this.cibs = cibs;
    }

    public String getSituacaoCar() {
        return situacaoCar;
    }

    public void setSituacaoCar(String situacaoCar) {
        this.situacaoCar = situacaoCar;
    }
}
