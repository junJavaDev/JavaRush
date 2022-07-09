package temp;// Обычно вам потребуются пакеты swing и awt,
// даже если вы работаете только с Swing.
import javax.swing.*;
import java.awt.*;
class MiniCalc {
    public static void main(String args[]) {

        // Создание каркаса
        JFrame frame = new JFrame("Chat Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize( 600, 600);

        // Создание панели меню и добавление компонентов
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Открыть");
        JMenuItem m22 = new JMenuItem("Сохранить как");
        m1.add(m11);
        m1.add(m22);

        // Создание панели внизу и добавление компонентов
        JPanel panel = new JPanel(); // панель не видна при выводе
        JLabel label = new JLabel("Заменить символ");
        JTextField from = new JTextField(2); // принимает до 1 символов
        JLabel label2 = new JLabel("На символ");
        JTextField to = new JTextField(2); // принимает до 1 символов
        JButton send = new JButton("Отправить");
        JButton reset = new JButton("Сброс");
        panel.add(label); // Компоненты, добавленные с помощью макета Flow Layout
        panel.add(from);
        panel.add(label2);
        panel.add(to);
        panel.add(send);
        panel.add(reset);

        // Текстовая область по центру
        JTextArea ta = new JTextArea("fggf");
        ta.setLineWrap(true);
        ta.setEditable(false);
        ta.setFont(new Font("Font", Font.PLAIN,20));
        JScrollPane scrollPane = new JScrollPane(ta);
        scrollPane.setViewportView(ta);


        // Добавление компонентов в рамку.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, scrollPane);
        frame.setVisible(true);


    }
}