import './App.css';
import Home from './pages/home'
import ShowArticles from './pages/showArticles'
import CreateArticle from './pages/createArticle'

import {BrowserRouter as Router, Route, Switch} from "react-router-dom";


function App() {
  return (
    <div>
         <Router>
              <Switch>
                  <Route exact path="/" component={Home}/>
                  <Route path="/showArticles" component={ShowArticles}/>
                  <Route path="/createArticle" component={CreateArticle}/>
              </Switch>
         </Router>
      
    </div>
  );
}

export default App;
