/*
Faca um programa que leia um numero inteiro n indicando o numero de
valores reais que devem ser lidos e salvos sequencialmente em um arquivo texto. Apos a leitura
dos valores, devemos fechar o arquivo. Em seguida, reabri-lo e fazer a leitura de tras para frente
usando os metodos getFilePointer e seek da classe RandomAccessFile e mostre todos os valores
lidos na tela. Nessa questao, voce nao pode usar, arrays, strings ou qualquer estrutura de dados.
A entrada padrao e composta por um numero inteiro n e mais n numeros reais. A saıda padrao
corresponde a n numeros reais mostrados um por linha de saıda.
*/

#include <stdio.h>
#include <stdlib.h>


     void main(int argc, char *argv[]){

     int n ;
//     scanf("%i",&n);


     double real ;

     
    FILE *arquivo = fopen("exemplo.txt", "w");

      if (arquivo == NULL) // Se nào conseguiu criar
     {
           printf("Problemas na CRIACAO do arquivo\n");

     }

        for(int i = 0; i < n ; i++){
         scanf("%f\n",&real);
         printf("%f",real);
         fprintf(arquivo, "%f\n",&real);
        } 
 
       fclose(arquivo); 


    FILE *arquivo2 = fopen("exemplo.txt", "r");

         for(int i = n-1; i >= 0 ; i--){
            fseek(arquivo2 ,8*i ,n-1);
            fscanf(arquivo2, "%f\n", &real);
            fprintf(arquivo2, "%f\n", &real);
         }

         fclose(arquivo2); 
    }

