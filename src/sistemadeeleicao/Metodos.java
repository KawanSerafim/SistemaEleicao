package sistemadeeleicao;
import javax.swing.*;
import java.io.*;
public class Metodos{
    public Eleitor[] cad_el(Eleitor[] eleitor) throws IOException{
        // Loop responsável por "limpar" os espaços do vetor dos objetos de Eleitor. Conforme abaixo:
        for(int i=0;i<10;i++){
            eleitor[i] = new Eleitor();
        }

        /*
         * Criação de um arquivo .txt para gravação dos dados de eleitores.
         * - É usado um Buffer de escrita "BufferedWriter" para que seja possível a gravação dos dados.
         * Assim conforme abaixo:
         */
        String filename = "cadastro_eleitor.txt";
        BufferedWriter gravar = new BufferedWriter(new FileWriter(filename));

        /* 
         * Agora, o loop "for" permite com que cada espaço do vetor seja preenchido pelo
         * usuário, e não só isso, como também já é gravado os dados no "cadastro_eleitor.txt".
         * 
         * Assim conforme abaixo:
         */
        for(int i=0;i<10;i++){
            eleitor[i].nome = JOptionPane.showInputDialog("Digite o nome do Eleitor.");
            eleitor[i].num = Integer.parseInt(JOptionPane.showInputDialog("Digite o número do Eleitor."));
            eleitor[i].secao = Integer.parseInt(JOptionPane.showInputDialog("Digite a seção do Eleitor."));
            if(i!=0){
                gravar.newLine();
            }
            gravar.write(eleitor[i].nome);
            gravar.newLine();
            gravar.write(Integer.toString(eleitor[i].num));
            gravar.newLine();
            gravar.write(Integer.toString(eleitor[i].secao));
        }
        gravar.close();
        return eleitor;
    }

    /* 
     * Quando o usuário escolhe o menu de votação, esse módulo é chamado e exibe mais um menu
     * tanto para escolher a votação 1 quanto a votação 2.
     * 
     * Assim conforme abaixo:
     */
    public void menu_vt(Eleitor[] eleitor, Votacao[] votacao1, Votacao[] votacao2, Votacao[] apuracao)throws IOException{
        int opc = 0;
        while(opc!=9){
            opc = Integer.parseInt(JOptionPane.showInputDialog("MENU DE VOTAÇÃO\n\n1 - Votacão 1.\n2 - Votação 2.\n9 - Sair."));
            switch (opc){
                case 1:
                    cad_vt1(votacao1, eleitor);
                    break;
                case 2:
                    cad_vt2(votacao2, eleitor);
                    break;
                case 9:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
            }
        }
    }

