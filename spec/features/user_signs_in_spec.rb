require 'rails_helper'
RSpec.describe "user signs in successfully" do
    scenario 'login page' do
       visit "users/sign_in"
       fill_in "user[email]",	with: "samir@gmail.com" 
       fill_in "user[password]",	with: "mostafa96"
       click_button(name: "commit") 
       expect(page).to have_text 'Logged in'    
    end
end
    RSpec.describe "user enter empty email or passowrd" do
        scenario 'empty login page' do
           visit "users/sign_in"
           fill_in "user[email]",	with: " " 
           fill_in "user[password]",	with: "mostafa96"
           click_button(name: "commit") 
           expect(page).to have_current_path(new_user_session_path)
        end
    end

    RSpec.describe "user enter invalid user" do
        scenario 'invalid login page' do
           visit "users/sign_in"
           fill_in "user[email]",	with: "invaliduser@invalid.com " 
           fill_in "user[password]",	with: "invalidinvalid"
           click_button(name: "commit") 
           expect(page).to have_current_path(new_user_session_path)
        end
    end

    RSpec.describe "user click on signup button" do
        scenario 'redirect to signup page' do
           visit "users/sign_in"
           click_on 'Sign up'
           expect(page).to have_current_path(new_user_registration_path)
        end
    end
