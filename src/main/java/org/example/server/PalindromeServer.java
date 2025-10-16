package org.example.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class PalindromeServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 50051;
        Server server = ServerBuilder.forPort(port)
                .addService(new PalindromeServiceImpl())
                .build()
                .start();

        System.out.println("Server started on port " + port);
        server.awaitTermination();
    }
}