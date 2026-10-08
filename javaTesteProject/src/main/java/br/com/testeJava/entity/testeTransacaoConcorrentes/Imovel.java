package br.com.testeJava.entity.testeTransacaoConcorrentes;

import br.com.testeJava.entity.IEntidade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @OneToMany(
            mappedBy = "imovel",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Cib> cibs = new ArrayList<>();

    @OneToMany(
            mappedBy = "imovel",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<GrupoHistorico> grupos = new ArrayList<>();

    @Column
    private String car;

    @OneToOne
    private Cidade cidade;

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

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public void adicionarCib(Cib cib) {
        if (cib == null) {
            return;
        }

        cibs.add(cib);
        cib.setImovel(this);
    }

    public void removerCib(Cib cib) {
        cibs.remove(cib);
        cib.setImovel(null);
    }

    public List<GrupoHistorico> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<GrupoHistorico> grupos) {
        this.grupos = grupos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Imovel)) return false;

        Imovel imovel = (Imovel) o;
        return id != null && Objects.equals(id, imovel.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void adicionarGrupoHistorico(GrupoHistorico grupoHistorico) {
        grupoHistorico.setImovel(this);
        if(grupos == null ){
            grupos = new ArrayList<>();
        }

        if(!grupos.contains(grupoHistorico) || grupoHistorico.getId() == null){
            grupos.add(grupoHistorico);
        }
    }
}
