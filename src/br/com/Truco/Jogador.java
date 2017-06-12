package br.com.Truco;

import java.util.ArrayList;

public class Jogador 
{
	private int PontoTruco;
	private int pontoRodada;
	private boolean ganhou;
	private ArrayList<Carta> maoJogador = new ArrayList<Carta>();
	
	public void pegarMaoTruco(Baralho b)
	{
		for(int i=0;i<28;i++)
		{
			if(!(b.baralho[i]==null))
			{
				maoJogador.add(b.baralho[i]);
				b.baralho[i]=null;
				break;
			}	
			
		}
	}
	public void removerMaoJogador(int indice){
		maoJogador.remove(indice);
		ArrayList<Carta> auxiliar = new ArrayList<>();
		for (Carta carta : maoJogador) {
				auxiliar.add(carta);
		}
		maoJogador = new ArrayList<Carta>();
		for (Carta carta : auxiliar) {
			maoJogador.add(carta);
		}
	}
	
	public ArrayList<Carta> getMaoJogador()
	{
		return maoJogador;
	}

	public int getPontoRodada() {
		return pontoRodada;
	}

	public void setPontoRodada(int pontoRodada) {
		this.pontoRodada = pontoRodada ;
	}

	public int getPontoTruco() {
		return PontoTruco;
	}

	public void setPontoTruco(int pontoTruco) {
		PontoTruco = pontoTruco;
	}
	public boolean isGanhou() {
		return ganhou;
	}
	public void setGanhou(boolean ganhou) {
		this.ganhou = ganhou;
	}
	
}
