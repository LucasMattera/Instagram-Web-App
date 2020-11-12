import axios from 'axios' ;

var Api = {
    getUser : function() {return axios.get("http://localhost:7000/user")},
    userLike : function(id) {return axios.put(`http://localhost:7000/post/${id}/like`)},
    getUserById : function(id) {return axios.get(`http://localhost:7000/user/${id}`)},
    login : function(data) {return axios.post("http://localhost:7000/login",data)},
    register : function(data) {return axios.post("http://localhost:7000/register",data)},
} 

export default Api ;





