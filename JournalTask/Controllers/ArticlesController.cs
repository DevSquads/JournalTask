﻿using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;
using JournalTask.Models;
using JournalTask.ViewModels;
using Microsoft.AspNet.Identity;

namespace JournalTask.Controllers
{
    public class ArticlesController : Controller
    {
        private readonly ApplicationDbContext _context;
        public ArticlesController()
        {
            _context = new ApplicationDbContext();

        }

        // GET: Articles
        public ActionResult Index()
        {
            ViewData["userId"] = User.Identity.GetUserId();

            var articles = _context.Articles
                .Where(a => a.IsApproved)
                .OrderByDescending(a => a.Author.NOfArticles)
                .Include(a => a.Author)
                .ToList();

            return View(articles);
        }

        // GET: Articles/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Article article = _context.Articles.Find(id);
            if (article == null)
            {
                return HttpNotFound();
            }
            return View(article);
        }

        [Authorize]
        // GET: Articles/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Articles/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Authorize]

        public async Task<ActionResult> Create(ArticleViewModel viewModel)
        {
            if (!ModelState.IsValid)
            {
                return View(viewModel);
            }

            var userId = User.Identity.GetUserId();
            var user = _context.Users.FirstOrDefault(u => u.Id == userId);

            var article = new Article(viewModel, userId);
            user.NewArticle(article);
            
            _context.Articles.Add(article);
            _context.SaveChanges();

            //_context.SaveChanges();
            return RedirectToAction("Index");
        }


        // GET: Articles/Edit/5
        [Authorize(Roles = "Manger")]
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Article article = _context.Articles.Find(id);
            if (article == null)
            {
                return HttpNotFound();
            }
            return View(article);
        }

        // POST: Articles/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Authorize(Roles = "Manger")]
        public ActionResult Edit([Bind(Include = "Id,Title,Description,Author")] Article article)
        {
            if (ModelState.IsValid)
            {
                _context.Entry(article).State = EntityState.Modified;
                _context.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(article);
        }

        // GET: Articles/Delete/5
        [Authorize(Roles = "Manger")]

        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }

            Article article = _context.Articles.Find(id);
            if (article == null)
            {
                return HttpNotFound();
            }


            return View(article);
        }

        // POST: Articles/Delete/5
        [Authorize(Roles = "Manger")]
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            var userId = User.Identity.GetUserId();
            var user = _context.Users.FirstOrDefault(u => u.Id == userId);

            user.DeleteArticle();

            Article article = _context.Articles.Find(id);
            _context.Articles.Remove(article);
            _context.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                _context.Dispose();
            }
            base.Dispose(disposing);
        }


    }
}
