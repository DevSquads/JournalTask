using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace journal_Task.Models
{
    public class Article
    {
        [Key]
        public int ID { get; set; }
        public string AuthorName { get; set; }
        public string Title { get; set; }
        public string Description { get; set; }
        public bool IsApproved { get; set; }
        public virtual User user { get; set; }
    }
}
