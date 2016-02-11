package simulation;

import exceptions.SimulationException;
import interfaces.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.Math;
import java.util.ArrayList;

/**
 * Created by Arlind on 03-Dec-15.
 */
public class World extends JPanel implements WorldInterface // Android extends View
{
    private ArrayList<Matter> list = new ArrayList<Matter>();
    private final int timer_frames = 60;
    private interfaces.Math math = new MathSimulation();
    private boolean isStarted = false;
    private int resolution = 1;
    Timer timer = null;
    private boolean drawGrid = true;
    private boolean showElectricField = true;

    public World()
    {
        super();
        initialize();
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(800, 700);
    }


    public void initialize()
    {
        this.setLocation(0, 0);
        this.setSize(500, 500);

        timer = new Timer(timer_frames, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                processGlobalPhysics();
                for (int i = 0; i < list.size(); i++)
                {
                    list.get(i).processPhysics();
                }
                repaint(); // Android invalidate();
            }
        });
    }

    public void startSimulation()
    {
        if (!isStarted)
        {
            timer.start();
            isStarted = true;
        }

//        worldTime.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//
//            }
//        }, 0, timer_frames);
    }

    public void stopSimulation()
    {
        if (timer != null && isStarted)
        {
            timer.stop();
            isStarted = false;
        }
    }

    void processField(Graphics gr) throws SimulationException
    {
        int field_color = 0;
        Graphics2D graphics = (Graphics2D) gr;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        for (int x = 0; x < getWidth(); x += getResolution())
        {
            for (int y = 0; y < getHeight(); y += getResolution())
            {
                double force_magnitude = 0;
                for (int i = 0; i < list.size(); i++)
                {
                    force_magnitude += list.get(i).getType() * math.electricForce(list.get(i).getCharge(), 1, math.euclideanDistance(list.get(i).getPosition(), new Point(x, y)));
                }

                if (force_magnitude < 0)
                {
                    force_magnitude = force_magnitude * -1;
                    if (force_magnitude > 1)
                    {
                        force_magnitude = 1;
                    }
                    if (force_magnitude < 0)
                    {
                        force_magnitude = 0;
                    }
                    field_color = Color.HSBtoRGB(0.6f, 1.0f, (float) force_magnitude);
                } else
                {
                    if (force_magnitude > 1)
                    {
                        force_magnitude = 1;
                    } else if (force_magnitude < 0)
                    {
                        force_magnitude = 0;
                    }
                    field_color = Color.HSBtoRGB(0.0f, 1.0f, (float) force_magnitude);
                }
                graphics.setColor(new Color(field_color));
                graphics.fillRect(x, y, getResolution(), getResolution());
            }
        }
    }

    public void addPhysicsObject(Matter physicsObject) throws SimulationException
    {
        if (physicsObject == null)
        {
            throw new SimulationException("Physics Objects cannot be null");
        }
        list.add(physicsObject);
    }

    public void processGlobalPhysics()
    {
        for (int i = 0; i < list.size(); i++)
        {
            double last_force_value = 0;
            Matter current_charge = list.get(i);
            Point force_vector_position = new Point(0, 0);
            Point last_force_vector_position = new Point(0, 0);

            for (int j = 0; j < list.size(); j++)
            {
                if (i != j)
                {
                    Matter other_charge = list.get(j);
                    double force_magnitude = math.electricForce(current_charge.getCharge(), other_charge.getCharge(), math.euclideanDistance(current_charge.getPosition(), other_charge.getPosition()));

                    double current_charge_theta_angle = math.angleToPoint(current_charge.getPosition(), other_charge.getPosition());

                    if (current_charge.getType() == other_charge.getType())
                    {
                        current_charge_theta_angle = math.oppositeAngle(current_charge_theta_angle);
                    }

                    force_vector_position = math.polarToRectangularCoordinates(force_magnitude, current_charge_theta_angle);
                    force_vector_position = math.add(force_vector_position, last_force_vector_position);

                    last_force_value = last_force_value + force_magnitude;

                    last_force_vector_position = force_vector_position;
                    current_charge.detectCollision(other_charge, this);
                }
            }
            current_charge.setForceValue(last_force_value);
            current_charge.setForce(force_vector_position);
            current_charge.setAcceleration(new Point(math.acceleration(force_vector_position.x, current_charge.getCharge()), math.acceleration(force_vector_position.y, current_charge.getCharge())));
        }
    }

    public void remove(Matter object)
    {
        for (int i = 0; i < list.size(); i++)
        {
            if (object.equals(list.get(i)))
            {
                list.remove(i);
                break;
            }
        }
    }

    @Override
    public void paintComponent(final Graphics gr)
    { // Android onDraw(Canvas canvas)

        Graphics2D g = (Graphics2D) gr;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.clearRect(0, 0, getWidth(), getHeight());

        if (showElectricField)
        {
            try
            {
                processField(g);
            } catch (SimulationException se)
            {
            }
        } else
        {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        if (drawGrid)
        {
            g.setColor(new Color(42, 42, 42));
            for (int i = 0; i < getWidth(); i += 20)
            {
                g.drawLine(i, 0, i, getHeight());
            }

            for (int i = 0; i < getHeight(); i += 20)
            {
                g.drawLine(0, i, getWidth(), i);
            }
        }


        for (int i = 0; i < list.size(); i++)
        {
            g.setColor(Color.black);
            g.fillOval((int) list.get(i).getPosition().x - 8, (int) list.get(i).getPosition().y - 8, 16, 16);
            g.setColor(list.get(i).getColor());
            g.fillOval((int) list.get(i).getPosition().x - 5, (int) list.get(i).getPosition().y - 5, 10, 10);
            g.setColor(Color.WHITE);
            g.drawString((int) list.get(i).getCharge() + "C", (int) list.get(i).getPosition().x + 3, (int) list.get(i).getPosition().y - 3);
            g.setColor(Color.WHITE);

            double d = (double) Math.round(list.get(i).getForceValue() * 100d) / 100d;
            g.drawString(d + "N", (int) list.get(i).getPosition().x+10, (int) list.get(i).getPosition().y+10);
            g.drawLine((int) list.get(i).getPosition().x, (int) list.get(i).getPosition().y, (int) list.get(i).getPosition().x + (int) list.get(i).getForce().x, (int) list.get(i).getPosition().y + (int)list.get(i).getForce().y);

        }
    }

    public void drawGrid(boolean b)
    {
        drawGrid = b;
    }

    public void showElectricField(boolean b)
    {
        showElectricField = b;
    }


    public int getResolution()
    {
        return resolution;
    }

    public void setResolution(int resolution)
    {
        if (resolution < 1)
        {
            resolution = 1;
        }
        this.resolution = resolution;
    }

    public void clearSimulation()
    {
        list.clear();
    }

}