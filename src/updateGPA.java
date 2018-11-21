import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

@WebServlet(name="updateGPA",urlPatterns = {"/updateGPA"})
public class updateGPA extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // JDBC driver name and database URL
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL="jdbc:mysql://127.0.0.1:3306/faculty";

        //  Database credentials
        final String USER = "root";
        final String PASS = "root1234";

        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(DB_URL,USER,PASS);
            Statement st = con.createStatement();
            String studentID = request.getParameter("id");
            String sql = "SELECT * FROM STUDENT WHERE ID = '" +studentID+"';";
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                String studentName = rs.getString("sname");
                String address = rs.getString("address");
                float oldGpa = rs.getFloat("gpa");
                sql = "UPDATE STUDENT SET GPA = GPA + 0.05 * GPA WHERE id ='"+studentID+"';";
                st.executeUpdate(sql);
                sql = "SELECT * FROM STUDENT WHERE ID = '" +studentID+"';";
                rs = st.executeQuery(sql);
                rs.next();
                float newGpa = rs.getFloat("gpa");
                HttpSession session = request.getSession();
                session.setAttribute("id",studentID);
                session.setAttribute("name",studentName);
                session.setAttribute("address",address);
                session.setAttribute("oldGpa",oldGpa);
                session.setAttribute("newGpa",newGpa);
                response.sendRedirect("result.jsp");
            }else{
                out.print("No Such Student");
            }
        }catch (ClassNotFoundException e){
            System.out.println("Class not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL Error!");
            e.printStackTrace();
        }
    }
}
