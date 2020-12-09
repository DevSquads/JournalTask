require 'rails_helper'


RSpec.describe "user try to logout" do
    scenario 'new article page' do
    sign_in_to_new_article_page()
    click_on 'Logout'
    expect(page).to have_current_path("/users/sign_in")
    end
end

RSpec.describe "user try to go back to index page" do
    scenario 'new article page' do
    sign_in_to_new_article_page()
    click_on 'Back'
    expect(page).to have_current_path(articles_path)
    expect(page).to have_text('Authors articles')
    end
end

RSpec.describe "user creates new article successfully" do
    scenario 'new article page' do
    sign_in_to_new_article_page()
    fill_in "article[title]",	with: "testing title" 
    fill_in "article[description]",	with: "testing description"
    click_on 'Create Article'
    expect(page).to have_current_path(root_path)
    expect(page).to have_text('Authors articles')
    end
end

RSpec.describe "user creates empty title" do
    scenario 'new article page' do
    sign_in_to_new_article_page()
    fill_in "article[title]",	with: " " 
    fill_in "article[description]",	with: "testing description"
    click_on 'Create Article'
    expect(page).to have_text('New Article')
    expect(page).not_to have_text('Authors articles')
    end
end

def sign_in_to_new_article_page()
    visit "users/sign_in"
    fill_in "user[email]",	with: "mostafa@gmail.com" 
    fill_in "user[password]",	with: "mostafa"
    click_button(name: "commit") 
    click_on 'New article'

end