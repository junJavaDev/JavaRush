package ua.com.javarush.quest.ogarkov.filter;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ua.com.javarush.quest.ogarkov.settings.Setting;
import ua.com.javarush.quest.ogarkov.util.RepositoryLoader;

@WebListener
public class Listener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("S", Setting.get());
        RepositoryLoader.load();
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        RepositoryLoader.save();
    }


}
