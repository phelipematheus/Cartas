package br.com.Truco;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Jogo {

	private Jogador usuario = new Jogador();
	private Jogador computador = new Jogador();
	private final Baralho b;
	private Scanner input;
	private int[] jogadaAtual = new int[2];

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
		for (int j = 0; j < 3; j++) {
			b.embaralhar();
			for (int i = 0; i < 3; i++) {
				usuario.pegarMaoTruco(b);
				computador.pegarMaoTruco(b);
			}
			int[] partida = new int[3];
			for (int i = 0; i < 3; i++) {
				if (usuario.getPontoRodada() == 2 || computador.getPontoRodada() == 2) {
					break;
				}
				int resultado = rodada();
				if (resultado > 0) {
					// Jogador ganhou rodada
					usuario.setPontoRodada(usuario.getPontoRodada() + 1);
					partida[i] = 1;
					mostraResultadoRodada();
				} else if (resultado < 0) {
					// Computador perdeu rodada
					computador.setPontoRodada(computador.getPontoRodada() + 1);
					partida[i] = -1;
					mostraResultadoRodada();
				} else {
					// Cangou
					if (i == 2) {
						// Se empatou for na ultima rodada
						if (partida[0] == 1) {
							usuario.setPontoRodada(usuario.getPontoRodada() + 1);
							mostraResultadoRodada();
						} else if (partida[0] == -1) {
							computador.setPontoRodada(computador.getPontoRodada() + 1);
							mostraResultadoRodada();
						}
					}
				}
			}
			if (usuario.getPontoRodada() > computador.getPontoRodada()) {
				usuario.setPontoTruco(usuario.getPontoTruco() + 1);
				mostrarResultadoPartida();
			} else if (computador.getPontoRodada() > usuario.getPontoRodada()) {
				computador.setPontoTruco(computador.getPontoTruco() + 1);
				mostrarResultadoPartida();
			} else {
				System.out.println("ERRO PONTO INESPERADO - NINGUEM PONTUOU");
				mostrarResultadoPartida();
			}
			usuario.setPontoRodada(0);
			computador.setPontoRodada(0);
		}
		if(usuario.getPontoTruco() > computador.getPontoTruco()){
			System.out.println("O ganhador foi: Usuario com "+usuario.getPontoTruco()+" pontos contra "+computador.getPontoTruco()+" do Computador");
		}else if( computador.getPontoTruco() > usuario.getPontoTruco()){
			System.out.println("O ganhador foi: Computador com "+computador.getPontoTruco()+" pontos contra "+usuario.getPontoTruco()+" do Usuario");
		}
		System.out.println("Fim");

	}

	private int rodada() {

		ArrayList<Carta> mesa = new ArrayList<>();
		System.out.println("Vez do usuário. Qual das suas cartas você deseja descartar?");
		for (Carta carta : usuario.getMaoJogador()) {
			int opcao = (usuario.getMaoJogador().indexOf(carta) + 1);
			System.out.println("Opção: " + opcao + " Carta: " + carta.getValor() + " de " + carta.getNipe());
		}
		int respUsuario = input.nextInt();
		if (respUsuario == 1) {
			System.out.println("Sua escolha foi " + usuario.getMaoJogador().get(0).getValor() + " de"
					+ usuario.getMaoJogador().get(0).getNipe());
			mesa.add(usuario.getMaoJogador().get(0));
			jogadaAtual[0] = 0;
		} else if (respUsuario == 2) {
			System.out.println("Sua escolha foi " + usuario.getMaoJogador().get(1).getValor() + " de"
					+ usuario.getMaoJogador().get(1).getNipe());
			mesa.add(usuario.getMaoJogador().get(1));
			jogadaAtual[0] = 1;
		} else if (respUsuario == 3) {
			System.out.println("Sua escolha foi " + usuario.getMaoJogador().get(2).getValor() + " de"
					+ usuario.getMaoJogador().get(2).getNipe());
			mesa.add(usuario.getMaoJogador().get(2));
			jogadaAtual[0] = 2;
		}

		System.out.println("Vez do computador.");
		// Adiciona na mesa uma carta aleatória que está na mão do jogandor
		int rd = intRandom(0, computador.getMaoJogador().size());
		jogadaAtual[1] = rd;
		System.out.println("A escolha do computador foi " + computador.getMaoJogador().get(rd).getValor() + " de"
				+ computador.getMaoJogador().get(rd).getNipe());
		mesa.add(computador.getMaoJogador().get(rd));

		/*
		 * Comparar jogador - computador, se resultado > 0 = jogador ganhou. Se
		 * resultado < 0 = computador ganhou. else cangou!
		 */
		int indUser = b.acharCarta(usuario.getMaoJogador().get(jogadaAtual[0]));
		int indComp = b.acharCarta(computador.getMaoJogador().get(jogadaAtual[1]));
		if (indUser == -1) {
			System.out.println("ERRO CARTA NÃO ENCONTRADA(JOGADOR)");
			System.exit(1);
		}
		if (indComp == -1) {
			System.out.println("ERRO CARTA NÃO ENCONTRADA(COMPUTADOR)");
			System.exit(1);
		}
		int resposta = indUser - indComp;
		usuario.removerMaoJogador(jogadaAtual[0]);
		computador.removerMaoJogador(jogadaAtual[1]);
		return resposta;
	}

	public int intRandom(int min, int max) {
		Random random = new Random();
		int randomNum = random.nextInt(((max - 1) - min) + 1) + min;
		return randomNum;
	}

	public void mostraResultadoRodada() {
		System.out.println("Pontuação da rodada:/n Usuario: " + usuario.getPontoRodada() + "/n Computador: "
				+ computador.getPontoRodada() + "/n/n/n");
	}

	public void mostrarResultadoPartida() {
		System.out.println("Pontuação da rodada:/n Usuario: " + usuario.getPontoTruco() + "/n Computador: "
				+ computador.getPontoTruco() + "/n/n/n");
	}

}
