import React from 'react' ;
import { useHistory } from "react-router-dom";
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

    return (
        <div>
            <nav className="navbar navbar-light bg-light justify-content-between">
                <a className="navbar-brand" href="/">Instagram</a>
                <form className="form-inline">
                <input className="form-control search" type="search" placeholder="Buscar..." aria-label="Search" />
                </form>
                <button type="submit" className="navbar-profile" onClick={goProfile}>Profile</button>
                <button type="submit" onClick={logout}>Logout</button>
            </nav>
        </div>


    )
}

export default Navbar ;