package net.felansu.sgi.model;

public enum Vagas {
	ESTUDANTE_NUTRICAO("Estudante de nutrição"), 
	ESTUDANTE_CIENCIAS_AGRARIAS("Estudante de ciências agrárias"), 
	ESTUDANTE_ADMINISTRACAO("Estudante de administração"), 
	CIENCIAS_CONTABEIS("Estudante de ciências contábeis"), 
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
