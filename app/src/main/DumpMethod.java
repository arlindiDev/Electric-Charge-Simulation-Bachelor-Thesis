package main;

import simulation.MathSimulation;
import simulation.World;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class DumpMethod {

    public static void main(String args[])
    {
        try {
            Class c = World.class;
            Method[] m = c.getDeclaredMethods();
            Field[] f = c.getDeclaredFields();
            for (int i = 0; i < m.length; i++){
                if(Modifier.isPrivate(m[i].getModifiers()))
                {
                    System.out.println("- "+m[i].toString());
                }
                else
                {
                    System.out.println("+ "+m[i].toString());
                }
            }
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
}