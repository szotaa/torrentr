package pl.szotaa.torrentr.worker.util;

/**
 * Utility class for converting scraped file size to unified unit.
 *
 * @author Jakub Szota
 */

public class FileSizeConverter {

    /**
     * Converts file size to kilobytes.
     * @param amount Size of a file.
     * @param unit File size unit.
     * @return File size in kilobytes.
     * @throws IllegalArgumentException Unit not supported.
     */

    public static double toKb(double amount, String unit){
        if(unit.equalsIgnoreCase("kib") || unit.equalsIgnoreCase("kb")){
            return amount;
        }
        else if(unit.equalsIgnoreCase("mib") || unit.equalsIgnoreCase("mb")){
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
