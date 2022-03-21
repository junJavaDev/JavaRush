package ru.javarush.ogarkov.cryptoanalizer.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Editor {
    private static final Object lock = new Object();
    private static final JFrame frame = new JFrame("Замена символов");
    String replacingSymbols;
    JTextArea textArea;
    JScrollPane scrollPane;
    Font font;
    JPanel panel;
    JLabel replaceFirstLabel, replaceSecondLabel, lineSeparatorLabel;
    JTextField replaceFirst, replaceSecond;
    JButton replace;
    JButton complete;

    public void createJFrame() {
        frame.setLocation(300, 200);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
        font = new Font("Font", Font.PLAIN, 20);
        textArea = new JTextArea();
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setFont(font);
        scrollPane = new JScrollPane(textArea);
        scrollPane.setViewportView(textArea);
        replaceFirstLabel = new JLabel("Заменить символ");
        replaceSecondLabel = new JLabel("На символ");
        lineSeparatorLabel = new JLabel("  \\n - Символ переноса строки");
        replaceFirst = new JTextField(1);
        replaceFirst.setFont(font);
        replaceSecond = new JTextField(1);
        replaceSecond.setFont(font);
        replace = new JButton("Заменить");
        complete = new JButton("Выход");

        // добавить элементы на панель
        panel = new JPanel();
        panel.add(replaceFirstLabel);
        panel.add(replaceFirst);
        panel.add(replaceSecondLabel);
        panel.add(replaceSecond);
        panel.add(replace);
        panel.add(complete);
        panel.add(lineSeparatorLabel);
        // Текстовая область по центру



        replace.addActionListener(e -> {
            String firstSymbol = replaceFirst.getText();
            String secondSymbol = replaceSecond.getText();
            if (firstSymbol.equals("\\n")) {
                firstSymbol = "\n";
            }
            if (secondSymbol.equals("\\n")) {
                secondSymbol = "\n";
            }
            replacingSymbols = firstSymbol + secondSymbol;
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