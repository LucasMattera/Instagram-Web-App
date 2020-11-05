import React from "react";
import Login from '../components/Login/Login'
import Register from '../components/Register/Register'
import Home from '../components/Home/Home'
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  BrowserRouter
} from "react-router-dom";

export default function Routes() {
    return (
        <Router>
            <Switch>
                <Route exact path="/" component={Home}/>
                <Route exact path="/login" component={Login}/>  
                <Route exact path="/register" component={Register}/>    
            </Switch>

        </Router>
    );
}