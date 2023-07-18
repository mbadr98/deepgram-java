package com.translate.main.connection.clients;


import com.translate.main.utils.TranslateUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class DeepgramClient {

    public static final String API_KEY = "8448d81e64f999cea31cff6492d460716340bd90";


    public static void recordingToText(HttpRequest.BodyPublisher bodyPublisher, String language) throws FileNotFoundException {
        HttpRequest httpRequest = DeepgramClient.createPostRequest("https://api.deepgram.com/v1/listen"+"?language="+language+"&model=nova",bodyPublisher);
        try {
            DeepgramClient.getResponse(httpRequest);
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static HttpRequest createPostRequest(String uri, HttpRequest.BodyPublisher bodyPublisher) throws FileNotFoundException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .timeout(Duration.ofMinutes(5))
                .header("Content-Type", "audio/wav")
                .header("Authorization", "Token "+API_KEY)
                .POST(bodyPublisher)
                .build();
        return request;
    }

    public static void getResponse(HttpRequest request) throws IOException, InterruptedException, ExecutionException, TimeoutException {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(300))
                .build();

        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(TranslateUtils::mapResponse)
                .thenAccept(t -> {
                        TranslateUtils.printResponse(t);
                }).get(300, TimeUnit.SECONDS);

    }



}
