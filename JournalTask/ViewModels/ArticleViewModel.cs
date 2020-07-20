using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;
using JournalTask.Models;

namespace JournalTask.ViewModels
{
    public class ArticleViewModel
    {
        public int Id { get; set; }

        [Required]
        public string Title { get; set; }
        [Required]
        public string Description { get; set; }
        


        public ArticleViewModel()
        {
            
        }

        public ArticleViewModel(int id, string title, string description)
        {
            Id = id;
            Title = title;
            Description = description;
        }

        public ArticleViewModel(Article article)
        {
            Id = article.Id;
            Title = article.Title;
            Description = article.Description;
        }
    }
}