    public Votacao[] cad_vt1(Votacao[] votacao1, Eleitor[] eleitor)throws IOException{
        // Loop responsável por "limpar" os espaços do vetor dos objetos de Votacao e Eleitor. Conforme abaixo:
        for(int i=0;i<10;i++){
            votacao1[i] = new Votacao();
            eleitor[i] = new Eleitor();
        }

        /*
         * Criação de um arquivo .txt para gravação dos dados de eleitores e leitura do arquivo .txt dos eleitores.
         * - É usado um Buffer de escrita "BufferedWriter" para que seja possível a gravação dos dados.
         * - É usado um Buffer de leitura "BufferedReader" para que seja possível a leitura dos dados.
         * Assim conforme abaixo:
         */
        String filename1 = "cadastro_votacao1.txt";
        String filename2 = "cadastro_eleitor.txt";
        BufferedWriter gravar = new BufferedWriter(new FileWriter(filename1));
        BufferedReader ler = new BufferedReader(new FileReader(filename2));
        int sit, sit2, lim=0, opc, aux;
        int copy[] = new int[10];

        /*
         * Abaixo, está o loop "for" responsável por realizar a leitura dos dados de eleitores e estabelecer a quantia de
         * dos eleitores da votação 1 de acordo com suas seções e os seus determinados números de 
         * eleitor.
         */
        for(int i=0;i<10;i++){
            eleitor[i].nome = ler.readLine();
            eleitor[i].num = Integer.parseInt(ler.readLine());
            eleitor[i].secao = Integer.parseInt(ler.readLine());
            switch (eleitor[i].secao){
                case 1:
                    copy[i] = eleitor[i].num;
                    lim++;
                    break;
                case 3:
                    copy[i] = eleitor[i].num;
                    lim++;
                    break;
                case 4:
                    copy[i] = eleitor[i].num;
                    lim++;
                    break;
            }
        }

        /* 
         * Abaixo, o primeiro loop "for" realiza o preenchimento de todos os campos de votação, o loop "while" faz
         * o papel de bloquear dados errados inseridos pelo usuário até que estejam certos, tanto para determinar se
         * o número de eleitor digitado está ou não na votação 1 (caso não esteja, vai analisar se o número existe ou
         * não no cadastro de eleitor), quanto para analisar se a seção digitada é condizente com o número. É claro que
         * é um pouco mais complexo que isso, mas explicar passo a passo deixaria o comentário muito grande.
         */
        for(int i=0;i<lim;i++){
            sit = 0;
            sit2 = 0;
            opc = 0;
            while(sit==0){
                votacao1[i].num = Integer.parseInt(JOptionPane.showInputDialog("Digite o número do eleitor."));
                for(int j = 0; j < 10; j++){
                    if(copy[j] == votacao1[i].num){
                        while(sit2==0){
                            votacao1[i].secao = Integer.parseInt(JOptionPane.showInputDialog("Digite a seção do eleitor."));
                            if(votacao1[i].secao == eleitor[j].secao){
                                while(opc==0){
                                    votacao1[i].code = Integer.parseInt(JOptionPane.showInputDialog("VOTAÇÃO\n\n1 - Jose.\n2 - Maria.\n3 - Branco.\n4 - Nulo."));
                                    switch(votacao1[i].code){
                                        case 1:
                                            opc = 1;
                                            break;
                                        case 2:
                                            opc = 1;
                                            break;
                                        case 3:
                                            opc = 1;
                                            break;
                                        case 4:
                                            opc = 1;
                                            break;
                                        default:
                                            JOptionPane.showMessageDialog(null, "Opção inválida.");
                                            opc = 0;
                                    }
                                }
                                sit2 = 1;
                                break;
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Seção do eleitor não condiz com o número de eleitor. Digite novamente.");
                            }
                        }
                        sit = 1;
                        break;
                    }   
                    else{
                        if(j==9){
                            for(int k=0;k<10;k++){
                                if(eleitor[k].num == votacao1[i].num){
                                    JOptionPane.showMessageDialog(null, "Número de eleitor não cadastrado para votação 1. Digite novamente.");
                                    break;
                                }
                                else{
                                    if(k==9){
                                        JOptionPane.showMessageDialog(null, "Número de eleitor não existe. Digite novamente.");
                                    }
                                }
                            }
                        }
                    }
                }
            }        
        }

        /*
         * Esses dois loops "for" servem para reorganizar os dados por código de candidato, seguindo
         * do menor para o maior.
         * 
         * - O "for" de índice "i" servirá para determinar até quando essa reorganização seguirá.
         * - O "for" de índice "j" servirá para puxar o valor de 1 índice a frente do "i", que
         * através de um "if", determina se ele será maior ou menor que o índice "i". Caso seja
         * menor, os valores são trocados.
         * 
         * Enquanto o processo anda, os dados são gravados com a sequência original alterada.
         */
        for(int i=0;i<lim;i++){
            for(int j=i+1;j<lim;j++){
                if(votacao1[i].code > votacao1[j].code){
                    aux = votacao1[j].num;
                    votacao1[j].num = votacao1[i].num;
                    votacao1[i].num = aux;
        
                    aux = votacao1[j].secao;
                    votacao1[j].secao = votacao1[i].secao;
                    votacao1[i].secao = aux;

                    aux = votacao1[j].code;
                    votacao1[j].code = votacao1[i].code;
                    votacao1[i].code = aux;
                }
            }
            if(i!=0){
                gravar.newLine();
            }
            gravar.write(Integer.toString(votacao1[i].num));
            gravar.newLine();
            gravar.write(Integer.toString(votacao1[i].secao));
            gravar.newLine();
            gravar.write(Integer.toString(votacao1[i].code));
        }
        gravar.close();
        ler.close();
        return votacao1;
    }
    public Votacao[] cad_vt2(Votacao[] votacao2, Eleitor[] eleitor)throws IOException{
        // Loop responsável por "limpar" os espaços do vetor dos objetos de Votacao e Eleitor. Conforme abaixo:
        for(int i=0;i<10;i++){
            votacao2[i] = new Votacao();
            eleitor[i] = new Eleitor();
        }

        /*
         * Criação de um arquivo .txt para gravação dos dados de eleitores e leitura do arquivo .txt dos eleitores.
         * - É usado um Buffer de escrita "BufferedWriter" para que seja possível a gravação dos dados.
         * - É usado um Buffer de leitura "BufferedReader" para que seja possível a leitura dos dados.
         * Assim conforme abaixo:
         */
        String filename1 = "cadastro_votacao2.txt";
        String filename2 = "cadastro_eleitor.txt";
        BufferedWriter gravar = new BufferedWriter(new FileWriter(filename1));
        BufferedReader ler = new BufferedReader(new FileReader(filename2));
        int sit, sit2, lim=0, opc, aux;
        int copy[] = new int[10];

        /*
         * Abaixo, está o loop "for" responsável por realizar a leitura dos dados de eleitores e estabelecer a quantia de
         * dos eleitores da votação 2 de acordo com suas seções e os seus determinados números de 
         * eleitor.
         */
        for(int i=0;i<10;i++){
            eleitor[i].nome = ler.readLine();
            eleitor[i].num = Integer.parseInt(ler.readLine());
            eleitor[i].secao = Integer.parseInt(ler.readLine());
            switch (eleitor[i].secao){
                case 5:
                    copy[i] = eleitor[i].num;
                    lim++;
                    break;
                case 9:
                    copy[i] = eleitor[i].num;
                    lim++;
                    break;
                case 10:
                    copy[i] = eleitor[i].num;
                    lim++;
                    break;
            }
        }

        /* 
         * Abaixo, o primeiro loop "for" realiza o preenchimento de todos os campos de votação, o loop "while" faz
         * o papel de bloquear dados errados inseridos pelo usuário até que estejam certos, tanto para determinar se
         * o número de eleitor digitado está ou não na votação 2 (caso não esteja, vai analisar se o número existe ou
         * não no cadastro de eleitor), quanto para analisar se a seção digitada é condizente com o número. É claro que
         * é um pouco mais complexo que isso, mas explicar passo a passo deixaria o comentário muito grande.
         */
        for(int i=0;i<lim;i++){
            sit = 0;
            sit2 = 0;
            opc = 0;
            if(i!=0){
            }
            while(sit==0){
                votacao2[i].num = Integer.parseInt(JOptionPane.showInputDialog("Digite o número do eleitor."));
                for(int j = 0; j < 10; j++){
                    if(copy[j] == votacao2[i].num){
                        while(sit2==0){
                            votacao2[i].secao = Integer.parseInt(JOptionPane.showInputDialog("Digite a seção do eleitor."));
                            if(votacao2[i].secao == eleitor[j].secao){
                                while(opc==0){
                                    votacao2[i].code = Integer.parseInt(JOptionPane.showInputDialog("VOTAÇÃO\n\n1 - Jose.\n2 - Maria.\n3 - Branco.\n4 - Nulo."));
                                    switch(votacao2[i].code){
                                        case 1:
                                            opc = 1;
                                            break;
                                        case 2:
                                            opc = 1;
                                            break;
                                        case 3:
                                            opc = 1;
                                            break;
                                        case 4:
                                            opc = 1;
                                            break;
                                        default:
                                            JOptionPane.showMessageDialog(null, "Opção inválida.");
                                            opc = 0;
                                    }
                                }
                                sit2 = 1;
                                break;
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Seção do eleitor não condiz com o número de eleitor. Digite novamente.");
                            }
                        }
                        sit = 1;
                        break;
                    }   
                    else{
                        if(j==9){
                            for(int k=0;k<10;k++){
                                if(eleitor[k].num == votacao2[i].num){
                                    JOptionPane.showMessageDialog(null, "Número de eleitor não cadastrado para votação 2. Digite novamente.");
                                    break;
                                }
                                else{
                                    if(k==9){
                                        JOptionPane.showMessageDialog(null, "Número de eleitor não existe. Digite novamente.");
                                    }
                                }
                            }
                        }
                    }
                }
            }        
        }

        /*
         * Esses dois loops "for" servem para reorganizar os dados por código de candidato, seguindo
         * do menor para o maior.
         * 
         * - O "for" de índice "i" servirá para determinar até quando essa reorganização seguirá.
         * - O "for" de índice "j" servirá para puxar o valor de 1 índice a frente do "i", que
         * através de um "if", determina se ele será maior ou menor que o índice "i". Caso seja
         * menor, os valores são trocados.
         * 
         * Enquanto o processo anda, os dados são gravados com a sequência original alterada.
         */
        for(int i=0;i<lim;i++){
            for(int j=i+1;j<lim;j++){
                if(votacao2[i].code > votacao2[j].code){
                    aux = votacao2[j].num;
                    votacao2[j].num = votacao2[i].num;
                    votacao2[i].num = aux;
        
                    aux = votacao2[j].secao;
                    votacao2[j].secao = votacao2[i].secao;
                    votacao2[i].secao = aux;

                    aux = votacao2[j].code;
                    votacao2[j].code = votacao2[i].code;
                    votacao2[i].code = aux;
                }
            }
            if(i!=0){
                gravar.newLine();
            }
            gravar.write(Integer.toString(votacao2[i].num));
            gravar.newLine();
            gravar.write(Integer.toString(votacao2[i].secao));
            gravar.newLine();
            gravar.write(Integer.toString(votacao2[i].code));
        }
        gravar.close();
        ler.close();
        return votacao2;
    }
    public Votacao[] apurar_votacaos(Votacao[] votacao1, Votacao[] votacao2, Votacao[] apuracao)throws IOException{
        // Loop responsável por "limpar" os espaços do vetor dos objetos de Votacao e Eleitor. Conforme abaixo:
        for(int i=0;i<10;i++){
            votacao1[i] = new Votacao();
            votacao2[i] = new Votacao();
            apuracao[i] = new Votacao();
        }

        /*
        * Criação de um arquivo .txt para gravação dos dados das duas votações e leitura dos arquivos .txt de votacões.
        * - É usado um Buffer de escrita "BufferedWriter" para que seja possível a gravação dos dados.
        * - É usado um Buffer de leitura "BufferedReader" para que seja possível a leitura dos dados.
        * Assim conforme abaixo:
        */
        String filename1 = "cadastro_votacao1.txt";
        BufferedReader ler1 = new BufferedReader(new FileReader(filename1));
        String filename2 = "cadastro_votacao2.txt";
        BufferedReader ler2 = new BufferedReader(new FileReader(filename2));
        String filename3 = "apuracao.txt";
        BufferedWriter gravar = new BufferedWriter(new FileWriter(filename3));

        String aux;
        int i=0, j=0, a,sit=0, sit2=0;

        /*
         * Abaixo, os 3 loops "while" servem para fazer a leitura dos arquivos de votações. A necessidade
         * deles veio, pois no método normal, que é utilizando o "for", dava um erro de não conseguir ler
         * uma String nula. Sinceramente, não sei o que dava esse erro, mas eu consegui concertar da forma
         * que está aí.
         */

        aux = "";
        while((aux = ler1.readLine())!=null){
            votacao1[i].num = Integer.parseInt(aux);
            votacao1[i].secao = Integer.parseInt(ler1.readLine());
            votacao1[i].code = Integer.parseInt(ler1.readLine());
            i++;
        }
        aux = "";
        i=0;
        while((aux = ler2.readLine())!=null){
            votacao2[i].num = Integer.parseInt(aux);
            votacao2[i].secao = Integer.parseInt(ler2.readLine());
            votacao2[i].code = Integer.parseInt(ler2.readLine());
            i++;
        }
        i=0;
        a=0;

        /*
         * Abaixo, há dois loops "while", e começando por partes, no primeiro há um "if" que só permite
         * que a gravação ocorra quando o espaço do vetor for diferente de zero. E quando o índice + 1 do
         * vetor for zero, quer dizer que os dados do primeiro arquivo terminaram, e aí entra o segundo
         * "while", que faz a mesma coisa. Enquanto esse processo anda, os dados são salvos em "apuracao".
         */
        while(sit==0){
            if(votacao1[i].num!=0){
                gravar.write(Integer.toString(votacao1[i].num));
                gravar.newLine();
                gravar.write(Integer.toString(votacao1[i].secao));
                gravar.newLine();
                gravar.write(Integer.toString(votacao1[i].code));
                gravar.newLine();

                apuracao[a].num = votacao1[i].num;
                apuracao[a].secao = votacao1[i].secao;
                apuracao[a].code = votacao1[i].code;

                if(votacao1[i+1].num==0){
                    while(sit2==0){
                        if(votacao2[j].num!=0){
                            if(j!=0){
                                gravar.newLine();
                            }
                            gravar.write(Integer.toString(votacao2[j].num));
                            gravar.newLine();
                            gravar.write(Integer.toString(votacao2[j].secao));
                            gravar.newLine();
                            gravar.write(Integer.toString(votacao2[j].code));
                            
                            apuracao[a].num = votacao2[j].num;
                            apuracao[a].secao = votacao2[j].secao;
                            apuracao[a].code = votacao2[j].code;

                            if(votacao2[j+1].num==0){
                                sit2 = 1;
                            }
                        }
                        j++;
                        a++;
                    }
                    sit = 1;
                }
            }
            a++;
            i++;
        }
        JOptionPane.showMessageDialog(null, "Agrupado.");
        gravar.close();
        ler1.close();
        ler2.close();
        return apuracao;
    }

