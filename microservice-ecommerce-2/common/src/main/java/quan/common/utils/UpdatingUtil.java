package quan.common.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

public class UpdatingUtil {
    public static String[] getNullPropertyNames(Object source) {
        // Create a new BeanWrapper object for the source object
        final BeanWrapper src = new BeanWrapperImpl(source);
        // Get the property descriptors for the source object
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        // Create a Set to hold the names of empty properties
        Set<String> emptyNames = new HashSet<>();
        // Iterate over the property descriptors
        for (PropertyDescriptor pd : pds) {
            // Get the value of the property in the source object
            Object srcValue = src.getPropertyValue(pd.getName());
            // If the value is null, add the name of the property to the Set
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        // Convert the Set of empty property names to a String array and return it
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
