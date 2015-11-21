package childbreath.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weixin.utility.SignUtil;
import childbreath.service.WeixinMessageProcess;

/**
 * Servlet implementation class weixinServlet
 */
@WebServlet("/weixinEnter")
public class weixinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public weixinServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			String signature = request.getParameter("signature");
			String timestamp = request.getParameter("timestamp");
			String nonce = request.getParameter("nonce");
			String echostr = request.getParameter("echostr");
			
			if( signature == null || timestamp == null ||
					nonce == null || echostr == null ){
				response.getWriter().print("参数错误");
				return;
			}
			
			if( SignUtil.checkSignature(signature, timestamp, nonce)){
				response.getWriter().print(echostr);
			}
		}catch( Exception e ){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String respMessage = WeixinMessageProcess.ProcessRequest(request);
		
		PrintWriter out = response.getWriter();
		out.print( respMessage );
		out.close();
	}

}
