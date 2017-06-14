package br.com.Truco;

import java.util.ArrayList;
import java.util.Random;

public class Baralho {

	Carta[] baralho = new Carta[28];
	private ArrayList<ArrayList<Carta>> ordemTruco = new ArrayList<>();
	public int vetcarta = 0;
	
	public Baralho() {
		this.criarBaralho();
	}

	private void criarBaralho() {

		criarBaralhoTodosOsNipes(12);
		criarBaralhoTodosOsNipes(11);
		criarBaralhoTodosOsNipes(13);
		criarBaralhoTodosOsNipes(1);
		criarBaralhoTodosOsNipes(2);
		criarBaralhoTodosOsNipes(3);

		Carta joker = new Carta();
		joker.setNipe("Joker");
		joker.setValor(14);
		baralho[vetcarta] = joker;
		ArrayList<Carta> jokerGrupo = new ArrayList<>();
		jokerGrupo.add(joker);
		ordemTruco.add(jokerGrupo);
		
		Carta seteOuro = new Carta();
		seteOuro.setValor(7);
		seteOuro.setNipe("Ouro");
		baralho[vetcarta] = seteOuro;
		ArrayList<Carta> seteOuroGrupo = new ArrayList<>();
		seteOuroGrupo.add(seteOuro);
		ordemTruco.add(seteOuroGrupo);
		
		Carta espadilha = new Carta();
		espadilha.setNipe("Espada");
		espadilha.setValor(1);
		baralho[vetcarta] = espadilha;
		ArrayList<Carta> espadilhaGrupo = new ArrayList<>();
		espadilhaGrupo.add(espadilha);
		ordemTruco.add(espadilhaGrupo);
		
		Carta seteCopas = new Carta();
		seteCopas.setValor(7);
		seteCopas.setNipe("Copas");
		baralho[vetcarta] = seteCopas;
		ArrayList<Carta> seteCopasGrupo = new ArrayList<>();
		seteCopasGrupo.add(seteCopas);
		ordemTruco.add(seteCopasGrupo);
		
		Carta Zap = new Carta();
		Zap.setNipe("Paus");
		Zap.setValor(4);
		baralho[vetcarta] = Zap;
		ArrayList<Carta> zapGrupo = new ArrayList<>();
		zapGrupo.add(Zap);
		ordemTruco.add(zapGrupo);
		
	
	}

	private int criarBaralhoTodosOsNipes(int cartaNumero) {
		ArrayList<String> nipe = new ArrayList<>();
		nipe.add("Copas");
		if(!(cartaNumero == 1)){
			nipe.add("Espada");
		}
		nipe.add("Ouro");
		nipe.add("Paus");
		ArrayList<Carta> grupo = new ArrayList<>();
		for (String cartaNipe : nipe) {
			Carta carta = new Carta();
			carta.setValor(cartaNumero);
			carta.setNipe(cartaNipe);
			baralho[this.vetcarta] = carta;
			this.vetcarta++;
			grupo.add(carta);
		}
		ordemTruco.add(grupo);


		return vetcarta;
	}

	public int acharCarta(Carta cartaJogador ){
		int index;
		for (ArrayList<Carta> grupoCarta : ordemTruco) {
		 index = grupoCarta.indexOf(cartaJogador);
			if(index != -1){	
				return index;
			}
		}
		return -1;
	}

	
	public void embaralhar() {
		Random gerador = new Random();
		int a, b;
		Carta carta = new Carta();

		for (int i = 0; i < 5000; i++) {
			a = gerador.nextInt(28);
			b = gerador.nextInt(28);
			if (a != b) {
				carta = baralho[a];
				baralho[a] = baralho[b];
				baralho[b] = carta;
			}
		}
	}

	public ArrayList<ArrayList<Carta>> getOrdemTruco() {
		return ordemTruco;
	}
	
}
