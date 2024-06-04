package br.com.greenworld.Green.World.Utils;


public class CalculoUtil {


	public double calcularVolume(double areaTelhado) {

		double volume_maximo = 0.1 * 2000 * areaTelhado;
		double volume_real;

		if (volume_maximo <= 1000) {
			volume_real = 1000;
		} else if (volume_maximo <= 2000) {
			volume_real = 2000;
		} else if (volume_maximo <= 3000) {
			volume_real = 3000;
		} else if (volume_maximo <= 4000) {
			volume_real = 4000;
		} else if (volume_maximo <= 5000) {
			volume_real = 5000;
		} else if (volume_maximo <= 6000) {
			volume_real = 6000;
		} else if (volume_maximo <= 8000) {
			volume_real = 8000;
		} else if (volume_maximo <= 10000) {
			volume_real = 10000;
		} else if (volume_maximo <= 12000) {
			volume_real = 12000;
		} else if (volume_maximo <= 15000) {
			volume_real = 15000;
		} else {
			volume_real = 20000;
		}

		return volume_real;
	}

	public double definirMedidasRaioMaior(double reservatorioEscolhido) {
		double raio_maior = 0;

		switch ((int) reservatorioEscolhido) {
		case 1000:
			raio_maior = 0.72;
			break;
		case 2000:
			raio_maior = 0.83;
			break;
		case 3000:
			raio_maior = 0.95;
			break;
		case 4000:
			raio_maior = 1.135;
			break;
		case 5000:
			raio_maior = 1.135;
			break;
		case 6000:
			raio_maior = 1.335;
			break;
		case 8000:
			raio_maior = 1.42;
			break;
		case 10000:
			raio_maior = 1.42;
			break;
		case 12000:
			raio_maior = 1.45;
			break;
		case 15000:
			raio_maior = 1.45;
			break;
		case 20000:
			raio_maior = 1.51;
			break;
		}
		return raio_maior;

	}

	public double definirMedidasRaioMenor(double reservatorioEscolhido) {

		double raio_menor = 0;

		switch ((int) reservatorioEscolhido) {
		case 1000:
			raio_menor = 0.60;
			break;
		case 2000:
			raio_menor = 0.63;
			break;
		case 3000:
			raio_menor = 0.76;
			break;
		case 4000:
			raio_menor = 0.91;
			break;
		case 5000:
			raio_menor = 0.91;
			break;
		case 6000:
			raio_menor = 1.15;
			break;
		case 8000:
			raio_menor = 1.15;
			break;
		case 10000:
			raio_menor = 1.15;
			break;
		case 12000:
			raio_menor = 1.15;
			break;
		case 15000:
			raio_menor = 1.15;
			break;
		case 20000:
			raio_menor = 1.15;
			break;
		}
		return raio_menor;
	}

	public double definirMedidasAlturaMax(double reservatorioEscolhido) {

		double altura_maxima = 0;

		switch ((int) reservatorioEscolhido) {
		case 1000:
			altura_maxima = 0.73;
			break;
		case 2000:
			altura_maxima = 1.19;
			break;
		case 3000:
			altura_maxima = 1.30;
			break;
		case 4000:
			altura_maxima = 1.22;
			break;
		case 5000:
			altura_maxima = 1.52;
			break;
		case 6000:
			altura_maxima = 1.24;
			break;
		case 8000:
			altura_maxima = 1.54;
			break;
		case 10000:
			altura_maxima = 1.93;
			break;
		case 12000:
			altura_maxima = 2.25;
			break;
		case 15000:
			altura_maxima = 2.82;
			break;
		case 20000:

			altura_maxima = 3.58;
			break;
		}
		return altura_maxima;
	}
	
	
	public double volumeAtual(Double altura_atual,Double raio_maior, Double raio_menor) {

		double volume_atual = ((3.14 * altura_atual) / 3) * ((raio_maior * raio_maior) + (raio_maior * raio_menor) + (raio_menor * raio_menor)) * 1000;
		return volume_atual;
		
	}
	
	public double porcentagemAtual(Double reservatorio, Double volume_atual) {
		double porcentagem_atual = (volume_atual * 100) / reservatorio;
		
		return porcentagem_atual;
	}
}
