package utils;

import lombok.experimental.UtilityClass;

import java.util.Date;
import java.util.Random;

@UtilityClass
public class CommonUtils {

    public String generateRandomStringWithPrefix(String prefix){
        return prefix + new Date();
    }
}
