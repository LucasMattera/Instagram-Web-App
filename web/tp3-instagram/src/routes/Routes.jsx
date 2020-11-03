import React from "react";
import Login from '../components/Login/Login'
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
                <Route path="/login" exact>
                    <Login />
                </Route>                
            </Switch>

        </Router>
    );
}