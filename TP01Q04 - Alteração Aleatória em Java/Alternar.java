import java.io.*;
import java.util.*;

 class Alternar{

    public static String criar(String h, Random gerar) { //Fazaletraquevaisermudada
        
        char saindo = ((char) ('a' + (Math.abs(gerar.nextInt()) % 26)));
        char entrando = ((char) ('a' + (Math.abs(gerar.nextInt()) % 26)));
        String mudada = "";

        for (int i=0;i<h.length();i++){
            if (h.charAt(i)==saindo){
                mudada+=entrando;
            }else{
                mudada+=h.charAt(i);
            }
        }
        return mudada;
    }
  //chamaFunçãoFim
	public static boolean Fim(String h){
	return (h.length() == 3 && h.charAt(0) == 'F' && h.charAt(1) == 'I' && h.charAt(2) == 'M');
}  

  //chamaFunçãoPincipal
    public static void main (String[] args) // gera o valor aleatorio
    {
        Random gerador = new Random();
        gerador.setSeed(4);
       String[] entrada = new String[1000];
       int NumEntrada = 0;
       
       do{	//verificanaEntradaseFIMforfalsedesconsiderar a função
         entrada[NumEntrada] = MyIO.readLine();
       } while(Fim(entrada[NumEntrada++])==false);
       NumEntrada--;
         
       for(int i = 0;i<NumEntrada; i++)
       {
           MyIO.println(criar(entrada[i], gerador));
       }
    }
 }
