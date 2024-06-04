package br.com.greenworld.Green.World.services;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.greenworld.Green.World.Utils.CalculoUtil;
import br.com.greenworld.Green.World.model.Relatorio;
import br.com.greenworld.Green.World.model.Residencia;
import br.com.greenworld.Green.World.model.Usuario;
import br.com.greenworld.Green.World.repository.IRelatorio;
import br.com.greenworld.Green.World.repository.IResidencia;
import br.com.greenworld.Green.World.repository.IUsuario;

@Service
public class ResidenciaService {

	@Autowired
	private IUsuario usuarioDao;
	@Autowired
	private IResidencia residenciaDao;

	@Autowired
	private IRelatorio relatorioDao;

	CalculoUtil calculos = new CalculoUtil();

	public Residencia cadastrarResidencia(@RequestBody Residencia residencia, @RequestParam String login) {
		Usuario userOwner = (Usuario) usuarioDao.findByLogin(login);

		if (userOwner == null) {
			return null;
		} else {
			Relatorio relatorio = new Relatorio();

			residencia.setDono(userOwner);

			List<Residencia> residenciaList = userOwner.getResidenciaList();
			residenciaList.add(residencia);
			userOwner.setResidenciaList(residenciaList);

			if (residenciaList.get(0).getAreaTelhado() != null) {

				double reservatorioRecomendado;
				int qntdOcupantes;

				if (residenciaList.size() > 1) {
					int qntdDeImoveis = residenciaList.size();

					reservatorioRecomendado = calculos
							.calcularVolume(residenciaList.get(qntdDeImoveis - 1).getAreaTelhado());
					qntdOcupantes = residenciaList.get(qntdDeImoveis - 1).getQntdMoradores();
					relatorio.setReservatórioEscolhido(reservatorioRecomendado);

				} else {
					reservatorioRecomendado = calculos.calcularVolume(residenciaList.get(0).getAreaTelhado());
					qntdOcupantes = residenciaList.get(0).getQntdMoradores();
					relatorio.setReservatórioEscolhido(reservatorioRecomendado);
				}

				double alturaMax = calculos.definirMedidasAlturaMax(reservatorioRecomendado);
				double raioMenor = calculos.definirMedidasRaioMenor(reservatorioRecomendado);
				double raioMaior = calculos.definirMedidasRaioMaior(reservatorioRecomendado);

				Random random = new Random();

				double alturaAtual = random.nextDouble() * alturaMax;

				double volumeAtual = calculos.volumeAtual(alturaAtual, raioMaior, raioMenor);

				volumeAtual = Math.round(volumeAtual * 100) / 100;

				relatorio.setVolumeAtual(volumeAtual);
				relatorio.setVolumeTotal(reservatorioRecomendado);

				

				double gastoDiario = qntdOcupantes * 150;
				double gastoMensal = gastoDiario * 30;

				relatorio.setGastoDiario(gastoDiario);
				relatorio.setGastoMensal(gastoMensal);

				double porcentagemAtual = calculos.porcentagemAtual(reservatorioRecomendado, volumeAtual);

				relatorio.setPorcentagemAtual(porcentagemAtual);

				relatorio.setResidencia(residencia);

				relatorioDao.save(relatorio);
				residencia.setRelatorio(relatorio);
			} else {
				relatorio.setReservatórioEscolhido(null);
			}

			return residenciaDao.save(residencia);

		}

	}
	
	
	public List<Residencia> findResidenciaByLogin(@RequestParam String login){
		Usuario userOwner = (Usuario) usuarioDao.findByLogin(login);
		
		if(userOwner == null ) {
			return null;
		}
		
		List<Residencia> residenciaList =  userOwner.getResidenciaList();
		
		return residenciaList;
		
	}
	

}
