package br.com.Truco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
					// Usuario ganhou rodada
					usuario.setPontoRodada(usuario.getPontoRodada() + 1);
					usuario.setGanhou(true);
					computador.setGanhou(false);
					partida[i] = 1;
					mostraResultadoRodada();
				} else if (resultado < 0) {
					// Computador perdeu rodada
					computador.setPontoRodada(computador.getPontoRodada() + 1);
					computador.setGanhou(true);
					usuario.setGanhou(false);
					partida[i] = -1;
					mostraResultadoRodada();
				} else {
					// Cangou
					if (i == 0) {
						// fazer o método em que mostra as duas cartas e compara
						// a maior com a maior
						cangouPrimeira();
						break;
					}
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
			mesa = vezDoUsuario(mesa);
			System.out.println("Vez do computador.");
			mesa = vezDoComputador(mesa);
		} else {
			System.out.println("Vez do computador.");
			mesa = vezDoComputador(mesa);

			System.out.println("Vez do usuário. Qual das suas cartas você deseja descartar?");
			mesa = vezDoUsuario(mesa);
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
		
		Collections.sort(valorCartasUsuario, new Comparator<Integer>(){

			@Override
			public int compare(Integer o1, Integer o2) {
				
				return o1.compareTo(o2);
			}
			
		});
		
		Collections.sort(valorCartasComputador, new Comparator<Integer>(){

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

	public ArrayList<Carta> vezDoUsuario(ArrayList<Carta> mesa) {

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
		} else if(respUsuario == 4){
			//Parte de pedir truco
			System.out.println("Você pediu TRUCO - se ganhar ganhará 3 pontos");
			respostaTrucoComputador();
		}

		return mesa;
	}

	public ArrayList<Carta> vezDoComputador(ArrayList<Carta> mesa) {

		// Adiciona na mesa uma carta aleatória que está na mão do jogandor
		int rd = intRandom(0, computador.getMaoJogador().size());
		jogadaAtual[1] = rd;
		System.out.println("A escolha do computador foi " + computador.getMaoJogador().get(rd).getValor() + " de"
				+ computador.getMaoJogador().get(rd).getNipe());
		mesa.add(computador.getMaoJogador().get(rd));

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

	public void respostaTrucoComputador(){
		int rd = intRandom(0, 2);
		if(rd == 0){
			System.out.println("Fujiu");
		}else if(rd == 1){
			System.out.println("Caí!");
		}else if(rd == 2){
			System.out.println("");
		}
	}
	
	public void respostaTrucoUsuario(){
		
	}
	
}
