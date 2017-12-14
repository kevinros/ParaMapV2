package Application;

import Box_Tools.Body;
import Box_Tools.Box;
import Box_Tools.Head;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.*;

public class DraggableMapNode extends JSplitPane {
    Point pressPoint;
    Point releasePoint;
    DragProcessor dragProcessor = new DragProcessor();
    JPanel head;
    JPanel body;

    public DraggableMapNode(JPanel head, JPanel body) {
        super(JSplitPane.VERTICAL_SPLIT, head, body);
        super.setBounds(getRandomInteger(100, 400), getRandomInteger(100, 400), 200, 150);
        setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EmptyBorder(0, 5, 0, 5)));
        addMouseListener(dragProcessor);
        addMouseMotionListener(dragProcessor);
        this.head = head;
        this.body = body;
    }

    public String getHead() {
        String head = ((JLabel) this.head.getComponent(0)).getText();
        return head;
    }

    private int getRandomInteger(int lowerBound, int upperBound) {
        Random r = new Random();
        int result = r.nextInt(upperBound - lowerBound) + lowerBound;
        return result;
    }

    protected class DragProcessor extends MouseAdapter implements MouseListener, MouseMotionListener {
        Window dragWindow = new JWindow() {
            public void paint(Graphics g) {
                super.paint(g);
                DraggableMapNode.this.paint(g);
            }
        };
        public void mouseDragged(MouseEvent e) {
            Point dragPoint = e.getPoint();
            int xDiff = pressPoint.x - dragPoint.x;
            int yDiff = pressPoint.y - dragPoint.y;

            Rectangle b = e.getComponent().getBounds();
            Point p = b.getLocation();
            SwingUtilities.convertPointToScreen(p, e.getComponent().getParent());
            p.x -= xDiff;
            p.y -= yDiff;

            dragWindow.setLocation(p);
        }

        public void mouseMoved(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
            pressPoint = e.getPoint();
            Rectangle b = e.getComponent().getBounds();
            Point p = b.getLocation();
            SwingUtilities.convertPointToScreen(p, e.getComponent().getParent());
            dragWindow.setBounds(b);
            dragWindow.setLocation(p);
            dragWindow.setVisible(true);
        }

        public void mouseReleased(MouseEvent e) {
            releasePoint = e.getPoint();
            dragWindow.setVisible(false);

            int xDiff = pressPoint.x - releasePoint.x;
            int yDiff = pressPoint.y - releasePoint.y;

            Rectangle b = e.getComponent().getBounds();
            Point p = b.getLocation();
            SwingUtilities.convertPointToScreen(p, e.getComponent().getParent());
            p.x -= xDiff;
            p.y -= yDiff;

            SwingUtilities.convertPointFromScreen(p, DraggableMapNode.this.getParent());
            if (p.x <= 0) {
                p.x = 1;
            }
            if (p.x > DraggableMapNode.this.getParent().getWidth() - b.width) {
                p.x = DraggableMapNode.this.getParent().getWidth() - b.width;
            }
            if (p.y <= 0) {
                p.y = 1;
            }
            if (p.y > DraggableMapNode.this.getParent().getHeight() - b.height) {
                p.y = DraggableMapNode.this.getParent().getHeight() - b.height;
            }
            setLocation(p);
            getParent().repaint();
        }
    }

}
