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
			
			default: System.out.println("Opção Inválida ");
			
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
				System.out.println("V O C Ê   P E R D E U   !!");
			}
			if(posicao == 9){
				a.layout();
				a.layoutMostra();
				System.out.println("P A R A B É N S    V O C Ê    V E N C E U   !!!");
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
		System.out.println("O game começou !");
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
				System.out.println("Você esta na posição " + posicao + " e combustivel " + TANQUE);
			}
		}else{
			System.out.println("Você não pode avançar sem gasolina");
		}
	}
	private void RECUAR() {
		if(posicao > 0){
			if(TANQUE>0){
			posicao = posicao - 1;
			TANQUE = TANQUE - 1;
			System.out.println("Você esta na posição " + posicao + " e combustivel " + TANQUE);
			}else{
				System.out.println("Você não pode retroceder sem gasolina");
			}
		}
	}
	private void DESCARREGAR() {
		if(posicao != 0){
			if(TANQUE>0){
				String aux;
				int valor;
				Scanner scan = new Scanner (System.in);
				System.out.println("Você esta na posição: " + posicao + " Seu conbustivel é " + TANQUE + " quantos galões você deseja descarregar ?");
				aux = scan.nextLine();
				valor = Integer.parseInt(aux);
				if((valor <= TANQUE) && (valor > 0)){
					mapa[posicao] = mapa[posicao] + valor;
					TANQUE = TANQUE - valor;
					System.out.println("Você esta na posição: " + posicao + " e descarregou: " + valor + " galões, Seu conbustivel é " + TANQUE);
				}else{
					System.out.println("Valor digitado invalido");	
				}
			}else{
				System.out.println("Você não tem gasolina para descarregar");	
			}
		}else{
			System.out.println("Você não pode descarregar na posição 0");
		}
	}
	private void CARREGAR() {
		if(posicao != 0){
			if(mapa[posicao] > 0){
				String aux;
				int valor;
				int testa_tanque;
				Scanner scan = new Scanner (System.in);
				System.out.println("Você tem "+ mapa[posicao] + " galões na posição "+ posicao + " Quantos galões você quer carregar?");
				aux = scan.nextLine();
				valor = Integer.parseInt(aux);
				testa_tanque = TANQUE + valor;
				if(testa_tanque <= 6){
					if(mapa[posicao] >= valor){
						TANQUE = TANQUE + valor;
						mapa[posicao] = mapa[posicao] - valor;
						System.out.println("Você esta na posição: " + posicao + " Seu conbustivel é " + TANQUE );
						testa_tanque = 0;
					}else{
						System.out.println("Você tem "+ mapa[posicao] + " galões apenas na posição" + posicao);
						testa_tanque = 0;
					}
				}else{
					System.out.println("o caminhão suporta no maximo 6 galões");
					testa_tanque = 0;
				}
			}else{
				System.out.println("Você não tem galões nesta posição");
			}
		}else{
			TANQUE = 6;
			System.out.println("Você esta na posição " + posicao + " e combustivel " + TANQUE);
		}
	}
}
