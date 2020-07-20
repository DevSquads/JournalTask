namespace JournalTask.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class SeedAdmin : DbMigration
    {
        public override void Up()
        {
            Sql(@"
INSERT INTO [dbo].[AspNetUsers] ([Id], [Email], [EmailConfirmed], [PasswordHash], [SecurityStamp], [PhoneNumber], [PhoneNumberConfirmed], [TwoFactorEnabled], [LockoutEndDateUtc], [LockoutEnabled], [AccessFailedCount], [UserName], [Name], [NOfArticles]) VALUES (N'535e45f1-839f-4a4f-bfca-9ff32c5075c8', N'Samir@test.com', 0, N'AJWveH/mYU0uwU5v5WXFf0gcOlDUWjdn7hXoSSjcfLPVrM4DXGK+4b5oVD+QXex19A==', N'03d91134-b708-42e8-9a31-6a30da76ac13', NULL, 0, 0, NULL, 1, 0, N'Samir@test.com', N'Samir', 0)
INSERT INTO [dbo].[AspNetRoles] ([Id], [Name]) VALUES (N'574de415-52e7-4fd7-bc8a-58cfc8d5a49c', N'Manger')
INSERT INTO [dbo].[AspNetUserRoles] ([UserId], [RoleId]) VALUES (N'535e45f1-839f-4a4f-bfca-9ff32c5075c8', N'574de415-52e7-4fd7-bc8a-58cfc8d5a49c')

");
        }
        
        public override void Down()
        {
        }
    }
}
