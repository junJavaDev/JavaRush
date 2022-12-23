package com.tictactoe;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LogicServlet", value = "/logic")
public class LogicServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // Получаем текущую сессию
        HttpSession currentSession = req.getSession();

        // Получаем объект игрового поля из сессии
        Field field = extractField(currentSession);



        if (!isGameOver(currentSession)) {
            Sign playerSign = (Sign)currentSession.getAttribute("playerSign");

            Sign computerSign = playerSign == Sign.CROSS ? Sign.NOUGHT : Sign.CROSS;
            // получаем индекс ячейки, по которой произошел клик
            int index = getSelectedIndex(req);
            Sign currentSign = field.getField().get(index);

            // Проверяем, что ячейка, по которой был клик пустая.
            // Иначе ничего не делаем и отправляем пользователя на ту же страницу без изменений
            // параметров в сессии
            if (Sign.EMPTY != currentSign) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                dispatcher.forward(req, resp);
                return;
            }

            // ставим крестик в ячейке, по которой кликнул пользователь
            field.getField().put(index, playerSign);

            // Проверяем, не победил ли крестик после добавления последнего клика пользователя
            if (checkWin(req, resp, currentSession, field)) {
                return;
            }

            // Получаем пустую ячейку поля
            int emptyFieldIndex = ComputerLogic.getMove(field, playerSign);

            if (emptyFieldIndex >= 0) {
                field.getField().put(emptyFieldIndex, computerSign);
                // Проверяем, не победил ли нолик после добавление последнего нолика
                if (checkWin(req, resp, currentSession, field)) {
                    return;
                }
            }
            // Если пустой ячейки нет и никто не победил - значит это ничья
            if (ComputerLogic.getMove(field, playerSign) < 0) {
                draw(req, resp, currentSession, field);
                return;
            }
        }
        // Считаем список значков
        List<Sign> data = field.getFieldData();

        // Обновляем объект поля и список значков в сессии
        currentSession.setAttribute("data", data);
        currentSession.setAttribute("field", field);
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }

    private static void draw(HttpServletRequest req, HttpServletResponse resp, HttpSession currentSession, Field field) throws IOException {
        // Добавляем в сессию флаг, который сигнализирует что произошла ничья
        currentSession.setAttribute("draw", true);

        // Считаем список значков
        List<Sign> data = field.getFieldData();

        // Обновляем этот список в сессии
        currentSession.setAttribute("data", data);
        currentSession.setAttribute("field", field);
        currentSession.setAttribute("gameOver", true);
        // Шлем редирект
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }

    private boolean isGameOver(HttpSession currentSession) {
        Object o = currentSession.getAttribute("gameOver");
        return o != null && (boolean) o;
    }


    private Field extractField(HttpSession currentSession) {
        Object fieldAttribute = currentSession.getAttribute("field");
        if (Field.class != fieldAttribute.getClass()) {
            currentSession.invalidate();
            throw new RuntimeException("Session is broken, try one more time");
        }
        return (Field) fieldAttribute;
    }


    private int getSelectedIndex(HttpServletRequest request) {
        String click = request.getParameter("click");
        boolean isNumeric = click.chars().allMatch(Character::isDigit);
        return isNumeric ? Integer.parseInt(click) : 0;
    }

    /**
     * Метод проверяет, нет ли трех крестиков/ноликов в ряд.
     * Возвращает true/false
     */
    private boolean checkWin(HttpServletRequest req, HttpServletResponse response, HttpSession currentSession, Field field) throws IOException {
        Sign winner = field.checkWin();
        if (Sign.CROSS == winner || Sign.NOUGHT == winner) {
            // Добавляем флаг, который показывает что кто-то победил
            currentSession.setAttribute("winner", winner);

            // Считаем список значков
            List<Sign> data = field.getFieldData();

            // Обновляем этот список в сессии
            currentSession.setAttribute("data", data);

            // Шлем редирект
            currentSession.setAttribute("gameOver", true);
            response.sendRedirect(req.getContextPath() + "/index.jsp");
            return true;
        }
        return false;
    }




}
