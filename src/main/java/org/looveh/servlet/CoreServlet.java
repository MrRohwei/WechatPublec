package org.looveh.servlet;

import org.looveh.service.CoreService;
import org.looveh.util.WeChatUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Create with IDEA
 * User:Looveh
 * Date:18/9/26
 * Time:上午9:43
 * Desc:
 */

public class CoreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");

        PrintWriter out = resp.getWriter();
        if(WeChatUtil.checkSignature(signature,timestamp,nonce)){
            out.print(echostr);
        }
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String respMessage = CoreService.processRequest(req);

        PrintWriter out = resp.getWriter();
        out.print(respMessage);
        out.close();
    }
}
