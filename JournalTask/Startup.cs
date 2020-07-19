using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(JournalTask.Startup))]
namespace JournalTask
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
