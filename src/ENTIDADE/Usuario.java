package ENTIDADE;

public class Usuario {

    private int cod_usuario;
    private String nome;
    private String email;
    private String senha;
    private String empresa;
    private String cod_perfil;
    private String cod_situacao;
    private Perfil perfil;
    private Situacao situacao;

    
    
    public String getCod_perfil() {
		return cod_perfil;
	}

	public void setCod_perfil(String cod_perfil) {
		this.cod_perfil = cod_perfil;
	}

	public String getCod_situacao() {
		return cod_situacao;
	}

	public void setCod_situacao(String cod_situacao) {
		this.cod_situacao = cod_situacao;
	}

	public int getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(int cod_usuario) {
        this.cod_usuario = cod_usuario;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public String toString() {
        return nome;
    }

}
