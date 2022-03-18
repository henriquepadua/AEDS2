import java.util.*;
import java.io.*;

//Classe Filmes
class Filmes{
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
   public Filmes(){
      this.Nome = "";
      this.Titulo = "";
      this.Datalancamento = new Date();
      this.Duracao = 0;
      this.Genero = "";
      this.Idioma = "";
      this.Situacao = "";
      this.Orcamento = 0.0;
      this.Palavrachave = new String();
   }
   
   public Filmes(String Nome,String Titulo,Date Datadelancamento,int Duracao,String Genero,String Idioma,String Situacao,float Orcamento,String[] Palavrachave){
      this.Nome = Nome;
      this.Titulo = Titulo;
      this.Datalancamento = Datalancamento;
      this.Duracao = Duracao;
      this.Genero = Genero;
      this.Idioma = Idioma;
      this.Situacao = Situacao;
      this.Orcamento = Orcamento;
      this.Palavrachave = Palavrachave;
   }

   //Metodos sets
   public void setNome(String Nome){this.Nome = Nome};
   public void setTitulo(String Titulo){this.Titulo = Titulo};
   public void setDatalancamento(Date Datalancamento){this.Datalancamento = Datalancamento};
   public void setDuracao(int Duracao){this.Duracao = Duracao};
   public void setGenero(String Genero){this.Genero = Genero};
   public void setIdioma(String Idioma){this.Idioma = Idioma};
   public void setSituacao(String Situacao){this.Situacao = Situacao};
   public void setOrcamento(float Orcamento){this.Orcamento = Orcamento};
   public void setPalavrachave(String[] Palavrachave){this.Palavrachave = Palavrachave};

   //Metodos gets
   public String getNome(){return this.Nome};
   public String getTitulo(){this.Titulo};
   public String getDatalancamento(){this.Datalancamento};
   public int getDuracao(){this.Duracao};
   public String getGenero(){this.Genero};
   public String getIdioma(){this.Idioma};
   public String getSituacao(){this.Situacao};
   public float getOrcamento(){this.Orcamento};
   public String[] getPalavrachave(){this.Palavrachave};   
 
   //Metodo clone
   public void Clone(){
     Filmes filmes = new Filmes();
        filmes.SetNome("");
        filmes.setTitulo("");
        filmes.setDatalancamento(new Date());
        filmes.setDuracao(0);
        filmes.setGenero("");
        filmes.setIdioma("");
        filmes.setSituacao("");
        filmes.setOrcamento(0.0);
        filmes.setPalavrachave(new String());
   }

   //metodo imprimir
   public void imprimir(){
     Filmes imprimindo = new Filmes();
        System.out.println(imprimindo.getNome() + "nome " + imprimindo.getTitulo() + "tituloOriginal " + imprimindo.getDatalancamento() + "dataLancamento " + imprimindo.getDuracao() + "duracao " + imprimindo.getGenero() + "genero " + imprimindo.getIdioma() + "Idioma " + imprimindo.getSitucao() + "situacao "  imprimindo.getOrcamento() + "orcamento " + imprimindo.getPalavrachave() + "[palavrasChave] ");
   }

   //metodo ler
   public void lerHtml(){
     
   }

}//fechando a classe Filmes


//classe Principal
class Main{
  public static void main(String[] args){
     Filmes pegando = new Filmes();
     pegando.imprimir();

  }   

}
  
}