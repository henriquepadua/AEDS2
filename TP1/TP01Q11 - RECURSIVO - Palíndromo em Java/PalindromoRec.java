class PalindromoRec{

       //metodo verifica se o metodo é Palindromo   
      public static boolean Palindromo(String s,int i){
           boolean resp = true;
           int n = s.length();
//           int recursao = 0;


              if(s.charAt(i) != s.charAt(n-1-i)){
                      return false;          
             }else if(i == n/2){
                       return true;
             }else{
                      return Palindromo(s, i+1);
             }

//              return resp;
     }

      public static boolean Fim(String s){
           return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M'); 
      }

       public static void main(String[] args){
           String[] s = new String[1000];
             int Nentrada = 0;           
  
               //chama metodo Fim verificando se é FIM            
             s[Nentrada] = MyIO.readLine();
           while((Fim(s[Nentrada]) == false)){
                 Nentrada++;
                s[Nentrada] = MyIO.readLine();
           }
              


            //Se for palindromo printa na tela SIM caso contrário printa NAO
              for (int i =0 ; i < Nentrada; i++){
                   if(Palindromo(s[i],0)){
                        MyIO.println("SIM");
                   } else {
                        MyIO.println("NAO");
              } 
               }
       }
}