import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import App from './App';

const puppeteer = require('puppeteer');
describe("Create Article Test", () => {
  test('Create Article', async () => {
    let browser = await puppeteer.launch({
      headless: false,
      devtools: true,
      slowMo: 250
    });
    let page = await browser.newPage();
    
    page.emulate({
      viewport: {
        width: 500,
        height: 900
      },
      userAgent: ''
    });    
    const data = {title: "My Post", description: "My Description", author:"My Name" }
    await page.goto('http://localhost:3000/');
    await page.click("button[id=createArticleBtn]");
    await page.type("input[id=title]", data.title);
    await page.type("input[id=description]", data.description);
    await page.type("input[id=author]", data.author);
    await page.click("button[id=createBtn]");
    browser.close();
  }, 9000000);
  afterAll(() => {
      const fs = require('fs');
      let rawdata = fs.readFileSync('C:/Users/hp/Projects/journal-task/backend/database.json');
      let dataa = JSON.parse(rawdata);
      dataa["Database"].splice(dataa["Database"].length-1, 1);
      dataa = JSON.stringify(dataa);
      fs.writeFileSync('C:/Users/hp/Projects/journal-task/backend/database.json', dataa);
  });
  
});

describe("Show Reader's Articles", () => {
  test('Reader', async () => {
    let browser = await puppeteer.launch({
      headless: false,
      devtools: true,
      slowMo: 250
    });
    let page = await browser.newPage();
    
    page.emulate({
      viewport: {
        width: 500,
        height: 900
      },
      userAgent: ''
    });    
    await page.goto('http://localhost:3000/');
    await page.click("button[id=showArticlesBtn]");
    await page.click("button[id=reader]");
    browser.close();
  }, 9000000);


});
describe("Show Author's Articles", () => {
  test('Author', async () => {
    let browser = await puppeteer.launch({
      headless: false,
      devtools: true,
      slowMo: 250
    });
    let page = await browser.newPage();
    
    page.emulate({
      viewport: {
        width: 500,
        height: 900
      },
      userAgent: ''
    });    
    await page.goto('http://localhost:3000/');
    await page.click("button[id=showArticlesBtn]");
    await page.click("button[id=author]");
    await page.waitForSelector('#authorName', {
      visible: true,
    })
    await page.type("input[id=authorName]", 'Mohamed');
    browser.close();
  }, 9000000);
});

describe("Delete Articles", () => {
  var obj={}
  beforeAll(() => {
      const fs = require('fs');
      let rawdata = fs.readFileSync('C:/Users/hp/Projects/journal-task/backend/database.json');
      let dataa = JSON.parse(rawdata);
      obj=dataa["Database"][0]
  })
  test('Delete', async () => {
    let browser = await puppeteer.launch({
      headless: false,
      devtools: true,
      slowMo: 250
    });
    let page = await browser.newPage();
    
    page.emulate({
      viewport: {
        width: 500,
        height: 900
      },
      userAgent: ''
    });    
    await page.goto('http://localhost:3000/');
    await page.click("button[id=showArticlesBtn]");
    await page.click("button[id=author]");
    await page.waitForSelector('#authorName', {
      visible: true,
    })
    await page.type("input[id=authorName]", 'Ahmed');
    await page.click("button[id=deleteBtn]");

    browser.close();
  }, 9000000);
  afterAll(() => {
      const fs = require('fs');
      let rawdata = fs.readFileSync('C:/Users/hp/Projects/journal-task/backend/database.json');
      let dataa = JSON.parse(rawdata);
      dataa["Database"].push(obj)
      dataa = JSON.stringify(dataa);
      fs.writeFileSync('C:/Users/hp/Projects/journal-task/backend/database.json', dataa);
  });
});