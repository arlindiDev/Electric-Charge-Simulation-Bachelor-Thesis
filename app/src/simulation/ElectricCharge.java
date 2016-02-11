package simulation;

import exceptions.SimulationException;
import interfaces.Matter;

import java.awt.*;

/**
 * Created by Arlind on 03-Dec-15.
 */
public class ElectricCharge implements Matter
{
    private Point position = new Point(0, 0);
    private Point velocity = new Point(0, 0);
    private Point acceleration = new Point(0, 0);
    private double charge;
    private int type;
    private Color color;
    private boolean is_point_charge = false;
    private Point force = new Point(0, 0);
    private boolean collision = false;
    private double force_value;

    public ElectricCharge(double charge, int type, Point position, Point velocity, Point acceleration) throws SimulationException
    {
        if (position == null)
        {
            throw new SimulationException("The electric charges position cannot be null");
        }
        if (velocity == null || velocity.x < 0 || velocity.y < 0)
        {
            throw new SimulationException("The electric charges velocity has a bad format");
        }
        if (acceleration == null || acceleration.x < 0 || acceleration.y < 0)
        {
            throw new SimulationException("The electric charges acceleration has a bad format");
        }
        if (charge <= 0)
        {
            throw new SimulationException("The electric charges charge cannot be smaller or equal to 0");
        }
        if (type != -1 && type != 1)
        {
            throw new SimulationException("The electric charges type can either -1 or 1");
        }

        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.charge = charge;
        setType(type);
    }

    public void processPhysics()
    {
        if (!isIs_point_charge())
        {
            position.y += velocity.y;
            position.x += velocity.x;

            velocity.y += acceleration.y;
            velocity.x += acceleration.x;
        }
    }

    public boolean isIs_point_charge()
    {
        return is_point_charge;
    }

    public void setIs_point_charge(boolean is_point_charge)
    {
        this.is_point_charge = is_point_charge;
    }

    public Point getPosition()
    {
        return position;
    }

    public void setPosition(Point position)
    {
        this.position = position;
    }

    public Point getVelocity()
    {
        return velocity;
    }

    public void setVelocity(Point velocity)
    {
        this.velocity = velocity;
    }

    public Point getAcceleration()
    {
        return acceleration;
    }

    public void setAcceleration(Point acceleration)
    {
        this.acceleration = acceleration;
    }

    public double getCharge()
    {
        return charge;
    }

    public void setCharge(float charge)
    {
        if (charge <= 0)
        {
            charge = 1;
        }
        this.charge = charge;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
        this.color = (type < 0) ? Color.blue : Color.red;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public Point getForce()
    {
        return force;
    }

    public void setForce(Point force)
    {
        this.force = force;
    }

    @Override
    public void detectCollision(Matter physicsObject, World world)
    {
        double distance = new MathSimulation().euclideanDistance(this.getPosition(), physicsObject.getPosition());

        if (distance < 15)
        {
            try
            {
                double charge = physicsObject.getCharge() - getCharge();
                double max = Math.max(physicsObject.getCharge() , getCharge());
                int type = 1;
                if(max == physicsObject.getCharge())
                {
                    type = physicsObject.getType();
                }
                else
                {
                    type = this.getType();
                }

                if (charge == 0 || charge == 1)
                {
                    charge = 2;
                }
                else if(charge < 0)
                {
                    charge = charge * -1;
                }

                Point position = new Point(physicsObject.getPosition().x + 20, physicsObject.getPosition().y + 20);

                charge = charge / 2;
                setCharge((float) charge);
                physicsObject.setCharge((float) charge);
                physicsObject.setPosition(position);
                setType(type);
                physicsObject.setType(type);

//                world.addPhysicsObject(new ElectricCharge(charge / 2, type, position, new Point(0, 0), new Point(0, 0)));
//                world.addPhysicsObject(new ElectricCharge(charge / 2, type, position_, new Point(0, 0), new Point(0, 0)));
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
            setCollision(true);
            physicsObject.setCollision(true);
        }
    }

    public boolean isCollision()
    {
        return collision;
    }

    @Override
    public void setCollision(boolean collision)
    {
        this.collision = collision;
    }

    @Override
    public boolean getCollision()
    {
        return collision;
    }

    @Override
    public double getForceValue()
    {
        return force_value;
    }

    @Override
    public void setForceValue(double force)
    {
        this.force_value = force;
    }

    @Override
    public String toString()
    {
        return "ElectricCharge{" +
                "position=" + position +
                ", velocity=" + velocity +
                ", acceleration=" + acceleration +
                ", charge=" + charge +
                ", type=" + type +
                ", color=" + color +
                '}';
    }



    @Override
    public boolean equals(Object object)
    {
        if (object instanceof ElectricCharge)
        {
            ElectricCharge electricCharge = (ElectricCharge) object;
            return getPosition().x == electricCharge.getPosition().x && getPosition().y == electricCharge.getPosition().y && electricCharge.getCharge() == getCharge() && electricCharge.getType() == getType();
        }
        return false;
    }


}
