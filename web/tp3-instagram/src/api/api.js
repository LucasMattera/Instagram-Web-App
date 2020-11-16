import axios from 'axios' ;

var Api = {
    getUser : function() {return axios.get("http://localhost:7000/user")},
    userLike : function(id) {return axios.put(`http://localhost:7000/post/${id}/like`)},
    getUserById : function(id) {return axios.get(`http://localhost:7000/user/${id}`)},
    getPostById : function(id) {return axios.get(`http://localhost:7000/post/${id}`)},
    login : function(data) {return axios.post("http://localhost:7000/login",data)},
    register : function(data) {return axios.post("http://localhost:7000/register",data)},
    userFollow : function(id) {return axios.put(`http://localhost:7000/user/${id}/follow`)},
    search : function() {return axios.get("http://localhost:7000/search")},
    postComment : function(id,data) {return axios.post(`http://localhost:7000/post/${id}/comment`,data)},
} 

export default Api ;





