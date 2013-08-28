import java.util.Scanner;

import javax.swing.JOptionPane;

public class Aula_1_caminhao {
	private static int[] mapa;
	private static String[] layout;
	private static String aux = "";
	private static int TANQUE = 6;
	private static int posicao = 0;
	private static String opcao;

	public Aula_1_caminhao(){
		imprimeStatus();
		mapa = new int[10];		
		layout = new String[10];
	}
	public static void main(String[] args) {		
		Aula_1_caminhao a = new Aula_1_caminhao ();
		boolean x=true;
		do{
		aux = "";
		a.layout();
		a.layoutMostra();
		a.teclado();
			switch(opcao) {
			
			case "AVANCAR": 
				a.AVANCAR();
				break;
			case "RECUAR": 
				a.RECUAR();
				break;
			case "DESCARREGAR": 
				a.DESCARREGAR();
				break;
			case "CARREGAR": 
				a.CARREGAR();
				break;
			
			default: System.out.println("Op��o Inv�lida ");
			
			} // fim do switch
			
			if(posicao == 9){
				x=false;
			}
			if(posicao != 0){
				if((TANQUE == 0)&&(mapa[posicao] == 0)){
					x=false;
				}
			}else{
				x=true;
			}
		}while(x);{
			aux = "";
			if(TANQUE == 0){
				a.layout();
				a.layoutMostra();
				System.out.println("V O C �   P E R D E U   !!");
			}
			if(posicao == 9){
				a.layout();
				a.layoutMostra();
				System.out.println("P A R A B � N S    V O C �    V E N C E U   !!!");
			}
		}
	}
	public void layout(){
		for (int i = 0; i < layout.length; i++) {
			layout[i] =("["+i+"]");
		}
	}
	public void layoutMostra(){
		for (int i = 0; i < layout.length; i++) {
			layout[posicao] = "[x]";
			aux = aux + layout[i];	
		}
			System.out.println(aux);
	}	
	public void imprimeStatus(){
		System.out.println("O game come�ou !");
	}
	public void teclado() {
        readingOne();
   }
	private void readingOne() {
		String opcao;
		  Scanner scan = new Scanner (System.in);  
		  if(posicao != 0){
			  if(mapa[posicao]>0){
				  System.out.print ("O que deseja fazer ? [Avancar] [Recuar] [Descarregar] [Carregar] \n");  
			      opcao = scan.nextLine();
			  }else{
				  System.out.print ("O que deseja fazer ? [Avancar] [Recuar] [Descarregar] \n");  
			        opcao = scan.nextLine();
			  }
		  }else{
			  System.out.print ("O que deseja fazer ? [Avancar] [Carregar] \n");  
		      opcao = scan.nextLine();
		  }   
		  this.opcao=opcao.toUpperCase(); 	 
	}
	public String getOpcao(){
		return opcao;
	}
	private void AVANCAR() {
		if(TANQUE>0){
			if(posicao < 9){
				posicao = posicao + 1;
				TANQUE = TANQUE - 1;
				System.out.println("Voc� esta na posi��o " + posicao + " e combustivel " + TANQUE);
			}
		}else{
			System.out.println("Voc� n�o pode avan�ar sem gasolina");
		}
	}
	private void RECUAR() {
		if(posicao > 0){
			if(TANQUE>0){
			posicao = posicao - 1;
			TANQUE = TANQUE - 1;
			System.out.println("Voc� esta na posi��o " + posicao + " e combustivel " + TANQUE);
			}else{
				System.out.println("Voc� n�o pode retroceder sem gasolina");
			}
		}
	}
	private void DESCARREGAR() {
		if(posicao != 0){
			if(TANQUE>0){
				String aux;
				int valor;
				Scanner scan = new Scanner (System.in);
				System.out.println("Voc� esta na posi��o: " + posicao + " Seu conbustivel � " + TANQUE + " quantos gal�es voc� deseja descarregar ?");
				aux = scan.nextLine();
				valor = Integer.parseInt(aux);
				if((valor <= TANQUE) && (valor > 0)){
					mapa[posicao] = mapa[posicao] + valor;
					TANQUE = TANQUE - valor;
					System.out.println("Voc� esta na posi��o: " + posicao + " e descarregou: " + valor + " gal�es, Seu conbustivel � " + TANQUE);
				}else{
					System.out.println("Valor digitado invalido");	
				}
			}else{
				System.out.println("Voc� n�o tem gasolina para descarregar");	
			}
		}else{
			System.out.println("Voc� n�o pode descarregar na posi��o 0");
		}
	}
	private void CARREGAR() {
		if(posicao != 0){
			if(mapa[posicao] > 0){
				String aux;
				int valor;
				int testa_tanque;
				Scanner scan = new Scanner (System.in);
				System.out.println("Voc� tem "+ mapa[posicao] + " gal�es na posi��o "+ posicao + " Quantos gal�es voc� quer carregar?");
				aux = scan.nextLine();
				valor = Integer.parseInt(aux);
				testa_tanque = TANQUE + valor;
				if(testa_tanque <= 6){
					if(mapa[posicao] >= valor){
						TANQUE = TANQUE + valor;
						mapa[posicao] = mapa[posicao] - valor;
						System.out.println("Voc� esta na posi��o: " + posicao + " Seu conbustivel � " + TANQUE );
						testa_tanque = 0;
					}else{
						System.out.println("Voc� tem "+ mapa[posicao] + " gal�es apenas na posi��o" + posicao);
						testa_tanque = 0;
					}
				}else{
					System.out.println("o caminh�o suporta no maximo 6 gal�es");
					testa_tanque = 0;
				}
			}else{
				System.out.println("Voc� n�o tem gal�es nesta posi��o");
			}
		}else{
			TANQUE = 6;
			System.out.println("Voc� esta na posi��o " + posicao + " e combustivel " + TANQUE);
		}
	}
}
