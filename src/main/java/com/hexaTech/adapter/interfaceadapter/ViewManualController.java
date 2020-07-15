package com.hexaTech.adapter.interfaceadapter;

import com.hexaTech.domain.port.in.ViewManualInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Component
public class ViewManualController {
    private final ViewManualInputPort viewManualInputPort;

    @Autowired
    ViewManualController(ViewManualInputPort viewManualInputPort){
        this.viewManualInputPort=viewManualInputPort;
    }

    /**
     * Opens the specified section of the manual.
     * @param title String - section's title.
     * @throws IOException if the document doesn't exists.
     */
    public void openManualSection(String title) throws IOException {
        viewManualInputPort.openManual(title);
    }

    public void openManual() throws IOException {
        viewManualInputPort.openDocument();
    }

}//ViewManualController
