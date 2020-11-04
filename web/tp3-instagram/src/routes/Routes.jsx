import React from "react";
import Register from '../components/Register/Register'
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
                <Route path="/register" exact>
                    <Register />
                </Route>                
            </Switch>

        </Router>
    );
}