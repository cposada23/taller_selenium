package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionRunner {
    private static Logger logger = LogManager.getLogger(ReflectionRunner.class.getName());
    public static void runReflectionMethod(String className, String methodName, Object... inputArgs) {
        logger.info("Running Reflection");
        Class<?> params[] = new Class[inputArgs.length];

        for (int i = 0; i < inputArgs.length; i++) {
            if (inputArgs[i] instanceof String) {
                params[i] = String.class;
            }else if (inputArgs[i] instanceof Integer) {
                params[i] = Integer.class;
            }else if (inputArgs[i] instanceof Double) {
                params[i] = Double.class;
            }
        }

        try {
            Class<?> cls = Class.forName(className);
            Object _instance = cls.newInstance();
            Method myMethod = cls.getDeclaredMethod(methodName, params);
            myMethod.invoke(_instance, inputArgs);

        } catch (ClassNotFoundException e) {
            System.err.format(className + ":- Class not found%n");
        } catch (IllegalArgumentException e) {
            System.err
                    .format("Method invoked with wrong number of arguments%n");
        } catch (NoSuchMethodException e) {
            System.err.format("In Class " + className + "::" + methodName
                    + ":- method does not exists%n");
        } catch (InvocationTargetException e) {
            System.err.format("Exception thrown by an invoked method%n");
        } catch (IllegalAccessException e) {
            System.err
                    .format("Can not access a member of class with modifiers private%n");
            e.printStackTrace();
        } catch (InstantiationException e) {
            System.err
                    .format("Object cannot be instantiated for the specified class using the newInstance method%n");
        }

    }
}
