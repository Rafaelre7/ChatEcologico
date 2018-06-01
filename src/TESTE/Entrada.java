package TESTE;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class Entrada {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String idade;
        
//        System.out.println("Digite sua idade: ");
//        idade = entrada.nextInt();
        
       idade =  JOptionPane.showInputDialog(null, "Qual o numero?");
     
        		
        System.out.printf("Sua idade é " + idade + "\n");
        
    }
}