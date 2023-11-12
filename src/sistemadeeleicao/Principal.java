package sistemadeeleicao;
import javax.swing.*;
import java.io.*;
public class Principal {
    public static void main(String[] args)throws IOException{
        Metodos m = new Metodos();
        int opc = 0;
        
        /*
        Instâncias das classes Eleitor e Votacao para os objetos:
        - eleitor de Eleitor;
        - votacao1 de Votacao;
        - Votacao2 de Votacao e
        - apuração de Votacao.
        
        Conforme abaixo:
        */
        Eleitor[] el = new Eleitor[10];
        Votacao[] vt1 = new Votacao[10];
        Votacao[] vt2 = new Votacao[10];
        Votacao[] ap = new Votacao[10];

        /*
         * Abaixo, o loop "while" serve como bloqueio a menos que o usuário digite 9, que
         * é o comando para sair do menu. Cada opção mostrada leva para seus respectivos 
         * métodos.
         */
        while(opc!=9){
            opc = Integer.parseInt(JOptionPane.showInputDialog("SISTEMA DE ELEIÇÃO\n\n1 - Cadastrar eleitor.\n2 - Menu de votação.\n3 - Agrupar votações.\n4 - Menu de estatísticas.\n9 - Finalizar."));
            switch(opc){
                case 1:
                    m.cad_el(el);
                    break;
                case 2:
                    m.menu_vt(el, vt1, vt2, ap);
                    break;
                case 3:
                    m.apurar_votacaos(vt1, vt2, ap);
                    break;
                case 4:
                    m.menu_estat(el, ap);
                    break;
                case 9:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
            }
        }
    }   
}
