package interfaces;

import exceptions.SimulationException;

/**
 * Created by Arlind on 03-Dec-15.
 */
public interface WorldInterface {
    void startSimulation();

    void stopSimulation();

    void addPhysicsObject(Matter physicsObject) throws SimulationException;

    void processGlobalPhysics();
}
