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


class Fila {
  private Filme[] array;
  private int primeiro; // Remove do indice "primeiro".
  private int ultimo,cont; // Insere no indice "ultimo".

  public Fila (){
    this(5);
  }

  public Fila (int tamanho){
    array = new Filme[tamanho+1];
    primeiro = ultimo = cont = 0;
  }


  /**
   * Insere um elemento na ultima posicao da fila.
   * @param x int elemento a ser inserido.
   * @throws Exception Se a fila estiver cheia.
   */
  public void inserir(Filme x) throws Exception {
    
     //validar insercao
     if (((ultimo + 1) % array.length) == primeiro) {
       array[ultimo] = remover();
     }

     array[ultimo] = x;
     ultimo = (ultimo + 1) % array.length;
     cont ++;
     float soma = 0;
     float media = 0;
     for(int i = primeiro;i != ultimo; i=((i+1)%array.length)){//soma até chegar no tamanho do array usando a mesma logica do if para remover
       soma += array[i].getDuracao();
     }
     media = soma/cont;
     int round = Math.round(media);
     System.out.println(round);
  }


  /**
   * Remove um elemento da primeira posicao da fila e movimenta 
   * os demais elementos para o primeiro da mesma.
   * @return resp int elemento a ser removido.
   * @throws Exception Se a fila estiver vazia.
   */
  public Filme remover() throws Exception {

     //validar remocao
     if (primeiro == ultimo) {
        throw new Exception("Erro ao remover!");
     }

     Filme resp = array[primeiro];
     cont --;
     primeiro = (primeiro + 1) % array.length;
     return resp;
  }

  /**
   * Mostra os array separados por espacos.
   */
  public void mostrar (){
     for(int i = primeiro; i != ultimo; i = ((i + 1) % array.length)) {
        System.out.print("[" + i + "] " + array[i].imprimir() + "\n ");
     }
  }

  public void mostrarRec(){
     System.out.print("[ ");
     mostrarRec(primeiro);
     System.out.println("]");
  }

  public void mostrarRec(int i){
     if(i != ultimo){
        System.out.print(array[i] + " ");
        mostrarRec((i + 1) % array.length);
     }
  }

  public boolean isCheia()throws Exception {
    boolean resp = false;
    if (((ultimo ) % array.length) == primeiro) {
      resp = true;
      throw new Exception("Erro ao inserir!");
    }
      return resp;
  }

  
    public boolean cheia (Fila q){
      /*Retorna true se fila cheia, false caso contrário*/
      return (q.primeiro == ((q.ultimo) % array.length));
      /*os dois ponteiros diferem de uma posição*/
    }

    public boolean cheia2 (Fila q){
      /*Retorna true se fila cheia, false caso contrário*/
      return (q.primeiro == ((q.ultimo + 1) % array.length));
      /*os dois ponteiros diferem de uma posição*/
    }
}



class QT7{
   public static void main(String[] args)throws Exception{
     MyIO.setCharset("UTF-8");

      String[] leitura = new String[1000];
      int Nentrada = 0,inteiro;
       
      String line = MyIO.readLine();
      while(!line.contains("FIM")){
        leitura[Nentrada++] = line;     
        line = MyIO.readLine();
      }

      Fila pegando = new Fila();
      for(int i = 0 ; i < Nentrada;i++){
        Filme filme = new Filme();
        try{     
          filme.lerHtml(leitura[i]); 
          pegando.inserir(filme);
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
        if(line.contains("I")){
            Filme filme = new Filme();
            filme.lerHtml(line.substring(2, line.length())); 
            pegando.inserir(filme);
        }else if(line.contains("R")){
            Filme filme = pegando.remover();
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