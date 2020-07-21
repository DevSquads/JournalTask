import React from 'react';
import './App.css';
import Login from './MyComponents/Login';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Button from '@material-ui/core/Button';
import ArticlesList from './MyComponents/ArticlesList'
import CreateArticle from './MyComponents/CreateArticle';
import ArticleView from './MyComponents/ArticleView';

class App extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            componentToRender: null,
            loggedUser: null,
            mainTitle: "Journal Task",
            ChoosenView: 0,
        }
        this.setLoggedUser = this.setLoggedUser.bind(this);
        this.setComponent = this.setComponent.bind(this);
    }

    componentDidMount(){
        this.setState({componentToRender: <Login setLoggedUser = {this.setLoggedUser}/>})
    }

    setLoggedUser(user){
        this.setState({loggedUser:user, componentToRender: <ArticlesList user={user} approve={false} setComponent = {this.setComponent}/>, mainTitle: "Articles List"});
    }

    setComponent(componentName, articleData){
        let {componentToRender, mainTitle, loggedUser} = this.state;
        if(!loggedUser){return;}
        if(componentName == "Articles List"){
            componentToRender = <ArticlesList user={loggedUser} approve={false} setComponent = {this.setComponent}/>;
            mainTitle = componentName;
        }
        else if(componentName == "Articles to Approve")
        {   
            componentToRender = <ArticlesList user={loggedUser} approve={true} setComponent = {this.setComponent}/>;
            mainTitle = componentName;
        }
        else if(componentName == "Create Article")
        {   
            componentToRender = <CreateArticle user={loggedUser} setComponent = {this.setComponent}/>;
            mainTitle = componentName;
        }
        else if(componentName == "View Article")
        {   
            componentToRender = <ArticleView article={articleData}/>;
            mainTitle = "Article Details";
        }
        this.setState({componentToRender, mainTitle});
    }

    render(){
        let {componentToRender, loggedUser, mainTitle} = this.state;
        return (
            <div className="App">
                <AppBar position="static">
                    <Toolbar>
                        <div className="AppBarTitle">
                            {mainTitle}
                        </div>
                        <div className="appBarTabs">
                            <Button color="inherit" onClick={() => this.setComponent("Create Article")}>Create Article</Button>
                            <Button color="inherit" onClick={() => this.setComponent("Articles List")}>Articles List</Button>
                            {loggedUser && loggedUser.userType == 1 ? <Button color="inherit" onClick={() => this.setComponent("Articles to Approve")}> Articles to Approve </Button> : null}
                        </div>
                    </Toolbar>
                </AppBar>
                {componentToRender}
            </div>
        );
    }
}

export default App;
