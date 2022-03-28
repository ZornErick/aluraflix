package br.com.alura.alura.usuario;

import javax.validation.constraints.NotBlank;

public class UsuarioForm {

    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    private String senha;

    UsuarioForm() {}

    public UsuarioForm(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario convert() {
        return new Usuario(this.nome, this.email, this.senha);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
