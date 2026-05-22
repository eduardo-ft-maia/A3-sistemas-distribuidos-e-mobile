package br.com.rotabus.enums;

public enum TipoUsuario {

    ADMIN("Administrador"),
    USER("Usuário"),
    EMPRESA("Empresa");

    private final String descricao;

    TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}