import java.util.*;
import java.io.*;
import java.text.*;


//Classe Filmes
class Filme{
   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
   private String Nome;
   private String Titulo;
   private Date Datadelancamento;
   private int Duracao;
   private String Genero;
   private String Idioma;
   private String Situacao;
   private float Orcamento;
   private String[] Palavrachave;

   //Construtores
   public Filme(){
      Nome = "";
      Titulo = "";
      Duracao = 0;
      Genero = "";
      Idioma = "";
      Situacao = "";
      Orcamento = 0;
      Palavrachave = new String[1000];
   }
   
   public Filme(String Nome,String Titulo,Date Datadelancamento,int Duracao,String Genero,String Idioma,String Situacao,float Orcamento,String[] Palavrachave){
      this.Nome = Nome;
      this.Titulo = Titulo;
      this.Datadelancamento = Datadelancamento;
      this.Duracao = Duracao;
      this.Genero = Genero;
      this.Idioma = Idioma;
      this.Situacao = Situacao;
      this.Orcamento = Orcamento;
      this.Palavrachave = Palavrachave;
   }

   //Metodos sets
   public void setNome(String Nome){this.Nome = Nome;};
   public void setTitulo(String Titulo){this.Titulo = Titulo;};
   public void setDatalancamento(Date Datadelancamento){this.Datadelancamento = Datadelancamento;};
   public void setDuracao(int Duracao){this.Duracao = Duracao;};
   public void setGenero(String Genero){this.Genero = Genero;};
   public void setIdioma(String Idioma){this.Idioma = Idioma;};
   public void setSituacao(String Situacao){this.Situacao = Situacao;};
   public void setOrcamento(float Orcamento){this.Orcamento = Orcamento;};
   public void setPalavrachave(String[] Palavrachave){this.Palavrachave = Palavrachave;};

   //Metodos gets
   public String getNome(){return this.Nome;};
   public String getTitulo(){return this.Titulo;};
   public Date getDatadelancamento(){return this.Datadelancamento;};
   public int getDuracao(){return this.Duracao;};
   public String getGenero(){return this.Genero;};
   public String getIdioma(){return this.Idioma;};
   public String getSituacao(){return this.Situacao;};
   public float getOrcamento(){return this.Orcamento;};
   public String[] getPalavrachave(){return this.Palavrachave;};   
   public String getPalavrachaveString(){
     String palavraschave = "";
     //MyIO.print("[");
     for(String s:this.Palavrachave){
       palavraschave +=  s + "," +" ";
       if(s == null){
         break;
       }
     }
     palavraschave = palavraschave.replaceAll(", null,", "");
    // MyIO.print("]");
     palavraschave.trim();
     return palavraschave.trim();
   }

 
   //Metodo clone
   public Filme clone(){
     Filme filmes = new Filme();
        filmes.setNome(this.Nome);
        filmes.setTitulo(this.Titulo);
        filmes.setDatalancamento(this.Datadelancamento);
        filmes.setDuracao(this.Duracao);
        filmes.setGenero(this.Genero);
        filmes.setIdioma(this.Idioma);
        filmes.setSituacao(this.Situacao);
        filmes.setOrcamento(this.Orcamento);
        filmes.setPalavrachave(this.Palavrachave);
        return filmes;
   }

   public String removeTags(String line){
    String resp = "";
    int i = 0;
    while(i < line.length()){ 
        if(line.charAt(i) == '<'){ 
            i++;
            while(line.charAt(i) != '>') i++;
        } else if(line.charAt(i) == '&'){ 
          i++;
          while(line.charAt(i) != ';') i++;
      } else { 
            resp += line.charAt(i);
        }
        i++;
    }
    return resp;
   }


   public static String pegarIdioma(String limpa){
    limpa = limpa.replaceAll("Idioma original", "");
    return limpa;
   }

   public static String pegarTitulo(String limpa){
    limpa = limpa.replaceAll("Título original", "");
    return limpa;
   }

   public static String pegarSituacao(String limpa){
     limpa = limpa.replaceAll("Situação", "");
     return limpa;
   }

   public static String pegarOrcamento(String limpa){
     limpa = limpa.replaceAll("Orçamento $", "");
     return limpa;
   }

