/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import DataBase.Persistencia;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author VICTOR
 */
public class Escola {

    private Scanner scan;
    private ArrayList<Aluno> aluno;
    private ArrayList<Matricula> matricula;
    private ArrayList<Curso> curso;
    private int codAluno;
    private int codMat;
    private int codCurso;

    public Escola() {
        this.scan = new Scanner(System.in);
        codMat = 0;
        codCurso = 0;
        codAluno = 0;
        aluno = new ArrayList<>();
        curso = new ArrayList<>();
        matricula = new ArrayList<>();     
    }


    
    public void preencherCurso(){     
        Curso aux = new Curso();
        aux.preencheCurso(codCurso++);
        getCurso().add(aux);
        aux.saveBD();
    }
    
    public void preencheAluno(){
        Aluno aux = new Aluno();
        aux.preencher(codAluno++);
        getAluno().add(aux);
        aux.saveBD();
    }
     public boolean existsAluno(int codAluno) {
        return this.aluno.stream().anyMatch((aluno) -> (aluno != null && aluno.getCodigo() == codAluno));
    }

    public boolean existsCurso(int codCurso) {
        return this.curso.stream().anyMatch((curso) -> (curso != null && curso.getCodCurso() == codCurso));
    }


    public void preencher() {
        Scanner ler = new Scanner(System.in);
        String data = "";
        System.out.println("Digite a data:");
        data = ler.next();
        System.out.print("Digite o código do curso: ");
        int idCursoLido = ler.nextInt();
        if (existsCurso(idCursoLido)) {
            System.out.print("Digite o código do aluno: ");
            int idAlunoLido = ler.nextInt();
            if (existsAluno(idAlunoLido)) {
                Matricula aux = new Matricula();
                aux.preencher(idCursoLido, idAlunoLido, this.getCodMat(), data);
                this.matricula.add(aux);
                aux.saveBD();
            } else {
                System.out.println("Aluno não cadastrado!");
            }
        } else {
            System.out.println("Curso não cadastrado!");
        }
    }
    
    
    
    /*public boolean verificaCurso(int cod){
        boolean a = false;
        for(int x = 0; x < codCurso; x++){
            a = cod == getCurso().get(x).getCodCurso();
        }
        return a;
    }
    public boolean verificaAluno(int cod){
        boolean a = false;
        for(int x = 0; x < codMat; x++){
            a = cod == getAluno().get(x).getCodigo();
        }
        return a;
    }

    public void preencher() {
        Matricula aux = new Matricula();
        aux.preencher(codMat++);
        if(!verificaCurso(aux.getCurso())){
            System.out.println("Curso não existe!");
        }
        else if(!verificaAluno(aux.getAluno())){
            System.out.println("Aluno não existe!");
        }  
    }*/

    public void imprimir() {
        int aux;
        for (int i = 0; i < getMatricula().size(); i++) {
            System.out.println("--------------------------------");
            getMatricula().get(i).imprimir();
            aux = getMatricula().get(i).getAluno();
            System.out.println("Nome:" + getAluno().get(aux).getNome());
            aux = getMatricula().get(i).getCurso();
            System.out.println("Curso:" + getCurso().get(aux).getNome());  
        }
    }
    public void imprimirCurso(){
        for (int i = 0; i < getCurso().size(); i++) {
            System.out.println("--------------------------------");
            getCurso().get(i).imprimir();
        }
    }
    

    public void menu() {
        System.out.println("1 - Matricular");
        System.out.println("2 - Imprimir Maticulas");
        System.out.println("3 - Cadastrar curso");
        System.out.println("4 - Imprimir Cursos");
        System.out.println("5 - Cadastrar aluno");
    }

    public void principal(){
        loadBD();
        int op = 0;
        do {
            menu();
            System.out.println("Selecione uma opção: ");
            op = scan.nextInt();
            switch (op) {

                case 1:
                    preencher();
                    break;
                case 2:
                    imprimir();
                    break;
                case 3:
                    preencherCurso();
                    break;
                case 4:
                    imprimirCurso();
                    break;
               
                case 5:
                    preencheAluno();
                    break;
                            default:
                    System.out.println("Opção inválida. Digite novamente: ");
                    op = scan.nextInt();
                    break;
            }
        } while (op != 0);
    }
        
    public void loadBDAluno(){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = Persistencia.conexao().prepareStatement("SELECT * FROM aluno");
            rs = ps.executeQuery();
            
            this.aluno = new ArrayList();
            
            while(rs.next()){
                Aluno aux = new Aluno();
                aux.preencher(rs.getInt("cod"), rs.getString("nome"), rs.getString("cpf"));
                this.aluno.add(aux);  
                
            }
        }catch(SQLException e){
                    System.out.println("Erro" + e);
                    }
    }
    
   public void loadBDCurso(){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = Persistencia.conexao().prepareStatement("SELECT * FROM curso");
            rs = ps.executeQuery();
            
            this.curso = new ArrayList();
            
            while(rs.next()){
                Curso aux = new Curso();
                aux.preencher(rs.getInt("cod"), rs.getString("nome"), rs.getInt("cargah"));
                this.curso.add(aux);  
                
            }
        }catch(SQLException e){
                    System.out.println("Erro" + e);
                    }
    }
   public void loadBDMatricula(){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = Persistencia.conexao().prepareStatement("SELECT * FROM matricula");
            rs = ps.executeQuery();
            
            this.matricula = new ArrayList();
            
            while(rs.next()){
                Matricula aux = new Matricula();
                aux.preencher(rs.getInt("cod"), rs.getString("data"), rs.getInt("cod_aluno"), rs.getInt("cod_curso"), rs.getInt("num_mat"));
                this.matricula.add(aux);  
                
            }
        }catch(SQLException e){
                    System.out.println("Erro" + e);
                    }
    }
   
   public void loadBD(){
       loadBDAluno();
       loadBDCurso();      
       loadBDMatricula();
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

    /**
     * @return the aluno
     */
    public ArrayList<Aluno> getAluno() {
        return aluno;
    }

    /**
     * @param aluno the aluno to set
     */
    public void setAluno(ArrayList<Aluno> aluno) {
        this.aluno = aluno;
    }

    /**
     * @return the matricula
     */
    public ArrayList<Matricula> getMatricula() {
        return matricula;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(ArrayList<Matricula> matricula) {
        this.matricula = matricula;
    }

    /**
     * @return the curso
     */
    public ArrayList<Curso> getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(ArrayList<Curso> curso) {
        this.curso = curso;
    }

    /**
     * @return the codAluno
     */
    public int getCodAluno() {
        return codAluno;
    }

    /**
     * @param codAluno the codAluno to set
     */
    public void setCodAluno(int codAluno) {
        this.codAluno = codAluno;
    }

    /**
     * @return the codMat
     */
    public int getCodMat() {
        return codMat;
    }

    /**
     * @param codMat the codMat to set
     */
    public void setCodMat(int codMat) {
        this.codMat = codMat;
    }

}
