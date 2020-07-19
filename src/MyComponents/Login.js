import React from 'react';
import TextField from '@material-ui/core/TextField';
import './Login.css';
import AccountCircle from '@material-ui/icons/AccountCircle';
import InputAdornment from '@material-ui/core/InputAdornment';
import Button from '@material-ui/core/Button';

function Login(){
    return (
        <div className="LoginRoot">
            <div className="LoginContainer">
                <TextField  fullWidth = {true} InputProps = {{ startAdornment:(<InputAdornment position="start">
                <AccountCircle />
                </InputAdornment>)
                }} label="Author" variant="outlined" size="small" placeholder="Enter author name"/>
                <Button fullWidth={true} variant="contained">
                    Login
                </Button>
            </div>
        </div>
    )
}

export default Login;