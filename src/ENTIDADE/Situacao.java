package ENTIDADE;

public class Situacao {

	private int cod_situacao;
	private String descricao_situacao;

	public int getCod_situacao() {
		return cod_situacao;
	}

	public void setCod_situacao(int cod_situacao) {
		this.cod_situacao = cod_situacao;
	}

	public String getDescricao_situacao() {
		return descricao_situacao;
	}

	public void setDescricao_situacao(String descricao_situacao) {
		this.descricao_situacao = descricao_situacao;
	}
	
	public String toString() {
		return descricao_situacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cod_situacao;
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
		Situacao other = (Situacao) obj;
		if (cod_situacao != other.cod_situacao)
			return false;
		return true;
	}

}
