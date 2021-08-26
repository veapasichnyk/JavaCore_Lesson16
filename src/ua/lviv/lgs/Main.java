package ua.lviv.lgs;

import java.lang.reflect.*;
import java.util.Arrays;

public class Main {
    public static void main ( String [] args ) throws NoSuchMethodException, IllegalAccessException,
                                                        InvocationTargetException, InstantiationException,
                                                        NoSuchFieldException {

        Class myObjectClass = Cat.class;
        System.out.println ( myObjectClass );

        //name
        String nameOfClass = myObjectClass.getName();
        System.out.println("Name of class = " + nameOfClass);

        String simpleName = myObjectClass.getSimpleName();
        System.out.println("Simple name  =  " + simpleName);

        //Modifiers
        int modifiers = myObjectClass.getModifiers();
        String modifierText = Modifier.toString ( modifiers );
        System.out.println("Class modifier = " + modifierText + " int = " + modifiers);

        System.out.println("is static = " + Modifier.isStatic(modifiers));
        System.out.println("is public = " + Modifier.isPublic(modifiers));
        System.out.println("is abstract = " + Modifier.isAbstract(modifiers));
        System.out.println ("is private = " + Modifier.isPrivate ( modifiers ) );

        //package info
        Package myPackage = myObjectClass.getPackage();
        System.out.println ("My package info = " + myPackage );

        //Super class info
        Class mySuperClass = myObjectClass.getSuperclass();
        System.out.println ( "My Super Class = " + mySuperClass );

        //get Interfaces
        Class[] interfaces = myObjectClass.getInterfaces();
        System.out.println ( "Interfaces =  " + Arrays.toString ( interfaces ) );

        //Constructors
        Constructor<Cat>[] personConstructor = myObjectClass.getConstructors();

        for (int i = 0; i < personConstructor.length; i++) {
            Constructor<Cat> constructor = personConstructor[i];
            System.out.println("Constructor = " + constructor);
        }

        //parameter Types
        Constructor constructor = personConstructor[0];
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> cl = parameterTypes[i];
            System.out.println(cl);
        }

        Field[] fields = myObjectClass.getFields ( );
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            System.out.println(field);
        }
        System.out.println();
        fields = myObjectClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            System.out.println(field);
        }

        //Methods
        Method[] methods = myObjectClass.getMethods ( );
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            System.out.println(method);
        }
        //add new Cat
        Constructor<Cat> constructor1 = myObjectClass.getConstructor(String.class, int.class);
        Cat newInstance = constructor1.newInstance ( "Kitty", 3 );
        System.out.println(newInstance);

        //Change the name of the cat
        Field fieldName = myObjectClass.getField ("name");
        fieldName.set(newInstance, "Lily");
        System.out.println(newInstance);

        //Change the age of the cat
        Method catMethod = myObjectClass.getMethod("setAge", int.class);
        catMethod.invoke(newInstance, 4);
        System.out.println(newInstance);

        //Return cat name
        catMethod = myObjectClass.getMethod("getName", null);
        System.out.println("Cat name - " + catMethod.invoke(newInstance, null));

        //Force the cat to sit
        Method method = Cat.class.getMethod("actions", String.class);
        Object returnValue = method.invoke(newInstance, "Sit!");
        System.out.println ( returnValue );
    }
}
