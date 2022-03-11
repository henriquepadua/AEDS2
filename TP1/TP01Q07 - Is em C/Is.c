/*Crie um metodo iterativo que recebe uma string e retorna true se a mesma e
composta somente por vogais. Crie outro metodo iterativo que recebe uma string e retorna
true se a mesma e composta somente por consoantes. Crie um terceiro metodo iterativo que
recebe uma string e retorna true se a mesma corresponde a um numero inteiro. Crie um quarto
metodo iterativo que recebe uma string e retorna true se a mesma corresponde a um numero
real. Na saıda padrao, para cada linha de entrada, escreva outra de saıda da seguinte forma X1
X2 X3 X4 onde cada Xi e um booleano indicando se a e entrada e: composta somente por vogais
(X1); composta somente somente por consoantes (X2); um numero inteiro (X3); um numero real
(X4). Se Xi for verdadeiro, seu valor sera SIM, caso contrario, NAO.
*/
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

                   // verifica se a entrada lida possui vogais
                 bool Vogal(char h[]){
	bool resp=true;
	int n = strlen(h);
	for(int i = 0 ;i <  n;i++){
                        if(h[i] != 'a' && h[i] != 'e' && h[i] != 'u' && h[i] != 'i' && h[i] != 'o' )
                        {
	         resp = false;
                         }
                    }
                         return resp;
                    }

                                        	
                   // verifica se a entrada lida possui consoantes
	bool Consoante(char h[]){
	bool resp=true;
	int n = strlen(h);
	for(int i = 0;i<n;i++){
                        if((h[i] == 'a' || h[i] == 'e' || h[i] == 'u' || h[i] == 'i' || h[i] == 'o'))
                        {
	         resp=false;
                         }
                    }
                         return resp;
                    }

                   // verifica se a entrada lida possui numeros inteiros
	bool Inteiro(char h[]){
	bool resp = true;
	int inteiro=0;
	for(inteiro=0;inteiro < strlen(h);inteiro++){
	  if(h[inteiro] != '9'   && h[inteiro] != '8'  && h[inteiro] != '7'   && h[inteiro] != '6' &&  h[inteiro] != '5' && h[inteiro] != '4' && h[inteiro] != '3' && h[inteiro] != '2' && h[inteiro] != '1' && h[inteiro] != '0' ){
                            resp = false;		
                      }
                    }
	      return resp;
                     }
                 
                   // verifica se a entrada lida possui numeros reais
	bool Real(char h[]){
	
	bool resp = false;
	double real=0;
	int inteiro = 0;
	if(real==inteiro){
	  resp=false;
                    }

 	else{
	   for(real=0;real<1000;real++){
	     resp=true;
                       }
                    }
                              return resp;
                    }

                   //Verifica se a palavra é FIM
 	bool Fim (char s[]){
	    return (strlen(s) == 3 && s[0]=='F' && s[1]=='I' && s[2]=='M');
                   }


        
                   //Chama metodo principal
                   void main(){
                     char entrada [1000][100];
                     int Nentrada = 0;

 
                   //chama os 4 metodos na ordem Vogal,Consoante,Inteiro e Real fazendo a verificação das entradas
	do{
                       scanf(" %[^\n]s",entrada[Nentrada]);
	}while (Fim (entrada[Nentrada++])==false);
	Nentrada--;

	for(int i=0;i<Nentrada;i++){
	
	if(Vogal(entrada[i])==true){
	printf("SIM ");
	}else{
	printf("NAO ");
	}

	if(Consoante(entrada[i])==true){
	printf("SIM ");
	}else{
	printf("NAO ");
	}

	
	if(Inteiro(entrada[i])==true){
	printf("SIM ");
	}else{
	printf("NAO ");
	}

	
	if(Real(entrada[i])==true){
	printf("SIM\n");
	}else{
	printf("NAO\n");
	}
}


}
	




