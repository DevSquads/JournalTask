require 'rails_helper'
RSpec.describe "user signs up" do
    scenario 'sign up page' do
       visit "users/sign_up"
       fill_in "user[email]",	with: "samir@gmail.com" 
       fill_in "user[password]",	with: "mostafa"
       fill_in "user[password_confirmation]", with: "mostafa"
       click_button 'commit' 
    end
    
end
