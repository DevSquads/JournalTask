require 'rails_helper'

RSpec.describe "admin try to logout" do
    scenario 'home page' do
    sign_in_as_admin()
    click_on 'Logout'
    expect(page).to have_current_path("/users/sign_in")
    end
end

RSpec.describe "admin try to create new article" do
    scenario 'home page' do
    sign_in_as_admin()
    expect(page).to have_current_path(root_path)
    expect(page).not_to have_text('pending for approve articles')
    create_article()
    expect(page).to have_current_path(root_path)
    expect(page).to have_text('pending for approve articles')
    expect(page).to have_text('testing title')
    end
end

RSpec.describe "admin try to create new article and approve it" do
    scenario 'home page' do
    sign_in_as_admin()
    create_article()
    expect(page).to have_current_path(root_path)
    expect(page).to have_text('pending for approve articles')
    expect(page).to have_text('testing title')
    click_on 'approve'
    expect(page).not_to have_text('pending for approve articles')
    end
end

RSpec.describe "admin try to create new article and delete it before approve" do
    scenario 'home page' do
    sign_in_as_admin()
    create_article()
    expect(page).to have_current_path(root_path)
    expect(page).to have_text('pending for approve articles')
    expect(page).to have_text('testing title')
    click_on 'Delete'
    expect(page).to have_current_path(root_path)
    expect(page).not_to have_text('pending for approve articles')
    expect(page).not_to have_text('testing title')
    end
end

RSpec.describe "admin try to create new article and approve it then delete it" do
    scenario 'home page' do
    sign_in_as_admin()
    create_article()
    expect(page).to have_current_path(root_path)
    expect(page).to have_text('pending for approve articles')
    expect(page).to have_text('testing title')
    click_on 'approve'
    expect(page).to have_current_path(root_path)
    expect(page).not_to have_text('pending for approve articles')
    expect(page).to have_text('testing title')
    click_on 'Delete'
    expect(page).to have_current_path(root_path)
    expect(page).not_to have_text('pending for approve articles')
    expect(page).not_to have_text('testing title')
    end
end

private

def sign_in_as_admin()
    visit "users/sign_in"
    fill_in "user[email]",	with: "samir@gmail.com" 
    fill_in "user[password]",	with: "mostafa96"
    click_button(name: "commit") 
end

def create_article()
    visit '/articles/new'
    fill_in "article[title]",	with: "testing title" 
    fill_in "article[description]",	with: "testing description"
    click_on 'Create Article'
end