package simulation;

import java.lang.Math;

/**
 * Created by Arlind on 03-Dec-15.
 */
public class MathSimulation implements interfaces.Math
{
    @Override
    public double acceleration(double force, double mass) {
        return force/mass;
    }

    @Override
    public Point add(Point point_1, Point point_2) {
        return new Point(point_1.x + point_2.x, point_1.y + point_2.y);
    }

    @Override
    public Point polarToRectangularCoordinates(double distance, double angle_theta) {
        return new Point( (int)(distance *  Math.cos(angle_theta)), (int)(distance *  Math.sin(angle_theta)));
    }

    @Override
    public double oppositeAngle(double radians) {
        return radians + Math.PI;
    }

    @Override
    public double angleToPoint(Point point_1, Point point_2) {
       return Math.atan2(point_2.y - point_1.y, point_2.x - point_1.x);
    }

    @Override
    public double electricForce(double q1, double q2, double distance) {
        return q1 * q2 / (float) (distance * distance);
    }

    @Override
    public double euclideanDistance(Point point_1, Point point_2) {
        return  Math.sqrt(((point_2.x - point_1.x) * (point_2.x - point_1.x)) + ((point_2.y - point_1.y) * ((point_2.y - point_1.y ))));
    }
}
