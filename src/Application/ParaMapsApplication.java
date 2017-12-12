package Application;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;

import javax.swing.border.EtchedBorder;

public class ParaMapsApplication extends JFrame {
    Canvas c = new Canvas();
    ConnectorPropertiesPanel props;
    JScrollPane scrollTextArea;

    public ParaMapsApplication() {
        super("ParaMaps");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());
        init();

        getContentPane().add(new JLabel("Connectors example. You can drag the connected component to see how the line will be changed"),
                new GridBagConstraints(0, 0, 2, 1, 1, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
        getContentPane().add(initConnectors(),
                new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        getContentPane().add(props,
                new GridBagConstraints(1, 1, 1, 1, 0, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.VERTICAL, new Insets(5, 0, 5, 5), 0, 0));
        getContentPane().add(scrollTextArea,
                new GridBagConstraints(0, 2, 3, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 40));

        setSize(800, 800);
        setLocationRelativeTo(null);
    }

    protected void init() {
        ConnectLine[] lines = new ConnectLine[5];
        lines[0] = new ConnectLine(new Point(200, 10), new Point(50, 300), ConnectLine.LINE_TYPE_SIMPLE, ConnectLine.LINE_START_HORIZONTAL, ConnectLine.LINE_ARROW_BOTH);
        lines[1] = new ConnectLine(new Point(200, 10), new Point(200, 150), ConnectLine.LINE_TYPE_SIMPLE, ConnectLine.LINE_START_HORIZONTAL, ConnectLine.LINE_ARROW_BOTH);
        lines[2] = new ConnectLine(new Point(50, 150), new Point(100, 100), ConnectLine.LINE_TYPE_SIMPLE, ConnectLine.LINE_START_HORIZONTAL, ConnectLine.LINE_ARROW_BOTH);
        lines[3] = new ConnectLine(new Point(150, 120), new Point(60, 70), ConnectLine.LINE_TYPE_SIMPLE, ConnectLine.LINE_START_HORIZONTAL, ConnectLine.LINE_ARROW_BOTH);

        c.setLines(lines, Color.blue);

        initTextArea();
    }

    protected void initTextArea() {
        JTextArea textArea = new JTextArea("Enter input here");
        textArea.setSize(800, 200);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                textArea.setText(null); // Empty the text field when it receives focus
            }

            @Override
            public void focusLost(FocusEvent e) {
                // You could do something here when the field loses focus, if you like
            }

        });

        scrollTextArea = new JScrollPane(textArea);
        scrollTextArea.setSize(800,300);
        scrollTextArea.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    protected ConnectorContainer initConnectors() {
        JConnector[] connectors = new JConnector[2];

        JPanel head1 = new JPanel();
        head1.add(new JLabel("Saturn"));
        JPanel body1 = new JPanel();
        DefaultListModel<String> l1 = new DefaultListModel<>();
        l1.addElement("is a planet");
        l1.addElement("is large");
        JList<String> bodyList = new JList<>(l1);
        body1.add(bodyList);
        JSplitPane splitPane = new DraggableLabel(JSplitPane.VERTICAL_SPLIT, head1, body1);
        splitPane.setBounds(10, 10, 200, 150);

        JPanel head2 = new JPanel();
        head2.add(new JLabel("Planet"));
        JPanel body2 = new JPanel();
        DefaultListModel<String> l2 = new DefaultListModel<>();
        l2.addElement("is round");
        l2.addElement("may have rings");
        JList<String> bodyList2 = new JList<>(l2);
        body2.add(bodyList2);
        JSplitPane splitPane2 = new DraggableLabel(JSplitPane.VERTICAL_SPLIT, head2, body2);
        splitPane2.setBounds(300, 300, 200, 150);

        connectors[0] = new JConnector(splitPane, splitPane2, ConnectLine.LINE_ARROW_DEST, JConnector.CONNECT_LINE_TYPE_RECTANGULAR, Color.red);
        props = new ConnectorPropertiesPanel(connectors[0]);

        ConnectorContainer cc = new ConnectorContainer(connectors);
        cc.setLayout(null);

        cc.add(splitPane);
        cc.add(splitPane2);

        cc.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        return cc;
    }

    public static void main(String[] args) {
        ParaMapsApplication pm = new ParaMapsApplication();
        pm.setVisible(true);
    }

    //temp class to test lines drawing
    protected static class Canvas extends JPanel {
        ConnectLine[] lines;
        Color color;
        public void setLines(ConnectLine[] lines, Color color) {
            this.lines = lines;
            this.color = color;
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.white);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.black);
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

            g.setColor(color);
            for (int i = 0; i < lines.length; i++) {
                if (lines[i] != null) {
                    lines[i].paint( (Graphics2D) g);
                }
            }
        }
    }
}

