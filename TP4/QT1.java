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
   public Date getDatadelancamento(){
    if(this.Datadelancamento == null)
       return new Date();     
      return this.Datadelancamento;
    }
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
   public void Clone(){
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
    limpa = limpa.replaceAll("Titulo original", "");
    return limpa;
   }

   public static String pegarSituacao(String limpa){
     limpa = limpa.replaceAll("Situa??o", "");
     return limpa;
   }

   public static String pegarOrcamento(String limpa){
     limpa = limpa.replaceAll("Or?amento $", "");
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
     String teste = filename;
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
          this.setDatalancamento(sdf.parse(buscarAteParenteses(line).trim()));

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
            line = line.trim().substring(49, line.trim().length() - 4).trim();
           // System.out.println("Titulo  da linha " + line);
            this.setTitulo(line);
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
            }
          }
          cont = cont>0?cont-1:0;
          Palavrachave = new String[cont];
          this.setPalavrachave(entrada);

       buffer.close();
     }catch(FileNotFoundException e){
      e.printStackTrace();
     }catch(ParseException e){
      e.getLocalizedMessage();
     }catch(IOException e){
      System.out.println("Nao consigo ler o arquivo");
     }
   }

   public String imprimir(){
     return (getNome() + " " + getTitulo()+ " " + sdf.format(getDatadelancamento())+ " " + getDuracao() + " " + getGenero() + " " + getIdioma() + " " +getSituacao() + " " +getOrcamento() + " [" + getPalavrachaveString() + "]");  
   }

}//fechando a classe Filmes

class No{
    public String elemento2;
    public No esq,dir;

    public No(){
        this(null);
    }
    public No(String elemento2){
        this.elemento2 = elemento2;
        this.esq = null;
        this.dir = null;
    }
}

class ArvoreBinaria{
    public No raiz;

    public ArvoreBinaria(){
        raiz = null;
    }

    public boolean pesquisar(String elemento){
        System.out.println(elemento);
        System.out.print("=>raiz ");
        return pesquisar(elemento, raiz);
    }

    private boolean pesquisar(String elemento,No i){
        boolean resp = false;
        if(i == null){
            resp = false;
        }else if(elemento.compareTo(i.elemento2) == 0 ){
            resp = true;
        }else if(elemento.compareTo(i.elemento2) < 0){
            System.out.print("esq ");
            resp = pesquisar(elemento,i.esq);
        }else if(elemento.compareTo(i.elemento2) > 0){
            System.out.print("dir ");
            resp = pesquisar(elemento, i.dir);
        }
            return resp;
    }

    public void inserir(Filme elemento) throws Exception{
        raiz = inserir(elemento,raiz);
    }

    private No inserir(Filme elemento,No i)throws Exception{
        if(i == null){
            i = new No(elemento.getTitulo());
            return i;
        }
        //System.out.println("->>>Titulo " + elemento.getTitulo());
        if(elemento.getTitulo().compareTo(i.elemento2) < 0){

            i.esq = inserir(elemento,i.esq);
        }
        else if(elemento.getTitulo().compareTo(i.elemento2) > 0){
            i.dir = inserir(elemento,i.dir);
        }
        else{          
            throw new Exception("Erro no seu inserir");
        }
            return i;
    }
    
    public void remover(String elemento)throws Exception{
        raiz = remover(elemento, raiz);
    }

    private No remover(String elemento,No i)throws Exception{
         if(i == null){
          //System.out.println("->" + elemento + "<-");
           //throw new Exception("Arvore vazia");
           return null;
         }
         //System.out.println("elemento " + elemento + " elemento do no " + i.elemento2);
         if(elemento.compareTo(i.elemento2) < 0){
           i.esq = remover(elemento, i.esq);
         }
         else if(elemento.compareTo(i.elemento2) > 0){
          i.dir = remover(elemento, i.dir);
         }
         else if (i.dir == null) {
          i = i.esq;
         }
         else if (i.esq == null) {
          i = i.dir;
         } 
         else{
          i.esq = maiorEsq(i,i.esq);
         }
          return i;
    }

    private No maiorEsq(No i,No j){
      if(j.dir == null){
        i.elemento2 = j.elemento2;
        j = j.esq;
      }
      else{
        j.dir = maiorEsq(i, j.dir);
      }
      return j;
    }
    
    public void caminharCentral() {
      System.out.print("[ ");
      caminharCentral(raiz);
      System.out.println("]");
    }
  
    /**
     * Metodo privado recursivo para exibir elementos.
     * @param i No em analise.
     */
    private void caminharCentral(No i) {
      if (i != null) {
        caminharCentral(i.esq); // Elementos da esquerda.
        System.out.print(i.elemento2 + ", "); // Conteudo do no.
        caminharCentral(i.dir); // Elementos da direita.
      }
    }
}

class QT1{
  public static void main(String[] args) throws Exception{
      Scanner sc = new Scanner(System.in);
      ArvoreBinaria arvore = new ArvoreBinaria();
      String[] leitura = new String[1000];
      int Nentrada = 0,Nentrada2 = 0;

      String line = MyIO.readLine();
      while(!line.contains("FIM")){
          leitura[Nentrada++] = line;
          line = MyIO.readLine();
      }

      for(int i = 0;i < Nentrada ;i++){
        Filme filme = new Filme();
        filme.lerHtml(leitura[i]);
        arvore.inserir(filme);
      }

      int inteiro = MyIO.readInt();
      for(int i = 0;i< inteiro;i++){
          line = MyIO.readLine();
          

          if(line.charAt(0) == 'I'){
            Filme filme = new Filme();  
            line = line.substring(2, line.length());
            filme.lerHtml(line);
            arvore.inserir(filme);
          }

          else if(line.charAt(0) == 'R'){
            line = line.substring(2, line.length());
            //System.out.println("->>" +line);
            arvore.remover(line);
          }
      }

          line = MyIO.readLine();  
          while(!line.contains("FIM")){
            if(arvore.pesquisar(line) == true){
             System.out.println("SIM");
              //line = sc.nextLine();
            }else{
             System.out.println("NAO");
              //line = sc.nextLine();
            }
            line = MyIO.readLine();
        }  
        //arvore.caminharCentral();
}
}