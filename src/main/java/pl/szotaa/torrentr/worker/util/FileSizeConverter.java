package pl.szotaa.torrentr.worker.util;

public class FileSizeConverter {

    public static double toKb(double amount, String unit){
        if(unit.equalsIgnoreCase("mib") || unit.equalsIgnoreCase("mb")){
            return 1024 * amount;
        }
        else if(unit.equalsIgnoreCase("gib") || unit.equalsIgnoreCase("gb")){
            return 1024 * 1024 * amount;
        }
        else if(unit.equalsIgnoreCase("tib") || unit.equalsIgnoreCase("tb")){
            return 1024 * 1024 * 1024 * amount;
        }
        throw new IllegalArgumentException("Unsupported unit string: " + unit);
    }
}
