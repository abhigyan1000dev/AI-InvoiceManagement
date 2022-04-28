package backendHRC.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HashMap<Object,Object>Response =new HashMap<Object,Object>();
		try {
			 
	            //System.out.println("Welcome");
	            int Sl_no = Integer.parseInt(request.getParameter("sl_no"));
	          //sl  int Cust_number = Integer.parseInt(request.getParameter("cust_number"));
	            //System.out.println("cust_number");
	            String Invoice_currency = request.getParameter("invoice_currency");
	            String Cust_payment_terms = request.getParameter("cust_payment_terms");
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grey_goose","root","abhi@2000");
	           
	            String sql = "UPDATE winter_internship SET invoice_currency = ?, cust_payment_terms = ? where sl_no = ?";
	            PreparedStatement ps = con.prepareStatement(sql);
//	            pojo data = new pojo();
	            //ps.setInt(1,Sl_no);
	            
	            PrintWriter out = response.getWriter();
	            
	          
	            ps.setString(1,Invoice_currency);
	            out.println(Invoice_currency);
	            ps.setString(2,Cust_payment_terms);
	            out.println(Cust_payment_terms);
	            
	            ps.setInt(3,Sl_no);
	            out.println(Sl_no);
	          //  ps.setString(3, data.getCust_number());
	          //  System.out.println(ps);
	            int k=ps.executeUpdate();
	            out.println("bruh");
	            out.println(ps);
	            out.println(ps.executeUpdate());
	            if(k>0) {
	            	out.println("bruh if");
	              Response.put("update", true);
	              
	            }else {
	            	out.println("bruh else");
	              Response.put("update", false);
	              
	            }
	            Gson gson = new Gson();
	            response.setHeader("Access-Control-Allow-Origin", "*");
	            String Responsejson = gson.toJson(Response);
	            response.getWriter().append(Responsejson);				
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
