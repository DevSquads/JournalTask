require 'rails_helper'
    RSpec.describe "user signs up with already taken email" do
        scenario 'sign up page' do
          visit "users/sign_up"
          fill_in "user[email]",	with: "samir@gmail.com" 
          fill_in "user[password]",	with: "mostafa"
          fill_in "user[password_confirmation]", with: "mostafa"
          click_button 'commit' 
          expect(page).to have_text 'Email has already been taken' 
        end
    end   

    RSpec.describe "users password dont match confirm password" do
        scenario 'sign up page' do
           visit "users/sign_up"
           fill_in "user[email]",	with: "testsignup@gmail.com" 
           fill_in "user[password]",	with: "mostafa"
           fill_in "user[password_confirmation]", with: "invalidpassword"
           click_button 'commit' 
           expect(page).to have_text "Password confirmation doesn't match Password"
           expect(page).to have_current_path("/users")
        end
    end

    RSpec.describe "user signs up successfully" do
        scenario 'sign up page' do
            visit "users/sign_up"
            fill_in "user[email]",	with: "success@gmail.com" 
            fill_in "user[password]",	with: "success"
            fill_in "user[password_confirmation]", with: "success"
            click_button 'commit' 
           expect(page).to have_current_path(root_path)
           expect(page).to have_text 'Logged in'
        end
    end

    RSpec.describe "user click on log in button" do
        scenario 'sign up page' do
            visit "users/sign_up"
            click_on 'Log in'
            expect(page).to have_current_path(new_user_session_path)
        end
    end
    RSpec.describe "user enter blank password" do
        scenario 'sign up page' do
          visit "users/sign_up"
          fill_in "user[email]",	with: "ttesttest@gmail.com" 
          fill_in "user[password]",	with: " "
          fill_in "user[password_confirmation]", with: " "
          click_button 'commit' 
          expect(page).to have_text "Password can't be blank" 
          expect(page).to have_current_path("/users")
        end
    end 

    RSpec.describe "user enter blank password" do
        scenario 'sign up page' do
          visit "users/sign_up"
          fill_in "user[email]",	with: " " 
          fill_in "user[password]",	with: "mostafa"
          fill_in "user[password_confirmation]", with: "mostafa"
          click_button 'commit' 
          expect(page).to have_text "Email can't be blank" 
          expect(page).to have_current_path("/users")
        end
    end 
    
    RSpec.describe "user enter blank data" do
        scenario 'sign up page' do
          visit "users/sign_up"
          fill_in "user[email]",	with: " " 
          fill_in "user[password]",	with: " "
          fill_in "user[password_confirmation]", with: " "
          click_button 'commit' 
          expect(page).to have_text "Email can't be blank" 
          expect(page).to have_text "Password can't be blank"
          expect(page).to have_current_path("/users")
        end
    end