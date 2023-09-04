package servlet;


import Connection.JavaConfiguration;
import Dao.EmpDao;
import Entity.Emp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException , IOException {

        String name = req.getParameter("name");
        String salary = req.getParameter("salary");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String department = req.getParameter("department");

        Emp emp = new Emp(name, department, salary, email, password);

        System.out.println(emp);

        EmpDao empDao = new EmpDao(JavaConfiguration.getSessionFactory());

        boolean f = empDao.saveEmp(emp);

        HttpSession session = req.getSession();

        if(f == true){
            session.setAttribute("msg", "Emp register successfully");
            System.out.println("Data inserted successfully");
        }
        else {
            session.setAttribute("msg", "something wrong");
            System.out.println("Something wrong");
        }

        resp.sendRedirect("index.jsp");

    }

}
