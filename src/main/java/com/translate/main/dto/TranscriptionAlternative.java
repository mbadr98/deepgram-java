package com.translate.main.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TranscriptionAlternative {
    @JsonProperty("transcript")
    private String transcript;
    @JsonProperty("confidence")
    private String confidence;
}
