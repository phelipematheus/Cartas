package br.com.Truco;

import java.util.ArrayList;
import java.util.Random;

public class Baralho {

	Carta[] baralho = new Carta[28];
	ArrayList<ArrayList<Carta>> ordemTruco = new ArrayList<>();

	public Baralho() {
		this.criarBaralho();
	}

	private void criarBaralho() {
		int vetcarta = 0;
		criarBaralhoTodosOsNipes(vetcarta,11);
		criarBaralhoTodosOsNipes(vetcarta,12);
		criarBaralhoTodosOsNipes(vetcarta,13);
		criarBaralhoTodosOsNipes(vetcarta,1);
		criarBaralhoTodosOsNipes(vetcarta,2);
		criarBaralhoTodosOsNipes(vetcarta,3);

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

	private int criarBaralhoTodosOsNipes(int vetcarta,int cartaNumero) {
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
			baralho[vetcarta] = carta;
			vetcarta++;
			grupo.add(carta);
		}
		ordemTruco.add(grupo);
		grupo.clear();

		return vetcarta;
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

	
	
}