   public static int pegarDuracao(String limpa){
     String line = "";
     line = limpa.trim();
     int duracao = 0;
     
     if(line.contains("h")){
       duracao += Integer.parseInt(line.charAt(0)+"")*60;
       if(line.contains("m")){
         line.trim();
         String minutos = line.substring(3, line.indexOf("m"));
         duracao += Integer.parseInt(minutos);
       }
     }

     else if(line.contains("m")){
       String minutos = line.substring(0, (line.indexOf("m")));
       duracao += Integer.parseInt(minutos);
     }
     return duracao;
   }

   public static String buscarAteParenteses(String filename){
     String line = "";

    for(int i = 0;filename.charAt(i) != '('; i++){
        line += filename.charAt(i);
    }    
     return line;
   }

   private String removeLetters(String value){
      // Data declaration
      String result = "";

      for(int i = 0; i < value.length(); i++){
          // If char is a number, a blank space, or a '.' (Used on convertBudget), will be stored into "result"
          if( (value.charAt(i) >= 48 && value.charAt(i) <= 57) || value.charAt(i) == ' ' || value.charAt(i) == '.')
              result += value.charAt(i);
      }
      return result;
   } 

   private Float convertBudget(String value){
        return Float.parseFloat(removeLetters(value));
     }



   public void lerHtml(String filename) throws ParseException{   
     String line = "";
     String verde = "/tmp/filmes/" + filename;
     String teste =  filename;
     //String teste2 = "/Users/1325905/Documents/TP2/" + filename;

     try{
      FileReader reader = new FileReader(verde);
      BufferedReader buffer = new BufferedReader(reader);
      
          line  = buffer.readLine();
          while(!line.contains("<title>")){
            line = buffer.readLine();
          }

          this.setNome((buscarAteParenteses(removeTags(line))).trim());

          //line = buffer.readLine();
          while(!line.contains("span class=\"release\"")){
            line = buffer.readLine();
          }
          line = buffer.readLine();
          this.setDatalancamento(sdf.parse(removeTags(line).trim()));

          line = buffer.readLine();
          while(!line.contains("span class=\"genres\"")){
            line = buffer.readLine();
          }
          line = buffer.readLine();
          line = buffer.readLine();
          this.setGenero(removeTags(line).trim());

          line = buffer.readLine();
          while(!line.contains("span class=\"runtime\"")){
            line = buffer.readLine();
          }
          line = buffer.readLine();
          line = buffer.readLine();
          this.setDuracao(pegarDuracao((line)));


          line = buffer.readLine();
          while(!line.contains("p class=\"wrap\"") && (!line.contains("<strong><bdi>"))){
            line = buffer.readLine();
          }
          if(line.contains("p class=\"wrap\"")){
            this.setTitulo(removeTags(pegarTitulo(line)).trim());
          }
          if(line.contains("<strong><bdi>")){
            this.Titulo = this.Nome;
          }

          while(!line.contains("<strong><bdi>")){
            line = buffer.readLine();
          }
          this.setSituacao(removeTags(pegarSituacao(line)).trim());    

          line = buffer.readLine();
          while(!line.contains("<p><strong>")){
            line = buffer.readLine();
          }
          this.setIdioma(removeTags(pegarIdioma(line)).trim());

          line = buffer.readLine();
          while(!line.contains("Orçamento</bdi>"));
            String aux = removeTags(line.replace("Orçamento", " ")).trim();
            this.setOrcamento((aux.equals("-")) ? 0.0F : convertBudget(aux));
         
          String[] entrada = new String[30];
          int cont = 0;
          line = buffer.readLine();
          while(!line.contains("</ul>")){
            line = buffer.readLine();
            if(line.contains("<li>")){
              entrada[cont++] = removeTags(line).trim();   
              line.trim();
            }
          }
          cont = cont>0?cont-1:0;
          Palavrachave = new String[cont];
          this.setPalavrachave(entrada);

          buffer.close();
        }catch(FileNotFoundException e){
           System.out.println("Nao encontrei no arquivo");
        }catch(ParseException e){
            e.getLocalizedMessage();
        }catch(IOException e){
            System.out.println("Nao consigo ler o arquivo");
        }
   }

   public String imprimir(){
     return (getNome() + " " + getTitulo()+ " "  + sdf.format(getDatadelancamento())+ " " + getDuracao() + " " + getGenero() + " " + getIdioma() + " " +getSituacao() + " " +getOrcamento() + " [" + getPalavrachaveString() + "]");  
   }

}//fechando a classe Filmes



