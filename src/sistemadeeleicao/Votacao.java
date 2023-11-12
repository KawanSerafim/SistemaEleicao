package sistemadeeleicao;
public class Votacao{
    int num, secao, code;
    Votacao(){
        this.num = 0;
        this.secao = 0;
        this.code = 0;
    }
    Votacao(int n, int sc, int c){
        num = n;
        secao = sc;
        code = c;
    }
}