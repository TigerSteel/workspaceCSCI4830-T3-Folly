import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Info;
import util.UtilDBFolly;

@WebServlet("/InsertFolly")
public class InsertFolly extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public InsertFolly() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String userName = request.getParameter("userName").trim();
      String phone = request.getParameter("phone").trim();
      String address = request.getParameter("address").trim();
      String email = request.getParameter("email").trim();
      String notes = request.getParameter("notes").trim();
      
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      
      if(userName != null && !userName.isEmpty())
      {
    	  if( (phone != null && !phone.isEmpty()) ||
    			  (address != null && !address.isEmpty()) ||  
    					  (email != null && !email.isEmpty())  )
          {
		      UtilDBFolly.createContact(userName, phone, address, email, notes);
		
		    //  response.setContentType("text/html");
		   //   PrintWriter out = response.getWriter();
		      String title = "Database Result";
		      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
		      out.println(docType + //
		            "<html>\n" + //
		            "<head><title>" + title + "</title></head>\n" + //
		            "<body bgcolor=\"#f0f0f0\">\n" + //
		            "<h1 align=\"center\">" + title + "</h1>\n");
		      out.println("<ul>");
		      out.println("<li> Name: " + userName);
		      out.println("<li> Phone: " + phone);
		      out.println("<li> Address: " + address);
		      out.println("<li> Email: " + email);
		      out.println("<li> Notes: " + notes);
		      out.println("</ul>");
		      
          } 
    	  else
    	  {
    		  String title = "ERROR DETECTED!... All these 3 Fields are Empty";
    		  String error = "Phone Number | Address | Email -- At Least 1 is Required!";
    	      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
    	      out.println(docType + //
    	            "<html>\n" + //
    	            "<head><title>" + title + "</title></head>\n" + //
    	            "<body bgcolor=\"#f0f0f0\">\n" + //
    	            "<h1 align=\"center\">" + title + "</h1>\n");
    	      out.println(docType + //
      	            "<html>\n" + //
      	            "<head><title>" + error + "</title></head>\n" + //
      	            "<body bgcolor=\"#f0f0f0\">\n" + //
      	            "<h1 align=\"center\">" + error + "</h1>\n");
    	      out.println("<ul>");
    	  }
      } 
      else
      { 
	      String title = "ERROR DETECTED!... Name Field is Empty";
	      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
	      out.println(docType + //
	            "<html>\n" + //
	            "<head><title>" + title + "</title></head>\n" + //
	            "<body bgcolor=\"#f0f0f0\">\n" + //
	            "<h1 align=\"center\">" + title + "</h1>\n");
	      out.println("<ul>");
      }
      
      out.println("</ul>");
      out.println("<a href=/" + projectName + "/" + insertWebName + ">Insert Contact</a> <br>");
      out.println("</body></html>");
      out.println("<br>");
      out.println("<a href=/" + projectName + "/" + searchWebName + ">Search Contact</a> <br>");
      out.println("</body></html>");
      out.println("<br>");
      out.println("<a href=/" +  util.Info.projectName  + "/" + util.Info.mainPageWebName + ">Main Page</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
