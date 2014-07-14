package net.felansu.sgi.model;

public enum Vagas {
	ESTUDANTE_NUTRICAO("Estudante de nutri��o"), 
	ESTUDANTE_CIENCIAS_AGRARIAS("Estudante de ci�ncias agr�rias"), 
	ESTUDANTE_ADMINISTRACAO("Estudante de administra��o"), 
	CIENCIAS_CONTABEIS("Estudante de ci�ncias cont�beis"), 
	ESTUDANTE_PEDAGOGIA("Estudante de pedagogia");

	private String descricao;

	private Vagas(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
