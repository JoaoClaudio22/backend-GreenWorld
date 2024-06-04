package br.com.greenworld.Green.World.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "residencias")
public class Residencia{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 50)
	private String rua;
	
	@Column(nullable = false, length = 50)
	private String bairro;
	
	@Column(nullable = false, length = 50)
	private String cidade;
	
	@Column(nullable = false, length = 20)
	private String estado;
	
	@Column(nullable = false)
	private Integer qntdMoradores;
	
	@Column(nullable = true)
	private Double areaTelhado;
	
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	@JsonBackReference
	private Usuario dono;
	
	
	
	
	@OneToOne(mappedBy = "residencia", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Relatorio relatorio;

	

	public Relatorio getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(Relatorio relatorio) {
		this.relatorio = relatorio;
	}

	public Usuario getDono() {
		return dono;
	}

	public void setDono(Usuario dono) {
		this.dono = dono;
	}

	public Long getId() {
		return id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getQntdMoradores() {
		return qntdMoradores;
	}

	public void setQntdMoradores(Integer qntdMoradores) {
		this.qntdMoradores = qntdMoradores;
	}

	public Double getAreaTelhado() {
		return areaTelhado;
	}

	public void setAreaTelhado(Double areaTelhado) {
		this.areaTelhado = areaTelhado;
	}
	
	
	
	
	
	
}
