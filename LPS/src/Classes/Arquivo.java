package Classes;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author aluno
 */
public class Arquivo {

    
    public static void escrever(String texto, String caminho) {
        FileWriter fw;
        File arquivo = new File(caminho);
            try {
                fw = new FileWriter(arquivo);
                fw.write(texto);
                fw.flush();
                fw.close();
            } catch (Exception e) {
                System.out.println("Erro: " + e.toString());
            }        
        }
    
    public static String lerArquivo(String caminho){ //usando delimitador
        try{
            String lido = "";
            FileReader fr = new FileReader(caminho); //classe pra leitura
            Scanner arquivoEntrada = new Scanner(fr); //scanea o arquivo carregado
            arquivoEntrada.useDelimiter("\n"); //define delimitador
            String aux = arquivoEntrada.next(); //pega posiÃ§Ã£o inicial do arquivo
            while(arquivoEntrada.hasNext()){ //loop enquanto existir texto
                lido += arquivoEntrada.next()+"\n"; //vai pegando cada palavra do texto e concatena
                //System.out.println(lido); //printa cada palavra
            }
            arquivoEntrada.close();
            return lido;
        }catch(Exception e){
            return "";
        }
        
    }
    
    public static String[] getLinhas(String lido){
        String facul = lido;
        String[] arrayFacul = facul.split("\r\n");
        return arrayFacul;        
    }
    }
