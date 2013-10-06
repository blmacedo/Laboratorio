package Test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import aplicacao.TravessiaDeserto;

public class TravessiaDesertoTest {
	private static final String String = null;
	private TravessiaDeserto auth = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		auth = new TravessiaDeserto();

	}

	@After
	public void tearDown() throws Exception {
	}
 

	@Test
	public void testVoltar() {
		auth.initGame();
		int pos = auth.getPos();
		int fuel = auth.getFuel();
		assertTrue(auth.getFuel() > 0);
		assertTrue(auth.getPos() == 0);

		auth.voltar();
		assertEquals("Verifica se andou uma posição" ,pos ,auth.getPos(), 0);
		assertEquals("Verifica se conbustivel diminuiu",fuel ,auth.getFuel(), 0);
		
		assertTrue(auth.getFuel()>0);
		while(auth.getPos() <= 2){
			auth.avancar();
			pos++;
			fuel--;
		}	
		
		assertTrue(auth.getPos()>0);
		assertTrue(auth.getFuel()>0);
		auth.voltar();
		pos--;
		fuel--;
		assertEquals("Verifica se andou uma posição" ,pos  ,auth.getPos(), 0);
		assertEquals("Verifica se conbustivel diminuiu",fuel ,auth.getFuel(), 0);
		
		assertTrue(auth.getPos()>0);
		assertTrue(auth.getFuel()>0);
		auth.voltar();
		pos--;
		fuel--;
		assertEquals("Verifica se andou uma posição" ,pos  ,auth.getPos(), 0);
		assertEquals("Verifica se conbustivel diminuiu",fuel ,auth.getFuel(), 0);

		assertTrue(auth.getPos()>0);
		assertTrue(auth.getFuel()>0);
		auth.voltar();
		pos--;
		fuel = 6;
		assertEquals("Verifica se andou uma posição" ,pos  ,auth.getPos(), 0);
		assertEquals("Verifica se conbustivel diminuiu",fuel ,auth.getFuel(), 0);
		
		assertTrue(auth.getFuel()>0);
		auth.avancar();
		pos++;
		fuel--;
		
		assertTrue(auth.getPos() > 0);
		assertTrue(auth.getFuel() > 0);
		while(auth.getFuel() > 0){
			auth.descarregar();
			fuel--;
		}
		
		assertTrue(auth.getPos()>0);
		assertTrue(auth.getFuel()==0);
		auth.voltar();
		assertEquals("Verifica se andou uma posição" ,pos  ,auth.getPos(), 0);
		assertEquals("Verifica se conbustivel diminuiu",fuel ,auth.getFuel(), 0);
	}
	
	@Test
	public void testAvancar() {
		auth.initGame();
		int pos = auth.getPos();
		int fuel = auth.getFuel();
		
		assertTrue(auth.getFuel() > 0);
		auth.avancar();
		pos++;
		fuel--;
		assertEquals("Verifica se andou uma posição" ,pos  ,auth.getPos(), 0);
		assertEquals("Verifica se conbustivel diminuiu",fuel ,auth.getFuel(), 0);
		
		assertTrue(auth.getFuel() > 0);
		auth.avancar();
		pos++;
		fuel--;
		assertEquals("Verifica se andou uma posição" ,pos  ,auth.getPos(), 0);
		assertEquals("Verifica se conbustivel diminuiu",fuel ,auth.getFuel(), 0);
		
		assertTrue(auth.getFuel() > 0);
		while(auth.getFuel() > 0){
			auth.descarregar();
			fuel--;
		}
		
		assertTrue(auth.getFuel()==0);
		auth.avancar();
		assertEquals("Verifica se andou uma posição" ,pos  ,auth.getPos(), 0);
		assertEquals("Verifica se conbustivel diminuiu",fuel ,auth.getFuel(), 0);
		
		
	}
	
	@Test
	public void testDescarregar() {
		auth.initGame();
		int pos = auth.getPos();
		int fuel = auth.getFuel();
		int[] map ;
		map = new int[10];
		
		assertTrue(auth.getFuel() > 0);
		while(auth.getFuel() > 0){
			auth.descarregar();
			fuel--;
			map[pos]++;
		}
			
		assertTrue(auth.getFuel() == 0);
		assertEquals("verifica se descarregou quantidade certa na posição" ,map[pos]  ,auth.getMap()[pos], 0);
		assertEquals("Verifica se conbustivel zerou",fuel ,auth.getFuel(), 0);
		
		assertTrue(auth.getFuel() == 0);
		auth.descarregar();
		assertEquals("verifica se descarregou quantidade certa na posição " ,map[pos]  ,auth.getMap()[pos], 0);
		assertEquals("Verifica se conbustivel zerou",fuel ,auth.getFuel(), 0);
		
	}
	
	@Test
	public void testCarregar() {
		auth.initGame();
		int pos = auth.getPos();
		int fuel = auth.getFuel();
		int[] map = auth.getMap();
		map = new int[10];
		
		assertTrue(auth.getFuel() > 0);
		auth.avancar();
		pos++;
		fuel--;
		
		assertTrue(auth.getFuel() > 0);
		while(auth.getFuel() >=1){
			auth.descarregar();
			fuel--;
			map[pos]++;
		}
		
		assertTrue(auth.getMap()[pos] > 0);
		auth.carregar();
		fuel++;
		map[pos]--;
		assertEquals("Verifica se conbustivel aumenta",fuel ,auth.getFuel(), 0);
		assertEquals("Verifica se conbustivel da posição diminuiu",map[pos] ,auth.getMap()[pos], 0);
		
		
		assertTrue(auth.getFuel() > 0);
		auth.voltar();
		pos--;
		fuel = 6;
		
		assertTrue(auth.getFuel() > 0);
		auth.avancar();
		pos++;
		fuel--;
		
		assertTrue(auth.getMap()[pos] > 0);
		while(auth.getMap()[pos] >=1){
			auth.carregar();
			fuel++;
		}
		//assertEquals("Verifica se conbustivel não passa da capacidade do caminhão",6 ,auth.getFuel(), 0);
			
		while(auth.getFuel() > 1){
			auth.descarregar();
			fuel--;
		}
	}	
	@Test
	public void testWinner() {
		auth.initGame();
		int pos = auth.getPos();
		int fuel = auth.getFuel();
		
		assertTrue(auth.getFuel() > 0);
		FimWinner( fuel, pos);
		fuel = getAvancarFuel();
		assertTrue("venceu o jogo", auth.isGameOver());
		assertEquals("Verifica se andou uma posição" ,auth.getMAP_SIZE()  ,auth.getPos(), 0);
	}

	@Test
	public void testGameOver() {
		auth.initGame();
		int pos = auth.getPos();
		int fuel = auth.getFuel();
		
		assertTrue(auth.getFuel() > 0);
		while(auth.getFuel() > 2){
			auth.descarregar();
			fuel--;
		}
		assertTrue(auth.getFuel() > 0);
		while(auth.getFuel() > 0){
		auth.avancar();
		fuel--;
		pos++;
		}
		
		assertTrue(auth.getFuel() == 0);
		assertTrue(auth.isGameOver());
		assertEquals("Verifica se conbustivel diminuiu",0 ,auth.getMap()[pos], 0);
		// VENCER O JOGO ################################################################
		auth.initGame();
		pos = auth.getPos();
		fuel = auth.getFuel();
		
		assertTrue(auth.getFuel() > 0);
		FimWinner( fuel, pos);
		fuel = getAvancarFuel();
		assertTrue("venceu o jogo", auth.isGameOver());
	}

	public void avancarWinner(int fuel, int pos){		
		for(int i=0; i < 2; i++){
			auth.avancar();
			fuel--;
			pos++;
		}
		
		for(int i=0; i < 2; i++){
			auth.descarregar();
			fuel--;
		}

		for(int i=0; i < 2; i++){
			auth.voltar();
			fuel--;
			pos--;
		}
		if(auth.getPos() == 0){
			fuel=6;
		}
		AvancarPos = pos;
		AvancarFuel = fuel;
	}

	public void avancarWinner2(int fuel, int pos){
		for(int i=0; i < 2; i++){
			auth.avancar();
			fuel--;
			pos++;
		}
		for(int i=0; i < 2; i++){
			auth.carregar();
			fuel++;
		}

		AvancarPos = pos;
		AvancarFuel = fuel;
	}
	
	public void FimWinner(int fuel, int pos){
		 
		for(int i=0; i < 4; i++){
			avancarWinner(fuel,pos);
				fuel = getAvancarFuel();
				pos = getAvancarPos();
		}
		
		avancarWinner2(fuel,pos);
			fuel = getAvancarFuel();
			pos = getAvancarPos();
			

		avancarWinner(fuel,pos);
			fuel = getAvancarFuel();
			pos = getAvancarPos();

		while(auth.getMap()[pos] > 0){
			auth.carregar();
			fuel++;
		}
		
		avancarWinner2(fuel,pos);
		fuel = getAvancarFuel();
		pos = getAvancarPos();
		
		while(auth.getFuel() > 0){
			auth.avancar();
			fuel--;
			pos++;
		}
		AvancarPos = pos;
		AvancarFuel = fuel;
	}
	int AvancarPos;
	int AvancarFuel;
	
	public int getAvancarPos(){
		return AvancarPos;
	}
	public int getAvancarFuel(){
		return AvancarFuel;
	}
}
	
	
	
















