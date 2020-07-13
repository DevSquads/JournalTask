using Microsoft.EntityFrameworkCore.Migrations;

namespace journal_Task.Data.Migrations
{
    public partial class authorEdit : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<string>(
                name: "AuthorName",
                table: "Articles",
                nullable: true);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "AuthorName",
                table: "Articles");
        }
    }
}
