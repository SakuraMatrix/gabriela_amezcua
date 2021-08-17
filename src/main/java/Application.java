import application.AccountMaker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.*;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import com.datastax.oss.driver.api.querybuilder.schema.CreateKeyspace;
import com.datastax.oss.driver.api.querybuilder.schema.CreateTable;

import java.time.Instant;

public class Application {

    private static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("Main method running.");
        System.out.println("hello main method");

        //starts a casandra session and .builder connects with default settings
        try (CqlSession session = CqlSession.builder().build()) {

            CreateKeyspace Accounts = SchemaBuilder.createKeyspace("Accounts")
                    .ifNotExists().withSimpleStrategy(1);
            //Execute create Keyspace.
            session.execute(Accounts.build());

            // Create Table.
            CreateTable customers = SchemaBuilder.createTable("Accounts", "customers")
                    .ifNotExists()
                    .withPartitionKey("SS", DataTypes.INT)
                    .withColumn("customer_Fname", DataTypes.TEXT)
                    .withColumn("customer_Lnname", DataTypes.TEXT)
                    .withColumn("customer_AcctNo", DataTypes.INT)
                    .withColumn("customer_DOB", DataTypes.TIMESTAMP);

            // Execute Create Table.
            session.execute(customers.build());

            // New book repository.
            CustomerRepository dao = new CustomerRepository(session);

            // New Book instance.
            Customer gaby = new Customer(545619931, "Gabriela", "Amezcua",
                    323344193, Instant.now());

            // Create.  put in post request look at tams in the server
            dao.create(gaby);

            // Read. this should be my get request get gaby. call it by get http... ss (545619931)
            dao.read(gaby);

            // Delete.
            dao.delete(gaby);
            Server server = new Server();
        }
    }
}



































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































