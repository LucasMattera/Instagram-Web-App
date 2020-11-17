import axios from 'axios' ;

var Api = {
    getUser : function() {return axios.get("http://localhost:7000/user")},
    userLike : function(id) {return axios.put(`http://localhost:7000/post/${id}/like`)},
    getUserById : function(id) {return axios.get(`http://localhost:7000/user/${id}`)},
    getPostById : function(id) {return axios.get(`http://localhost:7000/post/${id}`)},
    login : function(data) {return axios.post("http://localhost:7000/login",data)},
    register : function(data) {return axios.post("http://localhost:7000/register",data)},
    userFollow : function(id) {return axios.put(`http://localhost:7000/user/${id}/follow`)},
    search : function(data) {return axios.get(`http://localhost:7000/search?q=${data}`)},
    postComment : function(id,data,headers) {return axios.post(`http://localhost:7000/post/${id}/comment`,data,headers)},
} 

export default Api ;





