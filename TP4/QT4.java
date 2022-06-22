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
      FileReader reader = new FileReader(teste);
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
    public String elemento;
    public boolean cor;
    public No esq,dir;

    public No(){
        this(null);
    }

    public No(String elemento){
        this(elemento,false,null,null);
    }

    public No (String elemento,boolean cor){
        this(elemento,cor,null,null);
    }

    public No(String elemento,boolean cor,No esq,No dir){
        this.elemento = elemento;
        this.cor = cor;
        this.dir = dir;
        this.esq = esq;
    }
}

class Alvinegra {
    private No raiz; // Raiz da arvore.
 
    /**
     * Construtor da classe.
     */
    public Alvinegra() {
       raiz = null;
    }
 
    /**
     * Metodo publico iterativo para pesquisar elemento.
     * 
     * @param elemento Elemento que sera procurado.
     * @return <code>true</code> se o elemento existir,
     *         <code>false</code> em caso contrario.
     */
    public boolean pesquisar(String elemento) {
        System.out.println(elemento);
        System.out.print("raiz ");
       return pesquisar(elemento, raiz);
    }
 
    /**
     * Metodo privado recursivo para pesquisar elemento.
     * 
     * @param elemento Elemento que sera procurado.
     * @param i        NoAN em analise.
     * @return <code>true</code> se o elemento existir,
     *         <code>false</code> em caso contrario.
     */
    private boolean pesquisar(String elemento, No i) {
       boolean resp;
       if (i == null) {
          resp = false;
       } else if (elemento.compareTo(i.elemento) == 0) {
          resp = true;
       } else if (elemento.compareTo(i.elemento) < 0) {
          System.out.print("esq ");
          resp = pesquisar(elemento, i.esq);
       } else {
          System.out.print("dir ");
          resp = pesquisar(elemento, i.dir);
       }
       return resp;
    }
 
 
    /**
     * Metodo publico iterativo para inserir elemento.
     * 
     * @param elemento Elemento a ser inserido.
     * @throws Exception Se o elemento existir.
     */
    public void inserir(Filme elemento){
      inserir(elemento.getTitulo());
  }

