class ArticlesController < ApplicationController

    before_action :authenticate_user!
    before_action :require_admin, only: [:destroy, :approve]

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
      @article = Article.find(params[:id])
      @article.destroy
      redirect_to root_path, :notice => "The article has been deleted"
    end


    def approve
      @article = Article.find(params[:id])
      @article.toggle!(:approved)
      redirect_to root_path, :notice => "The article has been approved"
    end


    private

def article_params
    params.require(:article).permit(:title , :description , :author_name)
  end
  def require_admin
		if current_user.admin? != true
			flash[:danger] = "You not allowed to do this only samir can do it"
			redirect_to root_path
    end
  end
end
