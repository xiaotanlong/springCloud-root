package learn.java8;

/**
 * @author 0217319
 * @version V1.0
 * @Description: Artists(用一句话描述该文件做什么)
 * @date 2018/12/5 15:15
 */

import learn.java8.examples.Artist;

import java.util.List;
import java.util.Optional;

/**
 * Artists 类表示了一组艺术家，重构该类，使得getArtist 方法返回一
     个Optional<Artist> 对象。如果索引在有效范围内，返回对应的元素，否则返回一个空
 Optional 对象。此外，还需重构getArtistName 方法，保持相同的行为。
 */
public class Artists {
    private List<Artist> artists;
    public Artists(List<Artist> artists) {
        this.artists = artists;
    }
    public Optional<Artist> getArtist(int index) {
        if (index < 0 || index >= artists.size()) {
            return Optional.empty();
        }
        return Optional.of(artists.get(index));
    }
    private void indexException(int index) {
        throw new IllegalArgumentException(index +
                "doesn't correspond to an Artist");
    }
    public String getArtistName(int index) {
       /*try {
            Optional<Artist> artist = getArtist(index);
            return artist.map(a->a.getName()).get();
        } catch (IllegalArgumentException e) {
            return "unknown";
        }*/

        Optional<Artist> artist = getArtist(index);
        return artist.map(a->a.getName()).orElse("unknown");
    }
}
