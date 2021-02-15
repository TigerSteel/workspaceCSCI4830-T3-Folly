import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Contact;
//import util.Info;
import util.UtilDBFolly;


@WebServlet("/MyServletHibernateDBFolly")
public class MyServletHibernateDBFolly extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public MyServletHibernateDBFolly() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html");

     // #1
//      UtilDBFolly.createEmployees("user1", "11", "402-111-0101");
      
      // #2
      retrieveDisplayData(response.getWriter());
   }

   void retrieveDisplayData(PrintWriter out) {
      String title = "Contact List Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
            "transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");
      List<Contact> listEmployees = UtilDBFolly.listContacts();
      for (Contact employee : listEmployees) {
         System.out.println("[DBG] " + employee.getId() + ", " //
               + employee.getName() + ", " //
               + employee.getPhone() + ", " //
               + employee.getAddress() + ", " //
               + employee.getEmail() + ", " //
               + employee.getNotes());
         
         out.println("<li>" + employee.getId() + ") " //
        		 + "" + "	Name: " + employee.getName() //
        		 + "</br>" + "	Phone: " + employee.getPhone() //
        		 + "</br>" + "	Address: " + employee.getAddress() //
        		 + "</br>" + "	Email: " + employee.getEmail() //
        		 + "</br>" + "	Notes: " + employee.getNotes() + "</br>");
         out.println("<br>");
      }
      out.println("<br>");
      
      out.println("</ul>");
      out.println("<a href=/" + util.Info.projectName  + "/" + util.Info.searchWebName + ">Search Contact</a> <br>");
      out.println("</body></html>");
      out.println("</ul>");
      out.println("<br>");
      out.println("<a href=/" +  util.Info.projectName  + "/" + util.Info.insertWebName + ">Insert Contact</a> <br>");
      out.println("</body></html>");
      out.println("</ul>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
