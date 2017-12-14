package Application;

import Box_Tools.Box;
import Box_Tools.ParaMap;
import Factory.MapBuilder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import javax.swing.*;

import javax.swing.border.EtchedBorder;

public class ParaMapsApplication extends JFrame {
    ConnectorContainer cc;
    ConnectorPropertiesPanel props;
    JTextArea textArea;
    JScrollPane scrollTextArea;
    JButton generateButton;
    String userInput;
    MapBuilder mapBuilder;
    ParaMap map;

    public ParaMapsApplication() throws Exception {
        super("ParaMaps");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());
        init();

        getContentPane().add(new JLabel("Type in sentences and press Generate to create a ParaMap."),
                new GridBagConstraints(0, 0, 2, 1, 1, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
        getContentPane().add(cc,
                new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        getContentPane().add(props,
                new GridBagConstraints(1, 1, 1, 1, 0, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.VERTICAL, new Insets(5, 0, 5, 5), 0, 0));
        getContentPane().add(scrollTextArea,
                new GridBagConstraints(0, 2, 3, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 40));
        getContentPane().add(generateButton,
                new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        setSize(1200, 800);
        setLocationRelativeTo(null);
    }

    protected void init() throws Exception {

        initTextArea();
        initGenerateButton();
        initDefaultState();
    }

    protected void initTextArea() {
        textArea = new JTextArea("Enter input here");
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

    protected void initGenerateButton() throws Exception {
        generateButton = new JButton("Generate");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    userInput = textArea.getText();
                    mapBuilder = new MapBuilder(userInput);
                    map = mapBuilder.buildMap();
                    generateMap();
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }
        });

    }

    protected void initDefaultState() {
        ArrayList<JConnector> connectors = new ArrayList<>();

        props = new ConnectorPropertiesPanel(connectors);

        this.cc = new ConnectorContainer(connectors);
        this.cc.setLayout(null);
        this.cc.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

    }

    protected void generateMap() {
        // Remove the container first instead of pasting a new one on top of it
        getContentPane().remove(cc);

        // remove contents of map first
        cc.removeAll();
        cc.updateUI();

        // connectors will keep track of all the connections/lines
        ArrayList<JConnector> connectors = new ArrayList<>();

        // mapNodes and mapNodeHeads are directly related; that is, the index of mapNodes corresponds to
        //   the index of mapNodeHeads. This is how we will reference and check if mapNodes already exist.
        //   Example: the head that mapNodeHeads.get(1) references is the same as mapNodes.get(1)'s head.
        ArrayList<DraggableMapNode> mapNodes = new ArrayList<>();
        ArrayList<String> mapNodeHeads = new ArrayList<>();

        for (Box box : map.getMap()) {

            DraggableMapNode newMapNode = null;

            // if we have not created the map node for the given box yet, create it, add it to the
            //   node list, and add its head to the head list.
            if (!(mapNodeHeads.contains(box.getHead()))) {
                newMapNode = createMapNode(box);
                mapNodes.add(newMapNode);
                mapNodeHeads.add(box.getHead());
            }
            // else the map node already exists and we just need to reference it via the head list index.
            else {
                int headIndex = mapNodeHeads.indexOf(box.getHead());
                newMapNode = mapNodes.get(headIndex);
            }

            for (Box connectedBox : box.getBoxConnections()) {

                // if we have not created a map node for the connected box, we'll create it first
                //   then set the link
                if (!(mapNodeHeads.contains(connectedBox.getHead()))) {
                    DraggableMapNode newMapNode2 = createMapNode(connectedBox);
                    mapNodes.add(newMapNode2);
                    mapNodeHeads.add(connectedBox.getHead());
                    connectors.add(new JConnector(newMapNode, newMapNode2, ConnectLine.LINE_ARROW_DEST, JConnector.CONNECT_LINE_TYPE_RECTANGULAR, Color.red));
                }
                // else the map node already exists
                else {
                    int headIndex = mapNodeHeads.indexOf(connectedBox.getHead());
                    DraggableMapNode existingMapNode = mapNodes.get(headIndex);
                    connectors.add(new JConnector(newMapNode, existingMapNode, ConnectLine.LINE_ARROW_DEST, JConnector.CONNECT_LINE_TYPE_RECTANGULAR, Color.red));
                }
            }
        }

        props = new ConnectorPropertiesPanel(connectors);

        this.cc = new ConnectorContainer(connectors);
        this.cc.setLayout(null);
        this.cc.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

        for (DraggableMapNode mapNode : mapNodes) {
            this.cc.add(mapNode);
        }

        getContentPane().add(cc,
                new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

        cc.updateUI();
    }

    protected DraggableMapNode createMapNode(Box box) {
        JPanel head = createHead(box);
        JPanel body = createBody(box);

        DraggableMapNode mapNode = new DraggableMapNode(head, body);
        return mapNode;
    }

    private JPanel createHead(Box box) {
        JPanel panel = new JPanel();
        panel.add(new JLabel(box.getHead()));
        return panel;
    }

    private JPanel createBody(Box box) {
        JPanel panel = new JPanel();
        DefaultListModel<String> list = new DefaultListModel<>();
        for (String sentence : box.getBody()) {
            list.addElement(sentence);
        }

        panel.add(new JList<>(list));
        return panel;
    }

    public static void main(String[] args) throws Exception {
        ParaMapsApplication pm = new ParaMapsApplication();
        // The Application only works with this string right now (copy + paste + generate):
        // Jason is a man. He is 17 years old. Mary is a girl. She is 8 years old. Mary enjoys going to the mall. Jason is a student. He likes school. Jason is friends with Mary. Ralph is 28 years old. He is friends with Jason.
        pm.setVisible(true);
    }



}

