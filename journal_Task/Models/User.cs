using Microsoft.AspNetCore.Identity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace journal_Task.Models
{
    public class User:IdentityUser
    {
        public User()
        {
            Articles = new HashSet<Article>();
        }

        public int ArticleCount { get; set; }
        public virtual ICollection<Article> Articles { get; set; }
    }
}
