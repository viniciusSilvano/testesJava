package br.com.testeJava.entity.testeTransacaoConcorrentes;

import br.com.testeJava.entity.IEntidade;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.elasticsearch.common.util.CollectionUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@PersistenceUnit(name = "javaTesteProject")
@Table(name = "TB_GRUPO_HISTORICO")
public class GrupoHistorico implements IEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_GRUPO_HISTORICO")
    private Long id;

    @OneToMany (mappedBy = "grupo",
    fetch = FetchType.EAGER,
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    private List<HistoricoCibCar> historicos;

    @ManyToOne
    @JoinColumn(name = "ID_IMOVEL")
    @JsonIgnore
    private Imovel imovel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<HistoricoCibCar> getHistoricos() {
        return historicos;
    }

    public void setHistoricos(List<HistoricoCibCar> historicos) {
        this.historicos = historicos;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public void adicionarHistorico(HistoricoCibCar historicoCibCar) {

        historicoCibCar.setGrupo(this);
        if(historicos == null){
            historicos = new ArrayList<>();
        }

        if(!historicos.contains(historicoCibCar) || historicoCibCar.getId() == null){
            historicos.add(historicoCibCar);
        }


    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GrupoHistorico that = (GrupoHistorico) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
