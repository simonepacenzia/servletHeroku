package servlet;

import org.json.JSONObject;
import org.json.JSONArray;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Contact;
import javax.servlet.ServletInputStream;
import org.json.JSONException;

@WebServlet(
        name = "MyServlet",
        urlPatterns = {"/contacts/*"}
)
public class HelloServlet extends HttpServlet {

    private HashMap<Long, Contact> contacts;
    private long contactId;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        contacts = new HashMap<Long, Contact>();
        contactId = 0;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");
        JSONArray jA = new JSONArray();
        jA.put(contacts);
        System.out.println(jA.toString());
        byte[] outputArray = jA.toString().getBytes();
        out.write(outputArray);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("text/plain");
        try{
        String body =  req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        JSONObject  jO = new JSONObject(body);
        String nome = (String) jO.get("name");
        String number = (String) jO.get("number");
        Contact c = new Contact();
        c.setName(nome);
        c.setNumber(number);
            System.out.println(c.toString());
        contacts.put(contactId, c);
        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.setHeader("Location", "/contacts/" + contactId);
        contactId++;
        out.write("".getBytes());
        }catch(JSONException ex){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("".getBytes());
        }
        out.flush();
        out.close();
        
    }

}
