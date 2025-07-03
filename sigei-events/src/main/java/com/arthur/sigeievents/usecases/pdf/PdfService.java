package com.arthur.sigeievents.usecases.pdf;

import com.arthur.sigeievents.controllers.response.EventResponseDTO;
import com.lowagie.text.DocumentException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@AllArgsConstructor
public class PdfService {

    private final TemplateEngine templateEngine;

    public byte[] generateEventPdf(EventResponseDTO event) throws DocumentException, IOException {

        Context context = new Context();
        context.setVariable("event", event);
        String htmlContent = templateEngine.process("/pages/event/event-pdf", context);
        return generatePdfFromHtml(htmlContent);

    }


    private byte[] generatePdfFromHtml(String html) throws DocumentException, IOException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        return outputStream.toByteArray();
    }
}