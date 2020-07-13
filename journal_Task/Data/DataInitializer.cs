using journal_Task.Models;
using Microsoft.AspNetCore.Identity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace journal_Task.Data
{
    public class DataInitializer
    {
        public static void SeedData(UserManager<User> userManager, RoleManager<MyIdentityRoles> roleManager)
        {
            SeedRoles(roleManager);
            SeedUsers(userManager);
        }

        public static void SeedUsers(UserManager<User> userManager)
        {

            if (userManager.FindByNameAsync("samir@app.com").Result == null)
            {
                User user = new User();
                user.UserName = "samir@app.com";
                user.Email = "samir@app.com";

                IdentityResult result = userManager.CreateAsync(user, "Admin_123").Result;

                if (result.Succeeded)
                {
                    userManager.AddToRoleAsync(user, "Admin").Wait();
                }
            }

        }

        public static void SeedRoles(RoleManager<MyIdentityRoles> roleManager)
        {
            if (!roleManager.RoleExistsAsync("User").Result)
            {
                MyIdentityRoles role = new MyIdentityRoles();
                role.Name = "User";
                IdentityResult roleResult = roleManager.CreateAsync(role).Result;
            }


            if (!roleManager.RoleExistsAsync("Admin").Result)
            {
                MyIdentityRoles role = new MyIdentityRoles();
                role.Name = "Admin";
                IdentityResult roleResult = roleManager.CreateAsync(role).Result;
            }
        }
    }
}
