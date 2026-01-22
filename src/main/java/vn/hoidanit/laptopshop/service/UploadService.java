package vn.hoidanit.laptopshop.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;

@Service
public class UploadService {
  private final ServletContext servletContext;
  public UploadService(ServletContext servletContext) {
    this.servletContext = servletContext;
  }
  public String handleUploadFile(MultipartFile file, String targetFolder) {
    try {
      if (file.isEmpty()) {
        return "";
      }
      byte[] bytes = file.getBytes();
      String rootPath = this.servletContext.getRealPath("/resources/images/");
      File dir = new File(rootPath + File.separator+ targetFolder);
      if (!dir.exists()) {
        dir.mkdirs();
      }
      // create file server
      String finalName = System.currentTimeMillis() + file.getOriginalFilename();
      File serverFile = new File(dir.getAbsolutePath() + File.separator + finalName);

       BufferedOutputStream bufferedInputStream = new BufferedOutputStream(
        new FileOutputStream(serverFile));

       bufferedInputStream.write(bytes);
       bufferedInputStream.flush();
       bufferedInputStream.close();
       return finalName;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
