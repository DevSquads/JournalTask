package Model;

import java.util.ArrayList;
import java.util.List;

public class AccountsDatabase {
    List<Account> accounts=new ArrayList();
    public void generateAccounts(){
        Account admin=new Account("admin","admin");
        accounts.add(admin);
        Account samir=new Account("samir","admin");
        accounts.add(samir);
    }

    public boolean createAccount(String name,String password){

        for(Account u:accounts){
            if(u.name.equals(name))
                return false;
        }
        Account user=new Account(name,password);
        accounts.add(user);
        return true;
    }

    public boolean checkLogin(String name,String password){
        for(Account u:accounts){
            if(u.name.equals(name) && u.password.equals(password))
                return true;
        }
        return false;
    }
}
