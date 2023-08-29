import axios from "axios";

const getUser = async(username,password)=>
{
    return  await axios.post('http://localhost:8080/auth/signin',{username,password}) ;
}

export default getUser;