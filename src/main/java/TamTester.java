import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.*;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import com.datastax.oss.driver.api.querybuilder.schema.CreateKeyspace;
import com.datastax.oss.driver.api.querybuilder.schema.CreateTable;


public class TamTester {
    long minPrime;

    public static void main (String[] args){

        //starts a casandra session and .builder connects with default settings
        try (CqlSession session = CqlSession.builder().build()) {
            // execute a csql command i do it form the session.execute
            ResultSet rs = session.execute("SELECT * from system.local");
            Row row = rs.one();
            //method has to match the type of the db column
            System.out.println(row.getUuid("host_id"));

            //code to create keyspace, if not exists, with replication factor : '1', and execute.
            // Create keyspace
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
        }
    }

}