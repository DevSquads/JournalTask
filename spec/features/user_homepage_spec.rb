require 'rails_helper'
RSpec.describe "user logs out" do
    scenario 'home page' do
      sign_in()
      click_on 'Logout'
      expect(page).to have_current_path("/users/sign_in")
    end
end

RSpec.describe "user try to write new article" do
    scenario 'home page' do
      sign_in()
      click_on 'New article'
      expect(page).to have_current_path(new_article_path)
      expect(page).to have_text 'New Article'
    end
end


def sign_in()
    visit "users/sign_in"
    fill_in "user[email]",	with: "mostafa@gmail.com" 
    fill_in "user[password]",	with: "mostafa"
    click_button(name: "commit") 
end