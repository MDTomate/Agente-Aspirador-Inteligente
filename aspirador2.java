/* O c�digo-fonte abaixo foi desenvolvido por Mateus Dutra, aluno do 7� per�odo do
Bacharelado de Sistemas de Informa��o da FAMERC (2016.1), e tem a inten��o de auxiliar
todos os estudantes e interessados por sistemas informatizados em seus estudos e
aprimoramentos. O c�digo abaixo demonstra a aplica��o da Intelig�ncia Artificial sobre
o agente inteligente Aspirador de P�, n�o tendo vis�o de ser um sistema completo, sequer
complexo. Aproveite bem o material e bons estudos! Quaisquer d�vidas, entre em contato:

EMAIL - ti-mateusdutra@live.com
TWITTER - @MDTomate
GitHub - MDTomate								*/

//Importando bibliotecas em uso
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

//Iniciando o sistema
public class aspirador2 {

	//Declarando vari�veis globais
	public static int tam, v[], mid, pos, dir, cln, mov = 0, ok = 0;
	public static String msg;
	public static long bgn, end, time;

	//Criando codes de cor
	public static String y = "\u001B[33m", w = "\u001B[0m", c = "\u001B[36m", p = "\u001B[35m";

	//Fun��o para limpar a tela e imprimir o cabe�alho
	public static void head() throws IOException, InterruptedException {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		System.out.print(y + "\n=======================> " + p + "ASPIRADOR DE P� v2.0" + y);
		System.out.print("\n\t\t\t\t\t\tby Mateus Dutra (@MDTomate)\n\n\t\t\t");
		System.out.print(c + "Distribua apenas para estudo!\n\t\t\tComercializa��o proibida!");
		System.out.print(y + "\n\n=======================> INTELIG�NCIA ARTIFICIAL");
		System.out.print(w + "\n\n\tEntre com a �rea de atua��o do aspirador.\n\t\tN�mero de pisos > " + c + tam);
		System.out.println(y + "\n\n\t\t[ 1 ] <- piso sujo\n\t\t[ 0 ] <- piso limpo\n" + w);
	}

	//Fun��o para imprimir a �rea
	public static void draw() throws IOException, InterruptedException {
		System.out.print("\t");
		for (int i = 0; i < tam; i++)
			if (i == pos)
				System.out.print(p + "[:" + v[i] + ":]" + w);
			else if (v[i] == 0)
				System.out.print("[ " + c + v[i] + w + " ]");
			else
				System.out.print("[ " + y + v[i] + w + " ]");
	}

	//Fun��o para centralizar o aspirador ao chegar em uma das pontas da �rea	
	public static void mid() throws IOException, InterruptedException {
		head();
		draw();
		System.out.print("\n\n\t\t" + msg);
		new Thread().sleep(800);
		pos = mid;
		dir = 1;
		head();
		draw();
		System.out.print("\n\n\t\tAspirador posicionado no centro.");
		new Thread().sleep(800);
	}

	//Fun��o para mover o aspirador e retornar o movimento executado
	public static void move() throws IOException, InterruptedException {
		head();
		draw();
		System.out.print("\n\n\t\t" + msg);
		new Thread().sleep(800);
		mov++;
	}

	//Iniciando a classe principal do sistema
	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner io = new Scanner(System.in);

		//Verifica a entrada do valor
		do {
			//Cria��o do cabe�alho
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			System.out.print(y + "\n=======================> " + p + "ASPIRADOR DE P� v2.0" + y);
			System.out.print("\n\t\t\t\t\t\tby Mateus Dutra (@MDTomate)\n\n\t\t\t");
			System.out.print(c + "Distribua apenas para estudo!\n\t\t\tComercializa��o proibida!");
			System.out.print(y + "\n\n=======================> INTELIG�NCIA ARTIFICIAL");
			System.out.print(w + "\n\n\tEntre com a �rea de atua��o do aspirador.\n\t\tN�mero de pisos > " + c);
			
			//Garante que a entrada seja sempre um valor de tipo inteiro
			while (!io.hasNextInt()) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				System.out.print(y + "\n=======================> " + p + "ASPIRADOR DE P� v2.0" + y);
				System.out.print("\n\t\t\t\t\t\tby Mateus Dutra (@MDTomate)\n\n\t\t\t");
				System.out.print(c + "Distribua apenas para estudo!\n\t\t\tComercializa��o proibida!");
				System.out.print(y + "\n\n=======================> INTELIG�NCIA ARTIFICIAL");
				System.out.print(w + "\n\n\tEntre com a �rea de atua��o do aspirador.");
				System.out.print(y + "\n\t\tERRO: " + w + "Digite um valor " + c + "inteiro" + w + ", como " + c + "4 " + w + "ou " + c + "17" + w + ". > " + c);
				io.next();
			}

