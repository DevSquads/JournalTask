import React from "react";
import TextField from "@material-ui/core/TextField";
import "./Login.css";
import AccountCircle from "@material-ui/icons/AccountCircle";
import InputAdornment from "@material-ui/core/InputAdornment";
import Button from "@material-ui/core/Button";
import Axios from "axios";

class Login extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			userName: "",
        };
        this.handleUserNameChange = this.handleUserNameChange.bind(this);
        this.handleLogin = this.handleLogin.bind(this);
        this.addUser = this.addUser.bind(this);
	}

	handleLogin() {
		Axios.post("http://localhost:5000/users/", { userName: this.state.userName }
		).then((response) => {
            console.log(response);
			if (response.data.length) {
				this.props.setLoggedUser(response.data[0]);
			} else {
                this.addUser();
			}
		});
	}

	addUser() {
		Axios.post("http://localhost:5000/users/add",{ userName: this.state.userName, userType: 2 }).then((response) => {
            this.handleLogin();
        });
	}

	handleUserNameChange(event) {
		this.setState({ userName: event.target.value });
	}

	render() {
		return (
			<div className="LoginRoot">
				<div className="LoginContainer">
					<TextField
						value={this.state.userName}
						onChange={this.handleUserNameChange}
						fullWidth={true}
						InputProps={{
							startAdornment: (
								<InputAdornment position="start">
									<AccountCircle />
								</InputAdornment>
							),
						}}
						label="Author"
						variant="outlined"
						size="small"
						placeholder="Enter author name"
					/>
					<Button
						fullWidth={true}
						variant="contained"
						onClick={this.handleLogin}>
						Login
					</Button>
				</div>
			</div>
		);
	}
}

export default Login;
