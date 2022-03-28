package br.com.alura.alura.usuario;

public class UsuarioDto {

    private String nome;
    private String email;

    public UsuarioDto() {}

    public UsuarioDto(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getSenha();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
