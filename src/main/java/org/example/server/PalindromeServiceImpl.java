package org.example.server;

import io.grpc.stub.StreamObserver;
import org.example.palindrome.PalindromeRequest;
import org.example.palindrome.PalindromeResponse;
import org.example.palindrome.PalindromeServiceGrpc;

public class PalindromeServiceImpl extends PalindromeServiceGrpc.PalindromeServiceImplBase {

    @Override
    public void checkPalindrome(PalindromeRequest request,
                                StreamObserver<PalindromeResponse> responseObserver) {

        String message = request.getMessage();
        String processed = message.replaceAll("[^a-zA-Zа-яА-Я0-9]", "").toLowerCase();

        boolean isPalindrome = processed.equals(new StringBuilder(processed).reverse().toString());

        PalindromeResponse response = PalindromeResponse.newBuilder()
                .setIsPalindrome(isPalindrome)
                .setOriginalMessage(new StringBuilder(processed).reverse().toString()) // Добавляем исходное сообщение в ответ
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}