			//Quebra o ciclo quando um valor inteiro for inserido
			tam = io.nextInt();
			ok = 1;

		} while (ok == 0);
		
		//Inicia a cria��o da �rea
		v = new int[tam];
		head();

		//Inicializa��o do fator rand�mico
		Random rand = new Random();

		//Preenchimento da �rea aleatoriamente
		for (int i = 0; i < tam; i++)
			v[i] = rand.nextInt(2);

		//Defini��o do centro da �rea
		if (tam % 2 == 1)
			mid = (tam/2);
		else
			mid = (tam/2) - 1;

		//Centraliza��o do aspirador
		pos = mid;

		//Inicia��o da limpeza
		head();
		draw();
		io.nextLine();
		System.out.print(w + "\n\n\t\tPressione " + y + "Enter " + w + "para come�ar a limpeza.");
		io.nextLine();

		//Defini��o rand�mica da dire��o inicial do aspirador
		dir = rand.nextInt(2);

		//Inicia a contagem do tempo de execu��o do programa
		bgn = System.currentTimeMillis();

		for (;;) {

			//Verifica se o aspirador chegou � alguma das pontas da �rea
			if (pos == 0) {
				msg = w + "O aspirador chegou � " + c + "ponta da direita " + w + "e ser� movido para o " + c + "centro" + w + ".";
				mid();
				dir = 1;
			} else if (pos == tam) {
				msg = w + "O aspirador chegou � " + c + "ponta da esquerda " + w + "e ser� movido para o " + c + "centro" + w + ".";
				mid();
				dir = 0;
			}

			//Movimenta o aspirador ao longo da �rea e mostra sua posi��o
			while (v[pos] != 1 && pos > 0 && pos < tam) {
				if (dir == 1) {
					if (pos < tam-1) {
						pos++;
						msg = w + "O aspirador se movimentou para a " + c + "direita" + w + ".";
						move();
					} else {
						msg = w + "O aspirador chegou � " + c + "ponta da esquerda " + w + "e ser� movido para o " + c + "centro" + w + ".";
						mid();
						dir = 0;
					}
				} else {
					pos--;
					msg = w + "O aspirador se movimentou para a " + c + "esquerda" + w + ".";
					move();
				}
			}

			//Efetua a limpeza do piso sujo
			if (v[pos] == 1) {
				v[pos] = 0;
				head();
				draw();
				System.out.print(w + "\n\n\t\tO aspirador limpou o piso em " + c + "destaque" + w + ".");
				new Thread().sleep(800);
			}

			//Verifica se todos pisos est�o limpos
			for (int j = 0; j < tam; j++) {
				if (v[j] == 0)
					cln++;
				else
					cln--;
			}
			if (cln == tam)
				break; //se sim, o looping for � quebrado
			else
				cln = 0;
		}

		//Finaliza a contagem da execu��o
		end = System.currentTimeMillis();
		time = end - bgn;

		//Desenha o estado final da �rea
		head();
		draw();

		//Termina o programa mostrando quantos movimentos foram realizados e em quanto tempo
		System.out.print(w + "\n\n\t\tO vetor est� " + c + "LIMPO" + w + "! Foram feitos " + y + mov + w + " movimentos em " + y + (time/1000) + w + "s.\n\t\t");
		System.exit(0);
	}

}