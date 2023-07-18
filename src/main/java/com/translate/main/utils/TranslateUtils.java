package com.translate.main.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.translate.main.dto.Transcription;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TranslateUtils {

    public static Transcription mapResponse(String response){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            Transcription transcription = objectMapper.readValue(response, Transcription.class);
            return transcription;
        } catch (JsonProcessingException e) {
            log.error("Error on parse",e);
        }
        return new Transcription();
    }

    public static void printResponse(Transcription transcription){
//        String transcript = transcription.getResult()
//                .getTranscriptionChannels().stream().findFirst().map(TranscriptionChannel::getAlternatives)
//                .map(ta -> ta.stream().findFirst().map(TranscriptionAlternative::getTranscript).orElse("No Transcripts"))
//                .orElse("No transcripts");
        System.out.println("testing printResponse");
        String transcript = transcription.getResult().getTranscriptionChannels().get(0).getAlternatives().get(0).getTranscript();
        String confidence = transcription.getResult().getTranscriptionChannels().get(0).getAlternatives().get(0).getConfidence();
        System.out.println(transcript);
        System.out.println(confidence);
        log.info("Assigning result");
    }


}
