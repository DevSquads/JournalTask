namespace JournalTask.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class AddBasicEntityModels : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Articles",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Title = c.String(),
                        Description = c.String(),
                        IsApproved = c.Boolean(nullable: false),
                        AuthorId = c.String(maxLength: 128),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.AspNetUsers", t => t.AuthorId)
                .Index(t => t.AuthorId);
            
            AddColumn("dbo.AspNetUsers", "Name", c => c.String());
            AddColumn("dbo.AspNetUsers", "NOfArticles", c => c.Int(nullable: false));
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.Articles", "AuthorId", "dbo.AspNetUsers");
            DropIndex("dbo.Articles", new[] { "AuthorId" });
            DropColumn("dbo.AspNetUsers", "NOfArticles");
            DropColumn("dbo.AspNetUsers", "Name");
            DropTable("dbo.Articles");
        }
    }
}
