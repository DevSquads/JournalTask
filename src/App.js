import React from 'react';
import './App.css';
import Login from './MyComponents/Login';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import { makeStyles } from '@material-ui/core/styles';
import ArticleView from './MyComponents/ArticleView';
import ArticlesList from './MyComponents/ArticlesList'

class App extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            componentToRender: <Login/>
        }
    }

    render(){
        let {componentToRender} = this.state;
        return (
            <div className="App">
                <AppBar position="static">
                    <Toolbar>
                        <div className="AppBarTitle">
                            Journal Task
                        </div>
                    </Toolbar>
                </AppBar>
                {componentToRender}
            </div>
        );
    }
}

export default App;
