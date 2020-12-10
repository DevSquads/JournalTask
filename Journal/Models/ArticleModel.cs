using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Journal.Models
{
    public class ArticleModel
    {
        public string title { get; set; }
        public string description { get; set; }

        public string authorName { get; set; }
    }
}