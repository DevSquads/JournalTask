package Model;

import java.util.*;

public  class ArticlesDatabase {
    private static List<Article>  articles =new ArrayList();
    public static void generateArticles(){
        createArticle("Mirna","title","description");
        createArticle("Mirhan","title","description");
        createArticle("Maram","title","description");
        createArticle("Mirhan","title","description");
        createArticle("Mirhan","title","description");
        createArticle("Mariam","title","description");
        createArticle("Maram","title","description");
        createArticle("Mariam","title","description");
        createArticle("Mirna","title","description");
    }

    public static String createArticle(String author,String title,String description){
        Article art=new Article(author, title, description);
        getArticles().add(art);
        return "Model.Article created with title:"+title;
    }

    public static String deleteArticle(String title,String author){
        Article articleToBeDeleted = null;
        boolean found= false;
        for(Article a: getArticles()){
            if(a.title.equals(title) && a.author.equals(author)){
                articleToBeDeleted=a;
                found=true;
                break;
            }
        }
        if(found==true){
            getArticles().remove(articleToBeDeleted);
            return "Model.Article with title:"+title+" has been deleted";
        }
        return "Model.Article with title:"+title+" is not found on the system";
    }

    public static void viewList(String username){
        sortArticles();
        boolean author=false;
        for (Article a: getArticles()){
            if(a.author.equals(username)){
                author=true;
                break;
            }
        }
        if(!author){
        for (Article a: getArticles()){
            System.out.println("Author name:"+a.author+" title:"+a.title+" description:"+a.description);
        }}
        else{
            List <Article> edited= getArticles();
            List<Article> display=new ArrayList<>();
            for (Article x: getArticles()){
                if(x.author.equals(username))
                    display.add(x);
            }
            for (Article y:display){
                if(edited.contains(y))
                    edited.remove(y);
            }

            for (Article arc: edited) {
                display.add(arc);

            }
            for (Article a:display){
                System.out.println("Author name:"+a.author+" title:"+a.title+" description:"+a.description);
            }
            }
    }

    public static void sortArticles(){
        Collections.sort(getArticles(),new Comparator<Article>() {
            @Override
            public int compare(final Article object1, final Article object2) {
                return object1.author.compareTo(object2.author);
            }
        });
        Map<String, Integer> countMap= new HashMap<>();
        int count=1;
        for (int i = 1; i< getArticles().size(); i++){
            if (getArticles().get(i).author.equals(getArticles().get(i-1).author)){
                count++;
            }else{
                countMap.put(getArticles().get(i-1).author,count);
                count=1;
            }
        }
        countMap.put(getArticles().get(getArticles().size()-1).author,count);
        Integer max_count=0;
        String name="";
        List<Article> sortedArticles=new ArrayList<>();
        do{
        for (Map.Entry me : countMap.entrySet()) {
            if(max_count <(int) me.getValue()) {
                max_count=(int) me.getValue();
                name=(String)me.getKey();
            }
        }
        countMap.remove(name);
            for (Article arc: getArticles()){
            if(arc.author.equals(name))
                sortedArticles.add(arc);
        }max_count=0;
        }while(!countMap.isEmpty());
        setArticles(sortedArticles);

    }

    public static List<Article> getArticles() {
        return articles;
    }

    public static void setArticles(List<Article> articles) {
        ArticlesDatabase.articles = articles;
    }
}