class Lista{
      private Filme array[];
      private int n;

      public Lista(){
         this(1000);
      }

      public Lista(int tamanho){
        array = new Filme[tamanho];
        n = 0;
      }

      public void inserirFim(Filme filme)throws Exception{
        //System.out.println(n);
         if(n >= array.length){
           throw new Exception("Erro ao inserir");
         }
           array[n] = filme.clone();
           n++;
      }

      public void inserirInicio(Filme filme)throws Exception{
         if(n >= array.length){
           throw new Exception("Erro ao inserir");
         }

         for(int i = n; i > 0; i--){
          array[i] = array[i-1];
         }

   	       array[0] = filme;
           n++;
      }

      public void Inserir(Filme filme,int posicao)throws Exception{
        //System.out.println(array);
         if(n >= array.length || posicao < 0 || posicao > n){
            throw new Exception("Erro ao inserir");
         }
 
         //System.out.println(posicao);
          for(int i = n;i > posicao;i--){
            array[i] = array[i-1].clone();
          } 
            array[posicao] = filme.clone();
            n++;
      }

      public Filme removerFim() throws Exception {

        //validar remocao
        if (n == 0) {
            throw new Exception("Erro ao remover!");
        }

        return array[--n];
      }

      public Filme removerInicio() throws Exception {

        //validar remocao
        if (n == 0) {
            throw new Exception("Erro ao remover!");
        }

        Filme resp = array[0].clone();
        n--;

        for(int i = 0; i < n; i++){
            array[i] = array[i+1].clone();
        }

        return resp;
      }

      public Filme remover(int pos) throws Exception {

        //validar remocao
        if (n == 0 || pos < 0 || pos >= n) {
          throw new Exception("Erro ao remover!");
        }

        Filme resp = array[pos].clone();
        n--;
        for(int i = pos; i < n; i++){
          array[i] = array[i+1].clone();
        }

        return resp;
    }

    public void mostrar(){
      for(int i = 0; i < n; i++){
         System.out.print("[" + i + "] " + array[i].imprimir() + "\n");
      }
   }
}

class QT5{
   public static void main(String[] args)throws Exception{
     MyIO.setCharset("UTF-8");

      String[] leitura = new String[1000];
      //Lista pegando = new Lista();
      int Nentrada = 0,inteiro;
       
      String line = MyIO.readLine();
      while(!line.contains("FIM")){
        leitura[Nentrada++] = line;     
        line = MyIO.readLine();
      }

      Lista pegando = new Lista();
      for(int i = 0 ; i < Nentrada;i++){
        Filme filme = new Filme();
        try{     
          filme.lerHtml(leitura[i]); 
          pegando.inserirFim(filme);
        }catch(NullPointerException e){
          e.printStackTrace();
       }catch(ParseException e){
          e.printStackTrace();
       }catch(Exception e){
          e.printStackTrace();
       }  
      }

     inteiro = MyIO.readInt(); 
     for(int i = 0 ; i < inteiro;i++){
      try{     
        line = MyIO.readLine(); 
        if(line.contains("II")){
            String lendo = line.substring(3, line.length());
            Filme filme = new Filme();
            filme.lerHtml(lendo);            
            pegando.inserirInicio(filme);
            
        }if(line.contains("IF")){
            Filme filme = new Filme();
            filme.lerHtml(line.substring(3, line.length())); 
            pegando.inserirFim(filme);

        }if (line.contains("I*")){
            Filme filme = new Filme();
            String[] split = line.split(" ");
            int posicao = Integer.parseInt(split[1]);
            filme.lerHtml(line.substring(6, line.length())); 
            pegando.Inserir(filme, posicao);

        }if(line.contains("RI")){
            Filme filme = pegando.removerInicio();
            System.out.println("(R) " + filme.getNome());

        }if(line.contains("RF")){
            Filme filme = pegando.removerFim();
            System.out.println("(R) " + filme.getNome());

        }if(line.contains("R*")){
            String[] split = line.split(" ");
            int posicao = Integer.parseInt(split[1]);
            Filme filme = pegando.remover(posicao);
            System.out.println("(R) " + filme.getNome());
        }

      }catch(NullPointerException e){
         e.printStackTrace();
      }catch(ParseException e){
         e.printStackTrace();
      }catch(Exception e){
         e.printStackTrace();
      }
     }
     pegando.mostrar();
   }
}