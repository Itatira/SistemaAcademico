/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import DataBase.Persistencia;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author VICTOR
 */
public class Curso {
    private Scanner scan;
    private String nome;
    private int cargaH;
    private int codCurso;
    
    public Curso(){
        scan = new Scanner (System.in);
        nome = "";
        cargaH= 0;
        codCurso = 0;
    }
    
    public void Curso(String nome, int cargaH, int codCurso){
        this.scan = new Scanner (System.in);
        this.nome = nome;
        this.cargaH = cargaH;
        this.codCurso = codCurso;
    }
    

     
      public void preencheCurso(int codigo) {
          System.out.println("Digite o nome do curso:");
          nome = scan.nextLine();
          System.out.println("Digite a carga horaria do curso:");
          cargaH = scan.nextInt();     
    }
     
     public void imprimir(){
        System.out.println("Curso: " + nome);
        System.out.println("Carga Horária: " + cargaH);
        System.out.println("Código;" + codCurso);
    }
     

    public void saveBD(){
        PreparedStatement ps = null;
        try{
            ps = Persistencia.conexao().prepareStatement("INSERT INTO curso (nome, cargah) VALUES (?,?)");
            
            ps.setString(1, this.getNome());
            ps.setInt(2, this.getCargaH());
            
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println("Não conectou. "+e);
        }
    }
    
    public void preencher(int cod, String nome, int cargaH){
        this.codCurso = cod;
        this.nome = nome;
        this.cargaH = cargaH;
    }
     
    /**
     * @return the scan
     */
    public Scanner getScan() {
        return scan;
    }

    /**
     * @param scan the scan to set
     */
    public void setScan(Scanner scan) {
        this.scan = scan;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the cargaH
     */
    public int getCargaH() {
        return cargaH;
    }

    /**
     * @param cargaH the cargaH to set
     */
    public void setCargaH(int cargaH) {
        this.cargaH = cargaH;
    }

    
    /**
     * @return the codCurso
     */
    public int getCodCurso() {
        return codCurso;
    }

    /**
     * @param codCurso the codCurso to set
     */
    public void setCodCurso(int codCurso) {
        this.codCurso = codCurso;
    }
    
}
