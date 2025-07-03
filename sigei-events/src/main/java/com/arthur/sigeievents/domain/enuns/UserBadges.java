package com.arthur.sigeievents.domain.enuns;

import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum UserBadges {

    MESTRE_DOS_EVENTOS(1000, "Mestre dos Eventos", "Participou ou criou mais de 1000 pontos em eventos."),
    VETERANO(500, "Veterano", "Um participante experiente da comunidade."),
    ENTUSIASTA(250, "Entusiasta", "Já marcou presença em diversos eventos."),
    PARTICIPANTE_ATIVO(100, "Participante Ativo", "Começando a se destacar na plataforma."),
    INICIANTE(0, "Iniciante", "Novo membro da comunidade de eventos.");

    private final int pontosNecessarios;
    private final String nomeExibicao;
    private final String descricao;

    UserBadges(int pontosNecessarios, String nomeExibicao, String descricao) {
        this.pontosNecessarios = pontosNecessarios;
        this.nomeExibicao = nomeExibicao;
        this.descricao = descricao;
    }

    public static List<UserBadges> getDistintivosPorPontos(int pontos) {
        return Arrays.stream(UserBadges.values())
                .filter(distintivo -> pontos >= distintivo.getPontosNecessarios())
                .sorted(Comparator.comparingInt(UserBadges::getPontosNecessarios).reversed())
                .collect(Collectors.toList());
    }
}
