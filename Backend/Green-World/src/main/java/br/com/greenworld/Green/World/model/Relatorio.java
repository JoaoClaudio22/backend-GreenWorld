package br.com.greenworld.Green.World.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="relatorios")
public class Relatorio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double reservatórioEscolhido;
	
	private Double volumeTotal;
	
	private Double volumeAtual;
	
	private Double gastoDiario;
	
	private Double gastoMensal;
	
	private Double porcentagemAtual;
	
	@OneToOne
	@JoinColumn(name = "residencia_id")
	@JsonBackReference
	private Residencia residencia;
	
	
	
	
	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public Double getReservatórioEscolhido() {
		return reservatórioEscolhido;
	}




	public void setReservatórioEscolhido(Double reservatórioEscolhido) {
		this.reservatórioEscolhido = reservatórioEscolhido;
	}




	public Double getVolumeTotal() {
		return volumeTotal;
	}




	public void setVolumeTotal(Double volumeTotal) {
		this.volumeTotal = volumeTotal;
	}




	public Double getVolumeAtual() {
		return volumeAtual;
	}




	public void setVolumeAtual(Double volumeAtual) {
		this.volumeAtual = volumeAtual;
	}




	public Double getGastoDiario() {
		return gastoDiario;
	}




	public void setGastoDiario(Double gastoDiario) {
		this.gastoDiario = gastoDiario;
	}




	public Double getGastoMensal() {
		return gastoMensal;
	}




	public void setGastoMensal(Double gastoMensal) {
		this.gastoMensal = gastoMensal;
	}




	public Double getPorcentagemAtual() {
		return porcentagemAtual;
	}




	public void setPorcentagemAtual(Double porcentagemAtual) {
		this.porcentagemAtual = porcentagemAtual;
	}




	public Residencia getResidencia() {
		return residencia;
	}




	public void setResidencia(Residencia residencia) {
		this.residencia = residencia;
	}




	
	
	
	
	
	
}
