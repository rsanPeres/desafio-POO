package br.com.dio.desafio.infra;

import br.com.dio.desafio.UserRequest;
import br.com.dio.desafio.dominio.*;
import br.com.dio.desafio.dominio.Factories.UserFactory;

import java.time.LocalDate;
import java.util.Scanner;


public abstract class Menu {
    public static UserDataBase dataBase = new UserDataBase();
    public static Admin admin = new Admin("geral", "admin@gmail.com", "1234", UserType.ADMIN);
    public static void profType(){
        System.out.println("----------------- DIO -----------------");
        System.out.println("1 - Cadastrar usuario: ");
        System.out.println("2 - Sou Dev: ");
        System.out.println("3 - Sou Professor(a): ");
        System.out.println("4 - Sou Admin: ");
        System.out.println("0 - Sair: ");
        System.out.println("database:" + dataBase.getUsers());

        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();
        getMenuProf(op);
    }

    public static void getMenuProf(int option){
        int op;
        Scanner sc = new Scanner(System.in);

        switch (option){
            case 0:
                return;
            case 1:
                User user = cadastrar();
                dataBase.inserirUsuario(user);
                System.out.println("database: " + dataBase.getUsers());
            profType();
            case 2:
                op = opDev();
                iniciarDev(op);
            case 3:
                op = opProf();
                iniciarProf(op);
            case 4:
                op = opAdm();
                iniciarAdmin(op);
            default:
                profType();
        }
    }

    private static int opAdm() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-------------- Olá Admin --------------");
        System.out.println("0 - Sair: ");
        System.out.println("1 - Cadastrar bootcamp: ");

        return sc.nextInt();
    }

    public static int opDev(){
        Scanner sc = new Scanner(System.in);
        System.out.println("-------------- Olá Dev --------------");
        System.out.println("0 - Sair: ");
        System.out.println("1 - Cadastrar bootcamp: ");
        System.out.println("2 - iniciar aula: ");
        System.out.println("3 - Ver conteúdo cadastrado: ");
        System.out.println("4 - Ver conteúdo concluido: ");

        return sc.nextInt();
    }
    public static void iniciarDev(int option){
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o email");
        String email = sc.nextLine().toLowerCase().trim();
        Dev dev = (Dev)dataBase.buscaUsuario(email);
        if (dev == null){
            System.out.println("Usuario não existe");
            profType();
        }
        System.out.println("Login ok");
        switch (option){
            case 0:
                profType();
            case 1:
                System.out.println("Digite o nome do bootcamp: ");
                String nome = sc.nextLine().toLowerCase().trim();
                Bootcamp bootcamp = admin.getBootcamp(nome);
                if(bootcamp != null) {
                    dev.inscreverBootcamp(bootcamp);
                }else {
                    System.out.println("Bootcamp não existe");
                }
                profType();
                break;
            case 2:
                dev.progredir();
                profType();
            case 3:
                System.out.println(dev.getConteudosInscritos());
                profType();
            case 4:
                System.out.println(dev.getConteudosConcluidos());
                profType();
        }
    }

    public static int opProf(){
        System.out.println("-------------- Olá Professor --------------");
        System.out.println("0 - Sair: ");
        System.out.println("1 - Cadastrar conteudo ministrado: ");

        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
    public static void iniciarProf(int op){
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o email");
        String email = sc.nextLine().toLowerCase().trim();
        Professor prof = (Professor)dataBase.buscaUsuario(email);
        if (prof == null){
            System.out.println("Usuario não existe");
            profType();
        }
        System.out.println("Login ok");
        switch (op){
            case 0:
                profType();
            case 1:
                System.out.println("Digite o nome do Bootcamp: ");
                String boot = sc.nextLine();
                System.out.println("Qual tipo de conteúdo: 1 - Curso; 2: Mentoria");
                int op1 = sc.nextInt();
                System.out.println("Ok");
                sc.nextLine();
                System.out.println("Digite o nome: ");
                String name = sc.nextLine();
                System.out.println("Digite a descrição: ");
                String descr = sc.nextLine();
                ConteudoBase cont;
                if( op1 == 1){
                    System.out.println("Digite a carga horária: ");
                    int carga = sc.nextInt();
                    cont = new Curso(name, descr, carga);
                }else {
                    System.out.println("digite a data: ");
                    LocalDate data = LocalDate.parse(sc.nextLine());
                    cont = new Mentoria(name, descr, data);
                }
                admin.setConteudos(boot,prof,cont);
                admin.getBootcamp(boot);
                prof.setMinistrados(cont);
                profType();
        }
    }

    public static void iniciarAdmin(int op){
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o email");
        String email = sc.nextLine().toLowerCase().trim();
        Admin adm = (Admin)dataBase.buscaUsuario(email);
        if (adm == null){
            System.out.println("Usuario não existe");
            profType();
        }
        System.out.println("Login ok");
        switch (op){
            case 0:
                profType();
            case 1:
                System.out.println("Digite o nome do Bootcamp: ");
                String nome = sc.nextLine().toLowerCase().trim();
                Bootcamp boot = admin.getBootcamp(nome);
                if(boot != null){
                    System.out.println("Bootcamp já existe");
                    opAdm();
                }
                System.out.println("Digite a descrição");
                String descri = sc.nextLine();
                boot = new Bootcamp(nome, descri);
                admin.setConteudo(boot);
                profType();
        }

    }

    public static User cadastrar() {
        UserFactory factory = new UserFactory();
        UserRequest request = new UserRequest();
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o email: ");
        String email = sc.nextLine().toLowerCase().trim();
        if (dataBase.buscaUsuario(email) == null) {
            request.email = email;
            System.out.println("Digite o nome: ");
            request.nome = sc.nextLine();
            System.out.println("Digite a senha: ");
            request.password = sc.nextLine();
            System.out.println("Digite o tipo de usuário: ");
            String type = sc.nextLine().trim().toUpperCase();
            request.type = UserType.valueOf(type);
            return factory.userCreate(request);
        }
        return null;
    }
}
