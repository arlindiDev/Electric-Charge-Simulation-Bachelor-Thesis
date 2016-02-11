package interfaces;


import simulation.Point;

/**
 * Created by Arlind on 03-Dec-15.
 */
public interface Math
{
    double euclideanDistance(Point point_1, Point point_2);

    double electricForce(double q1,double q2, double distance);

    double angleToPoint(Point point_1, Point point_2);

    double oppositeAngle(double radians);

    Point polarToRectangularCoordinates(double distance, double angle_theta);

    Point add(Point point_1, Point point_2);

    double acceleration(double force, double mass);
}
