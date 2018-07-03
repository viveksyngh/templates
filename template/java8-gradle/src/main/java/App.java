import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.openfaas.Handler;

public class App {

    public static void main(String[] args) throws Exception {
        int port = 8081;

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new FunctionHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class FunctionHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            Handler sut = new Handler();
            String response = sut.Handle("test");

            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}