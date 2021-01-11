package server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GreetingServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(5005)
                .addService(new GreetServiceImpl())
                .build();

        server.start();
        System.out.println("started");
        Runtime.getRuntime().addShutdownHook(new Thread( () -> {
            System.out.println("received shutdown request");
            server.shutdown();
        }));

        server.awaitTermination();
    }
}
