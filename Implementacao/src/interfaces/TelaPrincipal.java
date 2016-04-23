package interfaces;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import automatos.Automato;
import automatos.AutomatoFinitoDeterministico;
import automatos.AutomatoMTFI;
import automatos.AutomatoPilhaDeterministico;
import servicos.Key;
import servicos.TuplaString;

public class TelaPrincipal {
	
	static boolean continuar = true;
	static Scanner input = new Scanner(System.in);
	
	public static void main(String args[]) {
		boasVindas();
		
		while(continuar) {
			fazerSimulacao();
		}
		
		fazerSimulacao();
	}
	
	public static void boasVindas() {
		System.out.println("----------------------------------------");
		System.out.println("\nBem vindo ao simulador de automatos");
		System.out.println("\n----------------------------------------");
		System.out.println("\nOpcoes de dispositivos(Codigo/Nome):\n");
		System.out.println("1 - Automato finito deterministico");
		System.out.println("2 - Automato pilha deterministico");
		System.out.println("3 - Automato Maquina de Turing com fita ilimitada\n");
		System.out.println("----------------------------------------");
	}
	
	public static void fazerSimulacao() {
		
		List<String> configuracaoEntrada = new ArrayList<String>();
		
		System.out.print("\nEntre com uma opcao de dispositivo: ");
		int opcaoAutomato = input.nextInt();
		
		System.out.print("Entre com o nome do arquivo de entrada: ");
		String fileName = input.next();
		
		BufferedReader bufferedReader;		
		
		try {
			FileReader fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
			
			String line = null;
			
			while((line = bufferedReader.readLine()) != null) {
				configuracaoEntrada.add(line);
			}
			
			bufferedReader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo nao encontrado.");
			return;
		} catch (IOException e) {
			System.out.println("Problema inesperado com o arquivo de entrada.");
			return;
		}
		
		System.out.print("Ativar trace (0: OFF, 1: ON): ");
		int ativarTraceInt = input.nextInt();
		
		input.nextLine();
		
		boolean ativarTrace = false;
		
		if(ativarTraceInt == 1)
			ativarTrace = true;
		
		switch(opcaoAutomato) {
		case 1:
			simularAutomatoFinito(configuracaoEntrada, ativarTrace);
			break;
		case 2:
			simularAutomatoPilha(configuracaoEntrada, ativarTrace);
			break;
		case 3:
			simularAutomatoMT(configuracaoEntrada, ativarTrace);
			break;
		default:
			System.out.println("Opcao invalida");
			return;
			
		}
	}

	private static void simularAutomatoMT(List<String> configuracaoEntrada, boolean ativarTrace) {
		Map<Key, TuplaString> transicoes = new HashMap<Key, TuplaString>();
		
		String estadoInicial = configuracaoEntrada.get(2);
		List<String> estadosFinais = Arrays.asList(configuracaoEntrada.get(3).split(","));
		List<String> alfabeto = Arrays.asList(configuracaoEntrada.get(1).split(","));
		
		if(ativarTrace) {
			System.out.println("\nEstados pertecentes ao automato: " + configuracaoEntrada.get(0));
			System.out.println("Alfabeto utilizado: " + alfabeto.toString());
			System.out.println("Estado inicial: " + estadoInicial);
			System.out.println("Estados finais: " + estadosFinais.toString());
		}
		
		for(int i = 4; i < configuracaoEntrada.size(); i++) {
			List<String> transicao = Arrays.asList(configuracaoEntrada.get(i).split(","));
			
			if(ativarTrace) {
				System.out.print("Transicao adicionada:\n" +
						"\tEstado atual: " + transicao.get(0) +
						"\n\tSimbolo: " + transicao.get(1) + 
						"\n\tProximo Estado: " + transicao.get(2) +
						"\n\tEscreve: " + transicao.get(3) +
						"\n\tMovimento: " + transicao.get(4) + "\n");
			}
			
			List<String> temp = new ArrayList<String>();
			
			temp.add(transicao.get(3));
			temp.add(transicao.get(4));
			
			transicoes.put(new Key(transicao.get(0), transicao.get(1), ""), new TuplaString(transicao.get(2), temp));
		}
		
		AutomatoMTFI automatoMT = new AutomatoMTFI(transicoes, estadosFinais, estadoInicial, ativarTrace, alfabeto);
		
		System.out.println("\nDispositivo carregado com sucesso.");
		System.out.print("Entre a cadeia a ser analisada: ");
		
		String cadeia = input.nextLine();
		
		System.out.println();
		
		automatoMT.iniciar(cadeia);
		
		List<Automato> resultadoAutomato= automatoMT.executar();
		
		boolean reconheceu = false;
		
		for(Automato a : resultadoAutomato) {
			if(a.extrapolou()) {
				reconheceu = false;
			} else {
				reconheceu = true;
			}
		}
		
		System.out.println("\nO automato reconheceu: " + reconheceu);
		System.out.println("\n----------------------------------------");
		
	}

