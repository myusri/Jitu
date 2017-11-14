package java.beans;

import java.lang.reflect.*;

public class PropertyDescriptor {
    private Class<?> objectClass;
    private String name;
    
    public PropertyDescriptor(String name, Class<?> objectClass) {
        this.name = name;
        this.objectClass = objectClass;
    }
    
    public Class<?> getPropertyType() {
        try {
            Class<?> propertyType = objectClass.getMethod(
                    "get" + Character.toUpperCase(name.charAt(0)) + name.substring(1))
                    .getReturnType();
            
            return propertyType;
        } catch (SecurityException e) {
            return null;
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
    
    public Method getWriteMethod() {
        try {
            return objectClass.getMethod("set" + Character.toUpperCase(name.charAt(0))
                    + name.substring(1), getPropertyType());
        } catch (SecurityException e) {
            return null;
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
