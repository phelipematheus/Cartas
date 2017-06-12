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
		
		criarBaralhoTodosOsNipes(11);
		criarBaralhoTodosOsNipes(12);
		criarBaralhoTodosOsNipes(13);
		criarBaralhoTodosOsNipes(1);
		criarBaralhoTodosOsNipes(2);
		criarBaralhoTodosOsNipes(3);

		Carta Joker = new Carta();
		Joker.setNipe("Joker");
		Joker.setValor(15);
		baralho[vetcarta] = Joker;
		ArrayList<Carta> jokerGrupo = new ArrayList<>();
		jokerGrupo.add(Joker);
		ordemTruco.add(jokerGrupo);
		
		Carta SeteOuro = new Carta();
		SeteOuro.setValor(7);
		SeteOuro.setNipe("Ouro");
		baralho[vetcarta] = SeteOuro;
		ArrayList<Carta> seteOuroGrupo = new ArrayList<>();
		seteOuroGrupo.add(SeteOuro);
		ordemTruco.add(seteOuroGrupo);
		
		Carta Espadilha = new Carta();
		Joker.setNipe("Espadas");
		Joker.setValor(1);
		baralho[vetcarta] = Espadilha;
		ArrayList<Carta> espadilhaGrupo = new ArrayList<>();
		espadilhaGrupo.add(Espadilha);
		ordemTruco.add(espadilhaGrupo);
		
		Carta SeteCopas = new Carta();
		SeteCopas.setValor(7);
		SeteCopas.setNipe("Copas");
		baralho[vetcarta] = SeteCopas;
		ArrayList<Carta> seteCopasGrupo = new ArrayList<>();
		seteCopasGrupo.add(SeteCopas);
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
