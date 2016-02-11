package main;

import customview.CircleCheckBox;
import customview.SeekBar;
import customview.SeekProgressChangedListener;
import exceptions.SimulationException;
import simulation.ElectricCharge;
import simulation.Point;
import simulation.World;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Arlind on 03-Dec-15.
 */
public class Main
{
    private static boolean isPointCharge = false;
    private static boolean isNegativeCharge = false;
    private static boolean simualtionState = true;
    private static JTextField field = new JTextField();
    private static int default_res = 10;

    public static void main(String[] args)
    {

        final World world = new World();
        try
        {
            world.addPhysicsObject(new ElectricCharge(700, 1, new Point(250, 300), new Point(0, 0), new Point(0, 0)));
            world.addPhysicsObject(new ElectricCharge(100, -1, new Point(300, 300), new Point(0, 0), new Point(0, 0)));
            world.addPhysicsObject(new ElectricCharge(100, -1, new Point(600, 100), new Point(0, 0), new Point(0, 0)));
            world.addPhysicsObject(new ElectricCharge(100, -1, new Point(100, 400), new Point(0, 0), new Point(0, 0)));
            world.addPhysicsObject(new ElectricCharge(100, -1, new Point(300, 100), new Point(0, 0), new Point(0, 0)));

            world.addPhysicsObject(new ElectricCharge(100, 1, new Point(300, 500), new Point(0, 0), new Point(0, 0)));
            world.addPhysicsObject(new ElectricCharge(200, 1, new Point(350, 200), new Point(0, 0), new Point(0, 0)));
            world.addPhysicsObject(new ElectricCharge(300, -1, new Point(500, 600), new Point(0, 0), new Point(0, 0)));

            world.setVisible(true);
            world.setLocation(10, 50);
            world.setResolution(default_res);

            world.addMouseListener(new MouseAdapter()
            {
                int type = 1;

                @Override
                public void mousePressed(MouseEvent e)
                {

                    try
                    {
                        int type = 1;
                        if (isNegativeCharge)
                        {
                            type = -1;
                        }
                        int chargeInt = 1;
                        try
                        {
                            chargeInt = Integer.parseInt(field.getText());
                        } catch (Exception exception)
                        {
                            chargeInt = 50;
                            exception.printStackTrace();
                        }
                        ElectricCharge physicsObject = new ElectricCharge(chargeInt, type, new Point(e.getX(), e.getY()), new Point(0, 0), new Point(0, 0));
                        physicsObject.setIs_point_charge(isPointCharge);
                        world.addPhysicsObject(physicsObject);
                    } catch (Exception exception)
                    {

                    }
                }

                @Override
                public void mouseReleased(MouseEvent e)
                {
                    type *= -1;
                }
            });
            world.startSimulation();

            JFrame jFrame_ = new JFrame();
            jFrame_.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame_.setPreferredSize(new Dimension(900, 500));
            jFrame_.setSize(900, 500);
            jFrame_.setExtendedState(JFrame.MAXIMIZED_BOTH);

           jFrame_.add(setUpControls(world));
            jFrame_.add(setUpSimulation(world));
           // jFrame_.add(setUpCircle());
            jFrame_.setVisible(true);


        } catch (SimulationException simulation_exception)
        {
            simulation_exception.printStackTrace();
        }
    }


    public static JPanel setUpCircle()
    {
        JPanel jPanel = new JPanel();
        jPanel.setBackground(new Color(73,36,73));
        jPanel.setVisible(true);
        jPanel.setPreferredSize(new Dimension(150, 600));
        jPanel.setSize(150, 600);
        jPanel.setLayout(new FlowLayout()); //Centered components

        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        final CircleCheckBox circleCheckBox = new CircleCheckBox(10, 10, 70, 6);
        jPanel.add(circleCheckBox);

        return jPanel;
    }

