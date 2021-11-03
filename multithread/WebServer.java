// Anaísa Forti da Fonseca
// 11811ECP012

import java.io.*;
import java.net.*;
import java.util.*;

public final class WebServer {
    public static void  main(String arvg[]) throws Exception {
        //ajuste de número de porta
        int port = 2222;
        //estabelece socket de escuta
        try {
            ServerSocket server = new ServerSocket(port);
            while(true) {
                //escuta de requisição TCP
                Socket client = server.accept();
                //constrói um objeto para processar a mensagem de requisição HTTP
                HttpRequest request = new HttpRequest(client);
                //cria um novo thread para processar a requisição
                Thread thread = new Thread(request);
                thread.start();
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}

final class HttpRequest implements Runnable {
    final static String CRLF = "\r\n";
    Socket socket;

    public HttpRequest(Socket socket) throws Exception {
        this.socket = socket;
    }

    public void run() {
        try{
            processRequest();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    private void processRequest() throws Exception {
        InputStream is = socket.getInputStream();
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());

        InputStreamReader ir = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(ir);

        String requestLine = br.readLine();

        System.out.println();
        System.out.println(requestLine);

        String headerLine = null;
        while((headerLine = br.readLine()).length() != 0) {
            System.out.println(headerLine);
        }

        StringTokenizer tokens = new StringTokenizer(requestLine);
        tokens.nextToken();

        String fileName = tokens.nextToken();
        
        System.out.println(fileName);

        fileName = "." + fileName;

        FileInputStream fis = null;
        Boolean fileExists = true;

        try {
            fis = new FileInputStream(fileName);
        } catch(FileNotFoundException e) {
            fileExists = false;
        }

        String statusLine = null;
        String contentTypeLine = null;
        String entityBody = null;

        if(fileExists) {
            statusLine = "HTTP/1.0 200 OK " + CRLF;
            contentTypeLine = "Content-type: " + contentType(fileName) + CRLF;
        } else {
            statusLine = "HTTP/1.0 404 Not Found " + CRLF;
            contentTypeLine = "Content-type: text/html" + CRLF;
            entityBody = "<HTML>" + 
                "<HEAD><TITLE>not found</TITLE><STYLE>body{background-color:rgb(245, 229, 177);}h1,h3{display:flex;justify-content:center;align-items:center;}</STYLE></HEAD>" + 
                "<BODY><h1>not found</h1><div style=\"display:flex; justify-content: center;\"><img src=\"assets/lost.gif\"/></div></BODY></HTML>";
        }

        System.out.println(statusLine);
        System.out.println(contentTypeLine);
        System.out.println(fileName);

        os.writeBytes(statusLine);
        os.writeBytes(contentTypeLine);
        os.writeBytes(CRLF);

        if(fileExists) {
            sendBytes(fis, os);
            fis.close();
        } else {
            os.writeBytes(entityBody);
        }

        os.close();
        br.close();
        socket.close();
    }

    private static void sendBytes(FileInputStream fis, OutputStream os) throws Exception {
        byte[] buffer = new byte[1024];
        int bytes = 0;

        while((bytes = fis.read(buffer)) != -1) {
            os.write(buffer, 0, bytes);
        }
    }

    private static String contentType(String fileName) {
        if(fileName.endsWith(".htm") || fileName.endsWith(".html") || fileName.endsWith(".xml")) {
            return "text/html";
        }
        if(fileName.endsWith(".gif")) {
            return "image/gif";
        }
        if(fileName.endsWith(".jpeg")) {
            return "image/jpeg";
        }
        if(fileName.endsWith(".pdf")) {
            return "application.pdf/jpeg";
        }
        return "application/octet-stream";
    }

}