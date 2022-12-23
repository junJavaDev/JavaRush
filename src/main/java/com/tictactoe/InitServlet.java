package com.tictactoe;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet(name = "InitServlet", value = {"/start", ""})
public class InitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Создание новой сессии
        HttpSession currentSession = req.getSession(true);

        // Создание игрового поля
        Field field = new Field();

        Sign playerSign = getRandomSign();
        firstStep(playerSign, field);
        // Получение списка значений поля
        List<Sign> data = field.getFieldData();
        currentSession.setAttribute("playerSign", playerSign);
        // Добавление в сессию параметров поля (нужно будет для хранения состояния между запросами)
        currentSession.setAttribute("field", field);
        // и значений поля, отсортированных по индексу (нужно для отрисовки крестиков и ноликов)
        currentSession.setAttribute("data", data);
        // Перенаправление запроса на страницу index.jsp через сервер
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private Sign getRandomSign() {
        List<Sign> signs = List.of(Sign.CROSS, Sign.NOUGHT);
        return signs.get(ThreadLocalRandom
                .current()
                .nextInt(2));
    }

    private void firstStep(Sign playerSign, Field field) {
        if (playerSign == Sign.NOUGHT && field.isEmpty()) {
            field.getField().put(ComputerLogic.getMove(field, playerSign), Sign.CROSS);
        }
    }
}
