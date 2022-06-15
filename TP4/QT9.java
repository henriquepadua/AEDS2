import java.io.*;
import java.text.*;
import java.util.*;

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
       //String teste = filename;
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
              }
            }
            cont = cont>0?cont-1:0;
            Palavrachave = new String[cont];
            this.setPalavrachave(entrada);
  
         buffer.close();
       }catch(FileNotFoundException e){
        e.printStackTrace();
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
      public No esq,dir;
  
      public No(){
          this(null);
      }
      public No(String elemento){
          this.elemento = elemento;
          this.esq = null;
          this.dir = null;
      }
}

class ArvoreBinaria{
    public No raiz;
    public int contcentral = 0,contpre = 0,contpro;

    public ArvoreBinaria(){
        raiz = null;
    }

    public boolean pesquisar(String elemento){
        return pesquisar(elemento, raiz);
    }

    private boolean pesquisar(String elemento,No i){
        boolean resp = false;
        if(i == null){
            resp = false;
        }else if(elemento.compareTo(i.elemento) == 0 ){
            resp = true;
        }else if(elemento.compareTo(i.elemento) < 0){
            resp = pesquisar(elemento,i.esq);
        }else{
            resp = pesquisar(elemento, i.dir);
        }
            return resp;
    }

    public void inserir(String elemento) throws Exception{
        raiz = inserir(elemento,raiz);
    }

    private No inserir(String elemento,No i)throws Exception{
        if(i == null){
            i = new No(elemento);
        }
        else if(elemento.compareTo(i.elemento) < 0){
            i.esq = inserir(elemento,i.esq);
        }
        else if(elemento.compareTo(i.elemento) > 0){
            i.dir = inserir(elemento,i.dir);
        }
        else{          
            throw new Exception("Tentativa de inserir elmentos iguais");
        }
            return i;
    }

    public void caminharCentral(){
        caminharCentral(raiz);
        System.out.println("");
    }

    private void caminharCentral(No i){
        if(i != null){
            caminharCentral(i.esq);
            System.out.print(i.elemento + " ");
            caminharCentral(i.dir);
        }
    }

    
    public void caminharPre(){
        caminharPre(raiz);
        System.out.println("");
    }

    private void caminharPre(No i){
        if(i != null){
            System.out.print(i.elemento + " ");
            caminharPre(i.esq);
            caminharPre(i.dir);
        }
    }

    
    public void caminharPos(){
        caminharPos(raiz);
        System.out.println("");
    }

    private void caminharPos(No i){
        if(i != null){
            caminharPos(i.esq);
            caminharPos(i.dir);
            System.out.print(i.elemento + " ");
        }
    }
}

class QT9{
    public static void main(String[] args) throws Exception{
        String line = "";
        ArvoreBinaria arvore = new ArvoreBinaria();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        
        while((line = input.readLine())!= null && line.length() > 0){
            if(line.contains("I ")){
                String input1 = line.split(" ")[1];
                arvore.inserir(input1);
            }
            else if(line.contains("INFIXA")){
                arvore.caminharCentral();
            }
            else if(line.contains("PREFIXA")){
                arvore.caminharPre();
            }
            else if(line.contains("POSFIXA")){
                arvore.caminharPos();
            }
            else if(line.contains("P ")){
                String input1 = line.split(" ")[1];
                if(arvore.pesquisar(input1) == true){
                    System.out.println(input1 + " existe");
                }
                else{
                    System.out.println(input1 + " nao existe");
                }
            }
        }
    }
}