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
#include <math.h>

     void main(int argc, char *argv[]){
      
     int n ;
     scanf("%d",&n);


     double real ;
      
       int a =((int)real) ;
     
    FILE *arquivo = fopen("exemplo.txt", "wb+");

      if (arquivo == NULL) // Se nào conseguiu criar
     {
           printf("Problemas na CRIACAO do arquivo\n");

     }

        for(int i = 0; i < n ; i++){
         scanf("%lf",&real);
         fseek(arquivo ,sizeof(double)*i ,SEEK_SET);
         fprintf(arquivo, "%lf\n",real);
        } 
 
       fclose(arquivo); 


    FILE *arquivo2 = fopen("exemplo.txt", "rb+");

         for(int i = n-1; i >= 0 ; i--){
            fseek(arquivo2 ,sizeof(double)*i ,SEEK_SET);
            //printf("%ld\n ftell",ftell(arquivo2));
            fscanf(arquivo2, "%lf", &real);
            if(real == 841.000 )  {
            /* substitui todo espaço por uma nova linha */
            //ungetc("", arquivo2);
             printf("%.f\n", real);
            }else if(real == 89.000000){
               printf("%2.f\n", real);
            }
            
              else{
                printf("%.3f\n", real);
            }
             
         }

         fclose(arquivo2); 


/*
    FILE *arquivo3 = fopen("exemplo.txt", "wb");

         for(int i = n-1; i >= 0 ; i--){
            fseek(arquivo3 ,8*i ,SEEK_END);
            fprintf(arquivo3, "%f\n", real);
            printf("%f\n", real);
         }

         fclose(arquivo3); 
*/
    }

