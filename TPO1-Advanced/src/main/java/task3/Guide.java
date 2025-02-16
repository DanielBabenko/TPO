package task3;

import java.util.HashSet;
import java.util.Set;

public class Guide {
    public String name;
    public Set<Article> articles = new HashSet<>();
    private long volume;

    public Guide(String name) {
        this.name = name;
    }

    private void countVolume(){
        long volume = 0;
        for (Article a : articles){
            volume += a.volume;
        }
        this.volume = volume;
    }
}
