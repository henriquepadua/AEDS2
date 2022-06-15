import java.util.Scanner;

class No{
    public int elemento;
    public No esq,dir;

    public No(){
        this(0);
    }
    public No(int elemento){
        this.elemento = elemento;
        this.esq = null;
        this.dir = null;
    }
}

class ArvoreBinaria{
  public No raiz;

  public ArvoreBinaria(){
      raiz = null;
  }

  public void inserir(int elemento) throws Exception{
      raiz = inserir(elemento,raiz);
  }

  private No inserir(int elemento,No i)throws Exception{
      if(i == null){
          i = new No(elemento);
      }
      else if(elemento < i.elemento){
          i.esq = inserir(elemento,i.esq);
      }
      else if(elemento > i.elemento){
          i.dir = inserir(elemento,i.dir);
      }
      else{          
          throw new Exception("Tentativa de inserir elmentos iguais");
      }
          return i;
  }

  public void caminharCentral(){
      caminharCentral(raiz);
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
  }

  private void caminharPos(No i){
      if(i != null){
          caminharPos(i.esq);
          caminharPos(i.dir);
          System.out.print(i.elemento + " ");
      }
  }

  public boolean pesquisar(int elemento){
    return pesquisar(elemento, raiz);
}

private boolean pesquisar(int elemento,No i){
    boolean resp = false;
    if(i == null){
        resp = false;
    }else if(i.elemento == elemento ){
        resp = true;
    }else if(i.elemento < elemento){
        resp = pesquisar(elemento,i.esq);
    }else if(i.elemento > elemento){
        resp = pesquisar(elemento, i.dir);
    }
        return resp;
}
}

class QT8{
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        ArvoreBinaria arvore = new ArvoreBinaria();
        int nCases = 0,nControle = 1,nInsercao = 0,numInserido = 0,cont = 0;

        nCases = sc.nextInt();
        while(nControle <= nCases){
            System.out.println("Case " + nControle + ":");
            nInsercao = sc.nextInt();
            arvore = new ArvoreBinaria();
            for(int i = 0;i < nInsercao;i++){
                numInserido = sc.nextInt();
                if(arvore.pesquisar(numInserido) == false){
                    arvore.inserir(numInserido);
                }
            }
            System.out.print("Pre.: "  );
            arvore.caminharPre();
            System.out.print("\n");
            System.out.print("In..: "  );
            arvore.caminharCentral();
            System.out.print("\n");
            System.out.print("Post: "  );
            arvore.caminharPos();
            System.out.print("\n");
            System.out.print("\n");
            nControle++;
        }
    }
}