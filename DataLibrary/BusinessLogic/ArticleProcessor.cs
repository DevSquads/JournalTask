using DataLibrary.DataAccess;
using DataLibrary.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataLibrary.BusinessLogic
{
    
    public static class ArticleProcessor
    {
        public static int CreateNewArticle(string title , string description , string author)
        {
            ArticleModel data = new ArticleModel
            {
                
                articleTitle = title,
                articleDescription = description,
                articleAuthorName = author
            };
            string sql = @"insert into dbo.Article (articleTitle, articleDescription,articleAuthorName) values (@articleTitle ,@articleDescription, @articleAuthorName);";
            return SqlDataAccess.SaveData(sql, data);
        }
        public static List<ArticleModel> LoadArticles()
        {
            string sql = @"select articleTitle, articleDescription , articleAuthorName from dbo.Article;";
            return SqlDataAccess.LoadData<ArticleModel>(sql);
        }
        public static int DeleteArticle(string title)
        {
            ArticleModel data = new ArticleModel
            {
                articleTitle = title,
                articleDescription = "",
                articleAuthorName = ""
            };
            string sql = @"delete from dbo.Article where articleTitle = @articleTitle;";
            return SqlDataAccess.SaveData(sql, data);
        }
    }
}
