package ua.com.javarush.quest.ogarkov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.javarush.quest.ogarkov.service.StatisticsService;
import ua.com.javarush.quest.ogarkov.settings.Go;
import ua.com.javarush.quest.ogarkov.settings.Setting;
import ua.com.javarush.quest.ogarkov.util.Jsp;
import ua.com.javarush.quest.ogarkov.util.Parser;

import java.io.IOException;
import java.io.Serial;

@WebServlet(Go.STATISTICS)
public class StatisticServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 6316268318161580029L;
    private final StatisticsService statisticsService = StatisticsService.INSTANCE;
    private final Setting S = Setting.get();
    private static final Logger log = LoggerFactory.getLogger(StatisticServlet.class);

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        log.info("Open page: {}, userID: {}", Go.STATISTICS, Parser.userId(req));
        statisticsService
                .getStatistics()
                .fillRequest(req);
        Jsp.forward(req, resp, S.jspStatistics);
    }
}