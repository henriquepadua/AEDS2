/*
Algebra Booleana em Java  - Crie um metodo iterativo que recebe uma string contendo uma
expressao booleana e o valor de suas entradas e retorna um booleano indicando se a expressao
e verdadeira ou falsa. Cada string de entrada e composta por um numero inteiro n indicando
o numero de entradas da expressao booleana corrente. Em seguida, a string contem n valores
binarios (um para cada entrada) e a expressao booleana. Na saıda padrao, para cada linha
de entrada, escreva uma linha de saıda com SIM / NAO indicando se a expressao corrente e 
verdadeira ou falsa.
*/

class AlgebraBooleana{
   
   public static boolean Verifica(String s){
          boolean  expres = false; 
          String a = "",b = "",c = "";
          int n = s.length();


            if( !((s == a) && (s == b) == false)){
                   expres = true;
            }
              else if( ((s == a) && (s == b) && (s == c) == true)){
                   expres = false;                        
              }

              return expres;
   }   

   public static boolean Fim(String h){
        return (h.length() == 3 && h.charAt(0) == 'F' && h.charAt(1) == 'I' && h.charAt(2) == 'M');
   }

   public static void main(String[] args){
       String[] entrada = new String[1000];
        int Nentrada = 0;

        entrada[Nentrada] = MyIO.readLine();
        while(Fim(entrada[Nentrada]) == false){
              Nentrada ++;
              entrada[Nentrada] = MyIO.readLine();
        }                     

       
           if(Verifica(entrada[i])){
             MyIO.println("1");           
           }
             else{
               MyIO.println("0");
            }
       
               
   }
}
