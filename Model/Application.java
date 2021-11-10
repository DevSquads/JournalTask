package Model;

import java.util.Scanner;

public class Application {
    String username;
    static ArticlesDatabase data =new ArticlesDatabase();
    AccountsDatabase userAccount = new AccountsDatabase();
    public boolean signup() {
        boolean created=false;
        String name;
        do{   Scanner sc = new Scanner(System.in);
        System.out.println("please enter account username:");
        name = sc.next();
        System.out.println("please enter password:");
        String password = sc.next();
        System.out.println("please confirm password:");
        String confirmPassword = sc.next();
        if (!password.equals(confirmPassword)) {
            boolean equal = false;
            while (!equal) {
                System.out.println("your password doesn't match,please reenter password:");
                password = sc.next();
                System.out.println("please confirm password:");
                confirmPassword = sc.next();
                if (password.equals(confirmPassword)){
                    equal = true;}
            }
        }
        created = userAccount.createAccount(name, password);
        if(!created)
            System.out.println("username already used.");
    }while (!created);
        username=name;
        System.out.println("you have successfully signed up to our application");
        return true;
    }

    public boolean login(){
            boolean loggedIn= false;
            String name="";
        do{
            Scanner sc = new Scanner(System.in);
            System.out.println("please enter account username:");
            name = sc.next();
            System.out.println("please enter password:");
            String password = sc.next();
            loggedIn=userAccount.checkLogin(name,password);
            if(!loggedIn)
                System.out.println("username or password is incorrect please try again.");
        }while(!loggedIn);
        username=name;
        System.out.println("you have successfully logged into our application");
        return loggedIn;
    }

    public void runBackend(){
        data.generateArticles();
        userAccount.generateAccounts();
    }

    public static void main(String[] args) {

        Application app=new Application();
        app.runBackend();
        Scanner sc=new Scanner(System.in);
        System.out.println("Welcome to our Model.Application");
        System.out.println("Type 1 to sign up 2 to login");
        int input=sc.nextInt();
        if(input==1)
            app.signup();
        else
            app.login();
        if(app.username.equals("samir")){
            while(input!=4){
            System.out.println("To  view  Articles please Enter 1");
            System.out.println("To create Model.Article  please Enter 2");
            System.out.println("To Delete Model.Article  please Enter 3");
            System.out.println("To    logout       please Enter 4");
            input=sc.nextInt();
            if(input==1)
                data.viewList(app.username);
            else if (input==2){
                System.out.println("who is the articles author?");
                String author=sc.next();
                System.out.println("what is the articles title?");
                String title=sc.next();
                System.out.println("what is the article description?");
                String description=sc.next();
                System.out.println( data.createArticle(author,title,description));
            }else if (input==3) {
                System.out.println("who is the articles author?");
                String author = sc.next();
                System.out.println("what is the title of the article you would like to delete?");
                String title = sc.next();
               System.out.println(data.deleteArticle(title, author));
            }}
        }else{
            while(input!=2){
        System.out.println("To  view  Articles please Enter 1");
        System.out.println("To    logout       please Enter 2");
        input=sc.nextInt();
        if(input==1){
            data.viewList(app.username);
        }}
        }


    }


}


