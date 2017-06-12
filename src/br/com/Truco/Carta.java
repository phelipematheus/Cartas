package br.com.Truco;

public class Carta 
{
	private String nipe;
	private int valor;
	
	public Carta(String nipe,int valor){
		this.nipe = nipe;
		this.valor = valor;
	}
	
	public Carta(){
		
	}
	
	public void setNipe(String naipe) {
		this.nipe = naipe;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getNipe() 
	{
		return nipe;
	}
	
	public int getValor() 
	{
		return valor;
	}		

}
