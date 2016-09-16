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
public class Matricula {

    private Scanner scan;
    private int numeroMat;
    private String data;
    private int codigo;
    private int curso;
    private int aluno;

    public Matricula() {
        scan = new Scanner(System.in);
        data = "";
        curso = 0;
        aluno = 0;
        numeroMat =0;
    }

    public Matricula(int numeroMat, int curso, int aluno, String data) {
        scan = new Scanner(System.in);
        this.numeroMat = numeroMat;
        this.data = data;
        this.curso = curso;
        this.aluno = aluno;
    }
    
    public void preencher(int aluno, int curso, int numeroMat, String data){
        this.aluno = aluno;
        this.curso = curso;
        this.data = data;
        this.numeroMat = numeroMat;        
    }

    public void imprimir() {
        System.out.println("Numero de matricula: " + numeroMat);
        System.out.println("Data da matricula: " + getData());
    }
    
    public void saveBD(){
        PreparedStatement ps = null;
        try{
            ps = Persistencia.conexao().prepareStatement("INSERT INTO matricula (data, cod_aluno, cod_curso, num_mat) VALUES (?,?,?,?)");
            
            ps.setString(1, this.getData());
            ps.setInt(2, this.getAluno());
            ps.setInt(3, this.getCurso());
            ps.setInt(4, this.getNumeroMat());
            
            
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println("Não conectou. "+e);
        }
    }
    
    public void preencher(int cod, String data, int aluno, int curso, int numeroMat){
        this.codigo = cod;
        this.data = data;
        this.aluno = aluno;
        this.curso = curso;
        this.numeroMat = numeroMat;
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
     * @return the numeroMat
     */
    public int getNumeroMat() {
        return numeroMat;
    }

    /**
     * @param numeroMat the numeroMat to set
     */
    public void setNumeroMat(int numeroMat) {
        this.numeroMat = numeroMat;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.setData(data);
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the curso
     */
    public int getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(int curso) {
        this.curso = curso;
    }

    /**
     * @return the aluno
     */
    public int getAluno() {
        return aluno;
    }

    /**
     * @param aluno the aluno to set
     */
    public void setAluno(int aluno) {
        this.aluno = aluno;
    }

  
    /**
     * @return the codigo
     */
}
