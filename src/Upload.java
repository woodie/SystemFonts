import java.io.DataOutputStream;
import java.io.IOException;
import javax.microedition.io.HttpConnection;
import javax.microedition.io.Connector;
import javax.microedition.lcdui.Image;

public class Upload {

  public void sample(byte[] bytes) {

    HttpConnection http = null;
    DataOutputStream dos = null;
    Image image;
 
    try {
      // bytes getBytesFromImage(image);
      http = (HttpConnection) Connector.open("http://localhost:8080/Project/ImageServlet");
      http.setRequestMethod(HttpConnection.POST);
      http.setRequestProperty("Connection", "keep-alive");
      http.setRequestProperty("Content-Type", "multipart/form-data");
      http.setRequestProperty("Content-Length", bytes.length + "");
 
      dos = http.openDataOutputStream();
      dos.write(bytes);
     
      int response = http.getResponseCode();
      if (response == HttpConnection.HTTP_OK) System.out.println("Success !");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (http != null) http.close();
        if (dos != null) dos.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}

/*
https://www.coderanch.com/t/495650/send-image-post-method-Servlet

ataInputStream din = new DataInputStream(request.getInputStream());
byte[] data = new byte[0];
byte[] buffer = new byte[512];
int bytesRead;
while ((bytesRead = din.read(buffer)) > 0 ) {
    // construct large enough array for all the data we now have
    byte[] newData = new byte[data.length + bytesRead];
    // copy data previously read
    System.arraycopy(data, 0, newData, 0, data.length);
    // append data newly read
    System.arraycopy(buffer, 0, newData, data.length, bytesRead);
    // discard the old array in favour of the new one
    data = newData;
}
*/
