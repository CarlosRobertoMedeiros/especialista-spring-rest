package com.example.algafoodapi.modelo;
/*
 *  @criado em: 12/06/2020 - {05:22}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

public class Cliente {

    private String nome;
    private String email;
    private String telefone;
    private boolean ativo = false;

    public Cliente(String nome, String email, String telefone, boolean ativo) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void ativar() {
        this.ativo = true;
    }
}
