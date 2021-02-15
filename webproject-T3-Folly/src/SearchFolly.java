import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Contact;
import util.Info;
import util.UtilDBFolly;

@WebServlet("/SearchFolly")
public class SearchFolly extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public SearchFolly() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String name = request.getParameter("userName").trim();
      String phone = request.getParameter("phone").trim();

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Contact List Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");

      List<Contact> listEmployees = null;
      if ((name != null && !name.isEmpty()) && (phone != null && !phone.isEmpty()))
      {
         listEmployees = UtilDBFolly.listContacts(name, phone);
      } 
      else  if (name != null && !name.isEmpty())
      {
          listEmployees = UtilDBFolly.listContactsByName(name);
       } 
      else  if (phone != null && !phone.isEmpty())
      {
          listEmployees = UtilDBFolly.listContactsByPhone(phone);
       }  
      else 
      {
         listEmployees = UtilDBFolly.listContacts();
      }
      
      display(listEmployees, out);
      out.println("</ul>");
      out.println("<a href=/" + projectName + "/" + searchWebName + ">Search Contact</a> <br>");
      out.println("</body></html>");
      out.println("<br>");
      out.println("</ul>");
      out.println("<a href=/" + projectName + "/" + insertWebName + ">Insert Contact</a> <br>");
      out.println("</body></html>");
      out.println("<br>");
      out.println("<a href=/" +  util.Info.projectName  + "/" + util.Info.mainPageWebName + ">Main Page</a> <br>");
      out.println("</body></html>");
   }

   void display(List<Contact> listEmployees, PrintWriter out) {
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
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
