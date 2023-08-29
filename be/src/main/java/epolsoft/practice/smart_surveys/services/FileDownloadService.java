package epolsoft.practice.smart_surveys.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import org.springframework.core.io.Resource;
import java.io.IOException;

public interface FileDownloadService {
    Document getReport(Long id) throws IOException, DocumentException;
    Resource getFileAsResource(String fileCode)throws IOException;
}
