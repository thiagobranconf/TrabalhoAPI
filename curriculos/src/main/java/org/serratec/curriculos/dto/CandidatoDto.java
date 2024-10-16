package org.serratec.curriculos.dto;

import java.time.LocalDate;

import org.serratec.curriculos.model.Candidato;
import org.serratec.curriculos.model.Escolaridade;
import org.serratec.curriculos.model.StatusCurriculo;
import org.serratec.curriculos.model.VagaDesejada;

public record CandidatoDto(
		Long id,
		String nome,
		LocalDate dataNascimento,
		Escolaridade escolaridade,
		VagaDesejada vagaDesejada,
		StatusCurriculo statusCurriculo
		) {
		
	public Candidato toEntity() {
		Candidato candidato = new Candidato();
		candidato.setId(this.id);
		candidato.setNome(this.nome);
		candidato.setDataNascimento(this.dataNascimento);
		candidato.setEscolaridade(this.escolaridade);
		candidato.setVagaDesejada(this.vagaDesejada);
		candidato.setStatusCurriculo(this.statusCurriculo);
		return candidato;
	}
	
	public static CandidatoDto toDto(Candidato candidato) {
		return new CandidatoDto(candidato.getId(), candidato.getNome(), candidato.getDataNascimento(),
				candidato.getEscolaridade(), candidato.getVagaDesejada(), candidato.getStatusCurriculo());
	}
}
