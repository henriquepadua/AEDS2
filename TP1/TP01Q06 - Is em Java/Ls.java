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
class Ls{

                   // verifica se a entrada lida possui vogais
                    public static boolean Vogal(String h){
	boolean resp=true;
	int n = h.length();
	for(int i = 0 ;i <  n;i++){
                        if(h.charAt(i) != 'a' && h.charAt(i) != 'e' && h.charAt(i) != 'u' && h.charAt(i) != 'i' && h.charAt(i) != 'o' )
                        {
	         resp = false;
                         }
                    }
                         return resp;
                    }

                                        	
                   // verifica se a entrada lida possui constantes
	public static boolean Consoante(String h){
	boolean resp=true;
	int n = h.length();
	for(int i = 0;i<n;i++){
                        if((h.charAt(i) == 'a' || h.charAt(i) == 'e' || h.charAt(i) == 'u' || h.charAt(i) == 'i' || h.charAt(i) == 'o'))
                        {
	         resp=false;
                         }
                    }
                         return resp;
                    }

                   // verifica se a entrada lida possui numeros inteiros
	public static boolean Inteiro(String h){
	boolean resp = true;
	int inteiro=0;
	for(inteiro=0;inteiro<h.length();inteiro++){
	  if(h.charAt(inteiro) != '9'   && h.charAt(inteiro) != '8'  && h.charAt(inteiro) != '7'   && h.charAt(inteiro) != '6' &&  h.charAt(inteiro) != '5' && h.charAt(inteiro) != '4' && h.charAt(inteiro) != '3' && h.charAt(inteiro) != '2' && h.charAt(inteiro) != '1' && h.charAt(inteiro) != '0' ){
                            resp = false;		
                      }
                    }
	      return resp;
                     }
                 
                   // verifica se a entrada lida possui numeros reais
	public static boolean Real(String h){
	
	boolean resp = false;
	double real=0;
	int inteiro = 0;
	if(real==inteiro){
	  resp=false;
                    }

 	else{
	   for(real=0;real<h.length();real++){
	     resp=true;
                       }
                    }
                              return resp;
                    }

                   //Verifica se a palavra é FIM
 	public static boolean Fim (String s){
	    return (s.length()==3 && s.charAt(0)=='F' && s.charAt(1)=='I' && s.charAt(2)=='M');
                   }


        
                  //Chama metodo principal
                  public static void main(String[] args){
                     String[] entrada = new String[1000];
                     int Nentrada = 0;

 
                   //chama os 4 metodos na ordem Vogal,Consoante,Inteiro e Real fazendo a verificação das entradas
	do{
	entrada[Nentrada]=MyIO.readLine();
	}while (Fim (entrada[Nentrada++])==false);
	Nentrada--;

	for(int i=0;i<Nentrada;i++){
	
	if(Vogal(entrada[i])==true){
	MyIO.print("SIM ");
	}else{
	MyIO.print("NAO ");
	}

	if(Consoante(entrada[i])==true){
	MyIO.print("SIM ");
	}else{
	MyIO.print("NAO ");
	}

	
	if(Inteiro(entrada[i])==true){
	MyIO.print("SIM ");
	}else{
	MyIO.print("NAO ");
	}

	
	if(Real(entrada[i])==true){
	MyIO.println("SIM ");
	}else{
	MyIO.println("NAO ");
	}
}


}
	
}



