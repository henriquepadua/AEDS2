import java.util.*;
import java.io.*;
import java.text.*;

//Classe Filmes
class Filme{
   private String Nome;
   private String Titulo;
   private Date Datadelancamento;
   private int Duracao;
   private String Genero;
   private String Idioma;
   private String Situacao;
   private double Orcamento;
   private String[] Palavrachave;

   //Construtores
   public Filme(){
      Nome = "";
      Titulo = "";
      Duracao = 0;
      Genero = "";
      Idioma = "";
      Situacao = "";
      Orcamento = 0.0;
   }
   
   public Filme(String Nome,String Titulo,Date Datadelancamento,int Duracao,String Genero,String Idioma,String Situacao,double Orcamento,String[] Palavrachave){
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
   public void setOrcamento(double Orcamento){this.Orcamento = Orcamento;};
   public void setPalavrachave(String[] Palavrachave){this.Palavrachave = Palavrachave;};

   //Metodos gets
   public String getNome(){return this.Nome;};
   public String getTitulo(){return this.Titulo;};
   public Date getDatadelancamento(){return this.Datadelancamento;};
   public int getDuracao(){return this.Duracao;};
   public String getGenero(){return this.Genero;};
   public String getIdioma(){return this.Idioma;};
   public String getSituacao(){return this.Situacao;};
   public double getOrcamento(){return this.Orcamento;};
   public String[] getPalavrachave(){return this.Palavrachave;};   
 
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

   public static String removeTags(String filename){
      String line = "";
 
     for(int i = 0 ; i < filename.length(); i++){
         if(filename.charAt(i) == '<'){
           while(filename.charAt(i) != '>');i++;
         }else{
           line += filename.charAt(i);
         }
     }
      return line;
   }

   public static String buscarAteParenteses(String filename){
     String line = "";

    for(int i = 0; i < filename.length(); i++){
        if(filename.charAt(i) != '('){
          i++;
        }else{ 
          line += filename.charAt(i);
        }
      }
     return line;
   }

   public void lerHtml(String filename){   
     String verde = "/tmp/filmes/" + filename;
     String teste = "/Users/User/Documents/TP2/" + filename;

     try{
      FileReader reader = new FileReader(teste);
      MyIO.print(this.Nome);
      BufferedReader buffer = new BufferedReader(reader);
      
      buffer.readLine();
      MyIO.print(Nome);
      while(!buffer.readLine().contains("<title>")){
        MyIO.print(Nome);
        Nome = (removeTags(buscarAteParenteses(buffer.readLine()).trim()));
      }

      while(!buffer.readLine().contains("span class\"release\"")){
         buffer.readLine();
         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
         Datadelancamento = sdf.parse(buscarAteParenteses(buffer.readLine()).trim()); 
      }
 
      /*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      Datadelancamento = sdf.parse(buscarAteParenteses(buffer.readLine()).trim()); */
       
      String[] entrada = new String[30];
      int cont = 0;
      while(!buffer.readLine().contains("</ul>")){
        buffer.readLine();
        if(buffer.readLine().contains("</li>")){
          entrada[cont++] = removeTags(buffer.readLine()).trim();   
         }
       }
       Palavrachave = buscarAteParenteses(buffer.readLine().trim()).split("");

       buffer.close();
     }catch(FileNotFoundException e){
      System.out.println("Nao encontrei no arquivo");
     }catch(ParseException e){
      System.out.println("Erro no parse do arquivo");
     }catch(IOException e){
      System.out.println("Nao consigo ler o arquivo");
     }
   }

   public String imprimir(){
     return (this.Nome + " " + getTitulo()+ " " + getDatadelancamento()+ " " + getDuracao() + " " + getGenero() + " " + getIdioma() + " " +getSituacao() + " " +getOrcamento() + " " + getPalavrachave());  
   }

}//fechando a classe Filmes


//classe Principal
class Main{
  public static void main(String[] args){
     String[] leitura = new String[1000];
     int Nentrada = 0;

     String line = MyIO.readLine();
      while(!line.contains("FIM")){
        leitura[Nentrada++] = line;
        line = MyIO.readLine();
      }
      
     Filme filmes[] = new Filme[Nentrada];
     for(int i = 0 ; i < Nentrada;i++){
       filmes[i] = new Filme();
       filmes[i].lerHtml(leitura[i]);
     }

     for(int i = 0;i < Nentrada;i++){
       MyIO.println(filmes[i].imprimir());
     }
   }
}   


  