	private static void simularAutomatoPilha(List<String> configuracaoEntrada, boolean ativarTrace) {
		Map<Key, TuplaString> transicoes = new HashMap<Key, TuplaString>();
		
		String estadoInicial = configuracaoEntrada.get(2);
		List<String> estadosFinais = Arrays.asList(configuracaoEntrada.get(3).split(","));
		
		List<String> alfabeto = Arrays.asList(configuracaoEntrada.get(1).split(","));
		
		if(ativarTrace) {
			System.out.println("\nEstados pertecentes ao automato: " + configuracaoEntrada.get(0));
			System.out.println("Alfabeto utilizado: " + alfabeto.toString());
			System.out.println("Estado inicial: " + estadoInicial);
			System.out.println("Estados finais: " + estadosFinais.toString());
		}
		
		for(int i = 4; i < configuracaoEntrada.size(); i++) {
			List<String> transicao = Arrays.asList(configuracaoEntrada.get(i).split(","));
			
			if(ativarTrace) {
				System.out.print("Transicao adicionada:\n" +
						"\tEstado atual: " + transicao.get(0) +
						"\n\tSimbolo: " + transicao.get(1) + 
						"\n\tTopo da Pilha: " + transicao.get(2) +
						"\n\tProximo Estado: " + transicao.get(3));
				
				if(transicao.size() > 4) {
					for(int index = 4; index < transicao.size(); index++) {
						System.out.print("\n\tAdiciona na pilha: " + transicao.get(index));
					}
					
				}
				
				System.out.println();
			}
			
			ArrayList<String> adicionarPilha = new ArrayList<String>();
			
			for(int index = 4; index < transicao.size(); index++) {
				adicionarPilha.add(transicao.get(index));
			}
			
			transicoes.put(new Key(transicao.get(0), transicao.get(1), transicao.get(2)), new TuplaString(transicao.get(3), adicionarPilha));
		}
		
		AutomatoPilhaDeterministico automatoPD = new AutomatoPilhaDeterministico(transicoes, estadosFinais, estadoInicial, ativarTrace, alfabeto);
		
		System.out.println("\nDispositivo carregado com sucesso.");
		System.out.print("Entre a cadeia a ser analisada: ");
		
		String cadeia = input.nextLine();
		
		System.out.println();
		
		automatoPD.iniciar(cadeia);
		
		List<Automato> resultadoAutomato= automatoPD.executar();
		
		boolean reconheceu = false;
		
		for(Automato a : resultadoAutomato) {
			if(a.estaEmEstadoFinal())
				if(a.estaEmFinalDeCadeia())
					reconheceu = true;
			/*if(a.isPilhaVazia()) {
				reconheceu = true;
			}*/
		}
		
		System.out.println("\nO automato reconheceu: " + reconheceu);
		System.out.println("\n----------------------------------------");
		
	}

	private static void simularAutomatoFinito(List<String> configuracaoEntrada, boolean ativarTrace) {		
		Map<Key, TuplaString> transicoes = new HashMap<Key, TuplaString>();
		
		String estadoInicial = configuracaoEntrada.get(2);
		List<String> estadosFinais = Arrays.asList(configuracaoEntrada.get(3).split(","));
		
		List<String> alfabeto = Arrays.asList(configuracaoEntrada.get(1).split(","));
		
		if(ativarTrace) {
			System.out.println("\nEstados pertecentes ao automato: " + configuracaoEntrada.get(0));
			System.out.println("Alfabeto utilizado: " + alfabeto.toString());
			System.out.println("Estado inicial: " + estadoInicial);
			System.out.println("Estados finais: " + estadosFinais.toString());
		}
		
		for(int i = 4; i < configuracaoEntrada.size(); i++) {
			List<String> transicao = Arrays.asList(configuracaoEntrada.get(i).split(","));
			
			if(ativarTrace) {
				System.out.print("Transicao adicionada:\n" +
						"\tEstado atual: " + transicao.get(0) +
						"\n\tSimbolo: " + transicao.get(1) + 
						"\n\tProximo Estado: " + transicao.get(2));
			}
			
			transicoes.put(new Key(transicao.get(0), transicao.get(1), ""), new TuplaString(transicao.get(2), new ArrayList<String>()));
		}
		
		AutomatoFinitoDeterministico automatoFD = new AutomatoFinitoDeterministico(transicoes, estadosFinais, estadoInicial, ativarTrace, alfabeto);
		
		System.out.println("\nDispositivo carregado com sucesso.");
		System.out.print("Entre a cadeia a ser analisada: ");
		
		String cadeia = input.nextLine();
		
		System.out.println();
		
		automatoFD.iniciar(cadeia);
		
		List<Automato> resultadoAutomato= automatoFD.executar();
		
		boolean reconheceu = false;
		
		for(Automato a : resultadoAutomato) {
			if(a.estaEmEstadoFinal())
				if(a.estaEmFinalDeCadeia())
					reconheceu = true;
		}
		
		System.out.println("\nO automato reconheceu: " + reconheceu);
		System.out.println("\n----------------------------------------");
		
	}

}
