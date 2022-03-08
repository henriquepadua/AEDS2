import java.io.*;
import java.net.*;

class HTML {

   public static boolean Vogal(String h) {
      boolean resp = true;
      int n = h.length();

      for (int i = 0; i < n; i++) {

         if (h.charAt(i) != 'a' && h.charAt(i) != 'e' && h.charAt(i) != 'u' && h.charAt(i) != 'i'
               && h.charAt(i) != 'o') {
            resp = false;
         }
      }
      return resp;
   }

   public static boolean Consoante(String h) {
      boolean resp = true;
      int n = h.length();
      for (int i = 0; i < n; i++) {

         if ((h.charAt(i) == 'a' || h.charAt(i) == 'e' || h.charAt(i) == 'u' || h.charAt(i) == 'i'
               || h.charAt(i) == 'o')) {
            resp = false;
         }
      }
      return resp;
   }

   public static void getHtml(String endereco) {
      URL url;
      InputStream is = null;
      BufferedReader br;
      String resp = "",line ;
      char a = 'a',e = 'e', u = 'u';
      int x1 = 0, x2 = 0;

      try {
         url = new URL(endereco);
         is = url.openStream(); // throws an IOException
         br = new BufferedReader(new InputStreamReader(is));

         
          
            while ((line = br.readLine()) != null) {
               for(int i = 0 ;i < line.length(); i++){
               //MyIO.print(line);
               if(line.charAt(i) == 'a' ){
                  //MyIO.print(line);
                  
                  x1 ++;
               }
               
            }
            
              
               /*if((line = br.readLine()) != null && Character.toString(e).matches("[a-z?]")){
                  MyIO.print(line);
                  resp = e +"(x1)"+ x2++;
               }*/

                
            }
            MyIO.println(a + "(" + x1 + ")");
         

      } catch (MalformedURLException mue) {
         mue.printStackTrace();
      } catch (IOException ioe) {
         ioe.printStackTrace();
      }

      try {
         is.close();
      } catch (IOException ioe) {
         // nothing to see here

      }
   }

   public static boolean FIM(String h) {
      return (h.length() == 3 && h.charAt(0) == 'F' && h.charAt(1) == 'I' && h.charAt(2) == 'M');
   }

   public static void main(String[] args) {
      String[] endereco = new String[10];
      String nomeweb;
      int Nentrada = 0;

      nomeweb = MyIO.readLine();
      endereco[Nentrada] = MyIO.readLine();

      while (FIM(nomeweb) == false) {
         Nentrada++;
         nomeweb = MyIO.readLine();
         endereco[Nentrada] = MyIO.readLine();
            
      }

      for (int i = 0; i < Nentrada; i++) {
         getHtml(endereco[i]);
      }
   }
}
