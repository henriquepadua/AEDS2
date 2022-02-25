/*
O Imperador Julio Cesar foi um dos principais nomes do
Imperio Romano. Entre suas contribuicoes, temos um algoritmo de criptografia chamado Ciframento de Cesar. Segundo os historiadores, Cesar utilizava esse algoritmo para criptografar as
mensagens que enviava aos seus generais durante as batalhas. A ideia basica e um simples deslocamento de caracteres. Assim, por exemplo, se a chave utilizada para criptografar as mensagens
for 3, todas as ocorrencias do caractere ’a’ sao substitu´ıdas pelo caractere ’d’, as do ’b’ por ’e’,
e assim sucessivamente. Crie um metodo iterativo que recebe uma string como parametro e
retorna outra contendo a entrada de forma cifrada. Neste exercıcio, suponha a chave de ciframento tres. Na saıda padrao, para cada linha de entrada, escreva uma linha com a mensagem
criptografada
*/

class CiframentoRec{

         //verifica se a palavra digitada é FIM 
         public static boolean Fim(String h ){
               return(h.length() == 3 && h.charAt(0) == 'F' && h.charAt(1) == 'I' && h.charAt(2) == 'M');
         }
         
         // metodo que retorna o Ciframento
         public static String Cifrar(String cifrar){
              boolean resp = true;
//              int i = 0;
             String troca = "";
              int n = cifrar.length();

            if(i == n){
               resp = false;
            }
              else{
                resp = true;
                troca = troca + (char)Cifrar(cifrar.charAt(i)+3);
                   i++;
              }
                 return troca;            
         }          


         public static void main(String[] args){
             String[] entrada = new String[705];
             int Nentrada = 0;
                 
               //verifica se a entrada é FIM
               entrada[Nentrada] = MyIO.readLine();
                while(Fim(entrada[Nentrada]) == false){
                     Nentrada ++;
                     entrada[Nentrada] = MyIO.readLine();
                }
               
               //printa na tela os numero com o ciframento            
               for(int i = 0;i < Nentrada; i++){
                   MyIO.println(Cifrar(entrada[i]));
              }       
        }
}

