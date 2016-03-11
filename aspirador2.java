/* O código-fonte abaixo foi desenvolvido por Mateus Dutra, aluno do 7º período do
Bacharelado de Sistemas de Informação da FAMERC (2016.1), e tem a intenção de auxiliar
todos os estudantes e interessados por sistemas informatizados em seus estudos e
aprimoramentos. O código abaixo demonstra a aplicação da Inteligência Artificial sobre
o agente inteligente Aspirador de Pó, não tendo visão de ser um sistema completo, sequer
complexo. Aproveite bem o material e bons estudos! Quaisquer dúvidas, entre em contato:

EMAIL - ti-mateusdutra@live.com
TWITTER - @MDTomate
GitHub - MDTomate								*/

//Importando bibliotecas em uso
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

//Iniciando o sistema
public class aspirador2 {

	//Declarando variáveis globais
	public static int tam, v[], mid, pos, dir, cln, mov = 0, ok = 0;
	public static String msg;
	public static long bgn, end, time;

	//Criando codes de cor
	public static String y = "\u001B[33m", w = "\u001B[0m", c = "\u001B[36m", p = "\u001B[35m";

	//Função para limpar a tela e imprimir o cabeçalho
	public static void head() throws IOException, InterruptedException {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		System.out.print(y + "\n=======================> " + p + "ASPIRADOR DE PÓ v2.0" + y);
		System.out.print("\n\t\t\t\t\t\tby Mateus Dutra (@MDTomate)\n\n\t\t\t");
		System.out.print(c + "Distribua apenas para estudo!\n\t\t\tComercialização proibida!");
		System.out.print(y + "\n\n=======================> INTELIGÊNCIA ARTIFICIAL");
		System.out.print(w + "\n\n\tEntre com a área de atuação do aspirador.\n\t\tNúmero de pisos > " + c + tam);
		System.out.println(y + "\n\n\t\t[ 1 ] <- piso sujo\n\t\t[ 0 ] <- piso limpo\n" + w);
	}

	//Função para imprimir a área
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

	//Função para centralizar o aspirador ao chegar em uma das pontas da área	
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

	//Função para mover o aspirador e retornar o movimento executado
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
			//Criação do cabeçalho
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			System.out.print(y + "\n=======================> " + p + "ASPIRADOR DE PÓ v2.0" + y);
			System.out.print("\n\t\t\t\t\t\tby Mateus Dutra (@MDTomate)\n\n\t\t\t");
			System.out.print(c + "Distribua apenas para estudo!\n\t\t\tComercialização proibida!");
			System.out.print(y + "\n\n=======================> INTELIGÊNCIA ARTIFICIAL");
			System.out.print(w + "\n\n\tEntre com a área de atuação do aspirador.\n\t\tNúmero de pisos > " + c);
			
			//Garante que a entrada seja sempre um valor de tipo inteiro
			while (!io.hasNextInt()) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				System.out.print(y + "\n=======================> " + p + "ASPIRADOR DE PÓ v2.0" + y);
				System.out.print("\n\t\t\t\t\t\tby Mateus Dutra (@MDTomate)\n\n\t\t\t");
				System.out.print(c + "Distribua apenas para estudo!\n\t\t\tComercialização proibida!");
				System.out.print(y + "\n\n=======================> INTELIGÊNCIA ARTIFICIAL");
				System.out.print(w + "\n\n\tEntre com a área de atuação do aspirador.");
				System.out.print(y + "\n\t\tERRO: " + w + "Digite um valor " + c + "inteiro" + w + ", como " + c + "4 " + w + "ou " + c + "17" + w + ". > " + c);
				io.next();
			}

			//Quebra o ciclo quando um valor inteiro for inserido
			tam = io.nextInt();
			ok = 1;

		} while (ok == 0);
		
		//Inicia a criação da área
		v = new int[tam];
		head();

		//Inicialização do fator randômico
		Random rand = new Random();

		//Preenchimento da área aleatoriamente
		for (int i = 0; i < tam; i++)
			v[i] = rand.nextInt(2);

		//Definição do centro da área
		if (tam % 2 == 1)
			mid = (tam/2);
		else
			mid = (tam/2) - 1;

		//Centralização do aspirador
		pos = mid;

		//Iniciação da limpeza
		head();
		draw();
		io.nextLine();
		System.out.print(w + "\n\n\t\tPressione " + y + "Enter " + w + "para começar a limpeza.");
		io.nextLine();

		//Definição randômica da direção inicial do aspirador
		dir = rand.nextInt(2);

		//Inicia a contagem do tempo de execução do programa
		bgn = System.currentTimeMillis();

		for (;;) {

			//Verifica se o aspirador chegou à alguma das pontas da área
			if (pos == 0) {
				msg = w + "O aspirador chegou à " + c + "ponta da direita " + w + "e será movido para o " + c + "centro" + w + ".";
				mid();
				dir = 1;
			} else if (pos == tam) {
				msg = w + "O aspirador chegou à " + c + "ponta da esquerda " + w + "e será movido para o " + c + "centro" + w + ".";
				mid();
				dir = 0;
			}

			//Movimenta o aspirador ao longo da área e mostra sua posição
			while (v[pos] != 1 && pos > 0 && pos < tam) {
				if (dir == 1) {
					if (pos < tam-1) {
						pos++;
						msg = w + "O aspirador se movimentou para a " + c + "direita" + w + ".";
						move();
					} else {
						msg = w + "O aspirador chegou à " + c + "ponta da esquerda " + w + "e será movido para o " + c + "centro" + w + ".";
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

			//Verifica se todos pisos estão limpos
			for (int j = 0; j < tam; j++) {
				if (v[j] == 0)
					cln++;
				else
					cln--;
			}
			if (cln == tam)
				break; //se sim, o looping for é quebrado
			else
				cln = 0;
		}

		//Finaliza a contagem da execução
		end = System.currentTimeMillis();
		time = end - bgn;

		//Desenha o estado final da área
		head();
		draw();

		//Termina o programa mostrando quantos movimentos foram realizados e em quanto tempo
		System.out.print(w + "\n\n\t\tO vetor está " + c + "LIMPO" + w + "! Foram feitos " + y + mov + w + " movimentos em " + y + (time/1000) + w + "s.\n\t\t");
		System.exit(0);
	}

}