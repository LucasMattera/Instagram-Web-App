import React, { useEffect } from 'react' ;
import { useHistory } from "react-router-dom";
import Api from '../../api/api';
import '../../styles/Navbar.css'

const Navbar = () => {
    const history = useHistory();

    const logout = () => {
        localStorage.removeItem("userData");
        localStorage.removeItem("token");
        history.push("/");
      };

    const goProfile = () => {
        history.push("/profile") ;
    }

    const goHome = () => {
        history.push("/home");
    }

    const searchChanged = (event) => {
        setState({ search: event.target.value })
    }

    return (
        <div>
            <search></search>
            <nav className="navbar navbar-light bg-light justify-content-between">
                <a type="submit" className="navbar-brand" onClick={goHome}>Instagram</a>
                <form className="form-inline">
                <input className="form-control search" type="search" value = {searchChanged} placeholder="Buscar..." aria-label="Search" onChange = { () => Api.search()} />
                <select name = "Perfil">
                    <option type="submit" className="navbar-profile" onClick={goProfile}>Profile</option>
                    <option type="submit" onClick={logout}>Logout</option>
                </select>
                </form>
            </nav>
        </div>


    )
}

export default Navbar ;