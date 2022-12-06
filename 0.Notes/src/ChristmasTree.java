import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import javax.imageio.ImageIO;

public class ChristmasTree {
    static class Point {
        Point(int a, int b) {
            x = a;
            y = b;
        }

        int x, y;

        double distance(Point o) {
            int dx = x - o.x;
            int dy = y - o.y;
            return Math.sqrt(dx * dx + dy * dy);
        }
    }

    static int size;
    static BufferedImage b;
    static Random r = new Random();

    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            size = 20;
        } else {
            size = Integer.parseInt(args[0]);
        }
        b = new BufferedImage(20 * size, 30 * size, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) b.getGraphics();
        g.setColor(new Color(140, 70, 20));
        int h = b.getHeight();
        h *= 0.4;
        for (int i = (int) (0.4 * b.getWidth()); i < (int) (0.6 * b.getWidth()); i++) {
            if (r.nextDouble() < 0.3) {
                g.drawLine(i, b.getHeight(), i + r.nextInt(2) - 1, h);
            }
        }
        for (int i = h; i < b.getHeight(); i++) {
            if (r.nextDouble() < 0.3) {
                g.drawLine((int) (0.4 * b.getWidth()), i, (int) (0.6 * b.getWidth()), i);
            }
        }
        for (int i = 0; i < size; i++) {
            g.setColor(new Color(r.nextInt(4), 150 + r.nextInt(15), 20 + r.nextInt(7)));
            g.drawLine(b.getWidth() / 2 - (int) (b.getWidth() * 0.42 * i / size), (int) (b.getHeight() * 0.9) + r.nextInt(5) - 2, b.getWidth() / 2 + (int) (b.getWidth() * 0.42 * i / size), (int) (b.getHeight() * 0.9) + r.nextInt(5) - 2);
            g.setColor(new Color(r.nextInt(4), 150 + r.nextInt(15), 20 + r.nextInt(7)));
            g.drawLine(b.getWidth() / 2 - (int) (b.getWidth() * 0.42 * i / size), (int) (b.getHeight() * 0.9), b.getWidth() / 2, (int) (b.getHeight() * (0.1 + (.06 * i) / size)));
            g.setColor(new Color(r.nextInt(4), 150 + r.nextInt(15), 20 + r.nextInt(7)));
            g.drawLine(b.getWidth() / 2 + (int) (b.getWidth() * 0.42 * i / size), (int) (b.getHeight() * 0.9), b.getWidth() / 2, (int) (b.getHeight() * (0.1 + (.06 * i) / size)));
        }
        g.setColor(new Color(150, 120, 40));
        g.fillOval((b.getWidth() - size - 2) / 2, b.getHeight() / 10, size + 2, size + 2);
        g.setColor(new Color(250, 240, 80));
        g.fillOval((b.getWidth() - size) / 2, b.getHeight() / 10, size, size);
        List<Color> c = Arrays.asList(new Color(0, 255, 0), new Color(255, 0, 0), new Color(130, 0, 100), new Color(0, 0, 200), new Color(110, 0, 200), new Color(200, 205, 210), new Color(0, 240, 255), new Color(255, 100, 0));
        List<Point> pts = new ArrayList<>();
        pts.add(new Point((b.getWidth() - size) / 2, b.getHeight() / 10));
        loop:
        for (int i = 0; i < 8 + size / 4; i++) {
            int y = r.nextInt((8 * b.getHeight()) / 11);
            int x = 1 + (int) (b.getWidth() * 0.35 * y / ((8 * b.getHeight()) / 11));
            x = r.nextInt(2 * x) - x + b.getWidth() / 2;
            y += b.getHeight() / 8;
            g.setColor(c.get(r.nextInt(c.size())));
            x -= size / 2;
            Point p = new Point(x, y);
            for (Point q : pts) {
                if (q.distance(p) < 1 + 2 * size) continue loop;
            }
            pts.add(p);
            g.fillOval(x, y, size, size);
        }
        File file = new File("tree.png");
        ImageIO.write(b, "png", file);
    }
}