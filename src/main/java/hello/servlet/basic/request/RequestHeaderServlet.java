package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

//http://localhost:8080/request-heder?usernmae=hello
@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        printStartLine(request);
        printHeaders(request);
        printHeaderUtills(request);
        printEtc(request);
    }

    private void printStartLine(HttpServletRequest request) {
        System.out.println("--- REQUEST-LINE - start ---");

        System.out.println("request.getMethod() = " + request.getMethod()); //GET
        System.out.println("requset.getProtocol() = " + request.getProtocol()); //HTTP/1.1
        System.out.println("request.getScheme() = " + request.getScheme()); //http
        System.out.println("request.getRequestURI() = " + request.getRequestURI()); // http://localhost:8080/request-header
        System.out.println("request.getRequestURI() = " + request.getRequestURI()); // /request-header
        System.out.println("request.getQueryString() = " + request.getQueryString()); // /username=hi
        System.out.println("request.isSecure() = " + request.isSecure()); //https 使用有無
        System.out.println("--- REQUEST-LINE - end ---");
        System.out.println();
    }

    private void printHeaders(HttpServletRequest request) {
        System.out.println("--- Headers - start ---");

        /*
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println(headerName + ": " + request.getHeader(headerName));
        */

        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> System.out.println(headerName + ": " + request.getHeader(headerName)));
        System.out.println("--- Headers - end ---");
        System.out.println();
    }

    private void printHeaderUtills(HttpServletRequest request) {
        System.out.println("--- Header 便意紹介　start ---");
        System.out.println("[Host 便意照会]");
        System.out.println("request.getServerName() = " + request.getServerName()); //Host Header
        System.out.println("request.getServerPort() = " + request.getServerPort()); //Host Header
        System.out.println();

        System.out.println("[Accept-Language 便意照会]");
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " + locale));
        System.out.println("request.getLocale() = " + request.getLocale());
        System.out.println();

        System.out.println("[cookie 便意照会]");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        System.out.println();

        System.out.println("[Content便意照会]");
        System.out.println("request.getContentType() = " + request.getContentType());
        System.out.println("request.getContentType() = " + request.getContentType());
        System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());

        System.out.println("--- Header 便意照会 end ---");
        System.out.println();
    }

    //その他
    private void printEtc(HttpServletRequest request) {
        System.out.println("--- その他　照会 start ---");

        System.out.println("[Remote 情報]");
        System.out.println("request.getRemoteHost() = " + request.getRemoteHost());
        System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr());
        System.out.println("request.getRemotePort() = " + request.getRemotePort());
        System.out.println();

        System.out.println("[Local 情報]");
        System.out.println("request.getLocalName() = " + request.getLocalName());
        System.out.println("request.getLocalAddr() = " + request.getLocalAddr());
        System.out.println("request.getLocalPort() = " + request.getLocalPort());

        System.out.println("--- その他照会 end ---");
        System.out.println();
    }
}
