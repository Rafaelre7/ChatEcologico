package ENTIDADE;

public class Perfil {

	private int cod_perfil;
	private String descricao_perfil;

	public int getCod_perfil() {
		return cod_perfil;
	}

	public void setCod_perfil(int cod_perfil) {
		this.cod_perfil = cod_perfil;
	}

	public String getDescricao_perfil() {
		return descricao_perfil;
	}

	public void setDescricao_perfil(String descricao_perfil) {
		this.descricao_perfil = descricao_perfil;
	}
	public String toString() {
		return descricao_perfil;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cod_perfil;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Perfil other = (Perfil) obj;
		if (cod_perfil != other.cod_perfil)
			return false;
		return true;
	}

}