using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using JournalTask.ViewModels;

namespace JournalTask.Models
{
    public class Article
    {
        public int Id { get; set; }
        public string Title { get; private set; }
        public string Description { get; private set; }
        public bool IsApproved { get; private set; }
        public string AuthorId { get; private set; }
        public ApplicationUser Author { get; set; }

        public Article()
        {

        }

        public Article(ArticleViewModel viewModel, string userId)
        {
            Title = viewModel.Title;
            Description = viewModel.Description;
            AuthorId = userId;
        }

        // and author name.
        public void Accept()
        {
            IsApproved = true;
        }
    }
}