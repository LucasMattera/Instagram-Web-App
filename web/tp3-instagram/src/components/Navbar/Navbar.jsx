import React, { useState } from 'react' ;
import { useHistory, Link } from "react-router-dom";
import '../../styles/Navbar.css'

const Navbar = () => {
    const history = useHistory();
    const [search,setSearch] = useState(" ")

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

    const changeSearch = (event) => setSearch(event.target.value);

    console.log(search)



    return (
        <div>
            <nav className="navbar navbar-light bg-light justify-content-between">
                <a type="submit" className="navbar-brand" onClick={goHome}>Instagram</a>
                <form className="form-inline">
                    <input className="form-control search" placeholder="Buscar..." aria-label="Search" onChange={changeSearch} />
                    <Link to={"/search/"+search}>
                        <button className="btn btn-outline-success my-2 my-sm-0" type="submit" hidden="hidden">Search</button>
                    </Link>
                </form>
                <button type="submit" className="navbar-profile" onClick={goProfile}>Profile</button>
                <button type="submit" onClick={logout}>Logout</button>
            </nav>
        </div>


    )
}

export default Navbar ;