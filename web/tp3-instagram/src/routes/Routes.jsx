import React from "react";
import Login from '../components/Login/Login'
import Register from '../components/Register/Register'
import Home from '../components/Home/Home'
import PublicRoute from './PublicRoute'
import PrivateRoute from './PrivateRoute'
import {
  BrowserRouter as Router,
  Switch,
} from "react-router-dom";

export default function Routes() {
    return (
        <Router>
            <Switch>
                <PublicRoute path="/login" component={Login} />
                <PublicRoute path="/register" component={Register} />
                <PrivateRoute path="/" component={Home} />
                <PrivateRoute path="*" component={Home} /> 
            </Switch>

        </Router>
    );
}