package interfaces;

import simulation.Point;
import simulation.World;

import java.awt.*;

/**
 * Created by Arlind on 03-Dec-15.
 */
public interface Matter
{

    void setForceValue(double force);

    double getForceValue();

    void processPhysics();

    void setForce(Point force);

    Point getForce();

    void setCollision(boolean collision);

    boolean getCollision();

    Point getPosition();

    void setPosition(Point position);

    Point getVelocity();

    void setVelocity(Point velocity);

    Point getAcceleration();

    void setAcceleration(Point acceleration);

    double getCharge();

    void setCharge(float charge);

    int getType();

    void setType(int type);

    Color getColor();

    void setColor(Color color);

    void detectCollision(Matter physicsObject, World world);
}
