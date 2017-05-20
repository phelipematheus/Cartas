package br.com.Truco;

import java.util.ArrayList;

public class Jogador 
{
	private int PontoTruco;
	private int pontoRodada;
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
	
	public ArrayList<Carta> getMaoJogador()
	{
		return maoJogador;
	}
	
}
