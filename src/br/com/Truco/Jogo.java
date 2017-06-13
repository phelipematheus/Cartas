package br.com.Truco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Jogo {

	private Jogador usuario = new Jogador();
	private Jogador computador = new Jogador();
	private Baralho b;
	private Scanner input;
	private int[] jogadaAtual = new int[2];
	private int ponto = 1;

	private Jogo() {
		input = new Scanner(System.in);
	}

	public static void main(String[] args) {
		Jogo jogo = new Jogo();
		jogo.inicio();
	}

	private void inicio() {
		System.out.println("Está começando o truco individual. Ele é o melhor de 3");
		for (int j = 0; j < 3; j++) {
			// Início de uma partida
			b = new Baralho();
			b.embaralhar();
			for (int i = 0; i < 3; i++) {
				// Início de uma rodada
				ponto = 1;
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
					// Usuario ganhou rodada
					usuario.setPontoRodada(usuario.getPontoRodada() + ponto);
					usuario.setGanhou(true);
					computador.setGanhou(false);
					partida[i] = 1;
					mostraResultadoRodada();
				} else if (resultado < 0) {
					// Computador perdeu rodada
					computador.setPontoRodada(computador.getPontoRodada() + ponto);
					computador.setGanhou(true);
					usuario.setGanhou(false);
					partida[i] = -1;
					mostraResultadoRodada();
				} else {
					// Cangou
					if (i == 0) {
						// étodo em que mostra as duas cartas e compara
						// a maior com a maior
						cangouPrimeira();
						break;
					}
					if (i == 1) {
						if (partida[0] == 1) {
							usuario.setPontoRodada(usuario.getPontoRodada() + ponto);
							mostraResultadoRodada();
						} else if (partida[0] == -1) {
							computador.setPontoRodada(computador.getPontoRodada() + ponto);
							mostraResultadoRodada();
						}
					}
					if (i == 2) {
						// Se empatou e for na ultima rodada
						if (partida[0] == 1) {
							usuario.setPontoRodada(usuario.getPontoRodada() + ponto);
							mostraResultadoRodada();
						} else if (partida[0] == -1) {
							computador.setPontoRodada(computador.getPontoRodada() + ponto);
							mostraResultadoRodada();
						}
					}
				}
			}
			if (usuario.getPontoRodada() > computador.getPontoRodada()) {
				usuario.setPontoTruco(usuario.getPontoTruco() + ponto);
				mostrarResultadoPartida();
			} else if (computador.getPontoRodada() > usuario.getPontoRodada()) {
				computador.setPontoTruco(computador.getPontoTruco() + ponto);
				mostrarResultadoPartida();
			} else {
				System.out.println("ERRO PONTO INESPERADO - NINGUEM PONTUOU");
				mostrarResultadoPartida();
			}
			usuario.setPontoRodada(0);
			computador.setPontoRodada(0);
		}
		if (usuario.getPontoTruco() > computador.getPontoTruco()) {
			System.out.println("O ganhador foi: Usuario com " + usuario.getPontoTruco() + " pontos contra "
					+ computador.getPontoTruco() + " do Computador");
		} else if (computador.getPontoTruco() > usuario.getPontoTruco()) {
			System.out.println("O ganhador foi: Computador com " + computador.getPontoTruco() + " pontos contra "
					+ usuario.getPontoTruco() + " do Usuario");
		}
		System.out.println("Fim");

	}

	private int rodada() {
		ArrayList<Carta> mesa = new ArrayList<>();

		if (usuario.isGanhou() || !(usuario.isGanhou()) && !(computador.isGanhou())) {
			System.out.println("Vez do usuário. Qual das suas cartas você deseja descartar?");
			if (mesa == vezDoUsuario(mesa, false)) {
				mesa = vezDoUsuario(mesa, true);
			}
			System.out.println("Vez do computador.");
			if (mesa == vezDoComputador(mesa, false)) {
				vezDoComputador(mesa, true);
			}
		} else {
			System.out.println("Vez do computador.");
			if (mesa == vezDoComputador(mesa, false)) {
				vezDoComputador(mesa, true);
			}
			System.out.println("Vez do usuário. Qual das suas cartas você deseja descartar?");
			if (mesa == vezDoUsuario(mesa, false)) {
				mesa = vezDoUsuario(mesa, true);
			}
		}
		/*
		 * Comparar jogador - computador, se resultado > 0 = jogador ganhou. Se
		 * resultado < 0 = computador ganhou. else cangou!
		 */
		int cartaValorUser = b.acharCarta(usuario.getMaoJogador().get(jogadaAtual[0]));
		int cartaValorComp = b.acharCarta(computador.getMaoJogador().get(jogadaAtual[1]));
		if (cartaValorUser == -1) {
			System.out.println("ERRO CARTA NÃO ENCONTRADA(JOGADOR)");
			System.exit(1);
		}
		if (cartaValorComp == -1) {
			System.out.println("ERRO CARTA NÃO ENCONTRADA(COMPUTADOR)");
			System.exit(1);
		}
		int resposta = cartaValorUser - cartaValorComp;
		usuario.removerMaoJogador(jogadaAtual[0]);
		computador.removerMaoJogador(jogadaAtual[1]);

		return resposta;
	}

	public ArrayList<Carta> vezDoUsuario(ArrayList<Carta> mesa, boolean truco) {

		for (Carta carta : usuario.getMaoJogador()) {
			int opcao = (usuario.getMaoJogador().indexOf(carta) + 1);
			System.out.println("Opção: " + opcao + " Carta: " + carta.getValor() + " de " + carta.getNipe());
		}
		if (!truco) {
			System.out.println("Opção: 4 Pedir TRUCO!");
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
			} else if (respUsuario == 4) {
				// Parte de pedir truco
				System.out.println("Você pediu TRUCO - se ganhar, ganhará 3 pontos");

				if (respostaTrucoComputador("Truco").equals("Seis!")) {
					System.out.println("Seis!");
					
					if (respostaTrucoUsuario("Seis!").equals("Nove!")) {
						System.out.println("Nove!");
						
						if (respostaTrucoComputador("Nove!").equals("Doze!")) {
							System.out.println("Doze!");
							
							if (respostaTrucoUsuario("Doze!").equals("Caí!")) {
								System.out.println("Caí!");
								ponto = 12;
								
							} else if (respostaTrucoUsuario("Doze!").equals("Fujiu")) {
								System.out.println("Fujiu");
								ponto = 9;
								
							}
						} else if (respostaTrucoComputador("Nove!").equals("Caí!")) {
							System.out.println("Caí!");
							ponto = 9;
							
						} else if (respostaTrucoComputador("Nove!").equals("Fujiu")) {
							System.out.println("Fujiu");
							ponto = 6;
							
						}

					} else if (respostaTrucoUsuario("Seis!").equals("Caí!")) {
						System.out.println("Caí!");
						ponto = 6;
						
					} else if (respostaTrucoUsuario("Seis!").equals("Fujiu")) {
						System.out.println("Fujiu");
						ponto = 3;
						
					}

				} else if (respostaTrucoComputador("Truco").equals("Caí!")) {
					System.out.println("Caí!");
					ponto = 3;
					
				} else if (respostaTrucoComputador("Truco").equals("Fujiu")) {
					System.out.println("Fujiu");
					ponto = 1;
					
				}
			}
		} else {
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
		}
		return mesa;
	}

	public ArrayList<Carta> vezDoComputador(ArrayList<Carta> mesa, boolean truco) {

		// Adiciona na mesa uma carta aleatória que está na mão do jogandor
		int rd;
		if (truco) {
			rd = intRandom(0, computador.getMaoJogador().size());
		} else {
			rd = intRandom(0, 4);
		}
		jogadaAtual[1] = rd;
		if (rd >= 0 && rd < 4) {
			System.out.println("A escolha do computador foi " + computador.getMaoJogador().get(rd).getValor() + " de"
					+ computador.getMaoJogador().get(rd).getNipe());
			mesa.add(computador.getMaoJogador().get(rd));
		} else if (rd == 4) {
			System.out.println("A escolha do computador foi TRUCO! - se ganhar, ganhará 3 pontos");

			if (respostaTrucoUsuario("Truco").equals("Seis!")) {

				if (respostaTrucoComputador("Seis!").equals("Nove!")) {

					if (respostaTrucoUsuario("Nove!").equals("Doze!")) {

						if (respostaTrucoComputador("Doze!").equals("Caí!")) {
							ponto = 12;
						} else if (respostaTrucoComputador("Doze!").equals("Fujiu")) {
							ponto = 9;
						}
					} else if (respostaTrucoUsuario("Nove!").equals("Caí!")) {
						ponto = 9;
					} else if (respostaTrucoUsuario("Nove!").equals("Fujiu")) {
						ponto = 6;
					}

				} else if (respostaTrucoComputador("Seis!").equals("Caí!")) {
					ponto = 6;
				} else if (respostaTrucoComputador("Seis!").equals("Fujiu")) {
					ponto = 3;
				}

			} else if (respostaTrucoUsuario("Truco").equals("Caí!")) {
				ponto = 3;
			} else if (respostaTrucoUsuario("Truco").equals("Fujiu")) {
				ponto = 1;
			}
		}
		return mesa;
	}

	public int intRandom(int min, int max) {
		Random random = new Random();
		int randomNum = random.nextInt(((max - 1) - min) + 1) + min;
		return randomNum;
	}

	public void mostraResultadoRodada() {
		System.out.println("Pontuação da rodada:\n Usuario: " + usuario.getPontoRodada() + "\n Computador: "
				+ computador.getPontoRodada() + "\n\n\n");
	}

	public void mostrarResultadoPartida() {
		System.out.println("Pontuação da rodada:\n Usuario: " + usuario.getPontoTruco() + "\n Computador: "
				+ computador.getPontoTruco() + "\n\n\n");
	}

	public String respostaTrucoComputador(String respUsuario) {
		int rd = 0;
		String resposta = "";
		switch (respUsuario.trim()) {
		case "Truco":
			if (computador.getPontoRodada() <= 9) {
				rd = intRandom(0, 2);
			} else {
				rd = intRandom(0, 1);
			}
			if (rd == 0) {
				resposta = "Fujiu";
			} else if (rd == 1) {
				resposta = "Caí!";
			} else if (rd == 2) {
				resposta = "Seis!";
			}
			break;
		case "Nove!":
			if (computador.getPontoRodada() <= 0) {
				rd = intRandom(0, 2);
			} else {
				rd = intRandom(0, 1);
			}
			if (rd == 0) {
				resposta = "Fujiu";
			} else if (rd == 1) {
				resposta = "Caí!";
			} else if (rd == 2) {
				resposta = "Doze!";
			}
			break;
		case "Seis!":
			if (computador.getPontoRodada() <= 3) {
				rd = intRandom(0, 2);
			} else {
				rd = intRandom(0, 1);
			}
			if (rd == 0) {
				resposta = "Fujiu";
			} else if (rd == 1) {
				resposta = "Caí!";
			} else if (rd == 2) {
				resposta = "Nove!";
			}
			break;
		default:
			resposta = "RESPOSTA INESPERADA - COMPUTADOR";
		}
		return resposta;

	}

	public String respostaTrucoUsuario(String respCoputador) {
		String resposta = "";
		switch (respCoputador.trim()) {
		case "Truco":
			if (computador.getPontoRodada() <= 9) {
				System.out.println("Responda ao TRUCO:\n1-Foje\nCaí!\n3-Seis");
				if (input.nextInt() == 0) {
					resposta = "Fujiu";
				} else if (input.nextInt() == 1) {
					resposta = "Caí!";
				} else if (input.nextInt() == 2) {
					resposta = "Seis!";
				}
			} else {
				System.out.println("Responda ao TRUCO:\n1-Foje\nCaí!");
				if (input.nextInt() == 0) {
					resposta = "Fujiu";
				} else if (input.nextInt() == 1) {
					resposta = "Caí!";
				}
			}
			break;
		case "9":
			if (computador.getPontoRodada() <= 0) {
				System.out.println("Responda ao TRUCO:\n1-Foje\nCaí!\n3-Doze");
				if (input.nextInt() == 0) {
					resposta = "Fujiu";
				} else if (input.nextInt() == 1) {
					resposta = "Caí!";
				} else if (input.nextInt() == 2) {
					resposta = "Doze!";
				}
			} else {
				System.out.println("Responda ao TRUCO:\n1-Foje\nCaí!");
				if (input.nextInt() == 0) {
					resposta = "Fujiu";
				} else if (input.nextInt() == 1) {
					resposta = "Caí!";
				}
			}
			break;
		case "6":
			if (computador.getPontoRodada() <= 3) {
				System.out.println("Responda ao TRUCO:\n1-Foje\nCaí!\n3-Nove");
				if (input.nextInt() == 0) {
					resposta = "Fujiu";
				} else if (input.nextInt() == 1) {
					resposta = "Caí!";
				} else if (input.nextInt() == 2) {
					resposta = "Nove!";
				}
			} else {
				System.out.println("Responda ao TRUCO:\n1-Foje\nCaí!");
				if (input.nextInt() == 0) {
					resposta = "Fujiu";
				} else if (input.nextInt() == 1) {
					resposta = "Caí!";
				}
			}
			break;
		default:
			resposta = "RESPOSTA INESPERADA - USUARIO";
		}

		return resposta;
	}

	public int cangouPrimeira() {
		ArrayList<Integer> valorCartasUsuario = new ArrayList<Integer>();
		ArrayList<Integer> valorCartasComputador = new ArrayList<Integer>();
		for (Carta carta : usuario.getMaoJogador()) {
			for (ArrayList<Carta> ordemCartas : b.getOrdemTruco()) {
				for (Carta carta2 : ordemCartas) {
					if (carta.equals(carta2)) {
						valorCartasUsuario.add(ordemCartas.indexOf(carta2));
					}
				}
			}
		}

		for (Carta carta : computador.getMaoJogador()) {
			for (ArrayList<Carta> ordemCartas : b.getOrdemTruco()) {
				for (Carta carta2 : ordemCartas) {
					if (carta.equals(carta2)) {
						valorCartasComputador.add(ordemCartas.indexOf(carta2));
					}
				}
			}
		}

		Collections.sort(valorCartasUsuario, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {

				return o1.compareTo(o2);
			}

		});

		Collections.sort(valorCartasComputador, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {

				return o1.compareTo(o2);
			}

		});
		System.out.println(valorCartasUsuario.get(0));
		System.out.println(valorCartasUsuario.get(1));
		System.out.println(valorCartasComputador.get(0));
		System.out.println(valorCartasComputador.get(1));

		int maiorCarta = valorCartasUsuario.get(0) - valorCartasComputador.get(0);

		return maiorCarta;
	}
}
