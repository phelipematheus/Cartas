package br.com.Truco;

import java.util.Random;

public class Baralho {

	Carta[] baralho = new Carta[28];

	public Baralho() {
		this.criarBaralho();
	}

	private void criarBaralho() {
		int vetcarta = 0;
		vetcarta = criarBaralhoTodosOsNipes(vetcarta,"Copas");
		vetcarta = criarBaralhoTodosOsNipes(vetcarta,"Paus");
		vetcarta = criarBaralhoTodosOsNipes(vetcarta,"Espada");
		vetcarta = criarBaralhoTodosOsNipes(vetcarta,"Ouro");
		
		Carta SeteOuro = new Carta();
		SeteOuro.setValor(7);
		SeteOuro.setNipe("Ouro");
		baralho[vetcarta] = SeteOuro;
		
		Carta SeteCopas = new Carta();
		SeteCopas.setValor(7);
		SeteCopas.setNipe("Copas");
		baralho[vetcarta] = SeteCopas;
		
		Carta Zap = new Carta();
		Zap.setNipe("Paus");
		Zap.setValor(4);
		baralho[vetcarta] = Zap;
		
		Carta Joker = new Carta();
		Joker.setNipe("Joker");
		Joker.setValor(15);
		baralho[vetcarta] = Joker;
	
	}

	private int criarBaralhoTodosOsNipes(int vetcarta,String nipe) {
		for (int j = 1; j < 15; j++) {
			if(j == 1 || j == 2 || j == 3 || j == 11 || j == 12 || j == 13){
			Carta carta = new Carta();
			carta.setValor(j);
			carta.setNipe(nipe);
			baralho[vetcarta] = carta;
			vetcarta++;
			}
		}
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
