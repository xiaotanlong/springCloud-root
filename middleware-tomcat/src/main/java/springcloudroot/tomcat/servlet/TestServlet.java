package springcloudroot.tomcat.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 0217319
 * @version V1.0
 * @Description: 启动一个Servlet
 * @date
 */
public class TestServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response)
            throws IOException, ServletException
    {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\" />");
        out.println("<title></title>");
        out.println("</head>");
        out.println("<body bgcolor=\"white\">");
        out.println("<h1> test a app !</h1>");
        out.println("</body>");
        out.println("</html>");
    }
}
