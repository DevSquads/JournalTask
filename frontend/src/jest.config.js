module.exports = {
    preset: "jest-puppeteer",
    globals: {
      URL: "http://localhost:8082"
    },
    testMatch: [
      "<path-to-the-tests-folder>/**.test.js"
    ],
    verbose: true
  };