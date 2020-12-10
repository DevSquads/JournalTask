using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataLibrary.Models
{
    public class ArticleModel
    {
        public int Id { get; set; }
        public string articleTitle { get; set; }
        public string articleDescription { get; set; }

        public string articleAuthorName { get; set; }

    }
}
