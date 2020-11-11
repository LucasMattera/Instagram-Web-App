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

    const goHome = () => {
        history.push("/home") ;
    }



    return (
        <div>
            <nav className="navbar navbar-light bg-light justify-content-between">
                <a type="submit" className="navbar-brand" onClick={goHome}>Instagram</a>
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