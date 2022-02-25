#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
       
        //Verifica se é Palindromo ou não
         bool Palindromo(char s[],int i){
          bool resp = true; 
          int p = strlen(s);

           for(int i = p/2; i < p; i++ ){
              if(s[i] != s[p-1-i]){
                   resp = false;
             }
           }
              return resp;
     }

         bool Fim(char h[]){
            return (strlen(h) == 3 && h[0] == 'F' && h[1] == 'I' && h[2] == 'M');
         }

       void main(){
           char s[1000][100];
           int o;
             
           //entrada verifica se a palavra é FIM     
           scanf(" %[^\n]s",s[o]);
          while(Fim(s[o]) == false){
              o++;
            scanf(" %[^\n]s",s[o]);
          }

        //saida printa na tela SIM se for Palindromo e NAO se não for
         for(int i = 0; i < o; i++){
             if(Palindromo(s[i],0) != true){
                 printf("NAO\n");
             }
              else{
                  printf("SIM\n");
               }
         }
   
        }