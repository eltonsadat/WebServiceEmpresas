package br.edu.unyleya.elton.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private String endereco;
        private String referencia;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	@Override
	public String toString() {
		return "Carro [id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", referencia=" + referencia + "]";
	}
}
