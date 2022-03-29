import java.util.*;
import java.io.*;

//Classe Filmes
class Filmes {
  SimpleDateFormat sdf =   SimpleDateFormat("dd/MM/yyyy/");
  private String Nome;
  private String Titulo;
  private Date Datadelancamento;
  private int Duracao;
  private String Genero;
  private String Idioma;
  private String Situacao;
  private double Orcamento;
  private String[] Palavrachave;

  // Construtores
  public Filmes() {
    Nome = "";
    Titulo = "";
    Datadelancamento = new Date();
    Duracao = 0;
    Genero = "";
    Idioma = "";
    Situacao = "";
    Orcamento = 0.0;
    Palavrachave = "";
  }

  public Filmes(String Nome, String Titulo, Date Datadelancamento, int Duracao, String Genero, String Idioma,
      String Situacao, double Orcamento, String[] Palavrachave) {
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

  // Metodos sets
  public void setNome(String Nome) {
    this.Nome = Nome;
  }

  public void setTitulo(String Titulo) {
    this.Titulo = Titulo;
  }

  public void setDatadelancamento(Date Datalancamento) {
    this.Datadelancamento = Datalancamento;
  }

  public void setDuracao(int Duracao) {
    this.Duracao = Duracao;
  }

  public void setGenero(String Genero) {
    this.Genero = Genero;
  }

  public void setIdioma(String Idioma) {
    this.Idioma = Idioma;
  }

  public void setSituacao(String Situacao) {
    this.Situacao = Situacao;
  }

  public void setOrcamento(double Orcamento) {
    this.Orcamento = Orcamento;
  }

  public void setPalavrachave(String[] Palavrachave) {
    this.Palavrachave = Palavrachave;
  }

  // Metodos gets
  public String getNome() {
    return this.Nome;
  }

  public String getTitulo() {
    return this.Titulo;
  }

  public Date getDatadelancamento() {
    return this.Datadelancamento;
  }

  public int getDuracao() {
    return this.Duracao;
  }

  public String getGenero() {
    return this.Genero;
  }

  public String getIdioma() {
    return this.Idioma;
  }

  public String getSituacao() {
    return this.Situacao;
  }

  public double getOrcamento() {
    return this.Orcamento;
  }

  public String[] getPalavrachave() {
    return this.Palavrachave;
  }

  // Metodo clone
  public Filmes Clone() {
    Filmes filmes = new Filmes();
    filmes.setNome(Nome);
    filmes.setTitulo("");
    filmes.setDatadelancamento(filmes.getDatadelancamento());
    filmes.setDuracao(0);
    filmes.setGenero("");
    filmes.setIdioma("");
    filmes.setSituacao("");
    filmes.setOrcamento(0.0);
    filmes.setPalavrachave(filmes.getPalavrachave());

    return filmes;
  }

  // metodo imprimir
  public String imprimir() {
    
    return getNome()+" "+getDuracao()+" "+getTitulo()+" "+getDatadelancamento()+" "+getDuracao()+" "+getIdioma()+" "+getSituacao()+" "+getOrcamento()+" "+getPalavrachave()+" ";
    
  }

  //metodo vai remover as Tags
  public static String removeTags(String string) {
      String limpa = "";
     for(int i = 0;i < string.length();i++){
       if(string.charAt(i) == '<'){
          while(string.charAt(i) != '>')i++;
       }else{ 
          limpa += string.charAt(i);
       }
     }
        return limpa;
  }

  public String buscaAteParenteses(String string){
    String limpa = "";
    for(int i = 0;string.charAt(i) != '(',i++){
     limpa += string.charAt(i);
    }
     return limpa;
  }

  //metodo de leitura da html
  public void lerHtml(String leitura) {
    FileReader reader;
    BufferedReader buffer;
    String myhtml = "/tmp/filmes/" + leitura;    //verde
    
    try{
      reader = new FileReader(leitura);
      buffer = new BufferedReader(reader);
      String line = "";
      
            while ((line = buffer.readLine()) != null) {
              for (int i = 0; i < line.length(); i++) {

                if(!line.contains("<title>")){
                  linha = buffer.readLine();
                }
                  set.Nome = (buscaAteParenteses(removeTags(linha)).trim());
                
                if(!line.contains("span class\"release\"")){
                    linha = buffer.readLine();
                }
                  
                line = buffer.readLine();

                setDatadelancamento = (buscaAteParentese(line).trim());

                if(!line.contains("Palavras-chave")){
                  line = buffer.readLine();
                }

                String[] tmp = new String[30];
                int cont = 0;               
                if(!line.contains("</ul>")){ 
                   line = buffer.readLine();
                   if (linha.contains("</li>")){
                    tmp[cont++] = removeTags(linha).trim();
                   }
                }
                  palavrasChave = new String[contador];
              } 
            }
          buffer.close();
          reader.close();
       }catch (IOException ioe) {
         ioe.printStackTrace();
       }
        catch (NullPointerException ioe) {
         ioe.printStackTrace();
       }
  }
}// fechando a classe Filmes


// classe Principal
class Main {

  public static boolean FIM(String h) {
    return (h.length() == 3 && h.charAt(0) == 'F' && h.charAt(1) == 'I' && h.charAt(2) == 'M');
  }

  public static void main(String[] args) {
    String[] madrugada = new String[1000];
    Filmes[] pegando = new Filmes[100];
    int cont = 0;
    int cont2 = 0;


    madrugada[cont] = MyIO.readLine();
    while (FIM(madrugada[cont]) == false) {
      cont++;
      cont2++;
      pegando[cont2].lerHtml(madrugada[cont]);
      pegando[cont2].imprimir();
      madrugada[cont] = MyIO.readLine();
    }

/*

while(!linha.contains("FIM")){
entrada[contador++] = linha;
linha = MyIO.readLine();

Filme filmes[] = new Filme[cont];
for(int i = 0;i< contador; i++){ 
filmes[i] = new Filme();
filmes[i].ler[entrada[i]);
}
for(int i = 0;i < cont;i++){
MyIO(filmes[i].Imprimir());
}

}

*/
  }
}