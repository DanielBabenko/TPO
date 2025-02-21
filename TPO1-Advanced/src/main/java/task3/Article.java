package task3;

import java.util.HashSet;
import java.util.Set;

public class Article {
    public Set<Genres> genres = new HashSet<>();
    public Set<Humanoid> authors = new HashSet<>();
    public int volume;

    public Object topic;

    public Article(Genres genre, Humanoid author, int volume, Object topic) {
        this.genres.add(genre);
        this.authors.add(author);
        if (volume <= 0) {
            throw new IllegalArgumentException("Статья должна занимать хотя бы 1 страницу.");
        }
        this.volume = volume;
        this.topic = topic;
    }

    public Article(Draft draft) {
        for (Genres g : draft.genres){
            this.genres.add(g);
        }
        for (Humanoid author : draft.authors){
            this.authors.add(author);
        }
        if (draft.volume <= 0) {
            throw new IllegalArgumentException("Статья должна занимать хотя бы 1 страницу.");
        }
        this.volume = draft.volume;
        this.topic = draft.topic;
    }

    public int getVolume() {
        return volume;
    }

    public Set<Genres> getGenres() {
        return genres;
    }

    public Set<Humanoid> getAuthors() {
        return authors;
    }

    public Object getTopic() {
        return topic;
    }
}
