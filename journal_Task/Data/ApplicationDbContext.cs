using System;
using System.Collections.Generic;
using System.Text;
using journal_Task.Models;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;

namespace journal_Task.Data
{
    public class ApplicationDbContext : IdentityDbContext<User, MyIdentityRoles, string>
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options)
        {
        }

        public DbSet<User> MyUsers { get; set; }
        public DbSet<Article> Articles { get; set; }
    }
}
