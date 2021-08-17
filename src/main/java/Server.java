import com.datastax.oss.driver.api.core.CqlSession;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

public class Server {
    public Server() {
        CqlSession session = CqlSession.builder().build();
        CustomerRepository cr = new CustomerRepository(session);
    HttpServer.create().host("localhost").port(8081).route(routes ->
        routes.get("/hello",(request, response) ->
            response.sendString(Mono.just("Hello World!")))
            .get("/account",(request, response) ->
            response.send(cr.readAll().map(Server::toByteBuff))))
            .bindNow().onDispose().block();
    }
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static ByteBuf toByteBuff(Object o) {
        try {
            return Unpooled.buffer()
                    .writeBytes(OBJECT_MAPPER.writerFor(Customer.class).writeValueAsBytes(o));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}