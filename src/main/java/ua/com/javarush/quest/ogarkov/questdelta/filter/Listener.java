package ua.com.javarush.quest.ogarkov.questdelta.filter;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;
import ua.com.javarush.quest.ogarkov.questdelta.util.RepositoryLoader;

@WebListener
public class Listener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        RepositoryLoader.load();
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("s", Setting.get());
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
//        RepositoryLoader.save();
    }


}