    /* 
     * Quando o usuário escolhe o menu de estatística, esse módulo é chamado e exibe mais um menu
     * para escolher o(a) vendedor(a), segundo lugar, votos em branco, votos nulos, mostrar os
     * eleitores e mostrar apuração.
     * 
     * Assim conforme abaixo:
     */
    public void menu_estat(Eleitor[] eleitor, Votacao[] apuracao)throws IOException{
        int opc = 0;
        while(opc!=9){
            opc = Integer.parseInt(JOptionPane.showInputDialog("MENU DE ESTATÍSTICA\n\n1 - Vencedor.\n2 - Segundo lugar.\n3 - Quantidade de votos brancos.\n4 - Quantidade de votos nulos.\n5 - Mostrar eleitores.\n6 - Mostrar apuração.\n9 - Sair."));
            switch (opc){
                case 1:
                    vencedor(apuracao);
                    break;
                case 2:
                    sec_lugar(apuracao);
                    break;
                case 3:
                    vt_branco(apuracao);
                    break;
                case 4:
                    vt_nulo(apuracao);
                    break;
                case 5:
                    mostra_eleitor(eleitor);
                    break;
                case 6:
                    mostra_apuracao(apuracao);
                    break;
                case 9:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
            }
        }
    }
    int c1=0, c2=0;
    public void vencedor(Votacao[] apuracao)throws IOException{
        c1=0;
        c2=0;
        // Loop responsável por "limpar" os espaços do vetor do objeto de Votacao. Conforme abaixo:
        for(int i=0;i<10;i++){
            apuracao[i] = new Votacao();
        }

        /*
         * O arquivo da apuração é chamado para que seja possível buscar o candidato com mais votos.
         * - É usado um Buffer de leitura "BufferedReader" para que seja possível a leitura dos dados.
         * Assim conforme abaixo:
         */
        String filename = "apuracao.txt";
        BufferedReader ler = new BufferedReader(new FileReader(filename));

        // Loop responsável por ler todos os dados do arquivo de apuração.
        for(int i=0;i<10;i++){
            apuracao[i].num = Integer.parseInt(ler.readLine());
            apuracao[i].secao = Integer.parseInt(ler.readLine());
            apuracao[i].code = Integer.parseInt(ler.readLine());
        }

        /*
         * Este loop junto do switch case serve para determinar o código de candidato mais votado. O if após o fim do "for"
         * serve para mostrar qual candidato venceu.
         */
        for(int i=0;i<10;i++){
            switch(apuracao[i].code){
                case 1:
                    c1++;
                    break;
                case 2:
                    c2++;
                    break;
            }
        }
        if(c1>c2){
            JOptionPane.showMessageDialog(null, "VENCEDOR(A)\n\nNome: José.\nCód. Candidato: 1.\nCom: " + c1 + " votos.");
        }
        else if(c2>c1){
            JOptionPane.showMessageDialog(null, "VENCEDOR(A)\n\nNome: Maria.\nCód. Candidato: 2.\nCom: " + c2 + " votos.");
        }
        else{
            JOptionPane.showMessageDialog(null, "EMPATE\n\nNome: José.\nCód. Candidato: 1.\n\nNome: Maria.\nCód. Candidato: 2.");
        }
    }

