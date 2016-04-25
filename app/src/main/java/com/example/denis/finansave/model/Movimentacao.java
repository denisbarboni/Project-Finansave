package com.example.denis.finansave.model;

import java.util.Date;

/**
 * Created by denis on 13/04/2016.
 */
public class Movimentacao {

    private float valor;
    private String data;
    private String descricao;
    private TipoMovimentacao tipo;
    private long id;

    public Movimentacao(){

        //new Movimentacao(1l,2.40f,new Date().toString(),"compra de bala",TipoMovimentacao.DESPESA);

    }

    public Movimentacao(long id, Float valor, String data, String descricao, TipoMovimentacao tipo) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public Float getValor() {
        return valor;
    }
    public void setValor(Float valor) {
        this.valor = valor;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public TipoMovimentacao getTipo() {
        return tipo;
    }
    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Movimentacao{" +
                "valor='" + valor + '\'' +
                ", date='" + data + '\'' +
                ", descricao='" + descricao + '\''+
                ", tipo='" + tipo + '\'' +
                ", id=" + id +
                '}';
    }
}
