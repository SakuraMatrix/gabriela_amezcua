package application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccountMaker {

    private static final Logger logger = LogManager.getLogger(AccountMaker.class);

    public void first_message(){
      logger.info("account maker method running.");
      System.out.println("Thank you for choosing to make an account with our bank.");
      System.out.println("Please enter the following information.");

    }

    public void input(){
        logger.info("account maker method running.");
        System.out.println("Thank you for choosing to make an account with our bank");
    }

}
