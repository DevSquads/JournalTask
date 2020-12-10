from selenium import webdriver

driver_location = "/snap/bin/chromium.chromedriver"
binary_location = "/usr/bin/chromium-browser"

options = webdriver.ChromeOptions()
options.binary_location = binary_location

driver = webdriver.Chrome(executable_path=driver_location, chrome_options=options)
driver.get("http://127.0.0.1:5000")
print(driver.page_source.encode('utf-8'))
print(driver.title)
driver.quit()












