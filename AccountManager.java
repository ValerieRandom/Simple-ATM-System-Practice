
public class AccountManager {

    public static void main(String[] args){
        Account account = new Account("Valerie", 10000,"0000",null,10.0,0.0);
        account.AccountNumberMaker("Valerie");
        account.passwordRule();

    }
}