    public void sec_lugar(Votacao[] apuracao)throws IOException{
        c1=0;
        c2=0;
        // Loop responsável por "limpar" os espaços do vetor do objeto de Votacao. Conforme abaixo:
        for(int i=0;i<10;i++){
            apuracao[i] = new Votacao();
        }

        /*
         * O arquivo da apuração é chamado para que seja possível buscar o segundo candidato com mais votos.
         * - É usado um Buffer de leitura "BufferedReader" para que seja possível a leitura dos dados.
         * Assim conforme abaixo:
         */
        String filename = "apuracao.txt";
        BufferedReader ler = new BufferedReader(new FileReader(filename));

        // Loop responsável por ler todos os dados do arquivo de apuração.
        for(int i=0;i<10;i++){
            apuracao[i].num = Integer.parseInt(ler.readLine());
            apuracao[i].secao = Integer.parseInt(ler.readLine());
            apuracao[i].code = Integer.parseInt(ler.readLine());
        }

        /*
         * Este loop junto do switch case serve para determinar o código de candidato menos votado. O if após o fim do "for"
         * serve para mostrar qual candidato perdeu.
         */
        for(int i=0;i<10;i++){
            switch(apuracao[i].code){
                case 1:
                    c1++;
                    break;
                case 2:
                    c2++;
                    break;
            }
        }
        if(c2>c1){
            JOptionPane.showMessageDialog(null, "SEGUNDO LUGAR\n\nNome: José.\nCód. Candidato: 1.\nCom: " + c1 + " votos.");
        }
        else if(c1>c2){
            JOptionPane.showMessageDialog(null, "SEGUNDO LUGAR\n\nNome: Maria.\nCód. Candidato: 2.\nCom: " + c2 + " votos.");
        }
        else{
            JOptionPane.showMessageDialog(null, "Foi empate.");
        }
    }
    public void vt_branco(Votacao[] apuracao)throws IOException{
        // Loop responsável por "limpar" os espaços do vetor do objeto de Votacao. Conforme abaixo:
        for(int i=0;i<10;i++){
            apuracao[i] = new Votacao();
        }

        /*
         * O arquivo da apuração é chamado para que seja possível buscar os votos em branco.
         * - É usado um Buffer de leitura "BufferedReader" para que seja possível a leitura dos dados.
         * Assim conforme abaixo:
         */
        String filename = "apuracao.txt";
        BufferedReader ler = new BufferedReader(new FileReader(filename));

        // Loop responsável por ler todos os dados do arquivo de apuração.
        for(int i=0;i<10;i++){
            apuracao[i].num = Integer.parseInt(ler.readLine());
            apuracao[i].secao = Integer.parseInt(ler.readLine());
            apuracao[i].code = Integer.parseInt(ler.readLine());
        }

        /*
         * Este loop junto do switch case serve para determinar o a quantia de votos em branco. O if após o fim do "for"
         * serve para mostrar quantos são.
         */
        int branco=0;
        for(int i=0;i<10;i++){
            switch(apuracao[i].code){
                case 3:
                    branco++;
                    break;
            }
        }
        JOptionPane.showMessageDialog(null, branco + " voto(s) em branco.");
    }
    public void vt_nulo(Votacao[] apuracao)throws IOException{
        // Loop responsável por "limpar" os espaços do vetor do objeto de Votacao. Conforme abaixo:
        for(int i=0;i<10;i++){
            apuracao[i] = new Votacao();
        }

        /*
         * O arquivo da apuração é chamado para que seja possível buscar os votos nulos.
         * - É usado um Buffer de leitura "BufferedReader" para que seja possível a leitura dos dados.
         * Assim conforme abaixo:
         */
        String filename = "apuracao.txt";
        BufferedReader ler = new BufferedReader(new FileReader(filename));

        // Loop responsável por ler todos os dados do arquivo de apuração.
        for(int i=0;i<10;i++){
            apuracao[i].num = Integer.parseInt(ler.readLine());
            apuracao[i].secao = Integer.parseInt(ler.readLine());
            apuracao[i].code = Integer.parseInt(ler.readLine());
        }

        /*
         * Este loop junto do switch case serve para determinar o a quantia de votos nulos. O if após o fim do "for"
         * serve para mostrar quantos são.
         */
        int nulo=0;
        for(int i=0;i<10;i++){
            switch(apuracao[i].code){
                case 4:
                    nulo++;
                    break;
            }
        }
        JOptionPane.showMessageDialog(null, nulo + " voto(s) em branco.");
    }
    public void mostra_eleitor(Eleitor[] eleitor)throws IOException{
        // Loop responsável por "limpar" os espaços do vetor do objeto de Eleitor. Conforme abaixo:
        for(int i=0;i<10;i++){
            eleitor[i] = new Eleitor();
        }

        /*
         * O arquivo de eleitores é chamado para que seja possível mostrá-los.
         * - É usado um Buffer de leitura "BufferedReader" para que seja possível a leitura dos dados.
         * Assim conforme abaixo:
         */
        String filename = "cadastro_eleitor.txt";
        BufferedReader ler = new BufferedReader(new FileReader(filename));

        // Loop responsável por ler todos os dados do arquivo de eleitor.
        for(int i=0;i<10;i++){
            eleitor[i].nome = ler.readLine();
            eleitor[i].num = Integer.parseInt(ler.readLine());
            eleitor[i].secao = Integer.parseInt(ler.readLine());
        }

        /*Este loop é responsável por mostrar todos os eleitores, onde é usado system.out para mostrar
         * pois o JOptionPane não seria uma boa.
         */
        System.out.println("\n================ ELEITORES ================");
        for(int i=0;i<10;i++){
            System.out.println("\nNome: " + eleitor[i].nome + "\nNº Eleitor: " + eleitor[i].num + "\nSeção: " + eleitor[i].secao);
        }
    }
    public void mostra_apuracao(Votacao[] apuracao)throws IOException{
        // Loop responsável por "limpar" os espaços do vetor do objeto de Votacao. Conforme abaixo:
        for(int i=0;i<10;i++){
            apuracao[i] = new Votacao();
        }

        /*
         * O arquivo de apuração é chamado para que seja possível buscar seus dados.
         * - É usado um Buffer de leitura "BufferedReader" para que seja possível a leitura dos dados.
         * Assim conforme abaixo:
         */
        String filename = "apuracao.txt";
        BufferedReader ler = new BufferedReader(new FileReader(filename));

        // Loop responsável por ler todos os dados do arquivo de eleitor.
        for(int i=0;i<10;i++){
            apuracao[i].num = Integer.parseInt(ler.readLine());
            apuracao[i].secao = Integer.parseInt(ler.readLine());
            apuracao[i].code = Integer.parseInt(ler.readLine());
        }

        /*Este loop é responsável por mostrar todos os dados da apuração, onde é usado system.out para mostrar
         * pois o JOptionPane não seria uma boa.
         */
        System.out.println("\n================ APURAÇÃO ================");
        for(int i=0;i<10;i++){
            System.out.println("\nNº Eleitor: " + apuracao[i].num + "\nNº Seção: " + apuracao[i].secao + "\nVoto: " + apuracao[i].code);
        }
    }
}