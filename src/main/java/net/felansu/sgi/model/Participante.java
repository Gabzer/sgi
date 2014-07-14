package net.felansu.sgi.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="participante")
public class Participante implements Serializable {
	private static final long serialVersionUID = 6180980221516214464L;

	private Long id;
	private String nome;
	private Date dataNascimento;
	private String cpf;
	private String rg;
	private String endereco;
	private String telefone;
	private String email;
	private String instituicaoEnsinoSuperior;
	private String curso;
	private String periodo;
	private Vagas vagaSolicitada;
	private String anexo;
	private Timestamp dataInscricao;

	public Participante() {
		this.id = 0L;
		this.nome = null;
		this.dataNascimento = null;
		this.cpf = null;
		this.rg = null;
		this.endereco = null;
		this.telefone = null;
		this.email = null;
		this.instituicaoEnsinoSuperior = null;
		this.curso = null;
		this.periodo = null;
		this.vagaSolicitada = null;
		this.anexo = null;
		this.dataInscricao = new Timestamp(new Date().getTime());
	}

	public Participante(Long id, String nome, Date dataNascimento,
			String cpf, String rg, String endereco, String telefone,
			String email, String instituicaoEnsinoSuperior, String curso,
			String periodo, Vagas vagaSolicitada, String anexo) {
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.rg = rg;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		this.instituicaoEnsinoSuperior = instituicaoEnsinoSuperior;
		this.curso = curso;
		this.periodo = periodo;
		this.vagaSolicitada = vagaSolicitada;
		this.anexo = anexo;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="nome")
	@NotNull
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name="data_nascimento")
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Column(name="cpf")
	@NotNull
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Column(name="rg")
	@NotNull
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@Column(name="endereco")
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Column(name="telefone")
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Column(name="email")
	@NotNull
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="instituicao_ensino_superior")
	public String getInstituicaoEnsinoSuperior() {
		return instituicaoEnsinoSuperior;
	}

	public void setInstituicaoEnsinoSuperior(String instituicaoEnsinoSuperior) {
		this.instituicaoEnsinoSuperior = instituicaoEnsinoSuperior;
	}

	@Column(name="curso")
	@NotNull
	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	@Column(name="periodo")
	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	@Column(name="vaga_solicitada")
	@Enumerated(EnumType.STRING)
	@NotNull
	public Vagas getVagaSolicitada() {
		return vagaSolicitada;
	}

	public void setVagaSolicitada(Vagas vagaSolicitada) {
		this.vagaSolicitada = vagaSolicitada;
	}

	@Column(name="anexo")
	public String getAnexo() {
		return anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}

	@Column(name="data_inscricao")
	public Timestamp getDataInscricao() {
		return dataInscricao;
	}

	public void setDataInscricao(Timestamp dataInscricao) {
		this.dataInscricao = dataInscricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Participante other = (Participante) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
