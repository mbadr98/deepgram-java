package com.translate.main.shell;

import com.translate.main.service.impls.DeepgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.FileNotFoundException;

@ShellComponent
@Slf4j
public class TranslateShell {

    @Autowired
    private DeepgramService deepgramService;

    @ShellMethod("sptt")
    public void sptt(
            @ShellOption(defaultValue = "recordings/test1EN.wav") String fileName,
            @ShellOption(defaultValue = "en-US") String language
    ) throws FileNotFoundException {
        deepgramService.recordingToText(fileName, language);
    }
}
