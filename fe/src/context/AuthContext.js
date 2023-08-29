import React, { createContext, useEffect, useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom';
import { notification } from 'antd';
import {Trans, useTranslation} from "react-i18next";
import getUser from '../services/getUser';


const AuthContext = createContext('test');

const AuthProvider = ({children})=>{

const [currentUser,setCurrentUser] = useState('');

const { t } = useTranslation();
let router = useLocation();
let navigate = useNavigate();
const login = async(username,password) =>{
    try{
        const response = await getUser(username,password);
        const user = response.data
        setCurrentUser(user); 
        return  true;
    
    }catch(error){
        notification.open({
            message:<Trans t ={t}>error.message</Trans>,
            description:<Trans t ={t}>error.description</Trans>,
            placement:"topRight",
            type:"error"
        });
        return  false;
    }
    
};

const logout = ()=>{
    setCurrentUser(null);
};
useEffect(()=> {
    if(currentUser === ''&& router.pathname !=="/login" ){
        navigate("/login");
    }
});




return (
    <AuthContext.Provider value={{currentUser,login,logout}}>
        {children}
    </AuthContext.Provider>
);
};



export {AuthContext,AuthProvider};
