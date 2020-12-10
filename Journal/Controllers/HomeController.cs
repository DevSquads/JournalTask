using Journal.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using static DataLibrary.BusinessLogic.ArticleProcessor;
namespace Journal.Controllers
{
    public class HomeController : Controller
    {
        
        /*public ActionResult Index()
        {
            return View();
        }*/

       /* public ActionResult About()
        {
            ViewBag.Message = "Your application description page.";

            return View();
        }

        public ActionResult Contact()
        {
            ViewBag.Message = "Your contact page.";

            return View();
        }*/
        public ActionResult BanCreate()
        {

            return View();
        }
        public ActionResult Login()
        {
            ViewBag.Message = "User Login";
            Session["Name"] = "";
            return View();
        }
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Login(UserModel model)
        {
            Session["Name"] = model.username;
            return RedirectToAction("ViewArticles");
        }

        public ActionResult CreateArticle()
        {
            if (Session["Name"].ToString() == "samir")
            {
                ViewBag.Message = "Create An Article";
                return View();
            }
            else
            {
                return RedirectToAction("BanCreate");
            }
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult CreateArticle(ArticleModel model)
        {
           
            int recordsCreated =CreateNewArticle(model.title, model.description, model.authorName);
           
            return RedirectToAction("Index");
        }
        public ActionResult ViewArticles()
        {
            ViewBag.Message = "Articles List";
            var data = LoadArticles();
            List<Models.ArticleModel> articles= new List<Models.ArticleModel>();
            foreach (var row in data)
            {
                articles.Add(new Models.ArticleModel
                {
                    title = row.articleTitle,
                    description = row.articleDescription,
                    authorName = row.articleAuthorName
                });
            }
            Dictionary<string, int> Num_of_articles = new Dictionary<string, int>();
            foreach (Models.ArticleModel row in articles)
            {
                if(Num_of_articles.ContainsKey(row.authorName) == true)
                {
                    Num_of_articles[row.authorName]++;
                }
                else
                {
                    Num_of_articles.Add(row.authorName, 0);
                }
                    
            }
            List<Models.ArticleModel> sorted_articles = new List<Models.ArticleModel>();
            foreach (var item in Num_of_articles.OrderByDescending(key => key.Value))
            {
                foreach (Models.ArticleModel row in articles)
                {
                    if(row.authorName ==item.Key)
                    {
                        sorted_articles.Add(row);
                    }
                        
                }
            }
            List<Models.ArticleModel> final_articles = new List<Models.ArticleModel>();
            foreach (Models.ArticleModel row in sorted_articles)
            {
               
                    if (row.authorName == Session["Name"].ToString())
                    {
                        final_articles.Add(row);
                    }

               
            }
            foreach (Models.ArticleModel row in sorted_articles)
            {
                if (row.authorName != Session["Name"].ToString())
                {
                    final_articles.Add(row);
                }
            }
             return View(final_articles);
        }
        public ActionResult Delete(string articleTitle)
        {
            int recordsDeleted = DeleteArticle(articleTitle);
            return RedirectToAction("ViewArticles");
        }
        
    }
}