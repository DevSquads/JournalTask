from selenium import webdriver
from getpass import getpass


driver_location = "/snap/bin/chromium.chromedriver"
binary_location = "/usr/bin/chromium-browser"

options = webdriver.ChromeOptions()
options.binary_location = binary_location

driver = webdriver.Chrome(executable_path=driver_location, chrome_options=options)
driver.get("http://127.0.0.1:5000")
print(driver.page_source.encode('utf-8'))

#A quick Test for the site title
file1=open("E2E_TestResults.txt", "w+")

print(driver.title)

#==========================================================#

file1.write("Test_1 \n")
file1.write("Title:\n")
file1.write(driver.title)
file1.write("\n========================================================\n")

#==========================================================#

username = input("Enter in your username: ") # write the following in terminal : dalia@gmail.com
password = getpass("Enter your password: ")  # write the following in terminal : dalia

file1.write("Test_2 \n")
file1.write("New Post:\n") 

username_textbox = driver.find_element_by_id("email")
username_textbox.send_keys(username)

password_textbox = driver.find_element_by_id("password")
password_textbox.send_keys(password)

login_button = driver.find_element_by_id("submit_login")
login_button.submit()



#file1.write()
file1.write("\n========================================================\n")



username = browser.find_element_by_id('User.username')
username.send_keys('dalia@gmail.com')





driver.quit()













