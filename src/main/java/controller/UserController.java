package controller;
//гет и пост запросы

import dao.UserDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static util.ActionEnum.*;

public class UserController extends HttpServlet {
    private static final String LIST_USER = "listUser.jsp";
    private static final String EDIT_USER = "editUser.jsp";

    UserDao userDao;

    public UserController() {
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String view = "";
        if (action.equalsIgnoreCase(LIST.name())) {
            req.setAttribute("users", userDao.listUser()); //любое название переменной
            view = LIST_USER;
        } else if (action.equalsIgnoreCase(CREATE.name())) {
            view = EDIT_USER;
        } else if (action.equalsIgnoreCase(EDIT.name())) {
            req.setAttribute("user", userDao.getUserById(Integer.parseInt(req.getParameter("id"))));
            view = EDIT_USER;
        } else if (action.equalsIgnoreCase(DELETE.name())) {
            userDao.deleteUser(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("users", userDao.listUser());
            view = LIST_USER;
        }
        req.getRequestDispatcher(view).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setFirstName(req.getParameter("firstname"));
        user.setEmail(req.getParameter("email"));
        user.setLastName(req.getParameter("lastname"));

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("dob"));
            user.setDob(date);
        } catch (ParseException e) {
            resp.sendError(400, "Invalid date");
            return;
        }

        String id = req.getParameter("id");
        if (id == null || id.isEmpty()) {
            userDao.addUser(user);
        } else {
            user.setId(Integer.parseInt(id));
            userDao.editUser(user);
        }

        req.setAttribute("users",userDao.listUser());
        req.getRequestDispatcher(LIST_USER).forward(req, resp);

    }
}
