package pl.szotaa.torrentr.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Immutable class representing single search result.
 *
 * @author Jakub Szota
 */

@Getter
@Builder
@ToString
public class Result implements Comparable<Result> {

    /**
     * Torrent's title.
     */

    private final String title;

    /**
     * Magnet link pointing to the desired resource.
     */

    private final String magnetLink;

    /**
     * Info link pointing to the resource description and comments (if available).
     */

    private final String infoLink;

    /**
     * Number of seeds.
     */

    private final int seeds;

    /**
     * Number of peers.
     */

    private final int peers;

    /**
     * Resource's size in kilobytes.
     */

    private final double size;

    /**
     * Search engine which found this result.
     */

    private final String source;

    /**
     * Compares two Result objects. If objectA has more seeds then objectB, objectA is considered greater than objectB.
     * @param o Object you compare to.
     * @return 0 when equal, >0 if object on which compareTo was invoked is greater <0 otherwise.
     */

    @Override
    public int compareTo(Result o) {
        return Integer.compare(this.seeds, o.getSeeds());
    }

    /**
     * Tests Result with other object for equality. Object with equal case-insensitive magnetLinks are considered equal.
     * @param obj Object tested for equality.
     * @return True if objects are equal, false otherwise.
     */

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(!(obj instanceof Result)){
            return false;
        }
        Result result = (Result) obj;
        return this.magnetLink.equalsIgnoreCase(result.magnetLink);
    }

    /**
     * HashCode is generated based on magnetLink's hashcode.
     * @return Hash code of a class instance.
     */

    @Override
    public int hashCode() {
        int result = this.magnetLink.toLowerCase().hashCode();
        result = 31 * result;
        return result;
    }
}
