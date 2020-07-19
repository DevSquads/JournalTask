import React from 'react';
import './App.css';
import Login from './MyComponents/Login';
import Typography from '@material-ui/core/Typography';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import { makeStyles } from '@material-ui/core/styles';
import ArticleView from './MyComponents/ArticleView';

const useStyles = makeStyles({
    root: {
        backgroundColor: "#2196f3",
        color: "white",
        fontWeight: "bold"
    }
});

function App() {
    const classes = useStyles();
 return (
    <div className="App">
        <AppBar classes={{root: classes.root}} position="static">
            <Toolbar>
                <div className="AppBarTitle">
                    Journal Task
                </div>
            </Toolbar>
        </AppBar>
        <ArticleView/>
    </div>
  );
}

export default App;
