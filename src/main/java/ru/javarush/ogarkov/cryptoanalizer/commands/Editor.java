package ru.javarush.ogarkov.cryptoanalizer.commands;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Editor {
    private static final Object lock = new Object();
    private static final JFrame frame = new JFrame();
    String replacingSymbols;
    JTextArea textArea;
    JScrollPane scrollPane;
    Font font;
    JPanel panel;
    JLabel replaceFirstLabel, replaceSecondLabel;
    JTextField replaceFirst, replaceSecond;
    JButton replace;
    JButton complete;

    public void createJFrame() {
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        font = new Font("Font", Font.PLAIN, 20);
        panel = new JPanel();
        replaceFirstLabel = new JLabel("Заменить символ");
        replaceSecondLabel = new JLabel("На символ");
        replaceFirst = new JTextField(1);
        replaceSecond = new JTextField(1);
        replace = new JButton("Заменить");
        complete = new JButton("Выход");
        frame.setLocation(300, 200);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
        replaceFirst.setFont(font);
        replaceSecond.setFont(font);
        // добавить элементы на панель
        panel.add(replaceFirstLabel);
        panel.add(replaceFirst);
        panel.add(replaceSecondLabel);
        panel.add(replaceSecond);
        panel.add(replace);
        panel.add(complete);
        // Текстовая область по центру
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setFont(font);
        scrollPane.setViewportView(textArea);

        replace.addActionListener(e -> {
            replacingSymbols = "" + replaceFirst.getText() + replaceSecond.getText();
            replaceFirst.setText("");
            replaceSecond.setText("");
            synchronized (lock) {
                frame.setVisible(false);
                lock.notify();
            }
        });

        complete.addActionListener(e -> {
            replacingSymbols = "complete";
            synchronized (lock) {
                frame.setVisible(false);
                lock.notify();
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                replacingSymbols = "complete";
                synchronized (lock) {
                    frame.setVisible(false);
                    lock.notify();
                }
            }
        });

        // Добавление компонентов в рамку.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, scrollPane);
        frame.setVisible(true);


    }

    public String edit(String decodedFragment) {
        textArea.setText(decodedFragment);
        frame.setVisible(true);
        Thread t = new Thread(() -> {
            synchronized (lock) {
                while (frame.isVisible())
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return replacingSymbols;
    }
}