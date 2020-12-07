class ArticlesController < ApplicationController

    before_action :authenticate_user!
    before_action :require_admin, only: [:destroy, :approve]

    def index
      @myarticles = Article.all.where(user_id: current_user.id)
      @myarticles = @myarticles.where(approved: true).reverse

      @unapproved_articles = Article.all.where(approved: false)
        # @articles =Article.all.where(approved: true).where.not(user_id: current_user.id)
        @articles = []
        all_users = User.order('approved_articles DESC').where.not(id: current_user.id)
        all_users.each do |user|
          user.articles.where(approved: true).reverse.each do |article|
          @articles << article
          end
        end
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
      @author = User.where(id: @article.user_id).last
      if @article.approved? != true
         redirect_to root_path, :notice => "The article has been deleted"
      else
        @author.approved_articles -=1
        @author.save
        redirect_to root_path, :notice => "The article has been deleted"
      end
    end


    def approve
      @article = Article.find(params[:id])
      @article.toggle!(:approved)  
      @author = User.where(id: @article.user_id).last
      @author.approved_articles +=1
      @author.save

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
