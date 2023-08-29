package epolsoft.practice.smart_surveys.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import epolsoft.practice.smart_surveys.services.impl.FileDownloadServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileDownloadController {
    @GetMapping("/downloadFile/{fileCode}")
    public void downloadFile(@PathVariable("fileCode") String fileCode, HttpServletResponse response) throws IOException {
        FileDownloadServiceImpl downloadUtil = new FileDownloadServiceImpl();

        Resource resource = null;
        try {resource = downloadUtil.getFileAsResource(fileCode);}
        catch (IOException e) {Exception exception = new Exception("file not found");}

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment; filename=" + resource.getFilename());

        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(resource.getFile());

        IOUtils.copy(in, out);
        out.close();
        in.close();
        resource.getFile().delete();
    }
}
