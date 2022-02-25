import java.io.*;
import java.net.*;

class HTML{

   public static String getHtml(String endereco){
      URL url;
      InputStream is = null;
      BufferedReader br;
      String resp = "", line;

      try {
         url = new URL(endereco);
         is = url.openStream();  // throws an IOException
         br = new BufferedReader(new InputStreamReader(is));

         while ((line = br.readLine()) != null) {
            resp += line + "\n";
         }
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

      return resp;
   }
           public static boolean Fim (String s){
	    return (s.length()==3 && s.charAt(0)=='F' && s.charAt(1)=='I' && s.charAt(2)=='M');
           }

   public static void main(String[] args) {
      String[] endereco = new String[1000];
      String paginaweb;
      int Nentrada = 0;

      endereco[Nentrada] = MyIO.readLine();
      paginaweb = MyIO.readLine();
      paginaweb = getHtml(endereco[Nentrada]);

     while(Fim(endereco[Nentrada]) == false){
                Nentrada++;
      endereco[Nentrada] = MyIO.readLine();
     }
   }
}
