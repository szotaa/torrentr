package pl.szotaa.torrentr.domain;

import lombok.Builder;
import lombok.Getter;

/**
 * Immutable class representing single search result.
 *
 * @author Jakub Szota
 */

@Getter
@Builder
public class Result {

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
}
