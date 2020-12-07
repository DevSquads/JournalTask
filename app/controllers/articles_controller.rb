class ArticlesController < ApplicationController

    before_action :authenticate_user!

    def index
        @articles = Article.all
    end

    def new
      
        @article = Article.new
        
    end

    def create
        @article = Article.new(article_params)
         
        @article.author_name = current_user.email.split('@')[0].capitalize!
        
        @article.user_id = current_user.id
        
        if @article.save
           
            redirect_to root_path
          else
            render 'new'
          end

    end

    def destroy
    end

    private

def article_params
    params.require(:article).permit(:title , :description , :author_name)
  end
end
