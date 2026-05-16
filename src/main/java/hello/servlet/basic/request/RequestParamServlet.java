package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

/**
 * 1. パラメータ送信機能
 * http://localhost:8080/request-param?username=hello&age=20
 * 2. 同一パラメータ送信機能
 * http://localhost:8080/request-param?username=hello&username=kim&age=20
 * **/
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[全体パラメータ取得] - start");

//        Enumeration<String> parameterName = req.getParameterNames();
//        while (parameterName.hasMoreElements()) {
//            String paramName = parameterName.nextElement();
//            System.out.println(paramName + "=" + req.getParameter(paramName));
//        }

        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + "=" + req.getParameter(paramName)));
        System.out.println("[全体パラメータ取得] - end");
        System.out.println();

        System.out.println("[単一パラメータ取得]");
        String username = req.getParameter("username");
        System.out.println("req.getParameter(username) = " + username);

        String age = req.getParameter("age");
        System.out.println("req.getParameter(age) = " + age);
        System.out.println();

        System.out.println("[名称が同じの複数パラメータ取得]");
        System.out.println("req.getParameter(username)");
        String[] usernames = req.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("username = " + name);
        }

        resp.getWriter().write("ok");
    }
}