  private void inserir(String elemento){
      //Se a arvore tiver vazia insere os elementos
      if(raiz == null){
          raiz = new No(elemento);
      }

      //Se a arvore tiver so um elemento vai inserir na esquerda se for menor ou na direita se for maior
      else if(raiz.dir == null && raiz.esq == null){//verifica se os dois sao null pois vai chamar recursivamente apontando para esq ou para dir
          if(elemento.compareTo(raiz.elemento) < 0){// se o elemento a ser inserido for menor que a raiz insere ele a esquerda
              raiz.esq = new No(elemento);
          }else{//caso contrario insere a sua direita
              raiz.dir = new No(elemento);
          }
      }

      //Se a arvore tiver dois elemento a (raiz) e a (esquerda)
      else if(raiz.dir == null){
          if(elemento.compareTo(raiz.elemento) > 0){//se a sua direita tiver vazia eu insiro e se o elemento for maior
              raiz.dir = new No(elemento);
          }else if(elemento.compareTo(raiz.esq.elemento) > 0){//se nao se o elemento for maior que a sua raiz.dir (a direita da raiz j? est? ocupada) 
              raiz.dir = new No(raiz.elemento);
              raiz.elemento = elemento;
          }else{//caso contrario eu insiro na direita pass
              raiz.dir = new No(raiz.elemento);
              raiz.elemento = raiz.esq.elemento;//elemento inserirdo na raiz passa a receber o elemento da esquerda
              raiz.esq.elemento = elemento;//elemento da esquerda da raiz passa a receber o elemento fazendo o pai ficar azuis e os filhos brancos
          }
          raiz.dir.cor = raiz.esq.cor = false;
      }

      //Se a arvore tiver dois elemento a (raiz) e a (direita)
      else if(raiz.esq == null){
          if(elemento.compareTo(raiz.elemento) < 0){//se a sua esquerda tiver vazia eu insiro e se o elemento for maior
              raiz.esq = new No(elemento);
          }else if(elemento.compareTo(raiz.dir.elemento) < 0){//se nao se o elemento for maior que a sua raiz.esq (a esquerda da raiz j? est? ocupada) 
              raiz.esq = new No(raiz.elemento);
              raiz.elemento = elemento;
          }else{//caso contrario eu insiro na direita pass
              raiz.esq = new No(raiz.elemento);
              raiz.elemento = raiz.dir.elemento;//elemento inserirdo na raiz passa a receber o elemento da dir
              raiz.dir.elemento = elemento;//elemento da dir da raiz passa a receber o elemento fazendo o pai ficar azuis e os filhos brancos
          }
          raiz.esq.cor = raiz.dir.cor = false;
      }

      //Senao a arvore tem tres ou mais elementos
      else{
          try {
              inserir(elemento,null,null,null,raiz);
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
      raiz.cor = false;
  }

 
    private void balancear(No bisavo, No avo, No pai, No i) {
       // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
       if (pai.cor == true) {
          // 4 tipos de reequilibrios e acoplamento
          if (pai.elemento.compareTo(avo.elemento) > 0) { // rotacao a esquerda ou direita-esquerda
             if (i.elemento.compareTo(pai.elemento) > 0) {
                avo = rotacaoEsq(avo);
             } else {
                avo = rotacaoDirEsq(avo);
             }
          } else { // rotacao a direita ou esquerda-direita
             if (i.elemento.compareTo(pai.elemento) < 0) {
                avo = rotacaoDir(avo);
             } else {
                avo = rotacaoEsqDir(avo);
             }
          }
          if (bisavo == null) {
             raiz = avo;
          } else if (avo.elemento.compareTo(bisavo.elemento) < 0) {
             bisavo.esq = avo;
          } else {
             bisavo.dir = avo;
          }
          // reestabelecer as cores apos a rotacao
          avo.cor = false;
          avo.esq.cor = avo.dir.cor = true;
          
       } // if(pai.cor == true)
    }
 
    /**
     * Metodo privado recursivo para inserir elemento.
     * 
     * @param elemento Elemento a ser inserido.
     * @param avo      NoAN em analise.
     * @param pai      NoAN em analise.
     * @param i        NoAN em analise.
     * @throws Exception Se o elemento existir.
     */
    private void inserir(String elemento, No bisavo, No avo, No pai, No i) throws Exception {
       if (i == null) {
          if (elemento.compareTo(pai.elemento) < 0) {
             i = pai.esq = new No(elemento, true);
          } else {
             i = pai.dir = new No(elemento, true);
          }
          if (pai.cor == true) {
             balancear(bisavo, avo, pai, i);
          }
       } else {
          // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
          if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
             i.cor = true;
             i.esq.cor = i.dir.cor = false;
             if (i == raiz) {
                i.cor = false;
             } else if (pai.cor == true) {
                balancear(bisavo, avo, pai, i);
             }
          }
          if (elemento.compareTo(i.elemento) < 0) {
             inserir(elemento, avo, pai, i, i.esq);
          } else if (elemento.compareTo(i.elemento) > 0) {
             inserir(elemento, avo, pai, i, i.dir);
          } else {
             throw new Exception("Erro inserir (elemento repetido)!");
          }
       }
    }
 
    private No rotacaoDir(No no) {
       //System.out.println("Rotacao DIR(" + no.elemento + ")");
       No noEsq = no.esq;
       No noEsqDir = noEsq.dir;
 
       noEsq.dir = no;
       no.esq = noEsqDir;
 
       return noEsq;
    }
 
    private No rotacaoEsq(No no) {
       //System.out.println("Rotacao ESQ(" + no.elemento + ")");
       No noDir = no.dir;
       No noDirEsq = noDir.esq;
 
       noDir.esq = no;
       no.dir = noDirEsq;
       return noDir;
    }
 
    private No rotacaoDirEsq(No no) {
       no.dir = rotacaoDir(no.dir);
       return rotacaoEsq(no);
    }
 
    private No rotacaoEsqDir(No no) {
       no.esq = rotacaoEsq(no.esq);
       return rotacaoDir(no);
    }
 }
 
class QT4 {
    public static void main(String[] args) throws Exception{
        //Scanner sc = new Scanner(System.in);
        Alvinegra arvore = new Alvinegra();
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
  /*
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
   */
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
