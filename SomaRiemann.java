package calculo;
import java.util.Scanner;
public class SomaRiemann {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        double coeficiente = 0;
        String variavel = null;
        double coeficienteDaVariavel = 1;
        String operacao = "+";
        
        System.out.println("Informe a função: "); // precisa usar espaços
        String funcao = sc.nextLine();
        System.out.println("Informe o intervalo da integral: ");
        System.out.println("Limite inferior: ");
        double limiteInferior = sc.nextDouble();
        System.out.println("Limite superior: ");
        double limiteSuperior = sc.nextDouble();
        System.out.println("Informe a quantidade de subintervalos: ");
        int subintervalos = sc.nextInt();
        System.out.println("Informe o expoente da variável: ");
        int expoente = sc.nextInt();

        String[] tokens = funcao.split ("\\s+"); // divide por espaços
        for (String token : tokens) {
            if (token.matches("^[+-]?\\d*\\.?\\d+$")) { //checa por números
                if (token.startsWith("-")) { //não funciona
                    coeficiente = Double.parseDouble(token);
                    coeficiente = -coeficiente;
                }
                else {
                    coeficiente = Double.parseDouble(token);
                }  
            } 
            else if (token.matches("^[+-]?\\d*\\.?\\d*[a-zA-Z].*$")) { // checa por letras
                variavel = token.replaceAll("[^a-zA-Z]", ""); // Remove caracteres que não estão no alfabeto
                String coeficienteString = token.replaceAll("[a-zA-Z]", ""); // Remove caracteres do alfabeto
                if (!coeficienteString.isEmpty()) {
                    coeficienteDaVariavel = Double.parseDouble(coeficienteString);
                } 
            }
            else {
                operacao = token;
            }
        }

        double tamanhoSubintervalo = (limiteSuperior - limiteInferior) / subintervalos;
        realizarCalculo(tamanhoSubintervalo, expoente, coeficiente, variavel, coeficienteDaVariavel, operacao, limiteInferior, limiteSuperior, subintervalos);
        sc.close();

    }

    public static void realizarCalculo(double tamanhoSubintervalo, int expoente, double coeficiente, String variavel, double coeficienteDaVariavel, String operacao, double limiteInferior, double limiteSuperior, int subintervalos) {
        double areaTotal = 0;
        double alturaDireita, alturaEsquerda;

        for (int i = 0; i < subintervalos; i++) {
            double xEsquerda = limiteInferior + i * tamanhoSubintervalo;
            double xDireita = xEsquerda + tamanhoSubintervalo;

            //calcular altura do retângulo
            if (operacao.equals("+")) {
                alturaEsquerda = coeficienteDaVariavel * Math.pow(xEsquerda, expoente) + coeficiente;
                alturaDireita = coeficienteDaVariavel * Math.pow(xDireita, expoente) + coeficiente;
            }
            else {
                alturaEsquerda = coeficienteDaVariavel * Math.pow(xEsquerda, expoente) - coeficiente;
                alturaDireita = coeficienteDaVariavel * Math.pow(xDireita, expoente) - coeficiente;
            }
            //media das alturas nos pontos esquerdos e direitos
            double altura = (alturaEsquerda + alturaDireita) / 2;
            double areaRetangulo = altura * tamanhoSubintervalo;

            areaTotal += areaRetangulo;
            System.out.println(areaTotal);
        }
        System.out.println("Resultado da soma de Riemann: " + areaTotal);
    }
}
