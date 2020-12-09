package jornaltask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.FilerException;


public class Editor {
    private String name;
    private List<Article> articles ;

    Editor(){
    
        articles = new ArrayList<>() ;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<Article> getArticles() throws IOException , FilerException{
        
        articles = new ArrayList<>() ;
        ArrayList lines = readArticlesFromFile();
        
        for(int i=0;i<lines.size();i++)
        {
            String[] ss= lines.get(i).toString().split(",");
            
            Article article = new Article();
            article.setAuthorName(ss[0]);
            article.setTitle(ss[1]);
            article.setDescription(ss[2]);
            
            this.articles.add(article);
        }        
        
        return this.articles;
    }
    
    public void deleteArticle(Article article) throws IOException
    {
        articles.remove(article);
        saveArticlesInFile();
    }
    
     public List<Article> getPopularArticles() throws IOException{
        HashMap<String ,Integer> Authorsmap = new HashMap(); 
        HashMap<String ,Integer> sortedAuthorsmap = new HashMap();
        List<Article> popularArticles =new ArrayList<>() ;
        
        getArticles();
        
        Authorsmap=getAuthorMap();
        
        sortedAuthorsmap = getSortedAuthorMap(Authorsmap);
        
        popularArticles=PopularArticles(sortedAuthorsmap);
        
        return popularArticles;
    } 
    
    private ArrayList readArticlesFromFile() throws IOException , FilerException
    {
        String fileName = "Articles.txt";
        String line ;
        ArrayList lines = new ArrayList();
        
        BufferedReader input = new BufferedReader(new FileReader(fileName));
            if(!input.ready())
            {
                return lines;
                //throw new IOException();
            }
            while((line=input.readLine())!= null)
            {
                lines.add(line);
            }
        input.close();
        return lines;
    }
    
    private void saveArticlesInFile() throws IOException
    {
        File fileName = new File("Articles.txt");
        FileWriter FW = new FileWriter(fileName);
        Writer output = new BufferedWriter(FW);
        
        for(int i=0;i<articles.size();i++)
        {
            output.write(articles.get(i).getAuthorName()+","+articles.get(i).getTitle()+","+articles.get(i).getDescription()+"\n");
        }
        
        output.close();
    }
    
    private HashMap<String ,Integer> getAuthorMap()
    {
        HashMap<String ,Integer> Authorsmap = new HashMap(); 
        
        for(int i=0;i<articles.size();i++)
        {
            String authorName = articles.get(i).getAuthorName();
            int c =1;
            
            if(Authorsmap.containsKey(authorName)==false)
            {
                Authorsmap.put(authorName, c);
            }
            else
            {
                Authorsmap.put(authorName, Authorsmap.get(authorName)+ 1);
            }
        }
        
        return Authorsmap;
    }
    
    private HashMap<String ,Integer> getSortedAuthorMap(HashMap<String ,Integer> Authorsmap)
    {
        
        LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
        Authorsmap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
        
        return reverseSortedMap;
    }
    
    private List<Article> PopularArticles(HashMap<String ,Integer> Authorsmap)
    {
        List<Article> popularArticles =new ArrayList<>() ;
        
        for(int i=0;i<articles.size();i++)
        {
            if(articles.get(i).getAuthorName().equals(Authorsmap.keySet().toArray()[0]))
            {
                popularArticles.add(articles.get(i));
                Authorsmap.put(articles.get(i).getAuthorName(), Authorsmap.get(articles.get(i).getAuthorName())- 1);
            }
            if(Authorsmap.get(Authorsmap.keySet().toArray()[0])==0)
            {
                Authorsmap.remove(articles.get(i).getAuthorName());
            }
            if(i==articles.size()-1 && Authorsmap.size()!=0)
            {
                i=-1;
            }
            if(Authorsmap.size() == 0)
            {
                break;
            }
       
        }
        return popularArticles;
    }
}
