package br.com.Truco;

import java.util.ArrayList;
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
			if(usuario.getPontoRodada() == 2 || computador.getPontoRodada() == 2){
				break;
			}
			rodada();
		}

	}

	private int rodada() {
		ArrayList<Carta> mesa = new ArrayList<>();
		int pontoRodada = 1;
		System.out.println("vez do usuário. Qual das suas cartas você deseja descartar?");
		ArrayList<Carta> maoUsuario = usuario.getMaoJogador();
		for (Carta carta : maoUsuario) {
			int opcao = (maoUsuario.indexOf(carta) +1);
			System.out.println("Opção: " + opcao + " Carta: " +carta.getValor()+" de " + carta.getNipe());
		}
		input.nextInt();
			if (input.equals(1)) {
				System.out.println("Sua escolha foi a carta nmr 1");
				mesa.add(usuario.getMaoJogador().get(0));
				usuario.getMaoJogador().remove(0);
			} else if (input.equals(2)) {
				System.out.println("Sua escolha foi a carta nmr 2");
				mesa.add(usuario.getMaoJogador().get(1));
				usuario.getMaoJogador().remove(1);
			} else if (input.equals(3)) {
				System.out.println("Sua escolha foi a carta nmr 3");
				mesa.add(usuario.getMaoJogador().get(2));
				usuario.getMaoJogador().remove(2);
			}
		
			System.out.println("Vez do computador.");
			
			
		return pontoRodada;
	}
	
	public Carta logicaTruco( ArrayList<Carta> maoComputador, ArrayList<Carta> mesa){
		
		/* Implementar toda a lógica básica do truco - Preciso da sua ajuda Murilo.
		 Temos que ensinar como se pensar ao ver uma carta na mesa */
		
		return null;
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
