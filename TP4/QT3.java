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

/**
 * Arvore binaria de pesquisa
 * @author Max do Val Machado
 */

class No {
	public String elemento; // Conteudo do no.
	public No esq, dir; // Filhos da esq e dir.
	public int nivel; // Numero de niveis abaixo do no

	/**
	 * Construtor da classe
	 * @param elemento Conteudo do no.
	 */
	public No() {
		this(null);
	}

	/**
	 * Construtor da classe.
	 * @param elemento Conteudo do no.
	 * @param esq      No da esquerda.
	 * @param dir      No da direita.
	 */
	public No(String elemento){
        this.elemento = elemento;
		this.esq = null;
		this.dir = null;
		this.nivel = 1;
	}

	/**
	 * Cálculo do número de níveis a partir de um vértice
	 */
	public void setNivel() {
		this.nivel = 1 + Math.max(getNivel(esq), getNivel(dir));
	}

	/**
	 * Retorna o número de níveis a partir de um vértice
	 * @param no nó que se deseja o nível.
	 */
	public static int getNivel(No no) {
		return (no == null) ? 0 : no.nivel;
	}
}

class AVL {
	private No raiz; // Raiz da arvore.

	/**
	 * Construtor da classe.
	 */
	public AVL() {
		raiz = null;
	}

	public boolean pesquisar(String x) {
		return pesquisar(x, raiz);
	}

	private boolean pesquisar(String x, No i) {
		boolean resp;
		if (i == null) {
			resp = false;
		} else if (x.compareTo(i.elemento) == 0) {
			resp = true;
		} else if (x.compareTo(i.elemento) < 0) {
			resp = pesquisar(x, i.esq);
		} else {
			resp = pesquisar(x, i.dir);
		}
		return resp;
	}


    public void inserir(Filme elemento) throws Exception{
        raiz = inserir(elemento.getTitulo(),raiz);
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
            throw new Exception("Erro no seu inserir");
        }
		return balancear(i);
	}

	public void remover(String x) throws Exception {
		raiz = remover(x, raiz);
	}

	private No remover(String x, No i) throws Exception {
		if (i == null) {
			throw new Exception("Erro ao remover!");
		} else if (x.compareTo(i.elemento) < 0) {
			i.esq = remover(x, i.esq);
		} else if (x.compareTo(i.elemento) > 0) {
			i.dir = remover(x, i.dir);
		// Sem no a direita.
		} else if (i.dir == null) {
			i = i.esq;
		// Sem no a esquerda.
		} else if (i.esq == null) {
			i = i.dir;
		// No a esquerda e no a direita.
		} else {
			i.esq = maiorEsq(i, i.esq);
		}
		return balancear(i);
	}

	private No maiorEsq(No i, No j) {
		// Encontrou o maximo da subarvore esquerda.
		if (j.dir == null) {
			i.elemento = j.elemento; // Substitui i por j.
			j = j.esq; // Substitui j por j.ESQ.
		// Existe no a direita.
		} else {
			// Caminha para direita.
			j.dir = maiorEsq(i, j.dir);
		}
		return j;
	}

	private No balancear(No no) throws Exception {
		if (no != null) {
			int fator = No.getNivel(no.dir) - No.getNivel(no.esq);
			// Se balanceada
			if (Math.abs(fator) <= 1) {
				no.setNivel();
			// Se desbalanceada para a direita
			} else if (fator == 2) {
				int fatorFilhoDir = No.getNivel(no.dir.dir) - No.getNivel(no.dir.esq);
				// Se o filho a direita tambem estiver desbalanceado
				if (fatorFilhoDir == -1) {
					no.dir = rotacionarDir(no.dir);
				}
				no = rotacionarEsq(no);
			// Se desbalanceada para a esquerda
			} else if (fator == -2) {
				int fatorFilhoEsq = No.getNivel(no.esq.dir) - No.getNivel(no.esq.esq);
				// Se o filho a esquerda tambem estiver desbalanceado
				if (fatorFilhoEsq == 1) {
					no.esq = rotacionarEsq(no.esq);
				}
				no = rotacionarDir(no);
			} else {
				throw new Exception(
						"Erro no No(" + no.elemento + ") com fator de balanceamento (" + fator + ") invalido!");
			}
		}
		return no;
	}

	private No rotacionarDir(No no) {
//		System.out.println("Rotacionar DIR(" + no.elemento + ")");
		No noEsq = no.esq;
		No noEsqDir = noEsq.dir;

		noEsq.dir = no;
		no.esq = noEsqDir;
		no.setNivel(); // Atualizar o nivel do no
		noEsq.setNivel(); // Atualizar o nivel do noEsq

		return noEsq;
	}

	private No rotacionarEsq(No no) {
//		System.out.println("Rotacionar ESQ(" + no.elemento + ")");
		No noDir = no.dir;
		No noDirEsq = noDir.esq;

		noDir.esq = no;
		no.dir = noDirEsq;

		no.setNivel(); // Atualizar o nivel do no
		noDir.setNivel(); // Atualizar o nivel do noDir
		return noDir;
	}
}


class QT3{
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        AVL arvore1 = new AVL();
        String[] leitura = new String[1000];
        int Nentrada = 0,Nentrada2 = 0;

        String line = sc.nextLine();
        while(!line.contains("FIM")){
            leitura[Nentrada++] = line;
            line = sc.nextLine();
        }

        int inteiro = sc.nextInt();
        for(int i = 0;i< inteiro;i++){
          try{
            line = sc.nextLine();
            if(line.contains("I")){
              Filme filme = new Filme();
              filme.lerHtml(line.substring(2,line.length()));
              arvore1.inserir(filme);
            }
            else if(line.contains("R")){
              line = line.substring(2, line.length());
              arvore1.remover(line);
            }
          }catch(NullPointerException e){
            e.printStackTrace();
         }catch(ParseException e){
            e.printStackTrace();
         }catch(Exception e){
            e.printStackTrace();
         }
        }

        line = sc.nextLine(); 
        while(!line.contains("FIM")){
          if(arvore1.pesquisar(line) == true){
            System.out.println("SIM");
          }else{
            System.out.println("NAO");
          }
          line = sc.nextLine();
        }
  }
}