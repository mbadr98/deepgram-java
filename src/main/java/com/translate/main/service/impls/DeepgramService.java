package com.translate.main.service.impls;

import com.translate.main.connection.clients.DeepgramClient;
import com.translate.main.service.interfaces.RecordingToText;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.net.http.HttpRequest;
import java.nio.file.Paths;

@Service
@Slf4j
public class DeepgramService implements RecordingToText {



    @Override
    public void recordingToText(String fileName, String language) throws FileNotFoundException {
       DeepgramClient.recordingToText(HttpRequest.BodyPublishers
                .ofFile(Paths.get(fileName)), language);
    }




}
