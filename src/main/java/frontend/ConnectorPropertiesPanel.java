package main.java.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ConnectorPropertiesPanel extends JPanel {
    JComboBox cbxType = new JComboBox(new String[] {"Simple", "Rectangular"});
    JComboBox cbxArrow = new JComboBox(new String[] {"No arrow", "Source", "Dest", "Both"});
    JButton btnColor = new JButton("...");
    ArrayList<JConnector> connectors;

    public ConnectorPropertiesPanel(ArrayList<JConnector> connectors) {
        this.connectors = connectors;
        setLayout(new GridBagLayout());
        add(new JLabel("Line type:"), new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        add(cbxType, new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0, 0));

        add(new JLabel("Line arrow:"), new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        add(cbxArrow, new GridBagConstraints(1, 1, 1, 1, 1, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0, 0));

        add(new JLabel("Line color:"), new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        add(btnColor, new GridBagConstraints(1, 2, 1, 1, 1, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 5, 5), 0, 0));

        add(new JLabel(" "), new GridBagConstraints(0, 7, 2, 1, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

        add(new JLabel("Parts of Speech Frequency:"), new GridBagConstraints(0, 3, 0, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        add(new JScrollPane(new JList<>(), ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER)
                , new GridBagConstraints(0, 4, 0, 1, 0, 0, GridBagConstraints.NORTHWEST,GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 0));

        add(new JLabel("Word Frequency:"), new GridBagConstraints(0, 5, 0, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        add(new JScrollPane(new JList<>(), ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER)
                , new GridBagConstraints(0, 6, 0, 1, 0, 0, GridBagConstraints.NORTHWEST,GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 0));

        for (JConnector connector : this.connectors) {
            setColor(connector.getLineColor());
            if (connector.getLineType() == ConnectLine.LINE_TYPE_SIMPLE) {
                cbxType.setSelectedIndex(0);
            } else {
                cbxType.setSelectedIndex(1);
            }
        }

        for (JConnector connector : this.connectors) {
            switch (connector.getLineArrow()) {
                case ConnectLine.LINE_ARROW_NONE:
                    cbxArrow.setSelectedIndex(0);
                    break;
                case ConnectLine.LINE_ARROW_SOURCE:
                    cbxArrow.setSelectedIndex(1);
                    break;
                case ConnectLine.LINE_ARROW_DEST:
                    cbxArrow.setSelectedIndex(2);
                    break;
                case ConnectLine.LINE_ARROW_BOTH:
                    cbxArrow.setSelectedIndex(3);
                    break;
            }
        }

        initListeners();
    }

    public ConnectorPropertiesPanel(ArrayList<JConnector> connectors, JList<String> pOSFrequencyList,
                                    JList<String> wordFrequencyList) {
        this.connectors = connectors;
        setLayout(new GridBagLayout());
        add(new JLabel("Line type:"), new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        add(cbxType, new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0, 0));

        add(new JLabel("Line arrow:"), new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        add(cbxArrow, new GridBagConstraints(1, 1, 1, 1, 1, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0, 0));

        add(new JLabel("Line color:"), new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        add(btnColor, new GridBagConstraints(1, 2, 1, 1, 1, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 5, 5), 0, 0));

        add(new JLabel(" "), new GridBagConstraints(0, 7, 2, 1, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

        add(new JLabel("Parts of Speech Frequency:"), new GridBagConstraints(0, 3, 0, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        add(new JScrollPane(pOSFrequencyList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER)
                , new GridBagConstraints(0, 4, 0, 1, 0, 0, GridBagConstraints.NORTHWEST,GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 0));

        add(new JLabel("Word Frequency:"), new GridBagConstraints(0, 5, 0, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        add(new JScrollPane(wordFrequencyList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER)
                , new GridBagConstraints(0, 6, 0, 1, 0, 0, GridBagConstraints.NORTHWEST,GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 0));

        for (JConnector connector : this.connectors) {
            setColor(connector.getLineColor());
            if (connector.getLineType() == ConnectLine.LINE_TYPE_SIMPLE) {
                cbxType.setSelectedIndex(0);
            } else {
                cbxType.setSelectedIndex(1);
            }
        }

        for (JConnector connector : this.connectors) {
            switch (connector.getLineArrow()) {
                case ConnectLine.LINE_ARROW_NONE:
                    cbxArrow.setSelectedIndex(0);
                    break;
                case ConnectLine.LINE_ARROW_SOURCE:
                    cbxArrow.setSelectedIndex(1);
                    break;
                case ConnectLine.LINE_ARROW_DEST:
                    cbxArrow.setSelectedIndex(2);
                    break;
                case ConnectLine.LINE_ARROW_BOTH:
                    cbxArrow.setSelectedIndex(3);
                    break;
            }
        }

        initListeners();
    }

    protected void setColor(Color c) {
        btnColor.setBackground(c);
    }

    protected void initListeners() {
        cbxType.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (JConnector connector : connectors) {
                    if (cbxType.getSelectedIndex() == 0) {
                        connector.setLineType(ConnectLine.LINE_TYPE_SIMPLE);
                    } else {
                        connector.setLineType(ConnectLine.LINE_TYPE_RECT_1BREAK);
                    }
                }
                getTopLevelAncestor().repaint();
            }
        });

        cbxArrow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (JConnector connector : connectors) {
                    switch (cbxArrow.getSelectedIndex()) {
                        case 0:
                            connector.setLineArrow(ConnectLine.LINE_ARROW_NONE);
                            break;
                        case 1:
                            connector.setLineArrow(ConnectLine.LINE_ARROW_SOURCE);
                            break;
                        case 2:
                            connector.setLineArrow(ConnectLine.LINE_ARROW_DEST);
                            break;
                        case 3:
                            connector.setLineArrow(ConnectLine.LINE_ARROW_BOTH);
                            break;
                    }
                }
                getTopLevelAncestor().repaint();
            }
        });

        btnColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(ConnectorPropertiesPanel.this, "Select line color", connectors.get(0).getLineColor());
                if (newColor != null) {
                    setColor(newColor);
                    for (JConnector connector : connectors) {
                        connector.setLineColor(newColor);
                    }
                    getTopLevelAncestor().repaint();
                }

            }
        });
    }
}
