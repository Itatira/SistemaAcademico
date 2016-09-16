/*
 * To change this license header, choose License Headers in Project Properties.      
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import DataBase.Persistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author VICTOR
 */
public class Aluno {

    private Scanner scan;
    private String nome;
    private String cpf;
    private int codigo;
 

    public Aluno() {
        this.scan = new Scanner(System.in);
        nome = "";
        cpf = "";
    }

    public Aluno(String nome, String cpf, int codigo) {
        this.scan = new Scanner(System.in);
        this.nome = nome;
        this.cpf = cpf;
    }

    public void preencher(int codigo) {
        System.out.println("Digite o nome do aluno:");
        nome = scan.next();
        System.out.println("Digite o cpf do aluno:");
        cpf = scan.next();
    }

    public void imprimir() {
        System.out.println("Aluno: " + nome);
        System.out.println("CPF: " + cpf);
    }
    
     
    public void saveBD(){
        PreparedStatement ps = null;
        try{
            ps = Persistencia.conexao().prepareStatement("INSERT INTO aluno (nome, cpf) VALUES (?, ?)");
            
            ps.setString(1, this.getNome());
            ps.setString(2, this.getCpf());
            
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println("Não conectou. "+e);
        }
    }
    public void preencher(int cod, String nome, String cpf){
        this.codigo = cod;
        this.nome = nome;
        this.cpf = cpf;
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
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
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
}
