package br.puc.portifolio.algoritmos.ordenacao;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class MergeSort {

	public static void main(String[] args) {
		try (Scanner entrada = new Scanner(System.in)) {
			int tamanho = leiaInteiro(entrada, "Quantos elementos o vetor deve ter? ", 0, Integer.MAX_VALUE);
			byte[] vetor = new byte[tamanho];

			int formaPreenchimento = leiaInteiro(entrada,
					"Como deseja preencher? (1 - manual, 2 - aleatorio): ", 1, 2);

			if (formaPreenchimento == 1) {
				for (int i = 0; i < vetor.length; i++) {
					vetor[i] = (byte) leiaInteiro(entrada,
							"Digite o valor do elemento " + (i + 1) + " (-128 a 127): ", Byte.MIN_VALUE,
							Byte.MAX_VALUE);
				}
			} else {
				for (int i = 0; i < vetor.length; i++) {
					vetor[i] = (byte) ThreadLocalRandom.current().nextInt(0, 101);
				}
			}

			System.out.print("Vetor original: ");
			imprima(vetor);
			merge(vetor, true);
			System.out.print("Vetor ordenado: ");
			imprima(vetor);
		}
	}

	private static int leiaInteiro(Scanner entrada, String mensagem, int minimo, int maximo) {
		while (true) {
			System.out.print(mensagem);
			try {
				int valor = Integer.parseInt(entrada.nextLine());
				if (valor >= minimo && valor <= maximo) {
					return valor;
				}
			} catch (NumberFormatException e) {
			}
			System.out.println("Entrada invalida. Informe um numero entre " + minimo + " e " + maximo + ".");
		}
	}

	public static void merge(byte[] vetor, boolean crescente) {

		// Caso base
		if (vetor.length <= 1) {
			return;
		}

		// Divide o vetor ao meio
		int meio = vetor.length / 2;

		byte[] esquerda = new byte[meio];
		byte[] direita = new byte[vetor.length - meio];

		// Copia as duas partes
		System.arraycopy(vetor, 0, esquerda, 0, esquerda.length);

		System.arraycopy(vetor, meio, direita, 0, direita.length);

		// Ordena recursivamente
		merge(esquerda, crescente);
		merge(direita, crescente);

		// Junta as partes
		junte(vetor, esquerda, direita, crescente);
	}

	private static void junte(byte[] vetor, byte[] esquerda, byte[] direita, boolean crescente) {

		int posEsq = 0;
		int posDir = 0;
		int posOrg = 0;

		while (posEsq < esquerda.length && posDir < direita.length) {

			if (crescente) {

				// Crescente: pega o menor
				if (esquerda[posEsq] <= direita[posDir]) {
					vetor[posOrg] = esquerda[posEsq];
					posEsq++;
				} else {
					vetor[posOrg] = direita[posDir];
					posDir++;
				}

			} else {

				// Decrescente: pega o maior
				if (esquerda[posEsq] >= direita[posDir]) {
					vetor[posOrg] = esquerda[posEsq];
					posEsq++;
				} else {
					vetor[posOrg] = direita[posDir];
					posDir++;
				}
			}

			posOrg++;
		}

		// Restante da esquerda
		while (posEsq < esquerda.length) {
			vetor[posOrg] = esquerda[posEsq];
			posEsq++;
			posOrg++;
		}

		// Restante da direita
		while (posDir < direita.length) {
			vetor[posOrg] = direita[posDir];
			posDir++;
			posOrg++;
		}
	}

	public static void imprima(byte[] vetor) {

		for (byte valor : vetor) {
			System.out.print(valor + " ");
		}

		System.out.println();
	}
}
