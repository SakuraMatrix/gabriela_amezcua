import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CustomerRepository {
    private static final String TABLE_NAME = "customers";
    private CqlSession session;

        // Constructor
    public CustomerRepository(CqlSession session) {
         this.session = session;
    }

    public void create(Customer customer) {
        PreparedStatement insertStatement = session.prepare(
                "INSERT INTO Accounts.customers (SS, customer_Fname, customer_Lnname, customer_AcctNo) VALUES (?, ?, ?, ?)");

        BoundStatement boundStatement = insertStatement.bind(
                customer.getSS(),
                customer.getCustomer_Fname(),
                customer.getCustomer_Lnname(),
                customer.getCustomer_AcctNo());
                //customer.getCustomer_DOB());
        session.execute(boundStatement);
    }

    public Mono<Customer> read(Customer customer) {
        return Mono.from(session.executeReactive(
                // UNCOMMENT for JSON result ('comment other SELECT statement')
//          "SELECT JSON * FROM" +
            "SELECT * FROM  Accounts.customers WHERE customer_Fname = ? ALLOW FILTERING" ))
                .map(row -> new Customer(row.getInt("SS"),row.getString("customer_Fname"),
                        row.getString("customer_Lnname"),
                        row.getInt("customer_AcctNo")));
        }
    public Flux<Customer> readAll() {
        return Flux.from(session.executeReactive(
                        // UNCOMMENT for JSON result ('comment other SELECT statement')
//          "SELECT JSON * FROM" +
                        "SELECT * FROM  Accounts.customers" ))
                .map(row -> new Customer(row.getInt("SS"),row.getString("customer_Fname"),
                        row.getString("customer_Lnname"),
                        row.getInt("customer_AcctNo")));
    }
    public void update(Customer customer, int customer_AcctNo) {
        PreparedStatement updateStatement = session.prepare(
                "UPDATE Accounts.customers SET customer_AcctNo = ? WHERE SS = ?");

        BoundStatement boundStatement = updateStatement.bind( // Statement.
                customer_AcctNo, customer.getSS());
        session.execute(boundStatement);
    }

    public void delete(Customer customer) {
        PreparedStatement deleteStatement = session.prepare(
                "DELETE FROM Accounts.customers WHERE SS = ?");

        BoundStatement boundStatement = deleteStatement.bind(customer.getSS());
        session.execute(boundStatement);
    }
}
