package com.zzy.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zzy.model.UserInfo;



/**
 * 用于chatroom
 * @author Administrator
 * 
 */
public class Messages extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4923827734065058552L;

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		String action = request.getParameter("action");
		if ("loginRoom".equals(action)) { // 登录时，写入系统公告
			this.loginRoom(request, response);
		} else if ("sendMessage".equals(action)) { // 发送聊天信息
			this.sendMessages(request, response);
		} else if ("getMessages".equals(action)) { // 从XML文件中读取聊天信息
			this.getMessages(request, response);
		}
	}

	/**
	 * 将页面重定向到显示聊天信息的页面
	 * 
	 * @param request
	 * @param response
	 */
	public void getMessages(HttpServletRequest request,
			HttpServletResponse response) {

		response.setContentType("text/html;charset=UTF-8");
		
		try {

			request.getRequestDispatcher("/webjsp/chatroom/content.jsp").forward(request,
					response);

		} catch (Exception ex) {
			Logger.getLogger(Messages.class.getName()).log(Level.SEVERE, null,
					ex);
		}

	}

	/**
	 * 登录时，写入系统公告
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void loginRoom(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		// session.setMaxInactiveInterval(600); // 设置Session的过期时间为10分钟

		String username = request.getParameter("username"); // 获得登录用户名
		UserInfo user = UserInfo.getInstance(); // 获得UserInfo类的对象
		@SuppressWarnings("rawtypes")
		Vector vector = user.getList();

		boolean flag = true; // 标记是否登录的变量

		// 判断用户是否登录
		System.out.println("vector的size：" + vector.size());
		if (vector != null && vector.size() > 0) {

			for (int i = 0; i < vector.size(); i++) {

				System.out.println("vector" + i + ":" + vector.elementAt(i)
						+ " user:" + username);
				if (username.equals(vector.elementAt(i))) {

					PrintWriter out;
					try {
						out = response.getWriter();
						
						
						out.println("<script language='javascript'>alert('该用户已经登录');window.location.href='/webjsp/chatroom/index.jsp';</script>");
					} catch (IOException e) {
						e.printStackTrace();
					}
					flag = false;
					break;
				}
			}
		}

		// 保存用户信息
		if (flag) {

			UserListener ul = new UserListener(); // 创建UserListener的对象
			ul.setUser(username); // 添加用户

			user.addUser(ul.getUser()); // 添加用户到UserInfo类的对象中
			session.setAttribute("user", ul); // 将UserListener对象绑定到Session中
			session.setAttribute("username", username); // 保存当前登录的用户名
			session.setAttribute("loginTime", new Date()); // 保存登录时间

			ServletContext application = getServletContext();
			String sourceMessage = "";
			if (null != application.getAttribute("message")) {
				sourceMessage = application.getAttribute("message").toString();
			}
			sourceMessage += "系统公告：<font color='gray'>" + username
					+ "走进了聊天室！</font><br>";
			application.setAttribute("message", sourceMessage);

			
			
			
			try {
				request.getRequestDispatcher("/webjsp/chatroom/login_ok.jsp").forward(request,
						response);
			} catch (Exception ex) {
				Logger.getLogger(Messages.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}
	}

	// 发送聊天信息
	public void sendMessages(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		Random random = new Random();
		
		String from = request.getParameter("from"); // 发言人
		String face = request.getParameter("face"); // 表情
		String to = request.getParameter("to"); // 接收者
		String color = request.getParameter("color"); // 字体颜色
		String content = request.getParameter("content"); // 发言内容
		String sendTime = new Date().toLocaleString(); // 发言时间
		
		ServletContext application = getServletContext();
		String sourceMessage = application.getAttribute("message").toString();
		
		try {

			sourceMessage += "<font color='blue'><strong>" + from
					+ "</strong></font><font color='#CC0000'>" + face
					+ "</font>对<font color='green'>[" + to + "]</font>说："
					+ "<font color='" + color + "'>" + content + "</font>（"
					+ sendTime + "）<br>";
			
			application.setAttribute("message", sourceMessage);
			request.getRequestDispatcher(
					"Messages?action=getMessages&nocache="
							+ random.nextInt(10000)).forward(request, response);
		} catch (Exception ex) {
			Logger.getLogger(Messages.class.getName()).log(Level.SEVERE, null,
					ex);
		}

	}

	/**
	 * Handles the HTTP <code>GET</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 * 
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}
}
