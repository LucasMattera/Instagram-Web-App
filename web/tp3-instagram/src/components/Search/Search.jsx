import React, { useState, useEffect} from "react";
import {useLocation} from 'react-router-dom';
import Navbar from '../Navbar/Navbar'
import Api from "../../api/api";
import { Link } from "react-router-dom";
import '../../styles/Search.css';

const Search = () => {
    const [searchData,userSearchData] = useState([])
    const location = useLocation() ;
    const newLocation = location.search.slice(3) || location.hash.replace("#","%23")

    useEffect(() => {   
    Api.search(newLocation)
        
        .then(success => {
            
            if (newLocation[0] === "%") {
                userSearchData(success.data.posts)
            } else {
                userSearchData(success.data.users)
            }
        })
        .catch(error => {
            console.log(error)
        }) 

    },[location])


    

    function handleContent(data) {
        if(newLocation[0] === "%"){
            return (
            <Link to={`/post/${data.id}`}>
                <div className="post text-center">
                    <div className="dataImage">
                        <img className="data_Image" src={data.landscape}/>
                    </div>
                </div>
            </Link>
            )}
        else{
            return (
            <Link to={`/user/${data.id}`}>    
                <div className="user text-center">
                    <div className="dataImage_">               
                        <img className="data_Imagee" src={data.image}/>
                    </div>
                    <div className="dataName">
                        <p>{data.name}</p>
                    </div>
                </div>
            </Link>
            
            )}
    }
    

    return(
        <div>
        <Navbar />
            <div className="container-fluid">
                <div className="row">
                    { searchData.map(data => (               
                        <div className="card col-md-4 col-sm-12">                
                            {handleContent(data)}                
                        </div>
                    ))}
                </div>
            </div>
        </div>

    

    )



}


export default Search ;