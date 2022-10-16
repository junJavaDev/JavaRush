package ua.com.javarush.quest.ogarkov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.ogarkov.service.StatisticsService;
import ua.com.javarush.quest.ogarkov.settings.Go;
import ua.com.javarush.quest.ogarkov.settings.Setting;
import ua.com.javarush.quest.ogarkov.util.Jsp;

import java.io.IOException;
import java.io.Serial;

@WebServlet(Go.STATISTICS)
public class StatisticServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 6316268318161580029L;
    private final StatisticsService statisticsService = StatisticsService.INSTANCE;
    private final Setting S = Setting.get();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        statisticsService
                .getStatistics()
                .fillRequest(req);
        Jsp.forward(req, resp, S.jspStatistics);
    }
}