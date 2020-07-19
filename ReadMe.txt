Native PHP used as the main programming langyage for this backend
U need Sql server & create database call it "dev_test";
"dev_test" have 2 tables: 
      1- articales [name'text' - Description 'text' - author'text' - visable 'boolean']

      2- users [name 'text' => unique  , password'text , role 'text]

//////////////////////////////////////////////////////////////////
test:

1- send post request for "request.js"  containing "script_name"[target] & 'data' contining paramters
   -paramters have all data that the target script is need it dependence in the script u will call. 

2- request.js call index.php then index.php handling errors & call the target script.

/////////////////////////////////                    