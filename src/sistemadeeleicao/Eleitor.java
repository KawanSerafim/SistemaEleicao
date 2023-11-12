package sistemadeeleicao;
public class Eleitor {
    int num, secao;
    String nome;
    Eleitor(){
        this.nome = "";
        this.num = 0;
        this.secao = 0;
    }
    Eleitor(String nm, int n, int sc){
        nome = nm;
        num = n;
        secao = sc;
    }
}