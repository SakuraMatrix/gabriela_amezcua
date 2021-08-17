import application.AccountMaker;
import org.junit.Ignore;
import org.junit.Test;


public class JunitTests {
    AccountMaker accountMaker = new AccountMaker();

    //@Ignore("work in progress")
    @Test
            public void firstMethodTest(){
            accountMaker.first_message();
    }
}
