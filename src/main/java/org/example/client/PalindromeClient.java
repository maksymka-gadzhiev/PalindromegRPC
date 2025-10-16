package org.example.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.palindrome.PalindromeRequest;
import org.example.palindrome.PalindromeResponse;
import org.example.palindrome.PalindromeServiceGrpc;

import java.util.Scanner;

public class PalindromeClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        PalindromeServiceGrpc.PalindromeServiceBlockingStub stub =
                PalindromeServiceGrpc.newBlockingStub(channel);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Введите строку для проверки (или 'exit' для выхода): ");
            String input = scanner.nextLine();

            if ("exit".equalsIgnoreCase(input)) {
                break;
            }

            PalindromeRequest request = PalindromeRequest.newBuilder()
                    .setMessage(input)
                    .build();

            PalindromeResponse response = stub.checkPalindrome(request);

            // Выводим строку, которую получили от сервера
            System.out.println("Получено от сервера: " + response.getOriginalMessage());

            if (response.getIsPalindrome()) {
                System.out.println("Палиндром: ДА");
            } else {
                System.out.println("Палиндром: НЕТ");
            }
            System.out.println();
        }

        channel.shutdown();
        scanner.close();
    }
}