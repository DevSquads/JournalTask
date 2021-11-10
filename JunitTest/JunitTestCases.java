package JunitTest;

import Model.Article;
import Model.ArticlesDatabase;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.*;
import org.junit.Test;


public class JunitTestCases {


    @Test
    public void testCreate()
    {

        String output = ArticlesDatabase.createArticle("mariam","Hello world","desc");
        Assert.assertEquals("Model.Article created with title:Hello world",output);
        Assert.assertEquals(1, ArticlesDatabase.getArticles().size());
    }
    @Test
    public void TestDelete() {
        Article a = new Article("mariam", "Hello world", "desc");
        ArticlesDatabase.getArticles().add(a);
        String output = ArticlesDatabase.deleteArticle("Hello world", "mariam");
        Assert.assertEquals("Model.Article with title:Hello world has been deleted", output);
        Assert.assertEquals(0, ArticlesDatabase.getArticles().size());
    }

    @Test
    public void TestSorted()
    {
        List<Article>  articles =new ArrayList();
        articles.add(new Article("Mirna","title","description"));
        articles.add(new Article("Mirhan","title","description"));
        articles.add(new Article("Maram","title","description"));
        articles.add(new Article("Mirhan","title","description"));
        articles.add(new Article("Mirhan","title","description"));
        articles.add(new Article("Mariam","title","description"));
        articles.add(new Article("Maram","title","description"));
        articles.add(new Article("Mariam","title","description"));
        articles.add(new Article("Mirna","title","description"));
        ArticlesDatabase.setArticles(articles);
        ArticlesDatabase.sortArticles();
        List<Article>  Expected =new ArrayList();
        Expected.add(new Article("Maram","title","description"));
        Expected.add(new Article("Maram","title","description"));
        Expected.add(new Article("Mariam","title","description"));
        Expected.add(new Article("Mariam","title","description"));
        Expected.add(new Article("Mirhan","title","description"));
        Expected.add(new Article("Mirhan","title","description"));
        Expected.add(new Article("Mirhan","title","description"));
        Expected.add(new Article("Mirna","title","description"));
        Expected.add(new Article("Mirna","title","description"));
        Assert.assertThat(Expected,Matchers.samePropertyValueAs(ArticlesDatabase.getArticles()));




    }
}
