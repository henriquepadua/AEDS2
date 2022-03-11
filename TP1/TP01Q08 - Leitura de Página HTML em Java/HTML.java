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
      String resp = "", line;
      char a = 'a', e = 'e', u = 'u', ii = 'i', o = 'o', aa = '\u00E1', ee ='\u00E9', iii = '\u00ED', oo = '\u00F3', uu = '\u00FA',aaa = '\u00E0',eee = '\u00E8',iiii = '\u00EC',oooo = '\u00F2',uuuu = '\u00F9', aaaa = '\u00E3',eeee = '\u00EA',iiiii = '\u00EE',ooooo = '\u00F4',uuuuu = '\u00FB',aaaaa = '\u00E2',eeeee = '\u00EA',iiiiii = '\u00EE',oooooo = '\u00F4',uuuuuu = '\u00FB';
      int x1 = 0, x2 = 0, x3 = 0, x4 = 0, x5 = 0, x6 = 0, x7 = 0, x8 = 0, x9 = 0, x10 = 0, x11 = 0, x12 = 0, x13 = 0,
            x14 = 0, x15 = 0, x16 = 0, x17 = 0, x18 = 0, x19 = 0, x20 = 0, x21 = 0, x22 = 0, x23 = 0, x24 = 0, x25 = 0,
            x26 = 0;

      try {
         url = new URL(endereco);
         is = url.openStream(); // throws an IOException
         br = new BufferedReader(new InputStreamReader(is));

         while ((line = br.readLine()) != null) {
            for (int i = 0; i < line.length(); i++) {
               if (line.charAt(i) == 'a') {
                  x1++;
                  x2++;
               }
               if (line.charAt(i) == 'e') {
                  x2++;
               }
               if (line.charAt(i) == 'i') {
                  x3++;
               }
               if (line.charAt(i) == 'o') {
                  x4++;
               }
               if (line.charAt(i) == 'u') {
                  x5++;
               }
               if (line.charAt(i) == '\u00E1') {
                  x6++;
               }
               if (line.charAt(i) == '\u00E9') {
                  x7++;
               }
               if (line.charAt(i) == '\u00ED') {
                  x8++;
               }
               if (line.charAt(i) == '\u00F3') {
                  x9++;
               }
               if (line.charAt(i) == '\u00FA') {
                  x10++;
               }if (line.charAt(i) == '\u00E0') {
                  x11++;
               }
               if (line.charAt(i) == '\u00E8') {
                  x12++;
               }
               if (line.charAt(i) == '\u00EC') {
                  x13++;
               }
               if (line.charAt(i) == '\u00F2') {
                  x14++;
               }
               if (line.charAt(i) == '\u00F9') {
                  x16++;
               }if (line.charAt(i) == '\u00E3') {
                  x17++;
               }
               if (line.charAt(i) == '\u00EA') {
                  x18++;
               }
               if (line.charAt(i) == '\u00EE') {
                  x19++;
               }
               if (line.charAt(i) == '\u00F4') {
                  x20++;
               }
               if (line.charAt(i) == '\u00FB') {
                  x21++;
               }
               }if (line.charAt(i) == '\u00E2') {
               x22++;
               }
               if (line.charAt(i) == '\u00EA') {
               x23++;
               }
               if (line.charAt(i) == '\u00EE') {
               x24++;
               }
               if (line.charAt(i) == '\u00F4') {
               x25++;
               }
               if (line.charAt(i) == '\u00FB') {
               x26++;
               }

            }
         

         MyIO.print(a + "(" + x1 + ") " + e + "(" + x2 + ") " + ii + "(" + x3 + ") " + o + "(" + x4 + ") " + u + "("
               + x5 + ") " + aa + "(" + x6 + ") " + ee + "(" + x7 + ") " + iii + "(" + x8 + ") " + oo + "(" + x9 + ") "+ uu + "(" + x10 + ") " + aaa + "(" + x6 + ") " + eee + "(" + x7 + ") " + iiii + "(" + x14 + ") " + oooo + "(" + x15 + ") "+ uuuu + "(" + x16 + ") "+ aaaa + "(" + x17 + ") " + eeee + "(" + x18 + ") " + iiiii + "(" + x19 + ") " + ooooo + "(" + x20 + ") "+ uuuuu + "(" + x21 + ") "+ aaaaa + "(" + x22 + ") " + eeeee + "(" + x23 + ") " + iiiiii + "(" + x24 + ") " + oooooo + "(" + x25 + ") "+ uuuuuu + "(" + x26 + ") " + "\n");

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
       MyIO.setCharset("UTF-8");
      String[] endereco = new String[10];
      String[] nomeweb = new String[1000];
      int Nentrada = 0;

      nomeweb[Nentrada] = MyIO.readLine();
      endereco[Nentrada] = MyIO.readLine();

      while (FIM(nomeweb[Nentrada]) == false) {
         Nentrada++;
         nomeweb[Nentrada] = MyIO.readLine();
         if (FIM(nomeweb[Nentrada]) == false) {
            endereco[Nentrada] = MyIO.readLine();
         }
      }

      for (int i = 0; i < Nentrada; i++) {
         getHtml(endereco[i]);

      }
   }
}
