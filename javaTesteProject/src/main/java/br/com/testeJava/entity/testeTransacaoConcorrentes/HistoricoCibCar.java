package br.com.testeJava.entity.testeTransacaoConcorrentes;

import br.com.testeJava.entity.IEntidade;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@PersistenceUnit(name = "javaTesteProject")
@Table(name = "TB_HISTORICO_CAR_CIB")
public class HistoricoCibCar implements IEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_HISTORICO_CAR_CIB")
    private Long id;

    @Column
    private String mensagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "historico_car",           // Foreign key column in 'employee' table
            referencedColumnName = "ID_HISTORICO_CAR_CIB"    // Explicitly references 'id' column in 'employee' table
    )
    @JsonIgnore
    private HistoricoCibCar historicoCar;

    @Column
    private String situacaoCar;

    @Column
    private String situacaoCib;

    @Transient
    private boolean irregular = false;

    @OneToMany(mappedBy = "historicoCar", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistoricoCibCar> historicosCibs = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public HistoricoCibCar getHistoricoCar() {
        return historicoCar;
    }

    public void setHistoricoCar(HistoricoCibCar historicoCar) {
        this.historicoCar = historicoCar;
    }

    public List<HistoricoCibCar> getHistoricosCibs() {
        return historicosCibs;
    }

    public void setHistoricosCibs(List<HistoricoCibCar> historicosCibs) {
        this.historicosCibs = historicosCibs;
    }

    public String getSituacaoCar() {
        return situacaoCar;
    }

    public void setSituacaoCar(String situacaoCar) {
        this.situacaoCar = situacaoCar;
    }

    public String getSituacaoCib() {
        return situacaoCib;
    }

    public void setSituacaoCib(String situacaoCib) {
        this.situacaoCib = situacaoCib;
    }

    public boolean isIrregular() {
        return irregular;
    }

    public void setIrregular(boolean irregular) {
        this.irregular = irregular;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        HistoricoCibCar that = (HistoricoCibCar) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
