/*
Faca um programa que leia um numero inteiro n indicando o numero de
valores reais que devem ser lidos e salvos sequencialmente em um arquivo texto. Apos a leitura
dos valores, devemos fechar o arquivo. Em seguida, reabri-lo e fazer a leitura de tras para frente
usando os metodos getFilePointer e seek da classe RandomAccessFile e mostre todos os valores
lidos na tela. Nessa questao, voce nao pode usar, arrays, strings ou qualquer estrutura de dados.
A entrada padrao e composta por um numero inteiro n e mais n numeros reais. A saıda padrao
corresponde a n numeros reais mostrados um por linha de saıda.
*/

import java.io.*;
import java.text.DecimalFormat;

class Arquivo{

    public static void main(String[] args)throws Exception{

     int n;
     n = MyIO.readInt();     


     double real = 0;
     DecimalFormat format = new DecimalFormat("##");
     format.format(real);
    RandomAccessFile arquivo = new RandomAccessFile("exemplo.txt", "rw");

        for(int i = 0; i < n ; i++){
           real = MyIO.readDouble();
           arquivo.writeDouble(real);
        } 
 
          arquivo.close(); 


    RandomAccessFile arquivo2 = new RandomAccessFile("exemplo.txt", "rw");  
        

        
         for(int i = n-1; i >= 0 ; i--){
           arquivo2.seek(8*i);
         
           MyIO.println(arquivo2.readDouble());
         } 

           arquivo2.close();
  }
}