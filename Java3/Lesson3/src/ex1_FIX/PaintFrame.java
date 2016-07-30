package ex1_FIX;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.*;

/**
 * Created by Vladislav Gasanov on 21.07.2016.
 * FIXED by Alex Vasilenko on 25.07.2016
 */
public class PaintFrame extends JFrame {
    Color color = Color.black;
    //FIXME: Реализовать сохранение цвета нарисованных линий.
//    List<Line2D.Float> lines = new ArrayList<>();
    //Fix here, added Map
    Map<Line2D.Float, Color> linesColors = new HashMap<>();
    DrawPanel panel;
    Point lastPoint;

    public PaintFrame(String title) throws HeadlessException {
        super(title);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(new BorderLayout());
        panel = new DrawPanel();
        DrawListener drawListener = new DrawListener();
        panel.addMouseListener(drawListener);
        panel.addMouseMotionListener(drawListener);
        add(panel);
        add(new ButtonPanel(),BorderLayout.NORTH);
    }

    private class DrawPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = ((Graphics2D) g);

            //Fixed here
            Set<Line2D.Float> setLines = linesColors.keySet();
            for(Line2D.Float line : setLines){
                g2d.setColor(linesColors.get(line));
                g2d.draw(line);
            }

//            g2d.setColor(color);
//            for (Line2D.Float line: lines) {
//                g2d.draw(line);
//            }
        }
    }

    private class ButtonPanel extends JPanel implements ActionListener {
        private Map<JButton, Color> jButtonColorMap = new HashMap<>();
//        public static final String RED = "red";
//        public static final String GREEN = "green";
//        public static final String BLUE = "blue";
//        public static final String BLACK = "black";
        JButton btnRed;
        JButton btnOrange;
        JButton btnYellow;
        JButton btnGreen;
        JButton btnBlue;
        JButton btnMagenta;
        JButton btnBlack;

        public ButtonPanel() {
            //FIXME: исправить так, чтобы не приходилось дублировать указание цвета
            initButton(btnRed, Color.RED);
            initButton(btnOrange, Color.ORANGE);
            initButton(btnYellow, Color.YELLOW);
            initButton(btnGreen, Color.GREEN);
            initButton(btnBlue, Color.BLUE);
            initButton(btnMagenta, Color.magenta);
            initButton(btnBlack, Color.BLACK);
        }


        private  void initButton(JButton button, Color color ){
            button = new JButton();
            button.setBackground(color);
            button.setOpaque(true);
            button.setBorderPainted(false);
            //Fixed here
            button.setActionCommand(color.toString());
            button.addActionListener(this);
            jButtonColorMap.put(button,color);
            add(button);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //FIXME: реализовать вместо switch, Map
            Set<JButton> buttons = jButtonColorMap.keySet();
            for (JButton button: buttons){
                if(e.getActionCommand().equals(jButtonColorMap.get(button).toString())){
                    color = jButtonColorMap.get(button);
                    break;
                }
            }
//            switch (e.getActionCommand()){
//                case RED:
//                    color = Color.RED;
//                    break;
//                case GREEN:
//                    color = Color.GREEN;
//                    break;
//                case BLUE:
//                    color = Color.BLUE;
//                    break;
//                case BLACK:
//                    color = Color.BLACK;
//                    break;
//            }
            System.out.println("You changed color.");
        }
    }

    private class DrawListener extends MouseAdapter{
        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
            Point point = e.getPoint();
            if (lastPoint == null) {
                lastPoint = point;
                return;
            }
            linesColors.put(new Line2D.Float(lastPoint, point), color);
            lastPoint = point;
            panel.repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            lastPoint = null;
        }
    }

    public static void main(String[] args) {
        PaintFrame paintFrame = new PaintFrame("My Super Paint");
        paintFrame.setVisible(true);
    }
}
