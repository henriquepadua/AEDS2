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
    limpa = limpa.replaceAll("T?tulo original", "");
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
          while(!line.contains("Or�amento</bdi>"));
            String aux = removeTags(line.replace("Or�amento", " ")).trim();
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
    public No esq,dir;
    public char letra;
    public No2 tmp;

    public No(){
        esq = dir = null;
    }

    public No(char letra){
        this.letra = letra;
        esq = dir = null;
        tmp = null;
    }
}

class No2{
    public Filme elemento;
    public No2 esq,dir;

    public No2(){
        esq = dir;
    }

    public No2(Filme elemento){
        this.elemento = elemento;
        esq = dir = null;
    }
}

class AA{
    public No raiz;

    public AA(){
        raiz = null;
        char[] letras = {'D','R','Z','X','V','B','F',
                         'P','U','I','G','E','J','L',
                         'H','T','A','W','S','O','M',
                         'N','K','C','Y','Q'
                        };

                       for(char letra : letras){
                        inserir(letra);
                       }
    }

    public void inserir(char letra){
       raiz = inserir(letra,raiz);
    }

    private No inserir(char letra,No i){
        if(i == null){
            i = new No(letra);
        }

        else if(letra < raiz.letra){
            i.esq = inserir(letra, i.esq);
        }

        else if(letra > raiz.letra){
            i.dir = inserir(letra, i.dir);
        }

            return i;
    }

    public void inserir(Filme elemento){
        inserir(elemento,raiz);
    }

    private void inserir(Filme elemento,No raiz){
        if(raiz == null){
            raiz = new No(elemento.getTitulo().toUpperCase().charAt(0));
        }

        else if(elemento.getTitulo().toUpperCase().charAt(0) < (raiz.letra)){
            inserir(elemento,raiz.esq);
        }

        else if(elemento.getTitulo().toUpperCase().charAt(0) > (raiz.letra)){
            inserir(elemento,raiz.dir);
        }

        else{
            raiz.tmp = inserir(elemento, raiz.tmp);
        }
    }

    private No2 inserir(Filme elemento,No2 raiz){
        if(raiz == null){

        }

        else if(elemento.getTitulo().compareTo(raiz.elemento.getTitulo()) < 0){
            raiz.esq = inserir(elemento, raiz.esq);
        }

        else if(elemento.getTitulo().compareTo(raiz.elemento.getTitulo()) > 0){
            raiz.dir = inserir(elemento, raiz.dir);
        }

        return raiz;
    }

    public boolean Pesquisar(String s){
        System.out.println("=> " + s);
        System.out.print("raiz ");
        return Pesquisar(s,raiz);
    }

    private boolean Pesquisar(String s,No raiz){
        boolean resp = false;

        if(raiz == null){
            resp = false;
        }

        else if(Pesquisar(raiz.tmp,s) == true){
            resp = true;
        }

        else {
            System.out.print("esq ");
            resp = Pesquisar(s,raiz.esq);
            if(resp == false){
                System.out.print("dir ");
                resp = Pesquisar(s,raiz.dir);
            }    
        }

        return resp;
    }

    private boolean Pesquisar(No2 raiz,String s){
        boolean resp = false;
        if(raiz == null){
            resp = false;
        }
        
        else if(s.compareTo(raiz.elemento.getTitulo()) == 0){
          //System.out.println("dsfsd");
            resp = true;
        }

        else{
            System.out.print("ESQ ");
            resp = Pesquisar(raiz.esq, s);
            if(resp == false){
                System.out.print("DIR ");
                resp = Pesquisar(raiz.dir, s);
            }
        }

        return resp;
    }

    public void mostrar(){
        mostrar(raiz);
    }

    public void mostrar(No i){
        if(i != null){
            mostrar(i.esq);
            mostrar(i.dir);
        }
    }

    public void mostrar(No2 i){
        if(i != null){
            mostrar(i.esq);
            mostrar(i.dir);
        }
    }
}

class QT2{
  public static void main(String[] args) throws Exception{
    Scanner sc = new Scanner(System.in);
    AA arvore = new AA();
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
       
    }

        line = MyIO.readLine();  
        while(!line.contains("FIM")){
          if(arvore.Pesquisar(line) == true){
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