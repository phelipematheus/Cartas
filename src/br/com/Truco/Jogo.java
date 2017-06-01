package br.com.Truco;

import java.util.List;
import java.util.Scanner;

public class Jogo {
	
	private Jogador usuario = new Jogador();
	private Jogador computador = new Jogador();
	private final Baralho b;
	private Scanner input;
	private List<Jogador> jogadores;
	
	private Jogo() {
		b = new Baralho();
		input = new Scanner(System.in);
	}
	
	public static void main(String[] args) {

		Jogo jogo = new Jogo();
		jogo.inicio();
	}

	private void inicio() {
		System.out.println("Está começando o truco individual. Ele é o melhor de 3");

		for (int i = 0; i < 3; i++) {
			usuario.pegarMaoTruco(b);
			computador.pegarMaoTruco(b);
		}
		for (int i = 0; i < 3; i++) {
			// rodada entra aqui dentro e pode ser feita no maixom 3 vezes. Tendo um break caso na segunda o kra consigo ganhar o jogo
		}

	}

	private int rodada() {
		int pontoRodada = 1;
		System.out.println(
				"vez do usuário. Qual das suas 3 cartas você deseja descartar? (para responder digite de 1 a 3 da esquerda para a direita))");
		input.nextInt();
		while (input.equals(1) && usuario.getMaoJogador().get(0) == null
				|| input.equals(2) && usuario.getMaoJogador().get(1) == null
				|| input.equals(3) && usuario.getMaoJogador().get(2) == null) {

			if (input.equals(1) && usuario.getMaoJogador().get(0) != null) {
				System.out.println("Sua escolha foi a carta nmr 1");
				usuario.getMaoJogador().remove(0);
			} else if (input.equals(2) && usuario.getMaoJogador().get(1) != null) {
				System.out.println("Sua escolha foi a carta nmr 2");
				usuario.getMaoJogador().remove(1);
			} else if (input.equals(3) && usuario.getMaoJogador().get(2) != null) {
				System.out.println("Sua escolha foi a carta nmr 3");
				usuario.getMaoJogador().remove(2);
			}
		}

		// Vez do copmutador com sua maneira randomica
		return pontoRodada;
	}

	private int comparacaoCartas(Carta usuarioCarta, Carta computadorCarta) {
		
		//Comparando se as cartas forem iguais
		if (usuarioCarta.getValor() == computadorCarta.getValor()) {
				if (usuarioCarta.getValor() == 7) {
					if (usuarioCarta.getNipe().equals("Copas")) {
						return 1;
					} else {
						return -1;
					}
				} else if (usuarioCarta.getValor() == 1) {
					if (usuarioCarta.getNipe().equals("Espada")) {
						return 1;
					} else if ((usuarioCarta.getNipe().equals("Copas") || usuarioCarta.getNipe().equals("Paus")
							|| usuarioCarta.getNipe().equals("Ouro"))
							&& (computadorCarta.getNipe().equals("Copas")
									|| computadorCarta.getNipe().equals("Paus")
									|| computadorCarta.getNipe().equals("Ouro"))) {
						return 0;
					} else if (computadorCarta.getNipe().equals("Espada")) {
						return -1;
					}
				} else if(usuarioCarta.getValor() == 2 || usuarioCarta.getValor() == 3 
						|| usuarioCarta.getValor() == 11 || usuarioCarta.getValor() == 12 
						|| usuarioCarta.getValor() == 13){
					return 0;
				}	
		//Comparando se não for igual
		}else {
			//implementar essa parte
			return -10;
		}
			return 0;
	}

}
