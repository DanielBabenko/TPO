package task3;

import java.util.ArrayList;
import java.util.List;

public class Humanoid {

    private String name;
    private boolean hasMuse;
    private Location location;
    private List<Clothes> clothes = new ArrayList<>();

    public Humanoid(String name) {
        this.name = name;
    }

    public void setHasMuse(boolean hasMuse) {
        this.hasMuse = hasMuse;
    }

    public boolean sleep(){
        if (hasMuse) return false;
        else return true;
    }

    public Article writeAnArticle(Object theme, Computer computer, Genres genres, int volume) throws Exception {
        if (computer.checkFunctionality() && !sleep()){
            int writtenVolume = computer.work(volume);
            Article newArticle;
            if (writtenVolume < volume){
                newArticle = new Draft(genres, this, writtenVolume, theme);
            } else {
                newArticle = new Article(genres, this, volume, theme);
            }
            return newArticle;
        } else {
            throw new Exception("Не сегодня.");
        }
    }

    public void publishAnArticle(Article article, Guide magazine) throws Exception {
        if (article instanceof Draft){
            throw new Exception("Черновик нужно довести до готовой статьи, чтобы опубликовать!");
        }
        magazine.articles.add(article);
    }

    public void becomeCoauthor(Article article){
        article.authors.add(this);
    }

    public void scrapCoauthor(Article article, Humanoid coauthor){
        article.authors.remove(coauthor);
    }

    public Article finishAnArticle(Draft draft, int newMaterial){
        draft.volume += newMaterial;
        Article basedOnDraft = new Article(draft);
        return basedOnDraft;
    }

    public void changeDislocation(Location destination){
        this.location = destination;
    }

    public Location getLocation() {
        return location;
    }

    public void putOnClothes(Clothes c){
        clothes.add(c);
    }

    public void putOffClothes(Clothes c){
        if (!clothes.isEmpty()){
            clothes.remove(c);
        }
    }

    public void setClothes(List<Clothes> clothes) {
        this.clothes = clothes;
    }

    public List<Clothes> getClothes() {
        return clothes;
    }
}
