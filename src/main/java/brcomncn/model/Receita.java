package brcomncn.model;

import java.time.LocalDateTime;

public class Receita {
    private Integer id;
    private String titulo;
    private String categoria;
    private String tempoPreparo;
    private Integer porcoes;
    private String ingredientes;
    private String modoPreparo;
    private String fonte;
    private LocalDateTime dataCadastro;

    public Receita() {
        this.dataCadastro = LocalDateTime.now();
    }

    public Receita(String titulo, String categoria, String tempoPreparo, Integer porcoes,
                   String ingredientes, String modoPreparo, String fonte) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.tempoPreparo = tempoPreparo;
        this.porcoes = porcoes;
        this.ingredientes = ingredientes;
        this.modoPreparo = modoPreparo;
        this.fonte = fonte;
        this.dataCadastro = LocalDateTime.now();
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTempoPreparo() {
        return tempoPreparo;
    }

    public void setTempoPreparo(String tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }

    public Integer getPorcoes() {
        return porcoes;
    }

    public void setPorcoes(Integer porcoes) {
        this.porcoes = porcoes;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getModoPreparo() {
        return modoPreparo;
    }

    public void setModoPreparo(String modoPreparo) {
        this.modoPreparo = modoPreparo;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}