using Microsoft.AspNetCore.Identity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace journal_Task.Models
{
    public class MyIdentityRoles:IdentityRole
    {
        public MyIdentityRoles()
        {

        }
        public MyIdentityRoles(string name)
        {
            this.Name = name;
        }
    }
}
