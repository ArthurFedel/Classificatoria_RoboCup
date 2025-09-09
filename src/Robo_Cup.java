import java.util.Scanner;

public class Robo_Cup {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int qntEquipes, qntCombates, num;
        double auxiliar;
        boolean troca;
        char placar;

        // 1 Quantidade de equipes
        System.out.println("Digite a quantidade de equipes participantes da competição: ");
        qntEquipes = sc.nextInt();

        // Arrays
        double [] notaDesign = new double [qntEquipes];
        //Matriz na qual a primeira coluna refere-se ao numero da equipe, e a segunda a nota da luta
        int [][] dadosEquipe = new int [qntEquipes][2];

        for(int i=0;i<qntEquipes;i++) {
            //numero de identificação da equipe
            // While para digitar o número da equipe, ver se é valido
            do {
                troca = false;
                System.out.print("Digite o número da equipe "+(i+1)+" (10 a 99): ");
                num = sc.nextInt();
                if (num < 10 || num > 99) {
                    System.out.println("Número inválido! Somente são validos valores entre 10 e 99.");
                    troca = true;
                //else if () para validar se o número já foi digitado, somente conferindo a partir da segunda rodada do for() principal
                } else if (i!=0){
                    for (int j = 0; j < i; j++) {
                        //dadosEquipe[j][0] é o vetor que contém os numeros validos digitados até agora, num é o numero que esta em analise no momento
                        if (dadosEquipe[j][0] == num) {
                            System.out.println("Número já cadastrado! Digite outro.");
                            troca = true;
                        }
                    }
                }
            } while (troca);
            //so entra no vetor depois do num ser considerado valido
            dadosEquipe[i][0] = num;

            //2 Nota do design
            System.out.println("Digite a nota de design (de 0 a 10) que a equipe de número "+ num +" teve para o design do robô: ");
            notaDesign[i] = sc.nextDouble();
            while (notaDesign[i]<0 || notaDesign[i]>10) {
                System.out.println("Número inválido!! Digite somente notas entre 0 a 10: ");
                notaDesign[i] = sc.nextDouble();
            }

            //3 Combates
            System.out.println("Digite a quantidade de combates realizada pela equipe de número "+ num +" :");
            qntCombates = sc.nextInt();
            for (int j=0;j<qntCombates;j++) {
                System.out.println("Digite 'V' para vitoria, 'E' para empate e 'D' para derrota.");
                System.out.println("Qual foi o placar da luta "+ (j + 1) +" para a equipe de número " + num + ": ");
                placar=sc.next().toUpperCase().charAt(0);
                // validação
                while (!(placar =='V') && !(placar =='E') && !(placar =='D')) {
                    System.out.println("Digite somente 'V' para vitoria, 'E' para empate e 'E' para derrota: ");
                    placar = sc.next().charAt(0);
                }
                //aplicando as notas em dadosEquipe[i][1]
                switch (placar) {
                    case 'V':
                        dadosEquipe[i][1]+=7;
                        break;
                    case 'E':
                        dadosEquipe[i][1]+=4;
                        break;
                }
            }
        }
        //4 Ordenação do ranking
        for (int i = 0; i < qntEquipes - 1; i++) {
            for (int j = i + 1; j < qntEquipes; j++) {
                troca = false;

                // primeiro critério: pontos
                if (dadosEquipe[j][1] > dadosEquipe[i][1]) {
                    troca = true;
                }
                // se empatar em pontos, desempate pelo design
                else if (dadosEquipe[j][1] == dadosEquipe[i][1] && notaDesign[j] > notaDesign[i]) {
                    troca = true;
                }

                if (troca) {
                    // troca pontos
                    auxiliar = dadosEquipe[i][1];
                    dadosEquipe[i][1] = dadosEquipe[j][1];
                    dadosEquipe[j][1] = (int) auxiliar;

                    // troca notas
                    auxiliar = notaDesign[i];
                    notaDesign[i] = notaDesign[j];
                    notaDesign[j] = auxiliar;

                    // troca numeros das equipes
                    auxiliar = dadosEquipe[i][0];
                    dadosEquipe[i][0] = dadosEquipe[j][0];
                    dadosEquipe[j][0] = (int) auxiliar;
                }
            }
        }
        // 5) Exibir classificação final
        System.out.println("\n--- CLASSIFICAÇÃO FINAL ---");
        for (int i = 0; i < qntEquipes; i++) {
            System.out.println((i+1) + "º lugar - Equipe " + dadosEquipe[i][0] +
                    " | Pontos: " + dadosEquipe[i][1] +
                    " | Design: " + notaDesign[i]);
        }
    }
}




