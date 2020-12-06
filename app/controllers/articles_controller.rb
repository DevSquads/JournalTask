class ArticlesController < ApplicationController
    def index
        @articles = Article.all
    end

    def new
        @article = Article.new
    end

    def create
        @article = Article.new(article_params)
         
        @author_name = current_user.email.split('@')[0].captalize
        
        @article.user_id = current_user.id

    end

    def destroy
    end

    private

def article_params
    params.require(:article).permit(:title , :description)
  end
end