    public static JPanel setUpControls(final World world)
    {
        JPanel jPanel = new JPanel();
        jPanel.setBackground(new Color(73,36,73));
        jPanel.setVisible(true);
        jPanel.setPreferredSize(new Dimension(150, 600));
        jPanel.setSize(150, 600);
        jPanel.setLayout(new FlowLayout()); //Centered components

        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        final SeekBar seekbar = new SeekBar(10, 10, 70, 6);
        seekbar.setMax(50);
        seekbar.setProgress(default_res);

        seekbar.setSeekProgressChangedListener(new SeekProgressChangedListener()
        {
            @Override
            public void onSeekProgressChangedListener(int progress)
            {
                world.setResolution(progress);
            }
        });

        JCheckBox jCheckBoxGrid = new JCheckBox("Hide Grid");
        jCheckBoxGrid.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
                boolean selected = abstractButton.getModel().isSelected();
                world.drawGrid(!selected);
            }
        });
        JCheckBox jCheckBoxElectricField = new JCheckBox("Hide Electric Field");
        jCheckBoxElectricField.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
                boolean selected = abstractButton.getModel().isSelected();
                world.showElectricField(!selected);
            }
        });
        JCheckBox jCheckBoxPointCharge = new JCheckBox("Point Charge");
        jCheckBoxPointCharge.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
                boolean selected = abstractButton.getModel().isSelected();
                isPointCharge = selected;
            }
        });
        JCheckBox jCheckBoxNegative = new JCheckBox("Negative Charge");
        jCheckBoxNegative.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
                boolean selected = abstractButton.getModel().isSelected();
                isNegativeCharge = selected;
            }
        });
        final JButton jButtonStartPause = new JButton("Pause Simulation");
        jButtonStartPause.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                if (simualtionState)
                {
                    world.stopSimulation();
                    jButtonStartPause.setText("Play Simulation");
                } else
                {
                    world.startSimulation();
                    jButtonStartPause.setText("Pause Simulation");
                }

                simualtionState = !simualtionState;
            }
        });

        field = new JTextField();
        PlainDocument doc = new PlainDocument();
        doc.setDocumentFilter(new DocumentFilter()
        {
            @Override
            public void insertString(FilterBypass fb, int off, String str, AttributeSet attr) throws BadLocationException
            {
                fb.insertString(off, str.replaceAll("\\D++", ""), attr);  // remove non-digits--
            }

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int off, int len, String str, AttributeSet attr) throws BadLocationException
            {
                if ((field.getText().length()) <= 3)
                {
                    fb.replace(off, len, str.replaceAll("\\D++", ""), attr);  // remove non-digits
                }
            }
        });

        field.setDocument(doc);
        field.setPreferredSize(new Dimension(100, 30));

        JLabel JfieldCharge = new JLabel("Charge (Coloumbs):");
        JfieldCharge.setForeground (new Color(246,210,130));
        JLabel jFieldres = new JLabel("Electric Field Resolution:");
        jFieldres.setForeground (new Color(246,210,130));
        JLabel jLabelDescription1 = new JLabel("Click the black simulation");
        jLabelDescription1.setForeground (new Color(246,210,130));
        JLabel jLabelDescription2 = new JLabel("to add an electric charge.");
        jLabelDescription2.setForeground (new Color(246,210,130));

        JButton jButtonClear = new JButton("Clear Simulation");
        jButtonClear.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                world.clearSimulation();
            }
        });

        jPanel.add(jCheckBoxGrid);
        jPanel.add(jCheckBoxElectricField);
        jPanel.add(jCheckBoxPointCharge);
        jPanel.add(jCheckBoxNegative);
        jPanel.add(JfieldCharge);
        jPanel.add(field);
        jPanel.add(jFieldres);
        jPanel.add(seekbar);
        jPanel.add(jButtonStartPause);
        jPanel.add(jButtonClear);
        jPanel.add(jLabelDescription1);
        jPanel.add(jLabelDescription2);

        return jPanel;
    }

    public static JPanel setUpSimulation(World world)
    {
        JPanel jPanel = new JPanel();
        jPanel.setBackground(new Color(73,36,73));

        jPanel.setLayout(new FlowLayout()); //Centered components

        jPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        jPanel.add(world);
        return jPanel;
    }
}
